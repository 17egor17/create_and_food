package net.egorplaytv.caf.datagen.caf;

import com.google.common.collect.Maps;
import net.egorplaytv.caf.datagen.custom.FreezingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.Map;
import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFFreezingRecipes {
    private static void freezing(Consumer<FinishedRecipe> pConsumer) {
        FreezingRecipeBuilder.freezingRecipe(CAFItems.STEEL_INGOT.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.STEEL_INGOT.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.STEEL_INGOT.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.STEEL_NUGGET.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.STEEL_NUGGET.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.STEEL_NUGGET.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.STEEL_SHEET.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.STEEL_SHEET.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.STEEL_SHEET.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.STEEL_DUST.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.STEEL_DUST.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.STEEL_DUST.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.RAW_IRON.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.RAW_IRON.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.IRON_INGOT.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.IRON_INGOT.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.IRON_INGOT.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.IRON_NUGGET.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.IRON_NUGGET.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.IRON_NUGGET.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.IRON_DUST.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.IRON_DUST.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.IRON_DUST.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.RAW_COPPER.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.RAW_COPPER.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.RAW_COPPER.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.COPPER_INGOT.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.COPPER_INGOT.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.COPPER_INGOT.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.COPPER_NUGGET.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.COPPER_NUGGET.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.COPPER_NUGGET.get())));

        FreezingRecipeBuilder.freezingRecipe(CAFItems.COPPER_COIN.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.COPPER_COIN.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.COPPER_COIN.get())));

        //Other Recipes with Metals

        FreezingRecipeBuilder.freezingRecipe(CAFItems.NICKEL_INGOT.get())
                .addResultFValueNBT(setResultNBT("deg", 24))
                .addIngredient(CAFItems.NICKEL_INGOT.get())
                .save(pConsumer, getFrizzing(getRecipeId(CAFItems.NICKEL_INGOT.get())));
    }

    public static ResourceLocation getFrizzing(String id) {
        return new ResourceLocation(MOD_ID, "freezing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static Map.Entry<String, String> setResultNBT(String key, String value) {
        return Maps.immutableEntry(key, value);
    }

    private static Map.Entry<String, Float> setResultNBT(String key, float value) {
        return Maps.immutableEntry(key, value);
    }

    public static void register(Consumer<FinishedRecipe> pConsumer) {
        freezing(pConsumer);
    }
}
