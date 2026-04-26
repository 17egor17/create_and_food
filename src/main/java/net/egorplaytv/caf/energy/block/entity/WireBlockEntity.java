package net.egorplaytv.caf.energy.block.entity;

import com.simibubi.create.foundation.utility.LangBuilder;
import net.egorplaytv.caf.energy.EnergyPacket;
import net.egorplaytv.caf.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WireBlockEntity extends EnergyBaseBlockEntity {
    private final Set<BlockPos> blockedSources = new HashSet<>();

    public WireBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(500, pType, pPos, pBlockState);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            if (level.getGameTime() % 100 == 0) {
                this.blockedSources.clear();
            }
        }
    }

    public void transferEnergy() {
        if (getSpeed() != 0)
            return;

        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(direction);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);

            if (neighbor instanceof EnergyBaseBlockEntity target) {
                EnergyPacket packet = extractEnergyPacket(100, true, 2);
                if (packet == null) continue;

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
        if (packet.isExpired()) return 0;
        if (blockedSources.contains(packet.sourcePos)) return 0;

        int received = super.receiveEnergyPacket(packet, simulate);
        if (!simulate && received > 0) {
            blockedSources.add(packet.sourcePos);
        }
        return received;
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);

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

        return true;
    }
}