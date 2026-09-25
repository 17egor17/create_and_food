package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.utility.VecHelper;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.EnergyStorage;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CreativeGeneratorBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    private EnergyStorage energyStorage;
    private LazyOptional<IEnergyStorage> energyCap;

    private static final int DEFAULT_VALUE = 100;

    private float dimperage = 100F;
    private float energyPerTick = 1000F;

    private CreativeGeneratorScrollValueBehaviour dimperageBehaviour;

    public CreativeGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(CAFBlockEntities.CREATIVE_GENERATOR.get(), pos, state);
        rebuildStorage();
        energyCap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        dimperageBehaviour = new CreativeGeneratorScrollValueBehaviour(TextUtils.getModTranslation("kinetics.generator.dimperage"),
                this, new GeneratorValueBox());
        dimperageBehaviour.between(0, 500 * 1000);
        dimperageBehaviour.value = DEFAULT_VALUE;
        dimperageBehaviour.withCallback(i -> this.onDimperageChanged(dimperageBehaviour.getValue()));
        behaviours.add(dimperageBehaviour);
    }

    private void onDimperageChanged(int value) {
        this.dimperage = (float) value;
        rebuildStorage();
        setChanged();
    }

    public float getDimperage() {
        return dimperage;
    }

    public float getEnergyPerTick() {
        return energyPerTick;
    }

    public void setEnergyPerTick(float value) {
        this.energyPerTick = value;
        setChanged();
    }

    private void rebuildStorage() {
        float capacity = Math.max(energyPerTick, 1F);
        energyStorage = new EnergyStorage(capacity, dimperage, capacity, capacity, capacity);
        if (energyCap != null) {
            energyCap.invalidate();
            energyCap = LazyOptional.of(() -> energyStorage);
        }
    }

    public static void tick(CreativeGeneratorBlockEntity be) {
        if (be.level == null || be.level.isClientSide) return;

        if (be.dimperageBehaviour != null) {
            float scrollValue = be.dimperageBehaviour.getValue();
            if (scrollValue != be.dimperage) {
                be.dimperage = scrollValue;
                be.rebuildStorage();
            }
        }

        be.energyStorage.setEnergyStored(new CAFEnergyUnits(be.energyPerTick, be.dimperage));

        for (Direction dir : Direction.values()) {
            if (dir == Direction.DOWN || dir == be.getBlockState().getValue(CreativeGeneratorBlock.FACING).getOpposite())
                continue;

            IEnergyStorage neighbor = be.getNeighborCapability(dir);
            if (neighbor == null || !neighbor.canReceive()) continue;

            CAFEnergyUnits toSend = new CAFEnergyUnits(be.energyPerTick, be.dimperage);
            neighbor.receiveEnergy(toSend.getRawEnergy(), be.dimperage, false);

        }

        be.setChanged();
    }

    @Nullable
    private IEnergyStorage getNeighborCapability(Direction dir) {
        if (level == null) return null;
        BlockEntity be = level.getBlockEntity(getBlockPos().relative(dir));
        if (be == null) return null;
        return be.getCapability(EnergyCapability.ENERGY, dir.getOpposite()).orElse(null);
    }

    @Override
    public void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putFloat("CAFEnergyDimperage", dimperage);
        tag.putFloat("CAFEnergyPerTick", energyPerTick);
    }

    @Override
    public void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        dimperage = tag.getFloat("CAFEnergyDimperage");
        energyPerTick = tag.getFloat("CAFEnergyPerTick");
        rebuildStorage();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCap.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EnergyCapability.ENERGY) {
            Direction facing = this.getBlockState().getValue(CreativeGeneratorBlock.FACING);
            if (side == null)
                return energyCap.cast();

            if (side == Direction.DOWN || side == facing.getOpposite())
                return LazyOptional.empty();

            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedEnergyTooltip(tooltip, isPlayerSneaking, getDimperage());
    }

    static class GeneratorValueBox extends ValueBoxTransform.Sided {

        @Override
        protected Vec3 getSouthLocation() {
            return VecHelper.voxelSpace(8, 8, 15.6);
        }

        @Override
        protected boolean isSideActive(BlockState state, Direction direction) {
            Direction facing = state.getValue(CreativeGeneratorBlock.FACING);
            if (direction == Direction.DOWN)
                return false;
            return direction == facing.getOpposite();
        }
    }
}
