package net.egorplaytv.create_and_food.block.entity.custom;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import net.egorplaytv.create_and_food.block.custom.connect.SlidingDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SlidingDoorBlockEntity extends SmartBlockEntity {

    public LerpedFloat animation;
    int bridgeTicks;
    public boolean deferUpdate;

    public SlidingDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        animation = LerpedFloat.linear()
                .startWithValue(isOpen(state) ? 1 : 0);
    }

    @Override
    public void tick() {
        if (deferUpdate && !level.isClientSide()) {
            deferUpdate = false;
            BlockState blockState = getBlockState();
            blockState.neighborChanged(level, worldPosition, Blocks.AIR, worldPosition, false);
        }

        super.tick();
        boolean open = isOpen(getBlockState());
        boolean wasSettled = animation.settled();
        animation.chase(open ? 1 : 0, .15f, LerpedFloat.Chaser.LINEAR);
        animation.tickChaser();

        if (level.isClientSide()) {
            if (bridgeTicks < 2 && open)
                bridgeTicks++;
            else if (bridgeTicks > 0 && !open && isVisible(getBlockState()))
                bridgeTicks--;
            return;
        }

        if (!open && !wasSettled && animation.settled() && !isVisible(getBlockState()))
            showBlockModel();
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox().inflate(1);
    }

    protected boolean isVisible(BlockState state) {
        return state.getOptionalValue(SlidingDoorBlock.VISIBLE)
                .orElse(true);
    }

    public boolean shouldRenderSpecial(BlockState state) {
        return !isVisible(state) || bridgeTicks != 0;
    }

    protected void showBlockModel() {
        level.setBlock(worldPosition, getBlockState().setValue(SlidingDoorBlock.VISIBLE, true), 3);
        level.playSound(null, worldPosition, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, .5f, 1);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    public static boolean isOpen(BlockState state) {
        return state.getOptionalValue(DoorBlock.OPEN)
                .orElse(false);
    }

}
