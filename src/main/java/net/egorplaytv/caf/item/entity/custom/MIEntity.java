package net.egorplaytv.caf.item.entity.custom;

import net.egorplaytv.caf.item.ItemEntities;
import net.egorplaytv.caf.item.custom.MetalItem;
import net.egorplaytv.caf.item.custom.interfaces.IMetalItem;
import net.egorplaytv.caf.item.entity.CAFItemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MIEntity extends CAFItemEntity {
    private int tick;
    public static final String TAG_DEGREE = "deg";
    public MIEntity(EntityType<? extends MIEntity> type, Level level) {
        super(type, level);
    }

    public MIEntity(Level level, double x, double y, double z, ItemStack is) {
        super(ItemEntities.MELT_ITEM, level, x, y, z, is);
        this.setExtendedLifetime();
    }

    @Override
    public void tick() {
        super.tick();

        final ItemStack is = this.getItem();
        final Item gc = is.getItem();

        if (!(gc instanceof IMetalItem))
            return;

        decreaseDegree((IMetalItem) gc, is);
    }

    private void decreaseDegree(IMetalItem gc, ItemStack is) {
        final int x = Mth.floor(this.getX());
        final int y = Mth.floor((this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D);
        final int z = Mth.floor(this.getZ());

        BlockPos pos = new BlockPos(x, y, z);
        final BlockState state = this.level.getBlockState(pos);

        final float cooling = gc.getCoolingFluid(state, level, pos);

        float deg = is.getTag() != null ? is.getTag().getFloat(TAG_DEGREE) : 0;

        if (cooling == 1) {
            if (deg >= 5000) {
                deg -= 25.11F;
            } else if (deg >= 1000) {
                deg -= 20.11F;
            } else if (deg >= 500) {
                deg -= 15.11F;
            } else if (deg >= 100) {
                deg -= 10.11F;
            } else if (deg >= 60) {
                deg -= 5.11F;
            } else if (deg > 30) {
                deg -= 1.11F;
            } else if (deg > 25) {
                deg -= 0.1F;
            } else if (deg > 24) {
                deg -= 0.01F;
            }
            is.getOrCreateTag().putFloat(TAG_DEGREE, deg);
        } else {
            if (tick >= 200) {
                if (deg > 30) {
                    deg -= 1;
                    tick = 0;
                } else if (deg > 25) {
                    deg -= 0.1F;
                    tick = 0;
                } else if (deg > 24) {
                    deg -= 0.01F;
                    tick = 0;
                }
            }
            is.getOrCreateTag().putFloat(TAG_DEGREE, deg);
        }

        if (!level.isClientSide()){
            ItemStack newItem = is.copy();

            this.setItem(newItem);

            if (is.getItem() != newItem.getItem()
                    && this.getPersistentData().contains(MetalItem.TAG_PREVENT_MAGNET)) {
                this.getPersistentData().remove(MetalItem.TAG_PREVENT_MAGNET);
            }
        }
    }
}
