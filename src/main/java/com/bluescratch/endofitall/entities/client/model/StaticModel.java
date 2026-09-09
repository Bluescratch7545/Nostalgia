package com.bluescratch.endofitall.entities.client.model;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.Static;
import com.bluescratch.endofitall.entities.utils.EntityUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.cache.object.GeoBone;

public class StaticModel extends GeoModel<Static> {

    @Override
    public ResourceLocation getModelResource(Static animatable) {
        return EntityUtils.getGeo("static");
    }

    @Override
    public ResourceLocation getTextureResource(Static animatable) {
        return EntityUtils.getTexture("static");
    }

    @Override
    public ResourceLocation getAnimationResource(Static animatable) {
        return EntityUtils.getAnim("steve-default");
    }

    @Override
    public void setCustomAnimations(
            Static animatable,
            long instanceId,
            AnimationState<Static> animationState
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone head = getAnimationProcessor().getBone("head");

        EntityModelData entityData =
                animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
