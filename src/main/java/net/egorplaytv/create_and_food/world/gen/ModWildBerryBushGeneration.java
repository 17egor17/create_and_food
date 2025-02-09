package net.egorplaytv.create_and_food.world.gen;

import net.egorplaytv.create_and_food.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;

public class ModWildBerryBushGeneration {
    public static void generateWildBerryBush(final BiomeLoadingEvent event){
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (types.contains(BiomeDictionary.Type.OVERWORLD)) {
            List<Holder<PlacedFeature>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            base.add(ModPlacedFeatures.WILD_BLUEBERRY_BUSH_PLACED);
            base.add(ModPlacedFeatures.WILD_CRANBERRY_BUSH_PLACED);
            base.add(ModPlacedFeatures.WILD_BLUE_GRAPE_BUSH_PLACED);
        }
    }
}
