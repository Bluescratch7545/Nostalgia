package com.bluescratch.endofitall.event;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.client.renderer.*;
import com.bluescratch.endofitall.entities.utils.EntityUtils;
import com.bluescratch.endofitall.registries.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.lang.reflect.Constructor;

@EventBusSubscriber(modid = Teoia.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.STRUCTURE_SPAWNER.get(),
                StructureSpawnerRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.STATIC.get(),
                StaticRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.SILENCE.get(),
                SilenceRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.GUEST.get(),
                GuestRenderer::new
        );
    }
}