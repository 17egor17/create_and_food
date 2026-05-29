package net.egorplaytv.caf.block.custom.combi_steamer;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.egorplaytv.caf.block.praperties.CAFBlockStateProperties;
import net.egorplaytv.caf.block.praperties.CombiSteamerBaseBlockType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CombiSteamerCasingBlock extends CasingBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CombiSteamerBaseBlockType> TYPE = CAFBlockStateProperties.CSType;
    public static final BooleanProperty COMPLETED = CAFBlockStateProperties.COMPLETED;

    public CombiSteamerCasingBlock(Properties properties) {
        super(properties);
        this.defaultBlockState().setValue(TYPE, CombiSteamerBaseBlockType.DEFAULT)
                .setValue(COMPLETED, Boolean.valueOf(false));
    }

    private static final VoxelShape SHAPE = box(0,0,0,16,16,16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        shouldBeCompleted(pState, pLevel, pPos);
    }

    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();

        return beCompleted(state, world, pos);
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    private void shouldBeCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockPos controllerPos1 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        BlockState controllerState1 = pLevel.getBlockState(controllerPos1);
        BlockPos controllerPos2 = new BlockPos(pPos.getX() - 1, pPos.getY() - 1, pPos.getZ());
        BlockState controllerState2 = pLevel.getBlockState(controllerPos2);
        BlockPos controllerPos3 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        BlockState controllerState3 = pLevel.getBlockState(controllerPos3);
        BlockPos controllerPos4 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ() - 1);
        BlockState controllerState4 = pLevel.getBlockState(controllerPos4);
        BlockPos controllerPos5 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        BlockState controllerState5 = pLevel.getBlockState(controllerPos5);
        BlockPos controllerPos6 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        BlockState controllerState6 = pLevel.getBlockState(controllerPos6);
        BlockPos controllerPos7 = new BlockPos(pPos.getX() + 1, pPos.getY() - 1, pPos.getZ());
        BlockState controllerState7 = pLevel.getBlockState(controllerPos7);
        BlockPos controllerPos8 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ() + 1);
        BlockState controllerState8 = pLevel.getBlockState(controllerPos8);
        BlockPos controllerPos9 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        BlockState controllerState9 = pLevel.getBlockState(controllerPos9);

        if (controllerState1.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState1.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState1.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState2.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState2.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState2.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState3.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState3.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState3.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState4.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState4.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState4.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState5.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState5.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState5.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState6.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState6.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState6.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState7.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState7.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState7.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState8.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState8.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState8.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState9.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState9.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState9.getValue(CombiSteamerController.FACING);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value))
                        .setValue(FACING, dir), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else {
            pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
        }
    }

    private BlockState beCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockPos controllerPos1 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
        BlockState controllerState1 = pLevel.getBlockState(controllerPos1);
        BlockPos controllerPos2 = new BlockPos(pPos.getX() - 1, pPos.getY() - 1, pPos.getZ());
        BlockState controllerState2 = pLevel.getBlockState(controllerPos2);
        BlockPos controllerPos3 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ());
        BlockState controllerState3 = pLevel.getBlockState(controllerPos3);
        BlockPos controllerPos4 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ() - 1);
        BlockState controllerState4 = pLevel.getBlockState(controllerPos4);
        BlockPos controllerPos5 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
        BlockState controllerState5 = pLevel.getBlockState(controllerPos5);
        BlockPos controllerPos6 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
        BlockState controllerState6 = pLevel.getBlockState(controllerPos6);
        BlockPos controllerPos7 = new BlockPos(pPos.getX() + 1, pPos.getY() - 1, pPos.getZ());
        BlockState controllerState7 = pLevel.getBlockState(controllerPos7);
        BlockPos controllerPos8 = new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ() + 1);
        BlockState controllerState8 = pLevel.getBlockState(controllerPos8);
        BlockPos controllerPos9 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
        BlockState controllerState9 = pLevel.getBlockState(controllerPos9);

        if (controllerState1.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState1.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState1.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState2.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState2.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState2.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState3.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState3.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState3.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState4.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState4.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState4.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState5.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState5.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState5.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState6.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState6.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState6.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState7.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState7.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState7.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState8.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState8.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState8.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState9.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState9.getValue(CombiSteamerController.COMPLETED);
                Direction dir = controllerState9.getValue(CombiSteamerController.FACING);
                return pState.setValue(COMPLETED, Boolean.valueOf(value)).setValue(FACING, dir);
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else {
            return pState.setValue(COMPLETED, Boolean.valueOf(false));
        }
    }

    public boolean getComplete(BlockState pState) {
        return pState.getValue(COMPLETED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, COMPLETED, TYPE);
    }
}
