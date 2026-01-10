package net.egorplaytv.caf.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.fluid.FluidIngredient;
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

public class FillingRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Ingredient> itemIngredients = Lists.newArrayList();
    private final List<FluidIngredient> fluidIngredients = Lists.newArrayList();

    public FillingRecipeBuilder(ItemLike result) {
        this.results.add(new ItemStack(result));
    }

    public static FillingRecipeBuilder fillingRecipe(ItemLike result) {
        return new FillingRecipeBuilder(result);
    }

    public FillingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public FillingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public FillingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public FillingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public FillingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.itemIngredients.add(ingredientIn);
        }

        return this;
    }

    public FillingRecipeBuilder addIngredient(TagKey<Fluid> tagIn, int amount) {
        return this.addIngredient(FluidIngredient.fromTag(tagIn, amount));
    }

    public FillingRecipeBuilder addIngredient(Fluid fluidIn, int amount) {
        return this.addIngredient(fluidIn, amount, 1);
    }

    public FillingRecipeBuilder addIngredient(Fluid fluidIn, int amount, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(FluidIngredient.fromFluid(fluidIn, amount));
        }

        return this;
    }

    public FillingRecipeBuilder addIngredient(FluidIngredient fluidStackIn) {
        return this.addIngredient(fluidStackIn, 1);
    }

    public FillingRecipeBuilder addIngredient(FluidIngredient ingredientIn, int quantity) {
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
        return this.results.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new FillingRecipeBuilder.Result(pRecipeId, this.results, this.itemIngredients,
                this.fluidIngredients));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Ingredient> itemIngredients;
        private final List<FluidIngredient> fluidIngredients;

        public Result(ResourceLocation id, List<ItemStack> results, List<Ingredient> itemIngredients,
                      List<FluidIngredient> fluidIngredients) {
            this.id = id;
            this.results = results;
            this.itemIngredients = itemIngredients;
            this.fluidIngredients = fluidIngredients;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientArray = new JsonArray();
            for (int i = 0; i < this.itemIngredients.size(); ++i) {
                Ingredient ingredient = this.itemIngredients.get(i);
                if (!this.itemIngredients.isEmpty()) {
                    ingredientArray.add(ingredient.toJson());
                }
            }

            for (int i = 0; i < this.fluidIngredients.size(); ++i) {
                FluidIngredient ingredient = this.fluidIngredients.get(i);
                if (!this.fluidIngredients.isEmpty()) {
                    ingredientArray.add(ingredient.serialize());
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
            return AllRecipeTypes.FILLING.getSerializer();
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
