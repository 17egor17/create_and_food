package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class RiceBlock extends BushBlock implements BonemealableBlock, LiquidBlockContainer {
    public static final IntegerProperty AGE;
    public static final BooleanProperty SUPPORTING;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public RiceBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(SUPPORTING, false));
    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (worldIn.isAreaLoaded(pos, 1)) {
            if (worldIn.getRawBrightness(pos.above(), 0) >= 6) {
                int age = this.getAge(state);
                if (age <= this.getMaxAge()) {
                    float chance = 10.0F;
                    if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / chance) + 1) == 0)) {
                        if (age == this.getMaxAge()) {
                            RicePaniclesBlock riceUpper = (RicePaniclesBlock) ModBlocks.RICE_CROP_PANICLES.get();
                            if (riceUpper.defaultBlockState().canSurvive(worldIn, pos.above()) && worldIn.isEmptyBlock(pos.above())) {
                                worldIn.setBlockAndUpdate(pos.above(), riceUpper.defaultBlockState());
                                ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                            }
                        } else {
                            worldIn.setBlock(pos, this.withAge(age + 1), 2);
                            ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                        }
                    }
                }
            }

        }
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        FluidState fluid = worldIn.getFluidState(pos);
        BlockState block = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
        if (!(block.getBlock() instanceof FloodedFarmlandBlock || block.getBlock() instanceof FloodedRichSoilFarmlandBlock)){
            return super.canSurvive(state, worldIn, pos) && fluid.is(FluidTags.WATER) && fluid.getAmount() == 8;
        } else {
            return super.canSurvive(state, worldIn, pos) && (block.getValue(FloodedFarmlandBlock.WATERLOGGED)
                    || block.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED));
        }
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        if (!(state.getBlock() instanceof FloodedFarmlandBlock || state.getBlock() instanceof FloodedRichSoilFarmlandBlock)){
            return super.mayPlaceOn(state, worldIn, pos) || state.is(BlockTags.DIRT);
        } else {
            return super.mayPlaceOn(state, worldIn, pos) || (state.getValue(FloodedFarmlandBlock.WATERLOGGED)
                    || state.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED));
        }
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 3;
    }

    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.RICE.get());
    }

    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    public boolean isMaxAge(BlockState state) {
        return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, SUPPORTING);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState state = super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        if (!state.isAir()) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
            if (facing == Direction.UP) {
                return state.setValue(SUPPORTING, this.isSupportingRiceUpper(facingState));
            }
        }

        return state;
    }

    public boolean isSupportingRiceUpper(BlockState topState) {
        return topState.getBlock() == ModBlocks.RICE_CROP_PANICLES.get();
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = context.getLevel().getBlockState(new BlockPos(context.getClickedPos().getX(),
                context.getClickedPos().getY() - 1, context.getClickedPos().getZ()));

        if ((state.getBlock() instanceof FloodedFarmlandBlock || state.getBlock() instanceof FloodedRichSoilFarmlandBlock)
                && (state.getValue(FloodedFarmlandBlock.WATERLOGGED) || state.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED))){
            BlockPos blockpos = context.getClickedPos();
            Player player = context.getPlayer();
            Level level = context.getLevel();
            ItemStack itemStack = context.getItemInHand();
            BlockState blockState = ModBlocks.RICE_PLANT.get().defaultBlockState();
            level.setBlock(blockpos, blockState, 3);
            if (player instanceof ServerPlayer){
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos, itemStack);
            }
            level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
            level.playSound(player, blockpos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            if (player == null || !player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            return null;
        } else {
            return fluid.is(FluidTags.WATER) && fluid.getAmount() == 8 ? super.getStateForPlacement(context) : null;
        }
    }

    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        BlockState upperState = worldIn.getBlockState(pos.above());
        if (upperState.getBlock() instanceof RicePaniclesBlock) {
            return !((RicePaniclesBlock)upperState.getBlock()).isMaxAge(upperState);
        } else {
            return true;
        }
    }

    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    protected int getBonemealAgeIncrease(Level worldIn) {
        return Mth.nextInt(worldIn.random, 1, 4);
    }

    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(worldIn), 7);
        if (ageGrowth <= this.getMaxAge()) {
            worldIn.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth));
        } else {
            BlockState top = worldIn.getBlockState(pos.above());
            if (top.getBlock() == ModBlocks.RICE_CROP_PANICLES.get()) {
                BonemealableBlock growable = (BonemealableBlock)worldIn.getBlockState(pos.above()).getBlock();
                if (growable.isValidBonemealTarget(worldIn, pos.above(), top, false)) {
                    growable.performBonemeal(worldIn, worldIn.random, pos.above(), top);
                }
            } else {
                RicePaniclesBlock riceUpper = (RicePaniclesBlock)ModBlocks.RICE_CROP_PANICLES.get();
                int remainingGrowth = ageGrowth - this.getMaxAge() - 1;
                if (riceUpper.defaultBlockState().canSurvive(worldIn, pos.above()) && worldIn.isEmptyBlock(pos.above())) {
                    worldIn.setBlockAndUpdate(pos, state.setValue(AGE, this.getMaxAge()));
                    worldIn.setBlock(pos.above(), riceUpper.defaultBlockState().setValue(RicePaniclesBlock.RICE_AGE, remainingGrowth), 2);
                }
            }
        }

    }

    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return false;
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SUPPORTING = BooleanProperty.create("supporting");
        SHAPE_BY_AGE = new VoxelShape[]{Block.box(3.0F, 0.0F, 3.0F, 13.0F, 8.0F, 13.0F),
                Block.box(3.0F, 0.0F, 3.0F, 13.0F, 10.0F, 13.0F),
                Block.box(2.0F, 0.0F, 2.0F, 14.0F, 12.0F, 14.0F),
                Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F)};
    }
}
