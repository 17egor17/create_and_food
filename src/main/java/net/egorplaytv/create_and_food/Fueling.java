package net.egorplaytv.create_and_food;

import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.create_and_food.block.entity.custom.MarbleBlastFurnaceBlockEntity.FUELS;

public class Fueling {
    public Fueling(){
        //Create
        add(AllItems.BLAZE_CAKE.get(), 1000);
        add(AllItems.CREATIVE_BLAZE_CAKE.get(), 1000);

        //Create And Food
        add(ModItems.ALLOY_SOULS_INGOT.get(), 1000);
        add(ModItems.ALLOY_SOULS_SHEET.get(), 1000);
        add(ModItems.ALLOY_SOULS.get(), 1000);
        add(ModItems.ALLOY_SOULS_NUGGET.get(), 100);

        //Minecraft
    }

    private static boolean isNeverAFurnaceFuel(Item pItem) {
        return pItem.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(TagKey<Item> pItemTag, int degree) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (degree > 0 && degree <= 1000) {
                if (!isNeverAFurnaceFuel(holder.value())) {
                    FUELS.put(holder.value(), degree);
                }
            }
        }
    }

    private static void add(ItemLike pItem, int degree) {
        Item item = pItem.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName(null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            if (degree > 0 && degree <= 1000) {
                FUELS.put(item, degree);
            }
        }
    }
}
