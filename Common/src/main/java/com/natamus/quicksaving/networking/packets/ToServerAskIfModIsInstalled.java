package com.natamus.quicksaving.networking.packets;

import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.natamus.collective.implementations.networking.data.PacketContext;
import com.natamus.collective.implementations.networking.data.Side;
import com.natamus.quicksaving.util.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ToServerAskIfModIsInstalled {
    public static final ResourceLocation CHANNEL = new ResourceLocation(Reference.MOD_ID, "to_server_ask_if_mod_is_installed_packet");

    public ToServerAskIfModIsInstalled() {
    }

    public static ToServerAskIfModIsInstalled decode(FriendlyByteBuf buf) {
        return new ToServerAskIfModIsInstalled();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public static void handle(PacketContext<ToServerAskIfModIsInstalled> ctx) {
        if (ctx.side().equals(Side.SERVER)) {
            Player player = ctx.sender();

            Dispatcher.sendToClient(new ToClientConfirmModIsInstalledPacket(), (ServerPlayer)player);
        }
    }
}
