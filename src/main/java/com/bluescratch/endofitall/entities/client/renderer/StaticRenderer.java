package com.bluescratch.endofitall.entities.client.renderer;

import com.bluescratch.endofitall.entities.Static;
import com.bluescratch.endofitall.entities.client.model.StaticModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StaticRenderer extends GeoEntityRenderer<Static> {
    public StaticRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new StaticModel());
    }
}
