package net.egorplaytv.caf.energy.block.entity;

import com.simibubi.create.foundation.utility.LangBuilder;
import net.egorplaytv.caf.energy.EnergyPacket;
import net.egorplaytv.caf.energy.energy_interface.IEnergyBlock;
import net.egorplaytv.caf.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EnergyConvertorBlockEntity extends EnergyBaseBlockEntity implements IEnergyBlock {
    public EnergyConvertorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(10000, typeIn, pos, state);
    }

    @Override
    public void transferEnergy() {
        if (getSpeed() != 0) {
            if (this.energyStorage.getEnergyStored() < this.energyStorage.getMaxEnergyStored()) {
                this.energyStorage.receiveEnergy(Math.round(Mth.clamp(Math.abs(getSpeed() / 16f), 1, 512)), false);
            }

            if (this.energyStorage.getEnergyStored() > this.energyStorage.getMaxEnergyStored()) {
                this.energyStorage.setEnergyStored(this.energyStorage.getMaxEnergyStored());
            }
        }

        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(direction);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);

            if (neighbor instanceof EnergyBaseBlockEntity target) {
                EnergyPacket packet = extractEnergyPacket(100, true, 2);
                if (packet == null) {
                    continue;
                }

                int received = target.receiveEnergyPacket(packet, true);
                if (received > 0) {
                    extractEnergyPacket(received, false, 2);
                    target.receiveEnergyPacket(new EnergyPacket(received, this.worldPosition, packet.ttl - 1), false);
                    break;
                }
            }
        }
    }

    @Override
    public int receiveEnergyPacket(EnergyPacket packet, boolean simulate) {
        return 0;
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        if (getSpeed() != 0 || getSpeed() == 0) {
            int energyStored = this.energyStorage.getEnergyStored();
            int maxEnergyStorage = this.energyStorage.getMaxEnergyStored();

            LangBuilder caf = Lang.text("CAF");
            LangBuilder kcaf = Lang.text("kCAF");

            Lang.translate("gui.goggles.energy_stats")
                    .forGoggles(tooltip);

            if (energyStored < 1000) {
                if (maxEnergyStorage >= 1000) {
                    Lang.builder()
                            .add(Lang.number(energyStored)
                                    .add(caf)
                                    .style(ChatFormatting.GOLD))
                            .text(ChatFormatting.GRAY, " / ")
                            .add(Lang.number((maxEnergyStorage / 1000F))
                                    .add(kcaf)
                                    .style(ChatFormatting.GRAY))
                            .forGoggles(tooltip);
                } else {
                    Lang.builder()
                            .add(Lang.number(energyStored)
                                    .add(caf)
                                    .style(ChatFormatting.GOLD))
                            .text(ChatFormatting.GRAY, " / ")
                            .add(Lang.number(maxEnergyStorage)
                                    .add(caf)
                                    .style(ChatFormatting.GRAY))
                            .forGoggles(tooltip);
                }
            } else {
                if (maxEnergyStorage >= 1000) {
                    Lang.builder()
                            .add(Lang.number((energyStored / 1000F))
                                    .add(kcaf)
                                    .style(ChatFormatting.GOLD))
                            .text(ChatFormatting.GRAY, " / ")
                            .add(Lang.number((maxEnergyStorage / 1000F))
                                    .add(kcaf)
                                    .style(ChatFormatting.GRAY))
                            .forGoggles(tooltip);
                }
            }
        }
        return true;
    }
}