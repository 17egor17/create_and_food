package net.egorplaytv.create_and_food.recipe;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public class CAFRecipesList {
    static List<PolishingRecipe> polishing;

    static public void init(MinecraftServer level) {
        polishing = level.getRecipeManager().getAllRecipesFor(AllRecipeTypes.POLISHING.getType());
    }

    static public boolean isPolishing(Recipe<?> r) {
        if (polishing == null) return true;
        if (polishing.isEmpty()) return true;

        for (PolishingRecipe recipe : polishing)
            if (recipe.getResultItem().getItem() == r.getResultItem().getItem())  return false;

        return true;
    }
}
