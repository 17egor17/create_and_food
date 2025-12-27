package net.egorplaytv.create_and_food.datagen.loot;

import net.egorplaytv.create_and_food.block.custom.RicePaniclesBlock;
import net.egorplaytv.create_and_food.block.custom.RicePlantBlock;
import net.egorplaytv.create_and_food.block.custom.RyePlantBlock;
import net.egorplaytv.create_and_food.block.custom.berry.*;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import static net.egorplaytv.create_and_food.block.CAFBlocks.*;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        this.dropSelf(TORN_SOUL_CHAIN.get());
        this.dropSelf(STEEL_CHAIN.get());
        this.dropSelf(TORN_SOUL_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_WAXED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get());
        this.dropSelf(GLOWING_BRASS_STEEL_LANTERN.get());
        this.dropSelf(LANTERN.get());
        this.dropSelf(SOUL_LANTERN.get());
        this.dropOther(ALMOND_WALL_SIGN.get(), CAFItems.ALMOND_SIGN.get());
        this.dropOther(ALMOND_SIGN.get(), CAFItems.ALMOND_SIGN.get());
        this.add(ALMOND_DOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(ALMOND_TRAPDOOR.get());
        this.dropSelf(ALMOND_BUTTON.get());
        this.dropSelf(ALMOND_PRESSURE_PLATE.get());
        this.dropSelf(ALMOND_FENCE.get());
        this.dropSelf(ALMOND_FENCE_GATE.get());
        this.dropSelf(ALMOND_LOG.get());
        this.dropSelf(STRIPPED_ALMOND_LOG.get());
        this.dropSelf(ALMOND_WOOD.get());
        this.dropSelf(STRIPPED_ALMOND_WOOD.get());
        this.dropSelf(ALMOND_PLANKS.get());
        this.dropSelf(ALMOND_STAIRS.get());
        this.add(ALMOND_SLAB.get(), BlockLoot::createSlabItemTable);
        this.add(ALMOND_LEAVES.get(), (block) ->
                createLeavesDrops(block, ALMOND_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(PASTRY_TABLE.get());
        this.dropSelf(FURNITURE_CUTTER.get());
        this.dropSelf(MARBLE_BLAST_FURNACE.get());
        this.dropSelf(ACACIA_BARREL.get());
        this.dropSelf(ALMOND_BARREL.get());
        this.dropSelf(BIRCH_BARREL.get());
        this.dropSelf(CRIMSON_BARREL.get());
        this.dropSelf(DARK_OAK_BARREL.get());
        this.dropSelf(JUNGLE_BARREL.get());
        this.dropSelf(OAK_BARREL.get());
        this.dropSelf(SPRUCE_BARREL.get());
        this.dropSelf(WARPED_BARREL.get());
        this.dropSelf(TERMINAL.get());
        this.dropSelf(COBBLED_MARBLE.get());
        this.dropSelf(COBBLED_MARBLE_BLACK_GALAXY.get());
        this.dropSelf(COBBLED_MARBLE_PERLIN_PINK.get());
        this.dropSelf(FIRECLAY_BRICKS.get());
        this.dropSelf(ACACIA_TERRACE.get());
        this.dropSelf(ACACIA_TERRACE_STAIRS.get());
        this.dropSelf(ALMOND_TERRACE.get());
        this.dropSelf(ALMOND_TERRACE_STAIRS.get());
        this.dropSelf(BIRCH_TERRACE.get());
        this.dropSelf(BIRCH_TERRACE_STAIRS.get());
        this.dropSelf(CRIMSON_TERRACE.get());
        this.dropSelf(CRIMSON_TERRACE_STAIRS.get());
        this.dropSelf(DARK_OAK_TERRACE.get());
        this.dropSelf(DARK_OAK_TERRACE_STAIRS.get());
        this.dropSelf(JUNGLE_TERRACE.get());
        this.dropSelf(JUNGLE_TERRACE_STAIRS.get());
        this.dropSelf(OAK_TERRACE.get());
        this.dropSelf(OAK_TERRACE_STAIRS.get());
        this.dropSelf(SPRUCE_TERRACE.get());
        this.dropSelf(SPRUCE_TERRACE_STAIRS.get());
        this.dropSelf(WARPED_TERRACE.get());
        this.dropSelf(WARPED_TERRACE_STAIRS.get());
        this.dropSelf(FRAMED_CALCITE.get());
        this.dropSelf(STEEL_LAMP_BLOCK.get());
        this.add(RUBY_ORE.get(),
                (block) -> createOreDrop(RUBY_ORE.get(), CAFItems.RAW_RUBY.get()));
        this.add(DEEPSLATE_RUBY_ORE.get(),
                (block) -> createOreDrop(DEEPSLATE_RUBY_ORE.get(), CAFItems.RAW_RUBY.get()));
        this.dropSelf(RAW_RUBY_BLOCK.get());
        this.dropSelf(RUBY_BLOCK.get());
        this.add(STONE_TANTALUM_ORE.get(),
                (block) -> createOreDrop(STONE_TANTALUM_ORE.get(), CAFItems.RAW_TANTALUM.get()));
        this.add(DEEPSLATE_TANTALUM_ORE.get(),
                (block) -> createOreDrop(DEEPSLATE_TANTALUM_ORE.get(), CAFItems.RAW_TANTALUM.get()));
        this.add(TANTALUM_ORE.get(),
                (block) -> createOreDrop(TANTALUM_ORE.get(), CAFItems.RAW_TANTALUM.get()));
        this.add(BLACKSTONE_TANTALUM_ORE.get(),
                (block) -> createOreDrop(BLACKSTONE_TANTALUM_ORE.get(), CAFItems.RAW_TANTALUM.get()));
        this.dropSelf(RAW_TANTALUM_BLOCK.get());
        this.add(TUNGSTEN_ORE.get(),
                (block) -> createOreDrop(TUNGSTEN_ORE.get(), CAFItems.RAW_TUNGSTEN.get()));
        this.add(STONE_TUNGSTEN_ORE.get(),
                (block) -> createOreDrop(STONE_TUNGSTEN_ORE.get(), CAFItems.RAW_TUNGSTEN.get()));
        this.add(DEEPSLATE_TUNGSTEN_ORE.get(),
                (block) -> createOreDrop(DEEPSLATE_TUNGSTEN_ORE.get(), CAFItems.RAW_TUNGSTEN.get()));
        this.dropSelf(RAW_TUNGSTEN_BLOCK.get());
        this.add(FARMLAND_SUMP_SAND.get(), BlockLoot::createSlabItemTable);
        this.add(FARMLAND_SUMP_RED_SAND.get(), BlockLoot::createSlabItemTable);
        this.add(FARMLAND_SUMP_RICH_SOIL.get(), BlockLoot::createSlabItemTable);
        this.add(FARMLAND_SUMP_DIRT.get(), BlockLoot::createSlabItemTable);
        this.dropSelf(FERTILIZED_SAND.get());
        this.dropOther(SAND_FARMLAND.get(), Blocks.SAND);
        this.dropSelf(FERTILIZED_RED_SAND.get());
        this.dropOther(RED_SAND_FARMLAND.get(), Blocks.RED_SAND);
        this.dropOther(FLOODED_FARMLAND.get(), FARMLAND_SUMP_DIRT.get());
        this.dropOther(FLOODED_RICH_SOIL_FARMLAND.get(), FARMLAND_SUMP_RICH_SOIL.get());
        this.dropSelf(NIXIE_VASE.get());
        this.dropSelf(NIXIE_VASE_PERLIN_PINK.get());
        this.dropSelf(NIXIE_VASE_BLACK_GALAXY.get());
        this.dropSelf(ALMOND_SAPLING.get());
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(RYE_PLANT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyePlantBlock.AGE, 7));
        this.add(RYE_PLANT.get(), createCropDrops(RYE_PLANT.get(), CAFItems.RYE.get(),
                CAFItems.RYE_SEEDS.get(), lootitemcondition$builder));
        this.add(RICE_PLANT.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.RICE.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RICE_PLANT.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RicePlantBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.RICE_PANICLE.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
        this.add(RICE_CROP.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.RICE.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
        this.add(RICE_CROP_PANICLES.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RICE_PLANT.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RicePaniclesBlock.RICE_AGE, 3)))
                                .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.RICE_PANICLE.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
        this.add(BLUEBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.BLUEBERRY_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(BLUEBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.BLUEBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(BLUEBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.BLUEBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_BLUEBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_BLUEBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildBlueberryBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.BLUEBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_BLUEBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildBlueberryBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.BLUEBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(CRANBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.CRANBERRY_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CRANBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CranberryBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.CRANBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CRANBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CranberryBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.CRANBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_CRANBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_CRANBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildCranberryBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.CRANBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_CRANBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildCranberryBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.CRANBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(RASPBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.RASPBERRY_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RASPBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RaspberryBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.RASPBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RASPBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RaspberryBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.RASPBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_RASPBERRY_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_RASPBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildRaspberryBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.RASPBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_RASPBERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildRaspberryBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.RASPBERRY.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(BLUE_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.BLUE_GRAPE_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(BLUE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueGrapeBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.BLUE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(BLUE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueGrapeBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.BLUE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_BLUE_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_BLUE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildBlueGrapeBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.BLUE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_BLUE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildBlueGrapeBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.BLUE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(GREEN_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.GREEN_GRAPE_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(GREEN_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GreenGrapeBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.GREEN_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(GREEN_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GreenGrapeBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.GREEN_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_GREEN_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_GREEN_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildGreenGrapeBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.GREEN_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_GREEN_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildGreenGrapeBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.GREEN_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(PURPLE_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.PURPLE_GRAPE_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PURPLE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PurpleGrapeBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.PURPLE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PURPLE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PurpleGrapeBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.PURPLE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_PURPLE_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_PURPLE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildPurpleGrapeBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.PURPLE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_PURPLE_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildPurpleGrapeBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.PURPLE_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(RED_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(CAFItems.RED_GRAPE_SAPLING.get()))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RED_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RedGrapeBlock.AGE, 6)))
                                .add(LootItem.lootTableItem(CAFItems.RED_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RED_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RedGrapeBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(CAFItems.RED_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));
        this.add(WILD_RED_GRAPE_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_RED_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildRedGrapeBlock.AGE, 1)))
                                .add(LootItem.lootTableItem(CAFItems.RED_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_RED_GRAPE_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildRedGrapeBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(CAFItems.RED_GRAPE.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))));

        this.add(PUMPKIN_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(CAFItems.PUMPKIN_SEEDS.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PUMPKIN_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PumpkinBushBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(Blocks.PUMPKIN.asItem())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));

        this.add(WILD_PUMPKIN_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_PUMPKIN_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildPumpkinBushBlock.AGE, 5)))
                                .add(LootItem.lootTableItem(Blocks.PUMPKIN.asItem())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));


        this.add(MELON_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(CAFItems.MELON_SEEDS.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(MELON_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MelonBushBlock.AGE, 7)))
                                .add(LootItem.lootTableItem(Blocks.MELON.asItem())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));

        this.add(WILD_MELON_BUSH.get(), (block) ->
                applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_MELON_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildMelonBushBlock.AGE, 5)))
                                .add(LootItem.lootTableItem(Blocks.MELON.asItem())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));

        this.dropSelf(UNBAKED_CLAY.get());
        this.dropSelf(KITCHEN_TABLE.get());
        this.dropSelf(KITCHEN_TABLE_INNER.get());
        this.dropSelf(KITCHEN_TABLE_OUTER.get());
        this.dropSelf(FERMENTATION_BARREL.get());
        this.dropSelf(SCALES.get());
        this.dropSelf(OAK_CUTTING_BOARD.get());
        this.dropSelf(SPRUCE_CUTTING_BOARD.get());
        this.dropSelf(BIRCH_CUTTING_BOARD.get());
        this.dropSelf(JUNGLE_CUTTING_BOARD.get());
        this.dropSelf(ACACIA_CUTTING_BOARD.get());
        this.dropSelf(DARK_OAK_CUTTING_BOARD.get());
        this.dropSelf(CRIMSON_CUTTING_BOARD.get());
        this.dropSelf(WARPED_CUTTING_BOARD.get());
        this.dropSelf(ALMOND_CUTTING_BOARD.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
