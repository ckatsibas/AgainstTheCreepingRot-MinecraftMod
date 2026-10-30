package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.entity.ATCREntities;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class ATCREntityLootTableProvider extends EntityLootSubProvider {
    public ATCREntityLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }


    @Override
    public void generate() {
        add(ATCREntities.LOST_TRAVELER.get(),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(3, 5))))
                        .add(LootItem.lootTableItem(ATCRItems.TRAVELER_SWORD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        // add(ModEntities.NO_LOOTTABLE.get(), LootTable.lootTable());
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ATCREntities.ENTITY_TYPES.getEntries().stream().map(Holder::value);
    }
}
