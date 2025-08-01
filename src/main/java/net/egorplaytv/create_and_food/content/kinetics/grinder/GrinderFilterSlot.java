package net.egorplaytv.create_and_food.content.kinetics.grinder;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.utility.VecHelper;

import net.egorplaytv.create_and_food.block.custom.GrinderBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class GrinderFilterSlot extends ValueBoxTransform {

    @Override
    public Vec3 getLocalOffset(BlockState state) {
        int offset = 6;
        if (state.getValue(GrinderBlock.HORIZONTAL_FACING) == Direction.NORTH || state.getValue(GrinderBlock.HORIZONTAL_FACING) == Direction.SOUTH)
            return VecHelper.voxelSpace(8, 14.5f, 8 + (state.getValue(GrinderBlock.HORIZONTAL_FACING) == Direction.SOUTH ? offset : -offset));
        return VecHelper.voxelSpace(8 + (state.getValue(GrinderBlock.HORIZONTAL_FACING) == Direction.EAST ? offset : -offset), 14.5f, 8);
    }

    @Override
    public void rotate(BlockState state, PoseStack ms) {
        int yRot = 180;
        TransformStack.cast(ms)
                .rotateY(yRot)
                .rotateX(90);
    }

}