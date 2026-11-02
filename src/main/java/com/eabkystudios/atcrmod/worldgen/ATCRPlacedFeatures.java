package com.eabkystudios.atcrmod.worldgen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ATCRPlacedFeatures {
    // CF is passed here
    // We determine how CFs are placed.

    /*ORES KEYS*/
    public static final ResourceKey<PlacedFeature> OVERWORLD_SILVER_ORE_PLACED_KEY = registerKey("overworld_silver_placed_ore");

    /*CUSTOM FEATURES KEYS*/
    public static final ResourceKey<PlacedFeature> RED_CLUSTER_SPIKE_PLACED_KEY = registerKey("red_cluster_spike_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /*ORES*/
        register(context, OVERWORLD_SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ATCRConfiguredFeatures.OVERWORLD_SILVER_ORE_KEY),
                OrePlacements.rareOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(18))));

        /*CUSTOM FEATURES*/
        register(
                context,
                RED_CLUSTER_SPIKE_PLACED_KEY,
                configuredFeatures.getOrThrow(ATCRConfiguredFeatures.RED_CLUSTER_SPIKE_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(8),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-56),
                                VerticalAnchor.absolute(-12)
                        ),
                        BiomeFilter.biome()
                )
        );

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
