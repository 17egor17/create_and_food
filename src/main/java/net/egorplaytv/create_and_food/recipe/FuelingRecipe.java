package net.egorplaytv.create_and_food.recipe;

import com.google.common.base.Preconditions;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class FuelingRecipe implements  IJeiFuelingRecipe {
    private final List<ItemStack> inputs;
    private final int degree;

    public FuelingRecipe(ItemStack input, int degree){
        Preconditions.checkArgument(degree > 0, "composting chance must be greater than 0");
        this.inputs = List.of(input);
        this.degree = degree;
    }

    @Override
    public @Unmodifiable List<ItemStack> getInputs() {
        return inputs;
    }

    @Override
    public int getDegree() {
        return degree;
    }
}
