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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

public class SweetberryBlock extends BerryBushBlock {
    public SweetberryBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(Block.box(0, 0, 0, 0, 0, 0));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return CAFItems.BLUEBERRY.get().getDefaultInstance();
    }

    @Override
    public ItemStack getSapling() {
        return new ItemStack(Items.AIR);
    }

    @Override
    public ItemStack getBerry() {
        return new ItemStack(Items.AIR);
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
