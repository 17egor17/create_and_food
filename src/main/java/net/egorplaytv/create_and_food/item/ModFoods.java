package net.egorplaytv.create_and_food.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PIZZA = (new FoodProperties.Builder()).nutrition(16).saturationMod( 0.8F).build();
    public static final FoodProperties PIZZA_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationMod( 0.1F).build();
    public static final FoodProperties BERRY_CAKE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties BERRY_GLITTER_CAKE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties MACARONI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties MARSHMALLOW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties RYE_BREAD = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties BERRIES = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties HONEY_MILK = (new FoodProperties.Builder().effect(() -> {
        return new MobEffectInstance(MobEffects.REGENERATION, 3 * 60 * 20, 0, false, false);
    }, 1.0F).build());
}
