package net.egorplaytv.caf.block.custom.worktable;

import net.egorplaytv.caf.block.entity.custom.worktable.WorktableBlockEntity;
import net.egorplaytv.caf.block.praperties.WorktableType;
import net.egorplaytv.caf.screen.WorktableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class WorktableBlock extends AbstractWorktableBlock<WorktableBlockEntity> implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<WorktableType> TYPE = net.egorplaytv.caf.block.praperties.BlockStateProperties.WORKTABLE_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final DoubleBlockCombiner.Combiner<WorktableBlockEntity, Optional<Container>> WORKTABLE_COMBINER = new DoubleBlockCombiner.Combiner<WorktableBlockEntity, Optional<Container>>() {
        public Optional<Container> acceptDouble(WorktableBlockEntity p_51591_, WorktableBlockEntity p_51592_) {
            return Optional.of(new CompoundContainer(p_51591_, p_51592_));
        }

        public Optional<Container> acceptSingle(WorktableBlockEntity p_51589_) {
            return Optional.of(p_51589_);
        }

        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<WorktableBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<WorktableBlockEntity, Optional<MenuProvider>>() {
        public Optional<MenuProvider> acceptDouble(final WorktableBlockEntity pFirst, final WorktableBlockEntity pSecond) {
            final Container container = new CompoundContainer(pFirst, pSecond);
            return Optional.of(new MenuProvider() {
                @javax.annotation.Nullable
                public AbstractContainerMenu createMenu(int p_51622_, Inventory p_51623_, Player p_51624_) {
                    if (pFirst.canOpen(p_51624_) && pSecond.canOpen(p_51624_)) {
                        pFirst.unpackLootTable(p_51623_.player);
                        pSecond.unpackLootTable(p_51623_.player);
                        return WorktableMenu.worktableDouble(p_51622_, p_51623_, container);
                    } else {
                        return null;
                    }
                }

                @Override
                public Component getDisplayName() {
                    return new TextComponent("");
                }
            });
        }

        public Optional<MenuProvider> acceptSingle(WorktableBlockEntity p_51602_) {
            return Optional.of(p_51602_);
        }

        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };


    public WorktableBlock(Properties pProperties, Supplier<BlockEntityType<? extends WorktableBlockEntity>> blockEntityType) {
        super(pProperties, blockEntityType);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, WorktableType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState p_51583_) {
        WorktableType worktableType = p_51583_.getValue(TYPE);
        if (worktableType == WorktableType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return worktableType == WorktableType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (pFacingState.is(this) && pFacing.getAxis().isHorizontal()) {
            WorktableType worktableType = pFacingState.getValue(TYPE);
            if (pState.getValue(TYPE) == WorktableType.SINGLE && worktableType != WorktableType.SINGLE && pState.getValue(FACING) == pFacingState.getValue(FACING) && getConnectedDirection(pFacingState) == pFacing.getOpposite()) {
                return pState.setValue(TYPE, worktableType.getOpposite());
            }
        } else if (getConnectedDirection(pState) == pFacing) {
            return pState.setValue(TYPE, WorktableType.SINGLE);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return box(0, 0, 0, 16, 16, 16);
    }

    public static Direction getConnectedDirection(BlockState p_51585_) {
        Direction direction = p_51585_.getValue(FACING);
        return p_51585_.getValue(TYPE) == WorktableType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        WorktableType worktableType = WorktableType.SINGLE;
        Direction direction = pContext.getHorizontalDirection().getOpposite();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        boolean flag = pContext.isSecondaryUseActive();
        Direction direction1 = pContext.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(pContext, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                worktableType = direction2.getCounterClockWise() == direction1.getOpposite() ? WorktableType.RIGHT : WorktableType.LEFT;
            }
        }

        if (worktableType == WorktableType.SINGLE && !flag) {
            if (direction == this.candidatePartnerFacing(pContext, direction.getClockWise())) {
                worktableType = WorktableType.LEFT;
            } else if (direction == this.candidatePartnerFacing(pContext, direction.getCounterClockWise())) {
                worktableType = WorktableType.RIGHT;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, worktableType).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @javax.annotation.Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext pContext, Direction pDirection) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(pDirection));
        return blockstate.is(this) && blockstate.getValue(TYPE) == WorktableType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName()) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof WorktableBlockEntity) {
                ((WorktableBlockEntity)blockentity).setCustomName(pStack.getHoverName());
            }
        }

    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof Container) {
                Containers.dropContents(pLevel, pPos, (Container)blockentity);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pHit.getDirection().equals(Direction.UP)) {
            if (!pLevel.isClientSide()) {
                MenuProvider menuprovider = this.getMenuProvider(pState, pLevel, pPos);
                if (menuprovider != null) {
                    pPlayer.openMenu(menuprovider);
                }
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @javax.annotation.Nullable
    public static Container getContainer(WorktableBlock worktableBlock, BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride) {
        return worktableBlock.combine(pState, pLevel, pPos, pOverride).<Optional<Container>>apply(WORKTABLE_COMBINER).orElse((Container)null);
    }

    public DoubleBlockCombiner.NeighborCombineResult<? extends WorktableBlockEntity> combine(BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (pOverride) {
            bipredicate = (p_51578_, p_51579_) -> {
                return false;
            };
        } else {
            bipredicate = WorktableBlock::isWorktableBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(this.blockEntityType.get(), WorktableBlock::getBlockType, WorktableBlock::getConnectedDirection, FACING, pState, pLevel, pPos, bipredicate);
    }

    @Nullable
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return this.combine(pState, pLevel, pPos, false).<Optional<MenuProvider>>apply(MENU_PROVIDER_COMBINER).orElse((MenuProvider)null);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new WorktableBlockEntity(pPos, pState);
    }

    public static boolean isWorktableBlockedAt(LevelAccessor p_51509_, BlockPos p_51510_) {
        return false;
    }

    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, pBlockState, pLevel, pPos, false));
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        BlockState rotated = pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
        return pMirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());  // Forge: Fixed MC-134110 Structure mirroring breaking apart double chests
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, WATERLOGGED);
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
