package net.egorplaytv.caf.entity;

import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;

public class WrappedEnergyHandler implements IEnergyStorage {
    private final IEnergyStorage storage;

    public WrappedEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public CAFEnergyUnits receiveEnergy(float maxReceive, boolean simulate) {
        return null;
    }

    @Override
    public CAFEnergyUnits extractEnergy(float maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public CAFEnergyUnits getEnergyStored() {
        return storage.getEnergyStored();
    }

    @Override
    public float getMaxEnergyStored() {
        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return storage.canExtract();
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    @Override
    public float getMaxTransfer() {
        return storage.getMaxTransfer();
    }
}
