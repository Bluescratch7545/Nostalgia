// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.bluescratch.nostalgia.client.model;


import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.registries.entity.TestEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TestEntityModel extends EntityModel<TestEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, "test_entity"), "main");
	private final ModelPart foot_for;
	private final ModelPart foot_back;
	private final ModelPart bb_main;

	public TestEntityModel(ModelPart root) {
		this.foot_for = root.getChild("foot_for");
		this.foot_back = root.getChild("foot_back");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition foot_for = partdefinition.addOrReplaceChild("foot_for", CubeListBuilder.create().texOffs(0, 23).addBox(6.0F, -7.0F, -7.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 23).addBox(6.0F, -7.0F, 5.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition foot_back = partdefinition.addOrReplaceChild("foot_back", CubeListBuilder.create().texOffs(16, 23).addBox(-8.0F, -7.0F, 5.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 23).addBox(-8.0F, -7.0F, -7.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -7.0F, 16.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void setupAnim(TestEntity testEntity, float v, float v1, float v2, float v3, float v4) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        foot_for.render(poseStack, vertexConsumer, i, i1, i2);
        foot_back.render(poseStack, vertexConsumer, i, i1, i2);
        bb_main.render(poseStack, vertexConsumer, i, i1, i2);
    }
}