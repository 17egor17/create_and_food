package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.damage.CAFDamageSource;
import net.egorplaytv.caf.item.entity.custom.MIEntity;
import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalItem extends Item {
    private final int meltingPoint;
    private int tick;
    private int damageTick;
    protected int heatingSpeed;
    public static final String TAG_DEGREE = "deg";
    public static final String TAG_PREVENT_MAGNET = "PreventRemoteMovement";

    public MetalItem(int meltingPoint, MetalItem.Type type, Properties pProperties) {
        super(pProperties);
        this.meltingPoint = meltingPoint;
        switch (type) {
            case RAW, CRASHED_RAW, PIECE -> this.heatingSpeed = 2;
            case INGOT, SHEET -> this.heatingSpeed = 1;
            case NUGGET, COIN -> this.heatingSpeed = 3;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        if (pStack.getItem() instanceof MetalItem metal) {
            pTooltip.add(TextUtils.getToolTipTranslation("doesnt_despawn"));
            pTooltip.add(TextUtils.getToolTipTranslation("degrees", meltingPoint));
            pTooltip.add(TextUtils.getToolTipTranslation("ingot.degrees", metal.getDeg(pStack)));
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pIsSelected) {
            if (pEntity instanceof ServerPlayer entity) {
                if (pStack.getItem() instanceof MetalItem metal) {
                    if (!entity.gameMode.getGameModeForPlayer().isCreative()) {
                        if (entity.getItemInHand(InteractionHand.MAIN_HAND).is(metal) && metal.getDeg(pStack) >= 50
                                && !entity.getItemInHand(InteractionHand.OFF_HAND).is(CAFTags.forgeItemTag("tongs"))) {
                            CAFDamageSource.hotMetal(entity, 5.0F);
                            entity.drop(entity.getItemInHand(InteractionHand.MAIN_HAND), false, false);
                            entity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
        if (pEntity instanceof ServerPlayer entity) {
            if (pStack.getItem() instanceof MetalItem metal) {
                if (!entity.gameMode.getGameModeForPlayer().isCreative()) {
                    if (entity.getItemInHand(InteractionHand.OFF_HAND).is(metal) && metal.getDeg(pStack) >= 50
                            && !entity.getItemInHand(InteractionHand.MAIN_HAND).is(CAFTags.forgeItemTag("tongs"))) {
                        CAFDamageSource.hotMetal(entity, 5.0F);
                        entity.drop(entity.getItemInHand(InteractionHand.OFF_HAND), false, false);
                        entity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                    }
                }
            }
        }

        if (pStack.getItem() instanceof MetalItem metal) {
            int deg = metal.getDeg(pStack);

            ++tick;
            if (tick >= 200) {
                if (deg > 24) {
                    --deg;
                    tick = 0;
                }
            }
            metal.setDeg(pStack, deg);
        }
    }

    @Override
    public int getEntityLifespan(final ItemStack itemStack, final Level level) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(final Level level, final Entity location, final ItemStack stack) {
        final MIEntity entity = new MIEntity(level, location.getX(), location.getY(), location.getZ(), stack);
        entity.setDeltaMovement(location.getDeltaMovement());
        entity.setPickUpDelay(40);
        entity.getPersistentData().putBoolean(TAG_PREVENT_MAGNET, true);
        return entity;
    }

    public int getMeltingPoint() {
        return meltingPoint;
    }

    public int getHeatingSpeed() {
        return heatingSpeed;
    }

    public int getDeg(ItemStack is) {
        CompoundTag tag = is.getTag();
        return tag != null ? tag.getInt(TAG_DEGREE) : 0;
    }

    public void setDeg(ItemStack is, int degree) {
        is.getOrCreateTag().putInt(TAG_DEGREE, degree);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        MetalItem ingot = (MetalItem) pStack.getItem();
        if (ingot.getDeg(pStack) <= 24){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        MetalItem ingot = (MetalItem) pStack.getItem();
        float meltingPoint = ingot.getMeltingPoint();
        return Math.round(ingot.getDeg(pStack) * 13.0F / meltingPoint);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        MetalItem ingot = (MetalItem) pStack.getItem();
        float meltingPoint = ingot.getMeltingPoint();
        float f = Math.max(0.0F, (meltingPoint - (float)ingot.getDeg(pStack)) / meltingPoint);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowdedIn(category)){
            items.add(new ItemStack(this, 1));
        }
    }

    public enum Type {
        RAW, CRASHED_RAW, INGOT, NUGGET, SHEET, PIECE, COIN
    }
}
