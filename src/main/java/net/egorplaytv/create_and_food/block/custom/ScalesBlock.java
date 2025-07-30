package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.block.entity.custom.ScalesBlockEntity;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.egorplaytv.create_and_food.block.entity.custom.ScalesBlockEntity.getWeight;

public class ScalesBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    public ScalesBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof ScalesBlockEntity entity){
            ItemStack heldStack = player.getItemInHand(handIn);
            if (entity.isEmpty()){
                if (heldStack.isEmpty()) {
                    return InteractionResult.PASS;
                }

                if (entity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (handIn.equals(InteractionHand.MAIN_HAND) && player.isShiftKeyDown() && player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                    if (!player.isCreative()) {
                        if (!player.getInventory().add(entity.removeItem())) {
                            Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), entity.removeItem());
                        }
                    } else {
                        entity.removeItem();
                    }

                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.METAL_HIT, SoundSource.BLOCKS, 0.25F, 0.5F);
                    return InteractionResult.SUCCESS;
                }
                if (handIn.equals(InteractionHand.MAIN_HAND) && player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                    if (getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) != null) {
                        if (CreateAndFoodCommonConfigs.ENABLE_KILOGRAMS.get() && CreateAndFoodCommonConfigs.ENABLE_GRAMS.get()
                                && CreateAndFoodCommonConfigs.ENABLE_TONES.get()) {
                            if (getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) >= 1000000) {
                                float tn = (float) getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) / 1000000;
                                player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_tn", tn)), true);
                            } else if (getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) >= 1000) {
                                float kg = (float) getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) / 1000;
                                player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_kg", kg)), true);
                            } else {
                                float g = getWeight().get(entity.getInventory().getStackInSlot(0).getItem());
                                player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_g", g)), true);
                            }
                        } else if (CreateAndFoodCommonConfigs.ENABLE_KILOGRAMS.get()){
                            float kg = (float) getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) / 1000;
                            player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_kg", kg)), true);
                        } else if (CreateAndFoodCommonConfigs.ENABLE_GRAMS.get()){
                            float g = getWeight().get(entity.getInventory().getStackInSlot(0).getItem());
                            player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_g", g)), true);
                        } else if (CreateAndFoodCommonConfigs.ENABLE_TONES.get()){
                            float tn = (float) getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) / 1000000;
                            player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_tn", tn)), true);
                        } else {
                            float caf = (float)(getWeight().get(entity.getInventory().getStackInSlot(0).getItem()) * 3.14) / 10;
                            if (caf >= 1000) {
                                float kCaf = caf / 1000;
                                player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_kcaf", kCaf)), true);
                            } else {
                                player.displayClientMessage(new TranslatableComponent("create_and_food.scales_weight", TextUtils.getModTranslation("scales_weight_caf", caf)), true);
                            }
                        }
                    } else {
                        player.displayClientMessage(TextUtils.getModTranslation("scales_weight_nothing"), true);
                    }
                }
            }
        }


        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof ScalesBlockEntity) {
                Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((ScalesBlockEntity)tileEntity).getStoredItem());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }

    }

    public boolean isPossibleToRespawnInThis() {
        return true;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
                                                                  BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.SCALES_BLOCK_ENTITY.get(), ScalesBlockEntity::tick);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ScalesBlockEntity(pPos, pState);
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
}
