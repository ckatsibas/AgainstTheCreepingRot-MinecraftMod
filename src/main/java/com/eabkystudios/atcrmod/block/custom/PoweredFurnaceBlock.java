package com.eabkystudios.atcrmod.block.custom;

import com.eabkystudios.atcrmod.block.entity.ATCRBlockEntities;
import com.eabkystudios.atcrmod.block.entity.block.DenseRedBlockEntity;
import com.eabkystudios.atcrmod.block.entity.block.PoweredFurnaceEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;

import javax.annotation.Nullable;

public class PoweredFurnaceBlock extends AbstractPoweredBlock {
    public static final MapCodec<PoweredFurnaceBlock> CODEC = simpleCodec(PoweredFurnaceBlock::new);

    public PoweredFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PoweredFurnaceEntity(blockPos, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof PoweredFurnaceEntity poweredFurnaceEntity) {
                player.openMenu(new SimpleMenuProvider(poweredFurnaceEntity, Component.translatable("block.atcrmod.powered_furnace")), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, ATCRBlockEntities.POWERED_FURNACE_BE.get(), (level1, pos, state, entity) -> entity.tick(level1, pos, state));
    }
}
