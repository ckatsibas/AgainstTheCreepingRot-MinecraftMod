package com.eabkystudios.atcrmod.worldgen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ATCRBiomeModifiers {
    // PF is passed here.
    // Is a NeoForge class and places PFs into specific biomes.

    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_SILVER_ORE = registerKey("add_overworld_silver_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biome = context.lookup(Registries.BIOME);

        context.register(ADD_OVERWORLD_SILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
           biome.getOrThrow(BiomeTags.IS_OVERWORLD),
           HolderSet.direct(placedFeatures.getOrThrow(ATCRPlacedFeatures.OVERWORLD_SILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
    }
}
