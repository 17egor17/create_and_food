package net.egorplaytv.caf.block.pattern.interfaces;

import com.simibubi.create.foundation.utility.LangBuilder;
import net.egorplaytv.caf.energy.EnergyStorage;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStorage;
import net.egorplaytv.caf.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;
import java.util.Optional;

public interface IHaveGoggleInformation extends com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation {



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


        if (storage.getEnergyStored() >= 1000 && storage.getMaxEnergyStored() > 1000) {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        } else if (storage.getEnergyStored() < 1000 && storage.getMaxEnergyStored() > 1000) {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored())
                            .add(caf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored() / 1000F)
                            .add(kcaf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        } else {
            Lang.builder()
                    .add(Lang.number(storage.getEnergyStored())
                            .add(caf)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(storage.getMaxEnergyStored())
                            .add(caf)
                            .style(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        }

        return true;
    }
}
