package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import io.netty.channel.unix.Socket;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ATCRModelProvider extends ModelProvider {
    // public static BiConsumer<Identifier, ModelInstance> modelOutput;
    // public static Consumer<BlockModelDefinitionGenerator> blockStateOutput;

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

        /*BLOCKS*/
        blockModels.createTrivialCube(ATCRBlocks.STEEL_BLOCK.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_ORE.get());
        blockModels.createTrivialCube(ATCRBlocks.SILVER_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ATCRBlocks.GRASSY_COBBLESTONE.get());
        blockModels.createFurnace(ATCRBlocks.POWERED_FURNACE.get(), TexturedModel.ORIENTABLE);



    }

    /*METHODS*/
    //TODO: make the top side use an "_on" texture
    /*
    public void createPoweredBlock(Block furnace, TexturedModel.Provider provider) {
        MultiVariant normalModel = plainVariant(provider.create(furnace, modelOutput));
        Material frontTexture = TextureMapping.getBlockTexture(furnace, "_front_on");
        MultiVariant litModel = plainVariant(provider.get(furnace).updateTextures((t) -> t.put(TextureSlot.FRONT, frontTexture)).createWithSuffix(furnace, "_on", this.modelOutput));
        blockStateOutput.accept(MultiVariantGenerator.dispatch(furnace).with(createBooleanModelDispatch(BlockStateProperties.LIT, litModel, normalModel)).with(ROTATION_HORIZONTAL_FACING));
    }*/
}
