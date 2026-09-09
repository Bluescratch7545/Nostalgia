package com.bluescratch.endofitall.entities.client.renderer;

import com.bluescratch.endofitall.entities.StructureSpawner;
import com.bluescratch.endofitall.entities.client.model.StructureSpawnerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StructureSpawnerRenderer extends GeoEntityRenderer<StructureSpawner> {
    public StructureSpawnerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new StructureSpawnerModel());
    }
}
