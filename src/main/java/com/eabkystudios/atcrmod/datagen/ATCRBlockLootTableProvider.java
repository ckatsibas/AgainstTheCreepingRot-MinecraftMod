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
        dropSelf(ATCRBlocks.GRASSY_COBBLESTONE_STAIRS.get());
        dropSelf(ATCRBlocks.GRASSY_COBBLESTONE_SLAB.get());
        dropSelf(ATCRBlocks.POWERED_FURNACE.get());
        dropSelf(ATCRBlocks.DENSE_BLOCK_OF_RED.get());

        //TODO:should drop cobblestone when not mined with silk touch not stone
        dropSelf(ATCRBlocks.RUINED_STONE.get());
        dropSelf(ATCRBlocks.RUINED_STONE_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_STONE_SLAB.get());

        dropSelf(ATCRBlocks.RUINED_COBBLESTONE.get());
        dropSelf(ATCRBlocks.RUINED_COBBLESTONE_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_COBBLESTONE_SLAB.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_COBBLESTONE.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_COBBLESTONE_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_COBBLESTONE_SLAB.get());
        dropSelf(ATCRBlocks.RUINED_STONE_BRICKS.get());
        dropSelf(ATCRBlocks.RUINED_STONE_BRICKS_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_STONE_BRICKS_SLAB.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_STONE_BRICKS.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_STONE_BRICKS_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_STONE_BRICKS_SLAB.get());
        dropSelf(ATCRBlocks.RUINED_CRACKED_STONE_BRICKS.get());
        dropSelf(ATCRBlocks.RUINED_CRACKED_STONE_BRICKS_STAIRS.get());
        dropSelf(ATCRBlocks.RUINED_CRACKED_STONE_BRICKS_SLAB.get());
        dropSelf(ATCRBlocks.RUINED_CHISELED_STONE_BRICKS.get());
        dropSelf(ATCRBlocks.RUINED_STONE_WALL.get());
        dropSelf(ATCRBlocks.RUINED_COBBLESTONE_WALL.get());
        dropSelf(ATCRBlocks.RUINED_STONE_BRICKS_WALL.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_COBBLESTONE_WALL.get());
        dropSelf(ATCRBlocks.RUINED_MOSSY_STONE_BRICKS_WALL.get());
        dropSelf(ATCRBlocks.RUINED_CRACKED_STONE_BRICKS_WALL.get());
        dropSelf(ATCRBlocks.GRASSY_COBBLESTONE_WALL.get());

        add(ATCRBlocks.SILVER_ORE.get(), createOreDrop(ATCRBlocks.SILVER_ORE.get(), ATCRItems.RAW_SILVER.get()));
        add(ATCRBlocks.SILVER_DEEPSLATE_ORE.get(), createOreDrop(ATCRBlocks.SILVER_DEEPSLATE_ORE.get(), ATCRItems.RAW_SILVER.get()));
        add(ATCRBlocks.CLUSTER_OF_RED.get(), createOreDrop(ATCRBlocks.CLUSTER_OF_RED.get(), ATCRItems.RED_SHARD.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ATCRBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
