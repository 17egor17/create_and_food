package net.egorplaytv.caf.energy;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.units.energy.energy_interface.EnergyCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class CableBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {
    public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
    public static final EnumProperty<ConnectionType> EAST  = EnumProperty.create("east",  ConnectionType.class);
    public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
    public static final EnumProperty<ConnectionType> WEST  = EnumProperty.create("west",  ConnectionType.class);
    public static final EnumProperty<ConnectionType> UP    = EnumProperty.create("up",    ConnectionType.class);
    public static final EnumProperty<ConnectionType> DOWN  = EnumProperty.create("down",  ConnectionType.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final Map<Direction, EnumProperty<ConnectionType>> DIRECTION_PROPS = new EnumMap<>(Direction.class);

    static {
        DIRECTION_PROPS.put(Direction.NORTH, NORTH);
        DIRECTION_PROPS.put(Direction.SOUTH, SOUTH);
        DIRECTION_PROPS.put(Direction.EAST,  EAST);
        DIRECTION_PROPS.put(Direction.WEST,  WEST);
        DIRECTION_PROPS.put(Direction.UP,    UP);
        DIRECTION_PROPS.put(Direction.DOWN,  DOWN);
    }

    private static final VoxelShape SHAPE = Shapes.or(box(5,5,5,11,11,11));
    private static final VoxelShape SHAPE_DOWN = Block.box(5, 0, 5, 11, 5, 11);
    private static final VoxelShape SHAPE_UP = Block.box(5, 11, 5, 11, 16, 11);
    private static final VoxelShape SHAPE_NORTH = Block.box(5, 5, 0, 11, 11, 5);
    private static final VoxelShape SHAPE_SOUTH = Block.box(5, 5, 11, 11, 11, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(0, 5, 5, 5, 11, 11);
    private static final VoxelShape SHAPE_EAST = Block.box(11, 5, 5, 16, 11, 11);
    private static final VoxelShape SHAPE_DOWN_BLOCK = Shapes.or(box(5, 0, 5, 11, 5, 11),
            box(4, 0, 4, 12, 1, 12));
    private static final VoxelShape SHAPE_UP_BLOCK = Shapes.or(Block.box(5, 11, 5, 11, 16, 11),
            box(4, 15, 4, 12, 16, 12));
    private static final VoxelShape SHAPE_NORTH_BLOCK = Shapes.or(Block.box(5, 5, 0, 11, 11, 5),
            box(4, 4, 0, 12, 12, 1));
    private static final VoxelShape SHAPE_SOUTH_BLOCK = Shapes.or(Block.box(5, 5, 11, 11, 11, 16),
            box(4, 4, 15, 12, 12, 16));
    private static final VoxelShape SHAPE_WEST_BLOCK = Shapes.or(Block.box(0, 5, 5, 5, 11, 11),
            box(0, 4, 4, 1, 12, 12));
    private static final VoxelShape SHAPE_EAST_BLOCK = Shapes.or(Block.box(11, 5, 5, 16, 11, 11),
            box(15, 4, 4, 16, 12, 12));

    private final float transferRate;

    public CableBlock(float transferRate, Properties properties) {
        super(properties);
        this.transferRate = transferRate;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, ConnectionType.NONE)
                .setValue(EAST,  ConnectionType.NONE)
                .setValue(SOUTH, ConnectionType.NONE)
                .setValue(WEST,  ConnectionType.NONE)
                .setValue(UP,    ConnectionType.NONE)
                .setValue(DOWN,  ConnectionType.NONE)
                .setValue(WATERLOGGED, false));
    }

    public float getTransferRate() {
        return transferRate;
    }

    // ---------- State ----------

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    // ---------- Placement ----------

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        FluidState fluid = level.getFluidState(pos);
        BlockState state = defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        for (Direction dir : Direction.values()) {
            state = state.setValue(DIRECTION_PROPS.get(dir), getConnectionType(level, pos, dir));
        }
        return state;
    }

    // ---------- Neighbor updates ----------

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moved) {
        if (!state.is(oldState.getBlock())) {
            updateConnections(level, pos);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return state.setValue(DIRECTION_PROPS.get(dir),
                getConnectionType((Level) level, pos, dir));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
                                 BlockPos neighborPos, boolean isMoving) {
        updateConnections(level, pos);
    }

    /**
     * Recalculates cable connections at the specified position and, if they have changed,
     * updates the BlockState. It also forces neighboring cables to recalculate their connections.
     */
    public void updateConnections(Level level, BlockPos pos) {
        if (level.isClientSide()) return;
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof CableBlock)) return;

        boolean changed = false;
        BlockState newState = state;
        for (Direction dir : Direction.values()) {
            ConnectionType oldConn = state.getValue(DIRECTION_PROPS.get(dir));
            ConnectionType newConn = getConnectionType(level, pos, dir);
            if (oldConn != newConn) {
                newState = newState.setValue(DIRECTION_PROPS.get(dir), newConn);
                changed = true;
            }
        }

        if (changed) {
            level.setBlock(pos, newState, Block.UPDATE_CLIENTS | Block.UPDATE_NEIGHBORS);
        }

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (neighborState.getBlock() instanceof CableBlock) {
                BlockState updated = neighborState;
                boolean neighborChanged = false;
                for (Direction d : Direction.values()) {
                    ConnectionType oldConn = neighborState.getValue(DIRECTION_PROPS.get(d));
                    ConnectionType newConn = getConnectionType(level, neighborPos, d);
                    if (oldConn != newConn) {
                        updated = updated.setValue(DIRECTION_PROPS.get(d), newConn);
                        neighborChanged = true;
                    }
                }
                if (neighborChanged) {
                    level.setBlock(neighborPos, updated, Block.UPDATE_CLIENTS | Block.UPDATE_NEIGHBORS);
                }
            }
        }
    }

    /**
     * Determines the connection type in the specified direction:
     * CABLE — The adjacent block is also a cable;
     * BLOCK — The adjacent block has IEnergyStorage;
     * NONE — Nothing.
     */
    public ConnectionType getConnectionType(Level level, BlockPos pos, Direction dir) {
        BlockPos neighborPos = pos.relative(dir);
        if (!level.isLoaded(neighborPos)) return ConnectionType.NONE;
        BlockState neighborState = level.getBlockState(neighborPos);
        if (neighborState.getBlock() instanceof CableBlock) {
            return ConnectionType.CABLE;
        }
        BlockEntity be = level.getBlockEntity(neighborPos);
        if (be != null) {
            if (be.getCapability(EnergyCapability.ENERGY, dir.getOpposite()).isPresent()) {
                return ConnectionType.BLOCK;
            }
        }
        return ConnectionType.NONE;
    }

    // ---------- Waterlogged ----------

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState() : super.getFluidState(state);
    }

    // ---------- Shapes ----------

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        VoxelShape shape = SHAPE;

        if (state.getValue(NORTH) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_NORTH);
        if (state.getValue(SOUTH) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_SOUTH);
        if (state.getValue(EAST) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_EAST);
        if (state.getValue(WEST) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_WEST);
        if (state.getValue(UP) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_UP);
        if (state.getValue(DOWN) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_DOWN);

        if (state.getValue(NORTH) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_NORTH_BLOCK);
        if (state.getValue(SOUTH) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_SOUTH_BLOCK);
        if (state.getValue(EAST) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_EAST_BLOCK);
        if (state.getValue(WEST) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_WEST_BLOCK);
        if (state.getValue(UP) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_UP_BLOCK);
        if (state.getValue(DOWN) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_DOWN_BLOCK);

        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        VoxelShape shape = SHAPE;

        if (state.getValue(NORTH) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_NORTH);
        if (state.getValue(SOUTH) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_SOUTH);
        if (state.getValue(EAST) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_EAST);
        if (state.getValue(WEST) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_WEST);
        if (state.getValue(UP) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_UP);
        if (state.getValue(DOWN) == ConnectionType.CABLE) shape = Shapes.or(shape, SHAPE_DOWN);

        if (state.getValue(NORTH) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_NORTH_BLOCK);
        if (state.getValue(SOUTH) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_SOUTH_BLOCK);
        if (state.getValue(EAST) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_EAST_BLOCK);
        if (state.getValue(WEST) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_WEST_BLOCK);
        if (state.getValue(UP) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_UP_BLOCK);
        if (state.getValue(DOWN) == ConnectionType.BLOCK) shape = Shapes.or(shape, SHAPE_DOWN_BLOCK);

        return shape;
    }

    // ---------- BlockEntity ----------

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide() || type != CAFBlockEntities.CABLE_BLOCK.get()) return null;
        return (l, p, s, be) -> ((CableBlockEntity) be).tick();
    }
}
