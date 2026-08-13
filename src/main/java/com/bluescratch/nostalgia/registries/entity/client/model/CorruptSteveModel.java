package com.bluescratch.nostalgia.registries.entity.client.model;

import com.bluescratch.nostalgia.Nostalgia;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.bluescratch.nostalgia.registries.entity.customGoals.CorruptSteveDefault;

public class CorruptSteveModel extends GeoModel<CorruptSteveDefault> {
    @Override
    public ResourceLocation getModelResource(CorruptSteveDefault animatable) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "geo/corrupt_steve.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptSteveDefault animatable) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "textures/entity/corrupt_steve.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptSteveDefault animatable) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "animations/corrupt_steve.animation.json");
    }
}
