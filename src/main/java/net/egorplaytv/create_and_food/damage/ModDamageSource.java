package net.egorplaytv.create_and_food.damage;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource {
    public static DamageSource DRANK = new DamageSource("create_and_food.drank").bypassArmor().setScalesWithDifficulty();

}
