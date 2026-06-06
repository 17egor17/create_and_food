package net.egorplaytv.caf.block.custom.berry;

import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public abstract class BerryBushBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final BooleanProperty CUT = BooleanProperty.create("cut");

    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(5, 2, 5, 11, 8, 11)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(4, 2, 4, 12, 10, 12)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(3, 2, 3, 13, 12, 13)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(2, 2, 2, 14, 14, 14)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15))};

    private static final VoxelShape[] COLLISION_SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(0, 0, 0, 0, 0, 0)),
            Shapes.or(Block.box(5, 0, 5, 11, 4, 11)),
            Shapes.or(Block.box(4, 0, 4, 12, 5, 12)),
            Shapes.or(Block.box(3, 0, 3, 13, 6, 13)),
            Shapes.or(Block.box(2, 0, 2, 14, 7, 14)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15))};

    public BerryBushBlock(BlockBehaviour.Properties pProperties){
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(getAgeProperty(), Integer.valueOf(0))
                .setValue(CUT, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE[this.getAge(pState)];
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE[this.getAge(pState)];
    }

    public ItemStack getSapling() {
        return ItemStack.EMPTY;
    }

    public ItemStack getBerry() {
        return ItemStack.EMPTY;
    }

    public boolean getIsPrickly() {
        return false;
    }

    public DamageSource getDamage() {
        return null;
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState pState) {
        return pState.getValue(getAgeProperty());
    }

    public int getMaxAge(){
        return MAX_AGE;
    }

    public boolean isMaxAge(BlockState pState){
        return pState.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return !this.isMaxAge(pState);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        int i = getAge(pState);
        boolean j = pState.getValue(CUT);
        if (i < getMaxAge() && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(getAgeProperty(), Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
        if (i > 4 && j == Boolean.valueOf(true) && pLevel.getRawBrightness(pPos.above(), 0) >= 9 &&
                net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(CUT, Boolean.valueOf(false)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }

    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (getAge(pState) >= 2) {
            if (pEntity instanceof LivingEntity && pEntity.getType() != EntityType.FOX && pEntity.getType() != EntityType.BEE) {
                pEntity.makeStuckInBlock(pState, new Vec3((double) 0.8F, 0.75D, (double) 0.8F));
                if (getIsPrickly()) {
                    if (!pLevel.isClientSide && pState.getValue(AGE) > 0 && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
                        double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
                        double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
                        if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                            pEntity.hurt(getDamage(), 1.0F);
                        }
                    }
                }
            }
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 4 && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(false)) {
            ItemStack setBranch = getSapling();
            setBranch.setCount(1);
            pLevel.playSound((Player) null, pPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(CUT, Boolean.valueOf(true)), 2);
            if (!pPlayer.isCreative()) {
                pPlayer.getItemInHand(pHand).hurt(1, pLevel.random, (ServerPlayer) null);
            }
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setBranch);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (i > 4 && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(true)) {
            pPlayer.displayClientMessage(TextUtils.getBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (i < 5 && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS)) {
            pPlayer.displayClientMessage(TextUtils.getBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (i > 5) {
            ItemStack setBerries = getBerry();
            if (pState.getValue(AGE) == MAX_AGE) {
                setBerries.setCount(5);
            } else if (pState.getValue(AGE) == 6) {
                setBerries.setCount(3);
            }
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setBerries);
            pLevel.playSound((Player) null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(5)), 2);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, CUT);
    }

    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return !this.isMaxAge(pState);
    }

    public boolean isBonemealSuccess(Level pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        int i = Math.min(getMaxAge(), getAge(pState) + 1);
        pLevel.setBlock(pPos, pState.setValue(getAgeProperty(), Integer.valueOf(i)), 2);
    }
}
