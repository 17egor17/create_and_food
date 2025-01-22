package net.egorplaytv.create_and_food.block.praperties;

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
