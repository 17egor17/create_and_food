package net.egorplaytv.create_and_food.datagen;

import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.datagen.custom.BlastingRecipeBuilder;
import net.egorplaytv.create_and_food.datagen.custom.FermentationRecipeBuilder;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.FRAMED_CALCITE.get(), 4)
                .define('/', Items.STICK)
                .define('c', Blocks.CALCITE)
                .pattern("/c/")
                .pattern("c/c")
                .pattern("/c/")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .unlockedBy("has_calcite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.CALCITE).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.MARBLE_BLAST_FURNACE.get())
                .define('B', ModBlocks.FIRECLAY_BRICKS.get())
                .define('M', ModItems.MARBLE_BLACK_GALAXY_BRICK.get())
                .define('F', Blocks.FURNACE)
                .pattern("MBM")
                .pattern("BFB")
                .pattern("MBM")
                .unlockedBy("has_marble", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MARBLE_BLACK_GALAXY_BRICK.get()).build()))
                .unlockedBy("has_fireclay", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.FIRECLAY_BRICKS.get()).build()))
                .unlockedBy("has_furnace", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.FURNACE).build()))
                .save(pFinishedRecipeConsumer);

        new BlastingRecipeBuilder(ModItems.NETHER_ALLOY.get(), 1, 200, 1000, 0.1F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(ModItems.ALLOY_SOULS.get())
                .unlockedBy("has_cinder_flour", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.CINDER_FLOUR.get()).build()))
                .unlockedBy("has_alloy_souls", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALLOY_SOULS.get()).build()))
                .save(pFinishedRecipeConsumer);

        FermentationRecipeBuilder.fermentationRecipe(ModFluids.RED_GRAPE_JUICE.get(), 250, ModFluids.COCOA_OIL_FLUID.get(), 250)
                .addIngredient(ModItems.ALLOY_SOULS.get()).addIngredient(ModItems.COPPER_COIN.get()).addTimeInSeconds(10)
                .unlockedBy("juice_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RED_GRAPE_JUICE_BUCKET.get()).build()))
                .unlockedBy("alloy_souls", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALLOY_SOULS.get()).build()))
                .unlockedBy("copper_coin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.COPPER_COIN.get()).build()))
                .save(pFinishedRecipeConsumer);
    }
}