package net.egorplaytv.create_and_food.entity;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CAFEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, CreateAndFood.MOD_ID);


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
