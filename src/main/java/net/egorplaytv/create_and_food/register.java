package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.entity.ModEntityTypes;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.particle.ModParticles;
import net.egorplaytv.create_and_food.recipe.ModRecipes;
import net.egorplaytv.create_and_food.screen.ModMenuTypes;
import net.egorplaytv.create_and_food.sound.ModSounds;
import net.egorplaytv.create_and_food.util.ModTags;
import net.egorplaytv.create_and_food.villager.ModVillagers;
import net.egorplaytv.create_and_food.world.structure.ModStructures;
import net.minecraftforge.eventbus.api.IEventBus;

public class register {
    public register(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModFluids.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModSounds.register(eventBus);
        ModParticles.register(eventBus);
        ModVillagers.register(eventBus);
        ModRecipes.register(eventBus);
        ModStructures.register(eventBus);
        ModTags.init();

//        for 1.19.2
//        ModCreativeModeTab.register(eventBus);
    }
}
