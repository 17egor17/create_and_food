package net.egorplaytv.caf.energy.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.egorplaytv.caf.block.custom.shingles.RidgeShingleBlock;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.energy.block.entity.EnergyBaseBlockEntity;
import net.egorplaytv.caf.energy.block.entity.WireBlockEntity;
import net.egorplaytv.caf.energy.energy_interface.IEnergyBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.*;

public class WireBlock extends EnergyKineticBlock implements SimpleWaterloggedBlock, IBE<WireBlockEntity>, IEnergyBlock {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (map) -> {
        map.put(Direction.NORTH, NORTH);
        map.put(Direction.EAST, EAST);
        map.put(Direction.SOUTH, SOUTH);
        map.put(Direction.WEST, WEST);
        map.put(Direction.UP, UP);
        map.put(Direction.DOWN, DOWN);
    }));
    public static Set<Direction> DIRECTION_BY_PROPERTY;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape DEFAULT_SHAPE = Block.box(5, 5, 5, 11, 11, 11);
    private static final Map<Direction, VoxelShape> BLOCK_SHAPE_ARM = new HashMap<>();

    public WireBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
                .setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
                .setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
                .setValue(DOWN, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
        DIRECTION_BY_PROPERTY = ImmutableSet.copyOf(Util.make(Sets.newHashSet(), (set) ->  {
            if (NORTH.equals(true)) set.add(Direction.NORTH);
            if (EAST.equals(true)) set.add(Direction.EAST);
            if (SOUTH.equals(true)) set.add(Direction.SOUTH);
            if (WEST.equals(true)) set.add(Direction.WEST);
            if (UP.equals(true)) set.add(Direction.UP);
            if (DOWN.equals(true)) set.add(Direction.DOWN);
        }));
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
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) == null) {
            return DEFAULT_SHAPE;
        }

        return createShapeForSides(DIRECTION_BY_PROPERTY);
    }

    private VoxelShape createShapeForSides(Set<Direction> sides) {
        VoxelShape shape = DEFAULT_SHAPE;

        for (Direction dir : sides) {
            shape = Shapes.join(shape, BLOCK_SHAPE_ARM.get(dir), BooleanOp.OR);
        }
        return shape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);
        DIRECTION_BY_PROPERTY = ImmutableSet.copyOf(Util.make(Sets.newHashSet(), (set) ->  {
            if (NORTH.equals(true)) set.add(Direction.NORTH);
            if (EAST.equals(true)) set.add(Direction.EAST);
            if (SOUTH.equals(true)) set.add(Direction.SOUTH);
            if (WEST.equals(true)) set.add(Direction.WEST);
            if (UP.equals(true)) set.add(Direction.UP);
            if (DOWN.equals(true)) set.add(Direction.DOWN);
        }));

        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction direction, BlockState neighbor, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return  direction.getAxis().isHorizontal() ? pState.setValue(PROPERTY_BY_DIRECTION.get(direction),
                Boolean.valueOf(isEnergyBlock(neighbor, neighbor.isFaceSturdy(pLevel, pFacingPos, direction.getOpposite()))))
                : super.updateShape(pState, direction, neighbor, pLevel, pCurrentPos, pFacingPos);
    }

    public static boolean isEnergyBlock(BlockState pState, boolean pSolidSide) {
        return ((pState.getBlock() instanceof EnergyKineticBlock) || (pState.getBlock() instanceof EnergyHorizontalKineticBlock))
                || pSolidSide;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    static {
        BLOCK_SHAPE_ARM.put(Direction.UP, Block.box(5, 11, 5, 11, 16, 11));
        BLOCK_SHAPE_ARM.put(Direction.DOWN, Block.box(5, 0, 5, 11, 5, 11));
        BLOCK_SHAPE_ARM.put(Direction.NORTH, Block.box(5, 5, 0, 11, 11, 5));
        BLOCK_SHAPE_ARM.put(Direction.SOUTH, Block.box(5, 5, 11, 11, 11, 16));
        BLOCK_SHAPE_ARM.put(Direction.WEST, Block.box(0, 5, 5, 5, 11, 11));
        BLOCK_SHAPE_ARM.put(Direction.EAST, Block.box(11, 5, 5, 16, 11, 11));
    }
}
