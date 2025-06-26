package net.egorplaytv.create_and_food.damage;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource {
    public static DamageSource DRANK = new DamageSource("create_and_food.drank").bypassArmor().setScalesWithDifficulty();
    public static DamageSource POLISHING = new DamageSource("create_and_food.grinder_polishing_damage").bypassArmor();
    public static DamageSource HOT_METAL = new DamageSource("create_and_food.hot_metal").setScalesWithDifficulty();

}
