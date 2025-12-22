package net.egorplaytv.create_and_food.event;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CAFEventClientBusEvents {
    public static class ClientModBusEvents{
    }
}
