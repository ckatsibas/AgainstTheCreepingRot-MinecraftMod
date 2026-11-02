package com.eabkystudios.atcrmod.entity.client.Deer;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.client.ATCRModelLayerLocations;
import com.eabkystudios.atcrmod.entity.custom.DeerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerRenderState, DeerModel>{

    public DeerRenderer(EntityRendererProvider.Context context) {
        super(context, new DeerModel(context.bakeLayer(ATCRModelLayerLocations.DEER)), 1f);
    }
    @Override
    public Identifier getTextureLocation(DeerRenderState state) {
        return Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "textures/entity/deer/deer.png");
    }

    @Override
    public DeerRenderState createRenderState() {
        return new DeerRenderState();
    }

    @Override
    public void extractRenderState(DeerEntity entity, DeerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
