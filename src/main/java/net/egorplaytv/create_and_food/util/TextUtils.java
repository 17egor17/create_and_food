package net.egorplaytv.create_and_food.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.simibubi.create.foundation.utility.LangBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class TextUtils {

    private static final MutableComponent NO_EFFECTS;
    public static final Style NORMAL_TOOLTIP_TEXT = Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(false);

    public TextUtils() {
    }

    public static MutableComponent of(LocalizationTooltips buttonToolTips, Object... args) {
        return of(buttonToolTips, NORMAL_TOOLTIP_TEXT, args);
    }

    public static MutableComponent of(LocalizationTooltips buttonToolTips, Style style, Object... args) {
        return buttonToolTips.text(args).copy().withStyle(style);
    }

    public static MutableComponent getToolTipTranslation(String key, Object... args) {
        return new TranslatableComponent("tooltip." + MOD_ID + "." + key, args);
    }

    public static MutableComponent getJeiTranslation(String key, Object... args) {
        return new TranslatableComponent("jei." + MOD_ID + "." + key, args);
    }
    public static MutableComponent getTranslation(String key, Object... args) {
        return new TranslatableComponent(key, args);
    }
    public static MutableComponent getModTranslation(String key, Object... args) {
        return new TranslatableComponent(MOD_ID + "." + key, args);
    }
    public static MutableComponent getBerryBushTranslation(String key, Object... args) {
        return new TranslatableComponent(MOD_ID + ".berry_bush." + key, args);
    }
    public static MutableComponent getWildBerryBushTranslation(String key, Object... args) {
        return new TranslatableComponent(MOD_ID + ".wild_berry_bush." + key, args);
    }
    public static MutableComponent getPumpkinAndMelonBushTranslation(String key, Object... args) {
        return new TranslatableComponent(MOD_ID + ".pumpkin_and_melon_bush." + key, args);
    }

    @OnlyIn(Dist.CLIENT)
    public static void addFoodEffectTooltip(ItemStack itemIn, List<Component> lores, float durationFactor) {
        FoodProperties foodStats = itemIn.getItem().getFoodProperties();
        if (foodStats != null) {
            List<Pair<MobEffectInstance, Float>> effectList = foodStats.getEffects();
            List<Pair<Attribute, AttributeModifier>> attributeList = Lists.newArrayList();
            Iterator var6;
            Pair pair;
            TranslatableComponent iformattabletextcomponent;
            MobEffect effect;
            if (effectList.isEmpty()) {
                lores.add(NO_EFFECTS);
            } else {
                for (var6 = effectList.iterator(); var6.hasNext(); lores.add(iformattabletextcomponent.withStyle(effect.getCategory().getTooltipFormatting()))) {
                    pair = (Pair) var6.next();
                    MobEffectInstance instance = (MobEffectInstance) pair.getFirst();
                    iformattabletextcomponent = new TranslatableComponent(instance.getDescriptionId());
                    effect = instance.getEffect();
                    Map<Attribute, AttributeModifier> attributeMap = effect.getAttributeModifiers();
                    if (!attributeMap.isEmpty()) {
                        Iterator var12 = attributeMap.entrySet().iterator();

                        while (var12.hasNext()) {
                            Map.Entry<Attribute, AttributeModifier> entry = (Map.Entry) var12.next();
                            AttributeModifier rawModifier = (AttributeModifier) entry.getValue();
                            AttributeModifier modifier = new AttributeModifier(rawModifier.getName(), effect.getAttributeModifierValue(instance.getAmplifier(), rawModifier), rawModifier.getOperation());
                            attributeList.add(new Pair((Attribute) entry.getKey(), modifier));
                        }
                    }

                    if (instance.getAmplifier() > 0) {
                        iformattabletextcomponent = new TranslatableComponent("potion.withAmplifier", new Object[]{iformattabletextcomponent, new TranslatableComponent("potion.potency." + instance.getAmplifier())});
                    }

                    if (instance.getDuration() > 20) {
                        iformattabletextcomponent = new TranslatableComponent("potion.withDuration", new Object[]{iformattabletextcomponent, MobEffectUtil.formatDuration(instance, durationFactor)});
                    }
                }
            }

            if (!attributeList.isEmpty()) {
                lores.add(TextComponent.EMPTY);
                lores.add((new TranslatableComponent("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));
                var6 = attributeList.iterator();

                while (var6.hasNext()) {
                    pair = (Pair) var6.next();
                    AttributeModifier modifier = (AttributeModifier) pair.getSecond();
                    double amount = modifier.getAmount();
                    double formattedAmount;
                    if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                        formattedAmount = modifier.getAmount();
                    } else {
                        formattedAmount = modifier.getAmount() * 100.0;
                    }

                    if (amount > 0.0) {
                        lores.add((new TranslatableComponent("attribute.modifier.plus." + modifier.getOperation().toValue(), new Object[]{ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), new TranslatableComponent(((Attribute) pair.getFirst()).getDescriptionId())})).withStyle(ChatFormatting.BLUE));
                    } else if (amount < 0.0) {
                        formattedAmount *= -1.0;
                        lores.add((new TranslatableComponent("attribute.modifier.take." + modifier.getOperation().toValue(), new Object[]{ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), new TranslatableComponent(((Attribute) pair.getFirst()).getDescriptionId())})).withStyle(ChatFormatting.RED));
                    }
                }
            }

        }
    }

    static {
        NO_EFFECTS = (new TranslatableComponent("effect.none")).withStyle(ChatFormatting.GRAY);
    }
}
