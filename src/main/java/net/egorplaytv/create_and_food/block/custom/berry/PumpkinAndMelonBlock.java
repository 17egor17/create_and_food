package net.egorplaytv.create_and_food.block.custom.berry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.BiomeDictionary;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class PumpkinAndMelonBlock extends BushBlock implements BonemealableBlock {
    public static final BooleanProperty IN_JUNGLE = BooleanProperty.create("in_jungle");
    public boolean hasJungle = false;
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(7.5, 0, 7.5, 8.5, 2, 8.5)),
            Shapes.or(Block.box(6, 0, 6, 10, 4, 10)),
            Shapes.or(Block.box(6, 0, 6, 10, 4, 10)),
            Shapes.box(0.375, 0, 0.3125, 0.625, 0.25, 0.5625),
            Shapes.or(Block.box(5, 0, 5, 11, 6, 11)),
            Shapes.or(Block.box(3, 0, 3, 13, 10, 13)),
            Shapes.or(Block.box(1, 0, 1, 15, 13, 15)),
            Shapes.or(Block.box(0, 0, 0, 16, 16, 16))};

    private static final VoxelShape[] COLLISION_SHAPE = new VoxelShape[]{
            Shapes.or(Block.box(0, 0, 0, 0, 0, 0)),
            Shapes.or(Block.box(0, 0, 0, 0, 0, 0)),
            Shapes.or(Block.box(0, 0, 0, 0, 0, 0)),
            Shapes.box(0.375, 0, 0.3125, 0.625, 0.25, 0.5625),
            Shapes.or(Block.box(5, 0, 5, 11, 6, 11)),
            Shapes.or(Block.box(3, 0, 3, 13, 10, 13)),
            Shapes.or(Block.box(1, 0, 1, 15, 13, 15)),
            Shapes.or(Block.box(0, 0, 0, 16, 16, 16))};

    public PumpkinAndMelonBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(IN_JUNGLE, hasJungle));
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);

        if (pLevel.getBiome(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ())).value().getRegistryName() != null &&
                BiomeDictionary.hasType(ResourceKey.create(Registry.BIOME_REGISTRY,
                                Objects.requireNonNull(pLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY)
                                        .getKey(pLevel.getBiome(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ())).value()))),
                        BiomeDictionary.Type.JUNGLE)) {
            this.hasJungle = true;
            this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(IN_JUNGLE, hasJungle));
        } else {
            this.hasJungle = false;
            this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(IN_JUNGLE, hasJungle));
        }
    }

    protected int getAge(BlockState pState) {
        return pState.getValue(AGE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE[this.getAge(pState)];
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE[this.getAge(pState)];
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        int i = pState.getValue(AGE);
        if (i < MAX_AGE && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,pRandom.nextInt(5) == 0)) {
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, IN_JUNGLE);
    }

    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    public boolean isBonemealSuccess(Level pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        int i = Math.min(MAX_AGE, pState.getValue(AGE) + 1);
        pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(i)), 2);
    }
}
