package net.egorplaytv.caf.units.energy;

import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class EnergyStorage implements IEnergyStorage, INBTSerializable<CompoundTag> {
    protected CAFEnergyUnits energy;
    protected float capacity;
    protected float maxReceive;
    protected float maxExtract;

    public EnergyStorage(float capacity, float dimperage) {
        this(capacity, dimperage, capacity, capacity, 0F);
    }

    public EnergyStorage(float capacity, float dimperage, float maxTransfer) {
        this(capacity, dimperage, maxTransfer, maxTransfer, 0F);
    }

    public EnergyStorage(float capacity, float dimperage, float maxReceive, float maxExtract) {
        this(capacity, dimperage, maxReceive, maxExtract, 0F);
    }

    public EnergyStorage(float capacity, float dimperage, float maxReceive, float maxExtract, float energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = new CAFEnergyUnits(Math.max(0, Math.min(capacity, energy)), dimperage);
    }

    @Override
    public CAFEnergyUnits receiveEnergy(float maxReceive, float dimperage, boolean simulate) {
        if (!canReceive())
            return CAFEnergyUnits.EMPTY;

        CAFEnergyUnits energyReceived = new CAFEnergyUnits(Math.min(capacity - energy.getRawEnergy(), Math.min(this.maxReceive, maxReceive)), dimperage);
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getRawEnergy() + energyReceived.getRawEnergy(), dimperage);
        return energyReceived;
    }

    @Override
    public CAFEnergyUnits extractEnergy(float maxExtract, float dimperage, boolean simulate) {
        if (!canExtract())
            return CAFEnergyUnits.EMPTY;

        CAFEnergyUnits energyExtracted = new CAFEnergyUnits(Math.min(energy.getRawEnergy(), Math.min(this.maxExtract, maxExtract)), dimperage);
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getRawEnergy() - energyExtracted.getRawEnergy(), dimperage);
        return energyExtracted;
    }

    public void setEnergyStored(CAFEnergyUnits energy) {
        this.energy = energy;
    }

    @Override
    public CAFEnergyUnits getEnergyStored() {
        return energy;
    }

    @Override
    public float getMaxEnergyStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public float getMaxTransfer() {
        return maxReceive;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("CAFEnergy", this.getEnergyStored().getRawEnergy());
        tag.putFloat("CAFEnergyDimperage", this.getEnergyStored().getRawDimperage());

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.energy = new CAFEnergyUnits(nbt.getFloat("CAFEnergy"), nbt.getFloat("CAFEnergyDimperage"));
    }
}