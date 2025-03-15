package net.egorplaytv.create_and_food.block.entity.renderer;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.egorplaytv.create_and_food.block.custom.connect.ModPartialModels;
import net.egorplaytv.create_and_food.block.entity.custom.MechanicalBlenderBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalBlenderRenderer extends KineticBlockEntityRenderer<MechanicalBlenderBlockEntity> {
    public MechanicalBlenderRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRenderOffScreen(MechanicalBlenderBlockEntity be) {
        return true;
    }

    @Override
    protected void renderSafe(MechanicalBlenderBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {

        if (Backend.canUseInstancing(be.getLevel())) return;

        BlockState blockState = be.getBlockState();

        VertexConsumer vb = buffer.getBuffer(RenderType.solid());

        SuperByteBuffer superBuffer = CachedBufferer.partial(ModPartialModels.STEEL_COGWHEEL, blockState);
        standardKineticRotationTransform(superBuffer, be, light).renderInto(ms, vb);

        float renderedHeadOffset = be.getRenderedHeadOffset(partialTicks);
        float speed = be.getRenderedHeadRotationSpeed(partialTicks);
        float time = AnimationTickHolder.getRenderTime(be.getLevel());
        float angle = ((time * speed * 6 / 10f) % 360) / 180 * (float) Math.PI;

        SuperByteBuffer poleRender = CachedBufferer.partial(ModPartialModels.MECHANICAL_BLENDER_POLE, blockState);
        poleRender.translate(0, -renderedHeadOffset, 0)
                .light(light)
                .renderInto(ms, vb);

        VertexConsumer vbCutout = buffer.getBuffer(RenderType.cutoutMipped());
        SuperByteBuffer headRender = CachedBufferer.partial(ModPartialModels.MECHANICAL_BLENDER_HEAD, blockState);
        headRender.rotateCentered(Direction.UP, angle)
                .translate(0, -renderedHeadOffset, 0)
                .light(light)
                .renderInto(ms, vbCutout);
    }
}
