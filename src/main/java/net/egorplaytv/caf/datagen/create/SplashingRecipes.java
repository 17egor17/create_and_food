package net.egorplaytv.caf.datagen.create;

import net.egorplaytv.caf.datagen.custom.SplashingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class SplashingRecipes {
    private static void splashing(Consumer<FinishedRecipe> pConsumer) {
        SplashingRecipeBuilder.splashingRecipe(CAFItems.RYE_DOUGH.get())
                .addIngredient(CAFItems.RYE_FLOUR.get())
                .save(pConsumer, getSplashing(getRecipeId(CAFItems.RYE_DOUGH.get())));

//        SplashingRecipeBuilder.splashingRecipe()
//                .addIngredient()
//                .save(pConsumer, getSplashing(getRecipeId()));
    }

    public static ResourceLocation getSplashing(String id) {
        return new ResourceLocation(MOD_ID, "create/splashing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        splashing(pConsumer);
    }
}
