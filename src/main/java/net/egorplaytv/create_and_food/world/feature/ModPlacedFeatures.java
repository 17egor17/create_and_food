package net.egorplaytv.create_and_food.world.feature;

import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> ALMOND_PLACED = PlacementUtils.register("almond_placed",
            ModConfiguredFeatures.ALMOND_SPAWN, VegetationPlacements.treePlacement(
            PlacementUtils.countExtra(3,01f,2)));

    public static final Holder<PlacedFeature> RUBY_ORE_PLACED = PlacementUtils.register("ruby_ore_placed",
            ModConfiguredFeatures.RUBY_ORE, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.RUBY_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> OVERWORLD_TANTALUM_ORE_PLACED = PlacementUtils.register("overworld_tantalum_ore_placed",
            ModConfiguredFeatures.OVERWORLD_TANTALUM_ORE, ModOrePlacement.commonOrePlacement(
                    CreateAndFoodCommonConfigs.TUNTAL_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(10))));

    public static final Holder<PlacedFeature> NETHER_TANTALUM_ORE_PLACED = PlacementUtils.register("nether_tantalum_ore_placed",
            ModConfiguredFeatures.NETHER_TANTALUM_ORE, ModOrePlacement.commonOrePlacement(
                    CreateAndFoodCommonConfigs.TUNTAL_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-256), VerticalAnchor.aboveBottom(319))));

    public static final Holder<PlacedFeature> MARBLE_PLACED = PlacementUtils.register("marble_placed",
            ModConfiguredFeatures.MARBLE, ModOrePlacement.commonOrePlacement(CreateAndFoodCommonConfigs.MARBLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-10), VerticalAnchor.aboveBottom(128))));
}
