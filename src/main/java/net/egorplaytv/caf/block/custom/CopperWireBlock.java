package net.egorplaytv.caf.block.custom;

import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Iterate;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.entity.custom.WireBlockEntity;
import net.egorplaytv.caf.block.pattern.WireBlock;
import net.egorplaytv.caf.energy.EnergyPropagator;
import net.egorplaytv.caf.energy.EnergyTransportBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

public class CopperWireBlock extends WireBlock implements SimpleWaterloggedBlock, IBE<WireBlockEntity>,
        EncasableBlock {

    private static final VoxelShape OCCLUSION_BOX = Block.box(5, 5, 5, 11, 11, 11);

    public CopperWireBlock(Properties pProperties) {
        super(5 / 16F, pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult ray) {
        ItemStack heldItem = player.getItemInHand(hand);
        InteractionResult result = tryEncase(state, world, pos, heldItem, player, hand, ray);
        if (result.consumesAction())
            return result;

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        boolean blockTypeChanged = state.getBlock() != newState.getBlock();
        if (blockTypeChanged && !world.isClientSide)
            EnergyPropagator.propagateChangedWire(world, pos, state);
        if (state.hasBlockEntity() && (blockTypeChanged || !newState.hasBlockEntity()))
            world.removeBlockEntity(pos);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (world.isClientSide)
            return;
        if (state != oldState)
            world.scheduleTick(pos, this, 1, TickPriority.HIGH);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block otherBlock, BlockPos neighborPos,
                                boolean isMoving) {
        DebugPackets.sendNeighborsUpdatePacket(world, pos);
        Direction d = EnergyPropagator.validateNeighbourChange(state, world, pos, otherBlock, neighborPos, isMoving);
        if (d == null)
            return;
        world.scheduleTick(pos, this, 1, TickPriority.HIGH);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random r) {
        EnergyPropagator.propagateChangedWire(world, pos, state);
    }

    public static boolean isWire(BlockState state) {
        return state.getBlock() instanceof CopperWireBlock;
    }

    public static boolean canConnectTo(BlockAndTintGetter world, BlockPos neighbourPos, BlockState neighbour,
                                       Direction direction) {
        if (EnergyPropagator.hasEnergyCapability(world, neighbourPos, direction.getOpposite()))
            return true;
        EnergyTransportBehaviour transport = BlockEntityBehaviour.get(world, neighbourPos, EnergyTransportBehaviour.TYPE);
        if (transport == null)
            return false;
        return transport.canHaveFlowToward(neighbour, direction.getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, BlockStateProperties.WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState FluidState = context.getLevel()
                .getFluidState(context.getClickedPos());
        return updateBlockState(defaultBlockState(), context.getNearestLookingDirection(), null, context.getLevel(),
                context.getClickedPos()).setValue(BlockStateProperties.WATERLOGGED,
                Boolean.valueOf(FluidState.getType() == Fluids.WATER));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor world,
                                  BlockPos pos, BlockPos neighbourPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        if (neighbourState.hasProperty(BlockStateProperties.WATERLOGGED))
            world.scheduleTick(pos, this, 1, TickPriority.HIGH);
        return updateBlockState(state, direction, direction.getOpposite(), world, pos);
    }

    public BlockState updateBlockState(BlockState state, Direction preferredDirection, @Nullable Direction ignore,
                                       BlockAndTintGetter world, BlockPos pos) {

        BlockState prevState = state;
        int prevStateSides = (int) Arrays.stream(Iterate.directions)
                .map(PROPERTY_BY_DIRECTION::get)
                .filter(prevState::getValue)
                .count();

        // Update sides that are not ignored
        for (Direction d : Iterate.directions)
            if (d != ignore) {
                boolean shouldConnect = canConnectTo(world, pos.relative(d), world.getBlockState(pos.relative(d)), d);
                state = state.setValue(PROPERTY_BY_DIRECTION.get(d), shouldConnect);
            }

        // See if it has enough connections
        Direction connectedDirection = null;
        for (Direction d : Iterate.directions) {
            if (connectedDirection != null)
                return state;
            connectedDirection = d;

        }

        // Add opposite end if only one connection
        if (connectedDirection != null)
            return state.setValue(PROPERTY_BY_DIRECTION.get(connectedDirection.getOpposite()), true);

        // If we can't connect to anything and weren't connected before, do nothing
        if (prevStateSides == 2)
            return prevState;

        // Use preferred
        return state.setValue(PROPERTY_BY_DIRECTION.get(preferredDirection), true)
                .setValue(PROPERTY_BY_DIRECTION.get(preferredDirection.getOpposite()), true);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false)
                : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public Class<WireBlockEntity> getBlockEntityClass() {
        return WireBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends WireBlockEntity> getBlockEntityType() {
        return CAFBlockEntities.WIRE_BLOCK.get();
    }

    @Override
    public boolean supportsExternalFaceHiding(BlockState state) {
        return false;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return OCCLUSION_BOX;
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        BlockState rotated = pState;
        for (Direction direction : Iterate.horizontalDirections)
            rotated = rotated.setValue(PROPERTY_BY_DIRECTION.get(pRotation.rotate(direction)),
                    pState.getValue(PROPERTY_BY_DIRECTION.get(direction)));
        return rotated;
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        BlockState mirrored = pState;
        for (Direction direction : Iterate.horizontalDirections)
            mirrored = mirrored.setValue(PROPERTY_BY_DIRECTION.get(pMirror.mirror(direction)),
                    pState.getValue(PROPERTY_BY_DIRECTION.get(direction)));
        return mirrored;
    }
}
