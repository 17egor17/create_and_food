package net.egorplaytv.caf.damage;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFDamageSource {
    public static final DamageSource DRANK = new DamageSource(MOD_ID + ".drank").bypassArmor().setScalesWithDifficulty();
    public static final DamageSource POLISHING = new DamageSource(MOD_ID + ".grinder_polishing_damage").bypassArmor();
    public static final DamageSource HOT_METAL = new DamageSource(MOD_ID + ".hot_metal").setScalesWithDifficulty();
    public static final DamageSource RASPBERRY_BUSH = new DamageSource(MOD_ID + ".raspberry_bush");


    public static void drank(Entity entity, float amount){
        entity.hurt(DRANK, amount);
    }

    public static void polishing(Entity entity, float amount){
        entity.hurt(POLISHING, amount);
    }

    public static void hotMetal(Entity entity, float amount){
        entity.hurt(HOT_METAL, amount);
    }

    public static void raspberryBush(Entity entity, float amount){
        entity.hurt(RASPBERRY_BUSH, amount);
    }
}
