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

public class HauntingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Ingredient> ingredients = Lists.newArrayList();

    public HauntingRecipeBuilder(ItemLike result, int count) {
        this.results.add(new ItemStack(result, count));
    }

    public static HauntingRecipeBuilder sandpaperPolishingRecipe(ItemLike result, int count) {
        return new HauntingRecipeBuilder(result, count);
    }

    public static HauntingRecipeBuilder sandpaperPolishingRecipe(ItemLike result) {
        return new HauntingRecipeBuilder(result, 1);
    }

    public HauntingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public HauntingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public HauntingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public HauntingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public HauntingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    public HauntingRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1, 1);
    }

    public HauntingRecipeBuilder addResult(ItemLike result, int count) {
        return this.addResult(result, count, 1);
    }

    public HauntingRecipeBuilder addResult(ItemLike result, int count, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.results.add(new ItemStack(result, count));
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
        pFinishedRecipeConsumer.accept(new HauntingRecipeBuilder.Result(pRecipeId, this.results, this.ingredients));
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
                        if (result.getCount() > 1) {
                            resultSlot.addProperty("count", result.getCount());
                        }

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
            return AllRecipeTypes.HAUNTING.getSerializer();
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
