package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.util.Metals;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DependentMetalItem extends MetalItem {
    private final Boolean[] dependent;

    public DependentMetalItem(int meltingPoint, Type type, Metals metalType, Properties pProperties, Boolean... dependent) {
        super(meltingPoint, type, metalType, pProperties);
        this.dependent = dependent;
    }


    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        boolean depend = false;

        for (boolean d : dependent) {
            depend = d;
        }
        if (depend) {
            super.fillItemCategory(category, items);
        }
    }
}