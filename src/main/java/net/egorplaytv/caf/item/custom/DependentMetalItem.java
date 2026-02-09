package net.egorplaytv.caf.item.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DependentMetalItem extends MetalItem {
    private final Boolean[] dependent;

    public DependentMetalItem(int meltingPoint, Type type, Properties pProperties, Boolean... dependent) {
        super(meltingPoint, type, pProperties);
        this.dependent = dependent;
    }


    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        for (boolean depend : dependent){
            if (depend){
                super.fillItemCategory(category, items);
            }
        }
    }
}