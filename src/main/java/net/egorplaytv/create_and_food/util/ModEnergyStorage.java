package net.egorplaytv.create_and_food.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage {
    public ModEnergyStorage(int capacity) {
        super(capacity);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractEnergy = super.extractEnergy(maxExtract, simulate);
        if (extractEnergy != 0){
            onEnergyChange();
        }
        return extractEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receiveEnergy = super.receiveEnergy(maxReceive, simulate);
        if (receiveEnergy != 0){
            onEnergyChange();
        }
        return receiveEnergy;
    }

    public int setEnergy(int energy){
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChange();
}
