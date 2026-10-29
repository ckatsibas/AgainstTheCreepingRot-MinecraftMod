package com.eabkystudios.atcrmod.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class LostTravelerModel extends EntityModel<LostTravelerRenderState> {
    private final ModelPart root;
    private final ModelPart left_leg_core;
    private final ModelPart left_leg;
    private final ModelPart right_leg_core;
    private final ModelPart right_leg;
    private final ModelPart waist;
    private final ModelPart head;
    private final ModelPart lower_jaw;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart buckler;
    private final ModelPart right_arm;
    private final ModelPart sword;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;

    public LostTravelerModel(ModelPart root) {
        super (root);
        this.root = root.getChild("root");
        this.left_leg_core = this.root.getChild("left_leg_core");
        this.left_leg = this.left_leg_core.getChild("left_leg");
        this.right_leg_core = this.root.getChild("right_leg_core");
        this.right_leg = this.right_leg_core.getChild("right_leg");
        this.waist = this.root.getChild("waist");
        this.head = this.waist.getChild("head");
        this.lower_jaw = this.head.getChild("lower_jaw");
        this.headwear = this.head.getChild("headwear");
        this.body = this.waist.getChild("body");
        this.left_arm = this.waist.getChild("left_arm");
        this.buckler = this.left_arm.getChild("buckler");
        this.right_arm = this.waist.getChild("right_arm");
        this.sword = this.right_arm.getChild("sword");

        this.walkingAnimation = LostTravelerAnimations.WALK.bake(root);
        this.idlingAnimation = LostTravelerAnimations.IDLE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition left_leg_core = root.addOrReplaceChild("left_leg_core", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
        PartDefinition left_leg = left_leg_core.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 28).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false)
                .texOffs(48, 12).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, -12.0F, 0.0F));
        PartDefinition right_leg_core = root.addOrReplaceChild("right_leg_core", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
        PartDefinition right_leg = right_leg_core.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(72, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F))
                .texOffs(32, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -12.0F, 0.0F));
        PartDefinition waist = root.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));
        PartDefinition head = waist.addOrReplaceChild("head", CubeListBuilder.create().texOffs(96, 8).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.01F))
                .texOffs(104, 1).addBox(-3.5F, -8.0F, -3.9F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -1.0F));
        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -2.0F, -7.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 3.0F));
        PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                .texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = waist.addOrReplaceChild("body", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F))
                .texOffs(120, -1).addBox(0.0F, 0.0F, 2.0F, 0.0F, 12.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(56, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));
        PartDefinition left_arm = waist.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(5.0F, -9.0F, 0.0F));
        PartDefinition buckler = left_arm.addOrReplaceChild("buckler", CubeListBuilder.create(), PartPose.offset(1.0F, 7.0F, 0.25F));
        PartDefinition handle_r1 = buckler.addOrReplaceChild("handle_r1", CubeListBuilder.create().texOffs(0, 55).addBox(6.0F, -2.0F, -6.25F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(1.0F, -5.0F, -8.25F, 12.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 7.0F, -1.0F, 1.5708F, 0.0F, -1.5708F));
        PartDefinition right_arm = waist.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(-5.0F, -9.0F, 0.0F));
        PartDefinition sword = right_arm.addOrReplaceChild("sword", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));
        PartDefinition pummel_r1 = sword.addOrReplaceChild("pummel_r1", CubeListBuilder.create().texOffs(81, 60).addBox(3.5F, 3.0F, 4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(81, 44).addBox(4.0F, -3.0F, 4.5F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 56).addBox(2.5F, -5.0F, 4.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(59, 44).addBox(-0.5F, -22.0F, 5.0F, 11.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 5.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(LostTravelerRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
