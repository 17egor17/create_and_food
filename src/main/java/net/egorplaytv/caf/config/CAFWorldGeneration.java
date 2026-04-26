package net.egorplaytv.caf.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CAFWorldGeneration extends ConfigBase {

    public final ConfigGroup worldGen = group(0, "worldGen");

    public final ConfigGroup wildBlueberryBushGeneration = group(1, "wildBlueberryBush", Comments.wildBlueberryBush);
    public final ConfigInt wildBlueberryChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildCranberryBushGeneration = group(1, "wildCranberryBush", Comments.wildCranberryBush);
    public final ConfigInt wildCranberryChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildRaspberryBushGeneration = group(1, "wildRaspberryBush", Comments.wildRaspberryBush);
    public final ConfigInt wildRaspberryChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildBlueGrapeBushGeneration = group(1, "wildBlueGrapeBush", Comments.wildBlueGrapeBush);
    public final ConfigInt wildBlueGrapeChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildGreenGrapeBushGeneration = group(1, "wildGreenGrapeBush", Comments.wildGreenGrapeBush);
    public final ConfigInt wildGreenGrapeChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildPurpleGrapeBushGeneration = group(1, "wildPurpleGrapeBush", Comments.wildPurpleGrapeBush);
    public final ConfigInt wildPurpleGrapeChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildRedGrapeBushGeneration = group(1, "wildRedGrapeBush", Comments.wildRedGrapeBush);
    public final ConfigInt wildRedGrapeChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup oreGen = group(1, "oreGen", Comments.oreGen);
    public final ConfigInt rubyOreVeinsPerChunk = i(7, "rubyOreVeinsPerChunk", Comments.rubyOreVeinsPerChunk);
    public final ConfigInt rubyOreVeinsSize = i(9, 4, 20, "rubyOreVeinsSize", Comments.rubyOreVeinsSize);
    public final ConfigInt tantalumOreVeinsPerChunk = i(7, "tantalumOreVeinsPerChunk", Comments.tantalumOreVeinsPerChunk);
    public final ConfigInt tantalumOreVeinsSize = i(9, 4, 20, "tantalumOreVeinsSize", Comments.tantalumOreVeinsSize);
    public final ConfigInt tungstenOreVeinsPerChunk = i(7, "tungstenOreVeinsPerChunk", Comments.tungstenOreVeinsPerChunk);
    public final ConfigInt tungstenOreVeinsSize = i(9, 4, 20, "tungstenOreVeinsSize", Comments.tungstenOreVeinsSize);

    public final ConfigGroup wildPumpkinBush = group(1, "wildPumpkinBush", Comments.wildPumpkinBush);
    public final ConfigInt wildPumpkinBushChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup wildMelonBush = group(1, "wildMelonBush", Comments.wildMelonBush);
    public final ConfigInt wildMelonBushChance = i(16, 0, Integer.MAX_VALUE, "chance", Comments.chance);

    public final ConfigGroup marble = group(1, "marble", Comments.marble);
    public final ConfigInt marbleVeinsPerChunkUpper = i(6, "marbleVeinsPerChunkUpper", Comments.marbleVeinsPerChunk);
    public final ConfigInt marbleVeinsPerChunkLower = i(2, "marbleVeinsPerChunkLower", Comments.marbleVeinsPerChunk);
    public final ConfigInt marbleVeinSize = i(64, 20, 64, "marbleVeinSize", Comments.marbleVeinSize);

    public final ConfigGroup lootTableGeneration = group(1, "lootTableGeneration", Comments.lootTableGeneration);
    public final ConfigBool generateCAFChestLoot = b(true, "generateCAFChestLoot", Comments.generateCAFChestLoot);


    @Override
    public String getName() {
        return "worldgen";
    }

    private static class Comments {
        //Groups
        static String wildBlueberryBush = "Wild Blueberry Bush Generation";
        static String wildCranberryBush = "Wild Cranberry Bush Generation";
        static String wildRaspberryBush = "Wild Raspberry Bush Generation";
        static String wildBlueGrapeBush = "Wild Blue Grape Bush Generation";
        static String wildGreenGrapeBush = "Wild Green Grape Bush Generation";
        static String wildPurpleGrapeBush = "Wild Purple Grape Bush Generation";
        static String wildRedGrapeBush = "Wild Red Grape Bush Generation";
        static String oreGen = "Ore Generation Configs";
        static String wildPumpkinBush = "Wild Pumpkin Bush Generation Configs";
        static String wildMelonBush = "Wild Melon Bush Generation Configs";
        static String marble = "Marble Generation Configs";
        static String lootTableGeneration = "Generate CAF LootTables in minecraft chests";

        //Values
        static String rubyOreVeinsPerChunk = "How many Ruby Ore Veins spawn per chunk!";
        static String rubyOreVeinsSize = "How many Ruby Ore Blocks spawn in one Vein!";
        static String tantalumOreVeinsPerChunk = "How many Tantalum Ore Veins spawn per chunk!";
        static String tantalumOreVeinsSize = "How many Tantalum Ore Blocks spawn in one Vein!";
        static String tungstenOreVeinsPerChunk = "How many Tungsten Ore Veins spawn per chunk!";
        static String tungstenOreVeinsSize = "How many Tungsten Ore Blocks spawn in one Vein!";
        static String chance = "Chance of generating clusters. Smaller value = more frequent.";
        static String marbleVeinsPerChunk = "How many Marble Veins spawn per chunk!";
        static String marbleVeinSize = "How many Marble Blocks spawn in one Vein!";
        static String generateCAFChestLoot = "Should this mod add some of its items as extra chest loot across Minecraft?";
    }
}
