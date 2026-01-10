package net.egorplaytv.caf.entity;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

/*
*  WrappedHandler by noeppi_noeppi
*  under https://github.com/ModdingX/LibX/blob/1.18.2/LICENSE
*
*/
public class WrappedFluidHandler implements IFluidHandler {
    private final IFluidHandler handlerIn;
    private final IFluidHandler handlerOut;

    public WrappedFluidHandler(IFluidHandler handlerIn, IFluidHandler handlerOut) {
        this.handlerIn = handlerIn;
        this.handlerOut = handlerOut;
    }

    @Override
    public int getTanks() {
        return this.handlerIn.getTanks();
    }

    @NotNull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return this.handlerIn.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return this.handlerIn.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return this.handlerIn.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return this.handlerIn.fill(resource, action);
    }

    @NotNull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return handlerOut != null ? handlerOut.drain(resource, action) : FluidStack.EMPTY;
    }

    @NotNull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return handlerOut != null ? handlerOut.drain(maxDrain, action) : FluidStack.EMPTY;
    }

    public static class Out implements IFluidHandler {
        private final IFluidHandler handlerOut;
        public Out(IFluidHandler handlerOut){
            this.handlerOut = handlerOut;
        }

        @Override
        public int getTanks() {
            return handlerOut.getTanks();
        }

        @Override
        public @NotNull FluidStack getFluidInTank(int tank) {
            return handlerOut.getFluidInTank(tank);
        }

        @Override
        public int getTankCapacity(int tank) {
            return handlerOut.getTankCapacity(tank);
        }

        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return handlerOut.isFluidValid(tank, stack);
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0;
        }

        @Override
        public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
            return handlerOut.drain(resource, action);
        }

        @Override
        public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
            return handlerOut.drain(maxDrain, action);
        }
    }
}