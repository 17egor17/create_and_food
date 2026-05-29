package net.egorplaytv.caf.block.entity.custom.combi_steamer;

import net.egorplaytv.caf.block.custom.combi_steamer.CombiSteamerEnergyBlock;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CombiSteamerFluidCommunicationBlockEntity extends BlockEntity {
    protected FluidTank fluidInventory = new FluidTank(2000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };
    protected LazyOptional<IFluidHandler> fluidCapability = LazyOptional.of(() -> fluidInventory);

    public CombiSteamerFluidCommunicationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.COMBI_STEAMER_FLUID_COMMUNICATION_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            Direction direction = level.getBlockState(this.worldPosition).getValue(CombiSteamerEnergyBlock.FACING).getOpposite();
            if (side == direction) {
                return fluidCapability.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluidCapability.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("fluidInventory", fluidInventory.getFluid().writeToNBT(new CompoundTag()));
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        fluidInventory.setFluid(FluidStack.loadFluidStackFromNBT(pTag.getCompound("fluidInventory")));
    }
}
