package net.egorplaytv.caf.block.custom.worktable;

import net.egorplaytv.caf.block.entity.custom.worktable.WorktableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public abstract class AbstractWorktableBlock<E extends BlockEntity> extends BaseEntityBlock {
    protected final Supplier<BlockEntityType<? extends E>> blockEntityType;

    protected AbstractWorktableBlock(Properties pProperties, Supplier<BlockEntityType<? extends E>> blockEntityType) {
        super(pProperties);
        this.blockEntityType = blockEntityType;
    }

    public abstract DoubleBlockCombiner.NeighborCombineResult<? extends WorktableBlockEntity> combine(BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride);
}
