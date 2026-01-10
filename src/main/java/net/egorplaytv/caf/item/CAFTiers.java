package net.egorplaytv.caf.item;

import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static net.egorplaytv.caf.item.CAFItems.*;

public enum CAFTiers implements Tier {
    IRON(2, 250, 6.0F, 2.0F, 14,
            () -> Ingredient.of(IRON_INGOT.get())),
    COPPER(2, 200, 6.0F, 2.0F, 14,
            () -> Ingredient.of(COPPER_INGOT.get())),
    GOLD(0, 32, 12.0F, 0.0F, 22,
            () -> Ingredient.of(GOLD_INGOT.get())),
    NETHERITE(4, 2031, 9.0F, 4.0F, 15,
            () -> Ingredient.of(NETHERITE_INGOT.get())),
    STEEL(4, 1600, 9.0F, 4.0F, 15,
            () -> Ingredient.of(STEEL_INGOT.get())),
    TANTALUM(5, 2500, 10.0F, 5.0F, 16,
            () -> Ingredient.of(TANTALUM_INGOT.get())),
    TUNGSTEN(6, 3000, 10.5F, 5.5F, 17,
            () -> Ingredient.of(TUNGSTEN_INGOT.get()));

    public static TagKey<Block> getTagFromVanillaTier(CAFTiers tier) {
        return switch(tier) {
                    case IRON, COPPER -> BlockTags.NEEDS_IRON_TOOL;
                    case GOLD -> Tags.Blocks.NEEDS_GOLD_TOOL;
                    case NETHERITE, STEEL -> Tags.Blocks.NEEDS_NETHERITE_TOOL;
                    case TANTALUM -> CAFTags.Blocks.NEEDS_TANTALUM_TOOL;
                    case TUNGSTEN -> CAFTags.Blocks.NEEDS_TUNGSTEN_TOOL;
                };
    }

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    CAFTiers(int pLevel, int pUses, float pSpeed, float pDamage,
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

    @Nullable
    public TagKey<Block> getTag() {
        return getTagFromVanillaTier(this); }
}

