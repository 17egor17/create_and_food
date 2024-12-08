package net.egorplaytv.create_and_food.block.custom;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class RedSandFertilized extends SandBlock {
    public RedSandFertilized(int pDustColor, Properties pProperties) {
        super(pDustColor, pProperties);
    }


    @SuppressWarnings("removal")
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        if (stack.getItem() instanceof HoeItem) {
            if (state.is(ModBlocks.FERTILIZED_RED_SAND.get())) {
                return ModBlocks.RED_SAND_FARMLAND.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
    }

    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType plantType = plantable.getPlantType(world, pos.relative(facing));
        return plantType != PlantType.CROP && plantType != PlantType.NETHER;
    }
}
