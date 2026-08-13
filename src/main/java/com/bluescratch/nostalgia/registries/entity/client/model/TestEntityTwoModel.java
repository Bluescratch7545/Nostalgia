package com.bluescratch.nostalgia.registries.entity.client.model;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.registries.entity.TestEntityTwo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TestEntityTwoModel extends GeoModel<TestEntityTwo> {
    @Override
    public ResourceLocation getModelResource(TestEntityTwo entity) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "geo/testentitytwo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TestEntityTwo entity) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "textures/entity/testentitytwo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TestEntityTwo entity) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "animations/testentitytwo.animation.json");
    }


}
