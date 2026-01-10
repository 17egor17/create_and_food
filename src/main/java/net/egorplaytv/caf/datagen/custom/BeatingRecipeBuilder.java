package net.egorplaytv.caf.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.egorplaytv.caf.recipe.AllRecipeTypes;
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

public class BeatingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> ItemResults = Lists.newArrayList();
    private final List<Float> chance = Lists.newArrayList();
    private final List<FluidStack> FluidResults = Lists.newArrayList();
    private final List<Ingredient> ItemIngredients = Lists.newArrayList();
    private final List<FluidIngredient> FluidIngredients = Lists.newArrayList();
    private final HeatCondition heat;

    public BeatingRecipeBuilder(ItemLike itemResult, int count, float itemChance, Fluid fluidResult,
                                   int amount, HeatCondition heat){
        if (itemResult != null) {
            this.ItemResults.add(new ItemStack(itemResult, count));
        }
        this.chance.add(itemChance);
        if (fluidResult != null) {
            this.FluidResults.add(new FluidStack(fluidResult, amount));
        }
        this.heat = heat;
    }


    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, count, 0.0f, null, 0, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, 1,0.0f, null, 0, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count){
        return new BeatingRecipeBuilder(itemResult, count, 0.0f, null, 0, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult){
        return new BeatingRecipeBuilder(itemResult, 1, 0.0f, null, 0, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(Fluid fluidResult, int amount, HeatCondition heat){
        return new BeatingRecipeBuilder(null, 0, 0.0f, fluidResult, amount, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(Fluid fluidResult, int amount){
        return new BeatingRecipeBuilder(null, 0, 0.0f, fluidResult, amount, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, Fluid fluidResult, int amount, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, count, 0.0f, fluidResult, amount, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, Fluid fluidResult, int amount, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, 1, 0.0f, fluidResult, amount, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, Fluid fluidResult, int amount){
        return new BeatingRecipeBuilder(itemResult, count, 0.0f, fluidResult, amount, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, Fluid fluidResult, int amount){
        return new BeatingRecipeBuilder(itemResult, 1, 0.0f, fluidResult, amount, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, float itemChance, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, count, itemChance, null, 0, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, float itemChance, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, 1, itemChance, null, 0, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, float itemChance){
        return new BeatingRecipeBuilder(itemResult, count, itemChance, null, 0, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, float itemChance){
        return new BeatingRecipeBuilder(itemResult, 1, itemChance, null, 0, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, float itemChance, Fluid fluidResult, int amount, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, count, itemChance, fluidResult, amount, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, float itemChance, Fluid fluidResult, int amount, HeatCondition heat){
        return new BeatingRecipeBuilder(itemResult, 1, itemChance, fluidResult, amount, heat);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, int count, float itemChance, Fluid fluidResult, int amount){
        return new BeatingRecipeBuilder(itemResult, count, itemChance, fluidResult, amount, HeatCondition.NONE);
    }

    public static BeatingRecipeBuilder beatingRecipe(ItemLike itemResult, float itemChance, Fluid fluidResult, int amount){
        return new BeatingRecipeBuilder(itemResult, 1, itemChance, fluidResult, amount, HeatCondition.NONE);
    }

    public BeatingRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1, 0.0f, 1);
    }

    public BeatingRecipeBuilder addResult(ItemLike result, int count) {
        return this.addResult(result, count, 0.0f, 1);
    }

    public BeatingRecipeBuilder addResult(ItemLike result, float chance) {
        return this.addResult(result, 1, chance, 1);
    }

    public BeatingRecipeBuilder addResult(ItemLike result, float chance, int count) {
        return this.addResult(result, count, chance, 1);
    }

    public BeatingRecipeBuilder addResult(ItemLike result, int count, float chance, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ItemResults.add(new ItemStack(result, count));
            this.chance.add(chance);
        }

        return this;
    }

    public BeatingRecipeBuilder addResult(Fluid result, int amount) {
        return this.addResult(result, amount, 1);
    }

    public BeatingRecipeBuilder addResult(Fluid result, int amount, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.FluidResults.add(new FluidStack(result, amount));
        }

        return this;
    }


    public BeatingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public BeatingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public BeatingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public BeatingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public BeatingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ItemIngredients.add(ingredientIn);
        }

        return this;
    }


    public BeatingRecipeBuilder addIngredient(TagKey<Fluid> tagIn, int amount) {
        return this.addIngredient(FluidIngredient.fromTag(tagIn, amount));
    }

    public BeatingRecipeBuilder addIngredient(Fluid fluidIn, int amount) {
        return this.addIngredient(fluidIn, amount, 1);
    }

    public BeatingRecipeBuilder addIngredient(Fluid fluidIn, int amount, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(FluidIngredient.fromFluid(fluidIn, amount));
        }

        return this;
    }

    public BeatingRecipeBuilder addIngredient(FluidIngredient fluidStackIn) {
        return this.addIngredient(fluidStackIn, 1);
    }

    public BeatingRecipeBuilder addIngredient(FluidIngredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.FluidIngredients.add(ingredientIn);
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
        return ItemResults != null ? ItemResults.get(0).getItem() : FluidResults != null ? FluidResults.get(0).getFluid().getBucket() : null;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new BeatingRecipeBuilder.Result(pRecipeId, this.ItemResults, this.chance,
                this.FluidResults, this.ItemIngredients, this.FluidIngredients, this.heat));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> ItemResults;
        private final List<Float> chance;
        private final List<FluidStack> FluidResults;
        private final List<Ingredient> ItemIngredients;
        private final List<FluidIngredient> FluidIngredients;
        private final HeatCondition heat;

        public Result(ResourceLocation id, List<ItemStack> itemResults, List<Float> itemChance, List<FluidStack> fluidResults,
                      List<Ingredient> itemIngredients, List<FluidIngredient> fluidIngredients, HeatCondition heatCondition) {
            this.id = id;
            this.ItemResults = itemResults;
            this.chance = itemChance;
            this.FluidResults = fluidResults;
            this.ItemIngredients = itemIngredients;
            this.FluidIngredients = fluidIngredients;
            this.heat = heatCondition;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientArray = new JsonArray();
            if (this.ItemIngredients != null){
                for (int i = 0; i < this.ItemIngredients.size(); ++i) {
                    Ingredient ingredient = this.ItemIngredients.get(i);
                    if (!this.ItemIngredients.isEmpty()) {
                        ingredientArray.add(ingredient.toJson());
                    }
                }
            }
            if (this.FluidIngredients != null){
                for (int i = 0; i < this.FluidIngredients.size(); ++i) {
                    FluidIngredient ingredient = this.FluidIngredients.get(i);
                    if (!this.FluidIngredients.isEmpty()) {
                        ingredientArray.add(ingredient.serialize());
                    }
                }
            }
            json.add("ingredients", ingredientArray);

            JsonArray resultArray = new JsonArray();
            if (this.ItemResults != null){
                for (int i = 0; i < this.ItemResults.size(); ++i) {
                    ItemStack result = this.ItemResults.get(i);
                    if (!this.ItemResults.isEmpty()) {
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
            if (this.FluidResults != null){
                for (int i = 0; i < this.FluidResults.size(); ++i) {
                    FluidStack ingredient = this.FluidResults.get(i);
                    if (!this.FluidResults.isEmpty()) {
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
            return AllRecipeTypes.BEATING.getSerializer();
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
