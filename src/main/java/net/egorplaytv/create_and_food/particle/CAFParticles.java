package net.egorplaytv.create_and_food.particle;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CAFParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CreateAndFood.MOD_ID);

    public static final RegistryObject<SimpleParticleType> SOUL_PARTICLES =
            PARTICLE_TYPES.register("soul_particles",
                    () -> new SimpleParticleType(true));



    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}