package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.item.custom.*;
import com.eabkystudios.atcrmod.item.food.ATCRFoods;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.DeathProtection;
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
    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.registerItem("silver_sword", properties -> new SilverSwordItem(properties.sword(ATCRToolTiers.SILVER, 3f, -2f)));
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword", properties -> new Item(properties.sword(ATCRToolTiers.STEEL, 3f, -2.5f)));
    public static final DeferredItem<Item> TRAVELER_SWORD = ITEMS.registerItem("traveler_sword", properties -> new Item(properties.sword(ToolMaterial.IRON, 2f, -2.5f)));
    public static final  DeferredItem<Item> TRAVELER_DAGGER = ITEMS.registerItem("traveler_dagger", properties -> new TravelerDaggerItem(properties.sword(ToolMaterial.IRON, 0, -1.5f)));
    //public static final  DeferredItem<Item> TRAVELER_SHIELD = ITEMS.registerItem("traveler_shield", properties -> new ShieldItem(properties.));

    /*TOOLS*/
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe", properties -> new Item(properties.pickaxe(ATCRToolTiers.STEEL, 1f, -2.8f)));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.registerItem("steel_axe", properties -> new Item(properties.axe(ATCRToolTiers.STEEL, 1f, -2.8f)));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.registerItem("steel_shovel", properties -> new Item(properties.shovel(ATCRToolTiers.STEEL, 1f, -2.8f)));

    /*CURIOS*/
    public static final DeferredItem<Item> VITALITY_RING  = ITEMS.registerItem(ATCRCurios.VITALITY_RING.identifier().getPath(), RingVitalityItem::new, () -> new Item.Properties().stacksTo(1).durability(0));
    public static final DeferredItem<Item> GOLD_RING  = ITEMS.registerItem(ATCRCurios.GOLD_RING.identifier().getPath(), GoldRingItem::new, () -> new Item.Properties().stacksTo(1).durability(0));
    public static final DeferredItem<Item> AMULET_OF_THE_DEATHLESS  = ITEMS.registerItem(ATCRCurios.AMULET_OF_THE_DEATHLESS.identifier().getPath(), AmuletOfTheDeathlessItem::new, () -> new Item.Properties().stacksTo(1).durability(0).component(DataComponents.DEATH_PROTECTION, DeathProtection.TOTEM_OF_UNDYING));

    /*SPAWN EGGS*/
    public static final DeferredItem<Item> LOST_TRAVELER_SPAWN_EGG = ITEMS.registerItem("lost_traveler_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ATCREntities.LOST_TRAVELER.get())));
    public static final DeferredItem<Item> DEER_SPAWN_EGG = ITEMS.registerItem("deer_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ATCREntities.DEER.get())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
