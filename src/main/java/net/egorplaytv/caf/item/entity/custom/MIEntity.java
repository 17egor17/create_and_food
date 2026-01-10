package net.egorplaytv.caf.item.entity.custom;

import net.egorplaytv.caf.item.ItemEntities;
import net.egorplaytv.caf.item.custom.MeltItem;
import net.egorplaytv.caf.item.entity.CAFItemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

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

    final int x = Mth.floor(this.getX());
    final int y = Mth.floor((this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D);
    final int z = Mth.floor(this.getZ());

    BlockPos pos = new BlockPos(x, y, z);
    final BlockState state = this.level.getBlockState(pos);

    @Override
    public void tick() {
        super.tick();

        final ItemStack is = this.getItem();
        final Item gc = is.getItem();

        if (!(gc instanceof MeltItem))
            return;

        decreaseDegree(is);
    }

    private void decreaseDegree(ItemStack is) {
        int deg = is.getTag() != null ? is.getTag().getInt(TAG_DEGREE) : 0;
        int inWater = state.getFluidState().is(Fluids.WATER) ? 1 : 0;

        if (inWater == 1){
            if (deg >= 5000) {
                int degree = 25;
                deg = deg - degree;
            } else if (deg >= 1000) {
                int degree = 20;
                deg = deg - degree;
            } else if (deg >= 500) {
                int degree = 15;
                deg = deg - degree;
            } else if (deg >= 100) {
                int degree = 10;
                deg = deg - degree;
            } else if (deg >= 60) {
                int degree = 5;
                deg = deg - degree;
            } else if (deg > 0) {
                --deg;
            }
            is.getOrCreateTag().putInt(TAG_DEGREE, deg);
        } else {
            ++tick;
            if (tick >= 200) {
                if (deg > 0) {
                    --deg;
                    tick = 0;
                }
            }
            is.getOrCreateTag().putInt(TAG_DEGREE, deg);
        }

        if (!level.isClientSide()){
            ItemStack newItem = is.copy();

            this.setItem(newItem);

            if (is.getItem() != newItem.getItem()
                    && this.getPersistentData().contains(MeltItem.TAG_PREVENT_MAGNET)) {
                this.getPersistentData().remove(MeltItem.TAG_PREVENT_MAGNET);
            }
        }
    }
}
