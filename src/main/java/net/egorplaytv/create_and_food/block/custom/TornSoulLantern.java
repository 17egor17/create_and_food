package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.praperties.LanternAttachType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TornSoulLantern extends LanternBlock {
    protected static final VoxelShape FLOOR = Shapes.or(Block.box(5D, 0D, 5D, 11D, 10D, 11D),
            Block.box(4D, 1D, 4D, 12D, 9D, 12D));
    protected static final VoxelShape HANGING = Shapes.or(Block.box(5D, 1D, 5D, 11D, 11D, 11D),
            Block.box(4D, 2D, 4D, 12D, 10D, 12D),
            Block.box(6.5D, 0D, 6.5D, 9.5D, 2D, 9.5D));
    private VoxelShape TO_SOUTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.1875, 0.0625, 0.75, 0.6875, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.6875, 0.125, 0.6875, 0.75, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.125, 0.125, 0.6875, 0.1875, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.875, 0.625, 0.6875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.625, 0.5625, 0.5625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.6875, 0.5625, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.875, 0, 0.5625, 1, 0.6875), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_NORTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.1875, 0.4375, 0.75, 0.6875, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.6875, 0.5, 0.6875, 0.75, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.125, 0.5, 0.6875, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0, 0.625, 0.6875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.125, 0.5625, 0.5625, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.1875, 0.5625, 1, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.875, 0.3125, 0.5625, 1, 1), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_WEST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.1875, 0.25, 0.9375, 0.6875, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.6875, 0.3125, 0.875, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.125, 0.3125, 0.875, 0.1875, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0.375, 0.125, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.4375, 0.4375, 0.375, 0.5625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.4375, 0.3125, 1, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.875, 0.4375, 1, 1, 0.5625), BooleanOp.OR);

        return shape;
    }
    private VoxelShape TO_EAST(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.1875, 0.25, 0.5625, 0.6875, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.6875, 0.3125, 0.5, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.125, 0.3125, 0.5, 0.1875, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.3125, 0.375, 1, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.4375, 0.4375, 0.875, 0.5625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.5625, 0.4375, 0.8125, 1, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.4375, 0.6875, 1, 0.5625), BooleanOp.OR);

        return shape;
    }
    public TornSoulLantern(Properties pProperties) {
        super(pProperties);
    }

    private VoxelShape getVoxelShape(BlockState pState) {
        LanternAttachType lanternattachtype = pState.getValue(ATTACHMENT);
        if (lanternattachtype == LanternAttachType.FLOOR){
            return FLOOR;
        } else if (lanternattachtype == LanternAttachType.HANGING) {
            return HANGING;
        } else if (lanternattachtype == LanternAttachType.NORTH) {
            return TO_NORTH();
        } else if (lanternattachtype == LanternAttachType.SOUTH) {
            return TO_SOUTH();
        } else {
            return lanternattachtype == LanternAttachType.EAST ? TO_EAST() : TO_WEST();
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape(pState);
    }
}