package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.data.KeyData;
import net.egorplaytv.caf.event.KeyRegistry;
import net.egorplaytv.caf.item.custom.interfaces.ITickableItem;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KeyItem extends Item implements ITickableItem {


    public KeyItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        String id = KeyData.readKeyId(pStack);

        if (!id.equals(KeyData.UNBOUND)) {
            pTooltip.add(TextUtils.getToolTipTranslation("key.id", new TextComponent(id)
                    .withStyle(ChatFormatting.AQUA)));
        } else {
            pTooltip.add(TextUtils.getToolTipTranslation("key.unbound"));
        }

        if (pStack.getTag() != null && pStack.getTag().contains("keyName") && !pStack.getTag().getString("keyName").isEmpty()){
            pTooltip.add(TextUtils.getToolTipTranslation("key.named", new TextComponent(pStack.getTag().getString("keyName"))
                    .withStyle(ChatFormatting.GOLD)));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack mainHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand = pPlayer.getItemInHand(InteractionHand.OFF_HAND);

        if (mainHand.getItem() instanceof KeyItem && offHand.getItem() instanceof NameTagItem) {
            if (offHand.hasCustomHoverName()){
                mainHand.getOrCreateTag().putString("keyName", offHand.getHoverName().getString());
                offHand.shrink(1);
                return InteractionResultHolder.consume(mainHand);
            }
        } else if (mainHand.getItem() instanceof NameTagItem && offHand.getItem() instanceof KeyItem) {
            if (mainHand.hasCustomHoverName()){
                offHand.getOrCreateTag().putString("keyName", mainHand.getHoverName().getString());
                mainHand.shrink(1);
                return InteractionResultHolder.consume(offHand);
            }
        }

        if (mainHand.getItem() instanceof KeyItem && offHand.getItem() instanceof ShearsItem) {
            if (mainHand.getTag() != null && mainHand.getTag().contains("keyName")
                    && !mainHand.getTag().getString("keyName").isEmpty()) {
                ItemStack nameTag = new ItemStack(Items.NAME_TAG);
                nameTag.setCount(1);
                nameTag.setHoverName(new TextComponent(mainHand.getTag().getString("keyName")));
                ItemHandlerHelper.giveItemToPlayer(pPlayer, nameTag);
                offHand.hurtAndBreak(1, pPlayer, (u) -> u.broadcastBreakEvent(EquipmentSlot.OFFHAND));
                mainHand.getTag().putString("keyName", "");
                return InteractionResultHolder.consume(mainHand);
            }
        } else if (mainHand.getItem() instanceof ShearsItem && offHand.getItem() instanceof KeyItem) {
            if (offHand.getTag() != null && offHand.getTag().contains("keyName")
                    && !offHand.getTag().getString("keyName").isEmpty()) {
                ItemStack nameTag = new ItemStack(Items.NAME_TAG);
                nameTag.setCount(1);
                nameTag.setHoverName(new TextComponent(offHand.getTag().getString("keyName")));
                ItemHandlerHelper.giveItemToPlayer(pPlayer, nameTag);
                mainHand.hurtAndBreak(1, pPlayer, (u) -> u.broadcastBreakEvent(EquipmentSlot.OFFHAND));
                offHand.getTag().putString("keyName", "");
                return InteractionResultHolder.consume(offHand);
            }
        }


        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pLevel.isClientSide())
            return;

        tickInInventory(pStack, pLevel);
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if (pLevel.isClientSide())
            return;

        tickInInventory(pStack, pLevel);
    }

    @Override
    public void tickInInventory(ItemStack stack, Level level) {
        if (level.isClientSide())
            return;

        String id = KeyData.readKeyId(stack);

        if (id.isEmpty()) {
            KeyData.writeKeyId(stack, KeyRegistry.get(level).allocate());
        }
    }
}
