package com.bluescratch.nostalgia.client.renderer;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.client.model.TestEntityModel;
import com.bluescratch.nostalgia.registries.entity.TestEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TestEntityRenderer extends EntityRenderer<TestEntity> {
    private final TestEntityModel model;

    public TestEntityRenderer(EntityRendererProvider.Context context) {
        super(context);

        this.model = new TestEntityModel(context.bakeLayer(TestEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(TestEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.scale(1.0F, 1.0F, 1.0F);
        poseStack.translate(0, 1.5, 0);
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(180));

        VertexConsumer vertexConsumer = buffer.getBuffer(
                RenderType.entityCutout(getTextureLocation(entity))
        );

        model.setupAnim(entity, 0, 0, entity.tickCount + partialTick, entity.getYRot(), entity.getXRot());

        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity testEntity) {
        return ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "textures/entity/test_entity.png");
    }
}