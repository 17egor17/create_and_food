package net.egorplaytv.caf.units.energy;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public class CAFEnergyUnits {
    private float energy;

    public CAFEnergyUnits() {
        this.energy = 0F;
    }

    public CAFEnergyUnits(float energy) {
        this.energy = energy;
    }

    public float getEnergy() {
        return ((Math.round(this.energy * 100)) / 100F);
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public static CAFEnergyUnits fromJson(JsonObject json) {
        return new CAFEnergyUnits(GsonHelper.getAsFloat(json, "CAFEnergyUnits", 100));
    }

    public static CAFEnergyUnits fromNetwork(FriendlyByteBuf buf) {
        return new CAFEnergyUnits(buf.readFloat());
    }

    public static void toNetwork(FriendlyByteBuf buf, CAFEnergyUnits value) {
        buf.writeFloat(value.getEnergy());
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putFloat("CAFEnergyUnits", getEnergy());
        return nbt;
    }

    public static CAFEnergyUnits loadCAFEnergyUnitsFromNBT(CompoundTag nbt) {
        if (nbt == null)
            return new CAFEnergyUnits();
        CAFEnergyUnits energyUnits = new CAFEnergyUnits(nbt.getFloat("CAFEnergyUnits"));

        return energyUnits;
    }

    public boolean isEmpty() {
        return energy <= 0;
    }

    public CAFEnergyUnits copy() {
        return new CAFEnergyUnits(energy);
    }



    public static class CAFEnergyUnitsInteger {
        private int energy;

        public CAFEnergyUnitsInteger() {
            this.energy = 0;
        }

        public CAFEnergyUnitsInteger(int energy) {
            this.energy = energy;
        }

        public CAFEnergyUnitsInteger(float energy) {
            this.energy = Math.round(energy);
        }

        public CAFEnergyUnitsInteger(CAFEnergyUnits energy) {
            this.energy = Math.round(energy.getEnergy());
        }

        public int getEnergy() {
            return this.energy;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public static CAFEnergyUnitsInteger fromJson(JsonObject json) {
            return new CAFEnergyUnitsInteger(GsonHelper.getAsInt(json, "CAFEnergyUnits", 100));
        }

        public static CAFEnergyUnitsInteger fromNetwork(FriendlyByteBuf buf) {
            return new CAFEnergyUnitsInteger(buf.readInt());
        }

        public static void toNetwork(FriendlyByteBuf buf, CAFEnergyUnitsInteger value) {
            buf.writeInt(value.getEnergy());
        }

        public CompoundTag writeToNBT(CompoundTag nbt) {
            nbt.putInt("CAFEnergyUnits", getEnergy());
            return nbt;
        }

        public static CAFEnergyUnitsInteger loadCAFEnergyUnitsFromNBT(CompoundTag nbt) {
            if (nbt == null)
                return new CAFEnergyUnitsInteger();
            CAFEnergyUnitsInteger energyUnits = new CAFEnergyUnitsInteger(nbt.getInt("CAFEnergyUnits"));

            return energyUnits;
        }

        public boolean isEmpty() {
            return energy <= 0;
        }

        public CAFEnergyUnitsInteger copy() {
            return new CAFEnergyUnitsInteger(energy);
        }
    }
}
