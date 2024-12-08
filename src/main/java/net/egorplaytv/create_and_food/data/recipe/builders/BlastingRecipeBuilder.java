package net.egorplaytv.create_and_food.data.recipe.builders;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.data.recipe.RecipeBuilder;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlastingRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final int blastingTime;
    private final int blastingDeg;
    private final float experience;

    private BlastingRecipeBuilder(ItemLike resultIn, int count, int blastingTime, int blastingDeg, float experience){
        this.result = resultIn.asItem();
        this.count = count;
        this.blastingTime = blastingTime;
        this.blastingDeg = blastingDeg;
        this.experience = experience;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    public static BlastingRecipeBuilder blastingRecipe(ItemLike mainResult, int count, int blastingTime, int blastingDeg, float experience){
        return new BlastingRecipeBuilder(mainResult, count, blastingTime, blastingDeg, experience);
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
    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation pRecipeId) {
        consumer.accept(new Result(pRecipeId, this.result, this.count, this.ingredients, this.blastingTime, this.blastingDeg, this.experience));
    }

    @Override
    public void build(Consumer<FinishedRecipe> consumer, String recipeName) {
        RecipeBuilder.super.build(consumer, "blasting");
    }

    @Override
    public void build(Consumer<FinishedRecipe> consumer, String recipeName, String pRecipeId) {
        RecipeBuilder.super.build(consumer, "blasting", pRecipeId);
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int blastingTime;
        private final int blastingDeg;
        private final float experience;

        public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn, int blastingTimeIn, int blastingDegIn, float experienceIn) {
            this.id = idIn;
            this.ingredients = ingredientsIn;
            this.result = resultIn;
            this.count = countIn;
            this.blastingTime = blastingTimeIn;
            this.blastingDeg = blastingDegIn;
            this.experience = experienceIn;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray arrayIngredients = new JsonArray();
            Iterator var3 = this.ingredients.iterator();

            while(var3.hasNext()) {
                Ingredient ingredient = (Ingredient)var3.next();
                arrayIngredients.add(ingredient.toJson());
            }

            json.add("ingredients", arrayIngredients);
            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                objectResult.addProperty("count", this.count);
            }

            json.add("result", objectResult);
            json.addProperty("time", this.blastingTime);
            json.addProperty("degree", this.blastingDeg);
            if (this.experience > 0.0F) {
                json.addProperty("experience", this.experience);
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return MarbleFurnaceRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
