package net.egorplaytv.caf.block.custom.shingles;

import net.minecraft.util.StringRepresentable;

public enum RidgeShingleShape implements StringRepresentable {
    SINGLE("single"),
    STRAIGHT("straight"),
    INNER_LEFT("inner_left"),
    INNER_RIGHT("inner_right"),
    OUTER_LEFT("outer_left"),
    OUTER_RIGHT("outer_right");

    private final String name;

    RidgeShingleShape(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
