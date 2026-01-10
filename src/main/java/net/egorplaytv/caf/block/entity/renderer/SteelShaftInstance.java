package net.egorplaytv.caf.block.entity.renderer;

import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.custom.CAFShaftBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class SteelShaftInstance<T extends KineticBlockEntity> extends SingleRotatingInstance<T> {

    public SteelShaftInstance(MaterialManager materialManager, T blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected BlockState getRenderedBlockState() {
        return steelShaft();
    }

    protected BlockState steelShaft() {
        return steelShaft(getRotationAxis());
    }

    public static BlockState steelShaft(Direction.Axis axis) {
        return CAFBlocks.STEEL_SHAFT.getDefaultState()
                .setValue(CAFShaftBlock.AXIS, axis);
    }
}
