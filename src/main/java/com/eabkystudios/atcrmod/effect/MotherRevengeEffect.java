package com.eabkystudios.atcrmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MotherRevengeEffect extends MobEffect {
    public static final float DAMAGE_MULTI = 0.5f;
    private static final Logger logger = LoggerFactory.getLogger(MotherRevengeEffect.class);

    public MotherRevengeEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        //logger.debug("Mother's Revenge Effect tick.");

        if (mob.is(EntityTypeTags.UNDEAD) && mob.isAlive() && !mob.isInvulnerable()){
            mob.hurtServer(serverLevel, mob.damageSources().magic(), DAMAGE_MULTI * (amplification + 1));
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
