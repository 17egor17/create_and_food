package net.egorplaytv.create_and_food.integration;

import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.world.item.Item;
import snownee.jade.addon.harvest.*;
import vectorwing.farmersdelight.common.tag.ModTags;

import static net.egorplaytv.create_and_food.util.ModTags.Blocks.MINEABLE_WITH_HAMMER;
import static vectorwing.farmersdelight.common.registry.ModItems.*;

public class ToolProvider extends HarvestToolProvider {
    public ToolProvider(){
        registerHandler(new SimpleToolHandler("hammer", MINEABLE_WITH_HAMMER, new Item[]{ModItems.STEEL_HAMMER.get()}));
        registerHandler(new SimpleToolHandler("knife", ModTags.MINEABLE_WITH_KNIFE, IRON_KNIFE.get()));
    }
}
