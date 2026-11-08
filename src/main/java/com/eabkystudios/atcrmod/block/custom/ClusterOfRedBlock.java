package com.eabkystudios.atcrmod.block.custom;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.Nullable;

public class ClusterOfRedBlock extends DropExperienceBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public ClusterOfRedBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties);
    }


    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, true);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
