package net.egorplaytv.caf.datagen;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class CAFBlockTagsProvider extends TagsProvider<Block> {

    public CAFBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.BLOCK, CreateAndFood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        //Mineable tags
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(CAFTags.Blocks.ALMOND)
                .addTag(CAFTags.Blocks.CUTTING_BOARDS).add(CAFBlocks.PASTRY_TABLE.get())
                .add(CAFBlocks.KITCHEN_TABLE.get()).add(CAFBlocks.KITCHEN_TABLE_INNER.get())
                .add(CAFBlocks.KITCHEN_TABLE_OUTER.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(CAFBlocks.ALMOND_LEAVES.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(CAFTags.Blocks.modTag("stone_types/baked_clay")).addTag(CAFTags.Blocks.modTag("stone_types/black_baked_clay"))
                .addTag(CAFTags.Blocks.modTag("stone_types/blue_baked_clay")).addTag(CAFTags.Blocks.modTag("stone_types/cyan_baked_clay"))
                .addTag(CAFTags.Blocks.modTag("stone_types/gray_baked_clay")).addTag(CAFTags.Blocks.modTag("stone_types/green_baked_clay"))
                .addTag(CAFTags.Blocks.modTag("stone_types/marble")).addTag(CAFTags.Blocks.modTag("stone_types/marble_black_galaxy"))
                .addTag(CAFTags.Blocks.modTag("stone_types/marble_perlin_pink")).addTag(CAFTags.Blocks.modTag("stone_types/orange_baked_clay"))
                .addTag(CAFTags.Blocks.modTag("stone_types/red_baked_clay")).addTag(CAFTags.Blocks.modTag("stone_types/white_baked_clay"))
                .addTag(CAFTags.Blocks.modTag("stone_types/yellow_baked_clay")).addTag(CAFTags.Blocks.FRAMED_WALLS)
                .add(CAFBlocks.TORN_SOUL_CHAIN.get()).add(CAFBlocks.STEEL_CHAIN.get())
                .add(CAFBlocks.TORN_SOUL_LANTERN.get()).add(CAFBlocks.GLOWING_BRASS_COPPER_LANTERN.get())
                .add(CAFBlocks.GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get()).add(CAFBlocks.GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get())
                .add(CAFBlocks.GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get()).add(CAFBlocks.GLOWING_BRASS_WAXED_COPPER_LANTERN.get())
                .add(CAFBlocks.GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get()).add(CAFBlocks.GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get())
                .add(CAFBlocks.GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get()).add(CAFBlocks.GLOWING_BRASS_STEEL_LANTERN.get())
                .add(CAFBlocks.LANTERN.get()).add(CAFBlocks.SOUL_LANTERN.get())
                .add(CAFBlocks.FURNITURE_CUTTER.get()).add(CAFBlocks.MARBLE_BLAST_FURNACE.get())
                .add(CAFBlocks.TERMINAL.get()).add(CAFBlocks.MECHANICAL_GRINDER.get())
                .add(CAFBlocks.ALLOY_SOULS_CASING.get()).add(CAFBlocks.GOLDEN_WALL.get())
                .add(CAFBlocks.STEEL_SCAFFOLD.get())
                .add(CAFBlocks.COBBLED_MARBLE.get()).add(CAFBlocks.COBBLED_MARBLE_BLACK_GALAXY.get())
                .add(CAFBlocks.COBBLED_MARBLE_PERLIN_PINK.get()).add(CAFBlocks.FIRECLAY_BRICKS.get())
                .add(CAFBlocks.FRAMED_CALCITE.get()).add(CAFBlocks.SECURE_BLOCK.get())
                .add(CAFBlocks.STONE_WALKWAY.get()).add(CAFBlocks.DEEPSLATE_WALKWAY.get())
                .add(CAFBlocks.SANDSTONE_WALKWAY.get()).add(CAFBlocks.RED_SANDSTONE_WALKWAY.get())
                .add(CAFBlocks.STEEL_BLOCK.get()).add(CAFBlocks.STEEL_SHAFT.get())
                .add(CAFBlocks.STEEL_ENCASED_STEEL_SHAFT.get()).add(CAFBlocks.STEEL_COGWHEEL.get())
                .add(CAFBlocks.STEEL_ENCASED_STEEL_COGWHEEL.get()).add(CAFBlocks.LARGE_STEEL_COGWHEEL.get())
                .add(CAFBlocks.STEEL_ENCASED_LARGE_STEEL_COGWHEEL.get()).add(CAFBlocks.STEEL_DOOR.get())
                .add(CAFBlocks.STEEL_LAMP_BLOCK.get()).add(CAFBlocks.RUBY_ORE.get())
                .add(CAFBlocks.DEEPSLATE_RUBY_ORE.get()).add(CAFBlocks.RUBY_BLOCK.get())
                .add(CAFBlocks.RAW_RUBY_BLOCK.get()).add(CAFBlocks.TANTALUM_ORE.get())
                .add(CAFBlocks.BLACKSTONE_TANTALUM_ORE.get()).add(CAFBlocks.RAW_TANTALUM_BLOCK.get())
                .add(CAFBlocks.STONE_TANTALUM_ORE.get()).add(CAFBlocks.TUNGSTEN_ORE.get())
                .add(CAFBlocks.STONE_TUNGSTEN_ORE.get()).add(CAFBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .add(CAFBlocks.RAW_TUNGSTEN_BLOCK.get()).add(CAFBlocks.NIXIE_VASE.get())
                .add(CAFBlocks.NIXIE_VASE_PERLIN_PINK.get()).add(CAFBlocks.NIXIE_VASE_BLACK_GALAXY.get())
                .add(CAFBlocks.FERMENTATION_BARREL.get()).add(CAFBlocks.SCALES.get())
                .add(CAFBlocks.MECHANICAL_BLENDER.get()).addTag(CAFTags.Blocks.modTag("shingles/black_saman"))
                .addTag(CAFTags.Blocks.modTag("shingles/blue_saman")).addTag(CAFTags.Blocks.modTag("shingles/cyan_saman"))
                .addTag(CAFTags.Blocks.modTag("shingles/gray_saman")).addTag(CAFTags.Blocks.modTag("shingles/green_saman"))
                .addTag(CAFTags.Blocks.modTag("shingles/orange_saman")).addTag(CAFTags.Blocks.modTag("shingles/red_saman"))
                .addTag(CAFTags.Blocks.modTag("shingles/saman")).addTag(CAFTags.Blocks.modTag("shingles/white_saman"))
                .addTag(CAFTags.Blocks.modTag("shingles/yellow_saman"));
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(CAFBlocks.UNBAKED_CLAY.get()).add(CAFBlocks.FARMLAND_SUMP_SAND.get())
                .add(CAFBlocks.FARMLAND_SUMP_RED_SAND.get()).add(CAFBlocks.FARMLAND_SUMP_RICH_SOIL.get())
                .add(CAFBlocks.FARMLAND_SUMP_DIRT.get()).add(CAFBlocks.FERTILIZED_SAND.get())
                .add(CAFBlocks.FERTILIZED_RED_SAND.get()).add(CAFBlocks.SAND_FARMLAND.get())
                .add(CAFBlocks.RED_SAND_FARMLAND.get());

       //Minecraft tags
        this.tag(BlockTags.BEE_GROWABLES).addTag(CAFTags.Blocks.BERRY_BUSHES);
        this.tag(BlockTags.DOORS).add(CAFBlocks.ALMOND_DOOR.get()).add(CAFBlocks.STEEL_DOOR.get());
        this.tag(BlockTags.FALL_DAMAGE_RESETTING).addTag(CAFTags.Blocks.BERRY_BUSHES);
        this.tag(BlockTags.FENCE_GATES).add(CAFBlocks.ALMOND_FENCE_GATE.get());
        this.tag(BlockTags.FLOWERS).addTag(CAFTags.Blocks.BERRY_BUSHES);
        this.tag(BlockTags.GUARDED_BY_PIGLINS).addTag(CAFTags.Blocks.BARRELS);
        this.tag(BlockTags.LEAVES).add(CAFBlocks.ALMOND_LEAVES.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(CAFTags.Blocks.ALMOND_LOGS);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(CAFBlocks.RUBY_ORE.get()).add(CAFBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(CAFBlocks.STEEL_LAMP_BLOCK.get()).add(CAFBlocks.RUBY_BLOCK.get())
                .add(CAFBlocks.RAW_RUBY_BLOCK.get());
        this.tag(BlockTags.PLANKS).add(CAFBlocks.ALMOND_PLANKS.get());
        this.tag(BlockTags.SAPLINGS).add(CAFBlocks.ALMOND_SAPLING.get());
        this.tag(BlockTags.STANDING_SIGNS).add(CAFBlocks.ALMOND_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(CAFBlocks.ALMOND_WALL_SIGN.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(CAFBlocks.ALMOND_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(CAFBlocks.ALMOND_DOOR.get()).add(CAFBlocks.STEEL_DOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(CAFBlocks.ALMOND_FENCE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(CAFBlocks.ALMOND_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(CAFBlocks.ALMOND_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(CAFBlocks.ALMOND_STAIRS.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(CAFBlocks.ALMOND_TRAPDOOR.get());

        //Create and Food Tags
        this.tag(CAFTags.Blocks.ALMOND)
                .add(CAFBlocks.ALMOND_WALL_SIGN.get()).add(CAFBlocks.ALMOND_SIGN.get())
                .add(CAFBlocks.ALMOND_DOOR.get()).add(CAFBlocks.ALMOND_TRAPDOOR.get())
                .add(CAFBlocks.ALMOND_BUTTON.get()).add(CAFBlocks.ALMOND_PRESSURE_PLATE.get())
                .add(CAFBlocks.ALMOND_FENCE.get()).add(CAFBlocks.ALMOND_FENCE_GATE.get())
                .add(CAFBlocks.ALMOND_LOG.get()).add(CAFBlocks.STRIPPED_ALMOND_LOG.get())
                .add(CAFBlocks.ALMOND_WOOD.get()).add(CAFBlocks.STRIPPED_ALMOND_WOOD.get())
                .add(CAFBlocks.ALMOND_PLANKS.get()).add(CAFBlocks.ALMOND_STAIRS.get())
                .add(CAFBlocks.ALMOND_SLAB.get());
        this.tag(CAFTags.Blocks.ALMOND_LOGS)
                .add(CAFBlocks.ALMOND_WOOD.get()).add(CAFBlocks.ALMOND_LOG.get())
                .add(CAFBlocks.STRIPPED_ALMOND_WOOD.get()).add(CAFBlocks.STRIPPED_ALMOND_LOG.get());
        this.tag(CAFTags.Blocks.BARRELS)
                .add(CAFBlocks.ACACIA_BARREL.get()).add(CAFBlocks.ALMOND_BARREL.get())
                .add(CAFBlocks.BIRCH_BARREL.get()).add(CAFBlocks.CRIMSON_BARREL.get())
                .add(CAFBlocks.DARK_OAK_BARREL.get()).add(CAFBlocks.JUNGLE_BARREL.get())
                .add(CAFBlocks.OAK_BARREL.get()).add(CAFBlocks.SPRUCE_BARREL.get())
                .add(CAFBlocks.WARPED_BARREL.get());
        this.tag(CAFTags.Blocks.BASALT_ORE_REPLACEABLE).add(Blocks.BASALT);
        this.tag(CAFTags.Blocks.BERRY_BUSHES)
                .add(CAFBlocks.BLUEBERRY_BUSH.get()).add(CAFBlocks.WILD_BLUEBERRY_BUSH.get())
                .add(CAFBlocks.CRANBERRY_BUSH.get()).add(CAFBlocks.WILD_CRANBERRY_BUSH.get())
                .add(CAFBlocks.RASPBERRY_BUSH.get()).add(CAFBlocks.WILD_RASPBERRY_BUSH.get())
                .add(CAFBlocks.BLUE_GRAPE_BUSH.get()).add(CAFBlocks.WILD_BLUE_GRAPE_BUSH.get())
                .add(CAFBlocks.GREEN_GRAPE_BUSH.get()).add(CAFBlocks.WILD_GREEN_GRAPE_BUSH.get())
                .add(CAFBlocks.PURPLE_GRAPE_BUSH.get()).add(CAFBlocks.WILD_PURPLE_GRAPE_BUSH.get())
                .add(CAFBlocks.RED_GRAPE_BUSH.get()).add(CAFBlocks.WILD_RED_GRAPE_BUSH.get());
        this.tag(CAFTags.Blocks.BLACKSTONE_ORE_REPLACEABLE).add(Blocks.BLACKSTONE);
        this.tag(CAFTags.Blocks.CRIMSON_ORE_REPLACEABLE).add(Blocks.CRIMSON_NYLIUM);
        this.tag(CAFTags.Blocks.CUTTING_BOARDS)
                .add(CAFBlocks.ACACIA_CUTTING_BOARD.get()).add(CAFBlocks.ALMOND_CUTTING_BOARD.get())
                .add(CAFBlocks.BIRCH_CUTTING_BOARD.get()).add(CAFBlocks.CRIMSON_CUTTING_BOARD.get())
                .add(CAFBlocks.DARK_OAK_CUTTING_BOARD.get()).add(CAFBlocks.JUNGLE_CUTTING_BOARD.get())
                .add(CAFBlocks.OAK_CUTTING_BOARD.get()).add(CAFBlocks.SPRUCE_CUTTING_BOARD.get())
                .add(CAFBlocks.WARPED_CUTTING_BOARD.get());
        this.tag(CAFTags.Blocks.END_ORE_REPLACEABLE).add(Blocks.END_STONE);
        this.tag(CAFTags.Blocks.NETHERRACK_ORE_REPLACEABLE).add(Blocks.NETHERRACK);
        this.tag(CAFTags.Blocks.WARPED_NYLIUM_ORE_REPLACEABLE).add(Blocks.WARPED_NYLIUM);
        this.tag(CAFTags.Blocks.FAN_PROCESSING_CATALYSTS_FREEZING)
                .add(Blocks.POWDER_SNOW).add(Blocks.SNOW).add(Blocks.SNOW_BLOCK);
        this.tag(CAFTags.Blocks.MINEABLE_WITH_HAMMER).addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(CAFTags.Blocks.forgeTag("mineable/hammer"));
    }

    @Override
    public String getName() {
        return "Create and Food Block Tags";
    }
}
