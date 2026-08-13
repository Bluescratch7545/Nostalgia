package com.bluescratch.nostalgia.network;

import com.bluescratch.nostalgia.meta.event.packet.PopupShowPacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NostalgiaNetwork {
    public static void sendToPlayer(ServerPlayer player, PopupShowPacket packet) {
        PacketDistributor.sendToPlayer(
                player,
                packet
        );
    }
}
