package net.egorplaytv.caf.block.praperties;

import net.minecraft.util.StringRepresentable;

public enum CombiSteamerBaseBlockType implements StringRepresentable {
    DEFAULT("default"),
    CS_B_L("cs_b_l"),
    CS_B_U("cs_b_u"),
    CS_U("cs_u"),
    CS_R_L("cs_r_l"),
    CS_R_U("cs_r_u");

    private final String name;

    CombiSteamerBaseBlockType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}