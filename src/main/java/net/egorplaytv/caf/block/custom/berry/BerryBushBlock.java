package net.egorplaytv.caf.block.custom.berry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class BerryBushBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(5, 2, 5, 11, 8, 11)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(4, 2, 4, 12, 10, 12)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(3, 2, 3, 13, 12, 13)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(2, 2, 2, 14, 14, 14)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15)),
            Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D), Block.box(1, 2, 1, 15, 16, 15))};

    private static final VoxelShape[] COLLISION_SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(0, 0, 0, 0, 0, 0)),
            Shapes.or(Block.box(5, 0, 5, 11, 4, 11)),
            Shapes.or(Block.box(4, 0, 4, 12, 5, 12)),
            Shapes.or(Block.box(3, 0, 3, 13, 6, 13)),
            Shapes.or(Block.box(2, 0, 2, 14, 7, 14)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15)),
            Shapes.or(Block.box(1, 0, 1, 15, 8, 15))};

    public BerryBushBlock(BlockBehaviour.Properties pProperties){
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(getAgeProperty(), Integer.valueOf(0))
                .setValue(CUT, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE[this.getAge(pState)];
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE[this.getAge(pState)];
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState pState) {
        return pState.getValue(getAgeProperty());
    }

    public int getMaxAge(){
        return MAX_AGE;
    }

    public boolean isMaxAge(BlockState pState){
        return pState.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return !this.isMaxAge(pState);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        int i = getAge(pState);
        boolean j = pState.getValue(CUT);
        if (i < getMaxAge() && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(getAgeProperty(), Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
        if (i > 4 && j == Boolean.valueOf(true) && pLevel.getRawBrightness(pPos.above(), 0) >= 9 &&
                net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(CUT, Boolean.valueOf(false)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }

    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (getAge(pState) >= 2) {
            if (pEntity instanceof LivingEntity && pEntity.getType() != EntityType.FOX && pEntity.getType() != EntityType.BEE) {
                pEntity.makeStuckInBlock(pState, new Vec3((double) 0.8F, 0.75D, (double) 0.8F));
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, CUT);
    }

    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return !this.isMaxAge(pState);
    }

    public boolean isBonemealSuccess(Level pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        int i = Math.min(getMaxAge(), getAge(pState) + 1);
        pLevel.setBlock(pPos, pState.setValue(getAgeProperty(), Integer.valueOf(i)), 2);
    }
}
