package net.egorplaytv.caf.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import net.egorplaytv.caf.block.custom.connect.CAFPartialModels;
import net.egorplaytv.caf.block.entity.custom.VaseBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class VaseBlockRenderer implements BlockEntityRenderer<VaseBlockEntity> {
    public VaseBlockRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(VaseBlockEntity pBlockEntity, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        BlockState state = pBlockEntity.getBlockState();
        float level = pBlockEntity.getFillState();


        SuperByteBuffer fluid = CachedBufferer.partial(CAFPartialModels.FLUID_BOX, state);

        fluid.scale(1, level, 1);

        ms.pushPose();
        fluid.light(light).renderInto(ms, buffer.getBuffer(RenderType.cutout()));
        ms.popPose();
    }
}
