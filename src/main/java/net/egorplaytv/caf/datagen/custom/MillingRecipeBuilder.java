package net.egorplaytv.caf.datagen.custom;

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

public class MillingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Float> chance = Lists.newArrayList();
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final int processingTime;

    public MillingRecipeBuilder(ItemLike result, int count, float chance, int processingTime) {
        this.results.add(new ItemStack(result, count));
        this.chance.add(chance);
        this.processingTime = processingTime;
    }

    public static MillingRecipeBuilder millingRecipe(ItemLike result, int count, float chance, int processingTime) {
        return new MillingRecipeBuilder(result, count, chance, processingTime);
    }

    public static MillingRecipeBuilder millingRecipe(ItemLike result, float chance, int processingTime) {
        return new MillingRecipeBuilder(result, 1, chance, processingTime);
    }

    public static MillingRecipeBuilder millingRecipe(ItemLike result, int count, int processingTime) {
        return new MillingRecipeBuilder(result, count, 0.0f, processingTime);
    }

    public static MillingRecipeBuilder millingRecipe(ItemLike result, int processingTime) {
        return new MillingRecipeBuilder(result, 1, 0.0f, processingTime);
    }


    public MillingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public MillingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public MillingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public MillingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public MillingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    public MillingRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1, 0.0f, 1);
    }

    public MillingRecipeBuilder addResult(ItemLike result, int count) {
        return this.addResult(result, count, 0.0f, 1);
    }

    public MillingRecipeBuilder addResult(ItemLike result, float chance) {
        return this.addResult(result, 1, chance, 1);
    }

    public MillingRecipeBuilder addResult(ItemLike result, float chance, int count) {
        return this.addResult(result, count, chance, 1);
    }

    public MillingRecipeBuilder addResult(ItemLike result, int count, float chance, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.results.add(new ItemStack(result, count));
            this.chance.add(chance);
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
        return results.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new MillingRecipeBuilder.Result(pRecipeId, this.results, this.chance, this.ingredients,
                this.processingTime));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Float> chance;
        private final List<Ingredient> ingredients;
        private final int processingTime;

        public Result(ResourceLocation id, List<ItemStack> results, List<Float> chance,
                      List<Ingredient> ingredients, int processingTime) {
            this.id = id;
            this.results = results;
            this.chance = chance;
            this.ingredients = ingredients;
            this.processingTime = processingTime;
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
                        if (this.chance.get(i) > 0) {
                            resultSlot.addProperty("chance", this.chance.get(i));
                        }

                        resultArray.add(resultSlot);
                    }
                }
            }
            json.add("results", resultArray);

            json.addProperty("processingTime", this.processingTime);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MILLING.getSerializer();
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
