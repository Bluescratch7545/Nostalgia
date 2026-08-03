package com.bluescratch.nostalgia.event;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.client.model.TestEntityModel;
import com.bluescratch.nostalgia.registries.ModEntities;
import com.bluescratch.nostalgia.client.renderer.TestEntityRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Nostalgia.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.TEST_ENTITY.get(),
                TestEntityRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                TestEntityModel.LAYER_LOCATION,
                TestEntityModel::createBodyLayer
        );
    }
}