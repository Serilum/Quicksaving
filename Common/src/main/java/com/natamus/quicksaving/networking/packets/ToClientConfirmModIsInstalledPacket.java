package com.natamus.quicksaving.networking.packets;

import com.natamus.collective.implementations.networking.data.PacketContext;
import com.natamus.collective.implementations.networking.data.Side;
import com.natamus.quicksaving.data.Variables;
import com.natamus.quicksaving.util.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ToClientConfirmModIsInstalledPacket {
    public static final ResourceLocation CHANNEL = new ResourceLocation(Reference.MOD_ID, "to_client_confirm_mod_is_installed_packet");

    public ToClientConfirmModIsInstalledPacket() {
    }

    public static ToClientConfirmModIsInstalledPacket decode(FriendlyByteBuf buf) {
        return new ToClientConfirmModIsInstalledPacket();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public static void handle(PacketContext<ToClientConfirmModIsInstalledPacket> ctx) {
        if (ctx.side().equals(Side.CLIENT)) {
            Variables.isInstalledOnServer = true;
        }
    }
}
