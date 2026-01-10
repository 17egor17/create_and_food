package net.egorplaytv.caf.datagen.caf;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFFermentationRecipes {

    private static void fermentation(Consumer<FinishedRecipe> pConsumer) {

    }

    public static ResourceLocation getFermentation(String id) {
        return new ResourceLocation(MOD_ID, "fermentation/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
    private static String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer) {
        fermentation(pConsumer);
    }
}
