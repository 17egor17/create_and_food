package net.egorplaytv.create_and_food.damage;

import net.minecraft.world.damagesource.DamageSource;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class ModDamageSource {
    public static final DamageSource DRANK = new DamageSource(MOD_ID + ".drank").bypassArmor().setScalesWithDifficulty();
    public static final DamageSource POLISHING = new DamageSource(MOD_ID + ".grinder_polishing_damage").bypassArmor();
    public static final DamageSource HOT_METAL = new DamageSource(MOD_ID + ".hot_metal").setScalesWithDifficulty();
    public static final DamageSource RASPBERRY_BUSH = new DamageSource(MOD_ID + ".raspberry_bush");
}
