package com.eabkystudios.atcrmod.creativemodtab;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgainstTheCreepingRot.MODID);

    //TODO: make method that adds all current and new items and blocks
    public static final Supplier<CreativeModeTab> ACTR_TAB = CREATIVE_MODE_TABS.register("actr_all_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ATCRItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.atcrmod.actr_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ATCRItems.STEEL_INGOT);
                        output.accept(ATCRBlocks.STEEL_BLOCK);
                        output.accept(ATCRBlocks.SILVER_ORE);
                        output.accept(ATCRBlocks.SILVER_DEEPSLATE_ORE);
                        output.accept(ATCRItems.RAW_SILVER);
                        output.accept(ATCRItems.SILVER_INGOT);
                        output.accept(ATCRItems.LOTUS_FLOWER);
                        output.accept(ATCRItems.LEATHER_WRAPPED_HANDLE);
                        output.accept(ATCRItems.SILVER_SWORD);
                        output.accept(ATCRItems.STEEL_SWORD);
                        output.accept(ATCRBlocks.GRASSY_COBBLESTONE);
                        output.accept(ATCRBlocks.POWERED_FURNACE);
                        output.accept(ATCRItems.TRAVELER_SWORD);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
