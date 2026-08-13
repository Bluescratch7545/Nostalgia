package com.bluescratch.nostalgia.registries.entity.client.renderer;

import com.bluescratch.nostalgia.registries.entity.TestEntityTwo;
import com.bluescratch.nostalgia.registries.entity.client.model.TestEntityTwoModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TestEntityTwoRenderer extends GeoEntityRenderer<TestEntityTwo> {
    public TestEntityTwoRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new TestEntityTwoModel());
    }
}
