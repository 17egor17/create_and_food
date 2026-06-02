package net.egorplaytv.caf.world;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.config.CAFConfigs;
import net.egorplaytv.caf.world.gen.CAFOreGeneration;
import net.egorplaytv.caf.world.gen.CAFTreeGeneration;
import net.egorplaytv.caf.world.gen.CAFWildBushGeneration;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID)
public class CAFWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        CAFOreGeneration.generateOres(event);
        CAFTreeGeneration.generateTrees(event);
        if (CAFConfigs.common().worldGen.genCAFWildBushes.get())
            CAFWildBushGeneration.generateWildBush(event);
    }


    /**
    * This method delete PlacedFeature from {@link net.minecraft.data.worldgen.features.VegetationFeatures }
    */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        String biome = event.getName().getPath();

        if (!CAFConfigs.common().worldGen.genCAFWildBushes.get())
            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(holder -> {
                PlacedFeature placedFeature = holder.value();
                Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder = placedFeature.feature();

                ResourceLocation name = configuredFeatureHolder.unwrapKey().orElse(null).location();

                    if (name.equals(new ResourceLocation("patch_melon"))) {
                        System.out.println(CreateAndFood.CAFDebugFormat() + "PlacedFeature in " + biome + ": " + name + " deleted");
                    } else if (name.equals(new ResourceLocation("patch_pumpkin"))) {
                        System.out.println(CreateAndFood.CAFDebugFormat() + "PlacedFeature in " + biome + ": " + name + " deleted");
                    } else if (name.equals(new ResourceLocation("patch_berry_bush"))) {
                        System.out.println(CreateAndFood.CAFDebugFormat() + "PlacedFeature in " + biome + ": " + name + " deleted");
                    }

                   return  name.equals(new ResourceLocation("patch_melon")) ||
                            name.equals(new ResourceLocation("patch_pumpkin")) ||
                            name.equals(new ResourceLocation("patch_berry_bush"));
            });
    }
}
