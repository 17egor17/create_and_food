package net.egorplaytv.create_and_food.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
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
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class MarbleBlastFurnaceRecipeCategory implements IRecipeCategory<MarbleFurnaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(CreateAndFood.MOD_ID, "blasting");
    public final static ResourceLocation TEXTURE = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/jei/crafts.png");
    private static final ResourceLocation WIDGETS = new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");

    private final IDrawable background;
    private final IDrawableAnimated arrow;
    private final IDrawable icon;
    private final IDrawableAnimated deg;

    public static final RecipeType<MarbleFurnaceRecipe> BLASTING_TYPE =
            new RecipeType<>(UID, MarbleFurnaceRecipe.class);

    public MarbleBlastFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 91, 136, 90);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MARBLE_BLAST_FURNACE.get()));
        this.arrow = helper.drawableBuilder(WIDGETS, 225,3,30,10).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        this.deg = helper.drawableBuilder(WIDGETS, 177,29, 35, 5).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(MarbleFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        drawTimeSecond(recipe, stack, 57, 78);
        degrees(recipe, stack, 13,56);
        this.arrow.draw(stack,40,36);
        this.deg.draw(stack, 13, 66);
    }

    protected void drawTimeSecond(MarbleFurnaceRecipe recipe, PoseStack poseStack, int x, int y) {
        int time = recipe.getTime();
        if (time >= 1200) {
            int cookTimeMinutes = time / 1200 ;
            TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.minute_time", cookTimeMinutes);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            fontRenderer.draw(poseStack, timeString, x, y, 0xFF424242);
        } else if (time <= 1180){
            int cookTimeSeconds = time / 20;
            TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.second_time", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            fontRenderer.draw(poseStack, timeString, x, y, 0xFF424242);
        }
    }
    private void degrees(MarbleFurnaceRecipe recipe, PoseStack stack, int x, int y) {
        int deg = recipe.getDeg();
        TranslatableComponent timeString = new TranslatableComponent("jei.create_and_food.blasting.deg", deg);
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        fontRenderer.draw(stack, timeString, x, y, 0xFF424242);
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("jei.create_and_food.blasting");
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
        return UID;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends MarbleFurnaceRecipe> getRecipeClass() {
        return MarbleFurnaceRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MarbleFurnaceRecipe recipe, IFocusGroup focuses) {
        if (recipe.getIngredients().size() == 1){
            builder.addSlot(RecipeIngredientRole.INPUT, 11,11).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.OUTPUT, 91,37).addItemStack(recipe.getResultItem());
        } else if (recipe.getIngredients().size() == 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 11,11).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 31,11).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.OUTPUT, 91,37).addItemStack(recipe.getResultItem());
        } else if (recipe.getIngredients().size() == 3) {
            builder.addSlot(RecipeIngredientRole.INPUT, 11, 11).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 31, 11).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 21, 31).addIngredients(recipe.getIngredients().get(2));
            builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 37).addItemStack(recipe.getResultItem());
        }
    }
}
