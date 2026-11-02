package com.eabkystudios.atcrmod.worldgen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.worldgen.features.RedClusterSpikeFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ATCRFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, AgainstTheCreepingRot.MODID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RED_CLUSTER_SPIKE = FEATURES.register("red_cluster_spike", () -> new RedClusterSpikeFeature(NoneFeatureConfiguration.CODEC));

    private ATCRFeatures() {
    }

    public static void register(IEventBus modEventBus) {
        FEATURES.register(modEventBus);
    }
}
