package com.bluescratch.endofitall.entities.client.model;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.StructureSpawner;
import com.bluescratch.endofitall.entities.utils.EntityUtils;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StructureSpawnerModel extends GeoModel<StructureSpawner> {
    @Override
    public ResourceLocation getModelResource(StructureSpawner entity) {
        return EntityUtils.getGeo("steve-default");
    }

    @Override
    public ResourceLocation getTextureResource(StructureSpawner entity) {
        return EntityUtils.getTexture("steve_default.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StructureSpawner entity) {
        return EntityUtils.getAnim("steve-default");
    }
}
