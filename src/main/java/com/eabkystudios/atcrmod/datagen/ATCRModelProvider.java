package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jspecify.annotations.NonNull;

public class ATCRModelProvider extends ModelProvider
{
    public ATCRModelProvider(PackOutput output, String modId) {
        super(output, AgainstTheCreepingRot.MODID);
    }

    @Override
    protected void registerModels(@NonNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        /*ITEMS*/
        itemModels.generateFlatItem(ATCRItems.STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.SILVER_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.RAW_SILVER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.LOTUS_FLOWER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.LEATHER_WRAPPED_HANDLE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.declareCustomModelItem(ATCRItems.SILVER_SWORD.get());

        /*BLOCKS*/
        blockModels.createTrivialCube(ATCRBlocks.STEEL_BLOCK.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_ORE.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_DEEPSLATE_ORE.get());
    }
}
