package net.egorplaytv.caf.block.entity.custom.combi_steamer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class CombiSteamerBlockEntityTicker<T extends BlockEntity> implements BlockEntityTicker<T> {
    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
        if (!pBlockEntity.hasLevel())
            pBlockEntity.setLevel(pLevel);
        ((CombiSteamerBlockEntity) pBlockEntity).tick(pLevel, pState, pPos, pBlockEntity);
    }
}
