package net.egorplaytv.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.caf.block.entity.custom.MarbleBlastFurnaceBlockEntity.FUELS;

public class Fueling {
    public Fueling(){
        //Create
        add(AllItems.BLAZE_CAKE.get(), 1000);
        add(AllItems.CREATIVE_BLAZE_CAKE.get(), 1000);

        //Create And Food
        add(CAFItems.ALLOY_SOULS_INGOT.get(), 1000);
        add(CAFItems.ALLOY_SOULS_SHEET.get(), 1000);
        add(CAFItems.ALLOY_SOULS.get(), 1000);
        add(CAFItems.ALLOY_SOULS_NUGGET.get(), 100);

        //Minecraft
    }

    private static boolean isNeverAFurnaceFuel(Item pItem) {
        return pItem.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
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
