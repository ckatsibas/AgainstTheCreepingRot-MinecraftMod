package com.eabkystudios.atcrmod.block.entity.block;

import com.eabkystudios.atcrmod.block.entity.ATCRBlockEntities;
import com.eabkystudios.atcrmod.menu.custom.PoweredFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PoweredFurnaceEntity extends AbstractPoweredBlockEntity
{
    public PoweredFurnaceEntity(BlockPos worldPosition, BlockState blockState) {
        super(ATCRBlockEntities.POWERED_FURNACE_BE.get(), worldPosition, blockState);
    }

    public @NonNull Component getDisplayName() {
        return Component.translatable("block.atcrmod.powered_furnace");
    }

    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new PoweredFurnaceMenu(id, inventory, this, this.inventory, this.dataAccess);
    }
}
