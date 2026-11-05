package com.eabkystudios.atcrmod.block.entity.block;

import com.eabkystudios.atcrmod.block.custom.AbstractPoweredBlock;
import com.eabkystudios.atcrmod.block.resources.RedEnergy;
import com.eabkystudios.atcrmod.data.ATCRDataComponents;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class AbstractPoweredBlockEntity extends BlockEntity implements MenuProvider {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    protected final ContainerData dataAccess;
    protected final RedEnergy energy;
    protected int progress = 0;
    protected int maxProgress = 72;
    private int litTimeRemaining;
    private int litTotalTime;
    private int cookingTimer;
    private int cookingTotalTime;
    protected int capacity;
    private int pendingEnergy;


    public AbstractPoweredBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState, int capacity, int receive, int extract) {
        super(type, worldPosition, blockState);

        energy = new RedEnergy(capacity, receive, extract);
        this.capacity = capacity;
        this.dataAccess = new ContainerData() {
            {
                Objects.requireNonNull(AbstractPoweredBlockEntity.this);
                Objects.requireNonNull(AbstractPoweredBlockEntity.this);
            }

            @Override
            public int get(int dataId) {
                switch (dataId) {
                    case 0:
                        return AbstractPoweredBlockEntity.this.progress;
                    case 1:
                        return AbstractPoweredBlockEntity.this.maxProgress;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> AbstractPoweredBlockEntity.this.progress = value;
                    case 1 -> AbstractPoweredBlockEntity.this.maxProgress = value;
                }

            }

            // How many pieces of data in the container.
            @Override
            public int getCount() {
                return 2;
            }
        };

        // this.quickCheck = RecipeManager.createCheck(recipeType);
        // this.recipeType = recipeType;
    }

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(2){
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            AbstractPoweredBlockEntity.this.setChanged();
        }
    };

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, i);
            inv.setItem(i, new ItemStack(itemAccess.getResource().getItem(), itemAccess.getAmount()));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
    }

    public void setEnergy(int amount) {
        energy.set(Math.clamp(amount, 0, capacity));
    }

    public void craftItem() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().assemble(new SingleRecipeInput(inventory.getResource(INPUT_SLOT).toStack()));

        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, OUTPUT_SLOT);

            inventory.extract(INPUT_SLOT, inventory.getResource(INPUT_SLOT), 1, transaction);
            inventory.set(OUTPUT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());

            transaction.commit();
        }
    }

    public boolean hasRecipe() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().assemble(new SingleRecipeInput(inventory.getResource(INPUT_SLOT).toStack()));

        boolean outputSlotAmount = canInsertAmountIntoOutputSlot(output.getCount());
        boolean outputSlotItem = canInsertItemIntoOutputSlot(output);

        return outputSlotAmount && outputSlotItem;
    }

    public Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe() {
        return ((ServerLevel)level).recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(inventory.getResource(INPUT_SLOT).toStack()), level);
    }

    public boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).is(output.getItem());
    }

    public boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getResource(OUTPUT_SLOT).isEmpty() ? 64 : inventory.getResource(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.getAmountAsInt(OUTPUT_SLOT);

        return maxCount >= currentCount + count;
    }

    public boolean isOutputSlotEmptyOrReceivable() {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).test(stack -> stack.count() < stack.getMaxStackSize());
    }

    public boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    public void increaseCraftingProgress() {
        progress++;
    }

    public void resetProgress() {
        progress = 0;
        maxProgress = 72;
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
            setEnergy(amount);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        super.removeComponentsFromTag(output);
        output.discard("energy");
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return null;
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
        output.putInt("powered_furnace.progress", progress);
        output.putInt("powered_furnace.max_progress", maxProgress);
        output.putInt("energy", energy.getEnergyStored());

        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr("powered_furnace.progress", 0);
        maxProgress = input.getIntOr("powered_furnace.max_progress", 0);
        setEnergy(input.getIntOr("energy", 0));

        input.child("inventory").ifPresent(inventory::deserialize);
    }
}
