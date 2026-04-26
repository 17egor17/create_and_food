package net.egorplaytv.caf.energy.block.entity.renderer;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.egorplaytv.caf.block.custom.connect.CAFPartialModels;
import net.egorplaytv.caf.energy.block.entity.EnergyConvertorBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EnergyConverterRenderer extends KineticBlockEntityRenderer<EnergyConvertorBlockEntity> {
    public EnergyConverterRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(EnergyConvertorBlockEntity be, BlockState state) {
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return CachedBufferer.partialFacing(CAFPartialModels.ENERGY_CONVERTOR_SHAFT, state, direction);
    }
}
