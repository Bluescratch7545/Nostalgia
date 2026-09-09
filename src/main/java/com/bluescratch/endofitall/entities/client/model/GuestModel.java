package com.bluescratch.endofitall.entities.client.model;

import com.bluescratch.endofitall.entities.GuestIntro;
import com.bluescratch.endofitall.entities.utils.EntityUtils;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuestModel extends GeoModel<GuestIntro> {

    @Override
    public ResourceLocation getModelResource(GuestIntro animatable) {
        return EntityUtils.getGeo("guest");
    }

    @Override
    public ResourceLocation getTextureResource(GuestIntro animatable) {
        return EntityUtils.getTexture("guest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuestIntro animatable) {
        return EntityUtils.getAnim("guest");
    }
}
