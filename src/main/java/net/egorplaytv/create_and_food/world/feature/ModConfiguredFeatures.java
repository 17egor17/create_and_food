package net.egorplaytv.create_and_food.world.feature;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ALMOND_TREE =
            FeatureUtils.register("almond", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.ALMOND_LOG.get()),
                    new StraightTrunkPlacer(5,6,3),
                    BlockStateProvider.simple(ModBlocks.ALMOND_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),4),
                    new TwoLayersFeatureSize(1,0,2)).build());

    public static final Holder<PlacedFeature> ALMOND_CHECKED = PlacementUtils.register("almond_checked", ALMOND_TREE,
                    PlacementUtils.filteredByBlockSurvival(ModBlocks.ALMOND_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ALMOND_SPAWN =
            FeatureUtils.register("almond_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ALMOND_CHECKED,
                            0.5F)), ALMOND_CHECKED));


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
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.MARBLE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.MARBLE_BLACK_GALAXY.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.MARBLE_PERLIN_PINK.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.MARBLE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.MARBLE_BLACK_GALAXY.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.MARBLE_PERLIN_PINK.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORE = FeatureUtils.register("ruby_ore",
          Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES, CreateAndFoodCommonConfigs.RUBY_ORE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARBLE = FeatureUtils.register("marble",
            Feature.ORE, new OreConfiguration(OVERWORLD_MARBLE, CreateAndFoodCommonConfigs.MARBLE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_TANTALUM_ORE = FeatureUtils.register("tantalum_ore",
        Feature.ORE, new OreConfiguration(OVERWORLD_TANTALUM_ORES, CreateAndFoodCommonConfigs.TUNTAL_ORE_VEINS_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_TANTALUM_ORE = FeatureUtils.register("nether_tantalum_ore",
            Feature.ORE, new OreConfiguration(NETHER_TANTALUM_ORES, CreateAndFoodCommonConfigs.TUNTAL_ORE_VEINS_SIZE.get()));
}
