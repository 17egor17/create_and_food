package net.egorplaytv.caf.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.egorplaytv.caf.block.custom.WarpedCuttingBoardBlock;
import net.egorplaytv.caf.block.entity.custom.WarpedCuttingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class WarpedCuttingBoardRenderer implements BlockEntityRenderer<WarpedCuttingBoardBlockEntity> {
    public WarpedCuttingBoardRenderer(BlockEntityRendererProvider.Context context){
    }

    public void render(WarpedCuttingBoardBlockEntity cuttingBoardEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Direction direction = ((Direction)cuttingBoardEntity.getBlockState().getValue(WarpedCuttingBoardBlock.FACING)).getOpposite();
        ItemStack boardStack = cuttingBoardEntity.getStoredItem();
        int posLong = (int)cuttingBoardEntity.getBlockPos().asLong();
        if (!boardStack.isEmpty()) {
            poseStack.pushPose();
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            boolean isBlockItem = itemRenderer.getModel(boardStack, cuttingBoardEntity.getLevel(), (LivingEntity)null, 0).isGui3d();
            if (cuttingBoardEntity.isItemCarvingBoard()) {
                this.renderItemCarved(poseStack, direction, boardStack);
            } else if (isBlockItem) {
                this.renderBlock(poseStack, direction);
            } else {
                this.renderItemLayingDown(poseStack, direction);
            }

            Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, buffer, posLong);
            poseStack.popPose();
        }

    }

    public void renderItemLayingDown(PoseStack matrixStackIn, Direction direction) {
        matrixStackIn.translate(0.5, 0.08, 0.5);
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        matrixStackIn.scale(0.6F, 0.6F, 0.6F);
    }

    public void renderBlock(PoseStack matrixStackIn, Direction direction) {
        matrixStackIn.translate(0.5, 0.27, 0.5);
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        matrixStackIn.scale(0.8F, 0.8F, 0.8F);
    }

    public void renderItemCarved(PoseStack matrixStackIn, Direction direction, ItemStack itemStack) {
        matrixStackIn.translate(0.5, 0.23, 0.5);
        float f = -direction.toYRot() + 180.0F;
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        Item toolItem = itemStack.getItem();
        float poseAngle;
        if (!(toolItem instanceof PickaxeItem) && !(toolItem instanceof HoeItem)) {
            if (toolItem instanceof TridentItem) {
                poseAngle = 135.0F;
            } else {
                poseAngle = 180.0F;
            }
        } else {
            poseAngle = 225.0F;
        }

        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(poseAngle));
        matrixStackIn.scale(0.6F, 0.6F, 0.6F);
    }
}
