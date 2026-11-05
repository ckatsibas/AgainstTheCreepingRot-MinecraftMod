package com.eabkystudios.atcrmod.block.entity.block;

import com.eabkystudios.atcrmod.block.custom.DenseRedBlock;
import com.eabkystudios.atcrmod.block.entity.ATCRBlockEntities;
import com.eabkystudios.atcrmod.block.resources.RedEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DenseRedBlockEntity extends AbstractEnergyGeneratorBlockEntity {
    public static final int GENERATION_RATE = 20;
    protected static final int CAPACITY = 10000;
    private static final Logger log = LoggerFactory.getLogger(DenseRedBlockEntity.class);

    public DenseRedBlockEntity(BlockPos pos, BlockState state) {
        super(ATCRBlockEntities.DENSE_BLOCK_OF_RED_BE.get(), pos, state, CAPACITY, 0, GENERATION_RATE);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {

        boolean generating = energy.getEnergyStored() <= CAPACITY - GENERATION_RATE;

        setEnergy(energy.getEnergyStored() + GENERATION_RATE);

        if (getBlockState().getValue(DenseRedBlock.ON) != generating) {
            level.setBlock(worldPosition, getBlockState().setValue(DenseRedBlock.ON, generating), Block.UPDATE_CLIENTS);
        }

        Direction[] directions = Direction.values();
        int start = (int) (level.getGameTime() % directions.length);

        for (int i = 0; i < directions.length; i++) {
            Direction side = directions[(start + i) % directions.length];
            BlockPos targetPos = worldPosition.relative(side);

            if (!level.hasChunkAt(targetPos)) {
                continue;
            }

            var target = level.getCapability(Capabilities.Energy.BLOCK, targetPos, side.getOpposite());
            if (target == null) {
                continue;
            }

            int offered = energy.extractEnergy(GENERATION_RATE, true);
            int accepted;

            try (var transaction = net.neoforged.neoforge.transfer.transaction.Transaction.openRoot()) {
                accepted = target.insert(offered, transaction);
                transaction.commit();
            }

            energy.extractEnergy(accepted, false);
        }

        log.debug("Energy in generator: " + energy.getEnergyStored());
    }
}
