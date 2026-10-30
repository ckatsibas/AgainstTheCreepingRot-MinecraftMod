package com.eabkystudios.atcrmod.worldgen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.entity.custom.LostTravelerEntity;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.random.Weighted;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ATCRBiomeModifiers {
    // PF is passed here.
    // Is a NeoForge class and places PFs into specific biomes.

    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_SILVER_ORE = registerKey("add_overworld_silver_ore");

    /*MONSTER SPAWNS KEYS*/
    public static final ResourceKey<BiomeModifier> SPAWN_LOST_TRAVELER = registerKey("spawn_lost_traveler");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(
                ADD_OVERWORLD_SILVER_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeatures.getOrThrow(ATCRPlacedFeatures.OVERWORLD_SILVER_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        /*MONSTERS SPAWNS MODIFIERS*/
        context.register(
                SPAWN_LOST_TRAVELER,
                BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
                        HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                        new Weighted<>(new MobSpawnSettings.SpawnerData(ATCREntities.LOST_TRAVELER.get(), 1, 4), 2)));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
    }
}
