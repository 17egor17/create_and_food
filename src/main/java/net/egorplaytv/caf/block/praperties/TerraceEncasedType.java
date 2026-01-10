package net.egorplaytv.caf.block.praperties;

import net.minecraft.util.StringRepresentable;

public enum TerraceEncasedType implements StringRepresentable {
    YES("true"),
    NO("false");

    private final String name;

    TerraceEncasedType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
