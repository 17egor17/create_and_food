package net.egorplaytv.create_and_food.recipe.recipe;

import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.recipe.builder.MarbleFurnaceRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class BlastingRecipes {

    public static void register(Consumer<FinishedRecipe> consumer){
        blasting(consumer);
    }

    private static void blasting(Consumer<FinishedRecipe> consumer){
        MarbleFurnaceRecipeBuilder.blastingRecipe(ModItems.NETHER_ALLOY.get(), 200, 700, 5.0F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(ModItems.ALLOY_SOULS.get())
                .save(consumer);
    }
}
