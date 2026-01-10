package net.egorplaytv.caf.item.custom;

import com.google.common.collect.Sets;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier pTier, float attackDamageIn, float attackSpeedIn, Item.Properties properties) {
        super(attackDamageIn, attackSpeedIn, pTier, CAFTags.Blocks.MINEABLE_WITH_HAMMER, properties);
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(Enchantments.BLOCK_EFFICIENCY, Enchantments.SILK_TOUCH, Enchantments.UNBREAKING, Enchantments.BLOCK_FORTUNE, Enchantments.MENDING, Enchantments.KNOCKBACK);
        if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
            return true;
        } else {
            Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(Enchantments.FIRE_ASPECT, Enchantments.FISHING_LUCK);
            return !DENIED_ENCHANTMENTS.contains(enchantment) && enchantment.category.canEnchant(stack.getItem());
        }
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        boolean ret = super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        MineableHammerSelect.execute(pLevel, pPos, pEntityLiving);
        return ret;
    }

    @Mod.EventBusSubscriber(
            modid = CreateAndFood.MOD_ID,
            bus = Mod.EventBusSubscriber.Bus.FORGE
    )
    public static class HammerEvents {
        public HammerEvents(){
        }

        @SubscribeEvent
        public static void onHammerKnockback(LivingKnockBackEvent event) {
            LivingEntity attacker = event.getEntityLiving().getKillCredit();
            ItemStack toolStack = attacker != null ? attacker.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
            if (toolStack.getItem() instanceof HammerItem) {
                float f = event.getOriginalStrength();
                event.setStrength(f - 0.1F);
            }

        }
    }
}
