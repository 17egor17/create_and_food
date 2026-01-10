package net.egorplaytv.caf.world;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.world.gen.CAFOreGeneration;
import net.egorplaytv.caf.world.gen.CAFTreeGeneration;
import net.egorplaytv.caf.world.gen.CAFWildBushGeneration;
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
