package net.egorplaytv.caf.energy;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.utility.VecHelper;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class KineticGeneratorBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {
    private static final float BASE_OUTPUT = 1.0F;
    private static final float EFFICIENCY = 0.75F;
    private static final float BUFFER_CAPACITY = 10000F;

    private static final int DEFAULT_VALUE = 100;
    private float dimperage = 100F;

    private EnergyStorage energyStorage;
    private LazyOptional<IEnergyStorage> energyCap;

    private float lastGenerated = 0F;

    private KineticGeneratorScrollValueBehaviour dimperageBehaviour;

    public KineticGeneratorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        rebuildStorage();
        energyCap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        dimperageBehaviour = new KineticGeneratorScrollValueBehaviour(TextUtils.getModTranslation("kinetics.generator.dimperage"),
                this, new GeneratorValueBox());
        dimperageBehaviour.between(0, 500 * 5);
        dimperageBehaviour.value = DEFAULT_VALUE;
        dimperageBehaviour.withCallback(i -> this.onDimperageChanged(dimperageBehaviour.getValue()));
        behaviours.add(dimperageBehaviour);
    }

    public float getDimperage() {
        return dimperage;
    }

    private void onDimperageChanged(int value) {
        this.dimperage = (float) value;
        rebuildStorage();
        setChanged();
    }

    private void rebuildStorage() {
        float capacity = Math.max(BUFFER_CAPACITY, 1F);
        energyStorage = new EnergyStorage(capacity, dimperage, capacity, capacity, 0);
        if (energyCap != null) {
            energyCap.invalidate();
            energyCap = LazyOptional.of(() -> energyStorage);
        }
    }

    @Override
    public float calculateStressApplied() {
        float speed = Math.abs(getSpeed());
        return speed > 0 ? 8.0F : 0F;
    }

    @Override
    public void tick() {
        super.tick();
        if (level == null || level.isClientSide)
            return;

        if (dimperageBehaviour != null) {
            float scrollValue = dimperageBehaviour.getValue();
            if (scrollValue != dimperage) {
                dimperage = scrollValue;
                rebuildStorage();
            }
        }

        float speed = Math.abs(getSpeed());

        float generated = BASE_OUTPUT * speed * EFFICIENCY;
        System.out.println(speed);
        if (speed != 0)
            System.out.println(generated);
        lastGenerated = generated;


        float newEnergy = Math.min(energyStorage.getEnergyStored().getRawEnergy() + generated, BUFFER_CAPACITY);

        energyStorage.setEnergyStored(new CAFEnergyUnits(newEnergy, getDimperage()));

        Direction facing = getBlockState().getValue(KineticGeneratorBlock.FACING);

        for (Direction dir : Direction.values()) {
            if (dir == facing || dir == Direction.DOWN) continue;

            IEnergyStorage neighbor = getNeighborCapability(dir);
            if (neighbor == null || !neighbor.canReceive()) continue;

            float available = energyStorage.getEnergyStored().getRawEnergy();
            if (available <= 0F) break;

            CAFEnergyUnits extracted = energyStorage.extractEnergy(available, energyStorage.getEnergyStored().getRawDimperage(), false);
            if (extracted.isEmpty()) continue;

            CAFEnergyUnits received = neighbor.receiveEnergy(extracted.getRawEnergy(), extracted.getRawDimperage(), false);
            float leftover = extracted.getRawEnergy() - received.getRawEnergy();
            if (leftover > 0F) {
                energyStorage.setEnergyStored(
                        new CAFEnergyUnits(energyStorage.getEnergyStored().getRawEnergy() + leftover, getDimperage())
                );
            }
        }

        setChanged();
    }

    @Nullable
    private IEnergyStorage getNeighborCapability(Direction dir) {
        if (level == null) return null;
        BlockEntity be = level.getBlockEntity(getBlockPos().relative(dir));
        if (be == null) return null;
        return be.getCapability(EnergyCapability.ENERGY, dir.getOpposite()).orElse(null);
    }

    @Override
    public boolean addToTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return super.addToTooltip(tooltip, isPlayerSneaking);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        return containedEnergyTooltip(tooltip, isPlayerSneaking, this.getDimperage());
    }

    private static final String KEY_STORAGE = "CableEnergyStorage";

    @Override
    public void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.put(KEY_STORAGE, energyStorage.serializeNBT());
        tag.putFloat("CAFEnergyDimperage", dimperage);
        tag.putFloat("CAFLastGenerated", lastGenerated);
    }

    @Override
    public void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        if (tag.contains(KEY_STORAGE)) {
            energyStorage.deserializeNBT(tag.getCompound(KEY_STORAGE));
        }
        dimperage = tag.getFloat("CAFEnergyDimperage");
        lastGenerated = tag.getFloat("CAFLastGenerated");
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
            Direction facing = getBlockState().getValue(KineticGeneratorBlock.FACING);
            if (side == null)
                return energyCap.cast();

            if (side == facing || side == facing.getOpposite() || side == Direction.DOWN) {
                return LazyOptional.empty();
            }

            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    static class GeneratorValueBox extends ValueBoxTransform.Sided {
        @Override
        protected Vec3 getSouthLocation() {
            return VecHelper.voxelSpace(8, 8, 15.6);
        }

        @Override
        protected boolean isSideActive(BlockState state, Direction direction) {
            Direction facing = state.getValue(KineticGeneratorBlock.FACING);
            if (direction == Direction.DOWN)
                return false;
            return direction == facing.getOpposite();
        }
    }
}
