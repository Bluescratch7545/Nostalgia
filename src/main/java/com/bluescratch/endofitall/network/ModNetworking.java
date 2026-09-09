package com.bluescratch.endofitall.network;

import com.bluescratch.endofitall.meta.event.packet.KillMinecraftPacket;
import com.bluescratch.endofitall.meta.event.packet.PopupShowPacket;
import com.bluescratch.endofitall.popup.PopupLauncher;
import com.bluescratch.endofitall.popup.WindowUtil;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetworking {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                PopupShowPacket.TYPE,
                PopupShowPacket.STREAM_CODEC,
                (packet, context) -> {

                    context.enqueueWork(() -> {
                        PopupLauncher.show(packet.minimize(), packet.title(), packet.text());
                    });
                }
        );
        registrar.playToClient(
                KillMinecraftPacket.TYPE,
                KillMinecraftPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        WindowUtil.killMinecraftInstance(packet.retrn());
                    });
                }
        );
    }
}