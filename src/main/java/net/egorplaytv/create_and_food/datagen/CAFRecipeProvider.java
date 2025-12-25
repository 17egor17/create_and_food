package net.egorplaytv.create_and_food.datagen;

import net.egorplaytv.create_and_food.datagen.create.*;
import net.egorplaytv.create_and_food.datagen.create_and_food.*;
import net.egorplaytv.create_and_food.datagen.farmersdelight.CuttingRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.FurnaceRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.CraftingTableRecipes;
import net.egorplaytv.create_and_food.datagen.minecraft.StoneCutterRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class CAFRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public CAFRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pConsumer) {
        // Minecraft Recipes
        CraftingTableRecipes.register(pConsumer);
        FurnaceRecipes.register(pConsumer);
        StoneCutterRecipes.register(pConsumer);

        // Create
        CrushingRecipes.register(pConsumer);
        FillingRecipes.register(pConsumer);
        HauntingRecipes.register(pConsumer);
        ItemApplicationRecipes.register(pConsumer);
        MillingRecipes.register(pConsumer);
        MixerRecipes.register(pConsumer);
        PressRecipes.register(pConsumer);
        SandpaperPolishingRecipes.register(pConsumer);
        SequencedAssemblyRecipes.register(pConsumer);
        SplashingRecipes.register(pConsumer);

        // Create And Food Recipes
        CAFChoppingRecipes.register(pConsumer);
        CAFBeatingRecipes.register(pConsumer);
        CAFBlastingRecipes.register(pConsumer);
        CAFFermentationRecipes.register(pConsumer);
        CAFPolishingRecipes.register(pConsumer);

        // Farmer's Delight
        CuttingRecipes.register(pConsumer);
    }
}