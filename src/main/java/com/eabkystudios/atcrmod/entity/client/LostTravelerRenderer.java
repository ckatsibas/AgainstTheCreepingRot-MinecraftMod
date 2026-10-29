package com.eabkystudios.atcrmod.entity.client;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.custom.LostTravelerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class LostTravelerRenderer extends MobRenderer<LostTravelerEntity, LostTravelerRenderState, LostTravelerModel> {
    public LostTravelerRenderer(EntityRendererProvider.Context context) {
        super(context, new LostTravelerModel(context.bakeLayer(ATCRModelLayerLocations.LOST_TRAVELER)), 0.5f);
    }
    @Override
    public Identifier getTextureLocation(LostTravelerRenderState state) {
        return Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "textures/entity/lost_traveler/lost_traveler.png");
    }

    @Override
    public LostTravelerRenderState createRenderState() {
        return new LostTravelerRenderState();
    }

    @Override
    public void extractRenderState(LostTravelerEntity entity, LostTravelerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
