package net.egorplaytv.caf.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.egorplaytv.caf.recipe.fluids.FluidStack;
import net.egorplaytv.caf.util.FluidJSONUtil;
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
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class MixingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> itemResults = Lists.newArrayList();
    private final List<Float> chance = Lists.newArrayList();
    private final List<FluidStack> fluidResults = Lists.newArrayList();
    private final List<Ingredient> itemIngredients = Lists.newArrayList();
    private final List<FluidIngredient> fluidIngredients = Lists.newArrayList();
    private final HeatCondition heat;

    public MixingRecipeBuilder(ItemLike itemResult, int count, float itemChance, Fluid fluidResult,
                                   int amount, HeatCondition heat){
        if (itemResult != null) {
            this.itemResults.add(new ItemStack(itemResult, count));
        }
        this.chance.add(itemChance);
        if (fluidResult != null) {
            this.fluidResults.add(new FluidStack(fluidResult, amount));
        }
        this.heat = heat;
    }

    // Mixing Recipe Item
    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count, float chance, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, count, chance, null, 0, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, float chance, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, 1, chance, null, 0, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, count, 0.0f, null, 0, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, 1, 0.0f, null, 0, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count, float chance) {
        return new MixingRecipeBuilder(itemResult, count, chance, null, 0, HeatCondition.NONE);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, float chance) {
        return new MixingRecipeBuilder(itemResult, 1, chance, null, 0, HeatCondition.NONE);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count) {
        return new MixingRecipeBuilder(itemResult, count, 0.0f, null, 0, HeatCondition.NONE);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult) {
        return new MixingRecipeBuilder(itemResult, 1, 0.0f, null, 0, HeatCondition.NONE);
    }

    // Mixing Recipe Fluid
    public static MixingRecipeBuilder mixingRecipe(Fluid fluidResult, int amount, HeatCondition heat) {
        return new MixingRecipeBuilder(null, 0, 0.0f, fluidResult, amount, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(Fluid fluidResult, int amount) {
        return new MixingRecipeBuilder(null, 0, 0.0f, fluidResult, amount, HeatCondition.NONE);
    }

    // Mixing Recipe Fluid and Item
    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count, float chance, Fluid fluidResult, int amount, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, count, chance, fluidResult, amount, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, float chance, Fluid fluidResult, int amount, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, 1, chance, fluidResult, amount, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, Fluid fluidResult, int amount, HeatCondition heat) {
        return new MixingRecipeBuilder(itemResult, 1, 0.0f, fluidResult, amount, heat);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, int count, float chance, Fluid fluidResult, int amount) {
        return new MixingRecipeBuilder(itemResult, count, chance, fluidResult, amount, HeatCondition.NONE);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, float chance, Fluid fluidResult, int amount) {
        return new MixingRecipeBuilder(itemResult, 1, chance, fluidResult, amount, HeatCondition.NONE);
    }

    public static MixingRecipeBuilder mixingRecipe(ItemLike itemResult, Fluid fluidResult, int amount) {
        return new MixingRecipeBuilder(itemResult, 1, 0.0f, fluidResult, amount, HeatCondition.NONE);
    }

    // Add Result Item
    public MixingRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1, 0.0f, 1);
    }

    public MixingRecipeBuilder addResult(ItemLike result, int count) {
        return this.addResult(result, count, 0.0f, 1);
    }

    public MixingRecipeBuilder addResult(ItemLike result, float chance) {
        return this.addResult(result, 1, chance, 1);
    }

    public MixingRecipeBuilder addResult(ItemLike result, float chance, int count) {
        return this.addResult(result, count, chance, 1);
    }

    public MixingRecipeBuilder addResult(ItemLike result, int count, float chance, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.itemResults.add(new ItemStack(result, count));
            this.chance.add(chance);
        }

        return this;
    }

    // Add Result Fluid

    public MixingRecipeBuilder addResult(Fluid result, int amount) {
        return this.addResult(result, amount, 1);
    }

    public MixingRecipeBuilder addResult(Fluid result, int amount, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.fluidResults.add(new FluidStack(result, amount));
        }

        return this;
    }

    // Add Ingredient Item
    public MixingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public MixingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public MixingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public MixingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public MixingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.itemIngredients.add(ingredientIn);
        }

        return this;
    }

    // Add Ingredient Fluid
    public MixingRecipeBuilder addIngredient(TagKey<Fluid> tagIn, int amount) {
        return this.addIngredient(FluidIngredient.fromTag(tagIn, amount));
    }

    public MixingRecipeBuilder addIngredient(Fluid fluidIn, int amount) {
        return this.addIngredient(fluidIn, amount, 1);
    }

    public MixingRecipeBuilder addIngredient(Fluid fluidIn, int amount, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(FluidIngredient.fromFluid(fluidIn, amount));
        }

        return this;
    }

    public MixingRecipeBuilder addIngredient(FluidIngredient fluidStackIn) {
        return this.addIngredient(fluidStackIn, 1);
    }

    public MixingRecipeBuilder addIngredient(FluidIngredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.fluidIngredients.add(ingredientIn);
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
        return itemResults != null ? itemResults.get(0).getItem() : fluidResults != null ? fluidResults.get(0).getFluid().getBucket() : null;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new MixingRecipeBuilder.Result(pRecipeId, this.itemResults, this.chance,
                this.fluidResults, this.itemIngredients, this.fluidIngredients, this.heat));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> itemResults;
        private final List<Float> chance;
        private final List<FluidStack> fluidResults;
        private final List<Ingredient> itemIngredients;
        private final List<FluidIngredient> fluidIngredients;
        private final HeatCondition heat;

        public Result(ResourceLocation id, List<ItemStack> itemResults, List<Float> chance, List<FluidStack> fluidResults,
                      List<Ingredient> itemIngredients, List<FluidIngredient> fluidIngredients, HeatCondition heatCondition) {
            this.id = id;
            this.itemResults = itemResults;
            this.chance = chance;
            this.fluidResults = fluidResults;
            this.itemIngredients = itemIngredients;
            this.fluidIngredients = fluidIngredients;
            this.heat = heatCondition;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientArray = new JsonArray();
            if (this.itemIngredients != null){
                for (int i = 0; i < this.itemIngredients.size(); ++i) {
                    Ingredient ingredient = this.itemIngredients.get(i);
                    if (!this.itemIngredients.isEmpty()) {
                        ingredientArray.add(ingredient.toJson());
                    }
                }
            }
            if (this.fluidIngredients != null){
                for (int i = 0; i < this.fluidIngredients.size(); ++i) {
                    FluidIngredient ingredient = this.fluidIngredients.get(i);
                    if (!this.fluidIngredients.isEmpty()) {
                        ingredientArray.add(ingredient.serialize());
                    }
                }
            }
            json.add("ingredients", ingredientArray);

            JsonArray resultArray = new JsonArray();
            if (this.itemResults != null){
                for (int i = 0; i < this.itemResults.size(); ++i) {
                    ItemStack result = this.itemResults.get(i);
                    if (!this.itemResults.isEmpty()) {
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
            if (this.fluidResults != null){
                for (int i = 0; i < this.fluidResults.size(); ++i) {
                    FluidStack ingredient = this.fluidResults.get(i);
                    if (!this.fluidResults.isEmpty()) {
                        resultArray.add(FluidJSONUtil.toJson(ingredient));
                    }
                }
            }
            json.add("results", resultArray);

            if (this.heat == HeatCondition.HEATED){
                json.addProperty("heatRequirement", "heated");
            } else if (this.heat == HeatCondition.SUPERHEATED) {
                json.addProperty("heatRequirement", "superheated");
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MIXING.getSerializer();
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
