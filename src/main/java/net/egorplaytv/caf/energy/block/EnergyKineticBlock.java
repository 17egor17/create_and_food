package net.egorplaytv.caf.energy.block;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyKineticBlock extends KineticBlock {
    public EnergyKineticBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return null;
    }
}
