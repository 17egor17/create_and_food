package net.egorplaytv.create_and_food.integration.animations;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.kinetics.saw.SawBlock;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.GrinderBlock;
import net.egorplaytv.create_and_food.block.custom.connect.ModPartialModels;
import net.minecraft.core.Direction;

public class AnimatedGrinder extends AnimatedKinetics {

    public void draw(PoseStack matrixStack, int xOffset, int yOffset) {
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 0);
        matrixStack.translate(0, 0, 200);
        matrixStack.translate(2, 22, 0);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(22.5f + 90));
        int scale = 25;

        blockElement(shaft(Direction.Axis.X))
                .rotateBlock(-getCurrentAngle(), 0, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(ModBlocks.MECHANICAL_GRINDER.getDefaultState()
                .setValue(GrinderBlock.HORIZONTAL_FACING, Direction.WEST))
                .rotateBlock(0, 0, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(ModPartialModels.GRINDER_BELT_ACTIVE)
                .rotateBlock(0, 90, 0)
                .scale(scale)
                .render(matrixStack);

        matrixStack.popPose();
    }

}