package net.egorplaytv.caf.block.pattern.interfaces;

import com.simibubi.create.foundation.utility.LangBuilder;
import net.egorplaytv.caf.config.CAFConfigs;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.egorplaytv.caf.units.weight.CAFWeightUnits;
import net.egorplaytv.caf.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHaveGoggleInformation extends com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation {

    default boolean containedWeightTooltip(List<Component> tooltip, boolean isPlayerSneaking, ItemLike item) {

        LangBuilder gram = Lang.translate("generic.unit.gram");
        LangBuilder kilogram = Lang.translate("generic.unit.kilogram");
        LangBuilder ton = Lang.translate("generic.unit.ton");

        LangBuilder caf = Lang.translate("generic.unit.caf");
        LangBuilder kcaf = Lang.translate("generic.unit.kcaf");

        LangBuilder error = Lang.text("0-Ld").style(ChatFormatting.GOLD);

        Lang.translate("gui.goggles.weight_stats")
                .forGoggles(tooltip);

        if (item != Items.AIR)
            if (CAFWeightUnits.getWeightItems().get(item) != null) {
                Lang.itemName(new ItemStack(item))
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
                if (CAFConfigs.common().gameSettings.enableGrams.get() && CAFConfigs.common().gameSettings.enableKilograms.get()
                        && CAFConfigs.common().gameSettings.enableTones.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000000 : CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000
                                            ? CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000 : CAFWeightUnits.getWeightItems().get(item).getWeight())
                                    .add(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? ton : CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000
                                            ? kilogram : gram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableGrams.get() && CAFConfigs.common().gameSettings.enableKilograms.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000
                                            ? CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000 : CAFWeightUnits.getWeightItems().get(item).getWeight())
                                    .add(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000
                                            ? kilogram : gram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableGrams.get() && CAFConfigs.common().gameSettings.enableTones.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000000 : CAFWeightUnits.getWeightItems().get(item).getWeight())
                                    .add(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? ton : gram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableKilograms.get() && CAFConfigs.common().gameSettings.enableTones.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000000 : CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000)
                                    .add(CAFWeightUnits.getWeightItems().get(item).getWeight() >= 1000000
                                            ? ton : kilogram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableGrams.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight())
                                    .add(gram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableKilograms.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000)
                                    .add(kilogram)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else if (CAFConfigs.common().gameSettings.enableTones.get()) {
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(CAFWeightUnits.getWeightItems().get(item).getWeight() / 1000000)
                                    .add(ton)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                } else {
                    float cafUnits = (Math.round(((CAFWeightUnits.getWeightItems().get(item).getWeight() * 3.14F) / 10F) * 100F) / 100F);
                    Lang.translate("gui.goggles.weight_stats.weight")
                            .add(Lang.number(cafUnits >= 1000 ? cafUnits / 1000 : cafUnits)
                                    .add(cafUnits >= 1000 ? kcaf : caf)
                                    .style(ChatFormatting.GOLD))
                            .style(ChatFormatting.GRAY)
                            .forGoggles(tooltip, 1);
                }
            } else
                Lang.translate("gui.goggles.weight_stats.error")
                        .add(error)
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
        else {
            if (CAFConfigs.common().gameSettings.enableGrams.get()) {
                Lang.translate("gui.goggles.weight_stats.weight")
                        .add(Lang.number(0)
                                .add(gram)
                                .style(ChatFormatting.GOLD))
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
            } else if (CAFConfigs.common().gameSettings.enableKilograms.get()) {
                Lang.translate("gui.goggles.weight_stats.weight")
                        .add(Lang.number(0)
                                .add(kilogram)
                                .style(ChatFormatting.GOLD))
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
            } else if (CAFConfigs.common().gameSettings.enableTones.get()) {
                Lang.translate("gui.goggles.weight_stats.weight")
                        .add(Lang.number(0)
                                .add(ton)
                                .style(ChatFormatting.GOLD))
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
            } else {
                Lang.translate("gui.goggles.weight_stats.weight")
                        .add(Lang.number(0)
                                .add(caf)
                                .style(ChatFormatting.GOLD))
                        .style(ChatFormatting.GRAY)
                        .forGoggles(tooltip, 1);
            }
        }

        return true;
    }

    default boolean containedEnergyTooltip(List<Component> tooltip, boolean isPlayerSneaking,
                                           LazyOptional<IEnergyStorage> handler) {
        Optional<IEnergyStorage> resolve = handler.resolve();
        if (!resolve.isPresent())
            return false;

        IEnergyStorage storage = resolve.get();

        LangBuilder caf = Lang.translate("generic.unit.caf");
        LangBuilder kcaf = Lang.translate("generic.unit.kcaf");

        Lang.translate("gui.goggles.energy_container")
                .forGoggles(tooltip);


        if (storage.getEnergyStored().getEnergy() >= 1000 && storage.getMaxEnergyStored().getEnergy() > 1000) {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored().getEnergy() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored().getEnergy() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        } else if (storage.getEnergyStored().getEnergy() < 1000 && storage.getMaxEnergyStored().getEnergy() > 1000) {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored().getEnergy())
                            .add(caf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored().getEnergy() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        } else {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored().getEnergy())
                            .add(caf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored().getEnergy())
                            .add(caf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        }

        return true;
    }
}
