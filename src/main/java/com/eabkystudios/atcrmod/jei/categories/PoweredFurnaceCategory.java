package com.eabkystudios.atcrmod.jei.categories;

import com.eabkystudios.atcrmod.AgainstTheCreepingRot;
import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.eabkystudios.atcrmod.jei.ATCRJEIPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import org.jspecify.annotations.Nullable;

public class PoweredFurnaceCategory implements IRecipeCategory<RecipeHolder<SmeltingRecipe>> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AgainstTheCreepingRot.MODID, "textures/gui/powered_furnace/powered_furnace_gui.png");
    private final IDrawable icon;
    private final IDrawable overlay;

    public PoweredFurnaceCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ATCRBlocks.POWERED_FURNACE));
    }

    @Override
    public IRecipeType<RecipeHolder<SmeltingRecipe>> getRecipeType() {
        return ATCRJEIPlugin.POWERED_FURNACE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.atcrmod.powered_furnace");
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SmeltingRecipe> recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).add(recipe.value().input());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).add(recipe.value().result());
    }

    @Override
    public void draw(RecipeHolder<SmeltingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
}
