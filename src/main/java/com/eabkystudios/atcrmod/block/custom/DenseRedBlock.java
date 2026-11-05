package com.eabkystudios.atcrmod.block.custom;

import com.eabkystudios.atcrmod.block.entity.ATCRBlockEntities;
import com.eabkystudios.atcrmod.block.entity.block.DenseRedBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class DenseRedBlock extends AbstractEnergyGeneratorBlock {
    public static final MapCodec<DenseRedBlock> CODEC = simpleCodec(DenseRedBlock::new);
    public static final BooleanProperty ON = BooleanProperty.create("on");

    public DenseRedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ON, true);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ON);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DenseRedBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, ATCRBlockEntities.DENSE_BLOCK_OF_RED_BE.get(), (level1, pos, state, entity) -> entity.tick(level1, pos, state));
    }
}
