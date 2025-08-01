package net.egorplaytv.create_and_food.content.kinetics.grinder;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;

import net.egorplaytv.create_and_food.block.entity.custom.GrinderBlockEntity;

public class GrinderInstance extends SingleRotatingInstance<GrinderBlockEntity> {

    public GrinderInstance(MaterialManager materialManager, GrinderBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(shaft());
    }
}