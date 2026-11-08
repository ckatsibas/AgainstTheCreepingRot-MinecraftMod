package com.eabkystudios.atcrmod.block;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.custom.ClusterOfRedBlock;
import com.eabkystudios.atcrmod.block.custom.DenseRedBlock;
import com.eabkystudios.atcrmod.block.custom.PoweredFurnaceBlock;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ATCRBlocks {

    private static ToIntFunction<BlockState> litBlockEmission(int lightEmission) {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? lightEmission : 0;
    }

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AgainstTheCreepingRot.MODID);

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block", properties -> new Block(properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.IRON)));
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore", properties -> new DropExperienceBlock(UniformInt.of(2,4),properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> SILVER_DEEPSLATE_ORE = registerBlock("deepslate_silver_ore", properties -> new DropExperienceBlock(UniformInt.of(4,6),properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> GRASSY_COBBLESTONE = registerBlock("grassy_cobblestone", properties -> new Block(properties.strength(2f,6f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> POWERED_FURNACE = registerBlock("powered_furnace", properties -> new PoweredFurnaceBlock(properties.strength(3.5f).requiresCorrectToolForDrops().lightLevel(litBlockEmission(10)).sound(SoundType.IRON)));
    public static final DeferredBlock<Block> CLUSTER_OF_RED = registerBlock("cluster_of_red", properties -> new ClusterOfRedBlock(UniformInt.of(2, 4),properties.strength(2f).requiresCorrectToolForDrops().lightLevel(litBlockEmission(8)).sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> DENSE_BLOCK_OF_RED = registerBlock("dense_block_of_red", properties -> new DenseRedBlock(properties.strength(2f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> RUINED_STONE = registerBlock("ruined_stone", properties -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_COBBLESTONE = registerBlock("ruined_cobblestone", properties -> new Block(properties.requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_COBBLESTONE = registerBlock("ruined_mossy_cobblestone", properties -> new Block(properties.requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_STONE_BRICKS = registerBlock("ruined_stone_bricks", properties -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_STONE_BRICKS = registerBlock("ruined_mossy_stone_bricks", properties -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_CRACKED_STONE_BRICKS = registerBlock("ruined_cracked_stone_bricks", properties -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_CHISELED_STONE_BRICKS = registerBlock("ruined_chiseled_stone_bricks", properties -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_STONE_STAIRS = registerBlock("ruined_stone_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_STONE.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_COBBLESTONE_STAIRS = registerBlock("ruined_cobblestone_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_COBBLESTONE.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_COBBLESTONE_STAIRS = registerBlock("ruined_mossy_cobblestone_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_MOSSY_COBBLESTONE.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_STONE_BRICKS_STAIRS = registerBlock("ruined_stone_bricks_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_STONE_BRICKS.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_STONE_BRICKS_STAIRS = registerBlock("ruined_mossy_stone_bricks_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_MOSSY_STONE_BRICKS.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_CRACKED_STONE_BRICKS_STAIRS = registerBlock("ruined_cracked_stone_bricks_stairs", properties -> new StairBlock(ATCRBlocks.RUINED_CRACKED_STONE_BRICKS.get().defaultBlockState(), properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_STONE_SLAB = registerBlock("ruined_stone_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_COBBLESTONE_SLAB = registerBlock("ruined_cobblestone_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_COBBLESTONE_SLAB = registerBlock("ruined_mossy_cobblestone_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_STONE_BRICKS_SLAB = registerBlock("ruined_stone_bricks_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_MOSSY_STONE_BRICKS_SLAB = registerBlock("ruined_mossy_stone_bricks_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RUINED_CRACKED_STONE_BRICKS_SLAB = registerBlock("ruined_cracked_stone_bricks_slab", properties -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GRASSY_COBBLESTONE_STAIRS = registerBlock("grassy_cobblestone_stairs", properties -> new StairBlock(ATCRBlocks.GRASSY_COBBLESTONE.get().defaultBlockState(), properties.strength(2f,6f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GRASSY_COBBLESTONE_SLAB = registerBlock("grassy_cobblestone_slab", properties -> new SlabBlock(properties.strength(2f,6f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ATCRItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
