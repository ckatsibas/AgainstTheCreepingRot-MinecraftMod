package com.eabkystudios.atcrmod.item;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ATCRCurios {

    public static final ResourceKey<Item> VITALITY_RING = createKey("ring_of_vitality");

    private static ResourceKey<Item> createKey(String path) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, path));
    }
}
