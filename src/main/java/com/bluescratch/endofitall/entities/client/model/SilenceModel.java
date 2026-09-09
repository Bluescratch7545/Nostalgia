package com.bluescratch.endofitall.entities.client.model;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.Silence;
import com.bluescratch.endofitall.entities.Static;
import com.bluescratch.endofitall.entities.utils.EntityUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.cache.object.GeoBone;

public class SilenceModel extends GeoModel<Silence> {

    @Override
    public ResourceLocation getModelResource(Silence animatable) {
        return EntityUtils.getGeo("static");
    }

    @Override
    public ResourceLocation getTextureResource(Silence animatable) {
        return EntityUtils.getTexture("silence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Silence animatable) {
        return EntityUtils.getAnim("steve-default");
    }

    @Override
    public void setCustomAnimations(Silence animatable, long instanceId, AnimationState<Silence> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone head = getAnimationProcessor().getBone("head");

        EntityModelData entityData =
                animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }

}
