package net.egorplaytv.create_and_food.datagen;

import net.egorplaytv.create_and_food.datagen.create_and_food.CAFBlastingRecipes;
import net.egorplaytv.create_and_food.datagen.create_and_food.CAFFermentationRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.FurnaceRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.CraftingTableRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.StoneCutterRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        new CraftingTableRecipes(pFinishedRecipeConsumer);
        new FurnaceRecipes(pFinishedRecipeConsumer);
        new StoneCutterRecipes(pFinishedRecipeConsumer);

        new CAFBlastingRecipes(pFinishedRecipeConsumer);
        new CAFFermentationRecipes(pFinishedRecipeConsumer);
    }
}