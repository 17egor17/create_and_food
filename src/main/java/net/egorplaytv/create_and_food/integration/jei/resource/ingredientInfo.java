package net.egorplaytv.create_and_food.integration.jei.resource;

import mezz.jei.api.registration.IRecipeRegistration;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.world.item.ItemStack;

import static mezz.jei.api.constants.VanillaTypes.*;

public class ingredientInfo {
    public ingredientInfo(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(ModItems.STEEL_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.TANTALUM_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.TUNGSTEN_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.IRON_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.DIAMOND_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.NETHERITE_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.GOLDEN_KNIFE.get()), ITEM_STACK,
                TextUtils.getJeiTranslation("info.knife"));

    }
}
