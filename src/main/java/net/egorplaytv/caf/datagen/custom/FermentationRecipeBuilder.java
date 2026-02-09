package net.egorplaytv.caf.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.egorplaytv.caf.recipe.FermentationBarrelRecipe;
import net.egorplaytv.caf.recipe.fluids.FluidStack;
import net.egorplaytv.caf.util.FluidJSONUtil;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class FermentationRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final FluidIngredient inputFluid;
    private final Item result;
    private final int count;
    private final Fluid outputFluid;
    private final int amountOut;
    private final Item tool;
    private int time;

    private FermentationRecipeBuilder(FluidIngredient inputFluid, Item result, int count, Fluid outputFluid, int amountOut, Item tool) {
        this.inputFluid = inputFluid;
        this.result = result != null ? result : null;
        this.count = result != null ? count : 0;
        this.outputFluid = outputFluid != null ? outputFluid : null;
        this.amountOut = outputFluid != null ? amountOut : 0;
        this.tool = tool != null ? tool : null;
    }

    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, count, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, 1, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), null, 0, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, count, null, 0, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, count, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, 1, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), null, 0, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count){
        return new FermentationRecipeBuilder(FluidIngredient.fromFluid(inputFluid, amountIn), result, count, null, 0, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, count, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, 1, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), null, 0, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, int count, Item tool){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, count, null, 0, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, count, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, 1, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), null, 0, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(TagKey<Fluid> inputFluid, int amountIn, Item result, int count){
        return new FermentationRecipeBuilder(FluidIngredient.fromTag(inputFluid, amountIn), result, count, null, 0, null);
    }


    public FermentationRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public FermentationRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    public FermentationRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    public FermentationRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    public FermentationRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    public FermentationRecipeBuilder addTimeInSeconds(int seconds){
        this.time = seconds * 20;

        return this;
    }

    public FermentationRecipeBuilder addTimeInMinutes(int minutes){
        this.time = minutes * 1200;

        return this;
    }

    public FermentationRecipeBuilder addTimeInDays(int days) {
        this.time = days * 24000;

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
        return result;
    }

    @Override
    public Fluid getOutputFluid() {
        return outputFluid;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
            pFinishedRecipeConsumer.accept(new FermentationRecipeBuilder.Result(pRecipeId, null, this.count, this.inputFluid,
                    this.outputFluid, this.amountOut, this.time, this.ingredients, this.tool));
    }

    private class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private FluidIngredient inputFluid;
        private final Item result;
        private final int count;
        private final FluidStack outputFluid;
        private final Item tool;
        private int time;

        public Result(ResourceLocation id, Item result, int count, FluidIngredient inputFluid, Fluid outputFluid,
                      int amountOut, int time, List<Ingredient> ingredients, Item tool) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result != null ? result : null;
            this.count = count;
            this.inputFluid = inputFluid;
            this.outputFluid = outputFluid != null ? new FluidStack(outputFluid, amountOut) : null;
            this.time = time != 0 ? time : 1000;
            this.tool = tool != null ? tool : null;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray array = new JsonArray();
            if (this.inputFluid != null && this.ingredients != null) {
                array.add(inputFluid.serialize());

                for (int i = 0; i < ingredients.size(); ++i) {
                    Ingredient ingredient = ingredients.get(i);
                    if (!ingredients.isEmpty()) {
                        array.add(ingredient.toJson());
                    }
                }
            } else if (this.inputFluid != null) {
                array.add(inputFluid.serialize());
            } else {
                for (int i = 0; i < ingredients.size(); ++i) {
                    Ingredient ingredient = ingredients.get(i);
                    if (!ingredients.isEmpty()) {
                        array.add(ingredient.toJson());
                    }
                }
            }

            json.add("ingredients", array);

            JsonArray jsonArray = new JsonArray();
            if (this.outputFluid != null && this.result != null) {
                jsonArray.add(FluidJSONUtil.toJson(this.outputFluid));

                jsonArray.getAsJsonObject().addProperty("item", this.result.getRegistryName().toString());
                if (this.count > 1) {
                    jsonArray.getAsJsonObject().addProperty("count", this.count);
                }
            } else if (this.outputFluid != null){
                jsonArray.add(FluidJSONUtil.toJson(this.outputFluid));
            } else {
                jsonArray.getAsJsonObject().addProperty("item", this.result.getRegistryName().toString());
                if (this.count > 1) {
                    jsonArray.getAsJsonObject().addProperty("count", this.count);
                }
            }
            json.add("results", jsonArray);

            if (tool != null) {
                JsonObject toolItem = new JsonObject();
                toolItem.addProperty("item", this.tool.getRegistryName().toString());
                json.add("tool", toolItem);
            }

            json.addProperty("comment", "min 20, max 120000");
            if (this.time >= 20 && this.time <= 12000) {
                json.addProperty("time", this.time);
            } else if (this.time > 12000) {
                this.time = 12000;
                json.addProperty("time", this.time);
            } else {
                this.time = 20;
                json.addProperty("time", this.time);
            }

        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return FermentationBarrelRecipe.Serializer.INSTANCE;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
