package net.egorplaytv.create_and_food.block.custom.farmland_into_barrel;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.FarmBlockIntoBarrel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class FarmBlockIntoAcaciaBarrel extends FarmBlockIntoBarrel {
    public FarmBlockIntoAcaciaBarrel(Properties pProperties) {
        super(pProperties);
    }

    public boolean isFertile(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.is(ModBlocks.FARMLAND_INTO_ACACIA_BARREL.get())) {
            return state.getValue(MOISTURE) > 0;
        } else {
            return false;
        }
    }
}