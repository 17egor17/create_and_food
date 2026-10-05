package net.egorplaytv.caf.item.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DependentKeyItem extends KeyItem {
    private final Boolean[] dependent;

    public DependentKeyItem(Properties pProperties, Boolean... dependent) {
        super(pProperties);
        this.dependent = dependent;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        boolean depend = false;

        for (boolean d : dependent) {
            depend = d;
        }
        if (depend) {
            super.fillItemCategory(pCategory, pItems);
        }
    }
}
