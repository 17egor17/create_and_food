package net.egorplaytv.caf.block.custom.combi_steamer;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.egorplaytv.caf.block.praperties.CAFBlockStateProperties;
import net.egorplaytv.caf.block.praperties.CombiSteamerBaseBlockType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class CombiSteamerCasingBlock extends CasingBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CombiSteamerBaseBlockType> TYPE = CAFBlockStateProperties.CSType;
    public static final BooleanProperty COMPLETED = CAFBlockStateProperties.COMPLETED;
    public static boolean complete = false;

    public CombiSteamerCasingBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = box(0,0,0,16,16,16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        setComplete(pState.getValue(COMPLETED));
    }

    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }


    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean value) {
        complete = value;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, COMPLETED, TYPE);
    }
}
