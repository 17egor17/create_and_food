package net.egorplaytv.create_and_food.block.custom;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.AbstractSimpleShaftBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlock;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.utility.Iterate;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CAFCogWheelBlock extends AbstractSimpleShaftBlock implements ICogWheel, EncasableBlock {

    public boolean isLarge;

    protected CAFCogWheelBlock(boolean large, Properties properties) {
        super(properties);
        isLarge = large;
    }

    public static CAFCogWheelBlock small(Properties properties) {
        return new CAFCogWheelBlock(false, properties);
    }

    public static CAFCogWheelBlock large(Properties properties) {
        return new CAFCogWheelBlock(true, properties);
    }

    @Override
    public boolean isLargeCog() {
        return isLarge;
    }

    @Override
    public boolean isSmallCog() {
        return !isLarge;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {
        super.fillItemCategory(pTab, pItems);
        // Ensure the belt item is added after large cogwheels in the creative tab
        if (AllBlocks.LARGE_COGWHEEL.is(this) && pTab == AllCreativeModeTabs.BASE_CREATIVE_TAB)
            pItems.add(AllItems.BELT_CONNECTOR.asStack());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return (isLarge ? AllShapes.LARGE_GEAR : AllShapes.SMALL_GEAR).get(state.getValue(AXIS));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return isValidCogwheelPosition(ICogWheel.isLargeCog(state), worldIn, pos, state.getValue(AXIS));
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (placer instanceof Player player)
            triggerShiftingGearsAdvancement(worldIn, pos, state, player);
    }

    protected void triggerShiftingGearsAdvancement(Level world, BlockPos pos, BlockState state, Player player) {
        if (world.isClientSide || player == null)
            return;

        Direction.Axis axis = state.getValue(CAFCogWheelBlock.AXIS);
        for (Direction.Axis perpendicular1 : Iterate.axes) {
            if (perpendicular1 == axis)
                continue;

            Direction d1 = Direction.get(Direction.AxisDirection.POSITIVE, perpendicular1);
            for (Direction.Axis perpendicular2 : Iterate.axes) {
                if (perpendicular1 == perpendicular2)
                    continue;
                if (axis == perpendicular2)
                    continue;

                Direction d2 = Direction.get(Direction.AxisDirection.POSITIVE, perpendicular2);
                for (int offset1 : Iterate.positiveAndNegative) {
                    for (int offset2 : Iterate.positiveAndNegative) {
                        BlockPos connectedPos = pos.relative(d1, offset1)
                                .relative(d2, offset2);
                        BlockState blockState = world.getBlockState(connectedPos);
                        if (!(blockState.getBlock() instanceof CAFCogWheelBlock))
                            continue;
                        if (blockState.getValue(CAFCogWheelBlock.AXIS) != axis)
                            continue;
                        if (ICogWheel.isLargeCog(blockState) == isLarge)
                            continue;

                        AllAdvancements.COGS.awardTo(player);
                    }
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult ray) {
        if (player.isShiftKeyDown() || !player.mayBuild())
            return InteractionResult.PASS;

        ItemStack heldItem = player.getItemInHand(hand);
        InteractionResult result = tryEncase(state, world, pos, heldItem, player, hand, ray);
        if (result.consumesAction())
            return result;

        return InteractionResult.PASS;
    }

    public static boolean isValidCogwheelPosition(boolean large, LevelReader worldIn, BlockPos pos, Direction.Axis cogAxis) {
        for (Direction facing : Iterate.directions) {
            if (facing.getAxis() == cogAxis)
                continue;

            BlockPos offsetPos = pos.relative(facing);
            BlockState blockState = worldIn.getBlockState(offsetPos);
            if (blockState.hasProperty(AXIS) && facing.getAxis() == blockState.getValue(AXIS))
                continue;

            if (ICogWheel.isLargeCog(blockState) || large && ICogWheel.isSmallCog(blockState))
                return false;
        }
        return true;
    }

    protected Direction.Axis getAxisForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown())
            return context.getClickedFace()
                    .getAxis();

        Level world = context.getLevel();
        BlockState stateBelow = world.getBlockState(context.getClickedPos()
                .below());

        if (AllBlocks.ROTATION_SPEED_CONTROLLER.has(stateBelow) && isLargeCog())
            return stateBelow.getValue(SpeedControllerBlock.HORIZONTAL_AXIS) == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;

        BlockPos placedOnPos = context.getClickedPos()
                .relative(context.getClickedFace()
                        .getOpposite());
        BlockState placedAgainst = world.getBlockState(placedOnPos);

        Block block = placedAgainst.getBlock();
        if (ICogWheel.isSmallCog(placedAgainst))
            return ((IRotate) block).getRotationAxis(placedAgainst);

        Direction.Axis preferredAxis = getPreferredAxis(context);
        return preferredAxis != null ? preferredAxis
                : context.getClickedFace()
                .getAxis();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean shouldWaterlog = context.getLevel()
                .getFluidState(context.getClickedPos())
                .getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(AXIS, getAxisForPlacement(context))
                .setValue(BlockStateProperties.WATERLOGGED, shouldWaterlog);
    }

    @Override
    public float getParticleTargetRadius() {
        return isLargeCog() ? 1.125f : .65f;
    }

    @Override
    public float getParticleInitialRadius() {
        return isLargeCog() ? 1f : .75f;
    }

    @Override
    public boolean isDedicatedCogWheel() {
        return true;
    }


    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.CAF_COGWHEEL.get();
    }
}