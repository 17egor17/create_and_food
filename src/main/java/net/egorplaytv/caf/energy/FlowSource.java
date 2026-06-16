package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.BlockFace;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;

import java.lang.ref.WeakReference;
import java.util.function.Predicate;

public abstract class FlowSource {

    private static final LazyOptional<IEnergyStorage> EMPTY = LazyOptional.empty();

    BlockFace location;

    public FlowSource(BlockFace location) {
        this.location = location;
    }

    public CAFEnergyUnits provideEnergy(Predicate<CAFEnergyUnits> extractionPredicate) {
        IEnergyStorage storage = provideHandler().orElse(null);
        if (storage == null)
            return new CAFEnergyUnits();
        CAFEnergyUnits immediateEnergy = storage.extractEnergy(1, true);
        if (extractionPredicate.test(immediateEnergy))
            return immediateEnergy;

        CAFEnergyUnits stored = storage.getEnergyStored();
        if (stored.isEmpty())
            return new CAFEnergyUnits();
        if (!extractionPredicate.test(stored))
            return new CAFEnergyUnits();
        CAFEnergyUnits toExtract = stored.copy();
        toExtract.setEnergy(1);
        return storage.extractEnergy(toExtract.getEnergy(), true);
    }

    public abstract boolean isEndpoint();

    public void manageSource(Level world) {}

    public void whileFlowPresent(Level world, boolean pulling) {}

    public LazyOptional<IEnergyStorage> provideHandler() {
        return EMPTY;
    }

    public static class EnergyHandler extends FlowSource {
        LazyOptional<IEnergyStorage> energyHandler;

        public EnergyHandler(BlockFace location) {
            super(location);
            energyHandler = EMPTY;
        }

        @Override
        public void manageSource(Level world) {
            if (energyHandler.isPresent() && world.getGameTime() % 20 != 0)
              return;
            BlockEntity blockEntity = world.getBlockEntity(location.getConnectedPos());
            if (blockEntity != null)
                energyHandler = blockEntity.getCapability(EnergyCapability.ENERGY, location.getOppositeFace());
        }

        @Override
        public LazyOptional<IEnergyStorage> provideHandler() {
            return energyHandler;
        }

        @Override
        public boolean isEndpoint() {
            return true;
        }
    }

    public static class OtherWire extends FlowSource {
        WeakReference<EnergyTransportBehaviour> cached;

        public OtherWire(BlockFace location) {
            super(location);
        }

        @Override
        public void manageSource(Level world) {
            if (cached != null && cached.get() != null && !cached.get().blockEntity.isRemoved())
                return;
            cached = null;
            EnergyTransportBehaviour energyTransportBehaviour =
                    BlockEntityBehaviour.get(world, location.getConnectedPos(), EnergyTransportBehaviour.TYPE);
            if (energyTransportBehaviour != null)
                cached = new WeakReference<>(energyTransportBehaviour);
        }

        @Override
        public CAFEnergyUnits provideEnergy(Predicate<CAFEnergyUnits> extractionPredicate) {
            if (cached == null || cached.get() == null)
                return new CAFEnergyUnits();
            EnergyTransportBehaviour behaviour = cached.get();
            CAFEnergyUnits providedOutwardEnergy = behaviour.getProvidedOutwardEnergy(location.getOppositeFace());
            return extractionPredicate.test(providedOutwardEnergy) ? providedOutwardEnergy : new CAFEnergyUnits();
        }

        @Override
        public boolean isEndpoint() {
            return false;
        }
    }

    public static class Blocked extends FlowSource {

        public Blocked(BlockFace location) {
            super(location);
        }

        @Override
        public boolean isEndpoint() {
            return false;
        }

    }
}
