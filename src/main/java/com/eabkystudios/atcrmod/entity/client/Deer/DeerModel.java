package com.eabkystudios.atcrmod.entity.client.Deer;

import com.eabkystudios.atcrmod.entity.client.LostTraveler.LostTravelerAnimations;
import com.eabkystudios.atcrmod.entity.client.LostTraveler.LostTravelerRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class DeerModel extends EntityModel<DeerRenderState>{
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart noose;
    private final ModelPart ear1;
    private final ModelPart ear2;
    private final ModelPart horn;
    private final ModelPart horn2;
    private final ModelPart cape;
    private final ModelPart tail;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;

    public DeerModel(ModelPart root) {
        super (root);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.neck = this.root.getChild("neck");
        this.head = this.neck.getChild("head");
        this.noose = this.head.getChild("noose");
        this.ear1 = this.head.getChild("ear1");
        this.ear2 = this.head.getChild("ear2");
        this.horn = this.head.getChild("horn");
        this.horn2 = this.head.getChild("horn2");
        this.cape = this.neck.getChild("cape");
        this.tail = this.root.getChild("tail");
        this.leg0 = this.root.getChild("leg0");
        this.leg1 = this.root.getChild("leg1");
        this.leg2 = this.root.getChild("leg2");
        this.leg3 = this.root.getChild("leg3");

        this.walkingAnimation = DeerAnimations.WALK.bake(root);
        this.idlingAnimation = DeerAnimations.IDLE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, -4.0F, -8.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -2.0F));
        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -16.8F, -2.5F, 7.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, -1.0F, -0.0873F, 0.0F, 0.0F));
        PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, -8.0F));
        PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(31, 14).addBox(-3.0F, -14.0F, 3.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 5.0F, 0.9163F, 0.0F, 0.0F));
        PartDefinition neck_r2 = neck.addOrReplaceChild("neck_r2", CubeListBuilder.create().texOffs(16, 34).addBox(-2.0F, -20.0F, -3.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 5.0F, 0.48F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -4.0F, -4.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.0F, -5.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition noose = head.addOrReplaceChild("noose", CubeListBuilder.create().texOffs(41, 6).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.0F, 0.1309F, 0.0F, 0.0F));
        PartDefinition ear1 = head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(11, 33).addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.5236F, -0.3927F));
        PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(30, 22).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, 0.0F, 0.0F, -0.5236F, 0.3927F));
        PartDefinition horn = head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(12, 44).addBox(-2.0F, -8.0F, -0.5F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -0.7418F, -0.0436F, 0.5236F));
        PartDefinition horn2 = head.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(0, 43).addBox(-2.0F, -8.0F, 0.0F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, 0.0F, -0.6981F, 0.0F, -0.6545F));
        PartDefinition cape = neck.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, -6.0F, -0.2182F, 0.0F, 0.0F));
        PartDefinition cape_r1 = cape.addOrReplaceChild("cape_r1", CubeListBuilder.create().texOffs(26, 26).addBox(-2.5F, -20.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, 0.48F, 0.0F, 0.0F));
        PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 19).addBox(-1.5F, -0.4023F, -1.2817F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -14.0F, 7.0F, 0.3491F, 0.0F, 0.0F));
        PartDefinition leg0 = root.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(42, 22).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -9.0F, -7.5F));
        PartDefinition leg1 = root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(27, 41).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -9.0F, -7.5F));
        PartDefinition leg2 = root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(37, 47).addBox(-1.0F, 4.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(37, 37).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -10.0F, 4.0F));
        PartDefinition leg3 = root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 4.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 33).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -10.0F, 4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(DeerRenderState state) {
        root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1.5f, 2f);
        idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
