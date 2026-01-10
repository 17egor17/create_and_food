package net.egorplaytv.caf.villager;

import com.google.common.collect.ImmutableSet;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class CAFVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, CreateAndFood.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.PROFESSIONS, CreateAndFood.MOD_ID);

    public static final RegistryObject<PoiType> CONFECTIONER_POI = POI_TYPES.register("confectioner_poi",
            () -> new PoiType("confectioner_poi", PoiType.getBlockStates(CAFBlocks.PASTRY_TABLE.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> CONFECTIONER =
            VILLAGER_PROFESSIONS.register("confectioner",
                    () -> new VillagerProfession("confectioner", CONFECTIONER_POI.get(),
                            ImmutableSet.of(CAFItems.BASE_OF_DOUGH.get(), Items.GLOW_BERRIES), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, CONFECTIONER_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {

        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}