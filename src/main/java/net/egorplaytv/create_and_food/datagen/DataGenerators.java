package net.egorplaytv.create_and_food.datagen;

import net.egorplaytv.create_and_food.CreateAndFood;
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

        generator.addProvider(new ModRecipeProvider(generator));
        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
    }
}
