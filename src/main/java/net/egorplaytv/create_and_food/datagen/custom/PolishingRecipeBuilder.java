package net.egorplaytv.create_and_food.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.recipe.AllRecipeTypes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class PolishingRecipeBuilder implements net.minecraft.data.recipes.RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final int speedLimits;
    private final int processingTime;
    private final boolean fragile;

    public PolishingRecipeBuilder(ItemLike result, int count, int speedLimits, int processingTime, boolean fragile) {
        this.results.add(new ItemStack(result, count));
        this.speedLimits = speedLimits;
        this.processingTime = processingTime;
        this.fragile = fragile;
    }

    public static PolishingRecipeBuilder polishingRecipe(ItemLike result, int count, int speedLimits, int processingTime, boolean fragile) {
        return new PolishingRecipeBuilder(result, count, speedLimits, processingTime, fragile);
    }

    public static PolishingRecipeBuilder polishingRecipe(ItemLike result, int speedLimits, int processingTime, boolean fragile) {
        return new PolishingRecipeBuilder(result, 1, speedLimits, processingTime, fragile);
    }

    public static PolishingRecipeBuilder polishingRecipe(ItemLike result, int processingTime, boolean fragile) {
        return new PolishingRecipeBuilder(result, 1, 1, processingTime, fragile);
    }

    public static PolishingRecipeBuilder polishingRecipe(ItemLike result, int processingTime) {
        return new PolishingRecipeBuilder(result, 1, 1, processingTime, false);
    }

    public PolishingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public PolishingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public PolishingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public PolishingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public PolishingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    @Override
    public net.minecraft.data.recipes.RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.results.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new PolishingRecipeBuilder.Result(pRecipeId, this.results, this.ingredients,
                this.speedLimits, this.processingTime, this.fragile));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Ingredient> ingredients;
        private final int speedLimits;
        private final int processingTime;
        private final boolean fragile;

        public Result(ResourceLocation id, List<ItemStack> results,
                      List<Ingredient> ingredients, int speedLimits, int processingTime, boolean fragile) {
            this.id = id;
            this.results = results;
            this.ingredients = ingredients;
            this.speedLimits = speedLimits;
            this.processingTime = processingTime;
            this.fragile = fragile;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientArray = new JsonArray();
            if (this.ingredients != null){
                for (int i = 0; i < this.ingredients.size(); ++i) {
                    Ingredient ingredient = this.ingredients.get(i);
                    if (!this.ingredients.isEmpty()) {
                        ingredientArray.add(ingredient.toJson());
                    }
                }
            }
            json.add("ingredients", ingredientArray);

            JsonArray resultArray = new JsonArray();
            if (this.results != null){
                for (int i = 0; i < this.results.size(); ++i) {
                    ItemStack result = this.results.get(i);
                    if (!this.results.isEmpty()) {
                        JsonObject resultSlot = new JsonObject();
                        resultSlot.addProperty("item", result.getItem().getRegistryName().toString());
                        if (result.getCount() > 1) {
                            resultSlot.addProperty("count", result.getCount());
                        }

                        resultArray.add(resultSlot);
                    }
                }
            }
            json.add("results", resultArray);
            json.addProperty("speedLimits", speedLimits);
            json.addProperty("processingTime", processingTime);
            json.addProperty("fragile", fragile);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.POLISHING.getSerializer();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
