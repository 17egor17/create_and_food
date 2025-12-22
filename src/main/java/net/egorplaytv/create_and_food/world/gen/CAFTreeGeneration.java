package net.egorplaytv.create_and_food.world.gen;

import net.egorplaytv.create_and_food.world.feature.CAFPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CAFTreeGeneration {
    public static void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName()));
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.JUNGLE)
                || types.contains(BiomeDictionary.Type.SAVANNA)) {
            List<Holder<PlacedFeature>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(CAFPlacedFeatures.ALMOND_PLACED);
        }
    }
}
