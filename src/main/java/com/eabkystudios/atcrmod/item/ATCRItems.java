package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.food.ATCRFoods;
import com.eabkystudios.atcrmod.item.custom.SilverSwordItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATCRItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgainstTheCreepingRot.MODID);

    /*MATERIALS*/
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerSimpleItem("steel_ingot");
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerSimpleItem("silver_ingot");
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.registerSimpleItem("raw_silver");

    /*MISC*/
    public static final DeferredItem<Item> LEATHER_WRAPPED_HANDLE = ITEMS.registerSimpleItem("leather_wrapped_handle");

    /*FOODS*/
    public static final DeferredItem<Item> LOTUS_FLOWER = ITEMS.registerItem("lotus_flower",
            properties -> new Item(properties.food(ATCRFoods.LOTUS_FLOWER, ATCRFoods.LOTUS_FLOWER_CONSUMABLE)));

    /*WEAPONS*/
    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.registerItem("silver_sword",
            properties -> new SilverSwordItem(properties.sword(ATCRToolTiers.SILVER, 2, -2f)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
