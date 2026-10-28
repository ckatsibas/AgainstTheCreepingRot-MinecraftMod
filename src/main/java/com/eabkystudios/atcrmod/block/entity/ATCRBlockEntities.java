package com.eabkystudios.atcrmod.block.entity;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.block.entity.block.PoweredFurnaceEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATCRBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AgainstTheCreepingRot.MODID);

    public static final Supplier<BlockEntityType<PoweredFurnaceEntity>> POWERED_FURNACE_BE = BLOCK_ENTITY_TYPES.register(
            "powered_furnace_be", () -> new BlockEntityType<>(PoweredFurnaceEntity::new, ATCRBlocks.POWERED_FURNACE.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
