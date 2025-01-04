package net.egorplaytv.create_and_food.item;

import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static net.egorplaytv.create_and_food.item.ModItems.*;

public enum ModTiers implements Tier {
    COPPER(2, 200, 6.0F, 2.0F, 14,
            () -> Ingredient.of(Items.COPPER_INGOT)),
    STEEL(4, 1400, 9.0F, 4.0F, 15,
            () -> Ingredient.of(STEEL_INGOT.get())),
    TANTALUM(5, 2000, 10.0F, 5.0F, 16,
            () -> Ingredient.of(TANTALUM_INGOT.get())),
    TUNGSTEN(6, 2500, 10.5F, 5.5F, 17,
            () -> Ingredient.of(TUNGSTEN_INGOT.get()));

    public static TagKey<Block> getTagFromVanillaTier(ModTiers tier) {
        return switch(tier)
                {
//                    case YOUR_NAME -> Tags.Blocks.NEEDS_WOOD_TOOL;
//                    case YOUR_NAME -> Tags.Blocks.NEEDS_GOLD_TOOL;
//                    case YOUR_NAME -> BlockTags.NEEDS_STONE_TOOL;
                    case COPPER -> BlockTags.NEEDS_IRON_TOOL;
//                    case YOUR_NAME -> BlockTags.NEEDS_DIAMOND_TOOL;
                    case STEEL -> ModTags.Blocks.NEEDS_STEEL_TOOL;
//                    case YOUR_NAME -> Tags.Blocks.NEEDS_NETHERITE_TOOL;
                    case TANTALUM -> ModTags.Blocks.NEEDS_TANTALUM_TOOL;
                    case TUNGSTEN -> ModTags.Blocks.NEEDS_TUNGSTEN_TOOL;
                };
    }

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModTiers(int pLevel, int pUses, float pSpeed, float pDamage,
                     int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    public int getUses() {

        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @javax.annotation.Nullable
    public TagKey<Block> getTag() {
        return getTagFromVanillaTier(this); }
}

