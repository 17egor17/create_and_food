package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.METALS;
import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.TYPES;
import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.TAGS;

public class Sample {
    public Sample(){
        //Create


        //Create And Food
        add(ModItems.ALLOY_SOULS.get(), 7, "alloy");


        //Forge
    }

    private static void add(TagKey<Item> pItemTag, int lines, String type, String tag) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (lines > 0 && lines <= 7) {
                METALS.put(holder.value(), lines);
                TYPES.put(holder.value(), type);
                TAGS.put(holder.value(), tag);
            }
        }

    }
    private static void add(ItemLike pItem, int lines, String type) {
        Item item = pItem.asItem();
        if (SharedConstants.IS_RUNNING_IN_IDE) {
            throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack) null).getString() + " a furnace fuel. That will not work!"));
        }
        if (lines > 0 && lines <= 7) {
            METALS.put(item, lines);
            TYPES.put(item, type);
        }
    }
}
