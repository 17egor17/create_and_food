package net.egorplaytv.create_and_food.recipe;

import mezz.jei.api.recipe.RecipeType;
import net.egorplaytv.create_and_food.CreateAndFood;

public class RecipeTypes {
    public static final RecipeType<FermentationBarrelRecipe> FERMENTATION =
            RecipeType.create(CreateAndFood.MOD_ID, "fermentation", FermentationBarrelRecipe.class);
    public static final RecipeType<MarbleFurnaceRecipe> BLASTING =
            RecipeType.create(CreateAndFood.MOD_ID, "blasting", MarbleFurnaceRecipe.class);
    public static final RecipeType<IJeiFuelingRecipe> FUELING =
            RecipeType.create(CreateAndFood.MOD_ID, "fueling", IJeiFuelingRecipe.class);

    private RecipeTypes() {}
}
