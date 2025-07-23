package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class DirtSlabBlock extends SumpBlock {
    public DirtSlabBlock(Properties pProperties) {
        super(pProperties);
    }

    @SuppressWarnings("removal")
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player,
                                           ItemStack stack, ToolAction toolAction) {
        if(stack.getItem() instanceof HoeItem) {
            if(state.is(ModBlocks.FARMLAND_SUMP_DIRT.get()) && state.getValue(SumpBlock.TYPE) == SlabType.BOTTOM) {
                if (state.getValue(DirtSlabBlock.WATERLOGGED)) {
                    return ModBlocks.FLOODED_FARMLAND.get().defaultBlockState()
                            .setValue(FloodedFarmlandBlock.WATERLOGGED, true);
                } else {
                    return ModBlocks.FLOODED_FARMLAND.get().defaultBlockState();
                }
            }
            if(state.is(ModBlocks.FARMLAND_SUMP_RICH_SOIL.get()) && state.getValue(SumpBlock.TYPE) == SlabType.BOTTOM) {
                if (state.getValue(DirtSlabBlock.WATERLOGGED)) {
                    return ModBlocks.FLOODED_RICH_SOIL_FARMLAND.get().defaultBlockState()
                            .setValue(FloodedRichSoilFarmlandBlock.WATERLOGGED, true);
                } else {
                    return ModBlocks.FLOODED_RICH_SOIL_FARMLAND.get().defaultBlockState();
                }
            }
        }

        return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
    }
}