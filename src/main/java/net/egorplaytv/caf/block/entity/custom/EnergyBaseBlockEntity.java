package net.egorplaytv.caf.block.entity.custom;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.EnergyStorage;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyBaseBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    protected final EnergyStorage energyStorage;

    protected final LazyOptional<IEnergyStorage> lazyEnergyHandler;

    public EnergyBaseBlockEntity(float capacity, BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        this.energyStorage = new EnergyStorage(new CAFEnergyUnits(capacity));
        this.lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EnergyCapability.ENERGY)
            return lazyEnergyHandler.cast();

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putFloat("CAFEnergyUnits", energyStorage.getEnergyStored().getEnergy());
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        energyStorage.setEnergyStored(new CAFEnergyUnits(tag.getFloat("CAFEnergyUnits")));
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide){
            transferEnergy();
        }
    }

    public abstract void transferEnergy();
}
