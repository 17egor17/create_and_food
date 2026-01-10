package net.egorplaytv.caf.integration.jei.resource;

import mezz.jei.api.registration.IRecipeRegistration;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.world.item.ItemStack;

import static mezz.jei.api.constants.VanillaTypes.*;

public class ingredientInfo {
    public ingredientInfo(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(CAFItems.STEEL_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.TANTALUM_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.TUNGSTEN_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.IRON_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.DIAMOND_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.NETHERITE_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(CAFItems.GOLDEN_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));

    }
}
