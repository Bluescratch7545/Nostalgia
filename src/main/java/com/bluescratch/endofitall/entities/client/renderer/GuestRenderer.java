package com.bluescratch.endofitall.entities.client.renderer;

import com.bluescratch.endofitall.entities.GuestIntro;
import com.bluescratch.endofitall.entities.client.model.GuestModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GuestRenderer extends GeoEntityRenderer<GuestIntro> {
    public GuestRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GuestModel());
    }
}
