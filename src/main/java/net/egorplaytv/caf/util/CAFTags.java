package net.egorplaytv.caf.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CAFTags {

    public static void init(){
        Blocks.init();
        Items.init();
        Fluids.init();
    }

    public static class Blocks {
        private static void init(){ }

        // Forge Tags
        public static final TagKey<Block> ALMOND = forgeTag("almond");
        public static final TagKey<Block> NEEDS_COPPER_TOOL = forgeTag("needs_copper_tool");
        public static final TagKey<Block> NEEDS_TANTALUM_TOOL = forgeTag("needs_tantalum_tool");
        public static final TagKey<Block> NEEDS_TUNGSTEN_TOOL = forgeTag("needs_tungsten_tool");
        public static final TagKey<Block> MINEABLE_WITH_HAMMER = forgeTag("mineable/hammer");
        public static final TagKey<Block> ORES_RUBY = forgeTag("ores/ruby");
        public static final TagKey<Block> ORES_TANTALUM = forgeTag("ores/tantalum");
        public static final TagKey<Block> ORES_TUNGSTEN = forgeTag("ores/tungsten");


        // Create And Food Tags
        public static final TagKey<Block> ALMOND_LOGS = modTag("almond_logs");
        public static final TagKey<Block> FRAMED_WALLS = modTag("framed_walls");
        public static final TagKey<Block> BASALT_ORE_REPLACEABLE = modTag("basalt_ore_replaceable");
        public static final TagKey<Block> BLACKSTONE_ORE_REPLACEABLE = modTag("blackstone_ore_replaceable");
        public static final TagKey<Block> CRIMSON_ORE_REPLACEABLE = modTag("crimson_ore_replaceable");
        public static final TagKey<Block> END_ORE_REPLACEABLE = modTag("end_ore_replaceable");
        public static final TagKey<Block> WARPED_NYLIUM_ORE_REPLACEABLE = modTag("warped_nylium_ore_replaceable");
        public static final TagKey<Block> NETHERRACK_ORE_REPLACEABLE = modTag("netherrack_ore_replaceable");
        public static final TagKey<Block> BERRY_BUSHES = modTag("berry_bushes");
        public static final TagKey<Block> BARRELS = modTag("barrels");
        public static final TagKey<Block> CUTTING_BOARDS = modTag("cutting_boards");
        public static final TagKey<Block> FAN_PROCESSING_CATALYSTS_FREEZING = modTag("fan_processing_catalysts/freezing");

        // Minecraft Tags


        // Utility
        public static TagKey<Block> modTag(String name) {
            return BlockTags.create(new ResourceLocation("caf", name));
        }
        public static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
        public static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation("minecraft", name));
        }
    }

    public static class Items {
        private static void init(){ }

        // Forge Tags
        public static final TagKey<Item> BANNER_PATTERNS = forgeTag("banner_patterns");
        public static final TagKey<Item> BERRIES = forgeTag("berries");
        public static final TagKey<Item> BOTTLES = forgeTag("bottles");
        public static final TagKey<Item> CROPS_RICE = forgeTag("crops/rice");
        public static final TagKey<Item> CROPS_RYE = forgeTag("crops/rye");
        public static final TagKey<Item> CHAINS = forgeTag("chains");
        public static final TagKey<Item> CHAINS_IRON = forgeTag("chains/iron");
        public static final TagKey<Item> CHAINS_SOUL = forgeTag("chains/soul");
        public static final TagKey<Item> CHAINS_STEEL = forgeTag("chains/steel");
        public static final TagKey<Item> CUT_TOOLS = forgeTag("cut_tools");
        public static final TagKey<Item> DUSTS = forgeTag("dusts");
        public static final TagKey<Item> DUSTS_COAL = forgeTag("dusts/coal");
        public static final TagKey<Item> DUSTS_IRON = forgeTag("dusts/iron");
        public static final TagKey<Item> DUSTS_STEEL = forgeTag("dusts/steel");
        public static final TagKey<Item> INGOTS = forgeTag("ingots");
        public static final TagKey<Item> INGOTS_COPPER = forgeTag("ingots/copper");
        public static final TagKey<Item> INGOTS_GOLD = forgeTag("ingots/gold");
        public static final TagKey<Item> INGOTS_IRON = forgeTag("ingots/iron");
        public static final TagKey<Item> INGOTS_NETHERITE = forgeTag("ingots/netherite");
        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final TagKey<Item> INGOTS_BRASS = forgeTag("ingots/brass");
        public static final TagKey<Item> INGOTS_ZINC = forgeTag("ingots/zinc");
        public static final TagKey<Item> INGOTS_ALUMINUM = forgeTag("ingots/aluminum");
        public static final TagKey<Item> INGOTS_CONSTANTAN = forgeTag("ingots/constantan");
        public static final TagKey<Item> INGOTS_ELECTRUM = forgeTag("ingots/electrum");
        public static final TagKey<Item> INGOTS_ENDERIUM = forgeTag("ingots/enderium");
        public static final TagKey<Item> INGOTS_LEAD = forgeTag("ingots/lead");
        public static final TagKey<Item> INGOTS_NICKEL = forgeTag("ingots/nickel");
        public static final TagKey<Item> INGOTS_SILVER = forgeTag("ingots/silver");
        public static final TagKey<Item> INGOTS_URANIUM = forgeTag("ingots/uranium");
        public static final TagKey<Item> INGOTS_BRONZE = forgeTag("ingots/bronze");
        public static final TagKey<Item> INGOTS_OSMIUM = forgeTag("ingots/osmium");
        public static final TagKey<Item> INGOTS_TIN = forgeTag("ingots/tin");
        public static final TagKey<Item> INGOTS_TANTALUM = forgeTag("ingots/tantalum");
        public static final TagKey<Item> INGOTS_TUNGSTEN = forgeTag("ingots/tungsten");
        public static final TagKey<Item> INGOTS_INVAR = forgeTag("ingots/invar");
        public static final TagKey<Item> INGOTS_LUMIUM = forgeTag("ingots/lumium");
        public static final TagKey<Item> INGOTS_SIGNALUM = forgeTag("ingots/signalum");
        public static final TagKey<Item> LANTERNS = forgeTag("lanterns");
        public static final TagKey<Item> MACARONI = forgeTag("macaroni");
        public static final TagKey<Item> MEAT = forgeTag("meat");
        public static final TagKey<Item> NUGGETS = forgeTag("nuggets");
        public static final TagKey<Item> NUGGETS_COPPER = forgeTag("nuggets/copper");
        public static final TagKey<Item> NUGGETS_GOLD = forgeTag("nuggets/gold");
        public static final TagKey<Item> NUGGETS_IRON = forgeTag("nuggets/iron");
        public static final TagKey<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final TagKey<Item> NUGGETS_TANTALUM = forgeTag("nuggets/tantalum");
        public static final TagKey<Item> NUGGETS_TUNGSTEN = forgeTag("nuggets/tungsten");
        public static final TagKey<Item> PLATES = forgeTag("plates");
        public static final TagKey<Item> PLATES_BRASS = forgeTag("plates/brass");
        public static final TagKey<Item> PLATES_STEEL = forgeTag("plates/steel");
        public static final TagKey<Item> PLATES_IRON = forgeTag("plates/iron");
        public static final TagKey<Item> SMALL_BASE = forgeTag("small_base");
        public static final TagKey<Item> SMALL_BASE_CHOCOLATE = forgeTag("small_base/chocolate");
        public static final TagKey<Item> SMALL_BASE_HONEY = forgeTag("small_base/honey");
        public static final TagKey<Item> STRIPPED_LOGS = forgeTag("stripped_logs");
        public static final TagKey<Item> STRIPPED_WOOD = forgeTag("stripped_wood");
        public static final TagKey<Item> TONGS = forgeTag("tongs");
        public static final TagKey<Item> TONGS_WOOD = forgeTag("tongs/wood");
        public static final TagKey<Item> TOOLS = forgeTag("tools");
        public static final TagKey<Item> TOOLS_KNIVES = forgeTag("tools/knives");
        public static final TagKey<Item> TORCHES = forgeTag("torches");
        public static final TagKey<Item> TORCHES_SOUL = forgeTag("torches");
        public static final TagKey<Item> TORCHES_TORCH = forgeTag("torches");

        // Create And Food Tags
        public static final TagKey<Item> RED_JUICE = modTag("grapes/red_juice");
        public static final TagKey<Item> WHITE_JUICE = modTag("grapes/white_juice");
        public static final TagKey<Item> GRAPES = modTag("grapes");
        public static final TagKey<Item> EGG = modTag("eggs/egg");
        public static final TagKey<Item> SPAWN_EGGS = modTag("eggs/spawn_eggs");
        public static final TagKey<Item> TURTLE_EGG = modTag("eggs/turtle_egg");
        public static final TagKey<Item> EGGS = modTag("eggs");
        public static final TagKey<Item> ALMOND_LOGS = modTag("almond_logs");
        public static final TagKey<Item> MOD_BERRIES = modTag("berries");
        public static final TagKey<Item> MARBLE = modTag("stone_types/marble");
        public static final TagKey<Item> MARBLE_BLACK_GALAXY = modTag("stone_types/marble_black_galaxy");
        public static final TagKey<Item> MARBLE_PERLIN_PINK = modTag("stone_types/marble_perlin_pink");
        public static final TagKey<Item> BARRELS = modTag("barrels");
        public static final TagKey<Item> CUTTING_BOARDS = modTag("cutting_boards");
        public static final TagKey<Item> TERRACES = modTag("terraces");
        public static final TagKey<Item> FUEL = modTag("fuel");
        public static final TagKey<Item> CREATIVE_FUEL = modTag("creative_fuel");

        // Minecraft Tags
        public static final TagKey<Item> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Item> TERRACOTTA = tag("terracotta");
        public static final TagKey<Item> DEAD_CORALS = tag("dead_corals");


        // Utility
        public static TagKey<Item> modTag(String name) {
            return ItemTags.create(new ResourceLocation("caf", name));
        }
        public static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
        public static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation("minecraft", name));
        }
    }

    public static class Fluids {
        private static void init(){ }

        // Forge Tags


        // Create And Food Tags


        // Minecraft Tags


        // Utility
        public static TagKey<Fluid> modTag(String name) {
            return FluidTags.create(new ResourceLocation("caf", name));
        }
        public static TagKey<Fluid> forgeTag(String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
        public static TagKey<Fluid> tag(String name){
            return FluidTags.create(new ResourceLocation("minecraft", name));
        }
    }
}

