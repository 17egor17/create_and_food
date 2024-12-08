package net.egorplaytv.create_and_food.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CreateAndFoodClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Create And Food");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
