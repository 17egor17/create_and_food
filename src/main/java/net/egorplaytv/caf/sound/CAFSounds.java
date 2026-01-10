package net.egorplaytv.caf.sound;

import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CAFSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CreateAndFood.MOD_ID);

    public static final RegistryObject<SoundEvent> RUBY_BREAK =
            registerSoundEvent("ruby_break");

    public static final RegistryObject<SoundEvent> RUBY_STEP =
            registerSoundEvent("ruby_step");

    public static final RegistryObject<SoundEvent> RUBY_PLACE =
            registerSoundEvent("ruby_place");

    public static final RegistryObject<SoundEvent> RUBY_HIT =
            registerSoundEvent("ruby_hit");

    public static final RegistryObject<SoundEvent> BAKED_CLAY_BREAK =
            registerSoundEvent("baked_clay_break");

    public static final RegistryObject<SoundEvent> BAKED_CLAY_STEP =
            registerSoundEvent("baked_clay_step");

    public static final RegistryObject<SoundEvent> BAKED_CLAY_PLACE =
            registerSoundEvent("baked_clay_place");

    public static final RegistryObject<SoundEvent> BAKED_CLAY_HIT =
            registerSoundEvent("baked_clay_hit");

    public static final RegistryObject<SoundEvent> STEEL_RUBY_LAMP_BREAK =
            registerSoundEvent("steel_ruby_lamp_break");

    public static final RegistryObject<SoundEvent> STEEL_RUBY_LAMP_STEP =
            registerSoundEvent("steel_ruby_lamp_step");

    public static final RegistryObject<SoundEvent> STEEL_RUBY_LAMP_PLACE =
            registerSoundEvent("steel_ruby_lamp_place");

    public static final RegistryObject<SoundEvent> STEEL_RUBY_LAMP_HIT =
            registerSoundEvent("steel_ruby_lamp_hit");

    public static final RegistryObject<SoundEvent> SOULS =
            registerSoundEvent("souls");

    public static final ForgeSoundType BAKED_CLAY = new ForgeSoundType(1f,1f,
            CAFSounds.BAKED_CLAY_BREAK, CAFSounds.BAKED_CLAY_STEP, CAFSounds.BAKED_CLAY_PLACE,
            CAFSounds.BAKED_CLAY_HIT, CAFSounds.BAKED_CLAY_STEP);

    public static final ForgeSoundType STEEL_RUBY_LAMP = new ForgeSoundType(1f,1f,
            CAFSounds.STEEL_RUBY_LAMP_BREAK, CAFSounds.STEEL_RUBY_LAMP_STEP, CAFSounds.STEEL_RUBY_LAMP_PLACE,
            CAFSounds.STEEL_RUBY_LAMP_HIT, CAFSounds.STEEL_RUBY_LAMP_STEP);

    public static final ForgeSoundType SOULS_VOICE = new ForgeSoundType(1f,1f,
            CAFSounds.SOULS, CAFSounds.SOULS, CAFSounds.SOULS,
            CAFSounds.SOULS, CAFSounds.SOULS);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(CreateAndFood.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
