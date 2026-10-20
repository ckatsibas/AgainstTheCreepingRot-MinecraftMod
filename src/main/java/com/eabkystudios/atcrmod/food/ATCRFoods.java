package com.eabkystudios.atcrmod.food;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ATCRFoods {

    public static final FoodProperties LOTUS_FLOWER = new FoodProperties.Builder().nutrition(4).saturationModifier(1f).build();

    public static final Consumable LOTUS_FLOWER_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(1.3f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 400)))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 500, 3)))
            .build();
}
