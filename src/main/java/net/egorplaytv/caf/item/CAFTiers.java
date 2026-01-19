package net.egorplaytv.caf.item;

import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static net.egorplaytv.caf.item.CAFItems.*;

public enum CAFTiers implements Tier {
    COPPER(2, 200, 6.0F, 2.0F, 14,
            () -> Ingredient.of(COPPER_INGOT.get())),
    IRON(3, 250, 6.0F, 2.0F, 14,
            () -> Ingredient.of(IRON_INGOT.get())),
    GOLD(0, 32, 12.0F, 0.0F, 22,
            () -> Ingredient.of(GOLD_INGOT.get())),
    DIAMOND(4, 1561, 8.0F, 3.0F, 10,
            () -> Ingredient.of(Items.DIAMOND)),
    NETHERITE(5, 2030, 9.0F, 4.0F, 15,
            () -> Ingredient.of(NETHERITE_INGOT.get())),
    STEEL(5, 1600, 9.0F, 4.0F, 15,
            () -> Ingredient.of(STEEL_INGOT.get())),
    TANTALUM(6, 2500, 10.0F, 5.0F, 16,
            () -> Ingredient.of(TANTALUM_INGOT.get())),
    TUNGSTEN(7, 3000, 10.5F, 5.5F, 17,
            () -> Ingredient.of(TUNGSTEN_INGOT.get()));

    public static TagKey<Block> getTagFromVanillaTier(CAFTiers tier) {
        return switch (tier) {
            case COPPER -> CAFTags.Blocks.NEEDS_COPPER_TOOL;
            case IRON -> BlockTags.NEEDS_IRON_TOOL;
            case GOLD -> Tags.Blocks.NEEDS_GOLD_TOOL;
            case DIAMOND -> BlockTags.NEEDS_DIAMOND_TOOL;
            case NETHERITE, STEEL -> Tags.Blocks.NEEDS_NETHERITE_TOOL;
            case TANTALUM -> CAFTags.Blocks.NEEDS_TANTALUM_TOOL;
            case TUNGSTEN -> CAFTags.Blocks.NEEDS_TUNGSTEN_TOOL;
        };
    }

    private final int level;
    private int uses;
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

    public static Tier diamond(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = DIAMOND.getUses();
            case IRON -> uses = (int) (DIAMOND.getUses() * 1.5F);
        }
        DIAMOND.setUses(uses);
        return DIAMOND;
    }

    public static Tier iron(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = IRON.getUses();
            case IRON -> uses = (int) (IRON.getUses() * 1.5F);
        }
        IRON.setUses(uses);
        return IRON;
    }

    public static Tier copper(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = COPPER.getUses();
            case IRON -> uses = (int) (COPPER.getUses() * 1.5F);
        }
        COPPER.setUses(uses);
        return COPPER;
    }

    public static Tier gold(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = GOLD.getUses();
            case IRON -> uses = (int) (GOLD.getUses() * 1.5F);
        }
        GOLD.setUses(uses);
        return GOLD;
    }

    public static Tier netherite(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = NETHERITE.getUses();
            case IRON -> uses = (int) (NETHERITE.getUses() * 1.5F);
        }
        NETHERITE.setUses(uses);
        return NETHERITE;
    }

    public static Tier steel(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = STEEL.getUses();
            case IRON -> uses = (int) (STEEL.getUses() * 1.5F);
        }
        STEEL.setUses(uses);
        return STEEL;
    }

    public static Tier tantalum(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = TANTALUM.getUses();
            case IRON -> uses = (int) (TANTALUM.getUses() * 1.5F);
        }
        TANTALUM.setUses(uses);
        return TANTALUM;
    }

    public static Tier tungsten(HandelType handelType) {
        int uses = 0;
        switch (handelType) {
            case WOOD -> uses = TUNGSTEN.getUses();
            case IRON -> uses = (int) (TUNGSTEN.getUses() * 1.5F);
        }
        TUNGSTEN.setUses(uses);
        return TUNGSTEN;
    }

    public void setUses(int uses) {
        this.uses = uses;
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
        return getTagFromVanillaTier(this);
    }

    public enum HandelType {
        WOOD, IRON
    }
}

