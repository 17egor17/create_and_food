package net.egorplaytv.create_and_food.entity;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/*
*  WrappedHandler by noeppi_noeppi
*  under https://github.com/ModdingX/LibX/blob/1.18.2/LICENSE
*
*/
public class WrappedFluidHandlerOut implements IFluidHandler {
    private final IFluidHandler handler;

    public WrappedFluidHandlerOut(IFluidHandler handler) {
        this.handler = handler;
    }

    @Override
    public int getTanks() {
        return this.handler.getTanks();
    }

    @NotNull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return this.handler.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return this.handler.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return this.handler.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return 0;
    }

    @NotNull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return this.handler.drain(resource, action);
    }

    @NotNull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return this.handler.drain(maxDrain, action);
    }
}