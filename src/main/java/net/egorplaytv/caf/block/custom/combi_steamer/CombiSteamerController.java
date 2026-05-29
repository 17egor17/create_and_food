package net.egorplaytv.caf.block.custom.combi_steamer;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.entity.custom.combi_steamer.CombiSteamerControllerBlockEntity;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

    private void shouldBeCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        Direction dir = pState.getValue(FACING);
        if (dir == Direction.NORTH) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ() + 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(true)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (dir == Direction.SOUTH) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ() - 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(true)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else if (dir == Direction.WEST) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ() - 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(true)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        } else {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ() + 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(true)), 3);
            } else {
                pLevel.setBlock(pPos, pState.setValue(COMPLETED, Boolean.valueOf(false)), 3);
            }
        }
    }

    private BlockState beCompleted(BlockState pState, Level pLevel, BlockPos pPos) {
        Direction dir = pState.getValue(FACING);
        if (dir == Direction.NORTH) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ() + 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (dir == Direction.SOUTH) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ() - 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else if (dir == Direction.WEST) {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() - 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() - 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() + 1, pPos.getY() + 1, pPos.getZ() - 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        } else {
            BlockPos baseBlockPos1 = new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1);
            BlockPos baseBlockPos2 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ() + 1);
            BlockPos baseBlockPos3 = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ());
            BlockPos baseBlockPos4 = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ());
            BlockPos baseBlockPos5 = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockPos energyBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() + 1);
            BlockPos communicationBlockPos = new BlockPos(pPos.getX() - 1, pPos.getY() + 1, pPos.getZ() + 1);

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
                pLevel.setBlock(baseBlockPos1, baseBlock1.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos2, baseBlock2.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos3, baseBlock3.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos4, baseBlock4.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(baseBlockPos5, baseBlock5.setValue(CombiSteamerCasingBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerCasingBlock.FACING, dir), 3);
                pLevel.setBlock(energyBlockPos, energyBlock.setValue(CombiSteamerEnergyBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerEnergyBlock.FACING, dir), 3);
                pLevel.setBlock(communicationBlockPos, communicationBlock.setValue(CombiSteamerFluidBlock.COMPLETED, Boolean.valueOf(true))
                        .setValue(CombiSteamerFluidBlock.FACING, dir), 3);

                return pState.setValue(COMPLETED, Boolean.valueOf(true));
            } else {
                return pState.setValue(COMPLETED, Boolean.valueOf(false));
            }
        }
    }

    @Override
    public boolean isController() {
        return true;
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (getComplete(pState))
            return new CombiSteamerControllerBlockEntity(pPos, pState);
        return null;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CombiSteamerControllerBlockEntity) {
                ((CombiSteamerControllerBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, CAFBlockEntities.COMBI_STEAMER_CONTROLLER_ENTITY.get(),
                CombiSteamerControllerBlockEntity::tick);
    }
}
