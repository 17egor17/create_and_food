package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.config.CAFConfigs;
import net.egorplaytv.caf.config.DegreeUnits;
import net.egorplaytv.caf.damage.CAFDamageSource;
import net.egorplaytv.caf.datagen.custom.ModItemModelsProperties;
import net.egorplaytv.caf.item.custom.interfaces.IMetalItem;
import net.egorplaytv.caf.item.entity.custom.MIEntity;
import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.units.degree.CAFDegreeUnits;
import net.egorplaytv.caf.util.Metals;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalItem extends Item implements IMetalItem {
    private final CAFDegreeUnits meltingPoint;
    protected int heatingSpeed;
    protected Metals metalType;
    private int radiationTick;
    public static final String TAG_DEGREE = "deg";
    public static final String TAG_PREVENT_MAGNET = "PreventRemoteMovement";
    private boolean defaultDegSet = true;

    public MetalItem(float meltingPoint, MetalItem.Type type, Metals metalType, Properties pProperties) {
        super(pProperties);
        this.meltingPoint = new CAFDegreeUnits(meltingPoint);
        switch (type) {
            case RAW, CRASHED_RAW, PIECE -> this.heatingSpeed = 2;
            case INGOT, SHEET -> this.heatingSpeed = 1;
            case NUGGET, COIN, DUST -> this.heatingSpeed = 3;
        }
        this.metalType = metalType;
        ModItemModelsProperties.registerMetalItem(this, metalType);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        if (pStack.getItem() instanceof MetalItem metal) {
            pTooltip.add(TextUtils.getToolTipTranslation("doesnt_despawn"));
            if (metal.metalType == Metals.URANIUM)
                pTooltip.add(TextUtils.getToolTipTranslation("ingot.radiation"));
            if (CAFConfigs.common().gameSettings.unitsOfMeasurement.get() == DegreeUnits.DEGREES_CELSIUS) {
                pTooltip.add(TextUtils.getToolTipTranslation("degreesC", meltingPoint.getDegreeInC()));
            } else if (CAFConfigs.common().gameSettings.unitsOfMeasurement.get() == DegreeUnits.DEGREES_FAHRENHEIT) {
                pTooltip.add(TextUtils.getToolTipTranslation("degreesF", meltingPoint.getDegreeInF()));
            } else {
                pTooltip.add(TextUtils.getToolTipTranslation("degreesK", meltingPoint.getDegreeInK()));
            }

            if (CAFConfigs.common().gameSettings.unitsOfMeasurement.get() == DegreeUnits.DEGREES_CELSIUS) {
                pTooltip.add(TextUtils.getToolTipTranslation("ingot.degreesC", metal.getDegUnits(pStack).getDegreeInC()));
            } else if (CAFConfigs.common().gameSettings.unitsOfMeasurement.get() == DegreeUnits.DEGREES_FAHRENHEIT) {
                pTooltip.add(TextUtils.getToolTipTranslation("ingot.degreesF", metal.getDegUnits(pStack).getDegreeInF()));
            } else {
                pTooltip.add(TextUtils.getToolTipTranslation("ingot.degreesK", metal.getDegUnits(pStack).getDegreeInK()));
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.getItem() instanceof MetalItem metal) {
            if (metal.metalType == Metals.URANIUM) {
                if (pEntity instanceof ServerPlayer entity) {
                    ++radiationTick;
                    if (radiationTick >= 80) {
                        CAFDamageSource.radiation(entity, 0.1F);
                        radiationTick = 0;
                    }
                }
            }
        }

        if (pIsSelected) {
            if (pEntity instanceof ServerPlayer entity) {
                if (pStack.getItem() instanceof MetalItem metal) {
                    if (!entity.gameMode.getGameModeForPlayer().isCreative()) {
                        if (entity.getItemInHand(InteractionHand.MAIN_HAND).is(metal) && metal.getDeg(pStack) >= 50
                                && !entity.getItemInHand(InteractionHand.OFF_HAND).is(CAFTags.Items.forgeTag("tongs"))) {
                            entity.drop(entity.getItemInHand(InteractionHand.MAIN_HAND), false, false);
                            entity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                            CAFDamageSource.hotMetal(entity, 5.0F);
                        }
                    }
                }
            }
        }
        if (pEntity instanceof ServerPlayer entity) {
            if (pStack.getItem() instanceof MetalItem metal) {
                if (!entity.gameMode.getGameModeForPlayer().isCreative()) {
                    if (entity.getItemInHand(InteractionHand.OFF_HAND).is(metal) && metal.getDeg(pStack) >= 50
                            && !entity.getItemInHand(InteractionHand.MAIN_HAND).is(CAFTags.Items.forgeTag("tongs"))) {
                        entity.drop(entity.getItemInHand(InteractionHand.OFF_HAND), false, false);
                        entity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                        CAFDamageSource.hotMetal(entity, 5.0F);
                    }
                }
            }
        }

        tickInInventory(pStack, pLevel);
    }

    @Override
    public void tickInInventory(ItemStack stack, Level level) {
        if (stack.getItem() instanceof MetalItem metal) {
            float deg = metal.getDeg(stack);

            if (defaultDegSet && (metal.getDeg(stack) == 0)){
                metal.setDeg(stack, 24);
                defaultDegSet = false;
            }

            if (deg > 24)
                deg -= 0.01F;
            else if (deg < 24) {
                deg += 0.01F;
            }

            metal.setDeg(stack, deg);
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

    @Override
    public CAFDegreeUnits getMeltingPoint() {
        return meltingPoint;
    }


    public int getHeatingSpeed() {
        return heatingSpeed;
    }


    public CAFDegreeUnits getDegUnits(ItemStack is) {
        return new CAFDegreeUnits(getDeg(is));
    }

    public void setDeg(ItemStack is, CAFDegreeUnits degree) {
        this.setDeg(is, degree.getDegreeInC());
    }

    public float getDeg(ItemStack is) {
        CompoundTag tag = is.getTag();
        return tag != null ? (Math.round(tag.getFloat(TAG_DEGREE) * 100) / 100F) : 0;
    }

    public void setDeg(ItemStack is, float degree) {
        is.getOrCreateTag().putFloat(TAG_DEGREE, degree);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        MetalItem ingot = (MetalItem) pStack.getItem();
        return ingot.getDeg(pStack) > 24;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        MetalItem ingot = (MetalItem) pStack.getItem();
        CAFDegreeUnits meltingPoint = ingot.getMeltingPoint();
        float deg = ingot.getDeg(pStack);
        if (deg > meltingPoint.getDegreeInC()) {
            return Math.round(meltingPoint.getDegreeInC() * 13.0F / meltingPoint.getDegreeInC());
        } else {
            return Math.round(deg * 13.0F / meltingPoint.getDegreeInC());
        }
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        MetalItem metal = (MetalItem) pStack.getItem();
        CAFDegreeUnits meltingPoint = metal.getMeltingPoint();
        float f = Math.max(0.0F, (meltingPoint.getDegreeInC() - metal.getDeg(pStack)) / meltingPoint.getDegreeInC());
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (this.allowdedIn(category)){
            items.add(new ItemStack(this, 1));
        }
    }

    @Override
    public float getCoolingFluid(BlockState state, @Nullable Level level, @Nullable BlockPos pos) {
        return state.getFluidState().is(Fluids.WATER) ? 1 : 0;
    }

    public enum Type {
        RAW, CRASHED_RAW, INGOT, NUGGET, SHEET, PIECE, COIN, DUST
    }
}
