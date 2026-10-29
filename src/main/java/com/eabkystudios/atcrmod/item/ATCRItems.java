package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.food.ATCRFoods;
import com.eabkystudios.atcrmod.item.custom.SilverSwordItem;
import com.eabkystudios.atcrmod.item.custom.TravelerDaggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATCRItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgainstTheCreepingRot.MODID);

    /*MATERIALS*/
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerSimpleItem("steel_ingot");
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerSimpleItem("silver_ingot");
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.registerSimpleItem("raw_silver");
    public static final DeferredItem<Item> RED_SHARD = ITEMS.registerSimpleItem("red_shard");

    /*COMPONENTS*/
    public static final DeferredItem<Item> LEATHER_WRAPPED_HANDLE = ITEMS.registerSimpleItem("leather_wrapped_handle");
    public static final DeferredItem<Item> BATTERY_BASIC = ITEMS.registerSimpleItem("battery_basic");

    /*FOODS*/
    public static final DeferredItem<Item> LOTUS_FLOWER = ITEMS.registerItem("lotus_flower",
            properties -> new Item(properties.food(ATCRFoods.LOTUS_FLOWER, ATCRFoods.LOTUS_FLOWER_CONSUMABLE)));

    /*WEAPONS*/
    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.registerItem("silver_sword", properties -> new SilverSwordItem(properties.sword(ATCRToolTiers.SILVER, 3, -2f)));
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword", properties -> new Item(properties.sword(ATCRToolTiers.STEEL, 3, -2.5f)));
    public static final DeferredItem<Item> TRAVELER_SWORD = ITEMS.registerItem("traveler_sword", properties -> new Item(properties.sword(ToolMaterial.IRON, 2, -2.5f)));
    public static final  DeferredItem<Item> TRAVELER_DAGGER = ITEMS.registerItem("traveler_dagger", properties -> new TravelerDaggerItem(properties.sword(ToolMaterial.IRON, 0, -1.5f)));
    //public static final  DeferredItem<Item> TRAVELER_SHIELD = ITEMS.registerItem("traveler_shield", properties -> new ShieldItem(properties.));

    /*SPAWN EGGS*/
    public static final DeferredItem<Item> LOST_TRAVELER_SPAWN_EGG = ITEMS.registerItem("lost_traveler_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ATCREntities.LOST_TRAVELER.get())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
