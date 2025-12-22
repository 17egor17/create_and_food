package net.egorplaytv.create_and_food.block.entity.renderer;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SteelShaftRenderer<T extends KineticBlockEntity> extends KineticBlockEntityRenderer<T> {

    public SteelShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(KineticBlockEntity be) {
        return steelShaft(getRotationAxisOf(be));
    }

    public static BlockState steelShaft(Direction.Axis axis) {
        return CAFBlocks.STEEL_SHAFT.getDefaultState()
                .setValue(BlockStateProperties.AXIS, axis);
    }

}
