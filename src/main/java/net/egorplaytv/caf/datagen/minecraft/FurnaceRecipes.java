package net.egorplaytv.caf.datagen.minecraft;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class FurnaceRecipes {
    private static void smoker(Consumer<FinishedRecipe> pConsumer) {

    }

    private static void blastFurnace(Consumer<FinishedRecipe> pConsumer) {

    }

    private static void furnace(Consumer<FinishedRecipe> pConsumer){

    }


    private static ResourceLocation getBlasting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/blasting/" + id);
    }
    private static ResourceLocation getSmelting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/smelting/" + id);
    }
    private static ResourceLocation getSmoking(String id){
        return new ResourceLocation(MOD_ID, "minecraft/smoking/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        furnace(pConsumer);
        smoker(pConsumer);
        blastFurnace(pConsumer);
    }
}
