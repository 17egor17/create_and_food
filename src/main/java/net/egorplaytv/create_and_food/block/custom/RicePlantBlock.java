package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.item.CAFItems;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

public class RicePlantBlock extends FloodedCropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Shapes.box(0.375, -0.5625, 0.375, 0.625, -0.25, 0.625),
            Shapes.box(0.3125, -0.5625, 0.3125, 0.6875, 0.0625, 0.6875),
            Shapes.box(0.25, -0.5625, 0.25, 0.75, 0.3125, 0.75),
            Shapes.box(0.1875, -0.5625, 0.1875, 0.8125, 0.4375, 0.8125),
            Shapes.box(0.1875, -0.5625, 0.1875, 0.8125, 0.5625, 0.8125),
            Shapes.box(0.125, -0.5625, 0.125, 0.875, 0.8125, 0.875),
            Shapes.box(0.0625, -0.5625, 0.0625, 0.9375, 0.875, 0.9375),
            Shapes.box(0, -0.5625, 0, 1, 1, 1)};

    public RicePlantBlock(Properties pProperties) {
        super(pProperties);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(this.getAgeProperty())];
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == getMaxAge();
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (flag){
            ItemStack setPanicle = vectorwing.farmersdelight.common.registry.ModItems.RICE_PANICLE.get().getDefaultInstance();
            setPanicle.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setPanicle);
            pLevel.playSound((Player) null, pPos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(5)), 2);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return CAFItems.RICE.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
