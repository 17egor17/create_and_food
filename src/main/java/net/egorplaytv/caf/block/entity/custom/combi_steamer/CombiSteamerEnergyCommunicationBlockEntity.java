package net.egorplaytv.caf.block.entity.custom.combi_steamer;

import net.egorplaytv.caf.block.custom.combi_steamer.CombiSteamerEnergyBlock;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.energy.EnergyStorage;
import net.egorplaytv.caf.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CombiSteamerEnergyCommunicationBlockEntity extends BlockEntity {
    protected EnergyStorage energyStorage = new EnergyStorage(10000);
    protected LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energyStorage);

    public CombiSteamerEnergyCommunicationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.COMBI_STEAMER_ENERGY_COMMUNICATION_ENTITY.get(), pPos, pBlockState);
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EnergyCapability.ENERGY) {
            Direction direction = level.getBlockState(this.worldPosition).getValue(CombiSteamerEnergyBlock.FACING).getOpposite();
            if (side == direction) {
                return energyCapability.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCapability.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        energyStorage.setEnergyStored(pTag.getInt("energy"));
    }
}
