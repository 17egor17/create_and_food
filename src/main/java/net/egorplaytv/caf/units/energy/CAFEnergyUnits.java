package net.egorplaytv.caf.units.energy;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public class CAFEnergyUnits {
    private float energy;
    private float dimperage;

    public static CAFEnergyUnits EMPTY = new CAFEnergyUnits(0F, 0F);

    public CAFEnergyUnits(float energy) {
        this(energy, 0F);
    }

    public CAFEnergyUnits(float energy, float dimperage) {
        this.energy = energy;
        this.dimperage = dimperage;
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

    public float getRawDimperage() {
        return this.dimperage;
    }

    public float getDimperage() {
        return ((Math.round(this.dimperage * 100)) / 100F);
    }

    public void setDimperage(float dimperage) {
        this.dimperage = dimperage;
    }

    public float getValueDamage() {
        return ((dimperage * getRawEnergy()) / ((getRawEnergy() / 2) * 1000));
    }

    public boolean isEmpty() {
        return energy <= 0;
    }

    public CAFEnergyUnits copy() {
        return new CAFEnergyUnits(getRawEnergy(), getRawDimperage());
    }

    public boolean is(CAFEnergyUnits energy) {
        return this.energy == energy.energy && this.dimperage == energy.dimperage;
    }



    public static CAFEnergyUnits fromJson(JsonObject json) {
        return new CAFEnergyUnits(GsonHelper.getAsFloat(json, "CAFEnergy", 100),
                GsonHelper.getAsFloat(json, "CAFEnergyDimperage", 100));
    }

    public static CAFEnergyUnits fromNetwork(FriendlyByteBuf buf) {
        return new CAFEnergyUnits(buf.readFloat(), buf.readFloat());
    }

    public static void toNetwork(FriendlyByteBuf buf, CAFEnergyUnits value) {
        buf.writeFloat(value.getRawEnergy());
        buf.writeFloat(value.getRawDimperage());
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putFloat("CAFEnergy", getRawEnergy());
        nbt.putFloat("CAFEnergyDimperage", getRawDimperage());
        return nbt;
    }

    public static CAFEnergyUnits loadFromNBT(CompoundTag nbt) {
        if (nbt == null)
            return CAFEnergyUnits.EMPTY;

        return new CAFEnergyUnits(nbt.getFloat("CAFEnergy"), nbt.getFloat("CAFEnergyDimperage"));
    }
}
