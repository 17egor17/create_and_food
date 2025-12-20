package net.egorplaytv.create_and_food.datagen.create_and_food;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class CAFFermentationRecipes {
    public CAFFermentationRecipes(Consumer<FinishedRecipe> pConsumer) {
        fermentation(pConsumer);
    }

    private void fermentation(Consumer<FinishedRecipe> pConsumer) {

    }

    public ResourceLocation getFermentation(String id) {
        return new ResourceLocation(MOD_ID, "fermentation/" + id);
    }

    private String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
    private String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }
}
