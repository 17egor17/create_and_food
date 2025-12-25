package net.egorplaytv.create_and_food.datagen.create;

import net.egorplaytv.create_and_food.datagen.custom.SandpaperPolishingRecipeBuilder;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class SandpaperPolishingRecipes {
    private static void polishing(Consumer<FinishedRecipe> pConsumer) {
        SandpaperPolishingRecipeBuilder.sandpaperPolishingRecipe(CAFItems.RUBY.get())
                .addIngredient(CAFItems.RAW_RUBY.get())
                .save(pConsumer, getSandpaperPolishing(getRecipeId(CAFItems.RUBY.get())));
    }

    public static ResourceLocation getSandpaperPolishing(String id) {
        return new ResourceLocation(MOD_ID, "create/sandpaper_polishing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        polishing(pConsumer);
    }
}
