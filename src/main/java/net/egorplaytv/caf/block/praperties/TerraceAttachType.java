package net.egorplaytv.caf.block.praperties;

import net.minecraft.util.StringRepresentable;

public enum TerraceAttachType implements StringRepresentable {
    SINGLE("single"),
    LOW("low"),
    MIDDLE("middle"),
    UP("up");

    private final String name;

    TerraceAttachType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
