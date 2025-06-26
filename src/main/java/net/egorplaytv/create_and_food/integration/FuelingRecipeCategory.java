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
import net.egorplaytv.create_and_food.recipe.IJeiFuelingRecipe;
import net.egorplaytv.create_and_food.recipe.RecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class FuelingRecipeCategory implements IRecipeCategory<IJeiFuelingRecipe> {
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/jei/fuel.png");
    private static final ResourceLocation WIDGETS =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final IDrawableAnimated degree;


    public FuelingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(TEXTURE, 0, 0, 128, 32);
        icon = helper.createDrawable(WIDGETS, 32, 49, 16, 16);
        localizedName = new TranslatableComponent("jei.create_and_food.blast_furnace_fuel");
        this.degree = helper.drawableBuilder(WIDGETS, 163,50, 92, 5).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return getRecipeType().getUid();
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends IJeiFuelingRecipe> getRecipeClass() {
        return getRecipeType().getRecipeClass();
    }

    @Override
    public RecipeType<IJeiFuelingRecipe> getRecipeType() {
        return RecipeTypes.FUELING;
    }

    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IJeiFuelingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 8)
                .addItemStacks(recipe.getInputs());
    }

    @Override
    public void draw(IJeiFuelingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        int degree = recipe.getDegree();
        this.degree.draw(stack, 31, 19);
        TranslatableComponent text = new TranslatableComponent("jei.create_and_food.fuel.degree", degree);

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        font.draw(stack, text, 31, 8, 0xFF505050);
    }
}
