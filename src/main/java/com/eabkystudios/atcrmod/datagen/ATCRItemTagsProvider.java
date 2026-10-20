package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.item.ATCRItems;
import com.eabkystudios.tags.ATCRTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ATCRItemTagsProvider extends ItemTagsProvider {
    public ATCRItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, AgainstTheCreepingRot.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ATCRTags.Items.STEEL_ITEM)
                .add(ATCRItems.STEEL_INGOT.get());

        tag(ATCRTags.Items.SILVER_ITEM)
                .add(ATCRItems.RAW_SILVER.get())
                .add(ATCRItems.SILVER_INGOT.get())
                .add(ATCRItems.SILVER_SWORD.get());

        tag(ATCRTags.Items.SILVER_REPAIRABLE)
                .add(ATCRItems.SILVER_SWORD.get());

        tag(ItemTags.SWORDS)
                .add(ATCRItems.SILVER_SWORD.get());
    }
}
