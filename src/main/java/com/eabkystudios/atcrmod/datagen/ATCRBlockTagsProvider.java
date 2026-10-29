package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ATCRBlockTagsProvider extends BlockTagsProvider {

    public ATCRBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, AgainstTheCreepingRot.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ATCRBlocks.SILVER_ORE.get())
                .add(ATCRBlocks.SILVER_DEEPSLATE_ORE.get())
                .add(ATCRBlocks.STEEL_BLOCK.get())
                .add(ATCRBlocks.GRASSY_COBBLESTONE.get())
                .add(ATCRBlocks.POWERED_FURNACE.get())
                .add(ATCRBlocks.CLUSTER_OF_RED.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ATCRBlocks.SILVER_ORE.get())
                .add(ATCRBlocks.SILVER_DEEPSLATE_ORE.get())
                .add(ATCRBlocks.STEEL_BLOCK.get())
                .add(ATCRBlocks.CLUSTER_OF_RED.get());
    }
}
