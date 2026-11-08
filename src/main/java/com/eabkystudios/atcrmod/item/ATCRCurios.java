package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ATCRCurios {

    public static final ResourceKey<Item> VITALITY_RING = createKey("ring_of_vitality");
    public static final ResourceKey<Item> GOLD_RING = createKey("gold_ring");
    public static final ResourceKey<Item> AMULET_OF_THE_DEATHLESS = createKey("amulet_of_the_deathless");

    private static ResourceKey<Item> createKey(String path) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, path));
    }
}
