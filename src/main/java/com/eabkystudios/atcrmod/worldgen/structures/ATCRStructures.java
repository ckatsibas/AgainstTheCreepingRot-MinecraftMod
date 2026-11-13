package com.eabkystudios.atcrmod.worldgen.structures;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.worldgen.structures.custom.UndergroundLairStructures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATCRStructures {
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registries.STRUCTURE_TYPE, AgainstTheCreepingRot.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<UndergroundLairStructures>> UNDERGROUND_LAIR_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("underground_lair_structures", () -> explicitStructureTypeTyping(UndergroundLairStructures.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
    }
}
