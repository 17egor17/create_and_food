package net.egorplaytv.caf.energy.block.entity;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.egorplaytv.caf.energy.EnergyPacket;
import net.egorplaytv.caf.energy.EnergyStorage;
import net.egorplaytv.caf.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStorage;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStored;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyBaseBlockEntity extends KineticBlockEntity implements IEnergyStored {

    protected final EnergyStorage energyStorage;

    protected final LazyOptional<IEnergyStorage> lazyEnergyHandler;

    public EnergyBaseBlockEntity(int capacity, BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        this.energyStorage = new EnergyStorage(capacity);
        this.lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EnergyCapability.ENERGY)
            return lazyEnergyHandler.cast();

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putInt("energyStored", energyStorage.getEnergyStored());
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        energyStorage.setEnergyStored(tag.getInt("energyStored"));
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide){
            transferEnergy();
        }
    }

    public abstract void transferEnergy();

    @Override
    public int receiveEnergyPacket(EnergyPacket packet, boolean simulate) {
        if (packet.isExpired()) return 0;
        if (packet.sourcePos.equals(this.worldPosition)) return 0;

        int space = Mth.clamp(energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored(), 0, energyStorage.getMaxEnergyStored());
        int toAccept = Math.min(packet.amount, space);

        if (!simulate && toAccept > 0) {
            energyStorage.setEnergyStored(energyStorage.getEnergyStored() + toAccept);
        }
        return toAccept;
    }

    @Override
    @Nullable
    public EnergyPacket extractEnergyPacket(int maxAmount, boolean simulate, float ttl) {
        int available = Math.min(maxAmount, energyStorage.getEnergyStored());
        if (available <= 0) return null;

        if (!simulate) {
            energyStorage.setEnergyStored(energyStorage.getEnergyStored() - available);
        }
        return new EnergyPacket(available, this.worldPosition, ttl);
    }
}
