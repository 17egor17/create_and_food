package net.egorplaytv.caf.item.custom.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public interface IMetalItem {

    /**
     * This parameter is responsible for the fluid in which the metal can cool.
     *
     * @param state The blockstate of the block the metal is currently in.
     * @return Cooling index. Zero if the metal cannot be cooled in the current fluid.
     */

    float getCoolingFluid(BlockState state, @Nullable Level level, @Nullable BlockPos pos);
}
