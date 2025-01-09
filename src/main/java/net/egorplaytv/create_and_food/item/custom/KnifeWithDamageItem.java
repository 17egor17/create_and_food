package net.egorplaytv.create_and_food.item.custom;

import com.google.common.collect.Sets;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Set;

public class KnifeWithDamageItem extends KnifeItem {
    public static ItemStack knife;
    public KnifeWithDamageItem(Tier tier, ItemStack knife, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        KnifeWithDamageItem.knife = knife;
    }

    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if (pLivingEntity instanceof Player player){
            ItemHandlerHelper.giveItemToPlayer(player, knife);
        }
        return ItemHandlerHelper.insertItem(null, itemStack, false);
    }

    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        ItemStack toolStack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Direction facing = context.getClickedFace();
        if (state.getBlock() == Blocks.PUMPKIN && toolStack.is(ModTags.KNIVES)) {
            Player player = context.getPlayer();
            if (player != null && !world.isClientSide) {
                Direction direction = facing.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : facing;
                world.playSound((Player)null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.setBlock(pos, (BlockState)Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction), 11);
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + (double)0.5F + (double)direction.getStepX() * 0.65, (double)pos.getY() + 0.1, (double)pos.getZ() + (double)0.5F + (double)direction.getStepZ() * 0.65, new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itemEntity.setDeltaMovement(0.05 * (double)direction.getStepX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction.getStepZ() + world.random.nextDouble() * 0.02);
                world.addFreshEntity(itemEntity);
                toolStack.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(context.getHand()));
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(new Enchantment[]{Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING});
        if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
            return true;
        } else {
            Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(new Enchantment[]{Enchantments.BLOCK_FORTUNE});
            return DENIED_ENCHANTMENTS.contains(enchantment) ? false : enchantment.category.canEnchant(stack.getItem());
        }
    }

    @Mod.EventBusSubscriber(
            modid = CreateAndFood.MOD_ID,
            bus = Mod.EventBusSubscriber.Bus.FORGE
    )
    public static class KnifeEvents {
        public KnifeEvents() {
        }

        @SubscribeEvent
        public static void onKnifeKnockback(LivingKnockBackEvent event) {
            LivingEntity attacker = event.getEntityLiving().getKillCredit();
            ItemStack toolStack = attacker != null ? attacker.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
            if (toolStack.getItem() instanceof KnifeItem) {
                float f = event.getOriginalStrength();
                event.setStrength(event.getOriginalStrength() - 0.1F);
            }

        }

        @SubscribeEvent
        public static void onCakeInteraction(PlayerInteractEvent.RightClickBlock event) {
            ItemStack toolStack = event.getPlayer().getItemInHand(event.getHand());
            if (toolStack.is(ModTags.KNIVES)) {
                Level world = event.getWorld();
                BlockPos pos = event.getPos();
                BlockState state = event.getWorld().getBlockState(pos);
                Block block = state.getBlock();
                if (state.is(ModTags.DROPS_CAKE_SLICE)) {
                    world.setBlock(pos, (BlockState)Blocks.CAKE.defaultBlockState().setValue(CakeBlock.BITES, 1), 3);
                    Block.dropResources(state, world, pos);
                    Containers.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack((ItemLike) ModItems.CAKE_SLICE.get()));
                    world.playSound((Player)null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                    event.setCanceled(true);
                }

                if (block == Blocks.CAKE) {
                    int bites = (Integer)state.getValue(CakeBlock.BITES);
                    if (bites < 6) {
                        world.setBlock(pos, (BlockState)state.setValue(CakeBlock.BITES, bites + 1), 3);
                    } else {
                        world.removeBlock(pos, false);
                    }

                    Containers.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack((ItemLike)ModItems.CAKE_SLICE.get()));
                    world.playSound((Player)null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                    event.setCanceled(true);
                }

            }
        }
    }
}
