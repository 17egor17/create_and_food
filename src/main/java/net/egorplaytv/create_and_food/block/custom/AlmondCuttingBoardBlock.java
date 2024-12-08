package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.block.entity.custom.AlmondCuttingBoardBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;

public class AlmondCuttingBoardBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape SHAPE;

    public AlmondCuttingBoardBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.getStateDefinition().any()).setValue(FACING, Direction.NORTH)).setValue(WATERLOGGED, false));
    }

    public static void spawnCuttingParticles(Level worldIn, BlockPos pos, ItemStack stack, int count) {
        for(int i = 0; i < count; ++i) {
            Vec3 vec3d = new Vec3(((double)worldIn.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, ((double)worldIn.random.nextFloat() - 0.5) * 0.1);
            if (worldIn instanceof ServerLevel) {
                ((ServerLevel)worldIn).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.1F), (double)((float)pos.getZ() + 0.5F), 1, vec3d.x, vec3d.y + 0.05, vec3d.z, 0.0);
            } else {
                worldIn.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.1F), (double)((float)pos.getZ() + 0.5F), vec3d.x, vec3d.y + 0.05, vec3d.z);
            }
        }

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

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof AlmondCuttingBoardBlockEntity cuttingBoardEntity) {
            ItemStack heldStack = player.getItemInHand(handIn);
            ItemStack offhandStack = player.getOffhandItem();
            if (cuttingBoardEntity.isEmpty()) {
                if (!offhandStack.isEmpty()) {
                    if (handIn.equals(InteractionHand.MAIN_HAND) && !offhandStack.is(ModTags.OFFHAND_EQUIPMENT) && !(heldStack.getItem() instanceof BlockItem)) {
                        return InteractionResult.PASS;
                    }

                    if (handIn.equals(InteractionHand.OFF_HAND) && offhandStack.is(ModTags.OFFHAND_EQUIPMENT)) {
                        return InteractionResult.PASS;
                    }
                }

                if (heldStack.isEmpty()) {
                    return InteractionResult.PASS;
                }

                if (cuttingBoardEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
                    worldIn.playSound((Player)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (!heldStack.isEmpty()) {
                    ItemStack boardStack = cuttingBoardEntity.getStoredItem().copy();
                    if (cuttingBoardEntity.processStoredItemUsingTool(heldStack, player)) {
                        spawnCuttingParticles(worldIn, pos, boardStack, 5);
                        return InteractionResult.SUCCESS;
                    }

                    return InteractionResult.CONSUME;
                }

                if (handIn.equals(InteractionHand.MAIN_HAND)) {
                    if (!player.isCreative()) {
                        if (!player.getInventory().add(cuttingBoardEntity.removeItem())) {
                            Containers.dropItemStack(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), cuttingBoardEntity.removeItem());
                        }
                    } else {
                        cuttingBoardEntity.removeItem();
                    }

                    worldIn.playSound((Player)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 0.25F, 0.5F);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof AlmondCuttingBoardBlockEntity) {
                Containers.dropItemStack(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), ((AlmondCuttingBoardBlockEntity)tileEntity).getStoredItem());
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }

    }

    public boolean isPossibleToRespawnInThis() {
        return true;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if ((Boolean)stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos floorPos = pos.below();
        return canSupportRigidBlock(worldIn, floorPos) || canSupportCenter(worldIn, floorPos, Direction.UP);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{FACING, WATERLOGGED});
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof AlmondCuttingBoardBlockEntity) {
            return !((AlmondCuttingBoardBlockEntity)tileEntity).isEmpty() ? 15 : 0;
        } else {
            return 0;
        }
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ((BlockEntityType) ModBlockEntities.ALMOND_CUTTING_BOARD.get()).create(pos, state);
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return (BlockState)pState.setValue(FACING, pRot.rotate((Direction)pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation((Direction)pState.getValue(FACING)));
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    }

    @Mod.EventBusSubscriber(
            modid = CreateAndFood.MOD_ID,
            bus = Mod.EventBusSubscriber.Bus.FORGE
    )
    public static class ToolCarvingEvent {
        public ToolCarvingEvent() {
        }

        @SubscribeEvent
        public static void onSneakPlaceTool(PlayerInteractEvent.RightClickBlock event) {
            Level world = event.getWorld();
            BlockPos pos = event.getPos();
            Player player = event.getPlayer();
            ItemStack heldStack = player.getMainHandItem();
            BlockEntity tileEntity = world.getBlockEntity(event.getPos());
            if (player.isSecondaryUseActive() && !heldStack.isEmpty() && tileEntity instanceof AlmondCuttingBoardBlockEntity && (heldStack.getItem() instanceof TieredItem || heldStack.getItem() instanceof TridentItem || heldStack.getItem() instanceof ShearsItem)) {
                boolean success = ((AlmondCuttingBoardBlockEntity)tileEntity).carveToolOnBoard(player.getAbilities().instabuild ? heldStack.copy() : heldStack);
                if (success) {
                    world.playSound((Player)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }

        }
    }
}
