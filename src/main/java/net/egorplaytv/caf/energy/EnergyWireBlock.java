package net.egorplaytv.caf.energy;

import net.egorplaytv.caf.block.custom.EnergyConvertorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnergyWireBlock extends Block {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public EnergyWireBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(NORTH, false).setValue(SOUTH, false)
                        .setValue(EAST, false).setValue(WEST, false)
                        .setValue(UP, false).setValue(DOWN, false)
        );
    }

    public float getMaxTransferPerTick() {
        return 64.0F;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (!neighborState.isAir()) {
            Block neighborBlock = neighborState.getBlock();

            boolean isConnected = false;

            if (neighborBlock instanceof EnergyWireBlock) {
                isConnected = true;
            } else if (neighborBlock instanceof EnergyConvertorBlock) {
                isConnected = true;
            } else if (neighborBlock instanceof net.minecraft.world.level.block.entity.BlockEntityTicker ||
                    (level instanceof Level && level.getBlockEntity(neighborPos) != null)) {

                if (isEnergyCapableBlock(neighborBlock)) {
                    isConnected = true;
                }
            }

            if (isConnected) {
                return state.setValue(getDirectionProperty(direction), true);
            }
        }

        return state.setValue(getDirectionProperty(direction), false);
    }

    private BooleanProperty getDirectionProperty(Direction d) {
        switch (d) {
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            case UP: return UP;
            case DOWN: return DOWN;
            default: throw new IllegalArgumentException("Invalid direction");
        }
    }

    private boolean isEnergyCapableBlock(Block block) {
        return block instanceof EnergyWireBlock ||
                block instanceof EnergyConvertorBlock;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Block.box(7, 7, 7, 9, 9, 9);

        if (pState.getValue(NORTH)) shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(7, 7, 0, 9, 9, 7), net.minecraft.world.phys.shapes.BooleanOp.OR);
        if (pState.getValue(SOUTH)) shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(7, 7, 9, 9, 9, 16), net.minecraft.world.phys.shapes.BooleanOp.OR);
        if (pState.getValue(EAST))  shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(9, 7, 7, 16, 9, 9), net.minecraft.world.phys.shapes.BooleanOp.OR);
        if (pState.getValue(WEST))  shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(0, 7, 7, 7, 9, 9), net.minecraft.world.phys.shapes.BooleanOp.OR);
        if (pState.getValue(UP))    shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(7, 9, 7, 9, 16, 9), net.minecraft.world.phys.shapes.BooleanOp.OR);
        if (pState.getValue(DOWN))  shape = net.minecraft.world.phys.shapes.Shapes.join(shape, Block.box(7, 0, 7, 9, 7, 9), net.minecraft.world.phys.shapes.BooleanOp.OR);

        return shape;
    }
}