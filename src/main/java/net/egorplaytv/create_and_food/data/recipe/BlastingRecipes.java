package net.egorplaytv.create_and_food.data.recipe;

import net.egorplaytv.create_and_food.data.recipe.builders.BlastingRecipeBuilder;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class BlastingRecipes {
    private static final int minTime = 200;
    private static final int oneIngot = 400;
    private static final int DoubleIngot = 800;
    private static final int TripleIngot = 1200;

    public BlastingRecipes(){
    }

    public static void register(Consumer<FinishedRecipe> consumer) {
        blastingDusts(consumer);
        blastingAlloys(consumer);
        blastingRawOres(consumer);
        blastingCrashedRawOres(consumer);
    }

    private static void blastingCrashedRawOres(Consumer<FinishedRecipe> consumer) {

    }

    private static void blastingRawOres(Consumer<FinishedRecipe> consumer) {

    }

    private static void blastingAlloys(Consumer<FinishedRecipe> consumer) {

    }

    private static void blastingDusts(Consumer<FinishedRecipe> consumer) {

    }
}
