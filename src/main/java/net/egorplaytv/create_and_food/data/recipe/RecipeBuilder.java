package net.egorplaytv.create_and_food.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public interface RecipeBuilder {

    Item getResult();

    void build(Consumer<FinishedRecipe> consumer, ResourceLocation pRecipeId);

    default void build(Consumer<FinishedRecipe> consumer, String recipeName) {
        this.build(consumer, new ResourceLocation("create_and_food:/" + recipeName + "/" + getDefaultRecipeId(this.getResult())));
    }
    default void build(Consumer<FinishedRecipe> consumer, String recipeName, int number, String pRecipeId){
        ResourceLocation resourcelocation1 = new ResourceLocation(pRecipeId);
        ResourceLocation resourcelocation;
        if (number == 2) {
            resourcelocation = new ResourceLocation("create_and_food:/" + recipeName + "/double/" + getDefaultRecipeId(this.getResult()));
            if (resourcelocation1.equals(resourcelocation)) {
                throw new IllegalStateException("Recipe " + pRecipeId + " should remove its 'save' argument as it is equal to default one");
            } else {
                this.build(consumer, resourcelocation1);
            }
        } else if (number == 3){
            resourcelocation = new ResourceLocation("create_and_food:/" + recipeName + "/triple/" + getDefaultRecipeId(this.getResult()));
            if (resourcelocation1.equals(resourcelocation)) {
                throw new IllegalStateException("Recipe " + pRecipeId + " should remove its 'save' argument as it is equal to default one");
            } else {
                this.build(consumer, resourcelocation1);
            }
        }
    }
    default void build(Consumer<FinishedRecipe> consumer, String recipeName, String pRecipeId) {
        ResourceLocation resourcelocation = new ResourceLocation( "create_and_food:/" + recipeName + "/" + getDefaultRecipeId(this.getResult()));
        ResourceLocation resourcelocation1 = new ResourceLocation(pRecipeId);
        if (resourcelocation1.equals(resourcelocation)) {
            throw new IllegalStateException("Recipe " + pRecipeId + " should remove its 'save' argument as it is equal to default one");
        } else {
            this.build(consumer, resourcelocation1);
        }
    }


    static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return new ResourceLocation(ForgeRegistries.ITEMS.getKey(pItemLike.asItem()).getPath());
    }
}
