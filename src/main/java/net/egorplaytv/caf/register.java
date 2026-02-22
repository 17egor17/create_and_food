package net.egorplaytv.caf;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.entity.CAFEntityTypes;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.particle.CAFParticles;
import net.egorplaytv.caf.recipe.AllRecipeTypes;
import net.egorplaytv.caf.recipe.CAFRecipes;
import net.egorplaytv.caf.screen.CAFMenuTypes;
import net.egorplaytv.caf.sound.CAFSounds;
import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.villager.CAFVillagers;
import net.egorplaytv.caf.world.structure.CAFStructures;
import net.minecraftforge.eventbus.api.IEventBus;

public class register {
    public register(IEventBus eventBus) {
        CAFItems.register(eventBus);
        CAFBlocks.register(eventBus);
        CAFBlockEntities.register(eventBus);
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
