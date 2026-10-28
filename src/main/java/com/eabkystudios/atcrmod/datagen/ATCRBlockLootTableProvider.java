package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ATCRBlockLootTableProvider extends BlockLootSubProvider {
    public ATCRBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ATCRBlocks.STEEL_BLOCK.get());
        dropSelf(ATCRBlocks.GRASSY_COBBLESTONE.get());
        dropSelf(ATCRBlocks.POWERED_FURNACE.get());

        add(ATCRBlocks.SILVER_ORE.get(),
                createOreDrop(ATCRBlocks.SILVER_ORE.get(), ATCRItems.RAW_SILVER.get()));
        add(ATCRBlocks.SILVER_DEEPSLATE_ORE.get(),
                createOreDrop(ATCRBlocks.SILVER_DEEPSLATE_ORE.get(), ATCRItems.RAW_SILVER.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ATCRBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
