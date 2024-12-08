package net.egorplaytv.create_and_food.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.recipe.FermentationBarrelRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FermentationBarrelRecipeCategory implements IRecipeCategory<FermentationBarrelRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(CreateAndFood.MOD_ID, "fermentation");
    public final static ResourceLocation TEXTURE = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/jei/crafts.png");
    private static final ResourceLocation WIDGETS = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");

    private final IDrawable background;
    private final IDrawable fluidTank;
    private final IDrawableAnimated arrow;
    private final IDrawable icon;

    public static final RecipeType<FermentationBarrelRecipe> FERMENTATION_TYPE =
            new RecipeType<>(UID, FermentationBarrelRecipe.class);

    public FermentationBarrelRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,77,134,83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FERMENTATION_BARREL.get()));
        this.arrow = helper.drawableBuilder(WIDGETS, 177,0,24,12).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        this.fluidTank = helper.createDrawable(WIDGETS, 0,49,16,63);
    }

    @Override
    public void draw(FermentationBarrelRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        drawTimeSecond(recipe, stack,57, 57);
        this.arrow.draw(stack,57,35);
        this.fluidTank.draw(stack, 14,14);
    }

    protected void drawTimeSecond(FermentationBarrelRecipe recipe, PoseStack poseStack, int x, int y) {
        int time = recipe.getTime();
        if (time >= 24000){
            int cookTimeDays = time / 24000;
            TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.day_time", cookTimeDays);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            fontRenderer.draw(poseStack, timeString, x, y, 0xFF808080);
        } else if (time >= 1200) {
            int cookTimeMinutes = time / 1200 ;
            TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.minute_time", cookTimeMinutes);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            fontRenderer.draw(poseStack, timeString, x, y, 0xFF808080);
        } else if (time <= 1180){
            int cookTimeSeconds = time / 20;
            TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.second_time", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            fontRenderer.draw(poseStack, timeString, x, y, 0xFF808080);
        }
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("jei.create_and_food.fermentation");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends FermentationBarrelRecipe> getRecipeClass() {
        return FermentationBarrelRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FermentationBarrelRecipe recipe, IFocusGroup focuses) {
        if(recipe.getIngredients().size() == 1){
            builder.addSlot(RecipeIngredientRole.INPUT, 36,15).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 61,15).addItemStack(recipe.getInputTool());
            builder.addSlot(RecipeIngredientRole.INPUT, 14, 14)
                    .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getInputFluid()))
                    .setFluidRenderer(2000, false, 16, 56);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 88,33).addItemStack(recipe.getResultItem());
        } else if(recipe.getIngredients().size() == 2){
            builder.addSlot(RecipeIngredientRole.INPUT, 36,15).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 36,33).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 61,15).addItemStack(recipe.getInputTool());
            builder.addSlot(RecipeIngredientRole.INPUT, 14, 14)
                    .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getInputFluid()))
                    .setFluidRenderer(2000, false, 16, 56);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 88,33).addItemStack(recipe.getResultItem());
        } else if(recipe.getIngredients().size() == 3){
            builder.addSlot(RecipeIngredientRole.INPUT, 36,15).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 36,33).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 36,51).addIngredients(recipe.getIngredients().get(2));
            builder.addSlot(RecipeIngredientRole.INPUT, 61,15).addItemStack(recipe.getInputTool());
            builder.addSlot(RecipeIngredientRole.INPUT, 14, 14)
                    .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getInputFluid()))
                    .setFluidRenderer(2000, false, 16, 56);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 88,33).addItemStack(recipe.getResultItem());
        }
    }

}
