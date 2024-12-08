package net.egorplaytv.create_and_food.block.custom.connect;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CTFramedWall extends Block {

    private boolean visible;

    public static CTFramedWall deprecated(Properties p_i48440_1_) {
        return new CTFramedWall(p_i48440_1_, false);
    }

    public CTFramedWall(Properties p_i48440_1_) {
        this(p_i48440_1_, true);
    }

    public CTFramedWall(Properties p_i48440_1_, boolean visible) {
        super(p_i48440_1_);
        this.visible = visible;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (visible)
            super.fillItemCategory(pCategory, pItems);
    }

}
