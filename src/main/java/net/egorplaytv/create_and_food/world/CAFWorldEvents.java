package net.egorplaytv.create_and_food.world;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.world.gen.CAFOreGeneration;
import net.egorplaytv.create_and_food.world.gen.CAFTreeGeneration;
import net.egorplaytv.create_and_food.world.gen.CAFWildBushGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID)
public class CAFWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        CAFOreGeneration.generateOres(event);
        CAFTreeGeneration.generateTrees(event);
        CAFWildBushGeneration.generateWildBush(event);
    }
}
