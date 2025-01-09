package net.egorplaytv.create_and_food.recipe.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class MarbleFurnaceRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final int time;
    private final int degree;
    private final float experience;

    private MarbleFurnaceRecipeBuilder(ItemLike resultIn, int count, int time, int degree, float experience){
        this.result = resultIn.asItem();
        this.count = count;
        this.time = time;
        this.degree = degree;
        this.experience = experience;
    }

    public static MarbleFurnaceRecipeBuilder blastingRecipe(ItemLike mainResult, int time, int degee, float experience) {
        return new MarbleFurnaceRecipeBuilder(mainResult, 1, time, degee, experience);
    }

    public static MarbleFurnaceRecipeBuilder blastingRecipe(ItemLike mainResult, int count, int time, int degee, float experience) {
        return new MarbleFurnaceRecipeBuilder(mainResult, count, time, degee, experience);
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return null;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    public MarbleFurnaceRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public MarbleFurnaceRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient((ItemLike)itemIn, 1);
    }

    public MarbleFurnaceRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(new ItemLike[]{itemIn}));
        }

        return this;
    }

    public MarbleFurnaceRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient((Ingredient)ingredientIn, 1);
    }

    public MarbleFurnaceRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    public void save(Consumer<FinishedRecipe> consumerIn) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.result);
        this.save(consumerIn, "create_and_food:blasting/" + location.getPath());
    }

    public void save(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Blasting Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(consumerIn, new ResourceLocation(save));
        }
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        consumerIn.accept(new MarbleFurnaceRecipeBuilder.Result(id, this.result, this.count, this.ingredients, this.time, this.degree, this.experience));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int time;
        private final int degree;
        private final float experience;

        public Result(ResourceLocation id, Item result, int count, List<Ingredient> ingredients, int time, int degree, float experience) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result;
            this.count = count;
            this.time = time;
            this.degree = degree;
            this.experience = experience;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray arrayIngredients = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                arrayIngredients.add(ingredient.toJson());
            }

            json.add("ingredients", arrayIngredients);
            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                objectResult.addProperty("count", this.count);
            }
            json.add("result", objectResult);

            json.addProperty("time", this.time);

            json.addProperty("degree", this.degree);

            if (this.experience > 0.0F) {
                json.addProperty("experience", this.experience);
            }
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return MarbleFurnaceRecipe.SERIALIZER;
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
