package com.eabkystudios.atcrmod;

import com.eabkystudios.atcrmod.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = AgainstTheCreepingRot.MODID)
public class AgainstTheCreepingRotDataGen {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        var lookupProvider = event.getLookupProvider();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true, new ATCRModelProvider(packOutput, AgainstTheCreepingRot.MODID));

        generator.addProvider(true, new ATCRBlockTagsProvider(packOutput, lookupProvider, AgainstTheCreepingRot.MODID));
        generator.addProvider(true, new ATCRItemTagsProvider(packOutput, lookupProvider, AgainstTheCreepingRot.MODID));

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ATCRBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        generator.addProvider(true, new ATCRRecipeProvider.Runner(packOutput, lookupProvider));
    }
}
