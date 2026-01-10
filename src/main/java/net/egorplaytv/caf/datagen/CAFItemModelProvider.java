package net.egorplaytv.caf.datagen;

import net.egorplaytv.caf.datagen.custom.ModItemModelsProperties;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;
import static net.egorplaytv.caf.block.CAFBlocks.*;
import static net.egorplaytv.caf.item.CAFItems.*;

public class CAFItemModelProvider extends ItemModelProvider {
    public CAFItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(BASE_OF_DOUGH.get(), "dough");
        simpleItem(SMALL_DOUGH_BASE.get(), "dough");
        simpleItem(RAW_PIZZA.get(), "dough");
        simpleItem(PIZZA.get(), "dough");
        simpleItem(PIZZA_SLICE.get(), "dough");
        simpleItem(RAW_GLOW_BERRY_CAKE.get(), "dough");
        simpleItem(INCOMPLETE_RAW_GLOW_BERRY_CAKE.get(), "dough", "raw_glow_berry_cake");
        simpleItem(GLOW_BERRY_CAKE.get(), "dough");
        simpleItem(RAW_BERRY_CAKE.get(), "dough");
        simpleItem(INCOMPLETE_RAW_BERRY_CAKE.get(), "dough", "raw_berry_cake");
        simpleItem(BERRY_CAKE.get(), "dough");
        simpleItem(RAW_CAKE.get(), "dough");
        simpleItem(INCOMPLETE_RAW_CAKE.get(), "dough", "raw_cake");
        simpleItem(RAW_SWEET_ROLL.get(), "dough");
        simpleItem(DOUGH_BASE_WITH_CHOCOLATE.get(), "dough");
        simpleItem(SMALL_DOUGH_BASE_WHiH_HONEY.get(), "dough");
        simpleItem(SMALL_DOUGH_BASE_WHiH_CHOCOLATE.get(), "dough");
        simpleItem(RYE_DOUGH.get(), "dough");
        simpleItem(SMALL_DOUGH.get(), "dough", "small_dough_for_the_base");
        simpleItem(BIZET.get());
        simpleItem(POWDERED_SUGAR.get());
        simpleItem(COCOA_POWDER.get());
        simpleItem(HARD_COCOA.get());
        simpleItem(COCOA_BUTTER_BRIQUETTE.get());
        simpleItem(RYE_FLOUR.get());
        simpleItem(RYE_BREAD.get());
        simpleItem(MOZZARELLA_CHEESE.get());
        simpleItem(RAW_EGG.get());
        simpleItem(RAW_YOLK.get());
        simpleItem(RAW_PROTEIN.get());
        simpleItem(BLUEBERRY.get(), "berry");
        simpleItem(BLUEBERRY_SAPLING.get(), "berry/saplings");
        simpleItem(CRANBERRY.get(), "berry");
        simpleItem(CRANBERRY_SAPLING.get(), "berry/saplings");
        simpleItem(RASPBERRY.get(), "berry");
        simpleItem(RASPBERRY_SAPLING.get(), "berry/saplings");
        simpleItem(BLUE_GRAPE.get(), "berry", "blue_grapes");
        simpleItem(BLUE_GRAPE_SAPLING.get(), "berry/saplings");
        simpleItem(GREEN_GRAPE.get(), "berry", "green_grapes");
        simpleItem(GREEN_GRAPE_SAPLING.get(), "berry/saplings");
        simpleItem(PURPLE_GRAPE.get(), "berry", "purple_grapes");
        simpleItem(PURPLE_GRAPE_SAPLING.get(), "berry/saplings");
        simpleItem(RED_GRAPE.get(), "berry", "red_grapes");
        simpleItem(RED_GRAPE_SAPLING.get(), "berry/saplings");
        minecraftItem(PUMPKIN_SEEDS.get());
        minecraftItem(MELON_SEEDS.get());
        simpleItem(APPLE_VINEGAR_BUCKET.get());
        simpleItem(COCOA_OIL_BUCKET.get());
        simpleItem(WHITE_CHOCOLATE_BUCKET.get());
        simpleItem(RED_GRAPE_JUICE_BUCKET.get());
        simpleItem(HONEY_MILK.get());
        simpleItem(ALMOND_NUT.get());
        simpleItem(MACARONI.get());
        simpleItem(PINK_MACARONI.get());
        simpleItem(RED_MACARONI.get());
        simpleItem(WHITE_MACARONI.get());
        simpleItem(EGG_SHELL.get());
        simpleItem(RYE_SEEDS.get());
        simpleItem(RYE.get());
        simpleItem(RICE.get());
        simpleItem(ROASTED_COCOA_BEANS.get());

        minecraftMetalItem(RAW_IRON.get());
        minecraftIngotItem(IRON_INGOT.get());
        minecraftMetalItem(IRON_NUGGET.get());
        minecraftMetalItem(RAW_COPPER.get());
        minecraftIngotItem(COPPER_INGOT.get());
        minecraftMetalItem(RAW_GOLD.get());
        minecraftIngotItem(GOLD_INGOT.get());
        metalItem(PIECE_OF_GOLD.get());
        minecraftMetalItem(GOLD_NUGGET.get());
        minecraftIngotItem(NETHERITE_INGOT.get());
        ingotItem(STEEL_INGOT.get(), 800, 1300);
        metalItem(STEEL_NUGGET.get());
        metalItem(STEEL_SHEET.get());
        ingotItem(GLOWING_BRASS_INGOT.get(), "brass", 500, 700);
        metalItem(GLOWING_BRASS_NUGGET.get());
        metalItem(GLOWING_BRASS_SHEET.get());
        metalItem(ALLOY_SOULS.get());
        ingotItem(ALLOY_SOULS_INGOT.get());
        metalItem(ALLOY_SOULS_NUGGET.get());
        metalItem(ALLOY_SOULS_SHEET.get());
        sequencedAssemblyItem(INCOMPLETE_NETHERITE_INGOT.get(), "metals");
        metalItem(NETHER_ALLOY.get());
        simpleItem(STEEL_COIL.get(), "coil");
        simpleItem(ELECTRUM_COIL.get(), "coil");
        simpleItem(ALUMINUM_COIL.get(), "coil");
        simpleItem(COPPER_COIL.get(), "coil");
        sequencedAssemblyItem(INCOMPLETE_COIN.get(), "coin");
        simpleItem(COPPER_COIN.get(), "coin");
        simpleItem(IRON_COIN.get(), "coin");
        simpleItem(GOLDEN_COIN.get(), "coin");
        simpleItem(BROKEN_COPPER_COIN.get(), "coin");
        simpleItem(BROKEN_IRON_COIN.get(), "coin");
        simpleItem(BROKEN_GOLDEN_COIN.get(), "coin");
        simpleItem(COAL_DUST.get());
        simpleItem(IRON_DUST.get());
        simpleItem(STEEL_DUST.get());
        simpleItem(BRICK_DUST.get());
        simpleItem(WOOD_SAWDUST.get());
        simpleItem(WOOD_CHIPS.get());
        simpleItem(CRIMSON_SAWDUST.get());
        simpleItem(CRIMSON_CHIPS.get());
        simpleItem(WARPED_SAWDUST.get());
        simpleItem(WARPED_CHIPS.get());
        simpleItem(THIN_CARDBOARD.get());
        simpleItem(CRIMSON_THIN_CARDBOARD.get());
        simpleItem(WARPED_THIN_CARDBOARD.get());
        simpleItem(RAW_RUBY.get());
        simpleItem(RUBY.get());
        simpleItem(FIRECLAY_BRICK.get());
        simpleItem(FIRECLAY_CLAY_BALL.get());
        sequencedAssemblyItem(INCOMPLETE_MARBLE_BRICK.get());
        simpleItem(MARBLE_BRICK.get());
        sequencedAssemblyItem(INCOMPLETE_MARBLE_BLACK_GALAXY_BRICK.get());
        simpleItem(MARBLE_BLACK_GALAXY_BRICK.get());
        sequencedAssemblyItem(INCOMPLETE_MARBLE_PERLIN_PINK_BRICK.get());
        simpleItem(MARBLE_PERLIN_PINK_BRICK.get());
        metalItem(RAW_TANTALUM.get());
        metalItem(CRASHED_RAW_TANTALUM.get());
        ingotItem(TANTALUM_INGOT.get(), 550, 1320);
        metalItem(TANTALUM_NUGGET.get());
        metalItem(RAW_TUNGSTEN.get());
        metalItem(CRASHED_RAW_TUNGSTEN.get());
        ingotItem(TUNGSTEN_INGOT.get(), 550, 1600);
        metalItem(TUNGSTEN_NUGGET.get());

        sequencedAssemblyItem(INCOMPLETE_TOOL_HANDLE.get(), "tools");
        handheldItem(TOOL_HANDLE.get());
        sequencedAssemblyItem(INCOMPLETE_DIAMOND_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(DIAMOND_KNIFE.get(), "diamond");
        minecraftHandheldItem(IRON_SHOVEL.get());
        minecraftHandheldItem(IRON_PICKAXE.get());
        minecraftHandheldItem(IRON_AXE.get());
        minecraftHandheldItem(IRON_HOE.get());
        minecraftHandheldItem(IRON_SWORD.get());
        sequencedAssemblyItem(INCOMPLETE_IRON_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(IRON_KNIFE.get(), "iron");
        handheldItem(COPPER_SHOVEL.get(), "copper");
        handheldItem(COPPER_PICKAXE.get(), "copper");
        handheldItem(COPPER_AXE.get(), "copper");
        handheldItem(COPPER_HOE.get(), "copper");
        handheldItem(COPPER_SWORD.get(), "copper");
        minecraftHandheldItem(GOLDEN_SHOVEL.get());
        minecraftHandheldItem(GOLDEN_PICKAXE.get());
        minecraftHandheldItem(GOLDEN_AXE.get());
        minecraftHandheldItem(GOLDEN_HOE.get());
        minecraftHandheldItem(GOLDEN_SWORD.get());
        sequencedAssemblyItem(INCOMPLETE_GOLDEN_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(GOLDEN_KNIFE.get(), "gold");
        minecraftHandheldItem(NETHERITE_SHOVEL.get());
        minecraftHandheldItem(NETHERITE_PICKAXE.get());
        minecraftHandheldItem(NETHERITE_AXE.get());
        minecraftHandheldItem(NETHERITE_HOE.get());
        minecraftHandheldItem(NETHERITE_SWORD.get());
        sequencedAssemblyItem(INCOMPLETE_NETHERITE_KNIFE.get(), "tools/incomplete_knives", 2, 3, 4);
        handheldItem(NETHERITE_KNIFE.get(), "netherite");
        handheldItem(STEEL_SWORD.get(), "steel");
        handheldItem(STEEL_PICKAXE.get(), "steel");
        handheldItem(STEEL_SHOVEL.get(), "steel");
        handheldItem(STEEL_AXE.get(), "steel");
        handheldItem(STEEL_HOE.get(), "steel");
        hammerItem(STEEL_HAMMER.get(), "steel");
        sequencedAssemblyItem(INCOMPLETE_STEEL_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(STEEL_KNIFE.get(), "steel");
        handheldItem(TANTALUM_SWORD.get(), "tantalum");
        handheldItem(TANTALUM_PICKAXE.get(), "tantalum");
        handheldItem(TANTALUM_SHOVEL.get(), "tantalum");
        handheldItem(TANTALUM_AXE.get(), "tantalum");
        handheldItem(TANTALUM_HOE.get(), "tantalum");
        hammerItem(TANTALUM_HAMMER.get(), "tantalum");
        sequencedAssemblyItem(INCOMPLETE_TANTALUM_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(TANTALUM_KNIFE.get(), "tantalum");
        handheldItem(TUNGSTEN_SWORD.get(), "tungsten");
        handheldItem(TUNGSTEN_PICKAXE.get(), "tungsten");
        handheldItem(TUNGSTEN_SHOVEL.get(), "tungsten");
        handheldItem(TUNGSTEN_AXE.get(), "tungsten");
        handheldItem(TUNGSTEN_HOE.get(), "tungsten");
        hammerItem(TUNGSTEN_HAMMER.get(), "tungsten");
        hammerItem(KITCHEN_HAMMER.get());
        sequencedAssemblyItem(INCOMPLETE_TUNGSTEN_KNIFE.get(), "tools/incomplete_knives", 3);
        handheldItem(TUNGSTEN_KNIFE.get(), "tungsten");

        simpleItem(CAFItems.TORN_SOUL_CHAIN.get());
        simpleItem(CAFItems.STEEL_CHAIN.get());
        simpleItem(CAFItems.TORN_SOUL_LANTERN.get());
        simpleItem(CAFItems.GLOWING_BRASS_COPPER_LANTERN.get());
        simpleItem(CAFItems.GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get());
        simpleItem(CAFItems.GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get());
        simpleItem(CAFItems.GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get());
        simpleItem(CAFItems.GLOWING_BRASS_WAXED_COPPER_LANTERN.get(), "", "glowing_brass_copper_lantern");
        simpleItem(CAFItems.GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get(), "", "glowing_brass_exposed_copper_lantern");
        simpleItem(CAFItems.GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get(), "", "glowing_brass_weathered_copper_lantern");
        simpleItem(CAFItems.GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get(), "", "glowing_brass_oxidized_copper_lantern");
        simpleItem(CAFItems.GLOWING_BRASS_STEEL_LANTERN.get());
        minecraftItem(CAFItems.LANTERN.get());
        minecraftItem(CAFItems.SOUL_LANTERN.get());
        simpleItem(CAFItems.ALMOND_SIGN.get());

        simpleItem(ALMOND_DOOR.get().asItem());
        blockItem(ALMOND_TRAPDOOR.get(), "trapdoors", "almond_trapdoor_bottom");
        blockItem(ALMOND_BUTTON.get(), "item", "almond_button_inventory");
        blockItem(ALMOND_PRESSURE_PLATE.get(), "item");
        blockItem(ALMOND_FENCE.get(), "item", "almond_fence_inventory");
        blockItem(ALMOND_FENCE_GATE.get(), "item");
        blockItem(ALMOND_LOG.get(), "item");
        blockItem(STRIPPED_ALMOND_LOG.get(), "item");
        blockItem(ALMOND_WOOD.get(), "item");
        blockItem(STRIPPED_ALMOND_WOOD.get(), "item");
        blockItem(ALMOND_PLANKS.get(), "planks", "almond_planks");
        blockItem(ALMOND_STAIRS.get(), "item");
        blockItem(ALMOND_SLAB.get(), "item");

        blockItem(ALMOND_LEAVES.get(), "leaves", "almond_leaves_0");
        blockItem(PASTRY_TABLE.get(), "custom");
        blockItem(FURNITURE_CUTTER.get(), "custom");
        blockItem(MARBLE_BLAST_FURNACE.get(), "furnace");
        blockItem(ACACIA_BARREL.get(), "barrels");
        blockItem(ALMOND_BARREL.get(), "barrels");
        blockItem(BIRCH_BARREL.get(), "barrels");
        blockItem(CRIMSON_BARREL.get(), "barrels");
        blockItem(DARK_OAK_BARREL.get(), "barrels");
        blockItem(JUNGLE_BARREL.get(), "barrels");
        blockItem(OAK_BARREL.get(), "barrels");
        blockItem(SPRUCE_BARREL.get(), "barrels");
        blockItem(WARPED_BARREL.get(), "barrels");
        blockItem(TERMINAL.get(), "terminal", "terminal_item");
        blockItem(COBBLED_MARBLE.get(), "marbles");
        blockItem(COBBLED_MARBLE_BLACK_GALAXY.get(), "marbles");
        blockItem(COBBLED_MARBLE_PERLIN_PINK.get(), "marbles");
        blockItem(FIRECLAY_BRICKS.get(), "item", "fireclay_bricks_0");
        blockItemModel(ACACIA_TERRACE.get(), "terrace_block");
        blockItemModel(ACACIA_TERRACE_STAIRS.get(), ACACIA_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(ALMOND_TERRACE.get(), "terrace_block");
        blockItemModel(ALMOND_TERRACE_STAIRS.get(), ALMOND_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(BIRCH_TERRACE.get(), "terrace_block");
        blockItemModel(BIRCH_TERRACE_STAIRS.get(), BIRCH_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(CRIMSON_TERRACE.get(), "terrace_block");
        blockItemModel(CRIMSON_TERRACE_STAIRS.get(), CRIMSON_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(DARK_OAK_TERRACE.get(), "terrace_block");
        blockItemModel(DARK_OAK_TERRACE_STAIRS.get(), DARK_OAK_TERRACE.get(),"terrace_stairs_west");
        blockItemModel(JUNGLE_TERRACE.get(), "terrace_block");
        blockItemModel(JUNGLE_TERRACE_STAIRS.get(), JUNGLE_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(OAK_TERRACE.get(), "terrace_block");
        blockItemModel(OAK_TERRACE_STAIRS.get(), OAK_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(SPRUCE_TERRACE.get(), "terrace_block");
        blockItemModel(SPRUCE_TERRACE_STAIRS.get(), SPRUCE_TERRACE.get(), "terrace_stairs_west");
        blockItemModel(WARPED_TERRACE.get(), "terrace_block");
        blockItemModel(WARPED_TERRACE_STAIRS.get(), WARPED_TERRACE.get(), "terrace_stairs_west");
        blockItem(FRAMED_CALCITE.get(), "wall");
        blockItem(STEEL_LAMP_BLOCK.get(), "lamps", "steel_ruby_lamp_block_off");
        blockItem(RUBY_ORE.get(), "item");
        blockItem(DEEPSLATE_RUBY_ORE.get(), "item");
        blockItem(RAW_RUBY_BLOCK.get(), "item");
        blockItem(RUBY_BLOCK.get(), "item");
        blockItem(STONE_TANTALUM_ORE.get(), "ores", "stone_tantalum_ore_0");
        blockItem(DEEPSLATE_TANTALUM_ORE.get(), "ores", "deepslate_tantalum_ore_0");
        blockItem(TANTALUM_ORE.get(), "ores", "tantalum_ore_0");
        blockItem(BLACKSTONE_TANTALUM_ORE.get(), "ores", "blackstone_tantalum_ore_0");
        blockItem(RAW_TANTALUM_BLOCK.get(), "item");
        blockItem(TUNGSTEN_ORE.get(), "ores", "tungsten_ore_0");
        blockItem(STONE_TUNGSTEN_ORE.get(), "ores", "stone_tungsten_ore_0");
        blockItem(DEEPSLATE_TUNGSTEN_ORE.get(), "ores", "deepslate_tungsten_ore_0");
        blockItem(RAW_TUNGSTEN_BLOCK.get(), "item");
        blockItem(FARMLAND_SUMP_SAND.get(), "farmlands", "farmland_sump_sand_bottom");
        blockItem(FARMLAND_SUMP_RED_SAND.get(), "farmlands", "farmland_sump_red_sand_bottom");
        blockItem(FARMLAND_SUMP_RICH_SOIL.get(), "farmlands", "farmland_sump_rich_soil_bottom");
        blockItem(FARMLAND_SUMP_DIRT.get(), "farmlands", "farmland_sump_dirt_bottom");
        blockItem(FERTILIZED_SAND.get(), "farmlands");
        blockItem(SAND_FARMLAND.get(), "farmlands");
        blockItem(FERTILIZED_RED_SAND.get(), "farmlands");
        blockItem(RED_SAND_FARMLAND.get(), "farmlands");
        blockItem(FLOODED_FARMLAND.get(), "farmlands");
        blockItem(FLOODED_RICH_SOIL_FARMLAND.get(), "farmlands");
        blockItem(FERTILIZED_SAND.get(), "farmlands");
        customBlockItem(NIXIE_VASE.get(), "vases", "vase_nixie_fluid")
                .texture("outside", "caf:block/vase/marble_vase_side")
                .texture("inside", "caf:block/vase/marble_vase_inner")
                .texture("particle", "caf:block/palettes/stone_types/natural/marble_0");
        customBlockItem(NIXIE_VASE_PERLIN_PINK.get(), "vases", "vase_nixie_fluid")
                .texture("outside", "caf:block/vase/marble_perlin_pink_vase_side")
                .texture("inside", "caf:block/vase/marble_perlin_pink_vase_inner")
                .texture("particle", "caf:block/palettes/stone_types/natural/marble_perlin_pink_0");
        customBlockItem(NIXIE_VASE_BLACK_GALAXY.get(), "vases", "vase_nixie_fluid")
                .texture("outside", "caf:block/vase/marble_black_galaxy_vase_side")
                .texture("inside", "caf:block/vase/marble_black_galaxy_vase_inner")
                .texture("particle", "caf:block/palettes/stone_types/natural/marble_black_galaxy_0");
        simpleItem(ALMOND_SAPLING.get().asItem(), "block", "", "");
        blockItem(UNBAKED_CLAY.get(), "item");
        blockItem(KITCHEN_TABLE.get(), "kitchen");
        blockItem(KITCHEN_TABLE_INNER.get(), "kitchen");
        blockItem(KITCHEN_TABLE_OUTER.get(), "kitchen");
        blockItem(FERMENTATION_BARREL.get(), "item");
        blockItem(SCALES.get(), "kitchen_scales", "item");
        blockItem(OAK_CUTTING_BOARD.get(), "cutting_board");
        blockItem(SPRUCE_CUTTING_BOARD.get(), "cutting_board");
        blockItem(BIRCH_CUTTING_BOARD.get(), "cutting_board");
        blockItem(JUNGLE_CUTTING_BOARD.get(), "cutting_board");
        blockItem(ACACIA_CUTTING_BOARD.get(), "cutting_board");
        blockItem(DARK_OAK_CUTTING_BOARD.get(), "cutting_board");
        blockItem(CRIMSON_CUTTING_BOARD.get(), "cutting_board");
        blockItem(WARPED_CUTTING_BOARD.get(), "cutting_board");
        blockItem(ALMOND_CUTTING_BOARD.get(), "cutting_board");
    }

    private void sequencedAssemblyItem(Item item) {
        sequencedAssemblyItem(item, "", 0, 0, 0, 0);
    }

    private void sequencedAssemblyItem(Item item, String textureFolder) {
        sequencedAssemblyItem(item, textureFolder, 0, 0, 0, 0);
    }

    private void sequencedAssemblyItem(Item item, String textureFolder, int progressStep) {
        sequencedAssemblyItem(item, textureFolder, progressStep, 0, 0, 0);
    }

    private void sequencedAssemblyItem(Item item, String textureFolder, int progressStep1, int progressStep2) {
        sequencedAssemblyItem(item, textureFolder, progressStep1, progressStep2, 0, 0);
    }

    private void sequencedAssemblyItem(Item item, String textureFolder, int progressStep1, int progressStep2, int progressStep3) {
        sequencedAssemblyItem(item, textureFolder, progressStep1, progressStep2, progressStep3, 0);
    }

    private void sequencedAssemblyItem(Item item, String textureFolder, int progressStep1, int progressStep2, int progressStep3, int progressStep4) {
        var baseId = item.getRegistryName().getPath();
        String folder = !textureFolder.isEmpty() ? textureFolder + "/" : "";

        if (progressStep1 == 0 && progressStep2 == 0 && progressStep3 == 0 && progressStep4 == 0)
            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/" + folder + baseId));

        else if (progressStep1 != 0 && progressStep2 == 0 && progressStep3 == 0 && progressStep4 == 0) {
            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_1"), "item/" + folder, item.getRegistryName().getPath() + "_1");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/" + folder + baseId + "_0"))
                    // 2nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep1)
                    .model(model1)
                    .end();
        } else if (progressStep2 != 0 && progressStep3 == 0 && progressStep4 == 0) {
            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_1"), "item/" + folder, item.getRegistryName().getPath() + "_1");
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_2"), "item/" + folder, item.getRegistryName().getPath() + "_2");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/" + folder + baseId + "_0"))
                    // 2nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep1)
                    .model(model1)
                    .end()
                    // 3nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep2)
                    .model(model2)
                    .end();
        } else if (progressStep3 != 0 && progressStep4 == 0) {
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_2"), "item/" + folder, item.getRegistryName().getPath() + "_2");
            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_1"), "item/" + folder, item.getRegistryName().getPath() + "_1");
            var model3 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_3"), "item/" + folder, item.getRegistryName().getPath() + "_3");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/" + folder + baseId + "_0"))
                    // 2nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep1)
                    .model(model1)
                    .end()
                    // 3nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep2)
                    .model(model2)
                    .end()
                    // 4nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep3)
                    .model(model3)
                    .end();
        } else {
            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_1"), "item/" + folder, item.getRegistryName().getPath() + "_1");
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_2"), "item/" + folder, item.getRegistryName().getPath() + "_2");
            var model3 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_3"), "item/" + folder, item.getRegistryName().getPath() + "_3");
            var model4 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_4"), "item/" + folder, item.getRegistryName().getPath() + "_4");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/" + folder + baseId + "_0"))
                    // 2nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep1)
                    .model(model1)
                    .end()
                    // 3nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep2)
                    .model(model2)
                    .end()
                    // 4nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep3)
                    .model(model3)
                    .end()
                    // 5nd incomplete stage
                    .override()
                    .predicate(ModItemModelsProperties.SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID, progressStep4)
                    .model(model4)
                    .end();
        }
    }

    private void minecraftIngotItem(Item item, int deg1, int deg2) {
        var baseId = item.getRegistryName().getPath();

        var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_red_hot"), "item/metals/hot/", "ingot_red_hot");
        var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_white_hot"), "item/metals/hot/", "ingot_white_hot");

        withExistingParent(baseId, "item/generated")
                .texture("layer0", new ResourceLocation("item/" + baseId))
                // 2nd degree stage
                .override()
                .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg1)
                .model(model1)
                .end()
                // 3rd degree stage
                .override()
                .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg2)
                .model(model2)
                .end();
    }

    private void minecraftIngotItem(Item item) {
        minecraftIngotItem(item, 550, 1300);
    }

    private void ingotItem(Item item, String type, int deg1, int deg2) {
        if (type.equals("default") || type.isEmpty()){
            var baseId = item.getRegistryName().getPath();

            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_red_hot"), "item/metals/hot/", "ingot_red_hot");
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_white_hot"), "item/metals/hot/", "ingot_white_hot");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/metals/" + baseId))
                    // 2nd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg1)
                    .model(model1)
                    .end()
                    // 3rd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg2)
                    .model(model2)
                    .end();

        } else if (type.equals("brass")) {
            var baseId = item.getRegistryName().getPath();

            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_red_hot"), "item/metals/hot/", "brass_ingot_red_hot");
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_white_hot"), "item/metals/hot/", "brass_ingot_white_hot");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/metals/" + baseId))
                    // 2nd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg1)
                    .model(model1)
                    .end()
                    // 3rd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg2)
                    .model(model2)
                    .end();
        } else if (type.equals("zinc")) {
            var baseId = item.getRegistryName().getPath();

            var model1 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_red_hot"), "item/metals/hot/", "zinc_ingot_red_hot");
            var model2 = simpleItem(new ResourceLocation(MOD_ID, baseId + "_white_hot"), "item/metals/hot/", "zinc_ingot_white_hot");

            withExistingParent(baseId, "item/generated")
                    .texture("layer0", new ResourceLocation(MOD_ID, "item/metals/" + baseId))
                    // 2nd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg1)
                    .model(model1)
                    .end()
                    // 3rd degree stage
                    .override()
                    .predicate(ModItemModelsProperties.DEGREE_PREDICATE_ID, deg2)
                    .model(model2)
                    .end();
        }
    }

    private void ingotItem(Item item, int deg1, int deg2){
        ingotItem(item, "default", deg1, deg2);
    }

    private void ingotItem(Item item, String type) {
        ingotItem(item, type, 550, 1300);
    }

    private void ingotItem(Item item) {
        ingotItem(item, "default", 550, 1300);
    }

    private ItemModelBuilder simpleItem(ResourceLocation id, String path, String texture){
        return withExistingParent(id.getPath(), new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, path + texture));
    }

    private ItemModelBuilder simpleItem(Item item, String texturePath, String path, String textureName){
        if (!path.isEmpty() && !textureName.isEmpty()){
            return withExistingParent(item.getRegistryName().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(MOD_ID, texturePath + "/" + path + "/" + textureName));
        } else if (!path.isEmpty() && textureName.isEmpty()){
            return withExistingParent(item.getRegistryName().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(MOD_ID, texturePath + "/" + path + "/" + item.getRegistryName().getPath()));
        } else if (path.isEmpty() && !textureName.isEmpty()){
            return withExistingParent(item.getRegistryName().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(MOD_ID, texturePath + "/" + textureName));
        } else {
            return withExistingParent(item.getRegistryName().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(MOD_ID, texturePath + "/" + item.getRegistryName().getPath()));
        }
    }

    private ItemModelBuilder minecraftItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder simpleItem(Item item, String path, String textureName){
        String p = path.isEmpty() ? "" : path + "/";
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/" + p + textureName));
    }
    private ItemModelBuilder simpleItem(Item item, String path){
        return simpleItem(item, path, item.getRegistryName().getPath());
    }
    private ItemModelBuilder simpleItem(Item item){
        return simpleItem(item, "", item.getRegistryName().getPath());
    }

    private ItemModelBuilder minecraftMetalItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder metalItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/metals/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder minecraftHandheldItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation("item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item, String path){
        String p = path.isEmpty() ? "" : path + "/";
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/tools/" + p + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder handheldItem(Item item){
        return handheldItem(item, "");
    }
    private ItemModelBuilder hammerItem(Item item, String path){
        String p = path.isEmpty() ? "" : path + "/";
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "custom/item/tool/hammer")).texture("material",
                new ResourceLocation(MOD_ID, "item/tools/" + p + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder hammerItem(Item item){
        return hammerItem(item, "");
    }

    private ItemModelBuilder blockItem(Block block, String path, String modelName){
        if (!path.isEmpty()) {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "block/" + path + "/" + modelName));
        } else {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "block/" + modelName));
        }
    }

    private ItemModelBuilder customBlockItem(Block block, String path, String modelName){
        if (!path.isEmpty()) {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "custom/" + path + "/" + modelName));
        } else {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "custom/" + modelName));
        }
    }

    private ItemModelBuilder blockItemModel(Block block, Block terrace, String modelName) {
        return withExistingParent(block.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "custom/block/terrace/" + modelName))
                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath()));

    }

    private ItemModelBuilder blockItemModel(Block block, String modelName){
        return blockItemModel(block, block, modelName);
    }

    private ItemModelBuilder blockItem(Block block, String path){
        return withExistingParent(block.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "block/" + path + "/" + block.getRegistryName().getPath()));
    }
}
