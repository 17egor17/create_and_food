package net.egorplaytv.caf.block.custom.combi_steamer;

import net.egorplaytv.caf.block.entity.custom.combi_steamer.CombiSteamerEnergyCommunicationBlockEntity;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.egorplaytv.caf.block.pattern.interfaces.ICommunicationBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CombiSteamerEnergyBlock extends CombiSteamerBaseBlock implements ICommunicationBlock {
    public CombiSteamerEnergyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        shouldBeCompleted(pState, pLevel, pPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState state = super.getStateForPlacement(pContext);
        BlockPos pos = pContext.getClickedPos();
        Level world = pContext.getLevel();

        return beCompleted(state, world, pos);
    }

    @Override
    public boolean isEnergyCommunication() {
        return true;
    }

    @Override
    public boolean isFluidCommunication() {
        return false;
    }

    @Override
    public boolean isController() {
        return false;
    }

    private void shouldBeCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockPos controllerPos1 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() - 1);
        BlockPos controllerPos2 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1);
        BlockPos controllerPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() - 1);
        BlockPos controllerPos4 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() + 1);

        BlockState controllerState1 = pLevel.getBlockState(controllerPos1);
        BlockState controllerState2 = pLevel.getBlockState(controllerPos2);
        BlockState controllerState3 = pLevel.getBlockState(controllerPos3);
        BlockState controllerState4 = pLevel.getBlockState(controllerPos4);

        if (controllerState1.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState1.getValue(CombiSteamerController.COMPLETED);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState2.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState2.getValue(CombiSteamerController.COMPLETED);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState3.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState3.getValue(CombiSteamerController.COMPLETED);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (controllerState4.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState4.getValue(CombiSteamerController.COMPLETED);
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(value)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else {
            pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
        }
    }

    private BlockState beCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockPos controllerPos1 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() - 1);
        BlockPos controllerPos2 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1);
        BlockPos controllerPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() - 1);
        BlockPos controllerPos4 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() + 1);

        BlockState controllerState1 = pLevel.getBlockState(controllerPos1);
        BlockState controllerState2 = pLevel.getBlockState(controllerPos2);
        BlockState controllerState3 = pLevel.getBlockState(controllerPos3);
        BlockState controllerState4 = pLevel.getBlockState(controllerPos4);

        if (controllerState1.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState1.getValue(CombiSteamerController.COMPLETED);
                return pState.setValue(COMPLETED, Boolean.valueOf(value));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState2.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState2.getValue(CombiSteamerController.COMPLETED);
                return pState.setValue(COMPLETED, Boolean.valueOf(value));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState3.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState3.getValue(CombiSteamerController.COMPLETED);
                return pState.setValue(COMPLETED, Boolean.valueOf(value));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (controllerState4.getBlock() instanceof CombiSteamerController block) {
            if (block.isController()) {
                boolean value = controllerState4.getValue(CombiSteamerController.COMPLETED);
                return pState.setValue(COMPLETED, Boolean.valueOf(value));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else {
            return pState.setValue(COMPLETED, Boolean.valueOf(false));
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (getComplete(pState))
            return new CombiSteamerEnergyCommunicationBlockEntity(pPos, pState);
        return null;
    }
}