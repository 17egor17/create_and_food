package net.egorplaytv.create_and_food.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.recipe.BlastFurnaceFuelRecipe;
import net.egorplaytv.create_and_food.recipe.RecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;

public class BlastFurnaceFuelRecipeCategory implements IRecipeCategory<BlastFurnaceFuelRecipe> {

    public final static ResourceLocation TEXTURE = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/jei/fuel.png");
    private static final ResourceLocation WIDGETS = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated deg;

    public BlastFurnaceFuelRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0, 128, 32);
        this.icon = helper.createDrawable(WIDGETS, 32, 39, 15, 15);
        this.deg = helper.drawableBuilder(WIDGETS, 177,29, 35, 5).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }
    @Override
    public Component getTitle() {
        return new TranslatableComponent("jei.create_and_food.blast_furnace_fuel");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return getRecipeType().getUid();
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends BlastFurnaceFuelRecipe> getRecipeClass() {
        return getRecipeType().getRecipeClass();
    }

    @Override
    public RecipeType<BlastFurnaceFuelRecipe> getRecipeType() {
        return RecipeTypes.FUELING;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, BlastFurnaceFuelRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 8).addItemStacks(Arrays.asList(recipe.input.get(0).getItems()));
    }

    @Override
    public void draw(BlastFurnaceFuelRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        this.deg.draw(stack, 31, 19);

        int deg = recipe.getDeg();
        TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.fuel.degree", deg);
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        fontRenderer.draw(stack, timeString, 32, 9, 0xFFe0e0e0);
    }
}
