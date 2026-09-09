package com.bluescratch.endofitall.entities.client.renderer;

import com.bluescratch.endofitall.entities.Silence;
import com.bluescratch.endofitall.entities.client.model.SilenceModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SilenceRenderer extends GeoEntityRenderer<Silence> {
    public SilenceRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SilenceModel());
    }
}
