package net.egorplaytv.caf.energy;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.EnergyStorage;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.EnumMap;
import java.util.Map;

public class CableBlockEntity extends BlockEntity {
    private final EnergyStorage energyStorage;
    private final float transferRate;
    private final LazyOptional<IEnergyStorage> energyCap;
    private final Map<Direction, Float> remainingTransfer = new EnumMap<>(Direction.class);

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(CAFBlockEntities.CABLE_BLOCK.get(), pos, state);
        if (state.getBlock() instanceof CableBlock cable) {
            this.transferRate = cable.getTransferRate();
        } else {
            this.transferRate = 100F;
        }
        this.energyStorage = new EnergyStorage(this.transferRate, 0F, this.transferRate, this.transferRate);
        this.energyCap = LazyOptional.of(() -> this.energyStorage);
    }

    public EnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public float getTransferRate() {
        return transferRate;
    }

    // ---------- Tick ----------

    public void tick() {
        if (level == null || level.isClientSide()) return;
        for (Direction dir : Direction.values()) {
            remainingTransfer.put(dir, transferRate);
        }

        BlockState state = getBlockState();

        for (Direction dir : Direction.values()) {
            ConnectionType conn = state.getValue(getProperty(dir));
            if (conn == ConnectionType.NONE) continue;

            BlockPos neighborPos = getBlockPos().relative(dir);
            BlockEntity neighbor = level.getBlockEntity(neighborPos);
            if (neighbor == null) continue;

            if (conn == ConnectionType.CABLE && neighbor instanceof CableBlockEntity otherCable) {
                balanceWithCable(otherCable, dir);
            } else {
                LazyOptional<IEnergyStorage> capOpt = neighbor.getCapability(EnergyCapability.ENERGY, dir.getOpposite());
                capOpt.ifPresent(otherStorage -> interactWithBlock(otherStorage, dir));
            }
        }
    }

    /**
     * It draws energy from a neighboring producer and transfers it to a neighboring consumer.
     * The direction points from the cable to the adjacent unit.
     */
    private void interactWithBlock(IEnergyStorage otherStorage, Direction dir) {
        float budget = remainingTransfer.getOrDefault(dir, transferRate);
        if (budget <= 0) return;

        if (otherStorage.canExtract()) {
            CAFEnergyUnits simulatedExtract = otherStorage.extractEnergy(budget, otherStorage.getEnergyStored().getRawDimperage(), true);
            if (!simulatedExtract.isEmpty()) {
                float canGive = simulatedExtract.getRawEnergy();
                CAFEnergyUnits simulatedReceive = energyStorage.receiveEnergy(canGive, simulatedExtract.getRawDimperage(), true);
                float actually = simulatedReceive.getRawEnergy();
                if (actually > 0) {
                    otherStorage.extractEnergy(actually, simulatedReceive.getRawDimperage(), false);
                    energyStorage.receiveEnergy(actually, simulatedReceive.getRawDimperage(), false);
                    budget -= actually;
                }
            }
        }

        if (budget <= 0) {
            remainingTransfer.put(dir, 0F);
            return;
        }

        if (otherStorage.canReceive()) {
            CAFEnergyUnits simulatedExtract = energyStorage.extractEnergy(budget, energyStorage.getEnergyStored().getRawDimperage(), true);
            if (!simulatedExtract.isEmpty()) {
                float canGive = simulatedExtract.getRawEnergy();
                CAFEnergyUnits simulatedReceive = otherStorage.receiveEnergy(canGive, simulatedExtract.getRawDimperage(), true);
                float actually = simulatedReceive.getRawEnergy();
                if (actually > 0) {
                    energyStorage.extractEnergy(actually, simulatedReceive.getRawDimperage(), false);
                    otherStorage.receiveEnergy(actually, simulatedReceive.getRawDimperage(), false);
                    budget -= actually;
                }
            }
        }

        remainingTransfer.put(dir, budget);
    }

    /**
     * It balances energy between the two cables: transferring it from the one with more
     * to the one with less until they equalize (or until the transfer limit is reached).
     */
    private void balanceWithCable(CableBlockEntity other, Direction dir) {
        float budget = remainingTransfer.getOrDefault(dir, transferRate);
        if (budget <= 0) return;

        EnergyStorage self = this.energyStorage;
        EnergyStorage target = other.energyStorage;

        float selfEnergy = self.getEnergyStored().getRawEnergy();
        float targetEnergy = target.getEnergyStored().getRawEnergy();

        if (selfEnergy <= targetEnergy) return;

        float diff = (selfEnergy - targetEnergy) / 2F;
        float amount = Math.min(diff, budget);
        amount = Math.min(amount, target.getMaxEnergyStored() - targetEnergy);

        if (amount <= 0) return;

        CAFEnergyUnits extracted = self.extractEnergy(amount, self.getEnergyStored().getRawDimperage(), false);
        if (!extracted.isEmpty()) {
            target.receiveEnergy(extracted.getRawEnergy(), extracted.getRawDimperage(), false);
        }

        remainingTransfer.put(dir, budget - amount);
    }

    // ---------- Capability ----------

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == EnergyCapability.ENERGY) {
            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCap.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
    }

    // ---------- NBT ----------

    private static final String KEY_STORAGE = "CableEnergyStorage";

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put(KEY_STORAGE, energyStorage.serializeNBT());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains(KEY_STORAGE)) {
            energyStorage.deserializeNBT(nbt.getCompound(KEY_STORAGE));
        }
    }

    // ---------- Utilities ----------

    private static final Map<Direction, EnumProperty<ConnectionType>> PROPS = new EnumMap<>(Direction.class);

    static {
        PROPS.put(Direction.NORTH, CableBlock.NORTH);
        PROPS.put(Direction.SOUTH, CableBlock.SOUTH);
        PROPS.put(Direction.EAST,  CableBlock.EAST);
        PROPS.put(Direction.WEST,  CableBlock.WEST);
        PROPS.put(Direction.UP,    CableBlock.UP);
        PROPS.put(Direction.DOWN,  CableBlock.DOWN);
    }

    private static EnumProperty<ConnectionType> getProperty(Direction dir) {
        return PROPS.get(dir);
    }
}
