package net.egorplaytv.caf.block.custom.shingles;

import net.minecraft.util.StringRepresentable;

public enum RidgeShingleShape implements StringRepresentable {
    SINGLE("single"),
    STRAIGHT_Z("straight_z"),
    STRAIGHT_X("straight_x"),
    NORTH_END("north_end"),
    SOUTH_END("south_end"),
    WEST_END("west_end"),
    EAST_END("east_end"),
    NORTH_THREE("north_three"),
    SOUTH_THREE("south_three"),
    WEST_THREE("west_three"),
    EAST_THREE("east_three"),
    NORTH_FOUR("north_four"),
    SOUTH_FOUR("south_four"),
    WEST_FOUR("west_four"),
    EAST_FOUR("east_four"),
    NORTH_ANGLE("north_angle"),
    SOUTH_ANGLE("south_angle"),
    WEST_ANGLE("west_angle"),
    EAST_ANGLE("east_angle");

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
