package net.egorplaytv.create_and_food.integration.jei.resource;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ingredientInfo {
    public ingredientInfo(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack((ItemLike) ModItems.STEEL_KNIFE.get()), VanillaTypes.ITEM_STACK,
                TextUtils.getTranslation("info.knife"));

    }
}
