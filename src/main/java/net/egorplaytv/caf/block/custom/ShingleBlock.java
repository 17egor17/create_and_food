package net.egorplaytv.caf.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.IntStream;

public class ShingleBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape OCTET_NPN = Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape OCTET_NPP = Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape OCTET_PPN = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape OCTET_PPP = Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(BOTTOM_AABB, OCTET_NPN, OCTET_PPN, OCTET_NPP, OCTET_PPP);
    private static final int[] SHAPE_BY_STATE = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};

    private static VoxelShape[] makeShapes(VoxelShape pSlabShape, VoxelShape pNwCorner, VoxelShape pNeCorner, VoxelShape pSwCorner, VoxelShape pSeCorner) {
        return IntStream.range(0, 16).mapToObj((p_56945_) -> {
            return makeStairShape(p_56945_, pSlabShape, pNwCorner, pNeCorner, pSwCorner, pSeCorner);
        }).toArray((p_56949_) -> {
            return new VoxelShape[p_56949_];
        });
    }

    private static VoxelShape makeStairShape(int pBitfield, VoxelShape pSlabShape, VoxelShape pNwCorner, VoxelShape pNeCorner, VoxelShape pSwCorner, VoxelShape pSeCorner) {
        VoxelShape voxelshape = pSlabShape;
        if ((pBitfield & 1) != 0) {
            voxelshape = Shapes.or(pSlabShape, pNwCorner);
        }

        if ((pBitfield & 2) != 0) {
            voxelshape = Shapes.or(voxelshape, pNeCorner);
        }

        if ((pBitfield & 4) != 0) {
            voxelshape = Shapes.or(voxelshape, pSwCorner);
        }

        if ((pBitfield & 8) != 0) {
            voxelshape = Shapes.or(voxelshape, pSeCorner);
        }

        return voxelshape;
    }

    public ShingleBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SHAPE, StairsShape.STRAIGHT).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return (BOTTOM_SHAPES)[SHAPE_BY_STATE[this.getShapeIndex(pState)]];
    }

    private int getShapeIndex(BlockState pState) {
        return pState.getValue(SHAPE).ordinal() * 4 + pState.getValue(FACING).get2DDataValue();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockPos blockpos = pContext.getClickedPos();
        FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);
        BlockState blockstate = this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        return blockstate.setValue(SHAPE, getStairsShape(blockstate, pContext.getLevel(), blockpos));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return pFacing.getAxis().isHorizontal() ? pState.setValue(SHAPE, getStairsShape(pState, pLevel, pCurrentPos)) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    private static StairsShape getStairsShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        Direction direction = pState.getValue(FACING);
        BlockState blockstate = pLevel.getBlockState(pPos.relative(direction));
        if (isShingles(blockstate)) {
            Direction direction1 = blockstate.getValue(FACING);
            if (direction1.getAxis() != pState.getValue(FACING).getAxis() && canTakeShape(pState, pLevel, pPos, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = pLevel.getBlockState(pPos.relative(direction.getOpposite()));
        if (isShingles(blockstate1)) {
            Direction direction2 = blockstate1.getValue(FACING);
            if (direction2.getAxis() != pState.getValue(FACING).getAxis() && canTakeShape(pState, pLevel, pPos, direction2)) {
                if (direction2 == direction.getCounterClockWise()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean canTakeShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pFace) {
        BlockState blockstate = pLevel.getBlockState(pPos.relative(pFace));
        return !isShingles(blockstate) || blockstate.getValue(FACING) != pState.getValue(FACING);
    }

    public static boolean isShingles(BlockState pState) {
        return pState.getBlock() instanceof ShingleBlock;
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        Direction direction = pState.getValue(FACING);
        StairsShape stairsshape = pState.getValue(SHAPE);
        switch(pMirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        default:
                            return pState.rotate(Rotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return pState.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT:
                            return pState.rotate(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(pState, pMirror);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, SHAPE, WATERLOGGED);
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
