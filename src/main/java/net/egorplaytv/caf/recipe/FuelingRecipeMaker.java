package net.egorplaytv.caf.recipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IIngredientManager;
import net.egorplaytv.caf.block.entity.custom.MarbleBlastFurnaceBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FuelingRecipeMaker {
    public static List<IJeiFuelingRecipe> getRecipes(IIngredientManager ingredientManager) {
        Map<Item, Integer> fuels = MarbleBlastFurnaceBlockEntity.FUELS;
        Collection<ItemStack> allIngredients = ingredientManager.getAllIngredients(VanillaTypes.ITEM_STACK);

        return allIngredients.stream()
                .<IJeiFuelingRecipe>mapMulti((itemStack, consumer) -> {
                    Item item = itemStack.getItem();
                    int fuelValue = fuels.getOrDefault(item, 0);
                    if (fuelValue > 0) {
                        FuelingRecipe recipe = new FuelingRecipe(itemStack, fuelValue);
                        consumer.accept(recipe);
                    }
                })
                .limit(fuels.size())
                .sorted(Comparator.comparingDouble(IJeiFuelingRecipe::getDegree))
                .toList();
    }
}
