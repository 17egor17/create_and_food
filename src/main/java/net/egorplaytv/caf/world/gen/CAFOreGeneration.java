package net.egorplaytv.caf.world.gen;

import net.egorplaytv.caf.world.feature.CAFPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class CAFOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event){
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
        base.add(CAFPlacedFeatures.RUBY_ORE_PLACED);
        base.add(CAFPlacedFeatures.OVERWORLD_TANTALUM_ORE_PLACED);
        base.add(CAFPlacedFeatures.NETHER_TANTALUM_ORE_PLACED);
        base.add(CAFPlacedFeatures.MARBLE_PLACED_UPPER);
        base.add(CAFPlacedFeatures.MARBLE_PLACED_LOWER);
        base.add(CAFPlacedFeatures.MARBLE_BLACK_GALAXY_PLACED_UPPER);
        base.add(CAFPlacedFeatures.MARBLE_BLACK_GALAXY_PLACED_LOWER);
        base.add(CAFPlacedFeatures.MARBLE_PERLIN_PINK_PLACED_UPPER);
        base.add(CAFPlacedFeatures.MARBLE_PERLIN_PINK_PLACED_LOWER);
    }
}
