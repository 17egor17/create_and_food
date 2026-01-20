package net.egorplaytv.caf.util;

import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collections;

public class CAFTags {

    public static void init(){
        Blocks.init();
        Items.init();
        Fluids.init();
    }

    public static class Blocks {
        private static void init(){}

        public static final TagKey<Block> ALMOND = forgeTag("almond");
        public static final TagKey<Block> ALMOND_LOGS = modTag("almond_logs");
        public static final TagKey<Block> FARMLAND_INTO_BARREL = modTag("farmland_into_barrel");
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

        public static final TagKey<Block> MINEABLE_WITH_HAMMER = modTag("mineable/hammer");

        public static final TagKey<Block> NEEDS_COPPER_TOOL = forgeTag("needs_copper_tool");
        public static final TagKey<Block> NEEDS_TANTALUM_TOOL = forgeTag("needs_tantalum_tool");
        public static final TagKey<Block> NEEDS_TUNGSTEN_TOOL = forgeTag("needs_tungsten_tool");

        public static final TagKey<Block> FAN_PROCESSING_CATALYSTS_FREEZING = modTag("fan_processing_catalysts/freezing");

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

        private static void init(){}

        public static final TagKey<Item> STEEL = forgeTag("ingots/steel");
        public static final TagKey<Item> BRASS = forgeTag("ingots/brass");
        public static final TagKey<Item> ZINC = forgeTag("ingots/zinc");
        public static final TagKey<Item> ALUMINUM = forgeTag("ingots/aluminum");
        public static final TagKey<Item> CONSTANTAN = forgeTag("ingots/constantan");
        public static final TagKey<Item> ELECTRUM = forgeTag("ingots/electrum");
        public static final TagKey<Item> ENDERIUM = forgeTag("ingots/enderium");
        public static final TagKey<Item> LEAD = forgeTag("ingots/lead");
        public static final TagKey<Item> NICKEL = forgeTag("ingots/nickel");
        public static final TagKey<Item> SILVER = forgeTag("ingots/silver");
        public static final TagKey<Item> URANIUM = forgeTag("ingots/uranium");
        public static final TagKey<Item> BRONZE = forgeTag("ingots/bronze");
        public static final TagKey<Item> OSMIUM = forgeTag("ingots/osmium");
        public static final TagKey<Item> TIN = forgeTag("ingots/tin");
        public static final TagKey<Item> TANTALUM = forgeTag("ingots/tantalum");
        public static final TagKey<Item> TUNGSTEN = forgeTag("ingots/tungsten");
        public static final TagKey<Item> INVAR = forgeTag("ingots/invar");
        public static final TagKey<Item> LUMIUM = forgeTag("ingots/lumium");
        public static final TagKey<Item> SIGNALUM = forgeTag("ingots/signalum");
        public static final TagKey<Item> INGOTS = forgeTag("ingots");

        public static final TagKey<Item> TORCHES = forgeTag("torches");
        public static final TagKey<Item> CHAINS = forgeTag("chains");

        public static final TagKey<Item> IRON_SHEET = forgeTag("plates/iron");
        public static final TagKey<Item> MEAT = forgeTag("meat");
        public static final TagKey<Item> EGGS = modTag("eggs");
        public static final TagKey<Item> BANNER_PATTERNS = forgeTag("banner_patterns");
        public static final TagKey<Item> MACARONI = forgeTag("macaroni");
        public static final TagKey<Item> SMALL_BASE = forgeTag("small_base");
        public static final TagKey<Item> ALMOND_LOGS = modTag("almond_logs");
        public static final TagKey<Item> FENCE_GATES = tag("fence_gates");
        public static final TagKey<Item> COAL_DUST = forgeTag("dusts/coal");
        public static final TagKey<Item> MOD_BERRIES = modTag("berries");
        public static final TagKey<Item> BERRIES = forgeTag("berries");
        public static final TagKey<Item> FUEL = modTag("fuel");
        public static final TagKey<Item> CREATIVE_FUEL = modTag("creative_fuel");
        public static final TagKey<Item> CUT_TOOLS = forgeTag("cut_tools");
        public static final TagKey<Item> TERRACOTTA = tag("terracotta");
        public static final TagKey<Item> DEAD_CORALS = tag("dead_corals");
        public static final TagKey<Item> LANTERNS = forgeTag("lanterns");

        public static final TagKey<Item> BOTTLES = forgeTag("bottles");

        public static final TagKey<Item> MARBLE = modTag("stone_types/marble");
        public static final TagKey<Item> MARBLE_BLACK_GALAXY = modTag("stone_types/marble_black_galaxy");
        public static final TagKey<Item> MARBLE_PERLIN_PINK = modTag("stone_types/marble_perlin_pink");

        public static final TagKey<Item> BARRELS = modTag("barrels");
        public static final TagKey<Item> CUTTING_BOARDS = modTag("cutting_boards");
        public static final TagKey<Item> TERRACES = modTag("terraces");


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
        private static void init(){}

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

    public static <T extends IForgeRegistryEntry<T>> TagKey<T> optionalTag(IForgeRegistry<T> registry,
                                                                           ResourceLocation id) {
        return registry.tags()
                .createOptionalTagKey(id, Collections.emptySet());
    }

    public static <T extends IForgeRegistryEntry<T>> TagKey<T> forgeTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation("forge", path));
    }

    public static <T extends IForgeRegistryEntry<T>> TagKey<T> CAFTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation(CreateAndFood.MOD_ID, path));
    }

    public static TagKey<Block> forgeBlockTag(String path) {
        return forgeTag(ForgeRegistries.BLOCKS, path);
    }

    public static TagKey<Item> forgeItemTag(String path) {
        return forgeTag(ForgeRegistries.ITEMS, path);
    }

    public static TagKey<Fluid> forgeFluidTag(String path) {
        return forgeTag(ForgeRegistries.FLUIDS, path);
    }

    public static TagKey<Block> CAFBlockTag(String path) {
        return CAFTag(ForgeRegistries.BLOCKS, path);
    }

    public static TagKey<Item> CAFItemTag(String path) {
        return CAFTag(ForgeRegistries.ITEMS, path);
    }

    public static TagKey<Fluid> CAFFluidTag(String path) {
        return CAFTag(ForgeRegistries.FLUIDS, path);
    }
}

