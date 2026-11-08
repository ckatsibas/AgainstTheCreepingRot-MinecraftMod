package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.conditions.NeoForgeConditions;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.CuriosTags;
import java.util.concurrent.CompletableFuture;

public class ATCRCuriosProvider extends CuriosDataProvider {
    public ATCRCuriosProvider(String modId, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries) {
        this.tag(CuriosTags.RING).add(ATCRItems.VITALITY_RING.get());
        this.tag(CuriosTags.RING).add(ATCRItems.GOLD_RING.get());

        this.tag(CuriosTags.NECKLACE).add(ATCRItems.AMULET_OF_THE_DEATHLESS.get());


        this.createEntities("player")
                .addPlayer()
                .addAllPresetSlots()
                .addCondition(NeoForgeConditions.always());
    }
}
