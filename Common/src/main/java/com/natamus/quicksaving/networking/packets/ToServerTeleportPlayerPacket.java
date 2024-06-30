package com.natamus.quicksaving.networking.packets;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.implementations.networking.data.PacketContext;
import com.natamus.collective.implementations.networking.data.Side;
import com.natamus.collective.services.Services;
import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.util.Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ToServerTeleportPlayerPacket {
    public static final ResourceLocation CHANNEL = new ResourceLocation(Reference.MOD_ID, "to_server_teleport_player_packet");

    private static Vec3 teleportLocation;
    private static ResourceKey<Level> teleportDimension;

    public ToServerTeleportPlayerPacket(Vec3 teleportLocationIn, ResourceKey<Level> teleportDimensionIn) {
        teleportLocation = teleportLocationIn;
        teleportDimension = teleportDimensionIn;
    }

    public static ToServerTeleportPlayerPacket decode(FriendlyByteBuf buf) {
        Vec3 teleportLocationIn = buf.readVec3();
        ResourceKey<Level> teleportDimensionIn = buf.readResourceKey(Registries.DIMENSION);

        return new ToServerTeleportPlayerPacket(teleportLocationIn, teleportDimensionIn);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVec3(teleportLocation);
        buf.writeResourceKey(teleportDimension);
    }

    public static void handle(PacketContext<ToServerTeleportPlayerPacket> ctx) {
        if (ctx.side().equals(Side.SERVER)) {
            Player player = ctx.sender();

			if (ConfigHandler.musthaveCheatAccessForQuickloadOnServer && !player.hasPermissions(2)) {
				MessageFunctions.sendMessage(player, "With the current server configuration, you may only quickload with cheat access enabled.", ChatFormatting.RED);
				return;
			}

            if (!player.hasEffect(MobEffects.SLOW_FALLING)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 255, true, false));
            }

            if (player.level().dimension().equals(teleportDimension)) {
                player.teleportTo(teleportLocation.x, teleportLocation.y, teleportLocation.z);
            }
            else {
                Services.TELEPORT.teleportEntity(player, teleportDimension, teleportLocation);
            }

            player.displayClientMessage(Component.literal("Quickloaded.").withStyle(ChatFormatting.DARK_GREEN), true);
        }
    }
}
