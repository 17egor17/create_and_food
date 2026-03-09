package net.egorplaytv.caf.block.custom.lanterns;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.praperties.BlockStateProperties;
import net.egorplaytv.caf.block.praperties.LanternAttachType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class CopperLanternBlock extends LanternBlock implements SimpleWaterloggedBlock, WeatheringCopperLantern {
    private static final VoxelShape FLOOR =
            Shapes.or(Block.box(5D, 0D, 5D, 11D, 11D, 11D),
                    Block.box(4D, 1D, 4D, 12D, 9D, 12D),
                    Block.box(3.8D, 1.8D, 3.8D, 12.2D, 8.2D, 12.2D));
    private static final VoxelShape HANGING =
            Shapes.or(Block.box(5D, 0D, 5D, 11D, 11D, 11D),
                    Block.box(4D, 1D, 4D, 12D, 9D, 12D),
                    Block.box(3.8D, 1.8D, 3.8D, 12.2D, 8.2D, 12.2D));

    private final WeatherState weatherState;

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

    public CopperLanternBlock(Properties pProperties, WeatherState weatherState) {
        super(pProperties);
        this.weatherState = weatherState;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        this.onRandomTick(pState, pLevel, pPos, pRandom);
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return WeatheringCopperLantern.getNext(pState.getBlock()).isPresent();
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
            if (state.getBlock() instanceof CopperLanternBlock) {
                if (state.getBlock() != CAFBlocks.GLOWING_BRASS_COPPER_LANTERN.get()) {
                    level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3005, pos, 0);
                    return WeatheringCopperLantern.getPrevious(state.getBlock()).get().defaultBlockState()
                            .setValue(WATERLOGGED, state.getValue(WATERLOGGED)).setValue(ATTACHMENT, state.getValue(ATTACHMENT));
                } else {
                    return super.getToolModifiedState(state, level, pos, player, stack, toolAction);
                }
            }
        }

        return super.getToolModifiedState(state, level, pos, player, stack, toolAction);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack.getItem() instanceof HoneycombItem){
            if (pState.getBlock() instanceof CopperLanternBlock){
                pLevel.playSound(pPlayer, pPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!pPlayer.isCreative()) {
                    itemStack.shrink(1);
                }
                pLevel.setBlock(pPos, Optional.ofNullable(WaxedCopperLanternBlock.WAXABLES.get().get(pState.getBlock())).get().defaultBlockState()
                        .setValue(WATERLOGGED, pState.getValue(WATERLOGGED)).setValue(ATTACHMENT, pState.getValue(ATTACHMENT)), 11);
                pLevel.levelEvent(pPlayer, 3003, pPos, 0);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }
}
