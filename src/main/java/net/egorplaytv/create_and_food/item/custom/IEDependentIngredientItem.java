package net.egorplaytv.create_and_food.item.custom;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IEDependentIngredientItem extends Item {
    public IEDependentIngredientItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> itemStacks) {
        if (CreateAndFood.IEIsPresent) {
            super.fillItemCategory(tab, itemStacks);
        }
    }
}
