package net.egorplaytv.caf.units.degree;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public class CAFDegreeUnits {
    private float degree;

    public static CAFDegreeUnits EMPTY = new CAFDegreeUnits(0F);

    public CAFDegreeUnits(float degree) {
        this.degree = degree;
    }

    public float getDegree() {
        return ((Math.round(this.degree * 100)) / 100F);
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }


    public static CAFDegreeUnits fromJson(JsonObject json) {
        return new CAFDegreeUnits(GsonHelper.getAsFloat(json, "degree", 100));
    }

    public static CAFDegreeUnits fromNetwork(FriendlyByteBuf buf) {
        return new CAFDegreeUnits(buf.readFloat());
    }

    public static void toNetwork(FriendlyByteBuf buf, CAFDegreeUnits value) {
        buf.writeFloat(value.getDegree());
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putFloat("CAFDegreeUnits", getDegree());
        return nbt;
    }

    public static CAFDegreeUnits loadFromNBT(CompoundTag nbt) {
        if (nbt == null)
            return CAFDegreeUnits.EMPTY;
        CAFDegreeUnits energyUnits = new CAFDegreeUnits(nbt.getFloat("CAFDegreeUnits"));

        return energyUnits;
    }

    public boolean isEmpty() {
        return degree <= 0;
    }

    public CAFDegreeUnits copy() {
        return new CAFDegreeUnits(degree);
    }



    public static class CAFDegreeUnitsInteger {
        private int degree;

        public CAFDegreeUnitsInteger() {
            this.degree = 0;
        }

        public CAFDegreeUnitsInteger(int degree) {
            this.degree = degree;
        }

        public CAFDegreeUnitsInteger(float degree) {
            this.degree = Math.round(degree);
        }

        public CAFDegreeUnitsInteger(CAFDegreeUnits degree) {
            this.degree = Math.round(degree.getDegree());
        }

        public int getDegree() {
            return this.degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public static CAFDegreeUnitsInteger fromJson(JsonObject json) {
            return new CAFDegreeUnitsInteger(GsonHelper.getAsFloat(json, "degree", 100));
        }

        public static CAFDegreeUnitsInteger fromNetwork(FriendlyByteBuf buf) {
            return new CAFDegreeUnitsInteger(buf.readFloat());
        }

        public static void toNetwork(FriendlyByteBuf buf, CAFDegreeUnitsInteger value) {
            buf.writeFloat(value.getDegree());
        }

        public CompoundTag writeToNBT(CompoundTag nbt) {
            nbt.putFloat("CAFDegreeUnits", getDegree());
            return nbt;
        }

        public static CAFDegreeUnitsInteger loadFromNBT(CompoundTag nbt) {
            if (nbt == null)
                return new CAFDegreeUnitsInteger();
            CAFDegreeUnitsInteger energyUnits = new CAFDegreeUnitsInteger(nbt.getFloat("CAFDegreeUnits"));

            return energyUnits;
        }

        public boolean isEmpty() {
            return degree <= 0;
        }

        public CAFDegreeUnitsInteger copy() {
            return new CAFDegreeUnitsInteger(degree);
        }
    }
}
