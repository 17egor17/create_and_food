package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.entity.CAFEntityTypes;
import net.egorplaytv.create_and_food.fluid.CAFFluids;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.particle.CAFParticles;
import net.egorplaytv.create_and_food.recipe.AllRecipeTypes;
import net.egorplaytv.create_and_food.recipe.CAFRecipes;
import net.egorplaytv.create_and_food.screen.CAFMenuTypes;
import net.egorplaytv.create_and_food.sound.CAFSounds;
import net.egorplaytv.create_and_food.util.CAFTags;
import net.egorplaytv.create_and_food.villager.CAFVillagers;
import net.egorplaytv.create_and_food.world.structure.CAFStructures;
import net.minecraftforge.eventbus.api.IEventBus;

public class register {
    public register(IEventBus eventBus) {
        CAFItems.register(eventBus);
        CAFBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        CAFMenuTypes.register(eventBus);
        CAFFluids.register(eventBus);
        CAFEntityTypes.register(eventBus);
        CAFSounds.register(eventBus);
        CAFParticles.register(eventBus);
        CAFVillagers.register(eventBus);
        CAFRecipes.register(eventBus);
        AllRecipeTypes.register(eventBus);
        CAFStructures.register(eventBus);
        CAFTags.init();

//        for 1.19.2
//        ModCreativeModeTab.register(eventBus);
    }
}
