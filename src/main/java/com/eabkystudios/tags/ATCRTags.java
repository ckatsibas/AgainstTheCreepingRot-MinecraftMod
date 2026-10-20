package com.eabkystudios.tags;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ATCRTags {
    public static class Items{
        public static final TagKey<Item> STEEL_ITEM = createTag("steel_item");
        public static final TagKey<Item> SILVER_ITEM = createTag("silver_item");
        public static final TagKey<Item> SILVER_REPAIRABLE = createTag("silver_repairable");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
        }
    }

    public static class Blocks{

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, name));
        }
    }
}
