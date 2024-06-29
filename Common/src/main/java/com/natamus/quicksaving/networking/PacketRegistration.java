package com.natamus.quicksaving.networking;

import com.natamus.collective.implementations.networking.api.Network;
import com.natamus.quicksaving.networking.packets.ToClientConfirmModIsInstalledPacket;
import com.natamus.quicksaving.networking.packets.ToServerAskIfModIsInstalled;
import com.natamus.quicksaving.networking.packets.ToServerTeleportPlayerPacket;

public class PacketRegistration {

    public void init() {
        initClientPackets();
        initServerPackets();
    }

    private void initClientPackets() {
        Network.registerPacket(ToClientConfirmModIsInstalledPacket.CHANNEL, ToClientConfirmModIsInstalledPacket.class, ToClientConfirmModIsInstalledPacket::encode, ToClientConfirmModIsInstalledPacket::decode, ToClientConfirmModIsInstalledPacket::handle);
    }

    private void initServerPackets() {
        Network.registerPacket(ToServerAskIfModIsInstalled.CHANNEL, ToServerAskIfModIsInstalled.class, ToServerAskIfModIsInstalled::encode, ToServerAskIfModIsInstalled::decode, ToServerAskIfModIsInstalled::handle)

        .registerPacket(ToServerTeleportPlayerPacket.CHANNEL, ToServerTeleportPlayerPacket.class, ToServerTeleportPlayerPacket::encode, ToServerTeleportPlayerPacket::decode, ToServerTeleportPlayerPacket::handle);
    }
}
