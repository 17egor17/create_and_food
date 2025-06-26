package net.egorplaytv.create_and_food.world.feature;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.berry.WildPumpkinAndMelonBlock;
import net.egorplaytv.create_and_food.block.custom.berry.WildPumpkinBushBlock;
import net.egorplaytv.create_and_food.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModConfiguredFeatures {
    public static final BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ALMOND_TREE =
            FeatureUtils.register("almond", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.ALMOND_LOG.get()),
                    new StraightTrunkPlacer(5,6,3),
                    BlockStateProvider.simple(ModBlocks.ALMOND_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                    new TwoLayersFeatureSize(1,0,1)).build());

    public static final Holder<PlacedFeature> ALMOND_CHECKED = PlacementUtils.register("almond_checked", ALMOND_TREE,
                    PlacementUtils.filteredByBlockSurvival(ModBlocks.ALMOND_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ALMOND_SPAWN =
            FeatureUtils.register("almond_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ALMOND_CHECKED,
                            0.5F)), ALMOND_CHECKED));



    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_BLUEBERRY_BUSH =
            FeatureUtils.register("wild_blueberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_BLUEBERRY_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_CRANBERRY_BUSH =
            FeatureUtils.register("wild_cranberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_CRANBERRY_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_RASPBERRY_BUSH =
            FeatureUtils.register("wild_raspberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_RASPBERRY_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_BLUE_GRAPE_BUSH =
            FeatureUtils.register("wild_blue_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_BLUE_GRAPE_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_GREEN_GRAPE_BUSH =
            FeatureUtils.register("wild_green_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_GREEN_GRAPE_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PURPLE_GRAPE_BUSH =
            FeatureUtils.register("wild_purple_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_PURPLE_GRAPE_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_RED_GRAPE_BUSH =
            FeatureUtils.register("wild_red_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_RED_GRAPE_BUSH.get(), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PUMPKIN_BUSH =
            FeatureUtils.register("wild_pumpkin_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_PUMPKIN_BUSH.get(), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PUMPKIN_BUSH_IN_JUNGLE =
            FeatureUtils.register("wild_pumpkin_bush_in_jungle", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_PUMPKIN_BUSH.get().defaultBlockState()
                                    .setValue(WildPumpkinAndMelonBlock.IN_JUNGLE, true).getBlock(), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_MELON_BUSH =
            FeatureUtils.register("wild_melon_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(ModBlocks.WILD_MELON_BUSH.get(), 64, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));


    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TANTALUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.STONE_TANTALUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TANTALUM_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NETHER_TANTALUM_ORES = List.of(
            OreConfiguration.target(net.egorplaytv.create_and_food.world.feature.OreFeatures.BLACKSTONE_ORE_REPLACEABLE,
                    ModBlocks.BLACKSTONE_TANTALUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(net.egorplaytv.create_and_food.world.feature.OreFeatures.NETHERRACK_ORE_REPLACEABLE,
                    ModBlocks.TANTALUM_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_RUBY_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE.getBaseBlock().get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE.getBaseBlock().get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE_BLACK_GALAXY = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_BLACK_GALAXY.getBaseBlock().get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_BLACK_GALAXY.getBaseBlock().get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE_PERLIN_PINK = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_PERLIN_PINK.getBaseBlock().get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_PERLIN_PINK.getBaseBlock().get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORE = FeatureUtils.register("ruby_ore",
          Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES, CreateAndFoodCommonConfigs.RUBY_ORE_VEINS_SIZE.get()));


    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE = FeatureUtils.register("marble",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE, CreateAndFoodCommonConfigs.MARBLE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE_BLACK_GALAXY = FeatureUtils.register("marble_black_galaxy",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE_BLACK_GALAXY, CreateAndFoodCommonConfigs.MARBLE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE_PERLIN_PINK = FeatureUtils.register("marble_perlin_pink",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE_PERLIN_PINK, CreateAndFoodCommonConfigs.MARBLE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_TANTALUM_ORE = FeatureUtils.register("tantalum_ore",
        Feature.ORE, new OreConfiguration(OVERWORLD_TANTALUM_ORES, CreateAndFoodCommonConfigs.TANTALUM_ORE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_TANTALUM_ORE = FeatureUtils.register("nether_tantalum_ore",
            Feature.ORE, new OreConfiguration(NETHER_TANTALUM_ORES, CreateAndFoodCommonConfigs.TANTALUM_ORE_VEINS_SIZE.get()));


    public static RandomPatchConfiguration getWildCropConfiguration(Block block, int tries, int xzSpread, BlockPredicate plantedOn) {
        return new RandomPatchConfiguration(tries, xzSpread, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn)));
    }
}
