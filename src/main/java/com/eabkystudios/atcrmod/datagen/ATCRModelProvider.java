package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;
import java.util.function.BiConsumer;
import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ATCRModelProvider extends ModelProvider {
    public ATCRModelProvider(PackOutput output, String modId) {
        super(output, modId);
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
        itemModels.declareCustomModelItem(ATCRItems.STEEL_SWORD.get());
        itemModels.declareCustomModelItem(ATCRItems.TRAVELER_SWORD.get());
        itemModels.declareCustomModelItem(ATCRItems.TRAVELER_DAGGER.get());
        // itemModels.declareCustomModelItem(ATCRItems.TRAVELER_SHIELD.get());
        itemModels.generateFlatItem(ATCRItems.RED_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.BATTERY_BASIC.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ATCRItems.LOST_TRAVELER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        /*BLOCKS*/
        blockModels.createTrivialCube(ATCRBlocks.STEEL_BLOCK.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_ORE.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ATCRBlocks.GRASSY_COBBLESTONE.get());
        createPoweredBlock(blockModels, ATCRBlocks.POWERED_FURNACE.get(), TexturedModel.ORIENTABLE, blockModels.modelOutput);
        blockModels.createTrivialCube(ATCRBlocks.CLUSTER_OF_RED.get());
    }

    /*METHODS*/
    public void createPoweredBlock(BlockModelGenerators blockModels, Block poweredBlock, TexturedModel.Provider provider, BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant normalModel = plainVariant(provider.create(poweredBlock, modelOutput));
        Material frontTexture = TextureMapping.getBlockTexture(poweredBlock, "_front_on");
        Material topTexture = TextureMapping.getBlockTexture(poweredBlock, "_top_on");
        MultiVariant litModel = plainVariant(provider.get(poweredBlock).updateTextures(textures -> textures.put(TextureSlot.FRONT, frontTexture)).updateTextures(textures -> textures.put(TextureSlot.TOP, topTexture)).createWithSuffix(poweredBlock, "_on", modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(poweredBlock).with(createBooleanModelDispatch(BlockStateProperties.LIT, litModel, normalModel)).with(ROTATION_HORIZONTAL_FACING));
    }
}
