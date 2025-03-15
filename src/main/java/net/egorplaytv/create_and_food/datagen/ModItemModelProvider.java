package net.egorplaytv.create_and_food.datagen;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;
import static net.egorplaytv.create_and_food.block.ModBlocks.*;
import static net.egorplaytv.create_and_food.item.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
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
        simpleItem(GLOW_BERRY_CAKE.get(), "dough");
        simpleItem(RAW_BERRY_CAKE.get(), "dough");
        simpleItem(BERRY_CAKE.get(), "dough");
        simpleItem(RAW_CAKE.get(), "dough");
        simpleItem(RAW_SWEET_ROLL.get(), "dough");
        simpleItem(DOUGH_BASE_WITH_CHOCOLATE.get(), "dough");
        simpleItem(SMALL_DOUGH_BASE_WHiH_HONEY.get(), "dough");
        simpleItem(SMALL_DOUGH_BASE_WHiH_CHOCOLATE.get(), "dough");
        simpleItem(RYE_DOUGH.get(), "dough");
        simpleItem(SMALL_DOUGH.get(), "dough", "small_dough_for_the_base");
        simpleItem(CREAM.get());
        simpleItem(BIZET.get());
        simpleItem(GLAZE.get());
        simpleItem(CUSTARD.get());
        simpleItem(POWDERED_SUGAR.get());
        simpleItem(COCOA_POWDER.get());
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
        simpleMinecraftItem(PUMPKIN_SEEDS.get());
        simpleMinecraftItem(MELON_SEEDS.get());
        simpleItem(APPLE_VINEGAR_BUCKET.get());
        simpleItem(COCOA_OIL_BUCKET.get());
        simpleItem(WHITE_CHOCOLATE_BUCKET.get());
        simpleItem(HONEY_MILK.get());
        simpleItem(ALMOND_NUT.get());
        simpleItem(MACARONI.get());
        simpleItem(PINK_MACARONI.get());
        simpleItem(RED_MACARONI.get());
        simpleItem(WHITE_MACARONI.get());
        simpleItem(EGG_SHELL.get());
        simpleItem(RYE_SEEDS.get());
        simpleItem(RYE.get());
        simpleItem(ROASTED_COCOA_BEANS.get());

        metalItem(STEEL_INGOT.get());
        metalItem(STEEL_NUGGET.get());
        metalItem(STEEL_SHEET.get());
        metalItem(GLOWING_BRASS_INGOT.get());
        metalItem(GLOWING_BRASS_NUGGET.get());
        metalItem(GLOWING_BRASS_SHEET.get());
        metalItem(PIECE_OF_GOLD.get());
        metalItem(ALLOY_SOULS.get());
        metalItem(ALLOY_SOULS_INGOT.get());
        metalItem(ALLOY_SOULS_NUGGET.get());
        metalItem(ALLOY_SOULS_SHEET.get());
        metalItem(INCOMPLETE_NETHERITE_INGOT.get());
        metalItem(NETHER_ALLOY.get());
        simpleItem(STEEL_COIL.get(), "coil");
        simpleItem(COPPER_COIL.get(), "coil");
        handheldItem(INCOMPLETE_TOOL_HANDLE.get());
        handheldItem(TOOL_HANDLE.get());
        handheldItem(TANTALUM_SWORD.get(), "tantalum");
        handheldItem(TANTALUM_PICKAXE.get(), "tantalum");
        handheldItem(TANTALUM_SHOVEL.get(), "tantalum");
        handheldItem(TANTALUM_AXE.get(), "tantalum");
        handheldItem(TANTALUM_HOE.get(), "tantalum");
        hammerItem(TANTALUM_HAMMER.get(), "tantalum");
        handheldItem(TUNGSTEN_SWORD.get(), "tungsten");
        handheldItem(TUNGSTEN_PICKAXE.get(), "tungsten");
        handheldItem(TUNGSTEN_SHOVEL.get(), "tungsten");
        handheldItem(TUNGSTEN_AXE.get(), "tungsten");
        handheldItem(TUNGSTEN_HOE.get(), "tungsten");
        hammerItem(TUNGSTEN_HAMMER.get(), "tungsten");
        handheldItem(STEEL_SWORD.get(), "steel");
        handheldItem(STEEL_PICKAXE.get(), "steel");
        handheldItem(STEEL_SHOVEL.get(), "steel");
        handheldItem(STEEL_AXE.get(), "steel");
        handheldItem(STEEL_HOE.get(), "steel");
        hammerItem(STEEL_HAMMER.get(), "steel");
        hammerItem(KITCHEN_HAMMER.get());
        handheldItem(COPPER_SWORD.get(), "copper");
        handheldItem(COPPER_PICKAXE.get(), "copper");
        handheldItem(COPPER_SHOVEL.get(), "copper");
        handheldItem(COPPER_AXE.get(), "copper");
        handheldItem(COPPER_HOE.get(), "copper");
        simpleItem(INCOMPLETE_COIN.get(), "coin");
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
        simpleItem(RAW_RUBY.get());
        simpleItem(RUBY.get());
        simpleItem(FIRECLAY_BRICK.get());
        simpleItem(FIRECLAY_CLAY_BALL.get());
        simpleItem(INCOMPLETE_MARBLE_BRICK.get());
        simpleItem(MARBLE_BRICK.get());
        simpleItem(INCOMPLETE_MARBLE_BLACK_GALAXY_BRICK.get());
        simpleItem(MARBLE_BLACK_GALAXY_BRICK.get());
        simpleItem(INCOMPLETE_MARBLE_PERLIN_PINK_BRICK.get());
        simpleItem(MARBLE_PERLIN_PINK_BRICK.get());
        metalItem(RAW_TANTALUM.get());
        metalItem(CRASHED_RAW_TANTALUM.get());
        metalItem(TANTALUM_INGOT.get());
        metalItem(TANTALUM_NUGGET.get());
        metalItem(RAW_TUNGSTEN.get());
        metalItem(CRASHED_RAW_TUNGSTEN.get());
        metalItem(TUNGSTEN_INGOT.get());
        metalItem(TUNGSTEN_NUGGET.get());

        handheldItem(IRON_KNIFE.get(), "iron");
        handheldItem(DIAMOND_KNIFE.get(), "diamond");
        handheldItem(NETHERITE_KNIFE.get(), "netherite");
        handheldItem(GOLDEN_KNIFE.get(), "gold");
        handheldItem(STEEL_KNIFE.get(), "steel");
        handheldItem(TANTALUM_KNIFE.get(), "tantalum");
        handheldItem(TUNGSTEN_KNIFE.get(), "tungsten");

        simpleItem(ModItems.TORN_SOUL_CHAIN.get());
        simpleItem(ModItems.STEEL_CHAIN.get());
        simpleItem(ModItems.TORN_SOUL_LANTERN.get());
        simpleItem(ModItems.GLOWING_BRASS_COPPER_LANTERN.get());
        simpleItem(ModItems.GLOWING_BRASS_STEEL_LANTERN.get());
        simpleItem(ModItems.ALMOND_SIGN.get());

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
        blockItem(TABLET.get(), "tablet", "tablet_item");
        blockItem(MARBLE.get(), "marbles", "marble_0");
        blockItem(MARBLE_BLACK_GALAXY.get(), "marbles", "marble_black_galaxy_0");
        blockItem(MARBLE_PERLIN_PINK.get(), "marbles", "marble_perlin_pink_0");
        blockItem(COBBLED_MARBLE.get(), "marbles");
        blockItem(COBBLED_MARBLE_BLACK_GALAXY.get(), "marbles");
        blockItem(COBBLED_MARBLE_PERLIN_PINK.get(), "marbles");
        blockItem(FIRECLAY_BRICKS.get(), "item", "fireclay_bricks_0");
        blockItemModel(ACACIA_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(ACACIA_TERRACE_STAIRS.get(), ACACIA_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(ALMOND_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(ALMOND_TERRACE_STAIRS.get(), ALMOND_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(BIRCH_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(BIRCH_TERRACE_STAIRS.get(), BIRCH_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(CRIMSON_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(CRIMSON_TERRACE_STAIRS.get(), CRIMSON_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(DARK_OAK_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(DARK_OAK_TERRACE_STAIRS.get(), DARK_OAK_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(JUNGLE_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(JUNGLE_TERRACE_STAIRS.get(), JUNGLE_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(OAK_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(OAK_TERRACE_STAIRS.get(), OAK_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(SPRUCE_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(SPRUCE_TERRACE_STAIRS.get(), SPRUCE_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
        blockItemModel(WARPED_TERRACE.get(), "terrace", "oak_terrace_block");
        blockItemModel(WARPED_TERRACE_STAIRS.get(), WARPED_TERRACE.get(), "terrace", "oak_terrace_stairs_west");
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
        blockItem(RAW_TUNGSTEN_BLOCK.get(), "item");
        blockItem(FARMLAND_SUMP_SAND.get(), "farmlands", "farmland_sump_sand_bottom");
        blockItem(FARMLAND_SUMP_RED_SAND.get(), "farmlands", "farmland_sump_red_sand_bottom");
        blockItem(FARMLAND_SUMP_RICH_SOIL.get(), "farmlands", "farmland_sump_rich_soil_bottom");
        blockItem(FARMLAND_SUMP_DIRT.get(), "farmlands", "farmland_sump_dirt_bottom");
        blockItem(FERTILIZED_SAND.get(), "farmlands");
        blockItem(SAND_FARMLAND.get(), "farmlands");
        blockItem(FERTILIZED_RED_SAND.get(), "farmlands");
        blockItem(RED_SAND_FARMLAND.get(), "farmlands");
        blockItem(FERTILIZED_SAND.get(), "farmlands");
        simpleItem(ALMOND_SAPLING.get().asItem(), "block", "", "");
        blockItem(UNBAKED_CLAY.get(), "item");
        blockItem(KITCHEN_TABLE.get(), "kitchen");
        blockItem(KITCHEN_TABLE_INNER.get(), "kitchen");
        blockItem(KITCHEN_TABLE_OUTER.get(), "kitchen");
        blockItem(FERMENTATION_BARREL.get(), "item");
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
    private ItemModelBuilder simpleItem(Item item, String path, String textureName){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/" + path + "/" + textureName));
    }
    private ItemModelBuilder simpleItem(Item item, String path){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/" + path + "/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder simpleItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder metalItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/metals/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder handheldItem(Item item, String path){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/tools/" + path + "/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder handheldItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MOD_ID, "item/tools/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder hammerItem(Item item, String path){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "custom/item/tool/hammer")).texture("material",
                new ResourceLocation(MOD_ID, "item/tools/" + path + "/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder hammerItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "custom/item/tool/hammer")).texture("material",
                new ResourceLocation(MOD_ID, "item/tools/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder simpleMinecraftItem(Item item){
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CreateAndFood.MINECRAFT_ID, "item/" + item.getRegistryName().getPath()));
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

    private ItemModelBuilder blockItemModel(Block block, Block terrace, String path, String modelName){
        if (!path.isEmpty()) {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "block/" + path + "/" + modelName))
                    .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath()));
        } else {
            return withExistingParent(block.getRegistryName().getPath(),
                    new ResourceLocation(MOD_ID, "block/" + modelName))
                    .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath()));
        }
    }

    private ItemModelBuilder blockItemModel(Block block, String path, String modelName){
        return blockItemModel(block, block, path, modelName);
    }

    private ItemModelBuilder blockItem(Block block, String path){
        return withExistingParent(block.getRegistryName().getPath(),
                new ResourceLocation(MOD_ID, "block/" + path + "/" + block.getRegistryName().getPath()));
    }
}
