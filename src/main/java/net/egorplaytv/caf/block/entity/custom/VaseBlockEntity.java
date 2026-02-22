package net.egorplaytv.caf.block.entity.custom;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.entity.WrappedFluidHandler;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

import static net.egorplaytv.caf.block.custom.VaseBlock.random;

public class VaseBlockEntity extends BlockEntity {
    public final FluidTank FLUID_TANK = new FluidTank(2000){
        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };
    private boolean isOpen = false;

    public VaseBlockEntity(BlockPos pPos, BlockState pBlockState){
        super(CAFBlockEntities.VASE_BLOCK_ENTITY.get(), pPos, pBlockState);
        setFluid(new FluidStack(CAFFluids.NIXIE_FLUID.get(), random(pBlockState)));
    }

    public void setIsOpen(){
        this.isOpen = true;
    }

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluid() {
        return this.FLUID_TANK.getFluid();
    }

    private final LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    private final Map<Direction, LazyOptional<WrappedFluidHandler.Out>> directionWrappedFluidHandlerInMap =
            Map.of(Direction.UP, LazyOptional.of(() -> new WrappedFluidHandler.Out(FLUID_TANK)),
                    Direction.DOWN, LazyOptional.empty(),
                    Direction.WEST, LazyOptional.empty(),
                    Direction.SOUTH, LazyOptional.empty(),
                    Direction.EAST, LazyOptional.empty(),
                    Direction.NORTH, LazyOptional.empty());

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (side == null) {
                return lazyFluidHandler.cast();
            }

            if (isOpen) {
                if (side == Direction.UP) {
                    return directionWrappedFluidHandlerInMap.get(side).cast();
                }
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putBoolean("isOpen", this.isOpen);
        FLUID_TANK.writeToNBT(pTag);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        isOpen = pTag.getBoolean("isOpen");
        FLUID_TANK.readFromNBT(pTag);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, VaseBlockEntity entity) {
    }
    public boolean getIsOpen() {
        return isOpen;
    }
}
