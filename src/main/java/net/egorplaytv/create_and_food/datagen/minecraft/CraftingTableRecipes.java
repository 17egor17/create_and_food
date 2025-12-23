package net.egorplaytv.create_and_food.datagen.minecraft;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.util.CAFTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;
import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class CraftingTableRecipes {
    public CraftingTableRecipes(Consumer<FinishedRecipe> pConsumer) {
        shapedRecipe(pConsumer);
        shapelessRecipes(pConsumer);
    }

    private void shapedRecipe(Consumer<FinishedRecipe> pConsumer){
        ShapedRecipeBuilder.shaped(CAFBlocks.ACACIA_BARREL.get())
                .define('0', Items.ACACIA_PLANKS).define('1', Items.ACACIA_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_acacia_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_PLANKS).build()))
                .unlockedBy("has_acacia_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ACACIA_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.ALLOY_SOULS.get())
                .define('0', CAFItems.ALLOY_SOULS_NUGGET.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.ALLOY_SOULS.get())));

        ShapedRecipeBuilder.shaped(CAFItems.ALLOY_SOULS_INGOT.get())
                .define('0', CAFItems.ALLOY_SOULS.get()).define('1', CAFItems.ALLOY_SOULS_NUGGET.get())
                .pattern("001")
                .unlockedBy("has_alloy_souls", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS.get()).build()))
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.ALLOY_SOULS_INGOT.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_BARREL.get())
                .define('0', CAFBlocks.ALMOND_PLANKS.get()).define('1', CAFBlocks.ALMOND_SLAB.get())
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_almond_slab", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_SLAB.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_DOOR.get(), 3)
                .define('0', CAFBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern("01").pattern("01").pattern("00")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_DOOR.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_FENCE.get(), 3)
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("101").pattern("101")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_FENCE.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_FENCE_GATE.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("010").pattern("010")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_FENCE_GATE.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_GLASS.get())
                .define('0', CAFBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern(" 0 ").pattern("010")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_GLASS.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_PRESSURE_PLATE.get())
                .define('0', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("00")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_PRESSURE_PLATE.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_SIGN.get(), 3)
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("111").pattern("111").pattern(" 0 ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_SIGN.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_SLAB.get(), 6)
                .define('0', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_SLAB.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_STAIRS.get(), 4)
                .define('0', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("0  ").pattern("00 ").pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_STAIRS.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_TRAPDOOR.get())
                .define('0', CAFBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern("010").pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_TRAPDOOR.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_WOOD.get(), 3)
                .define('0', CAFBlocks.ALMOND_LOG.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_almond_log", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_LOG.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_WOOD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.BIRCH_BARREL.get())
                .define('0', Items.BIRCH_PLANKS).define('1', Items.BIRCH_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_birch_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_PLANKS).build()))
                .unlockedBy("has_birch_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.BIRCH_BARREL.get())));

        ShapedRecipeBuilder.shaped(vectorwing.farmersdelight.common.registry.ModItems.CHOCOLATE_PIE.get())
                .define('0', CAFItems.COCOA_POWDER.get()).define('1', ForgeTags.MILK)
                .define('2', Items.SUGAR).define('3', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .pattern("000").pattern("111").pattern("232")
                .unlockedBy("has_cocoa_powder", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.COCOA_POWDER.get()).build()))
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item().of(ForgeTags.MILK).build()))
                .unlockedBy("has_sugar", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SUGAR).build()))
                .unlockedBy("has_pie_crust", inventoryTrigger(ItemPredicate.Builder.item().of(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(vectorwing.farmersdelight.common.registry.ModItems.CHOCOLATE_PIE.get())));

        ShapedRecipeBuilder.shaped(CAFItems.COPPER_COIL.get(), 2)
                .define('0', ItemTags.WOODEN_BUTTONS).define('1', CAFTags.forgeItemTag("plates/copper"))
                .define('2', CAFTags.forgeItemTag("nuggets/copper")).define('3', CAFTags.forgeItemTag("rods/wooden"))
                .pattern("202").pattern("131").pattern("202")
                .unlockedBy("has_wooden_button", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.WOODEN_BUTTONS).build()))
                .unlockedBy("has_copper_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/copper")).build()))
                .unlockedBy("has_copper_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/copper")).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.COPPER_COIL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ACACIA_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.ACACIA_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_acacia_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ACACIA_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.ALMOND_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', CAFBlocks.ALMOND_PLANKS.get())
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.BIRCH_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.BIRCH_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_birch_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.BIRCH_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.CRIMSON_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.CRIMSON_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_crimson_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CRIMSON_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.CRIMSON_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.DARK_OAK_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.DARK_OAK_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_dark_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DARK_OAK_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.DARK_OAK_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.JUNGLE_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.JUNGLE_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_jungle_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.JUNGLE_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.JUNGLE_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.OAK_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.OAK_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.OAK_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.OAK_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.SPRUCE_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.SPRUCE_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_spruce_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SPRUCE_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.SPRUCE_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.WARPED_CUTTING_BOARD.get())
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Items.WARPED_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_warped_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WARPED_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.WARPED_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(CAFItems.ALUMINUM_COIL.get(), 2)
                .define('0', ItemTags.WOODEN_BUTTONS).define('1', CAFTags.forgeItemTag("plates/aluminum"))
                .define('2', CAFTags.forgeItemTag("nuggets/aluminum")).define('3', CAFTags.forgeItemTag("rods/wooden"))
                .pattern("202").pattern("131").pattern("202")
                .unlockedBy("has_wooden_button", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.WOODEN_BUTTONS).build()))
                .unlockedBy("has_aluminum_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/aluminum")).build()))
                .unlockedBy("has_aluminum_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/aluminum")).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.ALUMINUM_COIL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.CRIMSON_BARREL.get())
                .define('0', Items.CRIMSON_PLANKS).define('1', Items.CRIMSON_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_crimson_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CRIMSON_PLANKS).build()))
                .unlockedBy("has_crimson_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CRIMSON_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.CRIMSON_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.DARK_OAK_BARREL.get())
                .define('0', Items.DARK_OAK_PLANKS).define('1', Items.DARK_OAK_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_dark_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DARK_OAK_PLANKS).build()))
                .unlockedBy("has_dark_oak_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DARK_OAK_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.DARK_OAK_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.DEEPSLATE_WALKWAY.get(), 4)
                .define('0', Items.DEEPSLATE_BRICKS)
                .pattern("00").pattern("00")
                .unlockedBy("has_deepslate_bricks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DEEPSLATE_BRICKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.DEEPSLATE_WALKWAY.get())));

        ShapedRecipeBuilder.shaped(CAFItems.ELECTRUM_COIL.get(), 2)
                .define('0', ItemTags.WOODEN_BUTTONS).define('1', CAFTags.forgeItemTag("plates/electrum"))
                .define('2', CAFTags.forgeItemTag("nuggets/electrum")).define('3', CAFTags.forgeItemTag("rods/wooden"))
                .pattern("202").pattern("131").pattern("202")
                .unlockedBy("has_wooden_button", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.WOODEN_BUTTONS).build()))
                .unlockedBy("has_electrum_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/electrum")).build()))
                .unlockedBy("has_electrum_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/electrum")).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.ELECTRUM_COIL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.FERMENTATION_BARREL.get())
                .define('0', AllBlocks.FLUID_PIPE.get()).define('1', AllBlocks.COPPER_CASING.get())
                .define('2', CAFTags.forgeItemTag("plates/copper"))
                .pattern("0").pattern("2").pattern("1")
                .unlockedBy("has_fluid_pipe", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.FLUID_PIPE.get()).build()))
                .unlockedBy("has_copper_casing", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.COPPER_CASING.get()).build()))
                .unlockedBy("has_copper_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/copper")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FERMENTATION_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.FERTILIZED_RED_SAND.get(), 2)
                .define('0', Items.RED_SAND).define('1', Items.BONE_MEAL).define('2', Items.DIRT)
                .pattern("01").pattern("20")
                .unlockedBy("has_red_sand", inventoryTrigger(ItemPredicate.Builder.item().of(Items.RED_SAND).build()))
                .unlockedBy("has_bone_meal", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BONE_MEAL).build()))
                .unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DIRT).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FERTILIZED_RED_SAND.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.FERTILIZED_SAND.get(), 2)
                .define('0', Items.SAND).define('1', Items.BONE_MEAL).define('2', Items.DIRT)
                .pattern("01").pattern("20")
                .unlockedBy("has_sand", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SAND).build()))
                .unlockedBy("has_bone_meal", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BONE_MEAL).build()))
                .unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DIRT).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FERTILIZED_SAND.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.FIRECLAY_BRICKS.get())
                .define('0', CAFItems.FIRECLAY_BRICK.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_fireclay_brick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.FIRECLAY_BRICK.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FIRECLAY_BRICKS.get())));

//        ShapedRecipeBuilder.shaped(CAFBlocks.FURNITURE_CUTTER.get())
//                .define('0', AllBlocks.DEPOT.get()).define('1', Items.IRON_INGOT)
//                .pattern("1").pattern("0")
//                .unlockedBy("has_depot", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.DEPOT.get()).build()))
//                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
//                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FURNITURE_CUTTER.get())));

        ShapedRecipeBuilder.shaped(CAFItems.GLOWING_BRASS_COPPER_LANTERN.get())
                .define('0', CAFItems.COPPER_COIL.get()).define('1', CAFItems.GLOWING_BRASS_INGOT.get())
                .define('2', CAFItems.GLOWING_BRASS_SHEET.get())
                .pattern("1").pattern("0").pattern("2")
                .unlockedBy("has_copper_coil", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.COPPER_COIL.get()).build()))
                .unlockedBy("has_glowing_brass_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_INGOT.get()).build()))
                .unlockedBy("has_glowing_brass_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_SHEET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.GLOWING_BRASS_COPPER_LANTERN.get())));

        ShapedRecipeBuilder.shaped(CAFItems.GLOWING_BRASS_INGOT.get())
                .define('0', CAFItems.GLOWING_BRASS_NUGGET.get())
                .pattern("000").pattern("000").pattern("000")
                .unlockedBy("has_glowing_brass_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.GLOWING_BRASS_INGOT.get())));

        ShapedRecipeBuilder.shaped(CAFItems.GLOWING_BRASS_STEEL_LANTERN.get())
                .define('0', CAFItems.STEEL_COIL.get()).define('1', CAFItems.GLOWING_BRASS_INGOT.get())
                .define('2', CAFItems.GLOWING_BRASS_SHEET.get())
                .pattern("1").pattern("0").pattern("2")
                .unlockedBy("has_steel_coil", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_COIL.get()).build()))
                .unlockedBy("has_glowing_brass_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_INGOT.get()).build()))
                .unlockedBy("has_glowing_brass_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_SHEET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.GLOWING_BRASS_STEEL_LANTERN.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.JUNGLE_BARREL.get())
                .define('0', Items.JUNGLE_PLANKS).define('1', Items.JUNGLE_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_jungle_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.JUNGLE_PLANKS).build()))
                .unlockedBy("has_jungle_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.JUNGLE_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.JUNGLE_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.LANTERN.get())
                .define('0', Items.TORCH).define('1', Items.IRON_NUGGET)
                .pattern("111").pattern("101").pattern("111")
                .unlockedBy("has_torch", inventoryTrigger(ItemPredicate.Builder.item().of(Items.TORCH).build()))
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.LANTERN.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.LARGE_STEEL_COGWHEEL.get())
                .define('0', CAFBlocks.STEEL_COGWHEEL.get()).define('1', CAFItems.STEEL_NUGGET.get())
                .pattern(" 1 ").pattern("101").pattern(" 1 ")
                .unlockedBy("has_steel_cogwheel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STEEL_COGWHEEL.get()).build()))
                .unlockedBy("has_steel_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.LARGE_STEEL_COGWHEEL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.LARGE_STEEL_COGWHEEL.get())
                .define('0', CAFBlocks.STEEL_SHAFT.get()).define('1', CAFItems.STEEL_INGOT.get())
                .define('2', CAFItems.STEEL_NUGGET.get())
                .pattern("212").pattern("101").pattern("212")
                .unlockedBy("has_steel_shaft", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STEEL_SHAFT.get()).build()))
                .unlockedBy("has_steel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_INGOT.get()).build()))
                .unlockedBy("has_steel_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.LARGE_STEEL_COGWHEEL.get()) + "_from_ingot"));

        ShapedRecipeBuilder.shaped(CAFBlocks.MECHANICAL_BLENDER.get())
                .define('0', CAFBlocks.STEEL_CASING.get()).define('1', CAFBlocks.STEEL_COGWHEEL.get())
                .define('2', AllItems.PROPELLER.get())
                .pattern("1").pattern("0").pattern("2")
                .unlockedBy("has_steel_casing", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STEEL_CASING.get()).build()))
                .unlockedBy("has_steel_cogwheel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STEEL_COGWHEEL.get()).build()))
                .unlockedBy("has_propeller", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.PROPELLER.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.MECHANICAL_BLENDER.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.OAK_BARREL.get())
                .define('0', Items.OAK_PLANKS).define('1', Items.OAK_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.OAK_PLANKS).build()))
                .unlockedBy("has_oak_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.OAK_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.OAK_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.PASTRY_TABLE.get())
                .define('0', Items.RED_CARPET).define('1', AllItems.BRASS_INGOT.get())
                .define('2', CAFTags.forgeItemTag("rods/wooden")).define('3', AllItems.BRASS_SHEET.get())
                .define('4', AllItems.BRASS_NUGGET.get()).define('5', ItemTags.PLANKS)
                .pattern("101").pattern("232").pattern("454")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item().of(Items.RED_CARPET).build()))
                .unlockedBy("has_brass_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.BRASS_INGOT.get()).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_brass_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.BRASS_SHEET.get()).build()))
                .unlockedBy("has_brass_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.BRASS_NUGGET.get()).build()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.PASTRY_TABLE.get())));

        ShapedRecipeBuilder.shaped(CAFItems.RAW_PIZZA.get())
                .define('0', CAFItems.MOZZARELLA_CHEESE.get()).define('1', CAFTags.Items.MEAT)
                .define('2', CAFItems.BASE_OF_DOUGH.get())
                .pattern("0").pattern("1").pattern("2")
                .unlockedBy("has_mozzarella_cheese", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.MOZZARELLA_CHEESE.get()).build()))
                .unlockedBy("has_meat", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.MEAT).build()))
                .unlockedBy("has_base_of_dough", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.BASE_OF_DOUGH.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RAW_PIZZA.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.RAW_RUBY_BLOCK.get())
                .define('0', CAFItems.RAW_RUBY.get())
                .pattern("000").pattern("000").pattern("000")
                .unlockedBy("has_raw_ruby", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RAW_RUBY.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.RAW_RUBY_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.RAW_TANTALUM_BLOCK.get())
                .define('0', CAFItems.RAW_TANTALUM.get())
                .pattern("000").pattern("000").pattern("000")
                .unlockedBy("has_raw_tantalum", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RAW_TANTALUM.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.RAW_TANTALUM_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())
                .define('0', CAFItems.RAW_TUNGSTEN.get())
                .pattern("000").pattern("000").pattern("000")
                .unlockedBy("has_raw_tungsten", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RAW_TUNGSTEN.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.RED_SANDSTONE_WALKWAY.get(), 4)
                .define('0', Items.CUT_RED_SANDSTONE)
                .pattern("00").pattern("00")
                .unlockedBy("has_cut_red_sandstone", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CUT_RED_SANDSTONE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.RED_SANDSTONE_WALKWAY.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.RUBY_BLOCK.get())
                .define('0', CAFItems.RUBY.get())
                .pattern("000").pattern("000").pattern("000")
                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RUBY.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.RUBY_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.SANDSTONE_WALKWAY.get(), 4)
                .define('0', Items.CUT_SANDSTONE)
                .pattern("00").pattern("00")
                .unlockedBy("has_cut_sandstone", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CUT_SANDSTONE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.SANDSTONE_WALKWAY.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.SECURE_BLOCK.get(), 3)
                .define('0', CAFTags.forgeItemTag("plates/brass")).define('1', CAFTags.forgeItemTag("plates/steel"))
                .pattern("101").pattern("010").pattern("101")
                .unlockedBy("has_brass_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/brass")).build()))
                .unlockedBy("has_steel_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/steel")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.SECURE_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFItems.SOUL_LANTERN.get())
                .define('0', Items.SOUL_TORCH).define('1', Items.IRON_NUGGET)
                .pattern("111").pattern("101").pattern("111")
                .unlockedBy("has_soul_torch", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SOUL_TORCH).build()))
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.SOUL_LANTERN.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.SPRUCE_BARREL.get())
                .define('0', Items.SPRUCE_PLANKS).define('1', Items.SPRUCE_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_spruce_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SPRUCE_PLANKS).build()))
                .unlockedBy("has_spruce_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SPRUCE_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.SPRUCE_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_AXE.get())
                .define('0', CAFItems.TOOL_HANDLE.get()).define('1', CAFTags.Items.STEEL)
                .define('2', Items.GOLD_NUGGET)
                .pattern("11").pattern("10").pattern(" 2")
                .unlockedBy("has_tool_handle", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TOOL_HANDLE.get()).build()))
                .unlockedBy("has_steel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .unlockedBy("has_gold_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.GOLD_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_AXE.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_CHAIN.get())
                .define('0', CAFItems.STEEL_COIL.get())
                .pattern("0").pattern("0").pattern("0")
                .unlockedBy("has_steel_coil", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_COIL.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_CHAIN.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.STEEL_COGWHEEL.get())
                .define('0', CAFBlocks.STEEL_SHAFT.get()).define('1', CAFItems.STEEL_INGOT.get())
                .pattern(" 1 ").pattern("101").pattern(" 1 ")
                .unlockedBy("has_steel_shaft", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STEEL_SHAFT.get()).build()))
                .unlockedBy("has_steel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.STEEL_COGWHEEL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_COIL.get(), 2)
                .define('0', ItemTags.WOODEN_BUTTONS).define('1', CAFTags.forgeItemTag("plates/steel"))
                .define('2', CAFTags.forgeItemTag("nuggets/steel")).define('3', CAFTags.forgeItemTag("rods/wooden"))
                .pattern("202").pattern("131").pattern("202")
                .unlockedBy("has_wooden_button", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.WOODEN_BUTTONS).build()))
                .unlockedBy("has_steel_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/steel")).build()))
                .unlockedBy("has_steel_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/steel")).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_COIL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_DUST.get())
                .define('0', CAFTags.forgeItemTag("dusts/coal")).define('1', CAFTags.forgeItemTag("dusts/iron"))
                .pattern("10").pattern("01")
                .unlockedBy("has_coal_dust", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("dusts/coal")).build()))
                .unlockedBy("has_iron_dust", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("dusts/iron")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_DUST.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_HOE.get())
                .define('0', CAFItems.TOOL_HANDLE.get()).define('1', CAFTags.Items.STEEL)
                .define('2', Items.GOLD_NUGGET)
                .pattern("11").pattern(" 0").pattern(" 2")
                .unlockedBy("has_tool_handle", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TOOL_HANDLE.get()).build()))
                .unlockedBy("has_steel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .unlockedBy("has_gold_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.GOLD_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_HOE.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_INGOT.get())
                .define('0', CAFItems.STEEL_NUGGET.get()).define('1', CAFTags.forgeItemTag("nuggets/steel"))
                .pattern("111").pattern("101").pattern("111")
                .unlockedBy("has_steel_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/steel")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_INGOT.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_PICKAXE.get())
                .define('0', CAFItems.TOOL_HANDLE.get()).define('1', CAFTags.Items.STEEL)
                .define('2', Items.GOLD_NUGGET)
                .pattern("111").pattern(" 0 ").pattern(" 2 ")
                .unlockedBy("has_tool_handle", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TOOL_HANDLE.get()).build()))
                .unlockedBy("has_steel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .unlockedBy("has_gold_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.GOLD_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_PICKAXE.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.STEEL_LAMP_BLOCK.get())
                .define('0', Items.REDSTONE).define('1', CAFItems.RUBY.get())
                .define('2', CAFTags.forgeItemTag("plates/steel"))
                .pattern("212").pattern("101").pattern("212")
                .unlockedBy("has_redstone", inventoryTrigger(ItemPredicate.Builder.item().of(Items.REDSTONE).build()))
                .unlockedBy("has_ruby", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RUBY.get()).build()))
                .unlockedBy("has_steel_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("plates/steel")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.STEEL_LAMP_BLOCK.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.STEEL_SHAFT.get(), 18)
                .define('0', CAFTags.Items.STEEL)
                .pattern("0").pattern("0")
                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.STEEL_SHAFT.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_SHOVEL.get())
                .define('0', CAFItems.TOOL_HANDLE.get()).define('1', CAFTags.Items.STEEL)
                .define('2', Items.GOLD_NUGGET)
                .pattern("1").pattern("0").pattern("2")
                .unlockedBy("has_tool_handle", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TOOL_HANDLE.get()).build()))
                .unlockedBy("has_steel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .unlockedBy("has_gold_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.GOLD_NUGGET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_SHOVEL.get())));

        ShapedRecipeBuilder.shaped(CAFItems.STEEL_SWORD.get())
                .define('0', CAFItems.TOOL_HANDLE.get()).define('1', CAFTags.Items.STEEL)
                .pattern("1").pattern("1").pattern("0")
                .unlockedBy("has_tool_handle", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TOOL_HANDLE.get()).build()))
                .unlockedBy("has_steel", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.STEEL).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_SWORD.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.STONE_WALKWAY.get(), 4)
                .define('0', Items.STONE_BRICKS)
                .pattern("00").pattern("00")
                .unlockedBy("has_stone_bricks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.STONE_BRICKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.STONE_WALKWAY.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.STRIPPED_ALMOND_WOOD.get(), 3)
                .define('0', CAFBlocks.STRIPPED_ALMOND_LOG.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.STRIPPED_ALMOND_LOG.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.STRIPPED_ALMOND_WOOD.get())));

        ShapedRecipeBuilder.shaped(CAFItems.TANTALUM_INGOT.get())
                .define('0', CAFItems.TANTALUM_NUGGET.get()).define('1', CAFTags.forgeItemTag("nuggets/tantalum"))
                .pattern("111").pattern("101").pattern("111")
                .unlockedBy("has_tantalum_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/tantalum")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TANTALUM_INGOT.get())));

        ShapedRecipeBuilder.shaped(CAFItems.TORN_SOUL_CHAIN.get())
                .define('0', CAFItems.ALLOY_SOULS_INGOT.get()).define('1', CAFItems.ALLOY_SOULS_NUGGET.get())
                .pattern("1").pattern("0").pattern("1")
                .unlockedBy("has_alloy_souls_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_INGOT.get()).build()))
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TORN_SOUL_CHAIN.get())));

        ShapedRecipeBuilder.shaped(CAFItems.TORN_SOUL_LANTERN.get())
                .define('0', CAFItems.ALLOY_SOULS_SHEET.get()).define('1', CAFItems.ALLOY_SOULS_NUGGET.get())
                .define('2', Items.SOUL_TORCH)
                .pattern("000").pattern("121").pattern("111")
                .unlockedBy("has_alloy_souls_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_SHEET.get()).build()))
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_NUGGET.get()).build()))
                .unlockedBy("has_soul_torch", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SOUL_TORCH).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TORN_SOUL_LANTERN.get())));

        ShapedRecipeBuilder.shaped(CAFItems.TUNGSTEN_INGOT.get())
                .define('0', CAFItems.TUNGSTEN_NUGGET.get()).define('1', CAFTags.forgeItemTag("nuggets/tungsten"))
                .pattern("111").pattern("101").pattern("111")
                .unlockedBy("has_tungsten_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("nuggets/tungsten")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TUNGSTEN_INGOT.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.WARPED_BARREL.get())
                .define('0', Items.WARPED_PLANKS).define('1', Items.WARPED_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_warped_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WARPED_PLANKS).build()))
                .unlockedBy("has_warped_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WARPED_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.WARPED_BARREL.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.SCALES.get())
                .define('0', AllItems.ANDESITE_ALLOY.get()).define('1', CAFTags.Items.IRON_SHEET)
                .define('2', AllItems.PRECISION_MECHANISM.get()).define('3', AllBlocks.ANDESITE_CASING.get())
                .pattern("111").pattern("020").pattern("030")
                .unlockedBy("has_precision_mechanism", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.PRECISION_MECHANISM.get()).build()))
                .unlockedBy("has_iron_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.IRON_SHEET.get()).build()))
                .unlockedBy("has_andesite_alloy", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.ANDESITE_ALLOY.get()).build()))
                .unlockedBy("has_andesite_casing", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.ANDESITE_CASING.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.SCALES.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.FRAMED_CALCITE.get(), 4)
                .define('0', CAFTags.forgeItemTag("rods/wooden")).define('1', Blocks.CALCITE)
                .pattern("010").pattern("101").pattern("010")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_calcite", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.CALCITE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.FRAMED_CALCITE.get())));

        ShapedRecipeBuilder.shaped(CAFBlocks.MARBLE_BLAST_FURNACE.get())
                .define('0', CAFBlocks.FIRECLAY_BRICKS.get()).define('1', CAFItems.MARBLE_BLACK_GALAXY_BRICK.get())
                .define('2', Blocks.FURNACE)
                .pattern("101").pattern("020").pattern("101")
                .unlockedBy("has_marble", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.MARBLE_BLACK_GALAXY_BRICK.get()).build()))
                .unlockedBy("has_fireclay", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.FIRECLAY_BRICKS.get()).build()))
                .unlockedBy("has_furnace", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.FURNACE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.MARBLE_BLAST_FURNACE.get())));

//        ShapedRecipeBuilder.shaped()
//                .define('0', )
//                .pattern("")
//                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of().build()))
//                .save(pConsumer, getCraftingTable(getRecipeId()));
    }

    private void shapelessRecipes(Consumer<FinishedRecipe> pConsumer){
        ShapelessRecipeBuilder.shapeless(vectorwing.farmersdelight.common.registry.ModBlocks.CUTTING_BOARD.get())
                .requires(CAFTags.Items.CUTTING_BOARDS)
                .unlockedBy("has_cutting_boards", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.CUTTING_BOARDS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(vectorwing.farmersdelight.common.registry.ModBlocks.CUTTING_BOARD.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.ALLOY_SOULS_NUGGET.get(), 9)
                .requires(CAFItems.ALLOY_SOULS_INGOT.get())
                .unlockedBy("has_alloy_souls_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.ALLOY_SOULS_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.ALLOY_SOULS_NUGGET.get())));

        ShapelessRecipeBuilder.shapeless(CAFBlocks.ALMOND_BUTTON.get())
                .requires(CAFBlocks.ALMOND_PLANKS.get())
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_BUTTON.get())));

        ShapelessRecipeBuilder.shapeless(CAFBlocks.ALMOND_PLANKS.get(), 4)
                .requires(CAFTags.Items.ALMOND_LOGS)
                .unlockedBy("has_almond_logs", inventoryTrigger(ItemPredicate.Builder.item().of(CAFTags.Items.ALMOND_LOGS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFBlocks.ALMOND_PLANKS.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.FIRECLAY_CLAY_BALL.get())
                .requires(CAFItems.BRICK_DUST.get()).requires(Items.CLAY_BALL)
                .unlockedBy("has_brick_dust", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.BRICK_DUST.get()).build()))
                .unlockedBy("has_clay_ball", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CLAY_BALL).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.FIRECLAY_CLAY_BALL.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.GLOWING_BRASS_NUGGET.get(), 9)
                .requires(CAFItems.GLOWING_BRASS_INGOT.get())
                .unlockedBy("has_glowing_brass_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.GLOWING_BRASS_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.GLOWING_BRASS_NUGGET.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.LANTERN.get())
                .requires(Items.LANTERN)
                .unlockedBy("has_lantern", inventoryTrigger(ItemPredicate.Builder.item().of(Items.LANTERN).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.LANTERN.get()) + "_from_minecraft_lantern"));

        ShapelessRecipeBuilder.shapeless(CAFItems.RAW_RUBY.get(), 9)
                .requires(CAFBlocks.RAW_RUBY_BLOCK.get())
                .unlockedBy("has_raw_ruby_block", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.RAW_RUBY_BLOCK.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RAW_RUBY.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.RAW_TANTALUM.get(), 9)
                .requires(CAFBlocks.RAW_TANTALUM_BLOCK.get())
                .unlockedBy("has_raw_tantalum_block", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.RAW_TANTALUM_BLOCK.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RAW_TANTALUM.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.RAW_TUNGSTEN.get(), 9)
                .requires(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())
                .unlockedBy("has_raw_tungsten_block", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.RAW_TUNGSTEN_BLOCK.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RAW_TUNGSTEN.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.RUBY.get(), 9)
                .requires(CAFBlocks.RUBY_BLOCK.get())
                .unlockedBy("has_ruby_block", inventoryTrigger(ItemPredicate.Builder.item().of(CAFBlocks.RUBY_BLOCK.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RUBY.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.RYE_DOUGH.get())
                .requires(CAFItems.RYE_FLOUR.get()).requires(Items.WATER_BUCKET)
                .unlockedBy("has_rye_flour", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.RYE_FLOUR.get()).build()))
                .unlockedBy("has_water_bucket", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WATER_BUCKET).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.RYE_DOUGH.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.SOUL_LANTERN.get())
                .requires(Items.SOUL_LANTERN)
                .unlockedBy("has_soul_lantern", inventoryTrigger(ItemPredicate.Builder.item().of(Items.LANTERN).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.SOUL_LANTERN.get()) + "_from_minecraft_soul_lantern"));

        ShapelessRecipeBuilder.shapeless(CAFItems.STEEL_NUGGET.get(), 9)
                .requires(CAFItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.STEEL_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.STEEL_NUGGET.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.TANTALUM_NUGGET.get(), 9)
                .requires(CAFItems.TANTALUM_INGOT.get())
                .unlockedBy("has_tantalum_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TANTALUM_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TANTALUM_NUGGET.get())));

        ShapelessRecipeBuilder.shapeless(CAFItems.TUNGSTEN_NUGGET.get(), 9)
                .requires(CAFItems.TUNGSTEN_INGOT.get())
                .unlockedBy("has_tungsten_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CAFItems.TUNGSTEN_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(CAFItems.TUNGSTEN_NUGGET.get())));

//        ShapelessRecipeBuilder.shapeless()
//                .requires()
//                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of().build()))
//                .save(pConsumer, getCraftingTable(getRecipeId()));
    }





    private ResourceLocation getCraftingTable(String id){
        return new ResourceLocation(MOD_ID, "minecraft/crafting_table/" + id);
    }

    private String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }
}
