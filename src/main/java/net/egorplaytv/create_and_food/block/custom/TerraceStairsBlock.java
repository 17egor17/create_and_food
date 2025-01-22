package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.praperties.TerraceAttachType;
import net.egorplaytv.create_and_food.block.praperties.TerraceEncasedType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerraceStairsBlock extends TerraceBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ENCASED = net.egorplaytv.create_and_food.block.praperties.BlockStateProperties.TERRACE_ENCASED;
    public TerraceStairsBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                .setValue(ATTACHMENT, TerraceAttachType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    private VoxelShape SAMPLE_NORTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.25, 0.5, 0.625, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.5, 0.6875, 0.25, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.5, 0.75, 0.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0.5, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, -0.25, 0, 0.8125, 0, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, -0.25, 0, 0.4375, 0, 0.125), BooleanOp.OR);

        return shape;
    }

    private VoxelShape SAMPLE_SOUTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0.25, 0.375, 0.625, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.6875, 0.25, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.0625, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.5, 1, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0, 1, 1, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.186875, -0.250625, 0.874375, 0.436875, -0.0006249999999999867, 0.999375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.561875, -0.250625, 0.874375, 0.811875, -0.0006249999999999867, 0.999375), BooleanOp.OR);

        return shape;
    }

    private VoxelShape SAMPLE_WEST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.5, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.5, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.25, 0.375, 0.625, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.0625, 0.3125, 0.6875, 0.25, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.25, 0.75, 0.0625, 0.75), BooleanOp.OR);

        return shape;
    }

    private VoxelShape SAMPLE_EAST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.5, 0, 0, 1, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0, 0.5, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.25, 0.375, 0.5, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.5, 0.25, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.5, 0.0625, 0.75), BooleanOp.OR);

        return shape;
    }

    private VoxelShape UP_NORTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.5, 0.625, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0.5, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, -0.25, 0, 0.8125, 0, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, -0.25, 0, 0.4375, 0, 0.125), BooleanOp.OR);

        return shape;
    }

    private VoxelShape UP_SOUTH() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.5, 1, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0, 1, 1, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.561875, -0.250625, 0.874375, 0.811875, -0.0006249999999999867, 0.999375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.186875, -0.250625, 0.874375, 0.436875, -0.0006249999999999867, 0.999375), BooleanOp.OR);

        return shape;
    }

    private VoxelShape UP_WEST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.375, 0.625, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.5, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.5, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }

    private VoxelShape UP_EAST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.5, 0.5, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0, 0, 1, 0.5, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0, 0.5, 1, 1), BooleanOp.OR);

        return shape;
    }

    private VoxelShape getVoxelShape(BlockState pState) {
        Direction directionproperty = pState.getValue(FACING);
        TerraceAttachType terraceattachtype = pState.getValue(ATTACHMENT);
        if (terraceattachtype == TerraceAttachType.SINGLE && directionproperty == Direction.NORTH){
            return SAMPLE_NORTH();
        } else if (terraceattachtype == TerraceAttachType.SINGLE && directionproperty == Direction.SOUTH){
            return SAMPLE_SOUTH();
        } else if (terraceattachtype == TerraceAttachType.SINGLE && directionproperty == Direction.WEST){
            return SAMPLE_WEST();
        } else if (terraceattachtype == TerraceAttachType.SINGLE && directionproperty == Direction.EAST){
            return SAMPLE_EAST();
        } else if (terraceattachtype == TerraceAttachType.LOW){
            return LOW();
        } else if (terraceattachtype == TerraceAttachType.MIDDLE){
            return MIDDLE();
        } else if (terraceattachtype == TerraceAttachType.UP && directionproperty == Direction.SOUTH){
            return UP_SOUTH();
        } else if (terraceattachtype == TerraceAttachType.UP && directionproperty == Direction.WEST) {
            return UP_WEST();
        } else if (terraceattachtype == TerraceAttachType.UP && directionproperty == Direction.EAST) {
            return UP_EAST();
        } else {
            return UP_NORTH();
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }

    /* FACING */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, Boolean.valueOf(flag)).setValue(ENCASED, Boolean.valueOf(false));
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, ATTACHMENT, ENCASED, WATERLOGGED);
    }
}
