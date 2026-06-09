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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WildBerryBushBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    private static final VoxelShape SHAPE = Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15));
    private static final VoxelShape COLLISION_SHAPE = Block.box(1, 0, 1, 15, 8, 15);

    public WildBerryBushBlock(Properties pProperties){
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(getAgeProperty(), Integer.valueOf(0))
                .setValue(CUT, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
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

    /**
     * This method sets DamageSource if your bush is prickly
     * Also, this method will not work if getIsPrickly() is set to false.
     */
    public DamageSource getDamage() {
        return null;
    }

    public boolean isNether() {
        return false;
    }

    public boolean isOtherSoils() {
        return false;
    }

    /**
     * This method accepts a maximum of 3 soil types.
     * Also, this method will not work if isOtherSoils() is set to false.
     */
    public List<Block> getOtherSoils() {
        List<Block> souls = new ArrayList<>();
        souls.add(Blocks.DIRT);
        souls.add(Blocks.FARMLAND);
        return souls;
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

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (isNether())
            return pState.is(Blocks.NETHERRACK) || pState.is(Blocks.WARPED_NYLIUM) || pState.is(Blocks.CRIMSON_NYLIUM);
        else if (isOtherSoils())
            return pState.is(getOtherSoils().get(0)) || getOtherSoils().get(1) != null ? pState.is(getOtherSoils().get(1)) : pState.is(getOtherSoils().get(0))
                    || getOtherSoils().get(2) != null ? pState.is(getOtherSoils().get(2)) : pState.is(getOtherSoils().get(1));
        else
            return super.mayPlaceOn(pState, pLevel, pPos);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        int i = getAge(pState);
        if (i < getMaxAge() && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(getAgeProperty(), Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }

    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity && pEntity.getType() != EntityType.FOX && pEntity.getType() != EntityType.BEE) {
            pEntity.makeStuckInBlock(pState, new Vec3((double) 0.8F, 0.75D, (double) 0.8F));
            if (getIsPrickly()) {
                if (!pLevel.isClientSide && pState.getValue(AGE) >= 0 && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
                    double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
                    double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
                    if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                        pEntity.hurt(getDamage(), 1.0F);
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
        } else if (pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(false)) {
            ItemStack setBranch = getSapling();
            setBranch.setCount(5);
            pLevel.playSound((Player) null, pPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(CUT, Boolean.valueOf(true)), 2);
            if (!pPlayer.isCreative()) {
                pPlayer.getItemInHand(pHand).hurt(1, pLevel.random, (ServerPlayer) null);
            }
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setBranch);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(true)) {
            pPlayer.displayClientMessage(TextUtils.getWildBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS)) {
            pPlayer.displayClientMessage(TextUtils.getWildBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (i > 0) {
            ItemStack setBerries = getBerry();
            if (pState.getValue(AGE) == MAX_AGE) {
                setBerries.setCount(5);
            } else if (pState.getValue(AGE) == 1) {
                setBerries.setCount(3);
            }
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setBerries);
            pLevel.playSound((Player) null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(0)), 2);
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
