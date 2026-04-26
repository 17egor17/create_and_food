package net.egorplaytv.caf.energy.block;

import com.simibubi.create.foundation.block.IBE;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.energy.block.entity.EnergyConvertorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnergyConvertorBlock extends EnergyHorizontalKineticBlock implements IBE<EnergyConvertorBlockEntity> {
    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public EnergyConvertorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public Class<EnergyConvertorBlockEntity> getBlockEntityClass() {
        return EnergyConvertorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends EnergyConvertorBlockEntity> getBlockEntityType() {
        return CAFBlockEntities.ENERGY_CONVERTER.get();
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

}