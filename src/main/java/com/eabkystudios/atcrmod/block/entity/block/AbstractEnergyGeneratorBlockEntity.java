package com.eabkystudios.atcrmod.block.entity.block;

import com.eabkystudios.atcrmod.block.resources.RedEnergy;
import com.eabkystudios.atcrmod.data.ATCRDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public abstract class AbstractEnergyGeneratorBlockEntity extends BlockEntity {
    protected int capacity;
    protected final RedEnergy energy;
    private int pendingEnergy;


    public AbstractEnergyGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int capacity, int receive, int extract) {
        super(type, pos, state);

        energy = new RedEnergy(capacity, receive, extract);
        this.capacity = capacity;
        setEnergy(pendingEnergy);
    }

    protected void tick(Level level, BlockPos pos, BlockState state) {}

    public void setEnergy(int amount) {
        energy.set(Math.clamp(amount, 0, capacity));
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        if (energy != null) {
            components.set(ATCRDataComponents.ENERGY.get(), energy.getEnergyStored());
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        int amount = components.getOrDefault(ATCRDataComponents.ENERGY.get(), 0);
        if (energy == null) {
            pendingEnergy = amount;
        } else {
            setEnergy(amount);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        super.removeComponentsFromTag(output);
        output.discard("energy");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void onDataPacket(Connection net, ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("energy", energy.getEnergyStored());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        setEnergy(input.getIntOr("energy", 0));
    }
}


