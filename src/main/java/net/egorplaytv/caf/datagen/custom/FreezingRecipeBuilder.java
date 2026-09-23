package net.egorplaytv.caf.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.caf.recipe.AllRecipeTypes;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FreezingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Map.Entry<String, String>> stringValueNBT = Lists.newArrayList();
    private final List<Map.Entry<String, Float>> floatValueNBT = Lists.newArrayList();
    private final List<Ingredient> ingredients = Lists.newArrayList();

    public FreezingRecipeBuilder(ItemLike result, int count) {
        this.results.add(new ItemStack(result, count));
    }

    public static FreezingRecipeBuilder freezingRecipe(ItemLike result, int count) {
        return new FreezingRecipeBuilder(result, count);
    }

    public static FreezingRecipeBuilder freezingRecipe(ItemLike result) {
        return new FreezingRecipeBuilder(result, 1);
    }

    public FreezingRecipeBuilder addResultFValueNBT(Map.Entry<String, Float> nbt) {
        this.floatValueNBT.add(nbt);
        return this;
    }

    public FreezingRecipeBuilder addResultFValueNBT(Map.Entry<String, Float>... nbt) {
        this.floatValueNBT.addAll(Arrays.asList(nbt));
        return this;
    }

    public FreezingRecipeBuilder addResultSValueNBT(Map.Entry<String, String> nbt) {
        this.stringValueNBT.add(nbt);
        return this;
    }

    public FreezingRecipeBuilder addResultSValueNBT(Map.Entry<String, String>... nbt) {
        this.stringValueNBT.addAll(Arrays.asList(nbt));
        return this;
    }

    public FreezingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public FreezingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public FreezingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public FreezingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public FreezingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
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
        pFinishedRecipeConsumer.accept(new FreezingRecipeBuilder.Result(pRecipeId, this.results, this.floatValueNBT, this.stringValueNBT, this.ingredients));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Map.Entry<String, String>> stringValueNBT;
        private final List<Map.Entry<String, Float>> floatValueNBT;
        private final List<Ingredient> ingredients;

        public Result(ResourceLocation id, List<ItemStack> results, List<Map.Entry<String, Float>> floatNBT, List<Map.Entry<String, String>> stringNBT, List<Ingredient> ingredients) {
            this.id = id;
            this.results = results;
            this.floatValueNBT = floatNBT;
            this.stringValueNBT = stringNBT;
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
                        if (!this.stringValueNBT.isEmpty())
                            for (int j = 0; j <= (this.stringValueNBT.size() - 1); j++) {
                                JsonObject nbt = new JsonObject();
                                nbt.addProperty(this.stringValueNBT.get(j).getKey(), this.stringValueNBT.get(j).getValue());
                                resultSlot.add("nbt", nbt);
                            }
                        if (!this.floatValueNBT.isEmpty())
                            for (int j = 0; j <= (this.floatValueNBT.size() - 1); j++) {
                                JsonObject nbt = new JsonObject();
                                nbt.addProperty(this.floatValueNBT.get(j).getKey(), this.floatValueNBT.get(j).getValue());
                                resultSlot.add("nbt", nbt);
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
            return AllRecipeTypes.FREEZING.getSerializer();
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
