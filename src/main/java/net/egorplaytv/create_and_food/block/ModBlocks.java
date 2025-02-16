package net.egorplaytv.create_and_food.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassBlock;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.custom.*;
import net.egorplaytv.create_and_food.block.custom.LanternBlock;
import net.egorplaytv.create_and_food.block.custom.berry.*;
import net.egorplaytv.create_and_food.block.custom.connect.*;
import net.egorplaytv.create_and_food.block.entity.ModWoodTypes;
import net.egorplaytv.create_and_food.item.ModCreativeModeTab;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.sound.ModSounds;
import net.egorplaytv.create_and_food.world.feature.tree.AlmondTreeGrower;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.egorplaytv.create_and_food.block.custom.connect.CTBlocks.*;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CreateAndFood.MOD_ID);

//________________________Create and Food: Decorative________________________\\
    public static final RegistryObject<Block> TORN_SOUL_CHAIN = registryBlockWithoutBlockItem("torn_soul_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).noOcclusion()));
    public static final RegistryObject<Block> STEEL_CHAIN = registryBlockWithoutBlockItem("steel_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).noOcclusion()));
    public static final RegistryObject<Block> TORN_SOUL_LANTERN = registryBlockWithoutBlockItem("torn_soul_lantern",
            () -> new TornSoulLantern(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_BRASS_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_BRASS_STEEL_LANTERN = registryBlockWithoutBlockItem("glowing_brass_steel_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion()));


    public static final RegistryObject<Block> ALMOND_WALL_SIGN = registryBlockWithoutBlockItem("almond_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.ALMOND));
    public static final RegistryObject<Block> ALMOND_SIGN = registryBlockWithoutBlockItem("almond_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.ALMOND));
    public static final RegistryObject<Block> ALMOND_DOOR = registryBlock("almond_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .noOcclusion()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_TRAPDOOR = registryBlock("almond_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_BUTTON = registryBlock("almond_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                    .noCollission()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_PRESSURE_PLATE = registryBlock("almond_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
            ), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_FENCE = registryBlock("almond_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_FENCE_GATE = registryBlock("almond_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_LOG = registryBlock("almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> STRIPPED_ALMOND_LOG = registryBlock("stripped_almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_WOOD = registryBlock("almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> STRIPPED_ALMOND_WOOD = registryBlock("stripped_almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_PLANKS = registryBlock("almond_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {

                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face){
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_STAIRS = registryBlock("almond_stairs",
            () -> new StairBlock(() -> ModBlocks.ALMOND_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_SLAB = registryBlock("almond_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static BlockEntry<ConnectedGlassBlock> ALMOND_GLASS;
    public static final RegistryObject<Block> ALMOND_LEAVES = registryBlock("almond_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {

                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face){
                    return 60;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            }, ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> PASTRY_TABLE = registryBlock("pastry_table",
            () -> new PastryTable(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F)
                    .noOcclusion().sound(SoundType.WOOD)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FURNITURE_CUTTER = registryBlock("furniture_cutter",
            () -> new FurnitureCutterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> MARBLE_BLAST_FURNACE = registryBlock("marble_black_galaxy_blast_furnace",
            () -> new MarbleBlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops().lightLevel(litBlockEmission(13))),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ACACIA_BARREL = registryBlock("acacia_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_BARREL = registryBlock("almond_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BIRCH_BARREL = registryBlock("birch_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> CRIMSON_BARREL = registryBlock("crimson_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DARK_OAK_BARREL = registryBlock("dark_oak_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> JUNGLE_BARREL = registryBlock("jungle_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> OAK_BARREL = registryBlock("oak_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SPRUCE_BARREL = registryBlock("spruce_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> WARPED_BARREL = registryBlock("warped_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> TABLET = registryBlock("tablet",
            () -> new TabletBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static BlockEntry<CasingBlock> ALLOY_SOULS_CASING;
    public static BlockEntry<ConnectedGlassBlock> ALLOY_SOULS_GLASS;
    public static BlockEntry<ConnectedWall> GOLDEN_WALL;

    public static BlockEntry<MetalScaffoldingBlock> STEEL_SCAFFOLD;

    public static final RegistryObject<Block> MARBLE = registryBlock("marble",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> MARBLE_BLACK_GALAXY = registryBlock("marble_black_galaxy",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> MARBLE_PERLIN_PINK = registryBlock("marble_perlin_pink",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> COBBLED_MARBLE = registryBlock("cobbled_marble",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> COBBLED_MARBLE_BLACK_GALAXY = registryBlock("cobbled_marble_black_galaxy",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> COBBLED_MARBLE_PERLIN_PINK = registryBlock("cobbled_marble_perlin_pink",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FIRECLAY_BRICKS = registryBlock("fireclay_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).strength(2.0F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> OAK_TERRACE = registryBlock("oak_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .requiresCorrectToolForDrops().sound(SoundType.WOOD)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> OAK_TERRACE_STAIRS = registryBlock("oak_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .requiresCorrectToolForDrops().sound(SoundType.WOOD)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> FRAMED_CALCITE = registryBlock("framed_calcite",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.75F)
                    .requiresCorrectToolForDrops().sound(SoundType.WOOD).noOcclusion()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static BlockEntry<CTFramedWall> BLACK_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> BLACK_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> BLACK_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> BLACK_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> BLUE_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> BLUE_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> BLUE_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> BLUE_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> BROWN_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> BROWN_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> BROWN_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> BROWN_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> CYAN_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> CYAN_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> CYAN_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> CYAN_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> FRAMED_WALL;
    public static BlockEntry<CTFramedWall> FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> GREEN_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> GREEN_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> GREEN_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> GREEN_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> LUMINOUS_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> LUMINOUS_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> LUMINOUS_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> LUMINOUS_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> ORANGE_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> ORANGE_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> ORANGE_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> ORANGE_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CTFramedWall> RED_FRAMED_WALL;
    public static BlockEntry<CTFramedWall> RED_FRAMED_WALL_SMALL_BRICK;
    public static BlockEntry<CTFramedWall> RED_FRAMED_WALL_BRICK;
    public static BlockEntry<CTFramedWall> RED_FRAMED_WALL_GOLDEN;
    public static BlockEntry<CasingBlock> SECURE_BLOCK;
    public static BlockEntry<CTFramedWall> STONE_WALKWAY;
    public static BlockEntry<CTFramedWall> DEEPSLATE_WALKWAY;
    public static BlockEntry<CTFramedWall> SANDSTONE_WALKWAY;
    public static BlockEntry<CTFramedWall> RED_SANDSTONE_WALKWAY;
    public static BlockEntry<CTFramedWall> STEEL_BLOCK;
    public static final BlockEntry<SlidingDoorBlock> STEEL_DOOR;
    public static final RegistryObject<Block> STEEL_LAMP_BLOCK = registryBlock("steel_ruby_lamp_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .lightLevel(litBlockEmission(15))
                    .sound(ModSounds.STEEL_RUBY_LAMP).requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> RUBY_ORE = registryBlock("ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registryBlock("deepslate_ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_RUBY_BLOCK = registryBlock("raw_ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(5.0F,6.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RUBY_BLOCK = registryBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(5.0F,6.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> STONE_TANTALUM_ORE = registryBlock("stone_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DEEPSLATE_TANTALUM_ORE = registryBlock("deepslate_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> TANTALUM_ORE = registryBlock("tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BLACKSTONE_TANTALUM_ORE = registryBlock("blackstone_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_TANTALUM_BLOCK = registryBlock("raw_tantalum_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = registryBlock("raw_tungsten_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);




    public static final RegistryObject<Block> FARMLAND_SUMP_SAND = registryBlock("farmland_sump_sand",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).noOcclusion().strength(0.6F)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_RED_SAND = registryBlock("farmland_sump_red_sand",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).noOcclusion().strength(0.6F)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_RICH_SOIL = registryBlock("farmland_sump_rich_soil",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).noOcclusion().strength(0.6F)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_DIRT = registryBlock("farmland_sump_dirt",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).noOcclusion().strength(0.6F)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> FERTILIZED_SAND = registryBlock("fertilized_sand",
            () -> new SandFertilized(14406560 ,BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.SAND)
                    .strength(0.6F)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SAND_FARMLAND = registryBlock("sand_farmland",
            () -> new SandFarmBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.SAND).strength(0.6F)
                    .isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)), CreativeModeTab.TAB_SEARCH);
    public static final RegistryObject<Block> FERTILIZED_RED_SAND = registryBlock("fertilized_red_sand",
            () -> new RedSandFertilized(11098145 ,BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.SAND)
                    .strength(0.6F)), ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> RED_SAND_FARMLAND = registryBlock("red_sand_farmland",
            () -> new RedSandFarmBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.SAND).strength(0.6F)
                    .isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)), CreativeModeTab.TAB_SEARCH);


    public static final RegistryObject<Block> ALMOND_SAPLING = registryBlock("almond_sapling",
            () -> new SaplingBlock(new AlmondTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)),
            ModCreativeModeTab.CREATE_AND_FOOD);
    public static final RegistryObject<Block> RYE_PLANT = registryBlockWithoutBlockItem("rye_plant",
            () -> new RyePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static final RegistryObject<Block> BLUEBERRY_BUSH = registryBlockWithoutBlockItem("blueberry_bush",
            () -> new BlueberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_BLUEBERRY_BUSH = registryBlockWithoutBlockItem("wild_blueberry_bush",
            () -> new WildBlueberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> CRANBERRY_BUSH = registryBlockWithoutBlockItem("cranberry_bush",
            () -> new CranberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_CRANBERRY_BUSH = registryBlockWithoutBlockItem("wild_cranberry_bush",
            () -> new WildCranberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> RASPBERRY_BUSH = registryBlockWithoutBlockItem("raspberry_bush",
            () -> new RaspberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_RASPBERRY_BUSH = registryBlockWithoutBlockItem("wild_raspberry_bush",
            () -> new WildRaspberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> BLUE_GRAPE_BUSH = registryBlockWithoutBlockItem("blue_grape_bush",
            () -> new BlueGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_BLUE_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_blue_grape_bush",
            () -> new WildBlueGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> GREEN_GRAPE_BUSH = registryBlockWithoutBlockItem("green_grape_bush",
            () -> new GreenGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_GREEN_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_green_grape_bush",
            () -> new WildGreenGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> PURPLE_GRAPE_BUSH = registryBlockWithoutBlockItem("purple_grape_bush",
            () -> new PurpleGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_PURPLE_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_purple_grape_bush",
            () -> new WildPurpleGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    public static final RegistryObject<Block> RED_GRAPE_BUSH = registryBlockWithoutBlockItem("red_grape_bush",
            () -> new RedGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));
    public static final RegistryObject<Block> WILD_RED_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_red_grape_bush",
            () -> new WildRedGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP)));

    //BAKED CLAY//
    public static final RegistryObject<Block> UNBAKED_CLAY = registryBlock("unbaked_clay",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.5F)),
            ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);







    //________________________Create and Food: Kitchen________________________\\
    static  {
        CreateAndFood.REGISTRATE.creativeModeTab(() -> ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    }
    public static final RegistryObject<Block> KITCHEN_TABLE = registryBlock("kitchen_table",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> KITCHEN_TABLE_INNER = registryBlock("kitchen_table_inner",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> KITCHEN_TABLE_OUTER = registryBlock("kitchen_table_outer",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    public static final RegistryObject<Block> FERMENTATION_BARREL = registryFermentationBarrel("fermentation_barrel",
            () -> new FermentationBarrelBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .requiresCorrectToolForDrops().noOcclusion().sound(SoundType.COPPER)), ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    public static final RegistryObject<Block> OAK_CUTTING_BOARD = registryBlock("oak_cutting_board",
            () -> new OakCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> SPRUCE_CUTTING_BOARD = registryBlock("spruce_cutting_board",
            () -> new SpruceCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> BIRCH_CUTTING_BOARD = registryBlock("birch_cutting_board",
            () -> new BirchCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> JUNGLE_CUTTING_BOARD = registryBlock("jungle_cutting_board",
            () -> new JungleCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> ACACIA_CUTTING_BOARD = registryBlock("acacia_cutting_board",
            () -> new AcaciaCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> DARK_OAK_CUTTING_BOARD = registryBlock("dark_oak_cutting_board",
            () -> new DarkOakCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> CRIMSON_CUTTING_BOARD = registryBlock("crimson_cutting_board",
            () -> new CrimsonCuttingBoardBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> WARPED_CUTTING_BOARD = registryBlock("warped_cutting_board",
            () -> new WarpedCuttingBoardBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> ALMOND_CUTTING_BOARD = registryBlock("almond_cutting_board",
            () -> new AlmondCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);











//________________________Create and Food: CTM________________________\\
    static {
        CreateAndFood.REGISTRATE.creativeModeTab(() -> ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

        ALLOY_SOULS_CASING = CreateAndFood.REGISTRATE.block("alloy_souls_casing", CasingBlock::new)
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.casing(() -> {
                    return SpriteShifts.ALLOY_SOULS_CASING;
                })).properties(p -> p.lightLevel($ -> 10))
        .register();

        STEEL_BLOCK = CreateAndFood.REGISTRATE.block("steel_block", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> {
                return SpriteShifts.STEEL_BLOCK;
            })).properties(p -> p.sound(SoundType.METAL))
        .register();

        STEEL_DOOR = CreateAndFood.REGISTRATE.block("steel_door", p -> new SlidingDoorBlock(p, true))
                .transform(BuilderTransformers.slidingDoor("steel"))
                .properties(p -> p.color(MaterialColor.METAL)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                ).register();

        STEEL_SCAFFOLD = CreateAndFood.REGISTRATE.block("steel_scaffolding", MetalScaffoldingBlock::new)
                .transform(BuilderTransformers.scaffold("steel",
                        () -> DataIngredient.tag(AllTags.forgeItemTag("ingots/steel")), MaterialColor.TERRACOTTA_YELLOW,
                        SpriteShifts.STEEL_SCAFFOLD, SpriteShifts.STEEL_SCAFFOLD_INSIDE, SpriteShifts.STEEL_BLOCK))
                .register();

        GOLDEN_WALL = goldenWall("golden_wall",
                () -> new HorizontalCTBehaviour(SpriteShifts.GOLDEN_WALL, SpriteShifts.GOLDEN_WALL_TOP));

        ALMOND_GLASS = almondGlass("almond_glass",
                () -> new SimpleCTBehaviour(SpriteShifts.ALMOND_GLASS));

        ALLOY_SOULS_GLASS = alloySoulsGlass("alloy_souls_glass",
            () -> new SimpleCTBehaviour(SpriteShifts.ALLOY_SOULS_GLASS));

        SECURE_BLOCK = (CreateAndFood.REGISTRATE.block("secure_block", CasingBlock::new)
                .properties(p -> p.color(MaterialColor.METAL))
                .transform(BuilderTransformers.casing(() -> {
                    return SpriteShifts.SECURE_BLOCK;
                })).properties(p -> p.sound(SoundType.METAL))
        ).register();

        STONE_WALKWAY = (CreateAndFood.REGISTRATE.block("stone_walkway", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.METAL))
                .transform(BuilderTransformers.walkway(() -> {
                    return SpriteShifts.STONE_WALKWAY;
                })).transform(BuilderTransformers.walkway(() -> {
                    return SpriteShifts.DEEPSLATE_WALKWAY;
                }))
        ).register();

        DEEPSLATE_WALKWAY = (CreateAndFood.REGISTRATE.block("deepslate_walkway", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.METAL))
                .transform(BuilderTransformers.walkway(() -> {
                    return SpriteShifts.DEEPSLATE_WALKWAY;
                })).transform(BuilderTransformers.walkway(() -> {
                    return SpriteShifts.STONE_WALKWAY;
                })).properties(p -> p.sound(SoundType.DEEPSLATE))
        ).register();

    SANDSTONE_WALKWAY = (CreateAndFood.REGISTRATE.block("sandstone_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> {
                return SpriteShifts.SANDSTONE_WALKWAY;
            })).transform(BuilderTransformers.walkway(() -> {
                return SpriteShifts.RED_SANDSTONE_WALKWAY;
            }))
    ).register();

    RED_SANDSTONE_WALKWAY = (CreateAndFood.REGISTRATE.block("red_sandstone_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> {
                return SpriteShifts.RED_SANDSTONE_WALKWAY;
            })).transform(BuilderTransformers.walkway(() -> {
                return SpriteShifts.SANDSTONE_WALKWAY;
            }))
    ).register();

        new framedWall(CreateAndFood.REGISTRATE);
        new framedWallBrick(CreateAndFood.REGISTRATE);
        new framedWallGolden(CreateAndFood.REGISTRATE);
        new framedWallSmallBrick(CreateAndFood.REGISTRATE);
        PaletteStoneTypes.register(CreateAndFood.REGISTRATE);

    }

    static {
        CreateAndFood.REGISTRATE.creativeModeTab(() -> ModCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    }






//________________________Add________________________\\

    private static <T extends Block> RegistryObject<T> registryBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<T> registryBlockWithoutBlockItemDesc(String name, Supplier<T> block, String Desc_ID) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockWithoutBlockItemDesc(name, toReturn, Desc_ID);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<BlockItem> registerBlockWithoutBlockItemDesc(String name, RegistryObject<T> block, String Id) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                if (Screen.hasShiftDown()) {
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food.shift_down"));
                    pTooltip.add(new TextComponent(""));
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food.block" + "." + Id + ".desc"));
                } else {
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food.shift"));
                }
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registryFermentationBarrel(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerFermentationBarrelItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerFermentationBarrelItem(String name, RegistryObject<T> block,
                                                                                CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food." + name +".seconds"));
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food." + name +".minutes"));
                    pTooltip.add(new TranslatableComponent("tooltip.create_and_food." + name +".days"));
            }
        });
    }
    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registryMeltBlock(String name, int degrees, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerMeltBlockItem(name, degrees, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerMeltBlockItem(String name, int degrees, RegistryObject<T> block,
                                                                           CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent("tooltip.create_and_food.degrees",degrees));
            }
        });
    }
    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }
    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
