package net.egorplaytv.caf.energy;

import net.minecraft.util.StringRepresentable;

public enum ConnectionType implements StringRepresentable {
    CABLE("cable"),
    BLOCK("block"),
    NONE("none");

    private final String name;

    ConnectionType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
