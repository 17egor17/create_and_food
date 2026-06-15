package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.BlockFace;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Pair;
import net.egorplaytv.caf.units.energy.CAFEnergyUnits;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.Supplier;

public class EnergyNetwork {
    private static int CYCLES_PER_TICK = 16;

    Level world;
    BlockFace start;

    Supplier<LazyOptional<IEnergyStorage>> sourceSupplier;
    LazyOptional<IEnergyStorage> source;
    int transferSpeed;

    int pauseBeforePropagation;
    List<BlockFace> queued;
    Set<Pair<BlockFace, WireConnection>> frontier;
    Set<BlockPos> visited;
    CAFEnergyUnits energy;
    List<Pair<BlockFace, LazyOptional<IEnergyStorage>>> targets;
    Map<BlockPos, WeakReference<EnergyTransportBehaviour>> cache;

    public EnergyNetwork(Level world, BlockFace location, Supplier<LazyOptional<IEnergyStorage>> sourceSupplier) {
        this.world = world;
        this.start = location;
        this.sourceSupplier = sourceSupplier;
        this.source = LazyOptional.empty();
        this.energy = new CAFEnergyUnits();
        this.frontier = new HashSet<>();
        this.visited = new HashSet<>();
        this.targets = new ArrayList<>();
        this.cache = new HashMap<>();
        this.queued = new ArrayList<>();
        reset();
    }

    public void tick() {
        if (pauseBeforePropagation > 0) {
            pauseBeforePropagation--;
            return;
        }

        for (int cycle = 0; cycle < CYCLES_PER_TICK; cycle++) {
            boolean shouldContinue = false;
            for (Iterator<BlockFace> iterator = queued.iterator(); iterator.hasNext();) {
                BlockFace blockFace = iterator.next();
                if (!isPresent(blockFace))
                    continue;
                WireConnection wireConnection = get(blockFace);
                if (wireConnection != null) {
                    if (blockFace.equals(start))
                        transferSpeed = (int) Math.max(1, wireConnection.voltage.get(true) / 2f);
                    frontier.add(Pair.of(blockFace, wireConnection));
                }
                iterator.remove();
            }

//			drawDebugOutlines();

            for (Iterator<Pair<BlockFace, WireConnection>> iterator = frontier.iterator(); iterator.hasNext();) {
                Pair<BlockFace, WireConnection> pair = iterator.next();
                BlockFace blockFace = pair.getFirst();
                WireConnection wireConnection = pair.getSecond();

                if (!wireConnection.hasFlow())
                    continue;

                WireConnection.Flow flow = wireConnection.flow.get();
                if (!energy.isEmpty()) {
                    iterator.remove();
                    continue;
                }
                if (!flow.inbound) {
                    if (wireConnection.compareVoltage() >= 0)
                        iterator.remove();
                    continue;
                }
                if (!flow.complete)
                    continue;

                if (energy.isEmpty())
                    energy = flow.energy;

                boolean canRemove = true;
                for (Direction side : Iterate.directions) {
                    if (side == blockFace.getFace())
                        continue;
                    BlockFace adjacentLocation = new BlockFace(blockFace.getPos(), side);
                    WireConnection adjacent = get(adjacentLocation);
                    if (adjacent == null)
                        continue;
                    if (!adjacent.hasFlow()) {
                        // Branch could potentially still appear
                        if (adjacent.hasVoltage() && adjacent.voltage.getSecond() > 0)
                            canRemove = false;
                        continue;
                    }
                    WireConnection.Flow outFlow = adjacent.flow.get();
                    if (outFlow.inbound) {
                        if (adjacent.compareVoltage() > 0)
                            canRemove = false;
                        continue;
                    }
                    if (!outFlow.complete) {
                        canRemove = false;
                        continue;
                    }

                    // Give pipe end a chance to init connections
                    if (!adjacent.source.isPresent() && !adjacent.determineSource(world, blockFace.getPos())) {
                        canRemove = false;
                        continue;
                    }

                    if (adjacent.source.isPresent() && adjacent.source.get()
                            .isEndpoint()) {
                        targets.add(Pair.of(adjacentLocation, adjacent.source.get()
                                .provideHandler()));
                        continue;
                    }

                    if (visited.add(adjacentLocation.getConnectedPos())) {
                        queued.add(adjacentLocation.getOpposite());
                        shouldContinue = true;
                    }
                }
                if (canRemove)
                    iterator.remove();
            }
            if (!shouldContinue)
                break;
        }

        if (!source.isPresent())
            source = sourceSupplier.get();
        if (!source.isPresent())
            return;

        if (targets.isEmpty())
            return;
        for (Pair<BlockFace, LazyOptional<IEnergyStorage>> pair : targets) {
            if (pair.getSecond()
                    .isPresent() && world.getGameTime() % 40 != 0)
                continue;
            WireConnection pipeConnection = get(pair.getFirst());
            if (pipeConnection == null)
                continue;
            pipeConnection.source.ifPresent(fs -> {
                if (fs.isEndpoint())
                    pair.setSecond(fs.provideHandler());
            });
        }

        int flowSpeed = transferSpeed;
        Map<IEnergyStorage, CAFEnergyUnits> accumulatedFill = new IdentityHashMap<>();

        for (boolean simulate : Iterate.trueAndFalse) {
            boolean action = simulate ? true : false;

            IEnergyStorage handler = source.orElse(null);
            if (handler == null)
                return;

            CAFEnergyUnits transfer = new CAFEnergyUnits();
            CAFEnergyUnits contained = handler.getEnergyStored();
            if (contained.isEmpty())
                continue;
            CAFEnergyUnits toExtract = new CAFEnergyUnits(flowSpeed);
            transfer = handler.extractEnergy(toExtract.getEnergy(), action);


            if (transfer.isEmpty()) {
                CAFEnergyUnits genericExtract = handler.extractEnergy(flowSpeed, action);
                if (!genericExtract.isEmpty())
                    transfer = genericExtract;
            }

            if (transfer.isEmpty())
                return;
            if (simulate)
                flowSpeed = Math.round(transfer.getEnergy());

            List<Pair<BlockFace, LazyOptional<IEnergyStorage>>> availableOutputs = new ArrayList<>(targets);

            while (!availableOutputs.isEmpty() && transfer.getEnergy() > 0) {
                int dividedTransfer = Math.round(transfer.getEnergy() / availableOutputs.size());
                int remainder = Math.round(transfer.getEnergy() % availableOutputs.size());

                for (Iterator<Pair<BlockFace, LazyOptional<IEnergyStorage>>> iterator =
                     availableOutputs.iterator(); iterator.hasNext();) {
                    Pair<BlockFace, LazyOptional<IEnergyStorage>> pair = iterator.next();
                    int toTransfer = dividedTransfer;
                    if (remainder > 0) {
                        toTransfer++;
                        remainder--;
                    }

                    if (transfer.isEmpty())
                        break;
                    IEnergyStorage targetHandler = pair.getSecond()
                            .orElse(null);
                    if (targetHandler == null) {
                        iterator.remove();
                        continue;
                    }

                    int simulatedTransfer = toTransfer;
                    if (simulate)
                        simulatedTransfer += Math.round(accumulatedFill.getOrDefault(targetHandler, new CAFEnergyUnits()).getEnergy());

                    CAFEnergyUnits divided = transfer.copy();
                    divided.setEnergy(simulatedTransfer);
                    CAFEnergyUnits fill = targetHandler.receiveEnergy(divided.getEnergy(), action);

                    if (simulate) {
                        accumulatedFill.put(targetHandler, fill);
                        fill = new CAFEnergyUnits(fill.getEnergy() - (simulatedTransfer - toTransfer));
                    }

                    transfer.setEnergy(transfer.getEnergy() - fill.getEnergy());
                    if (fill.getEnergy() < simulatedTransfer)
                        iterator.remove();
                }

            }

            flowSpeed -= transfer.getEnergy();
            transfer = new  CAFEnergyUnits();
        }
    }

    public void reset() {
        frontier.clear();
        visited.clear();
        targets.clear();
        queued.clear();
        energy = new CAFEnergyUnits();
        queued.add(start);
        pauseBeforePropagation = 2;
    }

    @Nullable
    private WireConnection get(BlockFace location) {
        BlockPos pos = location.getPos();
        EnergyTransportBehaviour energyTransport = getEnergyTransfer(pos);
        if (energyTransport == null)
            return null;
        return energyTransport.getConnection(location.getFace());
    }

    private boolean isPresent(BlockFace location) {
        return world.isLoaded(location.getPos());
    }

    @Nullable
    private EnergyTransportBehaviour getEnergyTransfer(BlockPos pos) {
        WeakReference<EnergyTransportBehaviour> weakReference = cache.get(pos);
        EnergyTransportBehaviour behaviour = weakReference != null ? weakReference.get() : null;
        if (behaviour != null && behaviour.blockEntity.isRemoved())
            behaviour = null;
        if (behaviour == null) {
            behaviour = BlockEntityBehaviour.get(world, pos, EnergyTransportBehaviour.TYPE);
            if (behaviour != null)
                cache.put(pos, new WeakReference<>(behaviour));
        }
        return behaviour;
    }
}
