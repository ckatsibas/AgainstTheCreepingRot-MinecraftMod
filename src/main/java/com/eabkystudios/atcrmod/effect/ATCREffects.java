package com.eabkystudios.atcrmod.effect;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATCREffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AgainstTheCreepingRot.MODID);

    public static final Holder<MobEffect> MOTHER_REVENGE_EFFECT = MOB_EFFECTS.register("mother_revenge",
            () -> new MotherRevengeEffect(MobEffectCategory.HARMFUL, 0x31050f));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
