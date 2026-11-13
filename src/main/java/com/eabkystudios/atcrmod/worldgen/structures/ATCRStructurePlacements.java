package com.eabkystudios.atcrmod.worldgen.structures;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.worldgen.structures.placement.DistanceFromCenterPlacement;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATCRStructurePlacements {

    public static final DeferredRegister<StructurePlacementType<?>> DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, AgainstTheCreepingRot.MODID);

    public static final DeferredHolder<StructurePlacementType<?>, StructurePlacementType<DistanceFromCenterPlacement>> DISTANCE_FROM_CENTER_STRUCTURE_PLACEMENT = DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register("distance_from_center_structure_placement", () -> explicitStructureTypeTyping(DistanceFromCenterPlacement.CODEC));

    private static <T extends StructurePlacement> StructurePlacementType<T> explicitStructureTypeTyping(MapCodec<T> structurePlacementTypeCodec) {
        return () -> structurePlacementTypeCodec;
    }

    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register(eventBus);
    }
}
