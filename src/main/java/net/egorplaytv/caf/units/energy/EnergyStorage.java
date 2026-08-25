package net.egorplaytv.caf.units.energy;

import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class EnergyStorage implements IEnergyStorage, INBTSerializable<CompoundTag> {
    protected CAFEnergyUnits energy;
    protected float capacity;
    protected float maxReceive;
    protected float maxExtract;

    public EnergyStorage(float capacity, float amperage) {
        this(capacity, amperage, capacity, capacity, 0F);
    }

    public EnergyStorage(float capacity, float amperage, float maxTransfer) {
        this(capacity, amperage, maxTransfer, maxTransfer, 0F);
    }

    public EnergyStorage(float capacity, float amperage, float maxReceive, float maxExtract) {
        this(capacity, amperage, maxReceive, maxExtract, 0F);
    }

    public EnergyStorage(float capacity, float amperage, float maxReceive, float maxExtract, float energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = new CAFEnergyUnits(Math.max(0, Math.min(capacity, energy)), amperage);
    }

    @Override
    public CAFEnergyUnits receiveEnergy(float maxReceive, boolean simulate) {
        if (!canReceive())
            return CAFEnergyUnits.EMPTY;

        CAFEnergyUnits energyReceived = new CAFEnergyUnits(Math.min(capacity - energy.getRawEnergy(), Math.min(this.maxReceive, maxReceive)), energy.getRawAmperage());
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getRawEnergy() + energyReceived.getRawEnergy(), energy.getRawAmperage());
        return energyReceived;
    }

    @Override
    public CAFEnergyUnits extractEnergy(float maxExtract, boolean simulate) {
        if (!canExtract())
            return CAFEnergyUnits.EMPTY;

        CAFEnergyUnits energyExtracted = new CAFEnergyUnits(Math.min(energy.getRawEnergy(), Math.min(this.maxExtract, maxExtract)), energy.getRawAmperage());
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getRawEnergy() - energyExtracted.getRawEnergy(), energy.getRawAmperage());
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
        tag.putFloat("CAFEnergyAmperage", this.getEnergyStored().getRawAmperage());

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.energy = new CAFEnergyUnits(nbt.getFloat("CAFEnergy"), nbt.getFloat("CAFEnergyAmperage"));
    }
}