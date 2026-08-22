package net.egorplaytv.caf.units.energy;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public class CAFEnergyUnits {
    private float energy;
    private float amperage;

    public static CAFEnergyUnits EMPTY = new CAFEnergyUnits(0F, 0F);

    public CAFEnergyUnits(float energy) {
        this(energy, 0F);
    }

    public CAFEnergyUnits(float energy, float amperage) {
        this.energy = energy;
        this.amperage = amperage;
    }

    public float getRawEnergy() {
        return this.energy;
    }

    public float getEnergy() {
        return ((Math.round(this.energy * 100)) / 100F);
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getRawAmperage() {
        return this.amperage;
    }

    public float getAmperage() {
        return ((Math.round(this.amperage * 100)) / 100F);
    }

    public void setAmperage(float amperage) {
        this.amperage = amperage;
    }

    public float getValueDamage() {
        return ((amperage * getRawEnergy()) / ((getRawEnergy() / 2) * 1000));
    }

    public boolean isEmpty() {
        return energy <= 0;
    }

    public CAFEnergyUnits copy() {
        return new CAFEnergyUnits(getRawEnergy(), getRawAmperage());
    }

    public boolean is(CAFEnergyUnits energy) {
        return this.energy == energy.energy && this.amperage == energy.amperage;
    }



    public static CAFEnergyUnits fromJson(JsonObject json) {
        return new CAFEnergyUnits(GsonHelper.getAsFloat(json, "CAFEnergy", 100),
                GsonHelper.getAsFloat(json, "CAFEnergyAmperage", 100));
    }

    public static CAFEnergyUnits fromNetwork(FriendlyByteBuf buf) {
        return new CAFEnergyUnits(buf.readFloat(), buf.readFloat());
    }

    public static void toNetwork(FriendlyByteBuf buf, CAFEnergyUnits value) {
        buf.writeFloat(value.getRawEnergy());
        buf.writeFloat(value.getRawAmperage());
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putFloat("CAFEnergy", getRawEnergy());
        nbt.putFloat("CAFEnergyAmperage", getRawAmperage());
        return nbt;
    }

    public static CAFEnergyUnits loadFromNBT(CompoundTag nbt) {
        if (nbt == null)
            return CAFEnergyUnits.EMPTY;

        return new CAFEnergyUnits(nbt.getFloat("CAFEnergy"), nbt.getFloat("CAFEnergyAmperage"));
    }
}
