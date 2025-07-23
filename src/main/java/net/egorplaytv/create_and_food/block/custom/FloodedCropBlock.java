package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FloodedCropBlock extends CropBlock {
    public FloodedCropBlock(Properties pProperties) {
        super(pProperties);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return (pState.getBlock() == ModBlocks.FLOODED_FARMLAND.get() || pState.getBlock() == ModBlocks.FLOODED_RICH_SOIL_FARMLAND.get())
                && (pState.getValue(FloodedFarmlandBlock.WATERLOGGED) || pState.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED));
    }
}
