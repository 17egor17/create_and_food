package net.egorplaytv.caf.energy;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.EnergyStorage;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import java.util.List;
import java.util.Optional;

public class EnergyCableBlockEntity extends BlockEntity implements IHaveGoggleInformation {
    private final EnergyStorage energyStorage;
    private final LazyOptional<IEnergyStorage> cap;

    private static final float MAX_TRANSFER_PER_TICK = 1000F;

    public EnergyCableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.ENERGY_CABLE.get(), pPos, pBlockState);
        this.energyStorage = new EnergyStorage(1000F, 10F, MAX_TRANSFER_PER_TICK, MAX_TRANSFER_PER_TICK);
        this.cap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        CompoundTag nbt = energyStorage.serializeNBT();
        tag.put("Energy", nbt);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Energy")) {
            CompoundTag nbt = tag.getCompound("Energy");
            energyStorage.deserializeNBT(nbt);
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == EnergyCapability.ENERGY) {
            return this.cap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        this.cap.invalidate();
        super.setRemoved();
    }

    public void onNeighborChanged(ServerLevel level, BlockPos pos) {
        updateConnections(level, pos);
    }

    public void invalidate(ServerLevel level) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = getBlockPos().relative(dir);
            updateNeighborConnection(level, neighborPos, dir.getOpposite());
        }
    }


    private void updateConnections(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        boolean north = hasConnection(level, pos, Direction.NORTH);
        boolean east  = hasConnection(level, pos, Direction.EAST);
        boolean south = hasConnection(level, pos, Direction.SOUTH);
        boolean west  = hasConnection(level, pos, Direction.WEST);
        boolean up    = hasConnection(level, pos, Direction.UP);
        boolean down  = hasConnection(level, pos, Direction.DOWN);

        BlockState newState = state
                .setValue(EnergyCableBlock.NORTH, north)
                .setValue(EnergyCableBlock.EAST, east)
                .setValue(EnergyCableBlock.SOUTH, south)
                .setValue(EnergyCableBlock.WEST, west)
                .setValue(EnergyCableBlock.UP, up)
                .setValue(EnergyCableBlock.DOWN, down);

        if (state != newState) {
            level.setBlock(pos, newState, 3);
        }
    }

    private boolean hasConnection(ServerLevel level, BlockPos pos, Direction dir) {
        BlockPos neighborPos = pos.relative(dir);
        BlockState neighborState = level.getBlockState(neighborPos);
        if (neighborState.is(CAFBlocks.ENERGY_CABLE.get())) {
            return true;
        }
        BlockEntity neighborEntity = level.getBlockEntity(neighborPos);
        if (neighborEntity != null) {
            if (neighborEntity.getCapability(EnergyCapability.ENERGY, dir.getOpposite()).isPresent()) {
                return true;
            }
        }
        return false;
    }

    private void updateNeighborConnection(ServerLevel level, BlockPos neighborPos, Direction neighborDir) {
        if (level.getBlockEntity(neighborPos) instanceof EnergyCableBlockEntity neighborTile) {
            neighborTile.updateConnections(level, neighborPos);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EnergyCableBlockEntity energyCableBlockEntity) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            if (level instanceof ServerLevel serverLevel) {
                energyCableBlockEntity.transferEnergyToNeighbor(serverLevel, neighborPos, dir);
            }
        }
    }

    private void transferEnergyToNeighbor(ServerLevel level, BlockPos neighborPos, Direction dir) {
        if (!energyStorage.canExtract()) {
            return;
        }

        BlockEntity neighborBe = level.getBlockEntity(neighborPos);
        if (neighborBe == null) {
            return;
        }

        LazyOptional<IEnergyStorage> opt = neighborBe.getCapability(EnergyCapability.ENERGY, dir.getOpposite());

        LazyOptional<IEnergyStorage> storage = this.getCapability(EnergyCapability.ENERGY, dir);

        if (!opt.isPresent()) {
            return;
        }

        var supplier = opt.resolve();
        if (supplier == null)
            return;

        var thisStorage = storage.resolve();
        if (thisStorage == null)
            return;

        IEnergyStorage neighborStorage = supplier.get();
        if (neighborStorage == null) {
            return;
        }

        IEnergyStorage tStorage = thisStorage.get();
        if (tStorage == null) {
            return;
        }

//        if (!neighborStorage.canReceive()) {
//            float maxTransfer = Math.min(tStorage.getMaxTransfer(), neighborStorage.getMaxTransfer());
//
//            CAFEnergyUnits simulated = neighborStorage.extractEnergy(maxTransfer, true);
//            if (simulated.isEmpty()) {
//                return;
//            }
//            float toAccept = simulated.getRawEnergy();
//            if (toAccept <= 0F) {
//                return;
//            }
//
//            CAFEnergyUnits actual = tStorage.receiveEnergy(toAccept, false);
//            if (actual.isEmpty()) {
//                return;
//            }
//
//            neighborStorage.extractEnergy(actual.getRawEnergy(), false);
//        }

        float maxTransfer = Math.min(tStorage.getMaxTransfer(), neighborStorage.getMaxTransfer());

        CAFEnergyUnits simulated = neighborStorage.receiveEnergy(maxTransfer, true);
        if (simulated == null || simulated.isEmpty())
            return;
        float toAccept = simulated.getRawEnergy();
        if (toAccept <= 0F) {
            return;
        }

        CAFEnergyUnits actual = tStorage.extractEnergy(toAccept, false);
        if (actual == null || actual.isEmpty()) {
            return;
        }

        neighborStorage.receiveEnergy(actual.getRawEnergy(), false);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedEnergyTooltip(tooltip, isPlayerSneaking, this.getCapability(EnergyCapability.ENERGY));
    }
}
