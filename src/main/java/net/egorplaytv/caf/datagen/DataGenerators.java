package net.egorplaytv.caf.datagen;

import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new CAFRecipeProvider(generator));
        generator.addProvider(new CAFLootTableProvider(generator));
        generator.addProvider(new CAFBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new CAFItemModelProvider(generator, existingFileHelper));
    }
}
