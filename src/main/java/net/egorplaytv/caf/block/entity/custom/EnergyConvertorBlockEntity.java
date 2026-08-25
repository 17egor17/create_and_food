package net.egorplaytv.caf.block.entity.custom;

import net.egorplaytv.caf.block.custom.EnergyConvertorBlock;
import net.egorplaytv.caf.entity.WrappedEnergyHandler;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class EnergyConvertorBlockEntity extends EnergyBaseBlockEntity {
    public EnergyConvertorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(10000, 2000, typeIn, pos, state);
    }

    @Override
    public void transferEnergy(Level level) {
        if (getSpeed() != 0) {
            if (this.energyStorage.getEnergyStored().getEnergy() < this.energyStorage.getMaxEnergyStored()) {
                this.energyStorage.receiveEnergy(Math.round(Mth.clamp(Math.abs(getSpeed() / 16f), 1, 512)), false);
            }

            if (this.energyStorage.getEnergyStored().getEnergy() > this.energyStorage.getMaxEnergyStored()) {
                this.energyStorage.setEnergyStored(new CAFEnergyUnits(this.energyStorage.getMaxEnergyStored()));
            }
        }

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = getBlockPos().relative(dir);
            if (level instanceof ServerLevel serverLevel) {
                transferEnergyToCable(serverLevel, neighborPos, dir);
            }
        }
    }

    private void transferEnergyToCable(Level level, BlockPos neighborPos, Direction dir) {
        if (!energyStorage.canExtract()) {
            return;
        }

        BlockEntity neighborBe = level.getBlockEntity(neighborPos);
        if (neighborBe == null) {
            return;
        }

        LazyOptional<IEnergyStorage> opt = neighborBe.getCapability(EnergyCapability.ENERGY, dir.getOpposite());

        if (!opt.isPresent()) {
            return;
        }

        var supplier = opt.resolve();
        if (supplier == null) {
            return;
        }

        IEnergyStorage neighborStorage = supplier.get();
        if (neighborStorage == null || !neighborStorage.canReceive()) {
            return;
        }

        float maxTransfer = Math.min(energyStorage.getMaxTransfer(), neighborStorage.getMaxTransfer());

        CAFEnergyUnits simulated = neighborStorage.receiveEnergy(maxTransfer, true);
        float toAccept = simulated.getRawEnergy();
        if (toAccept <= 0F) {
            return;
        }

        CAFEnergyUnits actual = energyStorage.extractEnergy(toAccept, false);
        if (actual.isEmpty()) {
            return;
        }

        neighborStorage.receiveEnergy(actual.getRawEnergy(), false);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        return containedEnergyTooltip(tooltip, isPlayerSneaking, this.getCapability(EnergyCapability.ENERGY));
    }
}