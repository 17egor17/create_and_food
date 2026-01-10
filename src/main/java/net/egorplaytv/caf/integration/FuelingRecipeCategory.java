package net.egorplaytv.caf.integration;

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
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.caf.config.DegreeUnits;
import net.egorplaytv.caf.recipe.IJeiFuelingRecipe;
import net.egorplaytv.caf.recipe.RecipeTypes;
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
        localizedName = new TranslatableComponent("jei.caf.blast_furnace_fuel");
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
        int degreeC = recipe.getDegree();
        float degreeF = degreeC*1.8F+32;
        float degreeK = degreeC+273.15F;
        this.degree.draw(stack, 31, 19);
        TranslatableComponent degC = new TranslatableComponent("jei.caf.fuel.degreeC", degreeC);
        TranslatableComponent degF = new TranslatableComponent("jei.caf.fuel.degreeF", degreeF);
        TranslatableComponent degK = new TranslatableComponent("jei.caf.fuel.degreeK", degreeK);

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;

        if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_CELSIUS) {
            font.draw(stack, degC, 31, 8, 0xFF505050);
        } else if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_FAHRENHEIT) {
            font.draw(stack, degF, 31, 8, 0xFF505050);
        } else if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_KELVIN) {
            font.draw(stack, degK, 31, 8, 0xFF505050);
        }
    }
}
