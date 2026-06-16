package net.egorplaytv.caf.block.entity.custom;

import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EnergyConvertorBlockEntity extends EnergyBaseBlockEntity {
    public EnergyConvertorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(10000, typeIn, pos, state);
    }

    @Override
    public void transferEnergy() {
        if (getSpeed() != 0) {
            if (this.energyStorage.getEnergyStored().getEnergy() < this.energyStorage.getMaxEnergyStored().getEnergy()) {
                this.energyStorage.receiveEnergy(Math.round(Mth.clamp(Math.abs(getSpeed() / 16f), 1, 512)), false);
            }

            if (this.energyStorage.getEnergyStored().getEnergy() > this.energyStorage.getMaxEnergyStored().getEnergy()) {
                this.energyStorage.setEnergyStored(this.energyStorage.getMaxEnergyStored());
            }
        }

        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(direction);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        return containedEnergyTooltip(tooltip, isPlayerSneaking, this.getCapability(EnergyCapability.ENERGY));
    }
}