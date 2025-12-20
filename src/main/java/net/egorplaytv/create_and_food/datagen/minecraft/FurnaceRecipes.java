package net.egorplaytv.create_and_food.datagen.minecraft;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class FurnaceRecipes {
    public FurnaceRecipes(Consumer<FinishedRecipe> pConsumer) {
        furnace(pConsumer);
        blastFurnace(pConsumer);
        smoker(pConsumer);
    }

    private void smoker(Consumer<FinishedRecipe> pConsumer) {

    }

    private void blastFurnace(Consumer<FinishedRecipe> pConsumer) {

    }

    private void furnace(Consumer<FinishedRecipe> pConsumer){

    }


    private ResourceLocation getBlasting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/blasting/" + id);
    }
    private ResourceLocation getSmelting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/smelting/" + id);
    }
    private ResourceLocation getSmoking(String id){
        return new ResourceLocation(MOD_ID, "minecraft/smoking/" + id);
    }

    private String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
}
