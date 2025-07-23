package net.egorplaytv.create_and_food.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier pModifier,PlacementModifier pModify){
        return List.of(pModifier, InSquarePlacement.spread(), pModify, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pModifier){
        return orePlacement(CountPlacement.of(pCount), pModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pModifier){
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pModifier);
    }
}
