package net.egorplaytv.create_and_food;

import com.simibubi.create.AllItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.registry.ModItems;

import static net.egorplaytv.create_and_food.block.entity.custom.ScalesBlockEntity.WEIGHT;
import static net.egorplaytv.create_and_food.item.ModItems.*;

public class Weight {
    public Weight(){
        //Create
        add(AllItems.DOUGH.get(), 800);

        //Create And Food
        add(RYE_DOUGH.get(), 800);

        //Farmer's Delight
        add(ModItems.WHEAT_DOUGH.get(), 800);
    }

    private static void add(TagKey<Item> pItemTag, int weight) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (weight > 0) {
                WEIGHT.put(holder.value(), weight);
            }
        }

    }

    private static void add(ItemLike pItem, int weight) {
        Item item = pItem.asItem();
        if (weight > 0) {
            WEIGHT.put(item, weight);
        }
    }

    private static void addBlock(TagKey<Block> pBlockTag, int weight) {
        for(Holder<Block> holder : Registry.BLOCK.getTagOrEmpty(pBlockTag)) {
            if (weight > 0) {
                WEIGHT.put(holder.value().asItem(), weight);
            }
        }

    }

    private static void addBlock(Block pBlock, int weight) {
        Item item = pBlock.asItem();
        if (weight > 0) {
            WEIGHT.put(item, weight);
        }
    }
}
