package net.egorplaytv.create_and_food.integration.animations;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.connect.ModPartialModels;
import net.minecraft.util.Mth;

public class AnimatedBlender extends AnimatedKinetics {
    @Override
    public void draw(PoseStack matrixStack, int xOffset, int yOffset) {
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 200);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(22.5f));
        int scale = 23;

        blockElement(ModPartialModels.STEEL_COGWHEEL)
                .rotateBlock(0, getCurrentAngle() * 2, 0)
                .atLocal(0, 0, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(ModBlocks.MECHANICAL_BLENDER.getDefaultState())
                .atLocal(0, 0, 0)
                .scale(scale)
                .render(matrixStack);

        float animation = ((Mth.sin(AnimationTickHolder.getRenderTime() / 32f) + 1) / 5) + .5f;

        blockElement(ModPartialModels.MECHANICAL_BLENDER_POLE)
                .atLocal(0, animation, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(ModPartialModels.MECHANICAL_BLENDER_HEAD)
                .rotateBlock(0, getCurrentAngle() * 4, 0)
                .atLocal(0, animation, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(AllBlocks.BASIN.getDefaultState())
                .atLocal(0, 1.65, 0)
                .scale(scale)
                .render(matrixStack);

        matrixStack.popPose();
    }

}
