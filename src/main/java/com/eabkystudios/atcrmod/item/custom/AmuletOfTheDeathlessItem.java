package com.eabkystudios.atcrmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class AmuletOfTheDeathlessItem extends Item implements ICurioItem {
    private static boolean shouldPreventDeath = false;
    private int tickCounter;

    public AmuletOfTheDeathlessItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        shouldPreventDeath = true;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        tickCounter++;

        if (tickCounter >= 6000 && !shouldPreventDeath){
            shouldPreventDeath = true;
            tickCounter = 0;
        }
    }

    public static boolean isShouldPreventDeath() {
        return shouldPreventDeath;
    }

    public static void setShouldPreventDeath(boolean change) {
        shouldPreventDeath = change;
    }
}
