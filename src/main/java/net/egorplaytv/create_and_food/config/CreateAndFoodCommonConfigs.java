package net.egorplaytv.create_and_food.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CreateAndFoodCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEINS_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> TUNTAL_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TUNTAL_ORE_VEINS_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MARBLE_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> MARBLE_VEINS_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> SPEED_ATTENUATION_FURNACE;

    static {
        BUILDER.push("Configs for Create And Food");

        RUBY_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Ruby Ore Veins spawn per chunk!")
                .translation("config.create_and_food.ruby_ore_veins_per_chunk")
                .define("Veins Per Chunk", 7);

        RUBY_ORE_VEINS_SIZE = BUILDER.comment("How many Ruby Ore Blocks spawn in one Vein!")
                .translation("config.create_and_food.ruby_ore_veins_size")
                .defineInRange("Vein Size", 9, 4, 20);

        TUNTAL_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Tantalum Ore Veins spawn per chunk!")
                .translation("config.create_and_food.tantalum_ore_veins_per_chunk")
                .define("Veins Per Chunk", 7);

        TUNTAL_ORE_VEINS_SIZE = BUILDER.comment("How many Tantalum Ore Blocks spawn in one Vein!")
                .translation("config.create_and_food.tantalum_ore_veins_size")
                .defineInRange("Vein Size", 9, 4, 20);

        MARBLE_PER_CHUNK = BUILDER.comment("How many Marble Veins spawn per chunk!")
                .translation("config.create_and_food.marble_per_chunk")
                .define("Veins Per Chunk", 7);

        MARBLE_VEINS_SIZE = BUILDER.comment("How many Marble Blocks spawn in one Vein!")
                .translation("config.create_and_food.marble_veins_size")
                .defineInRange("Vein Size", 10, 4, 20);

        SPEED_ATTENUATION_FURNACE = BUILDER.comment("Speed Attenuation Furnace")
                .translation("config.create_and_food.furnace_speed")
                .defineInRange("Time in seconds", 50, 10, 100);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
