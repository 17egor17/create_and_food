package net.egorplaytv.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.units.weight.CAFWeightUnits;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.registry.ModItems;

import static net.egorplaytv.caf.item.CAFItems.*;
import static net.egorplaytv.caf.units.weight.CAFWeightUnits.WEIGHT_ITEMS;

public class Weight {
    public Weight(){
        //Create
        add(AllItems.DOUGH.get(), 800);

        //Create And Food
        add(RYE_DOUGH.get(), 800);

        //Farmer's Delight
        add(ModItems.WHEAT_DOUGH.get(), 800);
    }

    private static void add(ItemLike pItem, float weight) {
        Item item = pItem.asItem();
        if (weight > 0) {
            WEIGHT_ITEMS.put(item, new CAFWeightUnits(weight));
        }
    }

    private static void addBlock(Block pBlock, float weight) {
        Item item = pBlock.asItem();
        if (weight > 0) {
            WEIGHT_ITEMS.put(item, new CAFWeightUnits(weight));
        }
    }
}
