package com.eabkystudios.atcrmod.datagen;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.item.ATCRItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ATCRRecipeProvider extends RecipeProvider {
    protected ATCRRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
            return new ATCRRecipeProvider(registries, recipeOutput);
        }

        @Override
        public String getName() {
            return "Against The Creeping Rot Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

        /*STEEL*/
        shaped(RecipeCategory.BUILDING_BLOCKS, ATCRBlocks.STEEL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ATCRItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ATCRItems.STEEL_INGOT.get()), has(ATCRItems.STEEL_INGOT))
                .group("steel")
                .save(output);

        shapeless(RecipeCategory.MISC, ATCRItems.STEEL_INGOT.get(), 9)
                .requires(ATCRBlocks.STEEL_BLOCK)
                .unlockedBy(getHasName(ATCRBlocks.STEEL_BLOCK.get()), has(ATCRBlocks.STEEL_BLOCK))
                .group("steel")
                .save(output);

        List<ItemLike> STEEL_SMELTABLES = List.of(Items.IRON_INGOT);

        //TODO: change to only accept coal based items as fuel
        oreBlasting(STEEL_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ATCRItems.STEEL_INGOT.get(), 0.2f, 100, "steel");

        /*MISC*/
        shapeless(RecipeCategory.MISC, ATCRItems.LEATHER_WRAPPED_HANDLE.get(), 1)
                .requires(Items.STICK)
                .requires(Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .group("misc")
                .save(output);

        /*SILVER*/
        shaped(RecipeCategory.TOOLS, ATCRItems.SILVER_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', ATCRItems.SILVER_INGOT.get())
                .define('S', ATCRItems.LEATHER_WRAPPED_HANDLE.get())
                .unlockedBy(getHasName(ATCRItems.SILVER_INGOT.get()), has(ATCRItems.SILVER_INGOT))
                .group("silver")
                .save(output);

        List<ItemLike> SILVER_SMELTABLES = List.of(ATCRItems.RAW_SILVER, ATCRBlocks.SILVER_ORE, ATCRBlocks.SILVER_DEEPSLATE_ORE);
        oreSmelting(SILVER_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ATCRItems.SILVER_INGOT.get(), 0.3f, 200, "silver");
        oreBlasting(SILVER_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ATCRItems.SILVER_INGOT.get(), 0.3f, 100, "silver");

        /*POWERED BLOCKS*/
        shaped(RecipeCategory.MISC, ATCRBlocks.POWERED_FURNACE.get())
                .pattern("BAB")
                .pattern("BFB")
                .pattern("SSS")
                .define('B', Blocks.BRICKS)
                .define('A', ATCRItems.BATTERY_BASIC)
                .define('F', Blocks.FURNACE)
                .define('S', ATCRBlocks.STEEL_BLOCK)
                .unlockedBy(getHasName(ATCRItems.STEEL_INGOT.get()), has(ATCRItems.STEEL_INGOT))
                .group("powered_blocks")
                .save(output);

        /*RED*/
        shaped(RecipeCategory.BUILDING_BLOCKS, ATCRBlocks.CLUSTER_OF_RED.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ATCRItems.RED_SHARD.get())
                .unlockedBy(getHasName(ATCRItems.RED_SHARD.get()), has(ATCRItems.RED_SHARD))
                .group("red")
                .save(output);
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group, String fromDesc) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, AgainstTheCreepingRot.MODID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));
        }
    }
}
