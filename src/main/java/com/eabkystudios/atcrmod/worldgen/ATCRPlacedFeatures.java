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
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ATCRPlacedFeatures {
    // CF is passed here
    // We determine how CFs are placed.

    public static final ResourceKey<PlacedFeature> OVERWORLD_SILVER_ORE_PLACED_KEY = registerKey("overworld_silver_placed_ore");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ATCRConfiguredFeatures.OVERWORLD_SILVER_ORE_KEY),
                OrePlacements.rareOrePlacement(3,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(18))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
