package net.egorplaytv.caf.block.custom;

import net.egorplaytv.caf.block.praperties.BlockStateProperties;
import net.egorplaytv.caf.block.praperties.TerraceAttachType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TerraceBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED =
            net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<TerraceAttachType> ATTACHMENT = BlockStateProperties.TERRACE_ATTACHMENT;
    public TerraceBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTACHMENT, TerraceAttachType.SINGLE)
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public VoxelShape SIMPLE(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.25, 0.375, 0.625, 0.875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.75, 0, 0.8125, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.6875, 0.25, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }
    public VoxelShape LOW(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.25, 0.375, 0.625, 1, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.6875, 0.25, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.0625, 0.75), BooleanOp.OR);

        return shape;
    }
    public VoxelShape MIDDLE(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 1, 0.625), BooleanOp.OR);

        return shape;
    }
    public VoxelShape UP(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.5, 0.375, 0.625, 0.875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.75, 0, 0.8125, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 0.5, 0.625), BooleanOp.OR);

        return shape;
    }



    private VoxelShape getVoxelShape(BlockState pState) {
        TerraceAttachType terraceattachtype = pState.getValue(ATTACHMENT);
        if (terraceattachtype == TerraceAttachType.SINGLE){
            return SIMPLE();
        } else if (terraceattachtype == TerraceAttachType.LOW){
            return LOW();
        } else if (terraceattachtype == TerraceAttachType.MIDDLE){
            return MIDDLE();
        } else {
            return UP();
        }
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return state.setValue(WATERLOGGED, Boolean.valueOf(flag));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction direction, BlockState neighbor, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean positive = direction.getAxisDirection() == Direction.AxisDirection.POSITIVE;
        TerraceAttachType type = pState.getValue(ATTACHMENT);
        Direction.Axis direction$axis = direction.getAxis();
        Direction.Axis faceAxis = direction.getAxis();
        boolean connectionAxisY = direction$axis == Direction.Axis.Y;
        Direction.Axis connectionAxis_Y = direction$axis = Direction.Axis.Y;
        if (!(neighbor.getBlock() instanceof TerraceBlock) && connectionAxisY) {
            if (type == TerraceAttachType.SINGLE)
                return pState;
            if (type == TerraceAttachType.MIDDLE)
                return pState.setValue(ATTACHMENT, positive ? TerraceAttachType.UP : TerraceAttachType.LOW);
            if ((type == TerraceAttachType.LOW) == positive)
                return pState.setValue(ATTACHMENT, TerraceAttachType.SINGLE);
            if ((type == TerraceAttachType.UP) != positive)
                return pState.setValue(ATTACHMENT, TerraceAttachType.SINGLE);
            return pState;
        }

        if (type == TerraceAttachType.SINGLE && connectionAxisY) {
            type = positive ? TerraceAttachType.LOW : TerraceAttachType.UP;
        } else if (connectionAxis_Y != faceAxis) {
            return pState;
        }

        if ((type == TerraceAttachType.LOW) != positive && connectionAxisY)
            type = TerraceAttachType.MIDDLE;


        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return pState.setValue(ATTACHMENT, type);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(ATTACHMENT, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
