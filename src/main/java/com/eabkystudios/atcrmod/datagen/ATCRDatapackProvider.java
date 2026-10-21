package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.worldgen.ATCRBiomeModifiers;
import com.eabkystudios.atcrmod.worldgen.ATCRConfiguredFeatures;
import com.eabkystudios.atcrmod.worldgen.ATCRPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ATCRDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ATCRConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ATCRPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATCRBiomeModifiers::bootstrap);

    public ATCRDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AgainstTheCreepingRot.MODID));
    }
}
