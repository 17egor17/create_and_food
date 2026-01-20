package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.damage.CAFDamageSource;
import net.egorplaytv.caf.item.entity.custom.MIEntity;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
        pTooltip.add(TextUtils.getToolTipTranslation("doesnt_despawn"));
        pTooltip.add(TextUtils.getToolTipTranslation("degrees", meltingPoint));
        pTooltip.add(TextUtils.getToolTipTranslation("ingot.degrees", 24 + getDeg(pStack)));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (pIsSelected) {
            if (pEntity == null)
                return;

            if (pEntity instanceof LivingEntity entity) {
                if (pStack.getItem() instanceof MetalItem metal) {
                    int deg = metal.getDeg(pStack);

                    if (deg >= 5000) {
                        ++damageTick;
                        if (damageTick >= 20) {
                            CAFDamageSource.hotMetal(entity, 20.0F);
                            damageTick = 0;
                        }
                    } else if (deg >= 1000) {
                        ++damageTick;
                        if (damageTick >= 20) {
                            CAFDamageSource.hotMetal(entity, 10.0F);
                            damageTick = 0;
                        }
                    } else if (deg >= 500) {
                        ++damageTick;
                        if (damageTick >= 20) {
                            CAFDamageSource.hotMetal(entity, 4.0F);
                            damageTick = 0;
                        }
                    } else if (deg >= 100) {
                        ++damageTick;
                        if (damageTick >= 20) {
                            CAFDamageSource.hotMetal(entity, 2.0F);
                            damageTick = 0;
                        }
                    } else if (deg >= 60) {
                        ++damageTick;
                        if (damageTick >= 20) {
                            CAFDamageSource.hotMetal(entity, 1.0F);
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
                    metal.setDeg(pStack, deg);
                }
            }
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
        if (ingot.getDeg(pStack) == 0){
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
