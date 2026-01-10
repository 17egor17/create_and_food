package net.egorplaytv.caf.block.custom;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

public class RicePaniclesBlock extends CropBlock {
    public static final IntegerProperty RICE_AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public RicePaniclesBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public IntegerProperty getAgeProperty() {
        return RICE_AGE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(RICE_AGE);
        boolean flag = i == getMaxAge();
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (flag){
            ItemStack setPanicle = vectorwing.farmersdelight.common.registry.ModItems.RICE_PANICLE.get().getDefaultInstance();
            setPanicle.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setPanicle);
            pLevel.playSound((Player) null, pPos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(RICE_AGE, Integer.valueOf(1)), 2);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        return InteractionResult.PASS;
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[(Integer)state.getValue(this.getAgeProperty())];
    }

    public int getMaxAge() {
        return 3;
    }

    protected ItemLike getBaseSeedId() {
        return (ItemLike) CAFItems.RICE.get();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{RICE_AGE});
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock() == CAFBlocks.RICE_CROP.get();
    }

    protected int getBonemealAgeIncrease(Level worldIn) {
        return super.getBonemealAgeIncrease(worldIn) / 3;
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && worldIn.getBlockState(pos.below()).getBlock() == CAFBlocks.RICE_CROP.get();
    }

    static {
        RICE_AGE = BlockStateProperties.AGE_3;
        SHAPE_BY_AGE = new VoxelShape[]{Block.box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)8.0F, (double)13.0F), Block.box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)10.0F, (double)13.0F), Block.box((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)12.0F, (double)14.0F), Block.box((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F)};
    }
}
