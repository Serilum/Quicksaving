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
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ToServerTeleportPlayerPacket {
    public static final Identifier CHANNEL = Identifier.fromNamespaceAndPath(Reference.MOD_ID, "to_server_teleport_player_packet");

    private final Vec3 teleportLocation;
    private final ResourceKey<Level> teleportDimension;

    public ToServerTeleportPlayerPacket(Vec3 teleportLocationIn, ResourceKey<Level> teleportDimensionIn) {
        this.teleportLocation = teleportLocationIn;
        this.teleportDimension = teleportDimensionIn;
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
            ToServerTeleportPlayerPacket packet = ctx.message();
            Player player = ctx.sender();

			if (ConfigHandler.musthaveCheatAccessForQuickloadOnServer && !player.permissions().hasPermission(Permissions.COMMANDS_ADMIN)) {
				MessageFunctions.sendMessage(player, "With the current server configuration, you may only quickload with cheat access enabled.", ChatFormatting.RED);
				return;
			}

            if (!player.hasEffect(MobEffects.SLOW_FALLING)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 255, true, false));
            }

            if (player.level().dimension().equals(packet.teleportDimension)) {
                player.teleportTo(packet.teleportLocation.x, packet.teleportLocation.y, packet.teleportLocation.z);
            }
            else {
                Services.TELEPORT.teleportEntity(player, packet.teleportDimension, packet.teleportLocation);
            }

            player.displayClientMessage(Component.literal("Quickloaded.").withStyle(ChatFormatting.DARK_GREEN), true);
        }
    }
}
