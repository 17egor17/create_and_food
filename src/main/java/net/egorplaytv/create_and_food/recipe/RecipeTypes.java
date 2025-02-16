package net.egorplaytv.create_and_food.recipe;

import mezz.jei.api.recipe.RecipeType;
import net.egorplaytv.create_and_food.CreateAndFood;

public class RecipeTypes {
    public static final RecipeType<FermentationFluidBarrelRecipe> FERMENTATION_FLUID =
            RecipeType.create(CreateAndFood.MOD_ID, "fermentation_fluid", FermentationFluidBarrelRecipe.class);
    public static final RecipeType<FermentationItemBarrelRecipe> FERMENTATION_ITEM =
            RecipeType.create(CreateAndFood.MOD_ID, "fermentation_item", FermentationItemBarrelRecipe.class);
    public static final RecipeType<MarbleFurnaceRecipe> BLASTING =
            RecipeType.create(CreateAndFood.MOD_ID, "blasting", MarbleFurnaceRecipe.class);

    private RecipeTypes() {}
}
