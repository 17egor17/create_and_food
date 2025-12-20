package net.egorplaytv.create_and_food.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class BlastingRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final int blastingTime;
    private final int blastingDeg;
    private final float experience;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public BlastingRecipeBuilder(Item result, int count, int time, int degree, float experience) {
        this.result = result;
        this.count = count;
        this.blastingTime = time;
        this.blastingDeg = degree;
        this.experience = experience;
    }

    public BlastingRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public BlastingRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient((ItemLike)itemIn, 1);
    }

    public BlastingRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(new ItemLike[]{itemIn}));
        }

        return this;
    }

    public BlastingRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient((Ingredient)ingredientIn, 1);
    }

    public BlastingRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

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
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new BlastingRecipeBuilder.Result(pRecipeId, this.result, this.count, this.blastingTime,
                this.blastingDeg, this.experience, this.ingredients,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private int blastingTime;
        private int blastingDeg;
        private final float experience;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Item pResult, int pCount, int pTime, int pDeg, float pExperience,
                      List<Ingredient> ingredients, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.result = pResult;
            this.count = pCount;
            this.blastingTime = pTime;
            this.blastingDeg = pDeg;
            this.experience = pExperience;
            this.ingredients = ingredients;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
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
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", this.result.getRegistryName().toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }
            pJson.add("result", jsonobject);
            if (this.blastingTime >= 200) {
                pJson.addProperty("comment_time", "min 200 time");
                pJson.addProperty("time", this.blastingTime);
            } else {
                this.blastingTime = 200;
                pJson.addProperty("comment_time", "min 200 time");
                pJson.addProperty("time", this.blastingTime);
            }
            if (this.blastingDeg >= 100 && this.blastingDeg <= 5000) {
                pJson.addProperty("comment", "min 100 deg, max 5000 deg");
                pJson.addProperty("degree", this.blastingDeg);
            } else if (this.blastingDeg > 5000){
                this.blastingDeg = 5000;
                pJson.addProperty("comment", "min 100 deg, max 5000 deg");
                pJson.addProperty("degree", this.blastingDeg);
            } else {
                this.blastingDeg = 100;
                pJson.addProperty("comment", "min 100 deg, max 5000 deg");
                pJson.addProperty("degree", this.blastingDeg);
            }
            if (this.experience > 0.0F) {
                pJson.addProperty("experience", this.experience);
            }

        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(CreateAndFood.MOD_ID,
                    this.result.getRegistryName().getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return MarbleFurnaceRecipe.Serializer.INSTANCE;
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
