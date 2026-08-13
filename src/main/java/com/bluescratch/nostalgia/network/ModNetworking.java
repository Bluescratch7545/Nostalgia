package com.bluescratch.nostalgia.network;

import com.bluescratch.nostalgia.meta.MetaEventManager;
import com.bluescratch.nostalgia.meta.event.PopupEvent;
import com.bluescratch.nostalgia.meta.event.packet.PopupShowPacket;
import com.bluescratch.nostalgia.popup.PopupLauncher;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import com.bluescratch.nostalgia.Nostalgia;

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
    }
}