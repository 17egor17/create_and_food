package net.egorplaytv.caf.energy.block.entity.renderer;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import net.egorplaytv.caf.block.custom.connect.CAFPartialModels;
import net.egorplaytv.caf.energy.block.entity.EnergyConvertorBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EnergyConverterInstance extends SingleRotatingInstance<EnergyConvertorBlockEntity> {
    public EnergyConverterInstance(MaterialManager materialManager, EnergyConvertorBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        Direction direction = getShaftDirection();
        return getRotatingMaterial().getModel(CAFPartialModels.ENERGY_CONVERTOR_SHAFT, blockState, direction);
    }

    protected Direction getShaftDirection() {
        return blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
    }
}
