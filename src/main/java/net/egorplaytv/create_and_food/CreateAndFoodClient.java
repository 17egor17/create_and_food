package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.block.custom.connect.ModPartialModels;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CreateAndFoodClient {
    public CreateAndFoodClient() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModPartialModels.init();
        eventBus.register(this);
    }


}
