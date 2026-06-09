package net.egorplaytv.caf.world.feature;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.custom.berry.WildBerryBushBlock;
import net.egorplaytv.caf.block.custom.berry.WildBlueberryBlock;
import net.egorplaytv.caf.block.custom.berry.WildPumpkinAndMelonBlock;
import net.egorplaytv.caf.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.caf.config.CAFConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;


public class CAFConfiguredFeatures {
    public static final BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ALMOND_TREE =
            register("almond", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(CAFBlocks.ALMOND_LOG.get()),
                    new StraightTrunkPlacer(5,6,3),
                    BlockStateProvider.simple(CAFBlocks.ALMOND_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                    new TwoLayersFeatureSize(1,0,1)).build());

    public static final Holder<PlacedFeature> ALMOND_CHECKED = PlacementUtils.register("almond_checked", ALMOND_TREE,
                    PlacementUtils.filteredByBlockSurvival(CAFBlocks.ALMOND_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ALMOND_SPAWN =
            register("almond_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ALMOND_CHECKED,
                            0.5F)), ALMOND_CHECKED));



    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_BLUEBERRY_BUSH =
            register("wild_blueberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_BLUEBERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_CRANBERRY_BUSH =
            register("wild_cranberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_CRANBERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_RASPBERRY_BUSH =
            register("wild_raspberry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_RASPBERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_BLUE_GRAPE_BUSH =
            register("wild_blue_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_BLUE_GRAPE_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_GREEN_GRAPE_BUSH =
            register("wild_green_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_GREEN_GRAPE_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PURPLE_GRAPE_BUSH =
            register("wild_purple_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_PURPLE_GRAPE_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_RED_GRAPE_BUSH =
            register("wild_red_grape_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_RED_GRAPE_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 32, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_SWEET_BERRY_BUSH =
            register("wild_sweet_berry_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_SWEET_BERRY_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_WARPED_FRUITLIGTH_BUSH =
            register("wild_warped_fruitlight_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_WARPED_FRUITLIGHT_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.WARPED_NYLIUM, Blocks.NETHERRACK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_CRIMSON_FRUITLIGTH_BUSH =
            register("wild_crimson_fruitlight_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_CRIMSON_FRUITLIGHT_BUSH.get().defaultBlockState().setValue(WildBerryBushBlock.AGE, Integer.valueOf(2))), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.CRIMSON_NYLIUM, Blocks.NETHERRACK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PUMPKIN_BUSH =
            register("wild_pumpkin_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_PUMPKIN_BUSH.get().defaultBlockState().setValue(WildPumpkinAndMelonBlock.AGE, Integer.valueOf(5))), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_PUMPKIN_BUSH_IN_JUNGLE =
            register("wild_pumpkin_bush_in_jungle", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_PUMPKIN_BUSH.get().defaultBlockState()
                                    .setValue(WildPumpkinAndMelonBlock.IN_JUNGLE, Boolean.valueOf(true))
                                    .setValue(WildPumpkinAndMelonBlock.AGE, Integer.valueOf(5))), 96, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_MELON_BUSH =
            register("wild_melon_bush", Feature.RANDOM_PATCH,
                    getWildCropConfiguration(BlockStateProvider.simple(CAFBlocks.WILD_MELON_BUSH.get().defaultBlockState()
                                    .setValue(WildPumpkinAndMelonBlock.IN_JUNGLE, Boolean.valueOf(true))
                                    .setValue(WildPumpkinAndMelonBlock.AGE, Integer.valueOf(5))), 64, 6,
                            BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK), BLOCK_BELOW)));


    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TANTALUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, CAFBlocks.STONE_TANTALUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, CAFBlocks.DEEPSLATE_TANTALUM_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NETHER_TANTALUM_ORES = List.of(
            OreConfiguration.target(net.egorplaytv.caf.world.feature.OreFeatures.BLACKSTONE_ORE_REPLACEABLE,
                    CAFBlocks.BLACKSTONE_TANTALUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(net.egorplaytv.caf.world.feature.OreFeatures.NETHERRACK_ORE_REPLACEABLE,
                    CAFBlocks.TANTALUM_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_RUBY_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, CAFBlocks.RUBY_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, CAFBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE.getBaseBlock().get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE_BLACK_GALAXY = List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_BLACK_GALAXY.getBaseBlock().get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MARBLE_PERLIN_PINK = List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, PaletteStoneTypes.MARBLE_PERLIN_PINK.getBaseBlock().get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORE = register("ruby_ore",
          Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES, CAFConfigs.common().worldGen.rubyOreVeinsSize.get()));


    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE = register("marble",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE, CAFConfigs.common().worldGen.marbleVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE_BLACK_GALAXY = register("marble_black_galaxy",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE_BLACK_GALAXY, CAFConfigs.common().worldGen.marbleVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE_PERLIN_PINK = register("marble_perlin_pink",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE_PERLIN_PINK, CAFConfigs.common().worldGen.marbleVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_TANTALUM_ORE = register("tantalum_ore",
        Feature.ORE, new OreConfiguration(OVERWORLD_TANTALUM_ORES, CAFConfigs.common().worldGen.tantalumOreVeinsSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_TANTALUM_ORE = register("nether_tantalum_ore",
            Feature.ORE, new OreConfiguration(NETHER_TANTALUM_ORES, CAFConfigs.common().worldGen.tantalumOreVeinsSize.get()));


    public static RandomPatchConfiguration getWildCropConfiguration(SimpleStateProvider block, int tries, int xzSpread, BlockPredicate plantedOn) {
        return new RandomPatchConfiguration(tries, xzSpread, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn)));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String pName, F pFeature, FC pConfig) {
        return registerExact(BuiltinRegistries.CONFIGURED_FEATURE, pName, new ConfiguredFeature<>(pFeature, pConfig));
    }

    public static <V extends T, T> Holder<V> registerExact(Registry<T> pRegistry, String pLocation, V p_206383_) {
        return BuiltinRegistries.register((Registry<V>)pRegistry, CreateAndFood.asResource(pLocation), p_206383_);
    }
}
