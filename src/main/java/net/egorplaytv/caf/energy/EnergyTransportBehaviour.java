package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Iterate;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class EnergyTransportBehaviour extends BlockEntityBehaviour {

    public static final BehaviourType<EnergyTransportBehaviour> TYPE = new BehaviourType<>();


    public Map<Direction, WireConnection> interfaces;

    public EnergyTransportBehaviour(SmartBlockEntity be) {
        super(be);
    }

    public boolean canPullEnergyFrom(CAFEnergyUnits energy, BlockState state, Direction direction) {
        return true;
    }

    public abstract boolean canHaveFlowToward(BlockState state, Direction direction);

    @Override
    public void initialize() {
        super.initialize();
        createConnectionData();
    }

    @Override
    public void tick() {
        super.tick();
        Level world = getWorld();
        BlockPos pos = getPos();
        boolean onServer = !world.isClientSide || blockEntity.isVirtual();

        if (interfaces == null)
            return;
        Collection<WireConnection> connections = interfaces.values();

        // Do not provide a lone pipe connection with its own flow input
        WireConnection singleSource = null;

        if (onServer) {
            boolean sendUpdate = false;
            for (WireConnection connection : connections) {
                sendUpdate |= connection.flipFlowsIfVoltageReversed();
                connection.manageSource(world, pos);
            }
            if (sendUpdate)
                blockEntity.notifyUpdate();
        }

        if (onServer) {
            CAFEnergyUnits availableFlow = new CAFEnergyUnits();

            for (WireConnection connection : connections) {
                CAFEnergyUnits energyInFlow = connection.getProvidedEnergy();
                if (energyInFlow.isEmpty())
                    continue;
                if (availableFlow.isEmpty()) {
                    singleSource = connection;
                    availableFlow = energyInFlow;
                    continue;
                }
                singleSource = null;
                availableFlow = energyInFlow;
                break;
            }

            boolean sendUpdate = false;
            for (WireConnection connection : connections) {
                CAFEnergyUnits internalFluid = singleSource != connection ? availableFlow : new CAFEnergyUnits();
                Predicate<CAFEnergyUnits> extractionPredicate =
                        extracted -> canPullEnergyFrom(extracted, blockEntity.getBlockState(), connection.side);
                sendUpdate |= connection.manageFlows(world, pos, internalFluid, extractionPredicate);
            }

            if (sendUpdate)
                blockEntity.notifyUpdate();
        }

        for (WireConnection connection : connections)
            connection.tickFlowProgress(world, pos);
    }

    @Override
    public void read(CompoundTag nbt, boolean clientPacket) {
        super.read(nbt, clientPacket);
        if (interfaces == null)
            interfaces = new IdentityHashMap<>();
        for (Direction face : Iterate.directions)
            if (nbt.contains(face.getName()))
                interfaces.computeIfAbsent(face, d -> new WireConnection(d));

        // Invalid data (missing/outdated). Defer init to runtime
        if (interfaces.isEmpty()) {
            interfaces = null;
            return;
        }

        interfaces.values()
                .forEach(connection -> connection.deserializeNBT(nbt, blockEntity.getBlockPos(), clientPacket));
    }

    @Override
    public void write(CompoundTag nbt, boolean clientPacket) {
        super.write(nbt, clientPacket);
        if (clientPacket)
            createConnectionData();
        if (interfaces == null)
            return;

        interfaces.values()
                .forEach(connection -> connection.serializeNBT(nbt, clientPacket));
    }

    public CAFEnergyUnits getProvidedOutwardEnergy(Direction side) {
        createConnectionData();
        if (!interfaces.containsKey(side))
            return new CAFEnergyUnits();
        return interfaces.get(side)
                .provideOutboundFlow();
    }

    @Nullable
    public WireConnection getConnection(Direction side) {
        createConnectionData();
        return interfaces.get(side);
    }

    public boolean hasAnyVoltage() {
        createConnectionData();
        for (WireConnection pipeConnection : interfaces.values())
            if (pipeConnection.hasVoltage())
                return true;
        return false;
    }

    @Nullable
    public WireConnection.Flow getFlow(Direction side) {
        createConnectionData();
        if (!interfaces.containsKey(side))
            return null;
        return interfaces.get(side).flow.orElse(null);
    }

    public void addVoltage(Direction side, boolean inbound, float pressure) {
        createConnectionData();
        if (!interfaces.containsKey(side))
            return;
        interfaces.get(side)
                .addVoltage(inbound, pressure);
        blockEntity.sendData();
    }

    public void wipeVoltage() {
        if (interfaces != null)
            for (Direction d : Iterate.directions) {
                if (!canHaveFlowToward(blockEntity.getBlockState(), d))
                    interfaces.remove(d);
                else
                    interfaces.computeIfAbsent(d, WireConnection::new);
            }
        createConnectionData();
        interfaces.values()
                .forEach(WireConnection::wipeVoltage);
        blockEntity.sendData();
    }

    private void createConnectionData() {
        if (interfaces != null)
            return;
        interfaces = new IdentityHashMap<>();
        for (Direction d : Iterate.directions)
            if (canHaveFlowToward(blockEntity.getBlockState(), d))
                interfaces.put(d, new WireConnection(d));
    }

    @Override
    public BehaviourType<?> getType() {
        return TYPE;
    }
}
