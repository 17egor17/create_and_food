package net.egorplaytv.create_and_food.datagen.minecraft;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class StoneCutterRecipes {
    public StoneCutterRecipes(Consumer<FinishedRecipe> pConsumer) {
        stoneCutting(pConsumer);
    }

    private void stoneCutting(Consumer<FinishedRecipe> pConsumer) {

    }

    private ResourceLocation getStoneCutting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/stone_cutting/" + id);
    }

    private String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
}
