package net.egorplaytv.create_and_food.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
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

public class SplashingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Ingredient> ingredients = Lists.newArrayList();

    public SplashingRecipeBuilder(ItemLike result, int count) {
        this.results.add(new ItemStack(result, count));
    }

    public static SplashingRecipeBuilder splashingRecipe(ItemLike result, int count) {
        return new SplashingRecipeBuilder(result, count);
    }

    public static SplashingRecipeBuilder splashingRecipe(ItemLike result) {
        return new SplashingRecipeBuilder(result, 1);
    }

    public SplashingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public SplashingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public SplashingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public SplashingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public SplashingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
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
        pFinishedRecipeConsumer.accept(new SplashingRecipeBuilder.Result(pRecipeId, this.results, this.ingredients));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Ingredient> ingredients;

        public Result(ResourceLocation id, List<ItemStack> results, List<Ingredient> ingredients) {
            this.id = id;
            this.results = results;
            this.ingredients = ingredients;
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
                        resultArray.add(resultSlot);
                    }
                }
            }
            json.add("results", resultArray);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.SPLASHING.getSerializer();
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
