package net.egorplaytv.create_and_food.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.custom.*;
import net.egorplaytv.create_and_food.block.custom.lanterns.*;
import net.egorplaytv.create_and_food.block.custom.berry.*;
import net.egorplaytv.create_and_food.block.custom.connect.*;
import net.egorplaytv.create_and_food.block.custom.lanterns.LanternBlock;
import net.egorplaytv.create_and_food.block.entity.ModWoodTypes;
import net.egorplaytv.create_and_food.content.kinetics.grinder.GrinderGenerator;
import net.egorplaytv.create_and_food.item.CAFCreativeModeTab;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.item.custom.CAFCogwheelBlockItem;
import net.egorplaytv.create_and_food.sound.CAFSounds;
import net.egorplaytv.create_and_food.world.feature.tree.AlmondTreeGrower;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
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

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static net.egorplaytv.create_and_food.CreateAndFood.REGISTRATE;
import static net.egorplaytv.create_and_food.block.custom.connect.CTBlocks.*;
import static net.egorplaytv.create_and_food.block.custom.connect.EncasedCogwheelBlock.steel;


public class CAFBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CreateAndFood.MOD_ID);

//________________________Create and Food: Decorative________________________\\
    public static final RegistryObject<Block> TORN_SOUL_CHAIN = registryBlockWithoutBlockItem("torn_soul_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_CHAIN = registryBlockWithoutBlockItem("steel_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TORN_SOUL_LANTERN = registryBlockWithoutBlockItem("torn_soul_lantern",
            () -> new TornSoulLantern(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion()));

    public static final RegistryObject<Block> GLOWING_BRASS_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_copper_lantern",
            () -> new CopperLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops(),
                    WeatheringCopperLantern.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> GLOWING_BRASS_EXPOSED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_exposed_copper_lantern",
            () -> new CopperLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops(),
                    WeatheringCopperLantern.WeatherState.EXPOSED));
    public static final RegistryObject<Block> GLOWING_BRASS_WEATHERED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_weathered_copper_lantern",
            () -> new CopperLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops(),
                    WeatheringCopperLantern.WeatherState.WEATHERED));
    public static final RegistryObject<Block> GLOWING_BRASS_OXIDIZED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_oxidized_copper_lantern",
            () -> new CopperLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops(),
                    WeatheringCopperLantern.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> GLOWING_BRASS_WAXED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_waxed_copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_waxed_exposed_copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_waxed_weathered_copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN = registryBlockWithoutBlockItem("glowing_brass_waxed_oxidized_copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GLOWING_BRASS_STEEL_LANTERN = registryBlockWithoutBlockItem("glowing_brass_steel_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LANTERN = registryBlockWithoutBlockItem("lantern",
            () -> new MinecraftLantern(BlockBehaviour.Properties.copy(Blocks.LANTERN).noCollission().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SOUL_LANTERN = registryBlockWithoutBlockItem("soul_lantern",
            () -> new MinecraftLantern(BlockBehaviour.Properties.copy(Blocks.SOUL_LANTERN).noCollission().requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> ALMOND_WALL_SIGN = registryBlockWithoutBlockItem("almond_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.ALMOND));
    public static final RegistryObject<Block> ALMOND_SIGN = registryBlockWithoutBlockItem("almond_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.ALMOND));
    public static final RegistryObject<Block> ALMOND_DOOR = registryBlock("almond_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .noOcclusion()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_TRAPDOOR = registryBlock("almond_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_BUTTON = registryBlock("almond_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                    .noCollission()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_PRESSURE_PLATE = registryBlock("almond_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
            ), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_FENCE = registryBlock("almond_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_FENCE_GATE = registryBlock("almond_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_LOG = registryBlock("almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> STRIPPED_ALMOND_LOG = registryBlock("stripped_almond_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_WOOD = registryBlock("almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> STRIPPED_ALMOND_WOOD = registryBlock("stripped_almond_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_PLANKS = registryBlock("almond_planks",
            () -> planks(MaterialColor.WOOD), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> ALMOND_STAIRS = registryBlock("almond_stairs",
            () -> new StairBlock(() -> CAFBlocks.ALMOND_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_SLAB = registryBlock("almond_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static BlockEntry<ConnectedGlassBlock> ALMOND_GLASS;
    public static final RegistryObject<Block> ALMOND_LEAVES = registryBlock("almond_leaves",
            () -> leaves(MaterialColor.TERRACOTTA_LIGHT_GREEN), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> PASTRY_TABLE = registryBlock("pastry_table",
            () -> new PastryTable(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F)
                    .noOcclusion().sound(SoundType.WOOD)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FURNITURE_CUTTER = registryBlock("furniture_cutter",
            () -> new FurnitureCutterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> MARBLE_BLAST_FURNACE = registryBlock("marble_black_galaxy_blast_furnace",
            () -> new MarbleBlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops().lightLevel(litBlockEmission(13))),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> OAK_BARREL = registryBlock("oak_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SPRUCE_BARREL = registryBlock("spruce_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BIRCH_BARREL = registryBlock("birch_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> JUNGLE_BARREL = registryBlock("jungle_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ACACIA_BARREL = registryBlock("acacia_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DARK_OAK_BARREL = registryBlock("dark_oak_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> CRIMSON_BARREL = registryBlock("crimson_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> WARPED_BARREL = registryBlock("warped_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_BARREL = registryBlock("almond_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> TERMINAL = registryBlock("terminal",
            () -> new TerminalBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);


    public static BlockEntry<GrinderBlock> MECHANICAL_GRINDER;

    public static BlockEntry<CasingBlock> ALLOY_SOULS_CASING;
    public static BlockEntry<ConnectedGlassBlock> ALLOY_SOULS_GLASS;
    public static BlockEntry<ConnectedWall> GOLDEN_WALL;

    public static BlockEntry<MetalScaffoldingBlock> STEEL_SCAFFOLD;

    public static final RegistryObject<Block> COBBLED_MARBLE = registryBlock("cobbled_marble",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> COBBLED_MARBLE_BLACK_GALAXY = registryBlock("cobbled_marble_black_galaxy",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> COBBLED_MARBLE_PERLIN_PINK = registryBlock("cobbled_marble_perlin_pink",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FIRECLAY_BRICKS = registryBlock("fireclay_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).strength(2.0F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> OAK_TERRACE = registryBlock("oak_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> OAK_TERRACE_STAIRS = registryBlock("oak_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SPRUCE_TERRACE = registryBlock("spruce_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SPRUCE_TERRACE_STAIRS = registryBlock("spruce_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BIRCH_TERRACE = registryBlock("birch_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BIRCH_TERRACE_STAIRS = registryBlock("birch_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> JUNGLE_TERRACE = registryBlock("jungle_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> JUNGLE_TERRACE_STAIRS = registryBlock("jungle_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ACACIA_TERRACE = registryBlock("acacia_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ACACIA_TERRACE_STAIRS = registryBlock("acacia_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DARK_OAK_TERRACE = registryBlock("dark_oak_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DARK_OAK_TERRACE_STAIRS = registryBlock("dark_oak_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> CRIMSON_TERRACE = registryBlock("crimson_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> CRIMSON_TERRACE_STAIRS = registryBlock("crimson_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> WARPED_TERRACE = registryBlock("warped_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> WARPED_TERRACE_STAIRS = registryBlock("warped_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_TERRACE = registryBlock("almond_terrace",
            () -> new TerraceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> ALMOND_TERRACE_STAIRS = registryBlock("almond_terrace_stairs",
            () -> new TerraceStairsBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> FRAMED_CALCITE = registryBlock("framed_calcite",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.75F)
                    .requiresCorrectToolForDrops().sound(SoundType.WOOD).noOcclusion()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
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
    public static BlockEntry<CasingBlock> STEEL_BLOCK;
    public static BlockEntry<CasingBlock> STEEL_CASING;
    public static BlockEntry<CAFShaftBlock> STEEL_SHAFT;
    public static BlockEntry<CAFCogWheelBlock> STEEL_COGWHEEL;
    public static BlockEntry<CAFCogWheelBlock> LARGE_STEEL_COGWHEEL;
    public static final BlockEntry<SlidingDoorBlock> STEEL_DOOR;
    public static final RegistryObject<Block> STEEL_LAMP_BLOCK = registryBlock("steel_ruby_lamp_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .lightLevel(litBlockEmission(15))
                    .sound(CAFSounds.STEEL_RUBY_LAMP).requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> RUBY_ORE = registryBlock("ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registryBlock("deepslate_ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_RUBY_BLOCK = registryBlock("raw_ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(5.0F,6.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RUBY_BLOCK = registryBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST).strength(5.0F,6.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> STONE_TANTALUM_ORE = registryBlock("stone_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> DEEPSLATE_TANTALUM_ORE = registryBlock("deepslate_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> TANTALUM_ORE = registryBlock("tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> BLACKSTONE_TANTALUM_ORE = registryBlock("blackstone_tantalum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHERRACK).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_TANTALUM_BLOCK = registryBlock("raw_tantalum_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> TUNGSTEN_ORE = registryBlock("tungsten_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> STONE_TUNGSTEN_ORE = registryBlock("stone_tungsten_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> DEEPSLATE_TUNGSTEN_ORE = registryBlock("deepslate_tungsten_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = registryBlock("raw_tungsten_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);




    public static final RegistryObject<Block> FARMLAND_SUMP_SAND = registryBlock("farmland_sump_sand",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).noOcclusion().strength(0.6F)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_RED_SAND = registryBlock("farmland_sump_red_sand",
            () -> new SumpBlock(BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).noOcclusion().strength(0.6F)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_RICH_SOIL = registryBlock("farmland_sump_rich_soil",
            () -> new DirtSlabBlock(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).noOcclusion().strength(0.6F)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> FARMLAND_SUMP_DIRT = registryBlock("farmland_sump_dirt",
            () -> new DirtSlabBlock(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).noOcclusion().strength(0.6F)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

    public static final RegistryObject<Block> FERTILIZED_SAND = registryBlock("fertilized_sand",
            () -> new SandFertilized(14406560 ,BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.SAND)
                    .strength(0.6F)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> SAND_FARMLAND = registryBlock("sand_farmland",
            () -> new SandFarmBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.SAND).strength(0.6F)
                    .isViewBlocking(CAFBlocks::always).isSuffocating(CAFBlocks::always)), CreativeModeTab.TAB_SEARCH);
    public static final RegistryObject<Block> FERTILIZED_RED_SAND = registryBlock("fertilized_red_sand",
            () -> new RedSandFertilized(11098145 ,BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.SAND)
                    .strength(0.6F)), CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> RED_SAND_FARMLAND = registryBlock("red_sand_farmland",
            () -> new RedSandFarmBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.SAND).strength(0.6F)
                    .isViewBlocking(CAFBlocks::always).isSuffocating(CAFBlocks::always)), CreativeModeTab.TAB_SEARCH);

    public static final RegistryObject<Block> FLOODED_FARMLAND = registryBlock("flooded_farmland",
            () -> new FloodedFarmlandBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND)), CreativeModeTab.TAB_SEARCH);

    public static final RegistryObject<Block> FLOODED_RICH_SOIL_FARMLAND = registryBlock("flooded_rich_soil_farmland",
            () -> new FloodedRichSoilFarmlandBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND)), CreativeModeTab.TAB_SEARCH);

    public static final RegistryObject<Block> NIXIE_VASE = registryBlock("nixie_vase",
            () -> new VaseBlock(BlockBehaviour.Properties.of(Material.GLASS).noCollission()),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> NIXIE_VASE_PERLIN_PINK = registryBlock("nixie_vase_perlin_pink",
            () -> new VaseBlock(BlockBehaviour.Properties.of(Material.GLASS).noCollission()),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);
    public static final RegistryObject<Block> NIXIE_VASE_BLACK_GALAXY = registryBlock("nixie_vase_black_galaxy",
            () -> new VaseBlock(BlockBehaviour.Properties.of(Material.GLASS).noCollission()),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);


    public static final RegistryObject<Block> ALMOND_SAPLING = registryBlock("almond_sapling",
            () -> new SaplingBlock(new AlmondTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)),
            CAFCreativeModeTab.CREATE_AND_FOOD);
    public static final RegistryObject<Block> RYE_PLANT = registryBlockWithoutBlockItem("rye_plant",
            () -> new RyePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static final RegistryObject<Block> RICE_PLANT = registryBlockWithoutBlockItem("rice_plant",
            () -> new RicePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission()));

    public static final RegistryObject<Block> RICE_CROP = registryBlockWithoutBlockItem("rice",
            () -> new RiceBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).strength(0.2F).noOcclusion()));

    public static final RegistryObject<Block> RICE_CROP_PANICLES = registryBlockWithoutBlockItem("rice_panicles",
            () -> new RicePaniclesBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static final RegistryObject<Block> BLUEBERRY_BUSH = registryBlockWithoutBlockItem("blueberry_bush",
            () -> new BlueberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_BLUEBERRY_BUSH = registryBlockWithoutBlockItem("wild_blueberry_bush",
            () -> new WildBlueberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> CRANBERRY_BUSH = registryBlockWithoutBlockItem("cranberry_bush",
            () -> new CranberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_CRANBERRY_BUSH = registryBlockWithoutBlockItem("wild_cranberry_bush",
            () -> new WildCranberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> RASPBERRY_BUSH = registryBlockWithoutBlockItem("raspberry_bush",
            () -> new RaspberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_RASPBERRY_BUSH = registryBlockWithoutBlockItem("wild_raspberry_bush",
            () -> new WildRaspberryBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> BLUE_GRAPE_BUSH = registryBlockWithoutBlockItem("blue_grape_bush",
            () -> new BlueGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_BLUE_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_blue_grape_bush",
            () -> new WildBlueGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> GREEN_GRAPE_BUSH = registryBlockWithoutBlockItem("green_grape_bush",
            () -> new GreenGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_GREEN_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_green_grape_bush",
            () -> new WildGreenGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> PURPLE_GRAPE_BUSH = registryBlockWithoutBlockItem("purple_grape_bush",
            () -> new PurpleGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_PURPLE_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_purple_grape_bush",
            () -> new WildPurpleGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> RED_GRAPE_BUSH = registryBlockWithoutBlockItem("red_grape_bush",
            () -> new RedGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));
    public static final RegistryObject<Block> WILD_RED_GRAPE_BUSH = registryBlockWithoutBlockItem("wild_red_grape_bush",
            () -> new WildRedGrapeBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(0.2F)));

    public static final RegistryObject<Block> PUMPKIN_BUSH = registryBlockWithoutBlockItem("pumpkin_bush",
            () -> new PumpkinBushBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(1.0F)));
    public static final RegistryObject<Block> WILD_PUMPKIN_BUSH = registryBlockWithoutBlockItem("wild_pumpkin_bush",
            () -> new WildPumpkinBushBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(1.0F)));

    public static final RegistryObject<Block> MELON_BUSH = registryBlockWithoutBlockItem("melon_bush",
            () -> new MelonBushBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(1.0F)));
    public static final RegistryObject<Block> WILD_MELON_BUSH = registryBlockWithoutBlockItem("wild_melon_bush",
            () -> new WildMelonBushBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.CROP).strength(1.0F)));

    //BAKED CLAY//
    public static final RegistryObject<Block> UNBAKED_CLAY = registryBlock("unbaked_clay",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.5F)),
            CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);







    //________________________Create and Food: Kitchen________________________\\
    public static final RegistryObject<Block> KITCHEN_TABLE = registryBlock("kitchen_table",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> KITCHEN_TABLE_INNER = registryBlock("kitchen_table_inner",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> KITCHEN_TABLE_OUTER = registryBlock("kitchen_table_outer",
            () -> new KitchenTable(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()
                    .strength(0.2F, 0.3F)), CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    public static final RegistryObject<Block> FERMENTATION_BARREL = registryFermentationBarrel("fermentation_barrel",
            () -> new FermentationBarrelBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)
                    .requiresCorrectToolForDrops().noOcclusion().sound(SoundType.COPPER)), CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    public static final RegistryObject<Block> SCALES = registryBlock("scales",
            () -> new ScalesBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F)), CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

    public static final BlockEntry<MechanicalBlenderBlock> MECHANICAL_BLENDER;

    public static final RegistryObject<Block> OAK_CUTTING_BOARD = registryBlock("oak_cutting_board",
            () -> new OakCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> SPRUCE_CUTTING_BOARD = registryBlock("spruce_cutting_board",
            () -> new SpruceCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> BIRCH_CUTTING_BOARD = registryBlock("birch_cutting_board",
            () -> new BirchCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> JUNGLE_CUTTING_BOARD = registryBlock("jungle_cutting_board",
            () -> new JungleCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> ACACIA_CUTTING_BOARD = registryBlock("acacia_cutting_board",
            () -> new AcaciaCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> DARK_OAK_CUTTING_BOARD = registryBlock("dark_oak_cutting_board",
            () -> new DarkOakCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> CRIMSON_CUTTING_BOARD = registryBlock("crimson_cutting_board",
            () -> new CrimsonCuttingBoardBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> WARPED_CUTTING_BOARD = registryBlock("warped_cutting_board",
            () -> new WarpedCuttingBoardBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);
    public static final RegistryObject<Block> ALMOND_CUTTING_BOARD = registryBlock("almond_cutting_board",
            () -> new AlmondCuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)),
            CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);











//________________________Create and Food: CTM________________________\\

    static {
        REGISTRATE.creativeModeTab(() -> CreateAndFood.VIIsPresent ? null : CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);

        MECHANICAL_GRINDER = REGISTRATE.block("mechanical_grinder", GrinderBlock::new)
                .initialProperties(SharedProperties::stone)
                .addLayer(() -> RenderType::cutoutMipped)
                .properties(p -> p.color(MaterialColor.PODZOL))
                .blockstate(new GrinderGenerator()::generate)
                .transform(BlockStressDefaults.setImpact(4.0))
                .item()
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .transform(customItemModel())
                .register();
    }

    static {
    REGISTRATE.creativeModeTab(() -> CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE);


    STEEL_SHAFT = REGISTRATE.block("steel_shaft", CAFShaftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
            .transform(BlockStressDefaults.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    STEEL_COGWHEEL = REGISTRATE.block("steel_cogwheel", CAFCogWheelBlock::small)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.METAL).color(MaterialColor.TERRACOTTA_GRAY))
            .transform(BlockStressDefaults.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .transform(BlockStressDefaults.setImpact(1.5))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CAFCogwheelBlockItem::new)
            .build()
            .register();

    LARGE_STEEL_COGWHEEL = REGISTRATE.block("large_steel_cogwheel", CAFCogWheelBlock::large)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.METAL).color(MaterialColor.TERRACOTTA_GRAY))
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .transform(BlockStressDefaults.setImpact(3.0))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CAFCogwheelBlockItem::new)
            .build()
            .register();


    ALLOY_SOULS_CASING = REGISTRATE.block("alloy_souls_casing", CasingBlock::new)
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.casing(() -> SpriteShifts.ALLOY_SOULS_CASING))
            .properties(p -> p.lightLevel($ -> 10))
            .register();

    STEEL_BLOCK = REGISTRATE.block("steel_block", CasingBlock::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.casing(() -> SpriteShifts.STEEL_BLOCK))
            .properties(p -> p.sound(SoundType.METAL))
            .register();

    STEEL_CASING = REGISTRATE.block("steel_casing", CasingBlock::new)
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(BuilderTransformers.casing(() -> SpriteShifts.STEEL_CASING))
            .properties(p -> p.sound(SoundType.WOOD))
            .register();

    STEEL_DOOR = REGISTRATE.block("steel_door", p -> new SlidingDoorBlock(p, true))
            .transform(BuilderTransformers.slidingDoor("steel"))
            .properties(p -> p.color(MaterialColor.METAL)
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ).register();

    STEEL_SCAFFOLD = REGISTRATE.block("steel_scaffolding", MetalScaffoldingBlock::new)
            .transform(BuilderTransformers.steelScaffold("steel",
                    () -> DataIngredient.tag(AllTags.forgeItemTag("ingots/steel")), MaterialColor.TERRACOTTA_YELLOW,
                    SpriteShifts.STEEL_SCAFFOLD, SpriteShifts.STEEL_SCAFFOLD_INSIDE, SpriteShifts.STEEL_CASING))
            .register();

    GOLDEN_WALL = goldenWall("golden_wall",
            () -> new HorizontalCTBehaviour(SpriteShifts.GOLDEN_WALL, SpriteShifts.GOLDEN_WALL_TOP));

    ALMOND_GLASS = almondGlass("almond_glass",
            () -> new SimpleCTBehaviour(SpriteShifts.ALMOND_GLASS));

    ALLOY_SOULS_GLASS = alloySoulsGlass("alloy_souls_glass",
            () -> new SimpleCTBehaviour(SpriteShifts.ALLOY_SOULS_GLASS));

    SECURE_BLOCK = (REGISTRATE.block("secure_block", CasingBlock::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.casing(() -> SpriteShifts.SECURE_BLOCK))
            .properties(p -> p.sound(SoundType.METAL)))
            .register();

    STONE_WALKWAY = (REGISTRATE.block("stone_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.STONE_WALKWAY))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.DEEPSLATE_WALKWAY)))
            .register();

    DEEPSLATE_WALKWAY = (REGISTRATE.block("deepslate_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.DEEPSLATE_WALKWAY))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.STONE_WALKWAY))
            .properties(p -> p.sound(SoundType.DEEPSLATE)))
            .register();

    SANDSTONE_WALKWAY = (REGISTRATE.block("sandstone_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.SANDSTONE_WALKWAY))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.RED_SANDSTONE_WALKWAY)))
            .register();

    RED_SANDSTONE_WALKWAY = (REGISTRATE.block("red_sandstone_walkway", CTFramedWall::new)
            .properties(p -> p.color(MaterialColor.METAL))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.RED_SANDSTONE_WALKWAY))
            .transform(BuilderTransformers.walkway(() -> SpriteShifts.SANDSTONE_WALKWAY)))
            .register();


    new framedWall(REGISTRATE);
    new framedWallBrick(REGISTRATE);
    new framedWallGolden(REGISTRATE);
    new framedWallSmallBrick(REGISTRATE);
    PaletteStoneTypes.register(REGISTRATE);

}

    public static final BlockEntry<EncasedShaftBlock> STEEL_ENCASED_STEEL_SHAFT =
            REGISTRATE.block("steel_encased_steel_shaft", p -> new EncasedShaftBlock(p, CAFBlocks.STEEL_CASING::get))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
                    .transform(BuilderTransformers.encasedShaft("steel", () -> SpriteShifts.STEEL_CASING))
                    .transform(EncasingRegistry.addVariantTo(CAFBlocks.STEEL_SHAFT))
                    .transform(pickaxeOnly())
                    .register();
    public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_STEEL_COGWHEEL =
            REGISTRATE.block("steel_encased_steel_cogwheel", p -> steel(p, false, CAFBlocks.STEEL_CASING::get))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
                    .transform(BlockStressDefaults.setImpact(1.5))
                    .transform(BuilderTransformers.encasedSteelCogwheel("steel", () -> SpriteShifts.STEEL_CASING))
                    .transform(EncasingRegistry.addVariantTo(CAFBlocks.STEEL_COGWHEEL))
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(SpriteShifts.STEEL_CASING,
                            Couple.create(SpriteShifts.STEEL_ENCASED_COGWHEEL_SIDE,
                                    SpriteShifts.STEEL_ENCASED_COGWHEEL_OTHERSIDE))))
                    .transform(pickaxeOnly())
                    .register();
    public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_LARGE_STEEL_COGWHEEL =
            REGISTRATE.block("steel_encased_large_steel_cogwheel", p -> steel(p, true, CAFBlocks.STEEL_CASING::get))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
                    .transform(BlockStressDefaults.setImpact(3.0))
                    .transform(BuilderTransformers.encasedSteelLargeCogwheel("steel", () -> SpriteShifts.STEEL_CASING))
                    .transform(EncasingRegistry.addVariantTo(CAFBlocks.LARGE_STEEL_COGWHEEL))
                    .transform(pickaxeOnly())
                    .register();

    static {
        REGISTRATE.creativeModeTab(() -> CAFCreativeModeTab.CREATE_AND_FOOD_KITCHEN);

        MECHANICAL_BLENDER = REGISTRATE.block("mechanical_blender", MechanicalBlenderBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.noOcclusion().color(MaterialColor.STONE))
                .transform(pickaxeOnly())
                .blockstate((c, p) ->
                        p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(BlockStressDefaults.setImpact(4.0))
                .item(AssemblyOperatorBlockItem::new)
                .transform(customItemModel())
                .register();
    }






//________________________Add________________________\\

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    private static LeavesBlock leaves(MaterialColor color) {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, color).strength(0.2F).randomTicks()
                .sound(SoundType.GRASS).noOcclusion().isValidSpawn(CAFBlocks::ocelotOrParrot)
                .isSuffocating(CAFBlocks::never).isViewBlocking(CAFBlocks::never)){
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
        };
    }

    private static Block planks(MaterialColor color){
        return new Block(BlockBehaviour.Properties.of(Material.WOOD, color)
                .strength(2.0F, 3.0F).sound(SoundType.WOOD)) {
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
        };
    }

    private static <T extends Block> RegistryObject<T> registryBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<T> registryBlockWithoutBlockItemDesc(String name, Supplier<T> block, String Desc_ID) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockWithoutBlockItemDesc(name, toReturn, Desc_ID);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<BlockItem> registerBlockWithoutBlockItemDesc(String name, RegistryObject<T> block, String Id) {
        return CAFItems.ITEMS.register(name, () -> new BlockItem(block.get(),
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
        return CAFItems.ITEMS.register(name, () -> new BlockItem(block.get(),
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
        return CAFItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registryMeltBlock(String name, int degrees, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerMeltBlockItem(name, degrees, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerMeltBlockItem(String name, int degrees, RegistryObject<T> block,
                                                                           CreativeModeTab tab) {
        return CAFItems.ITEMS.register(name, () -> new BlockItem(block.get(),
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
