package net.egorplaytv.caf.energy;

import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Pair;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.units.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnergyPropagator {
    public static void propagateChangedWire(LevelAccessor world, BlockPos pipePos, BlockState pipeState) {
        List<Pair<Integer, BlockPos>> frontier = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();

        frontier.add(Pair.of(0, pipePos));

        // Visit all connected pumps to update their network
        while (!frontier.isEmpty()) {
            Pair<Integer, BlockPos> pair = frontier.remove(0);
            BlockPos currentPos = pair.getSecond();
            if (visited.contains(currentPos))
                continue;
            visited.add(currentPos);
            BlockState currentState = currentPos.equals(pipePos) ? pipeState : world.getBlockState(currentPos);
            EnergyTransportBehaviour wire = getWire(world, currentPos);
            if (wire == null)
                continue;
            wire.wipeVoltage();

            for (Direction direction : getWireConnections(currentState, wire)) {
                BlockPos target = currentPos.relative(direction);
                if (world instanceof Level l && !l.isLoaded(target))
                    continue;

                BlockState targetState = world.getBlockState(target);
                if (visited.contains(target))
                    continue;
                EnergyTransportBehaviour targetWire = getWire(world, target);
                if (targetWire == null)
                    continue;
                Integer distance = pair.getFirst();
                if (!targetWire.hasAnyVoltage())
                    continue;
                if (targetWire.canHaveFlowToward(targetState, direction.getOpposite()))
                    frontier.add(Pair.of(distance + 1, target));
            }
        }
    }

    public static void resetAffectedEnergyNetworks(Level world, BlockPos start, Direction side) {
        List<BlockPos> frontier = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        frontier.add(start);

        while (!frontier.isEmpty()) {
            BlockPos pos = frontier.remove(0);
            if (visited.contains(pos))
                continue;
            visited.add(pos);
            EnergyTransportBehaviour wire = getWire(world, pos);
            if (wire == null)
                continue;

            for (Direction d : Iterate.directions) {
                if (pos.equals(start) && d != side)
                    continue;
                BlockPos target = pos.relative(d);
                if (visited.contains(target))
                    continue;

                WireConnection connection = wire.getConnection(d);
                if (connection == null)
                    continue;
                if (!connection.hasFlow())
                    continue;

                WireConnection.Flow flow = connection.flow.get();
                if (!flow.inbound)
                    continue;

                connection.resetNetwork();
                frontier.add(target);
            }
        }
    }

    public static Direction validateNeighbourChange(BlockState state, Level world, BlockPos pos, Block otherBlock,
                                                    BlockPos neighborPos, boolean isMoving) {
        if (world.isClientSide)
            return null;
        for (Direction d : Iterate.directions) {
            if (!pos.relative(d)
                    .equals(neighborPos))
                continue;
            return d;
        }
        return null;
    }

    public static EnergyTransportBehaviour getWire(BlockGetter reader, BlockPos pos) {
        return BlockEntityBehaviour.get(reader, pos, EnergyTransportBehaviour.TYPE);
    }

    public static List<Direction> getWireConnections(BlockState state, EnergyTransportBehaviour wire) {
        List<Direction> list = new ArrayList<>();
        for (Direction d : Iterate.directions)
            if (wire.canHaveFlowToward(state, d))
                list.add(d);
        return list;
    }

    public static boolean hasEnergyCapability(BlockGetter world, BlockPos pos, Direction side) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null)
            return false;
        LazyOptional<IEnergyStorage> capability =
                blockEntity.getCapability(EnergyCapability.ENERGY, side);
        return capability.isPresent();
    }
}
