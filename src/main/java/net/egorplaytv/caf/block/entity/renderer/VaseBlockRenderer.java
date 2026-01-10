package net.egorplaytv.caf.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.egorplaytv.caf.block.custom.connect.ModPartialModels;
import net.egorplaytv.caf.block.entity.custom.VaseBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class VaseBlockRenderer<BE extends VaseBlockEntity> extends SafeBlockEntityRenderer<BE> {
    public VaseBlockRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    protected void renderSafe(BE be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        BlockState state = be.getBlockState();
        boolean isEmpty = be.FLUID_TANK.isEmpty();
        int level;
        if (isEmpty) {
            level = 0;
        } else {
            level = 1;
        }


        SuperByteBuffer fluid = CachedBufferer.partial(ModPartialModels.FLUID_BOX, state);

        fluid.scale(1 , level, 1);

        ms.pushPose();
        fluid.light(light).renderInto(ms, buffer.getBuffer(RenderType.cutout()));
        ms.popPose();
    }
}
