package net.egorplaytv.create_and_food.recipe;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import javax.annotation.Nonnegative;
import java.util.List;

public interface IJeiFuelingRecipe {

    @Unmodifiable
    List<ItemStack> getInputs();

    @Nonnegative
    int getDegree();
}
