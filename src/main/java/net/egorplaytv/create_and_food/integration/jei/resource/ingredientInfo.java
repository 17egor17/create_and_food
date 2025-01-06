package net.egorplaytv.create_and_food.integration.jei.resource;

import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IRecipeRegistration;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import static mezz.jei.api.constants.VanillaTypes.*;

public class ingredientInfo {
    public ingredientInfo(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(ModItems.STEEL_KNIFE.get()), ITEM_STACK,
                TextUtils.getTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.TANTALUM_KNIFE.get()), ITEM_STACK,
                TextUtils.getTranslation("info.knife"));
        registration.addIngredientInfo(new ItemStack(ModItems.TUNGSTEN_KNIFE.get()), ITEM_STACK,
                TextUtils.getTranslation("info.knife"));

    }
}
