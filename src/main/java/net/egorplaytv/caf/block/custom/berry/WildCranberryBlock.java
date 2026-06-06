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

public class WildCranberryBlock extends WildBerryBushBlock {
    public WildCranberryBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return CAFItems.CRANBERRY.get().getDefaultInstance();
    }

    @Override
    public ItemStack getSapling() {
        return CAFItems.CRANBERRY_SAPLING.get().getDefaultInstance();
    }

    @Override
    public ItemStack getBerry() {
        return CAFItems.CRANBERRY.get().getDefaultInstance();
    }
}
