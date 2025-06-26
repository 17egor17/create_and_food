package net.egorplaytv.create_and_food.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.recipe.FermentationBarrelRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class FermentationRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Fluid inputFluid;
    private final int amountIn;
    private final Item result;
    private final int count;
    private final Fluid outputFluid;
    private final int amountOut;
    private final Item tool;
    private int time;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private FermentationRecipeBuilder(Fluid inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut, Item tool) {
        this.inputFluid = inputFluid;
        this.amountIn = amountIn;
        this.result = result != null ? result : null;
        this.count = result != null ? count : 0;
        this.outputFluid = outputFluid != null ? outputFluid : null;
        this.amountOut = outputFluid != null ? amountOut : 0;
        this.tool = tool != null ? tool : null;
    }

    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, count, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, 1, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Fluid outputFluid, int amountOut, Item tool){
        return new FermentationRecipeBuilder(inputFluid, amountIn, null, 0, outputFluid, amountOut, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Item tool){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, count, null, 0, tool);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, count, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, 1, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Fluid outputFluid, int amountOut){
        return new FermentationRecipeBuilder(inputFluid, amountIn, null, 0, outputFluid, amountOut, null);
    }
    public static FermentationRecipeBuilder fermentationRecipe(Fluid inputFluid, int amountIn, Item result, int count){
        return new FermentationRecipeBuilder(inputFluid, amountIn, result, count, null, 0, null);
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
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
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
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        if (this.result == null){
            pFinishedRecipeConsumer.accept(new FermentationRecipeBuilder.Result(pRecipeId, null, this.count, this.inputFluid, this.amountIn, this.outputFluid, this.amountOut, this.time, this.ingredients, this.tool,
                    this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                    this.outputFluid.getBucket().getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
        } else {
            pFinishedRecipeConsumer.accept(new FermentationRecipeBuilder.Result(pRecipeId, this.result, this.count, this.inputFluid, this.amountIn, this.outputFluid, this.amountOut, this.time, this.ingredients, this.tool,
                    this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                    this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
        }
    }

    private class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Fluid inputFluid;
        private final int amountIn;
        private final Item result;
        private final int count;
        private final Fluid outputFluid;
        private final int amountOut;
        private final Item tool;
        private int time;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item result, int count, Fluid inputFluid, int amountIn, Fluid outputFluid,
                      int amountOut, int time, List<Ingredient> ingredients, Item tool, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result != null ? result : null;
            this.count = count;
            this.inputFluid = inputFluid;
            this.amountIn = amountIn;
            this.outputFluid = outputFluid != null ? outputFluid : null;
            this.amountOut = amountOut;
            this.time = time != 0 ? time : 1000;
            this.tool = tool != null ? tool : null;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();

            for (int i = 0; i < ingredients.size(); ++i) {
                Ingredient ingredient = ingredients.get(i);
                if (!ingredients.isEmpty()) {
                    jsonarray.add(ingredient.toJson());
                }
            }
            pJson.add("ingredients", jsonarray);
            JsonObject json = new JsonObject();
            json.addProperty("fluid", this.inputFluid.getRegistryName().toString());
            json.addProperty("amount", this.amountIn);
            pJson.add("fluid", json);
            if (this.result != null) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("item", this.result.getRegistryName().toString());
                if (this.count > 1) {
                    jsonobject.addProperty("count", this.count);
                }
                pJson.add("resultItem", jsonobject);
            }
            if (this.outputFluid != null) {
                JsonObject object = new JsonObject();
                object.addProperty("fluid", this.outputFluid.getRegistryName().toString());
                object.addProperty("amount", this.amountOut);
                pJson.add("resultFluid", object);
            }
            if (tool != null) {
                JsonObject toolItem = new JsonObject();
                toolItem.addProperty("item", this.tool.getRegistryName().toString());
                pJson.add("tool", toolItem);
            }

            pJson.addProperty("comment", "min 20, max 120000");
            if (this.time >= 20 && this.time <= 12000) {
                pJson.addProperty("time", this.time);
            } else if (this.time > 12000) {
                this.time = 12000;
                pJson.addProperty("time", this.time);
            } else {
                this.time = 20;
                pJson.addProperty("time", this.time);
            }

        }

        @Override
        public ResourceLocation getId() {
            if (this.result != null && this.outputFluid != null){
                return id;
            } else if (this.result != null && this.outputFluid == null){
                return new ResourceLocation(CreateAndFood.MOD_ID, this.result.getRegistryName().getPath());
            } else {
                return new ResourceLocation(this.outputFluid.getRegistryName().toString());
            }
        }

        @Override
        public RecipeSerializer<?> getType() {
            return FermentationBarrelRecipe.Serializer.INSTANCE;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
