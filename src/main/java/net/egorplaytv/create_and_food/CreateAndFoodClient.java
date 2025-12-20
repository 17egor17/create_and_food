package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.block.custom.connect.ModPartialModels;
import net.egorplaytv.create_and_food.datagen.custom.ModItemModelsProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class CreateAndFoodClient {
    public CreateAndFoodClient() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModPartialModels.init();
        eventBus.addListener(this::modelRegistryEvent);
        eventBus.addListener(this::registerEntityRenderers);
        eventBus.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    public void modelRegistryEvent(ModelRegistryEvent event) {
        ModItemModelsProperties.init();
    }

    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        InitEntityRendering.init(event::registerEntityRenderer);
    }
}
