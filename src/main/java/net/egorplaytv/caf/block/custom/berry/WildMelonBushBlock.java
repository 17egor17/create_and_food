package net.egorplaytv.caf.block.custom.berry;

import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.util.TextUtils;
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

public class WildMelonBushBlock extends WildPumpkinAndMelonBlock{
    public WildMelonBushBlock(Properties pProperties) {
        super(pProperties);
        this.hasJungle = true;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(IN_JUNGLE, hasJungle));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return Blocks.MELON.asItem().getDefaultInstance();
    }

    @Override
    public ItemStack getFruit() {
        return Blocks.MELON.asItem().getDefaultInstance();
    }
}
