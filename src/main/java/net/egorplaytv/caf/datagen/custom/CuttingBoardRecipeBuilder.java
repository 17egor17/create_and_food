package net.egorplaytv.caf.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CuttingBoardRecipeBuilder implements RecipeBuilder {
    private final List<ChanceResult> results = new ArrayList(4);
    private final Ingredient ingredient;
    private final Ingredient tool;
    private String soundEventID;

    private CuttingBoardRecipeBuilder(Ingredient ingredient, Ingredient tool, ItemLike mainResult, int count, float chance) {
        this.results.add(new ChanceResult(new ItemStack(mainResult.asItem(), count), chance));
        this.ingredient = ingredient;
        this.tool = tool;
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, ItemLike tool, ItemLike mainResult, int count) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, ItemLike tool, ItemLike mainResult, int count, int chance) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, (float)chance);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, ItemLike tool, ItemLike mainResult) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, 1, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, TagKey<Item> tool, ItemLike mainResult, int count) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, TagKey<Item> tool, ItemLike mainResult, int count, int chance) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, (float)chance);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, TagKey<Item> tool, ItemLike mainResult) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, 1, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, TagKey<Item> tool, ItemLike mainResult, int count) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, TagKey<Item> tool, ItemLike mainResult, int count, int chance) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, (float)chance);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(ItemLike ingredient, TagKey<Item> tool, ItemLike mainResult) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, 1, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, ItemLike tool, ItemLike mainResult, int count) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, ItemLike tool, ItemLike mainResult, int count, int chance) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, count, (float)chance);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(TagKey<Item> ingredient, ItemLike tool, ItemLike mainResult) {
        return new CuttingBoardRecipeBuilder(Ingredient.of(ingredient), Ingredient.of(tool), mainResult, 1, 1.0F);
    }

    public CuttingBoardRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1);
    }

    public CuttingBoardRecipeBuilder addResult(ItemLike result, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), 1.0F));
        return this;
    }

    public CuttingBoardRecipeBuilder addResultWithChance(ItemLike result, float chance) {
        return this.addResultWithChance(result, chance, 1);
    }

    public CuttingBoardRecipeBuilder addResultWithChance(ItemLike result, float chance, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), chance));
        return this;
    }

    public CuttingBoardRecipeBuilder addSound(String soundEventID) {
        this.soundEventID = soundEventID;
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
        return results.get(0).getStack().getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new CuttingBoardRecipeBuilder.Result(pRecipeId, this.results, this.ingredient,
                this.tool, this.soundEventID == null ? "" : this.soundEventID));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<ChanceResult> results;
        private final Ingredient ingredient;
        private final Ingredient tool;
        private final String soundEventID;

        public Result(ResourceLocation id, List<ChanceResult> results, Ingredient ingredient, Ingredient tool, String soundEventIDIn){
            this.id = id;
            this.results = results;
            this.ingredient = ingredient;
            this.tool = tool;
            this.soundEventID = soundEventIDIn;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray arrayIngredients = new JsonArray();
            arrayIngredients.add(this.ingredient.toJson());
            json.add("ingredients", arrayIngredients);
            json.add("tool", this.tool.toJson());
            JsonArray arrayResults = new JsonArray();

            for(ChanceResult result : this.results) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getStack().getItem()).toString());
                if (result.getStack().getCount() > 1) {
                    jsonobject.addProperty("count", result.getStack().getCount());
                }

                if (result.getChance() < 1.0F) {
                    jsonobject.addProperty("chance", result.getChance());
                }

                arrayResults.add(jsonobject);
            }

            json.add("result", arrayResults);
            if (!this.soundEventID.isEmpty()) {
                json.addProperty("sound", this.soundEventID);
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.CUTTING.get();
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
