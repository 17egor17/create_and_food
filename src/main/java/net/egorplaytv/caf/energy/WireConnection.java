package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.BlockFace;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkStatus;

import java.util.Optional;
import java.util.function.Predicate;

public class WireConnection {

    public Direction side;

    //Layer I
    Couple<Float> voltage; //[inbound, outward]
    Optional<FlowSource> source;
    Optional<FlowSource> previousSource;

    // Layer II
    public Optional<Flow> flow;

    // Layer III
    Optional<EnergyNetwork> network; // not serialized

    public WireConnection(Direction side) {
        this.side = side;
        voltage = Couple.create(() -> 0f);
        flow = Optional.empty();
        previousSource = Optional.empty();
        source = Optional.empty();
        network = Optional.empty();
    }

    public CAFEnergyUnits getProvidedEnergy() {
        CAFEnergyUnits empty = new CAFEnergyUnits();
        if (!hasFlow())
            return empty;
        Flow flow = this.flow.get();
        if (!flow.inbound)
            return empty;
        if (!flow.complete)
            return empty;
        return flow.energy;
    }

    public boolean flipFlowsIfVoltageReversed() {
        if (!hasFlow())
            return false;
        boolean singlePressure = compareVoltage() != 0 && (getInboundVoltage() == 0 || getOutwardVoltage() == 0);
        Flow flow = this.flow.get();
        if (!singlePressure || compareVoltage() < 0 == flow.inbound)
            return false;
        flow.inbound = !flow.inbound;
        if (!flow.complete)
            this.flow = Optional.empty();
        return true;
    }

    public void manageSource(Level world, BlockPos pos) {
        if (!source.isPresent() && !determineSource(world, pos))
            return;
        FlowSource flowSource = source.get();
        flowSource.manageSource(world);
    }

    public boolean manageFlows(Level world, BlockPos pos, CAFEnergyUnits internalEnergy,
                               Predicate<CAFEnergyUnits> extractionPredicate) {

        // Only keep network if still valid
        Optional<EnergyNetwork> retainedNetwork = network;
        network = Optional.empty();

        // chunk border
        if (!source.isPresent() && !determineSource(world, pos))
            return false;
        FlowSource flowSource = source.get();

        if (!hasFlow()) {
            if (!hasVoltage())
                return false;

            // Try starting a new flow
            boolean prioritizeInbound = compareVoltage() < 0;
            for (boolean trueFalse : Iterate.trueAndFalse) {
                boolean inbound = prioritizeInbound == trueFalse;
                if (voltage.get(inbound) == 0)
                    continue;
                if (tryStartingNewFlow(inbound, inbound ? flowSource.provideEnergy(extractionPredicate) : internalEnergy))
                    return true;
            }
            return false;
        }

        // Manage existing flow
        Flow flow = this.flow.get();
        CAFEnergyUnits provided = flow.inbound ? flowSource.provideEnergy(extractionPredicate) : internalEnergy;
        if (!hasVoltage() || provided.isEmpty()) {
            this.flow = Optional.empty();
            return true;
        }

        // Overwrite existing flow
        if (flow.inbound != compareVoltage() < 0) {
            boolean inbound = !flow.inbound;
            if (inbound && !provided.isEmpty() || !inbound && !internalEnergy.isEmpty()) {
                EnergyPropagator.resetAffectedEnergyNetworks(world, pos, side);
                tryStartingNewFlow(inbound, inbound ? flowSource.provideEnergy(extractionPredicate) : internalEnergy);
                return true;
            }
        }

        flowSource.whileFlowPresent(world, flow.inbound);

        if (!flowSource.isEndpoint())
            return false;
        if (!flow.inbound)
            return false;

        // Layer III
        network = retainedNetwork;
        if (!hasNetwork())
            network = Optional.of(new EnergyNetwork(world, new BlockFace(pos, side), flowSource::provideHandler));
        network.get()
                .tick();

        return false;
    }

    private boolean tryStartingNewFlow(boolean inbound, CAFEnergyUnits providedEnergy) {
        if (providedEnergy.isEmpty())
            return false;
        Flow flow = new Flow(inbound, providedEnergy);
        this.flow = Optional.of(flow);
        return true;
    }

    public boolean determineSource(Level world, BlockPos pos) {
        BlockPos relative = pos.relative(side);
        // cannot use world.isLoaded because it always returns true on client
        if (world.getChunk(relative.getX() >> 4, relative.getZ() >> 4, ChunkStatus.FULL, false) == null)
            return false;

        BlockFace location = new BlockFace(pos, side);

        if (EnergyPropagator.hasEnergyCapability(world, location.getConnectedPos(), side.getOpposite())) {
            source = Optional.of(new FlowSource.EnergyHandler(location));
            return true;
        }

        EnergyTransportBehaviour behaviour =
                BlockEntityBehaviour.get(world, relative, EnergyTransportBehaviour.TYPE);
        source = Optional.of(behaviour == null ? new FlowSource.Blocked(location) : new FlowSource.OtherWire(location));
        return true;
    }

    public void tickFlowProgress(Level world, BlockPos pos) {
        if (!hasFlow())
            return;
        Flow flow = this.flow.get();
        if (flow.energy.isEmpty())
            return;

        if (world.isClientSide) {
            if (!source.isPresent())
                determineSource(world, pos);
        }

        float flowSpeed = 1 / 32f + Mth.clamp(voltage.get(flow.inbound) / 128f, 0, 1) * 31 / 32f;
        flow.progress.setValue(Math.min(flow.progress.getValue() + flowSpeed, 1));
        if (flow.progress.getValue() >= 1)
            flow.complete = true;
    }

    public void serializeNBT(CompoundTag tag, boolean clientPacket) {
        CompoundTag connectionData = new CompoundTag();
        tag.put(side.getName(), connectionData);

        if (hasVoltage()) {
            ListTag pressureData = new ListTag();
            pressureData.add(FloatTag.valueOf(getInboundVoltage()));
            pressureData.add(FloatTag.valueOf(getOutwardVoltage()));
            connectionData.put("Voltage", pressureData);
        }

        if (hasFlow()) {
            CompoundTag flowData = new CompoundTag();
            Flow flow = this.flow.get();
            flow.energy.writeToNBT(flowData);
            flowData.putBoolean("In", flow.inbound);
            if (!flow.complete)
                flowData.put("Progress", flow.progress.writeNBT());
            connectionData.put("Flow", flowData);
        }

    }

    public void deserializeNBT(CompoundTag tag, BlockPos blockEntityPos, boolean clientPacket) {
        CompoundTag connectionData = tag.getCompound(side.getName());

        if (connectionData.contains("Voltage")) {
            ListTag voltageData = connectionData.getList("Voltage", Tag.TAG_FLOAT);
            voltage = Couple.create(voltageData.getFloat(0), voltageData.getFloat(1));
        } else
            voltage.replace(f -> 0f);

        source = Optional.empty();

        if (connectionData.contains("Flow")) {
            CompoundTag flowData = connectionData.getCompound("Flow");
            CAFEnergyUnits energy = CAFEnergyUnits.loadCAFEnergyUnitsFromNBT(flowData);
            boolean inbound = flowData.getBoolean("In");
            if (!flow.isPresent()) {
                flow = Optional.of(new Flow(inbound, energy));
            }
            Flow flow = this.flow.get();

            flow.energy = energy;
            flow.inbound = inbound;
            flow.complete = !flowData.contains("Progress");

            if (!flow.complete)
                flow.progress.readNBT(flowData.getCompound("Progress"), clientPacket);
            else {
                if (flow.progress.getValue() == 0)
                    flow.progress.startWithValue(1);
                flow.progress.setValue(1);
            }

        } else
            flow = Optional.empty();

    }

    /**
     * @return zero if outward == inbound <br>
     *         positive if outward {@literal >} inbound <br>
     *         negative if outward {@literal <} inbound
     */
    public float compareVoltage() {
        return getOutwardVoltage() - getInboundVoltage();
    }

    public void wipeVoltage() {
        this.voltage.replace(f -> 0f);
        if (this.source.isPresent())
            this.previousSource = this.source;
        this.source = Optional.empty();
        resetNetwork();
    }

    public CAFEnergyUnits provideOutboundFlow() {
        if (!hasFlow())
            return new CAFEnergyUnits();
        Flow flow = this.flow.get();
        if (!flow.complete || flow.inbound)
            return new CAFEnergyUnits();
        return flow.energy;
    }

    public void addVoltage(boolean inbound, float voltage) {
        this.voltage = this.voltage.mapWithContext((f, in) -> in == inbound ? f + voltage : f);
    }

    public Couple<Float> getVoltage() {
        return voltage;
    }

    public boolean hasVoltage() {
        return getInboundVoltage() != 0 || getOutwardVoltage() != 0;
    }

    private float getOutwardVoltage() {
        return voltage.getSecond();
    }

    private float getInboundVoltage() {
        return voltage.getFirst();
    }


    public boolean hasFlow() {
        return flow.isPresent();
    }

    public boolean hasNetwork() {
        return network.isPresent();
    }

    public void resetNetwork() {
        network.ifPresent(EnergyNetwork::reset);
    }

    public class Flow {
        public boolean complete;
        public boolean inbound;
        public LerpedFloat progress;
        public CAFEnergyUnits energy;

        public Flow(boolean inbound, CAFEnergyUnits energy) {
            this.inbound = inbound;
            this.energy = energy;
            this.progress = LerpedFloat.linear()
                    .startWithValue(0);
            this.complete = false;
        }
    }
}
