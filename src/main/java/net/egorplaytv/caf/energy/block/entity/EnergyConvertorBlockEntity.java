package net.egorplaytv.caf.energy.block.entity;

import net.egorplaytv.caf.energy.EnergyPacket;
import net.egorplaytv.caf.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.energy.energy_interface.IEnergyBlock;
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
        return containedEnergyTooltip(tooltip, isPlayerSneaking, this.getCapability(EnergyCapability.ENERGY));
    }
}