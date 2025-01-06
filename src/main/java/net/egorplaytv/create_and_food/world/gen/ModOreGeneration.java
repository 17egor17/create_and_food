package net.egorplaytv.create_and_food.world.gen;

import net.egorplaytv.create_and_food.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event){
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
        base.add(ModPlacedFeatures.RUBY_ORE_PLACED);
        base.add(ModPlacedFeatures.OVERWORLD_TANTALUM_ORE_PLACED);
        base.add(ModPlacedFeatures.NETHER_TANTALUM_ORE_PLACED);
        base.add(ModPlacedFeatures.MARBLE_PLACED);
        base.add(ModPlacedFeatures.MARBLE_BLACK_GALAXY_PLACED);
        base.add(ModPlacedFeatures.MARBLE_PERLIN_PINK_PLACED);
    }
}
