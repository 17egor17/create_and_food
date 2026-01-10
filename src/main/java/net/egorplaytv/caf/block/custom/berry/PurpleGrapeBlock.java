package net.egorplaytv.caf.block.custom.berry;

import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.items.ItemHandlerHelper;

public class PurpleGrapeBlock extends BerryBushBlock {
    public PurpleGrapeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return CAFItems.PURPLE_GRAPE.get().getDefaultInstance();
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 4 && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS) && pState.getValue(CUT) == Boolean.valueOf(false)) {
            ItemStack setBranch = CAFItems.PURPLE_GRAPE_SAPLING.get().getDefaultInstance();
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
            ItemStack setBerries = CAFItems.PURPLE_GRAPE.get().getDefaultInstance();
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
