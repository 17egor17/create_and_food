package net.egorplaytv.caf.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

@ParametersAreNonnullByDefault
public class FreezingRecipe extends ProcessingRecipe<FreezingRecipe.FreezingWrapper> {
    public FreezingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(AllRecipeTypes.FREEZING, params);
    }

    public boolean matches(FreezingWrapper inv, Level pLevel) {
        if (inv.isEmpty())
            return false;
        return ingredients.get(0)
                .test(inv.getItem(0));
    }

    protected int getMaxInputCount() {
        return 1;
    }

    protected int getMaxOutputCount() {
        return 12;
    }

    public static class FreezingWrapper extends RecipeWrapper {
        public FreezingWrapper() {
            super(new ItemStackHandler(1));
        }
    }
}
