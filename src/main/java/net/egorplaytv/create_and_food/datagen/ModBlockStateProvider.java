package net.egorplaytv.create_and_food.datagen;

import net.egorplaytv.create_and_food.block.custom.*;
import net.egorplaytv.create_and_food.block.custom.lanterns.LanternBlock;
import net.egorplaytv.create_and_food.block.praperties.LanternAttachType;
import net.egorplaytv.create_and_food.block.praperties.TerraceAttachType;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;
import static net.egorplaytv.create_and_food.block.ModBlocks.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        chainBlock(TORN_SOUL_CHAIN.get(), "block/lantern");
        chainBlock(STEEL_CHAIN.get(), "block/lantern");
        lanternBlock(TORN_SOUL_LANTERN.get(), "block/lantern");
        lanternBlock(GLOWING_BRASS_COPPER_LANTERN.get(), "block/lantern");
        lanternBlock(GLOWING_BRASS_STEEL_LANTERN.get(), "block/lantern");
        minecraftLanternBlock(LANTERN.get(), "block/lantern");
        minecraftLanternBlock(SOUL_LANTERN.get(), "block/lantern");
        signBlock((StandingSignBlock) ALMOND_SIGN.get(), (WallSignBlock) ALMOND_WALL_SIGN.get(),
                blockTexture(ALMOND_PLANKS.get()));
        doorBlock(ALMOND_DOOR.get(), "block/doors", "almond_door_bottom", "almond_door_top");
        trapdoorBlock((TrapDoorBlock) ALMOND_TRAPDOOR.get(), blockTexture(ALMOND_TRAPDOOR.get()), true);
        buttonBlock((ButtonBlock) ALMOND_BUTTON.get(), blockTexture(ALMOND_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) ALMOND_PRESSURE_PLATE.get(), blockTexture(ALMOND_PLANKS.get()));
        fenceBlock((FenceBlock) ALMOND_FENCE.get(), blockTexture(ALMOND_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ALMOND_FENCE_GATE.get(), blockTexture(ALMOND_PLANKS.get()));
        logBlock((RotatedPillarBlock) ALMOND_LOG.get());
        axisBlock((RotatedPillarBlock) STRIPPED_ALMOND_LOG.get(), new ResourceLocation(MOD_ID, "block/stripped_almond_log"),
                new ResourceLocation(MOD_ID, "block/stripped_almond_log_top"));
        axisBlock((RotatedPillarBlock) ALMOND_WOOD.get(), blockTexture(ALMOND_LOG.get()), blockTexture(ALMOND_LOG.get()));
        axisBlock((RotatedPillarBlock) STRIPPED_ALMOND_WOOD.get(), new ResourceLocation(MOD_ID, "block/stripped_almond_log"),
                new ResourceLocation(MOD_ID, "block/stripped_almond_log_top"));
        getVariantBuilder(ALMOND_PLANKS.get()).partialState().setModels(new ConfiguredModel(models().cubeAll(ALMOND_PLANKS.get()
                    .getRegistryName().getPath() + "_0", new ResourceLocation(MOD_ID, "block/planks/" + ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_0"))), new ConfiguredModel(models().cubeAll(ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_1", new ResourceLocation(MOD_ID, "block/planks/" + ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_1"))), new ConfiguredModel(models().cubeAll(ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_2", new ResourceLocation(MOD_ID, "block/planks/" + ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_2"))), new ConfiguredModel(models().cubeAll(ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_3", new ResourceLocation(MOD_ID, "block/planks/" + ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_3"))), new ConfiguredModel(models().cubeAll(ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_4", new ResourceLocation(MOD_ID, "block/planks/" + ALMOND_PLANKS.get()
                .getRegistryName().getPath() + "_4"))));
        stairsBlock((StairBlock) ALMOND_STAIRS.get(), blockTexture(ALMOND_PLANKS.get()));
        slabBlock((SlabBlock) ALMOND_SLAB.get(), new ResourceLocation(MOD_ID, "block/planks/almond_planks"),
                blockTexture(ALMOND_PLANKS.get()));
        getVariantBuilder(ALMOND_LEAVES.get()).partialState().setModels(new ConfiguredModel(models().cubeAll(ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_0", new ResourceLocation(MOD_ID, "block/leaves/" + ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_0"))), new ConfiguredModel(models().cubeAll(ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_1", new ResourceLocation(MOD_ID, "block/leaves/" + ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_1"))), new ConfiguredModel(models().cubeAll(ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_2", new ResourceLocation(MOD_ID, "block/leaves/" + ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_2"))), new ConfiguredModel(models().cubeAll(ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_3", new ResourceLocation(MOD_ID, "block/leaves/" + ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_3"))), new ConfiguredModel(models().cubeAll(ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_4", new ResourceLocation(MOD_ID, "block/leaves/" + ALMOND_LEAVES.get()
                .getRegistryName().getPath() + "_4"))));
        customBlock(PASTRY_TABLE.get(), "block/custom");
        customBlock(FURNITURE_CUTTER.get(), "block/custom");
        furnaceBlock(MARBLE_BLAST_FURNACE.get(), "block/furnace");
        barrelBlock(ACACIA_BARREL.get(), "block/barrels");
        barrelBlock(ALMOND_BARREL.get(), "block/barrels");
        barrelBlock(BIRCH_BARREL.get(), "block/barrels");
        barrelBlock(CRIMSON_BARREL.get(), "block/barrels");
        barrelBlock(DARK_OAK_BARREL.get(), "block/barrels");
        barrelBlock(JUNGLE_BARREL.get(), "block/barrels");
        barrelBlock(OAK_BARREL.get(), "block/barrels");
        barrelBlock(SPRUCE_BARREL.get(), "block/barrels");
        barrelBlock(WARPED_BARREL.get(), "block/barrels");
        getVariantBuilder(TERMINAL.get()).forAllStates(state ->{
            Direction direction = state.getValue(TerminalBlock.FACING);
            if (direction.equals(Direction.NORTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(TERMINAL.get().getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, "block/terminal/" + TERMINAL.get().getRegistryName().getPath() + "_block")))
                        .build();
            } else if (direction.equals(Direction.EAST)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(TERMINAL.get().getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, "block/terminal/" + TERMINAL.get().getRegistryName().getPath() + "_block")))
                        .rotationY(90)
                        .build();
            } else if (direction.equals(Direction.SOUTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(TERMINAL.get().getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, "block/terminal/" + TERMINAL.get().getRegistryName().getPath() + "_block")))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(TERMINAL.get().getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, "block/terminal/" + TERMINAL.get().getRegistryName().getPath() + "_block")))
                        .rotationY(270)
                        .build();
            }
        });
        customBlock(COBBLED_MARBLE.get(), "block/marbles");
        customBlock(COBBLED_MARBLE_BLACK_GALAXY.get(), "block/marbles");
        customBlock(COBBLED_MARBLE_PERLIN_PINK.get(), "block/marbles");
        getVariantBuilder(FIRECLAY_BRICKS.get()).partialState().setModels(new ConfiguredModel(models().cubeAll(FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_0",
                        new ResourceLocation(MOD_ID, "block/" + FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_0"))),
                new ConfiguredModel(models().cubeAll(FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_1",
                        new ResourceLocation(MOD_ID, "block/" + FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_1"))),
                new ConfiguredModel(models().cubeAll(FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_2",
                        new ResourceLocation(MOD_ID, "block/" + FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_2"))),
                new ConfiguredModel(models().cubeAll(FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_3",
                        new ResourceLocation(MOD_ID, "block/" + FIRECLAY_BRICKS.get().getRegistryName().getPath() + "_3"))));
        terraceBlock(ACACIA_TERRACE.get(), "block/terrace");
        terraceStairsBlock(ACACIA_TERRACE_STAIRS.get(), ACACIA_TERRACE.get(), "block/terrace");
        terraceBlock(ALMOND_TERRACE.get(), "block/terrace");
        terraceStairsBlock(ALMOND_TERRACE_STAIRS.get(), ALMOND_TERRACE.get(), "block/terrace");
        terraceBlock(BIRCH_TERRACE.get(), "block/terrace");
        terraceStairsBlock(BIRCH_TERRACE_STAIRS.get(), BIRCH_TERRACE.get(), "block/terrace");
        terraceBlock(CRIMSON_TERRACE.get(), "block/terrace");
        terraceStairsBlock(CRIMSON_TERRACE_STAIRS.get(), CRIMSON_TERRACE.get(), "block/terrace");
        terraceBlock(DARK_OAK_TERRACE.get(), "block/terrace");
        terraceStairsBlock(DARK_OAK_TERRACE_STAIRS.get(), DARK_OAK_TERRACE.get(), "block/terrace");
        terraceBlock(JUNGLE_TERRACE.get(), "block/terrace");
        terraceStairsBlock(JUNGLE_TERRACE_STAIRS.get(), JUNGLE_TERRACE.get(), "block/terrace");
        terraceBlock(OAK_TERRACE.get(), "block/terrace");
        terraceStairsBlock(OAK_TERRACE_STAIRS.get(), OAK_TERRACE.get(), "block/terrace");
        terraceBlock(SPRUCE_TERRACE.get(), "block/terrace");
        terraceStairsBlock(SPRUCE_TERRACE_STAIRS.get(), SPRUCE_TERRACE.get(), "block/terrace");
        terraceBlock(WARPED_TERRACE.get(), "block/terrace");
        terraceStairsBlock(WARPED_TERRACE_STAIRS.get(), WARPED_TERRACE.get(), "block/terrace");
        customBlock(FRAMED_CALCITE.get(), "block/wall");
        getVariantBuilder(STEEL_LAMP_BLOCK.get()).forAllStates(state -> {
            if (state.getValue(RedstoneLampBlock.LIT).equals(true)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(STEEL_LAMP_BLOCK.get().getRegistryName().getPath() + "_on",
                                new ResourceLocation(MOD_ID, "block/lamps/" + STEEL_LAMP_BLOCK.get().getRegistryName().getPath() + "_on")))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(STEEL_LAMP_BLOCK.get().getRegistryName().getPath() + "_off",
                                new ResourceLocation(MOD_ID, "block/lamps/" + STEEL_LAMP_BLOCK.get().getRegistryName().getPath() + "_off")))
                        .build();
            }
        });
        simpleBlock(RUBY_ORE.get(), "block/ruby");
        simpleBlock(DEEPSLATE_RUBY_ORE.get(), "block/ruby");
        simpleBlock(RAW_RUBY_BLOCK.get(), "block/ruby", "rough_ruby_block");
        simpleBlock(RUBY_BLOCK.get(), "block/ruby");
        oreVariantBlock(STONE_TANTALUM_ORE.get());
        oreVariantBlock(DEEPSLATE_TANTALUM_ORE.get());
        oreVariantBlock(TANTALUM_ORE.get());
        oreVariantBlock(BLACKSTONE_TANTALUM_ORE.get());
        simpleBlock(RAW_TANTALUM_BLOCK.get());
        oreVariantBlock(TUNGSTEN_ORE.get());
        oreVariantBlock(STONE_TUNGSTEN_ORE.get());
        oreVariantBlock(DEEPSLATE_TUNGSTEN_ORE.get());
        simpleBlock(RAW_TUNGSTEN_BLOCK.get());
        sumpBlock(FARMLAND_SUMP_SAND.get(), "block/farmlands");
        sumpBlock(FARMLAND_SUMP_RED_SAND.get(), "block/farmlands");
        sumpBlock(FARMLAND_SUMP_RICH_SOIL.get(), "block/farmlands");
        sumpBlock(FARMLAND_SUMP_DIRT.get(), "block/farmlands");
        simpleBlock(FERTILIZED_SAND.get(), "block/farmlands");
        farmlandBlock(SAND_FARMLAND.get(), "block/farmlands");
        simpleBlock(FERTILIZED_RED_SAND.get(), "block/farmlands");
        farmlandBlock(RED_SAND_FARMLAND.get(), "block/farmlands");
        farmlandBlock(FLOODED_FARMLAND.get(), "block/farmlands");
        farmlandBlock(FLOODED_RICH_SOIL_FARMLAND.get(), "block/farmlands");
        nixieVaseBlock(NIXIE_VASE.get(), "custom/vases", "vase_nixie_fluid",
                "vase_nixie_fluid_open", "vase/marble_vase_side", "vase/marble_vase_inner",
                "palettes/stone_types/natural/marble_0");
        nixieVaseBlock(NIXIE_VASE_PERLIN_PINK.get(), "custom/vases", "vase_nixie_fluid",
                "vase_nixie_fluid_open", "vase/marble_perlin_pink_vase_side", "vase/marble_perlin_pink_vase_inner",
                "palettes/stone_types/natural/marble_perlin_pink_0");
        nixieVaseBlock(NIXIE_VASE_BLACK_GALAXY.get(), "custom/vases", "vase_nixie_fluid",
                "vase_nixie_fluid_open", "vase/marble_black_galaxy_vase_side", "vase/marble_black_galaxy_vase_inner",
                "palettes/stone_types/natural/marble_black_galaxy_0");
        simpleBlock(ALMOND_SAPLING.get(), models().cross(ALMOND_SAPLING.get().getRegistryName().getPath(),
                blockTexture(ALMOND_SAPLING.get())));
        cropPlantBlock((RyePlantBlock) RYE_PLANT.get(), "rye_stage", "rye_stage");
        floodedCropBlock((FloodedCropBlock) RICE_PLANT.get(), "rice_plant_stage");
        riceBlock(RICE_CROP.get(), RiceBlock.AGE, RiceBlock.SUPPORTING);
        ricePaniclesBlock(RICE_CROP_PANICLES.get(), RicePaniclesBlock.RICE_AGE);
        simpleBlock(UNBAKED_CLAY.get(), "block/baked_clay");
        rotatingBlock(KITCHEN_TABLE.get(), "block/kitchen");
        rotatingBlock(KITCHEN_TABLE_INNER.get(), "block/kitchen");
        rotatingBlock(KITCHEN_TABLE_OUTER.get(), "block/kitchen");
        customBlock(FERMENTATION_BARREL.get(), "block/item");
        rotatingBlock(SCALES.get(), "block/kitchen_scales", "block");
        rotatingBlock(OAK_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(SPRUCE_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(BIRCH_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(JUNGLE_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(ACACIA_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(DARK_OAK_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(CRIMSON_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(WARPED_CUTTING_BOARD.get(), "block/cutting_board");
        rotatingBlock(ALMOND_CUTTING_BOARD.get(), "block/cutting_board");


    }

    public void ricePaniclesBlock(Block block, IntegerProperty ageProperty) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            int ageSuffix = (Integer)state.getValue(ageProperty);
            String var10000 = block.getRegistryName().getPath();
            String stageName = var10000 + "_stage" + ageSuffix;
            return ConfiguredModel.builder().modelFile(models().cross(stageName,
                    new ResourceLocation(MOD_ID, "block/" + stageName))).build();
        });
    }

    public void riceBlock(Block block, IntegerProperty ageProperty, BooleanProperty isSupporting){
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            int age = state.getValue(ageProperty);
            String stageOrSupporting = state.getValue(isSupporting) && age == 3 ? "_supporting" : "_stage" + age;
            String var10000 = block.getRegistryName().getPath();
            String stageName = var10000 + stageOrSupporting;
            return ConfiguredModel.builder().modelFile(models().cross(stageName,
                    new ResourceLocation(MOD_ID, "block/" + stageName))).build();
        });
    }

    public void simpleBlock(Block block, String pathTexture, String textureName){
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models()
                .withExistingParent(block.getRegistryName().getPath(), "block/cube_all")
                .texture("all", new ResourceLocation(MOD_ID, pathTexture + "/" + textureName))));
    }

    public void simpleBlock(Block block, String pathTexture){
        simpleBlock(block, pathTexture, block.getRegistryName().getPath());
    }

    public void rotatingBlock(Block block, String pathModel, String modelName){
        getVariantBuilder(block).forAllStates(state ->{
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            if (direction.equals(Direction.NORTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName)))
                        .build();
            } else if (direction.equals(Direction.EAST)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName)))
                        .rotationY(90)
                        .build();
            } else if (direction.equals(Direction.SOUTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName)))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName)))
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void rotatingBlock(Block block, String pathModel){
        rotatingBlock(block, pathModel, block.getRegistryName().getPath());
    }

    public void nixieVaseBlock(Block block, String pathModel, String closeModelName, String openModelName, String outside, String inside, String particle){
        getVariantBuilder(block).forAllStates(state ->{
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            boolean isOpen = state.getValue(VaseBlock.isOpen);
            if (direction.equals(Direction.NORTH) && !isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_close",
                                new ResourceLocation(MOD_ID, pathModel + "/" + closeModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .build();
            } else if (direction.equals(Direction.EAST) && !isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_close",
                                new ResourceLocation(MOD_ID, pathModel + "/" + closeModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(90)
                        .build();
            } else if (direction.equals(Direction.SOUTH) && !isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_close",
                                new ResourceLocation(MOD_ID, pathModel + "/" + closeModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(180)
                        .build();
            } else if (direction.equals(Direction.WEST) && !isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_close",
                                new ResourceLocation(MOD_ID, pathModel + "/" + closeModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(270)
                        .build();
            } else if (direction.equals(Direction.NORTH) && isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + openModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .build();
            } else if (direction.equals(Direction.EAST) && isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + openModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(90)
                        .build();
            } else if (direction.equals(Direction.SOUTH) && isOpen){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + openModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + openModelName))
                                .texture("outside", "create_and_food:block/" + outside)
                                .texture("inside", "create_and_food:block/" + inside)
                                .texture("particle", "create_and_food:block/" + particle))
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void farmlandBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state ->{
            int moisture = state.getValue(FarmBlock.MOISTURE);
            if (moisture == 7){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_moist",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_moist")))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .build();
            }
        });
    }

    public void sumpBlock(Block block, String pathModel, String modelName){
        getVariantBuilder(block).forAllStates(state -> {
            SlabType type = state.getValue(SumpBlock.TYPE);
            if (type.equals(SlabType.TOP)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(modelName,
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName)))
                        .build();
            } else if (type.equals(SlabType.BOTTOM)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(modelName + "_bottom",
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName + "_bottom")))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(modelName + "_double",
                                new ResourceLocation(MOD_ID, pathModel + "/" + modelName + "_double")))
                        .build();
            }
        });
    }

    public void sumpBlock(Block block, String pathModel){
        sumpBlock(block, pathModel, block.getRegistryName().getPath());
    }

    public void oreVariantBlock(Block block, String pathModels, String modelsName){
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().cubeAll(modelsName + "_0",
                new ResourceLocation(MOD_ID, pathModels + "/" + modelsName + "_0"))),
                new ConfiguredModel(models().cubeAll(modelsName + "_1",
                        new ResourceLocation(MOD_ID, pathModels + "/" + modelsName + "_1"))),
                new ConfiguredModel(models().cubeAll(modelsName + "_2",
                        new ResourceLocation(MOD_ID, pathModels + "/" + modelsName + "_2"))),
                new ConfiguredModel(models().cubeAll(modelsName + "_3",
                        new ResourceLocation(MOD_ID, pathModels + "/" + modelsName + "_3"))));
    }
    public void oreVariantBlock(Block block, String modelsName){
        oreVariantBlock(block, "block/ores", modelsName);
    }

    public void oreVariantBlock(Block block){
        oreVariantBlock(block, "block/ores", block.getRegistryName().getPath());
    }

    public void chainBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);

            if (axis.equals(Direction.Axis.X)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .rotationY(90)
                        .build();
            } else if (axis.equals(Direction.Axis.Y)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .build();
            }
        });
    }

    public void lanternBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            LanternAttachType type = state.getValue(LanternBlock.ATTACHMENT);

            if (type.equals(LanternAttachType.FLOOR)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(LanternAttachType.HANGING)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_hanging",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_hanging")))
                        .build();
            } else if (type.equals(LanternAttachType.NORTH)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .build();
            } else if (type.equals(LanternAttachType.EAST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(90)
                        .build();
            } else if (type.equals(LanternAttachType.SOUTH)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void minecraftLanternBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            LanternAttachType type = state.getValue(LanternBlock.ATTACHMENT);

            if (type.equals(LanternAttachType.FLOOR)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation("block/" + block.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(LanternAttachType.HANGING)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_hanging",
                                new ResourceLocation("block/" + block.getRegistryName().getPath() + "_hanging")))
                        .build();
            } else if (type.equals(LanternAttachType.NORTH)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .build();
            } else if (type.equals(LanternAttachType.EAST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(90)
                        .build();
            } else if (type.equals(LanternAttachType.SOUTH)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on_wall",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on_wall")))
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void terraceBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            TerraceAttachType type = state.getValue(TerraceBlock.ATTACHMENT);
            if (type.equals(TerraceAttachType.SINGLE)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_block",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_block"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + block.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.LOW)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_bottom",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_bottom"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + block.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.MIDDLE)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_medium",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_medium"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + block.getRegistryName().getPath())))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_top",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_top"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + block.getRegistryName().getPath())))
                        .build();
            }
        });
    }

    public void terraceStairsBlock(Block block, Block terrace, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            TerraceAttachType type = state.getValue(TerraceStairsBlock.ATTACHMENT);
            Direction direction = state.getValue(TerraceStairsBlock.FACING);
            if (type.equals(TerraceAttachType.SINGLE) && direction.equals(Direction.NORTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_north",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_north"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.SINGLE) && direction.equals(Direction.SOUTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_south",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_south"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.SINGLE) && direction.equals(Direction.WEST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_west",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_west"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.SINGLE) && direction.equals(Direction.EAST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_east",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_east"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.UP) && direction.equals(Direction.NORTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_top_north",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_top_north"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.UP) && direction.equals(Direction.SOUTH)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_top_south",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_top_south"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.UP) && direction.equals(Direction.WEST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_top_west",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_top_west"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else if (type.equals(TerraceAttachType.UP) && direction.equals(Direction.EAST)) {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_top_east",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_stairs_top_east"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            }  else if (type.equals(TerraceAttachType.LOW)){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(terrace.getRegistryName().getPath() + "_bottom",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_bottom"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(terrace.getRegistryName().getPath() + "_medium",
                                new ResourceLocation(MOD_ID, pathModel + "/oak_terrace_medium"))
                                .texture("terrace", new ResourceLocation(MOD_ID, "block/" + terrace.getRegistryName().getPath())))
                        .build();
            }
        });
    }

    public void marbleBlock(Block block){
        marbleBlock(block, "marble");
    }

    public void marbleBlock(Block block, String type){
        marbleBlock(block, type, block.getRegistryName().getPath());
    }

    public void marbleBlock(Block block, String type, String modelName) {
        if (type.equals("marble")) {
            getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models()
                            .withExistingParent(block.getRegistryName().getPath() + "_0", new ResourceLocation(MOD_ID, "block/marbles/" + modelName + "_0"))),
                    new ConfiguredModel(models().withExistingParent(block.getRegistryName().getPath() + "_1", new ResourceLocation(MOD_ID, "block/marbles/" + modelName + "_1"))),
                    new ConfiguredModel(models().withExistingParent(block.getRegistryName().getPath() + "_2", new ResourceLocation(MOD_ID, "block/marbles/" + modelName + "_2"))),
                    new ConfiguredModel(models().withExistingParent(block.getRegistryName().getPath() + "_3", new ResourceLocation(MOD_ID, "block/marbles/" + modelName + "_3"))));
        } else if (type.equals("cobbled")) {
            getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models()
                            .withExistingParent(block.getRegistryName().getPath(), new ResourceLocation(MOD_ID, "block/marbles/" + modelName))));
        }
    }

    public void doorBlock(Block block, String folder, String bottom, String top){
        doorBlock((DoorBlock) block, models().withExistingParent(bottom, new ResourceLocation(MOD_ID, folder + "/" + bottom)),
                models().withExistingParent(bottom + "_hinge", new ResourceLocation(MOD_ID, folder + "/" + bottom + "_hinge")),
                models().withExistingParent(top, new ResourceLocation(MOD_ID, folder + "/" + top)),
                models().withExistingParent(top + "_hinge", new ResourceLocation(MOD_ID, folder + "/" + top + "_hinge")));
    }

    public void barrelBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            boolean open = state.getValue(BarrelBlock.OPEN);
            Direction direction = state.getValue(BarrelBlock.FACING);

            if (open == false && direction == Direction.DOWN){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(180)
                        .build();
            } else if (open == true && direction == Direction.DOWN){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .rotationX(180)
                        .build();
            } else if (open == false && direction == Direction.EAST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .rotationY(90)
                        .build();
            } else if (open == true && direction == Direction.EAST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .rotationX(90)
                        .rotationY(90)
                        .build();
            } else if (open == false && direction == Direction.NORTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .build();
            } else if (open == true && direction == Direction.NORTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .rotationX(90)
                        .build();
            } else if (open == false && direction == Direction.SOUTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .rotationY(180)
                        .build();
            } else if (open == true && direction == Direction.SOUTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .rotationX(90)
                        .rotationY(180)
                        .build();
            } else if (open == false && direction == Direction.UP){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .build();
            } else if (open == true && direction == Direction.UP){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .build();
            } else if (open == false && direction == Direction.WEST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationX(90)
                        .rotationY(270)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_open",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_open")))
                        .rotationX(90)
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void furnaceBlock(Block block, String pathModel){
        getVariantBuilder(block).forAllStates(state -> {
            boolean lit = state.getValue(MarbleBlastFurnaceBlock.LIT);
            Direction direction = state.getValue(MarbleBlastFurnaceBlock.FACING);

            if (lit == true && direction == Direction.NORTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on")))
                        .build();
            } else if (lit == true && direction == Direction.EAST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on")))
                        .rotationY(90)
                        .build();
            } else if (lit == true && direction == Direction.SOUTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on")))
                        .rotationY(180)
                        .build();
            } else if (lit == true && direction == Direction.WEST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath() + "_on",
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath() + "_on")))
                        .rotationY(270)
                        .build();
            } else if (lit == false && direction == Direction.NORTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .build();
            } else if (lit == false && direction == Direction.EAST){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationY(90)
                        .build();
            } else if (lit == false && direction == Direction.SOUTH){
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationY(180)
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(block.getRegistryName().getPath(),
                                new ResourceLocation(MOD_ID, pathModel + "/" + block.getRegistryName().getPath())))
                        .rotationY(270)
                        .build();
            }
        });
    }

    public void customBlock(Block block, String pathModel){
        customBlock(block, pathModel, block.getRegistryName().getPath());
    }
    public void customBlock(Block block, String pathModel, String modelName){
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models()
                .withExistingParent(block.getRegistryName().getPath(),
                        new ResourceLocation(MOD_ID, pathModel + "/" + modelName))));
    }

    public void floodedCropBlock(FloodedCropBlock block, String modelName){
        Function<BlockState, ConfiguredModel[]> function = state -> statesFloodedCropBlock(state, block, modelName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] statesFloodedCropBlock(BlockState state, CropBlock block, String modelName){
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().withExistingParent(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(MOD_ID, "block/flooded/" + modelName + state.getValue(block.getAgeProperty()))));

        return models;
    }


    public void cropPlantBlock(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> statesCropPlantBlock(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] statesCropPlantBlock(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(MOD_ID, "block/rye/" + textureName + "_" + state.getValue(block.getAgeProperty()))));

        return models;
    }
}
