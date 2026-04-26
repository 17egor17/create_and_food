package net.egorplaytv.caf.event;

import net.egorplaytv.caf.CreateAndFood;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class CAFEventServerBusEvents {
    public static class ServerModBusEvents{

    }
}
