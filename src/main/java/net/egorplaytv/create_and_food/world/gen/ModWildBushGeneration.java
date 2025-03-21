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

public class ModWildBushGeneration {
    public static void generateWildBush(final BiomeLoadingEvent event){
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
        if (!types.contains(BiomeDictionary.Type.JUNGLE)) {
            base.add(ModPlacedFeatures.WILD_PUMPKIN_PLACED);
        }

        if (types.contains(BiomeDictionary.Type.JUNGLE)){
            base.add(ModPlacedFeatures.WILD_MELON_PLACED);
            base.add(ModPlacedFeatures.WILD_PUMPKIN_IN_JUNGLE_PLACED);
        }

        if (types.contains(BiomeDictionary.Type.SWAMP) || types.contains(BiomeDictionary.Type.FOREST)
        || types.contains(BiomeDictionary.Type.SAVANNA) || types.contains(BiomeDictionary.Type.SNOWY)) {
            base.add(ModPlacedFeatures.WILD_BLUEBERRY_BUSH_PLACED);
        }
        if (types.contains(BiomeDictionary.Type.SWAMP) || types.contains(BiomeDictionary.Type.SNOWY)){
            base.add(ModPlacedFeatures.WILD_CRANBERRY_BUSH_PLACED);
        }
        if (types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.PLAINS)){
            base.add(ModPlacedFeatures.WILD_RASPBERRY_BUSH_PLACED);
        }
        if (types.contains(BiomeDictionary.Type.SAVANNA) || types.contains(BiomeDictionary.Type.PLAINS)
        || types.contains(BiomeDictionary.Type.JUNGLE) || types.contains(BiomeDictionary.Type.HILLS)){
            base.add(ModPlacedFeatures.WILD_BLUE_GRAPE_BUSH_PLACED);
            base.add(ModPlacedFeatures.WILD_GREEN_GRAPE_BUSH_PLACED);
            base.add(ModPlacedFeatures.WILD_PURPLE_GRAPE_BUSH_PLACED);
            base.add(ModPlacedFeatures.WILD_RED_GRAPE_BUSH_PLACED);
        }
    }
}
