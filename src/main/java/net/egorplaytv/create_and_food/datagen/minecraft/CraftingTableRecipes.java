package net.egorplaytv.create_and_food.datagen.minecraft;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
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
        ShapedRecipeBuilder.shaped(ModBlocks.ACACIA_BARREL.get())
                .define('0', Items.ACACIA_PLANKS).define('1', Items.ACACIA_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_acacia_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_PLANKS).build()))
                .unlockedBy("has_acacia_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ACACIA_BARREL.get())));

        ShapedRecipeBuilder.shaped(ModItems.ALLOY_SOULS.get())
                .define('0', ModItems.ALLOY_SOULS_NUGGET.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ALLOY_SOULS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModItems.ALLOY_SOULS.get())));

        ShapedRecipeBuilder.shaped(ModItems.ALLOY_SOULS_INGOT.get())
                .define('0', ModItems.ALLOY_SOULS.get()).define('1', ModItems.ALLOY_SOULS_NUGGET.get())
                .pattern("001")
                .unlockedBy("has_alloy_souls", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ALLOY_SOULS.get()).build()))
                .unlockedBy("has_alloy_souls_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ALLOY_SOULS_NUGGET.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModItems.ALLOY_SOULS_INGOT.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_BARREL.get())
                .define('0', ModBlocks.ALMOND_PLANKS.get()).define('1', ModBlocks.ALMOND_SLAB.get())
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_almond_slab", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_SLAB.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_BARREL.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_DOOR.get(), 3)
                .define('0', ModBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern("01").pattern("01").pattern("00")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_DOOR.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_FENCE.get(), 3)
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', ModBlocks.ALMOND_PLANKS.get())
                .pattern("101").pattern("101")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_FENCE.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_FENCE_GATE.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', ModBlocks.ALMOND_PLANKS.get())
                .pattern("010").pattern("010")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_FENCE_GATE.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_GLASS.get())
                .define('0', ModBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern(" 0 ").pattern("010")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_GLASS.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_PRESSURE_PLATE.get())
                .define('0', ModBlocks.ALMOND_PLANKS.get())
                .pattern("00")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_PRESSURE_PLATE.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_SIGN.get(), 3)
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', ModBlocks.ALMOND_PLANKS.get())
                .pattern("111").pattern("111").pattern(" 0 ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_SIGN.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_SLAB.get(), 6)
                .define('0', ModBlocks.ALMOND_PLANKS.get())
                .pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_SLAB.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_STAIRS.get(), 4)
                .define('0', ModBlocks.ALMOND_PLANKS.get())
                .pattern("0  ").pattern("00 ").pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_STAIRS.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_TRAPDOOR.get())
                .define('0', ModBlocks.ALMOND_PLANKS.get()).define('1', Tags.Items.GLASS_COLORLESS)
                .pattern("010").pattern("000")
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.GLASS_COLORLESS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_TRAPDOOR.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_WOOD.get(), 3)
                .define('0', ModBlocks.ALMOND_LOG.get())
                .pattern("00").pattern("00")
                .unlockedBy("has_almond_log", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_LOG.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_WOOD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.BIRCH_BARREL.get())
                .define('0', Items.BIRCH_PLANKS).define('1', Items.BIRCH_SLAB)
                .pattern("010").pattern("0 0").pattern("010")
                .unlockedBy("has_birch_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_PLANKS).build()))
                .unlockedBy("has_birch_slab", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_SLAB).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.BIRCH_BARREL.get())));

        ShapedRecipeBuilder.shaped(vectorwing.farmersdelight.common.registry.ModItems.CHOCOLATE_PIE.get())
                .define('0', ModItems.COCOA_POWDER.get()).define('1', ForgeTags.MILK)
                .define('2', Items.SUGAR).define('3', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .pattern("000").pattern("111").pattern("232")
                .unlockedBy("has_cocoa_powder", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COCOA_POWDER.get()).build()))
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item().of(ForgeTags.MILK).build()))
                .unlockedBy("has_sugar", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SUGAR).build()))
                .unlockedBy("has_pie_crust", inventoryTrigger(ItemPredicate.Builder.item().of(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(vectorwing.farmersdelight.common.registry.ModItems.CHOCOLATE_PIE.get())));

        ShapedRecipeBuilder.shaped(ModItems.COPPER_COIL.get(), 2)
                .define('0', ItemTags.WOODEN_BUTTONS).define('1', ModTags.forgeItemTag("plates/copper"))
                .define('2', ModTags.forgeItemTag("nuggets/copper")).define('3', ModTags.forgeItemTag("rods/wooden"))
                .pattern("202").pattern("131").pattern("202")
                .unlockedBy("has_wooden_button", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.WOODEN_BUTTONS).build()))
                .unlockedBy("has_copper_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("plates/copper")).build()))
                .unlockedBy("has_copper_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("nuggets/copper")).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModItems.COPPER_COIL.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ACACIA_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.ACACIA_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_acacia_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ACACIA_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ACACIA_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.ALMOND_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', ModBlocks.ALMOND_PLANKS.get())
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.BIRCH_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.BIRCH_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_birch_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BIRCH_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.BIRCH_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.CRIMSON_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.CRIMSON_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_crimson_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CRIMSON_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.CRIMSON_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.DARK_OAK_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.DARK_OAK_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_dark_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DARK_OAK_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.DARK_OAK_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.JUNGLE_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.JUNGLE_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_jungle_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.JUNGLE_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.JUNGLE_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.OAK_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.OAK_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_oak_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.OAK_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.OAK_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.SPRUCE_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.SPRUCE_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_spruce_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SPRUCE_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.SPRUCE_CUTTING_BOARD.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.WARPED_CUTTING_BOARD.get())
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Items.WARPED_PLANKS)
                .pattern("011").pattern("011")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_warped_planks", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WARPED_PLANKS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.WARPED_CUTTING_BOARD.get())));

//        ShapedRecipeBuilder.shaped()
//                .define('', )
//                .pattern("")
//                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of().build()))
//                .save(pConsumer, getCraftingTable(getRecipeId()));

        ShapedRecipeBuilder.shaped(ModBlocks.SCALES.get())
                .define('0', AllItems.ANDESITE_ALLOY.get()).define('1', ModTags.Items.IRON_SHEET)
                .define('2', AllItems.PRECISION_MECHANISM.get()).define('3', AllBlocks.ANDESITE_CASING.get())
                .pattern("111").pattern("020").pattern("030")
                .unlockedBy("has_precision_mechanism", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.PRECISION_MECHANISM.get()).build()))
                .unlockedBy("has_iron_sheet", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.IRON_SHEET.get()).build()))
                .unlockedBy("has_andesite_alloy", inventoryTrigger(ItemPredicate.Builder.item().of(AllItems.ANDESITE_ALLOY.get()).build()))
                .unlockedBy("has_andesite_casing", inventoryTrigger(ItemPredicate.Builder.item().of(AllBlocks.ANDESITE_CASING.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.SCALES.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.FRAMED_CALCITE.get(), 4)
                .define('0', ModTags.forgeItemTag("rods/wooden")).define('1', Blocks.CALCITE)
                .pattern("010").pattern("101").pattern("010")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.forgeItemTag("rods/wooden")).build()))
                .unlockedBy("has_calcite", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.CALCITE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.FRAMED_CALCITE.get())));

        ShapedRecipeBuilder.shaped(ModBlocks.MARBLE_BLAST_FURNACE.get())
                .define('0', ModBlocks.FIRECLAY_BRICKS.get()).define('1', ModItems.MARBLE_BLACK_GALAXY_BRICK.get())
                .define('2', Blocks.FURNACE)
                .pattern("101").pattern("020").pattern("101")
                .unlockedBy("has_marble", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MARBLE_BLACK_GALAXY_BRICK.get()).build()))
                .unlockedBy("has_fireclay", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.FIRECLAY_BRICKS.get()).build()))
                .unlockedBy("has_furnace", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.FURNACE).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.MARBLE_BLAST_FURNACE.get())));
    }

    private void shapelessRecipes(Consumer<FinishedRecipe> pConsumer){
        ShapelessRecipeBuilder.shapeless(vectorwing.farmersdelight.common.registry.ModBlocks.CUTTING_BOARD.get())
                .requires(ModTags.Items.CUTTING_BOARDS)
                .unlockedBy("has_cutting_boards", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.Items.CUTTING_BOARDS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(vectorwing.farmersdelight.common.registry.ModBlocks.CUTTING_BOARD.get())));

        ShapelessRecipeBuilder.shapeless(ModItems.ALLOY_SOULS_NUGGET.get(), 9)
                .requires(ModItems.ALLOY_SOULS_INGOT.get())
                .unlockedBy("has_alloy_souls_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ALLOY_SOULS_INGOT.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModItems.ALLOY_SOULS_NUGGET.get())));

        ShapelessRecipeBuilder.shapeless(ModBlocks.ALMOND_BUTTON.get())
                .requires(ModBlocks.ALMOND_PLANKS.get())
                .unlockedBy("has_almond_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.ALMOND_PLANKS.get()).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_BUTTON.get())));

        ShapelessRecipeBuilder.shapeless(ModBlocks.ALMOND_PLANKS.get(), 4)
                .requires(ModTags.Items.ALMOND_LOGS)
                .unlockedBy("has_almond_logs", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.Items.ALMOND_LOGS).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModBlocks.ALMOND_PLANKS.get())));

        ShapelessRecipeBuilder.shapeless(ModItems.FIRECLAY_CLAY_BALL.get())
                .requires(ModItems.BRICK_DUST.get()).requires(Items.CLAY_BALL)
                .unlockedBy("has_brick_dust", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BRICK_DUST.get()).build()))
                .unlockedBy("has_clay_ball", inventoryTrigger(ItemPredicate.Builder.item().of(Items.CLAY_BALL).build()))
                .save(pConsumer, getCraftingTable(getRecipeId(ModItems.FIRECLAY_CLAY_BALL.get())));

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
