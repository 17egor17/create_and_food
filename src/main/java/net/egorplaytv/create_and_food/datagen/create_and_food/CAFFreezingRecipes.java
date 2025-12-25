package net.egorplaytv.create_and_food.datagen.create_and_food;

import net.egorplaytv.create_and_food.datagen.custom.BeatingRecipeBuilder;
import net.egorplaytv.create_and_food.fluid.CAFFluids;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.simibubi.create.content.processing.recipe.HeatCondition.HEATED;
import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class CAFFreezingRecipes {
    private static void frizzing(Consumer<FinishedRecipe> pConsumer) {

    }

    public static ResourceLocation getFrizzing(String id) {
        return new ResourceLocation(MOD_ID, "frizzing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer) {
        frizzing(pConsumer);
    }
}
