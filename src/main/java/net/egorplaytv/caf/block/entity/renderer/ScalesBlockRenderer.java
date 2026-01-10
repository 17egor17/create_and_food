package net.egorplaytv.caf.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.egorplaytv.caf.block.custom.ScalesBlock;
import net.egorplaytv.caf.block.custom.connect.ModPartialModels;
import net.egorplaytv.caf.block.entity.custom.ScalesBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ScalesBlockRenderer<BE extends ScalesBlockEntity> extends SafeBlockEntityRenderer<BE> {
    public ScalesBlockRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    protected void renderSafe(BE be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        BlockState state = be.getBlockState();
        Direction facing = state.getValue(ScalesBlock.FACING);
        Direction direction = facing.getOpposite();
        ItemStack stack = be.getStoredItem();
        int posLong = (int) be.getBlockPos().asLong();

        float ticks = be.weighingTicks * -0.01F;

        SuperByteBuffer scales = CachedBufferer.partial(ModPartialModels.SCALES_HEAD, state);

        if (be.isWeighing) {
            scales.translate(0, ticks, 0);
        } else {
            scales.translate(0, ticks, 0);
        }


        scales.light(light).renderInto(ms, buffer.getBuffer(RenderType.cutout()));

        if (!stack.isEmpty()) {
            ms.pushPose();
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            boolean isBlockItem = itemRenderer.getModel(stack, be.getLevel(), null, 0).isGui3d();
            if (isBlockItem) {
                if (be.weighingTicks == be.maxWeighingTicks) {
                    ms.translate(0.5, 0.67, 0.5);
                } else {
                    ms.translate(0.5, 0.7, 0.5);
                }
                float f = -direction.toYRot();
                ms.mulPose(Vector3f.YP.rotationDegrees(f));
                ms.scale(0.8F, 0.8F, 0.8F);
            } else {
                if (be.weighingTicks == be.maxWeighingTicks) {
                    ms.translate(0.5, 0.49, 0.5);
                } else {
                    ms.translate(0.5, 0.52, 0.5);
                }
                float f = -direction.toYRot();
                ms.mulPose(Vector3f.YP.rotationDegrees(f));
                ms.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                ms.scale(0.6F, 0.6F, 0.6F);
            }

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED,
                    light, overlay, ms, buffer, posLong);
            ms.popPose();
        }
    }
}
