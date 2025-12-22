package net.egorplaytv.create_and_food.item.custom;

import net.egorplaytv.create_and_food.damage.CAFDamageSource;
import net.egorplaytv.create_and_food.item.entity.custom.IIEntity;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class IngotItem extends Item {
    private final int degrees;
    protected Level level;
    private int tick;
    private int damageTick;
    public static final String TAG_DEGREE = "deg";
    public static final String TAG_PREVENT_MAGNET = "PreventRemoteMovement";

    public IngotItem(int deg, Properties pProperties) {
        super(pProperties.setNoRepair());
        this.degrees = deg;
    }


    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        int deg = getDeg(pStack);
        if (deg >= 5000) {
            ++damageTick;
            if (damageTick >= 20) {
                pEntity.hurt(CAFDamageSource.HOT_METAL, 20);
                damageTick = 0;
            }
        } else if (deg >= 1000) {
            ++damageTick;
            if (damageTick >= 20) {
                pEntity.hurt(CAFDamageSource.HOT_METAL, 10);
                damageTick = 0;
            }
        } else if (deg >= 500) {
            ++damageTick;
            if (damageTick >= 20) {
                pEntity.hurt(CAFDamageSource.HOT_METAL, 4);
                damageTick = 0;
            }
        } else if (deg >= 100) {
            ++damageTick;
            if (damageTick >= 20) {
                pEntity.hurt(CAFDamageSource.HOT_METAL, 2);
                damageTick = 0;
            }
        } else if (deg >= 60) {
            ++damageTick;
            if (damageTick >= 20) {
                pEntity.hurt(CAFDamageSource.HOT_METAL, 1);
                damageTick = 0;
            }
        }
        ++tick;
        if (tick >= 200) {
            if (deg > 0) {
                --deg;
                tick = 0;
            }
        }
        setDeg(pStack, deg);
    }

    public int getDegrees() {
        return degrees;
    }

    public int getDeg(ItemStack is) {
        CompoundTag tag = is.getTag();
        return tag != null ? tag.getInt(TAG_DEGREE) : 0;
    }

    public void setDeg(ItemStack is, int degree) {
        is.getOrCreateTag().putInt(TAG_DEGREE, degree);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        pTooltip.add(TextUtils.getToolTipTranslation("doesnt_despawn"));
        pTooltip.add(TextUtils.getToolTipTranslation("degrees", degrees));
        pTooltip.add(TextUtils.getToolTipTranslation("ingot.degrees", 24 + getDeg(pStack)));

        super.appendHoverText(pStack, pLevel, pTooltip, pIsAdvanced);
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
        final IIEntity entity = new IIEntity(level, location.getX(), location.getY(), location.getZ(), stack);
        entity.setDeltaMovement(location.getDeltaMovement());
        entity.setPickUpDelay(40);
        entity.getPersistentData().putBoolean(TAG_PREVENT_MAGNET, true);
        return entity;
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowdedIn(category)){
            items.add(new ItemStack(this, 1));
        }
    }
}

