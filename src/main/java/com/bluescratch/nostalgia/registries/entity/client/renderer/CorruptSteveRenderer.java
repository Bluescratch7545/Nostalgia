package com.bluescratch.nostalgia.registries.entity.client.renderer;

import com.bluescratch.nostalgia.registries.entity.client.model.CorruptSteveModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.bluescratch.nostalgia.registries.entity.customGoals.CorruptSteveDefault;

public class CorruptSteveRenderer extends GeoEntityRenderer<CorruptSteveDefault> {
    public CorruptSteveRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CorruptSteveModel());
    }
}
