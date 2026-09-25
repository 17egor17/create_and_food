package net.egorplaytv.caf.block.entity.renderer;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import net.egorplaytv.caf.block.custom.connect.CAFPartialModels;
import net.egorplaytv.caf.energy.KineticGeneratorBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class KineticGeneratorInstance extends SingleRotatingInstance<KineticGeneratorBlockEntity> {
    public KineticGeneratorInstance(MaterialManager materialManager, KineticGeneratorBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        Direction direction = getShaftDirection();
        return getRotatingMaterial().getModel(CAFPartialModels.KINETIC_GENERATOR_SHAFT, blockState, direction);
    }

    protected Direction getShaftDirection() {
        return blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
    }
}
