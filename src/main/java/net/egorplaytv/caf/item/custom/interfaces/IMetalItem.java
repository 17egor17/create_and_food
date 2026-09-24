package net.egorplaytv.caf.item.custom.interfaces;

import net.egorplaytv.caf.units.degree.CAFDegreeUnits;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
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

    /**
     * This parameter determines the melting point of the metal.
     *
     * @return The melting point of the metal in {@link CAFDegreeUnits}
     */
    CAFDegreeUnits getMeltingPoint();

    /**
     * This method is called when the item is in the inventory of a block entity.
     * @param stack An item located in the inventory of an entity block.
     */

    void tickInInventory(ItemStack stack, Level level);
}
