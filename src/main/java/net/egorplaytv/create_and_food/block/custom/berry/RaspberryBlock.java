package net.egorplaytv.create_and_food.block.custom.berry;

import net.egorplaytv.create_and_food.damage.ModDamageSource;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;

public class RaspberryBlock extends BerryBushBlock {
    public RaspberryBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return ModItems.RASPBERRY.get().getDefaultInstance();
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity && pEntity.getType() != EntityType.FOX && pEntity.getType() != EntityType.BEE) {
            pEntity.makeStuckInBlock(pState, new Vec3((double)0.8F, 0.75D, (double)0.8F));
            if (!pLevel.isClientSide && pState.getValue(AGE) > 0 && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
                double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
                double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    pEntity.hurt(ModDamageSource.RASPBERRY_BUSH, 1.0F);
                }
            }
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 4 && pPlayer.getItemInHand(pHand).is(ModTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(false)) {
            ItemStack setBranch = ModItems.RASPBERRY_SAPLING.get().getDefaultInstance();
            setBranch.setCount(1);
            pLevel.playSound((Player) null, pPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(CUT, Boolean.valueOf(true)), 2);
            if (!pPlayer.isCreative()) {
                pPlayer.getItemInHand(pHand).hurt(1, pLevel.random, (ServerPlayer) null);
            }
            ItemHandlerHelper.giveItemToPlayer(pPlayer, setBranch);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (i > 4 && pPlayer.getItemInHand(pHand).is(ModTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(true)) {
            pPlayer.displayClientMessage(TextUtils.getBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (i < 5 && pPlayer.getItemInHand(pHand).is(ModTags.Items.CUT_TOOLS)) {
            pPlayer.displayClientMessage(TextUtils.getBerryBushTranslation("circumcised", new Object[0]), true);
        } else if (i > 5) {
            ItemStack setBerries = ModItems.RASPBERRY.get().getDefaultInstance();
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
}
