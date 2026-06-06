package net.egorplaytv.caf.block.custom.berry;

import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WildSweetBerryBlock extends WildBerryBushBlock {
    public WildSweetBerryBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(Block.box(0, 0, 0, 0, 0, 0));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return CAFItems.SWEET_BERRIES.get().getDefaultInstance();
    }

    @Override
    public ItemStack getSapling() {
        return CAFItems.SWEET_BERRIES_SAPLING.get().getDefaultInstance();
    }

    @Override
    public ItemStack getBerry() {
        return CAFItems.SWEET_BERRIES.get().getDefaultInstance();
    }

    @Override
    public boolean getIsPrickly() {
        return true;
    }

    @Override
    public DamageSource getDamage() {
        return DamageSource.SWEET_BERRY_BUSH;
    }
}
