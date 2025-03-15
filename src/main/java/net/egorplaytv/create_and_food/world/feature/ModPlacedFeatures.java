package net.egorplaytv.create_and_food.world.feature;

import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> ALMOND_PLACED = PlacementUtils.register("almond_placed",
            ModConfiguredFeatures.ALMOND_SPAWN, VegetationPlacements.treePlacement(
            PlacementUtils.countExtra(3,01f,2)));

    public static final Holder<PlacedFeature> WILD_BLUEBERRY_BUSH_PLACED = PlacementUtils.register("wild_blueberry_bush_placed",
            ModConfiguredFeatures.WILD_BLUEBERRY_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_BLUEBERRY_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_CRANBERRY_BUSH_PLACED = PlacementUtils.register("wild_cranberry_bush_placed",
            ModConfiguredFeatures.WILD_CRANBERRY_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_CRANBERRY_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_RASPBERRY_BUSH_PLACED = PlacementUtils.register("wild_raspberry_bush_placed",
            ModConfiguredFeatures.WILD_RASPBERRY_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_RASPBERRY_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_BLUE_GRAPE_BUSH_PLACED = PlacementUtils.register("wild_blue_grape_bush_placed",
            ModConfiguredFeatures.WILD_BLUE_GRAPE_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_BLUE_GRAPE_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_GREEN_GRAPE_BUSH_PLACED = PlacementUtils.register("wild_green_grape_bush_placed",
            ModConfiguredFeatures.WILD_GREEN_GRAPE_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_GREEN_GRAPE_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_PURPLE_GRAPE_BUSH_PLACED = PlacementUtils.register("wild_purple_grape_bush_placed",
            ModConfiguredFeatures.WILD_PURPLE_GRAPE_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_PURPLE_GRAPE_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_RED_GRAPE_BUSH_PLACED = PlacementUtils.register("wild_red_grape_bush_placed",
            ModConfiguredFeatures.WILD_RED_GRAPE_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_RED_GRAPE_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_PUMPKIN_PLACED = PlacementUtils.register("wild_pumpkin_placed",
            ModConfiguredFeatures.WILD_PUMPKIN_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_PUMPKIN_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_PUMPKIN_IN_JUNGLE_PLACED = PlacementUtils.register("wild_pumpkin_in_jungle_placed",
            ModConfiguredFeatures.WILD_PUMPKIN_BUSH_IN_JUNGLE, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_PUMPKIN_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILD_MELON_PLACED = PlacementUtils.register("wild_melon_placed",
            ModConfiguredFeatures.WILD_MELON_BUSH, RarityFilter.onAverageOnceEvery(CreateAndFoodCommonConfigs.CHANCE_WILD_MELON_BUSH.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> RUBY_ORE_PLACED = PlacementUtils.register("ruby_ore_placed",
            ModConfiguredFeatures.RUBY_ORE, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.RUBY_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> OVERWORLD_TANTALUM_ORE_PLACED = PlacementUtils.register("overworld_tantalum_ore_placed",
            ModConfiguredFeatures.OVERWORLD_TANTALUM_ORE, ModOrePlacement.commonOrePlacement(
                    CreateAndFoodCommonConfigs.TANTALUM_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(10))));

    public static final Holder<PlacedFeature> NETHER_TANTALUM_ORE_PLACED = PlacementUtils.register("nether_tantalum_ore_placed",
            ModConfiguredFeatures.NETHER_TANTALUM_ORE, ModOrePlacement.commonOrePlacement(
                    CreateAndFoodCommonConfigs.TANTALUM_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128))));

    public static final Holder<PlacedFeature> MARBLE_PLACED = PlacementUtils.register("marble_placed",
            ModConfiguredFeatures.MARBLE, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.MARBLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-10), VerticalAnchor.aboveBottom(319))));

    public static final Holder<PlacedFeature> MARBLE_BLACK_GALAXY_PLACED = PlacementUtils.register("marble_black_galaxy_placed",
            ModConfiguredFeatures.MARBLE_BLACK_GALAXY, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.MARBLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(10))));

    public static final Holder<PlacedFeature> MARBLE_PERLIN_PINK_PLACED = PlacementUtils.register("marble_perlin_pink_placed",
            ModConfiguredFeatures.MARBLE_PERLIN_PINK, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.MARBLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-10), VerticalAnchor.aboveBottom(319))));

}
