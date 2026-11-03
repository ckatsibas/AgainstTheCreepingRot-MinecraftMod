package com.eabkystudios.atcrmod.jei;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.jei.categories.PoweredFurnaceCategory;
import com.eabkystudios.atcrmod.menu.custom.PoweredFurnaceScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

import java.util.List;

@JeiPlugin
public class ATCRJEIPlugin implements IModPlugin {
    private static RecipeMap syncedRecipes = RecipeMap.EMPTY;

    public static final IRecipeType<RecipeHolder<SmeltingRecipe>> POWERED_FURNACE = createRecipeHolderType("power_smelting");

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "jei_plugin");
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PoweredFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(POWERED_FURNACE, this.getRecipes(syncedRecipes, RecipeType.SMELTING));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PoweredFurnaceScreen.class, 74, 30, 22, 20, POWERED_FURNACE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(POWERED_FURNACE, new ItemStack(ATCRBlocks.POWERED_FURNACE));
    }


    // From Occultism
    // Under MIT License
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(RecipeMap recipeMap, RecipeType<T> type) {
        return (List) recipeMap.byType(type);
    }

    @SuppressWarnings("unchecked")
    public static <T> IRecipeType<T> createRecipeHolderType(String path) {
        return (IRecipeType<T>) IRecipeType.create(Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, path), RecipeHolder.class);
    }

    @EventBusSubscriber(modid = AgainstTheCreepingRot.MODID)
    public static class ServerRecipeSync {
        @SubscribeEvent
        public static void onDatapackSync(OnDatapackSyncEvent event) {
            event.sendRecipes(
                    RecipeType.SMELTING
            );
        }
    }

    @EventBusSubscriber(modid = AgainstTheCreepingRot.MODID, value = Dist.CLIENT)
    public static class ClientRecipeSync {
        @SubscribeEvent
        public static void onRecipeReceived(RecipesReceivedEvent event) {
            syncedRecipes = event.getRecipeMap();
        }
    }
}
