package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.util.CAFTags;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.*;

public class Sample {
    public Sample(){
        //Create


        //Create And Food
        add(CAFTags.Items.TANTALUM, 7, "alloy", "tantalum");


        //Forge
    }

    // Don't work
    private static void add(TagKey<Item> pItemTag, int lines, String type, String tagName){
        add(pItemTag, lines, 1, type, tagName);
    }

    // Don't work
    private static void add(TagKey<Item> pItemTag, int lines, int countIngredientLines, String type, String tagName) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (lines > 0 && lines <= 7) {
                METALS.put(holder.value(), lines);
                INGREDIENTS_LINES.put(holder.value(), countIngredientLines);
                TYPES.put(holder.value(), type);
                TAGS.put(holder.value(), tagName);
            }
        }

    }

    // Work
    private static void add(ItemLike pItem, int lines, String type){
        add(pItem, lines, 1, type);
    }

    // Work
    private static void add(ItemLike pItem, int lines, int countIngredientLines, String type) {
        Item item = pItem.asItem();
        if (SharedConstants.IS_RUNNING_IN_IDE) {
            throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack) null).getString() + " a furnace fuel. That will not work!"));
        }
        if (lines > 0 && lines <= 7) {
            METALS.put(item, lines);
            INGREDIENTS_LINES.put(item, countIngredientLines);
            TYPES.put(item, type);
        }
    }
}
