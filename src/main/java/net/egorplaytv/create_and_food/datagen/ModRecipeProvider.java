package net.egorplaytv.create_and_food.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.datagen.custom.BlastingRecipeBuilder;
import net.egorplaytv.create_and_food.datagen.custom.FermentationRecipeBuilder;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.SCALES.get())
                .define('a', AllItems.ANDESITE_ALLOY.get())
                .define('i', ModTags.Items.IRON_SHEET)
                .define('p', AllItems.PRECISION_MECHANISM.get())
                .define('c', AllBlocks.ANDESITE_CASING.get())
                .pattern("iii")
                .pattern("apa")
                .pattern("aca")
                .unlockedBy("has_precision_mechanism", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.PRECISION_MECHANISM.get()).build()))
                .unlockedBy("has_iron_sheet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.IRON_SHEET.get()).build()))
                .unlockedBy("has_andesite_alloy", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.ANDESITE_ALLOY.get()).build()))
                .unlockedBy("has_andesite_casing", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllBlocks.ANDESITE_CASING.get()).build()))
                .save(pFinishedRecipeConsumer, getCraftingTable(getRecipeId(ModBlocks.SCALES.get())));
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
                .save(pFinishedRecipeConsumer, getCraftingTable(getRecipeId(ModBlocks.FRAMED_CALCITE.get())));

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
                .save(pFinishedRecipeConsumer, getCraftingTable(getRecipeId(ModBlocks.MARBLE_BLAST_FURNACE.get())));

        new BlastingRecipeBuilder(ModItems.NETHER_ALLOY.get(), 1, 200, 1000, 0.1F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(ModItems.ALLOY_SOULS.get())
                .unlockedBy("has_cinder_flour", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.CINDER_FLOUR.get()).build()))
                .unlockedBy("has_alloy_souls", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALLOY_SOULS.get()).build()))
                .save(pFinishedRecipeConsumer, getCAFBlasting(getRecipeId(ModItems.NETHER_ALLOY.get())));
    }

    private ResourceLocation getCraftingTable(String id){
        return new ResourceLocation(MOD_ID, "minecraft/crafting_table/" + id);
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
    private ResourceLocation getStoneCutting(String id){
        return new ResourceLocation(MOD_ID, "minecraft/stone_cutting/" + id);
    }
    public ResourceLocation getCAFBlasting(String id) {
        return new ResourceLocation(MOD_ID, "blasting/" + id);
    }
    public ResourceLocation getCAFBlastingDouble(String id) {
        return new ResourceLocation(MOD_ID, "blasting/double/" + id);
    }
    public ResourceLocation getCAFBlastingTriple(String id) {
        return new ResourceLocation(MOD_ID, "blasting/triple/" + id);
    }
    public ResourceLocation getFermentation(String id) {
        return new ResourceLocation(MOD_ID, "fermentation/" + id);
    }

    private String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
    private String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }
}