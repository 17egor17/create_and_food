package net.egorplaytv.create_and_food.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.NonNull;


public class LegacyRecipe extends CustomRecipe {
    public LegacyRecipe(ResourceLocation pId) {
        super(pId);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack firstStack = pContainer.getItem(0);
        boolean mixed = false;

        for(int i = 0; i < 9; ++i){
            ItemStack stack = pContainer.getItem(i);
            if(i == 4){
                if (!stack.isEmpty()) {
                    return false;
                }
            } else {
                if (stack.isEmpty() || !stack.is(ItemTags.PLANKS)) {
                    return false;
                }

                if (!ItemStack.matches(firstStack, stack)) {
                    mixed = true;
                }
            }
        }

        return mixed;
    }

    @NonNull
    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 3 && pHeight >= 3;
    }

    @NonNull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.LEGACY_RECIPE.get();
    }
}
