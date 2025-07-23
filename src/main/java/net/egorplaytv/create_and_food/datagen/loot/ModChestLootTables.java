package net.egorplaytv.create_and_food.datagen.loot;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.item.ModItems.*;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

import static net.egorplaytv.create_and_food.item.ModItems.*;

public class ModChestLootTables extends ChestLoot {
    public static final ResourceLocation VILLAGE_CONFECTIONER_HOUSE =
            new ResourceLocation(CreateAndFood.MOD_ID, "chests/loot_in_confectioner_house");
    public static final ResourceLocation VILLAGE_HOUSE =
            new ResourceLocation(CreateAndFood.MOD_ID, "chests/village_house");

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(VILLAGE_CONFECTIONER_HOUSE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(POWDERED_SUGAR.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 5))
                        .add(LootItem.lootTableItem(ROASTED_COCOA_BEANS.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                        .add(LootItem.lootTableItem(COCOA_POWDER.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 6))
                        .add(LootItem.lootTableItem(BERRY_CAKE.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                        .add(LootItem.lootTableItem(GLOW_BERRY_CAKE.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 4))
                        .add(LootItem.lootTableItem(RYE.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 7)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                        .add(LootItem.lootTableItem(RYE_SEEDS.get()).setWeight(5))));
        consumer.accept(VILLAGE_HOUSE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 3))
                        .add(LootItem.lootTableItem(RYE_SEEDS.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                        .add(LootItem.lootTableItem(RYE.get()).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(COPPER_COIN.get()).setWeight(30))
                        .add(LootItem.lootTableItem(IRON_COIN.get()).setWeight(20))
                        .add(LootItem.lootTableItem(GOLDEN_COIN.get()).setWeight(10))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))
                                .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));
    }
}
