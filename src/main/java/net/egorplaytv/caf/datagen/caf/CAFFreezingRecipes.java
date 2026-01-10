package net.egorplaytv.caf.datagen.caf;

import net.egorplaytv.caf.datagen.custom.FreezingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFFreezingRecipes {
    private static void frizzing(Consumer<FinishedRecipe> pConsumer) {
        FreezingRecipeBuilder.freezingRecipe(CAFItems.STEEL_INGOT.get())
                .addIngredient(CAFItems.STEEL_INGOT.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.STEEL_INGOT.get())));
    }

    public static ResourceLocation getFrizzing(String id) {
        return new ResourceLocation(MOD_ID, "frizzing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer) {
        frizzing(pConsumer);
    }
}
