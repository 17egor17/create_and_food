package net.egorplaytv.caf.item.custom.interfaces;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ITickableItem {
    /**
     * This method is called when the item is in the inventory of a block entity.
     * @param stack An item located in the inventory of an entity block.
     */

    void tickInInventory(ItemStack stack, Level level);
}
