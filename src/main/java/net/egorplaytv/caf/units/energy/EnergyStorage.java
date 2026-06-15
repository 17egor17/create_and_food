package net.egorplaytv.caf.units.energy;

import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class EnergyStorage implements IEnergyStorage, INBTSerializable<Tag> {
    protected CAFEnergyUnits energy;
    protected CAFEnergyUnits capacity;
    protected float maxReceive;
    protected float maxExtract;

    public EnergyStorage(int capacity) {
        this(new CAFEnergyUnits(capacity), capacity, capacity, new CAFEnergyUnits());
    }

    public EnergyStorage(CAFEnergyUnits capacity) {
        this(capacity, capacity.getEnergy(), capacity.getEnergy(), new CAFEnergyUnits());
    }

    public EnergyStorage(CAFEnergyUnits capacity, float maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer, new CAFEnergyUnits());
    }

    public EnergyStorage(CAFEnergyUnits capacity, float maxReceive, float maxExtract)
    {
        this(capacity, maxReceive, maxExtract, new CAFEnergyUnits());
    }

    public EnergyStorage(CAFEnergyUnits capacity, float maxReceive, float maxExtract, CAFEnergyUnits energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = new CAFEnergyUnits(Math.max(0 , Math.min(new CAFEnergyUnits.CAFEnergyUnitsInteger(capacity).getEnergy(), new CAFEnergyUnits.CAFEnergyUnitsInteger(energy).getEnergy())));
    }

    @Override
    public CAFEnergyUnits receiveEnergy(float maxReceive, boolean simulate) {
        if (!canReceive())
            return new CAFEnergyUnits();

        CAFEnergyUnits energyReceived = new CAFEnergyUnits(Math.min(capacity.getEnergy() - energy.getEnergy(), Math.min(this.maxReceive, maxReceive)));
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getEnergy() + energyReceived.getEnergy());
        return energyReceived;
    }

    @Override
    public CAFEnergyUnits extractEnergy(float maxExtract, boolean simulate) {
        if (!canExtract())
            return new CAFEnergyUnits();

        CAFEnergyUnits energyExtracted = new CAFEnergyUnits(Math.min(energy.getEnergy(), Math.min(this.maxExtract, maxExtract)));
        if (!simulate)
            energy = new CAFEnergyUnits(energy.getEnergy() - energyExtracted.getEnergy());
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
    public CAFEnergyUnits getMaxEnergyStored() {
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
    public Tag serializeNBT()
    {
        return FloatTag.valueOf(this.getEnergyStored().getEnergy());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (!(nbt instanceof FloatTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.energy = new CAFEnergyUnits(intNbt.getAsFloat());
    }
}