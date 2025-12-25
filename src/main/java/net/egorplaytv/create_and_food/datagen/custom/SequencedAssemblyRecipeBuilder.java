package net.egorplaytv.create_and_food.datagen.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeSerializer;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class SequencedAssemblyRecipeBuilder implements RecipeBuilder {
    private final List<ItemStack> results = Lists.newArrayList();
    private final List<Float> chance = Lists.newArrayList();
    private final Ingredient ingredient;
    private final ItemStack transitionalItem;
    private final List<SequencedRecipe<?>> sequence = Lists.newArrayList();
    private final int loops;

    public SequencedAssemblyRecipeBuilder(ItemLike result, int count, float chance,
                                          ItemLike ingredient, ItemLike transitionalItem, int loops) {
        this.results.add(new ItemStack(result, count));
        this.chance.add(chance);
        this.ingredient = Ingredient.of(ingredient);
        this.transitionalItem = new ItemStack(transitionalItem);
        this.loops = loops;
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, int count, float chance,
                                                                         ItemLike ingredient, ItemLike transitionalItem, int loops) {
        return new SequencedAssemblyRecipeBuilder(result, count, chance, ingredient, transitionalItem, loops);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, int count, float chance,
                                                                         ItemLike ingredient, ItemLike transitionalItem) {
        return new SequencedAssemblyRecipeBuilder(result, count, chance, ingredient, transitionalItem, 1);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, int count,
                                                                         ItemLike ingredient, ItemLike transitionalItem) {
        return new SequencedAssemblyRecipeBuilder(result, count, 0.0f, ingredient, transitionalItem, 1);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, float chance,
                                                                         ItemLike ingredient, ItemLike transitionalItem) {
        return new SequencedAssemblyRecipeBuilder(result, 1, chance, ingredient, transitionalItem, 1);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result,
                                                                         ItemLike ingredient, ItemLike transitionalItem) {
        return new SequencedAssemblyRecipeBuilder(result, 1, 0.0f, ingredient, transitionalItem, 1);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, int count,
                                                                         ItemLike ingredient, ItemLike transitionalItem, int loops) {
        return new SequencedAssemblyRecipeBuilder(result, count, 0.0f, ingredient, transitionalItem, loops);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result, float chance,
                                                                         ItemLike ingredient, ItemLike transitionalItem, int loops) {
        return new SequencedAssemblyRecipeBuilder(result, 1, chance, ingredient, transitionalItem, loops);
    }

    public static SequencedAssemblyRecipeBuilder sequencedAssemblyRecipe(ItemLike result,
                                                                         ItemLike ingredient, ItemLike transitionalItem, int loops) {
        return new SequencedAssemblyRecipeBuilder(result, 1, 0.0f, ingredient, transitionalItem, loops);
    }


    public <T extends ProcessingRecipe<?>> SequencedAssemblyRecipeBuilder addStep(ProcessingRecipeBuilder.ProcessingRecipeFactory<T> factory,
                                                                                  UnaryOperator<ProcessingRecipeBuilder<T>> builder) {
        ProcessingRecipeBuilder<T> recipeBuilder = new ProcessingRecipeBuilder<>(factory, new ResourceLocation("dummy"));

        sequence.add(new SequencedRecipe<>(builder.apply(recipeBuilder.require(transitionalItem.getItem())
                        .output(transitionalItem.getItem()))
                .build()));

        return this;
    }

    public SequencedAssemblyRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1, 0.0f, 1);
    }

    public SequencedAssemblyRecipeBuilder addResult(ItemLike result, int count) {
        return this.addResult(result, count, 0.0f, 1);
    }

    public SequencedAssemblyRecipeBuilder addResult(ItemLike result, float chance) {
        return this.addResult(result, 1, chance, 1);
    }

    public SequencedAssemblyRecipeBuilder addResult(ItemLike result, float chance, int count) {
        return this.addResult(result, count, chance, 1);
    }

    public SequencedAssemblyRecipeBuilder addResult(ItemLike result, int count, float chance, int quantity) {
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
        return null;
    }

    @Override
    public Item getResult() {
        return this.results.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new SequencedAssemblyRecipeBuilder.Result(pRecipeId, this.results, this.chance,
                this.ingredient, this.transitionalItem, this.sequence, this.loops));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ItemStack> results;
        private final List<Float> chance;
        private final Ingredient ingredient;
        private final ItemStack transitionalItem;
        private final List<SequencedRecipe<?>> sequence;
        private final int loops;

        public Result(ResourceLocation pRecipeId, List<ItemStack> results,
                      List<Float> chance, Ingredient ingredient, ItemStack transitionalItem, List<SequencedRecipe<?>> sequence, int loops) {
            this.id = pRecipeId;
            this.results = results;
            this.chance = chance;
            this.ingredient = ingredient;
            this.transitionalItem = transitionalItem;
            this.sequence = sequence;
            this.loops = loops;
        }


        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientArray = new JsonArray();
            ingredientArray.add(ingredient.toJson());
            json.add("ingredient", ingredientArray);
            JsonObject transitionalItemArray = new JsonObject();
            transitionalItemArray.addProperty("item", transitionalItem.getItem().getRegistryName().toString());
            json.add("transitionalItem", transitionalItemArray);

            JsonArray sequenceArray = new JsonArray();
            for (int i = 0; i < this.sequence.size(); ++i) {
                SequencedRecipe<?> recipe = this.sequence.get(i);
                if (!this.sequence.isEmpty()){
                    sequenceArray.add(recipe.toJson());
                }
            }
            json.add("sequence", sequenceArray);

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
            json.addProperty("loops", loops);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.SEQUENCED_ASSEMBLY.getSerializer();
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
