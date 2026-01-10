package net.egorplaytv.caf.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CreateAndFoodCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEINS_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_BLUEBERRY_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_CRANBERRY_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_RASPBERRY_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_BLUE_GRAPE_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_GREEN_GRAPE_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_PURPLE_GRAPE_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_RED_GRAPE_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_PUMPKIN_BUSH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHANCE_WILD_MELON_BUSH;

    public static final ForgeConfigSpec.ConfigValue<Integer> TANTALUM_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TANTALUM_ORE_VEINS_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MARBLE_PER_CHUNK_UPPER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MARBLE_PER_CHUNK_LOWER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MARBLE_VEINS_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> SPEED_ATTENUATION_FURNACE;
    public static final ForgeConfigSpec.EnumValue<DegreeUnits> UNITS;

    public static final ForgeConfigSpec.BooleanValue GENERATE_CAF_CHEST_LOOT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_KILOGRAMS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_GRAMS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TONES;

    static {
        BUILDER.comment("World generation").push("world");
        BUILDER.comment("Wild blueberry bush generation").push("wild_blueberry_bush");
        CHANCE_WILD_BLUEBERRY_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild cranberry bush generation").push("wild_cranberry_bush");
        CHANCE_WILD_CRANBERRY_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild raspberry bush generation").push("wild_raspberry_bush");
        CHANCE_WILD_RASPBERRY_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild blue grape bush generation").push("wild_blue_grape_bush");
        CHANCE_WILD_BLUE_GRAPE_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild green grape bush generation").push("wild_green_grape_bush");
        CHANCE_WILD_GREEN_GRAPE_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild purple grape bush generation").push("wild_purple_grape_bush");
        CHANCE_WILD_PURPLE_GRAPE_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild red grape bush generation").push("wild_red_grape_bush");
        CHANCE_WILD_RED_GRAPE_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 16, 0, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Ore generation").push("ore");
        RUBY_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Ruby Ore Veins spawn per chunk!")
                .translation("config.caf.ruby_ore_veins_per_chunk")
                .define("Ruby Ore Veins Per Chunk", 7);
        RUBY_ORE_VEINS_SIZE = BUILDER.comment("How many Ruby Ore Blocks spawn in one Vein!")
                .translation("config.caf.ruby_ore_veins_size")
                .defineInRange("Ruby Ore Vein Size", 9, 4, 20);
        TANTALUM_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Tantalum Ore Veins spawn per chunk!")
                .translation("config.caf.tantalum_ore_veins_per_chunk")
                .define("Tantalum Veins Per Chunk", 7);
        TANTALUM_ORE_VEINS_SIZE = BUILDER.comment("How many Tantalum Ore Blocks spawn in one Vein!")
                .translation("config.caf.tantalum_ore_veins_size")
                .defineInRange("Tantalum Vein Size", 9, 4, 20);
        BUILDER.pop();
        BUILDER.comment("Wild pumpkin bush generation").push("wild_pumpkin_bush");
        CHANCE_WILD_PUMPKIN_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 300, 200, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Wild melon bush generation").push("wild_melon_bush");
        CHANCE_WILD_MELON_BUSH = BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .translation("config.caf.chance_wild_berry_bush")
                .defineInRange("Chance", 6, 6, Integer.MAX_VALUE);
        BUILDER.pop();
        BUILDER.comment("Marble generation").push("marble");
        MARBLE_PER_CHUNK_UPPER = BUILDER.comment("How many Marble Veins spawn per chunk!")
                .translation("config.caf.marble_per_chunk")
                .define("Marble Veins Per Chunk Upper", 6);
        MARBLE_PER_CHUNK_LOWER = BUILDER.comment("How many Marble Veins spawn per chunk!")
                .translation("config.caf.marble_per_chunk")
                .define("Marble Veins Per Chunk Lower", 2);

        MARBLE_VEINS_SIZE = BUILDER.comment("How many Marble Blocks spawn in one Vein!")
                .translation("config.caf.marble_veins_size")
                .defineInRange("Marble Vein Size", 64, 20, 64);
        BUILDER.pop();
        BUILDER.comment("Generate CAF LootTables in minecraft chests").push("lootTableGeneration");
        GENERATE_CAF_CHEST_LOOT = BUILDER.comment("Should this mod add some of its items as extra chest loot across Minecraft?")
                .translation("config.caf.loot_table_generation")
                .define("generateCAFChestLoot", true);
        BUILDER.pop();
        BUILDER.pop();
        BUILDER.comment("Game settings").push("configs");
        SPEED_ATTENUATION_FURNACE = BUILDER.comment("Time in seconds")
                .translation("config.caf.furnace_speed")
                .defineInRange("Speed Attenuation Blast Furnace", 50, 10, 100);
        UNITS = BUILDER.comment("Units of measurement")
                .defineEnum("unitsOfMeasurement", DegreeUnits.DEGREES_CELSIUS);

        ENABLE_KILOGRAMS = BUILDER.comment("Includes kilograms")
                .translation("config.caf.enable_kilograms")
                .define("Enable kilograms", true);

        ENABLE_GRAMS = BUILDER.comment("Includes grams")
                .translation("config.caf.enable_grams")
                .define("Enable grams", true);

        ENABLE_TONES = BUILDER.comment("Includes tones")
                .translation("config.caf.enable_tones")
                .define("Enable tones", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static DegreeUnits getUnits(){
        return UNITS.get();
    }
}
