package net.egorplaytv.create_and_food.block.custom.berry;

import net.egorplaytv.create_and_food.util.CAFTags;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.items.ItemHandlerHelper;

public class PumpkinBushBlock extends PumpkinAndMelonBlock{
    public PumpkinBushBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return Blocks.PUMPKIN.asItem().getDefaultInstance();
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i < MAX_AGE && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS)) {
            pPlayer.displayClientMessage(TextUtils.getPumpkinAndMelonBushTranslation("cut", new Object[0]), true);
        } else if (i == MAX_AGE && pPlayer.getItemInHand(pHand).is(CAFTags.Items.CUT_TOOLS)) {
            ItemStack setPumpkin = Blocks.PUMPKIN.asItem().getDefaultInstance();
            setPumpkin.setCount(1);

            ItemHandlerHelper.giveItemToPlayer(pPlayer, setPumpkin);
            pLevel.playSound((Player) null, pPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(2)), 2);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
