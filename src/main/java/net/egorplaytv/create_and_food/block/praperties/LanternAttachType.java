package net.egorplaytv.create_and_food.block.praperties;

import net.minecraft.util.StringRepresentable;

public enum LanternAttachType implements StringRepresentable {
    FLOOR("floor"),
    HANGING("hanging"),
    WEST("west"),
    EAST("east"),
    SOUTH("south"),
    NORTH("north");

    private final String name;

    LanternAttachType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
