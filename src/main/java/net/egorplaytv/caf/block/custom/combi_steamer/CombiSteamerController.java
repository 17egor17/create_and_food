package net.egorplaytv.caf.block.custom.combi_steamer;

import net.egorplaytv.caf.block.entity.custom.combi_steamer.CombiSteamerBlockEntity;
import net.egorplaytv.caf.block.entity.custom.combi_steamer.CombiSteamerBlockEntityTicker;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.egorplaytv.caf.block.pattern.interfaces.ICommunicationBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CombiSteamerController extends CombiSteamerBaseBlock {
    public CombiSteamerController(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pDirection == Direction.NORTH) {
            BlockPos baseBlockPos1 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY(), pCurrentPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY(), pCurrentPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY(), pCurrentPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY() + 1, pCurrentPos.getZ() + 1);

            BlockState baseBlock1 = pLevel.getBlockState(baseBlockPos1);
            BlockState baseBlock2 = pLevel.getBlockState(baseBlockPos2);
            BlockState baseBlock3 = pLevel.getBlockState(baseBlockPos3);
            BlockState baseBlock4 = pLevel.getBlockState(baseBlockPos4);
            BlockState baseBlock5 = pLevel.getBlockState(baseBlockPos5);
            BlockState energyBlock = pLevel.getBlockState(energyBlockPos);
            BlockState communicationBlock = pLevel.getBlockState(communicationBlockPos);

            if (baseBlock1.getBlock() instanceof CombiSteamerCasingBlock && baseBlock2.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock3.getBlock() instanceof CombiSteamerCasingBlock && baseBlock4.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock5.getBlock() instanceof CombiSteamerCasingBlock && energyBlock.getBlock() instanceof CombiSteamerEnergyBlock
                    && communicationBlock.getBlock() instanceof CombiSteamerFluidBlock) {
                baseBlock1 = baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                baseBlock2 = baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                baseBlock3 = baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                baseBlock4 = baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                baseBlock5 = baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                energyBlock = energyBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                communicationBlock = communicationBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH);
                pLevel.setBlock(baseBlockPos1, baseBlock1, 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2, 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3, 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4, 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5, 3);
                pLevel.setBlock(energyBlockPos, energyBlock, 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock, 3);
                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            }
        } else if (pDirection == Direction.SOUTH) {
            BlockPos baseBlockPos1 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY(), pCurrentPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY(), pCurrentPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY(), pCurrentPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY() + 1, pCurrentPos.getZ() - 1);

            BlockState baseBlock1 = pLevel.getBlockState(baseBlockPos1);
            BlockState baseBlock2 = pLevel.getBlockState(baseBlockPos2);
            BlockState baseBlock3 = pLevel.getBlockState(baseBlockPos3);
            BlockState baseBlock4 = pLevel.getBlockState(baseBlockPos4);
            BlockState baseBlock5 = pLevel.getBlockState(baseBlockPos5);
            BlockState energyBlock = pLevel.getBlockState(energyBlockPos);
            BlockState communicationBlock = pLevel.getBlockState(communicationBlockPos);

            if (baseBlock1.getBlock() instanceof CombiSteamerCasingBlock && baseBlock2.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock3.getBlock() instanceof CombiSteamerCasingBlock && baseBlock4.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock5.getBlock() instanceof CombiSteamerCasingBlock && energyBlock.getBlock() instanceof CombiSteamerEnergyBlock
                    && communicationBlock.getBlock() instanceof CombiSteamerFluidBlock) {
                baseBlock1 = baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                baseBlock2 = baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                baseBlock3 = baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                baseBlock4 = baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                baseBlock5 = baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                energyBlock = energyBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                communicationBlock = communicationBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.SOUTH);
                pLevel.setBlock(baseBlockPos1, baseBlock1, 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2, 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3, 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4, 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5, 3);
                pLevel.setBlock(energyBlockPos, energyBlock, 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock, 3);
                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            }
        } else if (pDirection == Direction.WEST) {
            BlockPos baseBlockPos1 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY(), pCurrentPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY(), pCurrentPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY(), pCurrentPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pCurrentPos.getX() + 1, pCurrentPos.getY() + 1, pCurrentPos.getZ() - 1);

            BlockState baseBlock1 = pLevel.getBlockState(baseBlockPos1);
            BlockState baseBlock2 = pLevel.getBlockState(baseBlockPos2);
            BlockState baseBlock3 = pLevel.getBlockState(baseBlockPos3);
            BlockState baseBlock4 = pLevel.getBlockState(baseBlockPos4);
            BlockState baseBlock5 = pLevel.getBlockState(baseBlockPos5);
            BlockState energyBlock = pLevel.getBlockState(energyBlockPos);
            BlockState communicationBlock = pLevel.getBlockState(communicationBlockPos);

            if (baseBlock1.getBlock() instanceof CombiSteamerCasingBlock && baseBlock2.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock3.getBlock() instanceof CombiSteamerCasingBlock && baseBlock4.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock5.getBlock() instanceof CombiSteamerCasingBlock && energyBlock.getBlock() instanceof CombiSteamerEnergyBlock
                    && communicationBlock.getBlock() instanceof CombiSteamerFluidBlock) {
                baseBlock1 = baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                baseBlock2 = baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                baseBlock3 = baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                baseBlock4 = baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                baseBlock5 = baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                energyBlock = energyBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                communicationBlock = communicationBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.WEST);
                pLevel.setBlock(baseBlockPos1, baseBlock1, 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2, 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3, 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4, 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5, 3);
                pLevel.setBlock(energyBlockPos, energyBlock, 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock, 3);
                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            }
        } else {
            BlockPos baseBlockPos1 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY(), pCurrentPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY(), pCurrentPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pCurrentPos.getX(), pCurrentPos.getY() + 1, pCurrentPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY(), pCurrentPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pCurrentPos.getX() - 1, pCurrentPos.getY() + 1, pCurrentPos.getZ() + 1);

            BlockState baseBlock1 = pLevel.getBlockState(baseBlockPos1);
            BlockState baseBlock2 = pLevel.getBlockState(baseBlockPos2);
            BlockState baseBlock3 = pLevel.getBlockState(baseBlockPos3);
            BlockState baseBlock4 = pLevel.getBlockState(baseBlockPos4);
            BlockState baseBlock5 = pLevel.getBlockState(baseBlockPos5);
            BlockState energyBlock = pLevel.getBlockState(energyBlockPos);
            BlockState communicationBlock = pLevel.getBlockState(communicationBlockPos);

            if (baseBlock1.getBlock() instanceof CombiSteamerCasingBlock && baseBlock2.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock3.getBlock() instanceof CombiSteamerCasingBlock && baseBlock4.getBlock() instanceof CombiSteamerCasingBlock
                    && baseBlock5.getBlock() instanceof CombiSteamerCasingBlock && energyBlock.getBlock() instanceof CombiSteamerEnergyBlock
                    && communicationBlock.getBlock() instanceof CombiSteamerFluidBlock) {
                baseBlock1 = baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                baseBlock2 = baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                baseBlock3 = baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                baseBlock4 = baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                baseBlock5 = baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                energyBlock = energyBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                communicationBlock = communicationBlock.setValue(COMPLETED, Boolean.valueOf(true)).setValue(FACING, Direction.EAST);
                pLevel.setBlock(baseBlockPos1, baseBlock1, 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2, 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3, 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4, 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5, 3);
                pLevel.setBlock(energyBlockPos, energyBlock, 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock, 3);
                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            }
        }
        return pState;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (getComplete(pState))
            return new CombiSteamerBlockEntity(pPos, pState);
        return null;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
       return new CombiSteamerBlockEntityTicker<>();
    }

    @Override
    public boolean isController() {
        return true;
    }
}
