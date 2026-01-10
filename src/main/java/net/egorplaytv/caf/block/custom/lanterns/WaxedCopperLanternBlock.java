package net.egorplaytv.caf.block.custom.lanterns;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.praperties.BlockStateProperties;
import net.egorplaytv.caf.block.praperties.LanternAttachType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
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
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class WaxedCopperLanternBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED =
            net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<LanternAttachType> ATTACHMENT = BlockStateProperties.LANTERN_ATTACHMENT;
    private static final VoxelShape FLOOR =
            Shapes.or(Block.box(5D, 0D, 5D, 11D, 11D, 11D),
                    Block.box(4D, 1D, 4D, 12D, 9D, 12D),
                    Block.box(3.8D, 1.8D, 3.8D, 12.2D, 8.2D, 12.2D));
    private static final VoxelShape HANGING =
            Shapes.or(Block.box(5D, 0D, 5D, 11D, 11D, 11D),
                    Block.box(4D, 1D, 4D, 12D, 9D, 12D),
                    Block.box(3.8D, 1.8D, 3.8D, 12.2D, 8.2D, 12.2D));

    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(CAFBlocks.GLOWING_BRASS_COPPER_LANTERN.get(), CAFBlocks.GLOWING_BRASS_WAXED_COPPER_LANTERN.get())
                .put(CAFBlocks.GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get(), CAFBlocks.GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get())
                .put(CAFBlocks.GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get(), CAFBlocks.GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get())
                .put(CAFBlocks.GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get(), CAFBlocks.GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get())
                .build();
    });
    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> {
        return WAXABLES.get().inverse();
    });

    private VoxelShape TO_SOUTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.0625, 0.75, 0.625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.125, 0.6875, 0.125, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.625, 0.125, 0.6875, 0.75, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.23750000000000004, 0.175, 0.050000000000000044, 0.7625, 0.575, 0.575), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.875, 0.625, 0.6875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.625, 0.5625, 0.5625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.6875, 0.5625, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.875, 0, 0.5625, 1, 0.6875), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_NORTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.4375, 0.75, 0.625, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.5, 0.6875, 0.125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.625, 0.5, 0.6875, 0.75, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.23750000000000004, 0.175, 0.42500000000000004, 0.7625, 0.575, 0.95), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0, 0.625, 0.6875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.125, 0.5625, 0.5625, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.1875, 0.5625, 1, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.875, 0.3125, 0.5625, 1, 1), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_WEST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.125, 0.25, 0.9375, 0.625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.0625, 0.3125, 0.875, 0.125, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.625, 0.3125, 0.875, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.42500000000000004, 0.175, 0.23750000000000004, 0.95, 0.575, 0.7625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0.375, 0.125, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.4375, 0.4375, 0.375, 0.5625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.4375, 0.3125, 1, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.875, 0.4375, 1, 1, 0.5625), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_EAST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.125, 0.25, 0.5625, 0.625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.3125, 0.5, 0.125, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.625, 0.3125, 0.5, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.050000000000000044, 0.175, 0.23750000000000004, 0.575, 0.575, 0.7625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.3125, 0.375, 1, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.4375, 0.4375, 0.875, 0.5625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.5625, 0.4375, 0.8125, 1, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.4375, 0.6875, 1, 0.5625), BooleanOp.OR);

        return shape;
    }

    public WaxedCopperLanternBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTACHMENT, LanternAttachType.FLOOR)
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }


    private VoxelShape getVoxelShape(BlockState pState) {
        LanternAttachType lanternattachtype = pState.getValue(ATTACHMENT);
        if (lanternattachtype == LanternAttachType.FLOOR){
            return FLOOR;
        } else if (lanternattachtype == LanternAttachType.HANGING) {
            return HANGING;
        } else if (lanternattachtype == LanternAttachType.NORTH) {
            return TO_NORTH();
        } else if (lanternattachtype == LanternAttachType.SOUTH) {
            return TO_SOUTH();
        } else {
            return lanternattachtype == LanternAttachType.EAST ? TO_EAST() : TO_WEST();
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

    @SuppressWarnings("removal")
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        if (stack.getItem() instanceof AxeItem) {
            if (state.getBlock() instanceof WaxedCopperLanternBlock) {
                level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, pos, 0);
                return Optional.ofNullable(WAX_OFF_BY_BLOCK.get().get(state.getBlock())).get().defaultBlockState()
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED)).setValue(ATTACHMENT, state.getValue(ATTACHMENT));

            }
        }
        return super.getToolModifiedState(state, level, pos, player, stack, toolAction);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        Direction direction = pContext.getClickedFace();
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        Direction.Axis direction$axis = direction.getAxis();
        if (direction$axis == Direction.Axis.Y) {
            BlockState blockstate = this.defaultBlockState()
                    .setValue(ATTACHMENT, direction == Direction.DOWN ? LanternAttachType.HANGING : LanternAttachType.FLOOR);
            if (blockstate.canSurvive(pContext.getLevel(), blockpos)) {
                return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }
        } else if (direction$axis == Direction.Axis.X) {
            BlockState blockstate = this.defaultBlockState()
                    .setValue(ATTACHMENT, direction == Direction.WEST ? LanternAttachType.EAST : LanternAttachType.WEST);
            if (blockstate.canSurvive(pContext.getLevel(), blockpos)) {
                return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }
        } else if (direction$axis == Direction.Axis.Z) {
            BlockState blockstate = this.defaultBlockState()
                    .setValue(ATTACHMENT, direction == Direction.NORTH ? LanternAttachType.SOUTH : LanternAttachType.NORTH);
            if (blockstate.canSurvive(pContext.getLevel(), blockpos)) {
                return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }
        } else {
            boolean flag = level.getBlockState(blockpos.below()).isFaceSturdy(level, blockpos.below(), Direction.WEST);
            BlockState blockstate1 = this.defaultBlockState()
                    .setValue(ATTACHMENT, flag ? LanternAttachType.EAST : LanternAttachType.WEST);
            if (blockstate1.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate1.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }
            boolean flag1 = level.getBlockState(blockpos.below()).isFaceSturdy(level, blockpos.below(), Direction.SOUTH);
            blockstate1 = blockstate1.setValue(ATTACHMENT, flag1 ? LanternAttachType.NORTH : LanternAttachType.SOUTH);
            if (blockstate1.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate1.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }

            boolean flag2 = level.getBlockState(blockpos.below()).isFaceSturdy(level, blockpos.below(), Direction.UP);
            blockstate1 = blockstate1.setValue(ATTACHMENT, flag2 ? LanternAttachType.FLOOR : LanternAttachType.HANGING);
            if (blockstate1.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate1.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
            }
        }

        return null;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return getConnectedDirection(pState).getOpposite() == pDirection
                && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Direction direction = getConnectedDirection(pState).getOpposite();
        return Block.canSupportCenter(pLevel, pPos.relative(direction), direction.getOpposite());
    }


    private static Direction getConnectedDirection(BlockState pState){
        return switch ((LanternAttachType) pState.getValue(ATTACHMENT)) {
            case FLOOR -> Direction.UP;
            case HANGING -> Direction.DOWN;
            case NORTH -> Direction.SOUTH;
            case EAST -> Direction.WEST;
            case WEST -> Direction.EAST;
            default -> Direction.NORTH;
        };
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ATTACHMENT, WATERLOGGED);
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
