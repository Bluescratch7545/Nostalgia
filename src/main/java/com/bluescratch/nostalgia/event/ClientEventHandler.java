package com.bluescratch.nostalgia.event;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.registries.ModEntities;

import com.bluescratch.nostalgia.registries.entity.client.renderer.CorruptSteveRenderer;
import com.bluescratch.nostalgia.registries.entity.client.renderer.TestEntityTwoRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Nostalgia.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.TEST_ENTITY_TWO.get(),
                TestEntityTwoRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.CORRUPT_STEVE.get(),
                CorruptSteveRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.CORRUPT_STEVE_STALK.get(),
                CorruptSteveRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.CORRUPT_STEVE_CHASE.get(),
                CorruptSteveRenderer::new
        );
    }
}