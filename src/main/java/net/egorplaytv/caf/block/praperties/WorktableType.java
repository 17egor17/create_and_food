package net.egorplaytv.caf.block.praperties;

import net.minecraft.util.StringRepresentable;

public enum WorktableType implements StringRepresentable {
    SINGLE("single", 0),
    LEFT("left", 2),
    RIGHT("right", 1);

    public static final WorktableType[] BY_ID = values();
    private final String name;
    private final int opposite;

    WorktableType(String pName, int pOpposite) {
        this.name = pName;
        this.opposite = pOpposite;
    }

    public String getSerializedName() {
        return this.name;
    }

    public WorktableType getOpposite() {
        return BY_ID[this.opposite];
    }
}
