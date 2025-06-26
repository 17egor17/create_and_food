package net.egorplaytv.create_and_food.item.custom;

import net.egorplaytv.create_and_food.damage.ModDamageSource;
import net.egorplaytv.create_and_food.util.TextUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import java.util.List;

public class IngotItem extends Item {
    private final int degrees;
    protected Level level;
    private int tick;
    public static final String TAG_DEGREE = "deg";
    public static final String TAG_PREVENT_MAGNET = "PreventRemoteMovement";

    public IngotItem(int deg, Properties pProperties) {
        super(pProperties.setNoRepair());
        this.degrees = deg;
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
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        int deg = getDeg(pStack);
        if (deg >= 5000){
            pEntity.hurt(ModDamageSource.HOT_METAL, 20);
        } else if (deg >= 1000){
            pEntity.hurt(ModDamageSource.HOT_METAL, 10);
        } else if (deg >= 500){
            pEntity.hurt(ModDamageSource.HOT_METAL, 4);
        } else if (deg >= 100){
            pEntity.hurt(ModDamageSource.HOT_METAL, 2);
        } else if (deg >= 60) {
            pEntity.hurt(ModDamageSource.HOT_METAL, 1);
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

    @Override
    public int getEntityLifespan(final ItemStack itemStack, final Level level) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(Level level, Entity location, ItemStack stack) {
        ItemEntity entity = new ItemEntity(level, location.getX(), location.getY(), location.getZ(), stack){
            final int x = Mth.floor(this.getX());
            final int y = Mth.floor((this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D);
            final int z = Mth.floor(this.getZ());

            BlockPos pos = new BlockPos(x, y, z);
            final BlockState state = this.level.getBlockState(pos);

            @Override
            public void baseTick() {
                super.baseTick();

                final ItemStack is = this.getItem();
                final Item gc = is.getItem();

                if (!(gc instanceof IngotItem))
                    return;

                if (state.getBlock().defaultBlockState().getFluidState().is(Fluids.WATER)){
                    decreaseInWaterDeg(is);
                } else {
                    decreaseDeg(is);
                }
            }

            private void decreaseInWaterDeg(ItemStack is) {
                int deg = getDeg(is);
                if (deg >= 5000){
                    int degree = 25;
                    deg = deg - degree;
                } else if (deg >= 1000){
                    int degree = 20;
                    deg = deg - degree;
                } else if (deg >= 500){
                    int degree = 15;
                    deg = deg - degree;
                } else if (deg >= 100){
                    int degree = 10;
                    deg = deg - degree;
                } else if (deg >= 60) {
                    int degree = 5;
                    deg = deg - degree;
                } else if (deg > 0){
                    --deg;
                }
                setDeg(is, deg);
            }

            private void decreaseDeg(ItemStack is) {
                int deg = getDeg(is);
                ++tick;
                if (tick >= 200) {
                    if (deg > 24) {
                        --deg;
                        tick = 0;
                    }
                }
                setDeg(is, deg);
            }
        };

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
}

