package com.eabkystudios.atcrmod.worldgen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ATCRConfiguredFeatures {
    // Feature = any type of "build", e.g tree.
    // CF -> describes how something is "built".

    /*ORES KEYS*/
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("overworld_silver_ore");

    /*CUSTOM FEATURES KEYS*/
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_CLUSTER_SPIKE_KEY = registerKey("red_cluster_spike");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<Block> redClusterReplaceables = List.of();

        /*ORES*/
        register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(stoneReplaceables, ATCRBlocks.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ATCRBlocks.SILVER_DEEPSLATE_ORE.get().defaultBlockState())), 9));

        /*CUSTOM FEATURES*/
        register(
                context,
                RED_CLUSTER_SPIKE_KEY,
                ATCRFeatures.RED_CLUSTER_SPIKE.get(),
                NoneFeatureConfiguration.INSTANCE
        );

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
