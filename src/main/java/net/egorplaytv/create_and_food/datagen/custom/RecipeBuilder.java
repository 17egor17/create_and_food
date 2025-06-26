package net.egorplaytv.create_and_food.datagen.custom;

import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public interface RecipeBuilder {
    RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger);

    RecipeBuilder group(@Nullable String pGroupName);

    Item getResult();

    Fluid getOutputFluid();

    void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId);

    default void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.save(pFinishedRecipeConsumer, getResult() == null ? getDefaultRecipeId(this.getOutputFluid()) : getDefaultRecipeId(this.getResult()));
    }

    default void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pRecipeId) {
        ResourceLocation resourcelocation = getResult() == null ? getDefaultRecipeId(this.getOutputFluid()) : getDefaultRecipeId(this.getResult());
        ResourceLocation resourcelocation1 = new ResourceLocation(pRecipeId);
        if (resourcelocation1.equals(resourcelocation)) {
            throw new IllegalStateException("Recipe " + pRecipeId + " should remove its 'save' argument as it is equal to default one");
        } else {
            this.save(pFinishedRecipeConsumer, resourcelocation1);
        }
    }

    static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return Registry.ITEM.getKey(pItemLike.asItem());
    }
    static ResourceLocation getDefaultRecipeId(Fluid pFluid) {
        return Registry.FLUID.getKey(pFluid);
    }
}