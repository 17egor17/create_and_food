package net.egorplaytv.create_and_food.datagen.minecraft;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class StoneCutterRecipes {
    private static void stoneCutting(Consumer<FinishedRecipe> pConsumer) {

    }

    private static ResourceLocation getStoneCutting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/stone_cutting/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        stoneCutting(pConsumer);
    }
}
