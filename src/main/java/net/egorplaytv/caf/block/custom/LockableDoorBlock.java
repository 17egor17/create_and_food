package net.egorplaytv.caf.block.custom;

import net.egorplaytv.caf.block.entity.custom.LockableDoorBlockEntity;
import net.egorplaytv.caf.block.praperties.CAFBlockStateProperties;
import net.egorplaytv.caf.data.KeyData;
import net.egorplaytv.caf.item.custom.KeyItem;
import net.egorplaytv.caf.item.custom.LockableDoorItem;
import net.egorplaytv.caf.util.CAFTags;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class LockableDoorBlock extends DoorBlock implements EntityBlock {
    private static final BooleanProperty LOCKED = CAFBlockStateProperties.LOCKED;

    public LockableDoorBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(LOCKED, Boolean.valueOf(false)));
    }

    private int getCloseSound() {
        return this.material == Material.METAL ? 1011 : 1012;
    }

    private int getOpenSound() {
        return this.material == Material.METAL ? 1005 : 1006;
    }

    private void playSound(Level pLevel, BlockPos pPos, boolean pIsOpening) {
        pLevel.levelEvent((Player)null, pIsOpening ? this.getOpenSound() : this.getCloseSound(), pPos, 0);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack mainHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

        BlockEntity entity = pLevel.getBlockEntity(pPos);

        if (entity instanceof LockableDoorBlockEntity doorBlockEntity) {
            if (mainHand.is(CAFTags.Items.KEYS)) {
                if (mainHand.getTag() != null && mainHand.getTag().contains("keyId") && !mainHand.getTag().getString("keyId").isEmpty()
                        && doorBlockEntity.getKeyId().isEmpty()) {
                    writeBoth(pLevel, pPos, mainHand.getTag().getString("keyId"));
                    pPlayer.displayClientMessage(TextUtils.getModTranslation("door.key_bound"), true);
                }

                if (mainHand.getTag() != null && mainHand.getTag().contains("keyId")
                        && mainHand.getTag().getString("keyId").equals(doorBlockEntity.getKeyId())
                        && doorBlockEntity.isBound()) {
                    if (pState.getValue(HALF) == DoubleBlockHalf.UPPER) {
                        boolean isLocked = !pState.getValue(LOCKED);
                        if (pState.getValue(OPEN)) {
                            this.playSound(pLevel, pPos, false);
                        }
                        pLevel.setBlock(pPos, pState.setValue(LOCKED, isLocked)
                                .setValue(HALF, DoubleBlockHalf.UPPER).setValue(OPEN, Boolean.valueOf(false)), 3);
                        pLevel.setBlock(new BlockPos(pPos.getX(), pPos.getY() - 1, pPos.getZ()),
                                pState.setValue(LOCKED, isLocked).setValue(HALF, DoubleBlockHalf.LOWER)
                                        .setValue(OPEN, Boolean.valueOf(false)), 3);
                        pLevel.playSound(pPlayer, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
                    } else {
                        boolean isLocked = !pState.getValue(LOCKED);
                        if (pState.getValue(OPEN)) {
                            this.playSound(pLevel, pPos, false);
                        }
                        pLevel.setBlock(pPos, pState.setValue(LOCKED, isLocked)
                                .setValue(HALF, DoubleBlockHalf.LOWER).setValue(OPEN, Boolean.valueOf(false)), 3);
                        pLevel.setBlock(new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ()),
                                pState.setValue(LOCKED, isLocked).setValue(HALF, DoubleBlockHalf.UPPER)
                                        .setValue(OPEN, Boolean.valueOf(false)), 3);
                        pLevel.playSound(pPlayer, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
                    }

                    if (pState.getValue(LOCKED)) {
                        pPlayer.displayClientMessage(TextUtils.getModTranslation("door.unlocked"), true);
                    } else {
                        pPlayer.displayClientMessage(TextUtils.getModTranslation("door.locked"), true);
                    }

                } else if (mainHand.getTag() != null && mainHand.getTag().contains("keyId")
                        && !mainHand.getTag().getString("keyId").equals(doorBlockEntity.getKeyId())
                        && pState.getValue(LOCKED) && doorBlockEntity.isBound()) {
                    pPlayer.displayClientMessage(TextUtils.getModTranslation("door.wrong_key")
                            .withStyle(ChatFormatting.RED), true);
                }
                return InteractionResult.CONSUME;
            }

            if (mainHand.isEmpty()) {
                if (pState.getValue(LOCKED)) {
                    pPlayer.displayClientMessage(TextUtils.getModTranslation("door.locked"), true);
                    this.playSound(pLevel, pPos, false);
                }
            }

            if (!pState.getValue(LOCKED)) {
                pState = pState.cycle(OPEN);
                pLevel.setBlock(pPos, pState, 10);
                pLevel.levelEvent(pPlayer, pState.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pPos, 0);
                pLevel.gameEvent(pPlayer, this.isOpen(pState) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pState.getValue(LOCKED)) {
            boolean flag = pLevel.hasNeighborSignal(pPos) || pLevel.hasNeighborSignal(pPos.relative(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
            if (!this.defaultBlockState().is(pBlock) && flag != pState.getValue(POWERED)) {
                if (flag != pState.getValue(OPEN)) {
                    this.playSound(pLevel, pPos, flag);
                    pLevel.gameEvent(flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
                }

                pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)), 2);
            }
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (pLevel.isClientSide) return;

        String keyId = KeyData.readDoorKeyId(pStack);
        if (keyId.equals(KeyData.UNBOUND))
            return;

        writeBoth(pLevel, pPos, keyId);
    }

    public void writeBoth(Level level, BlockPos pos, String keyId) {
        LockableDoorBlockEntity a = LockableDoorBlockEntity.find(level, pos);
        if (a != null) a.setKeyId(keyId);
        BlockPos other = level.getBlockState(pos).getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos.above();

        if (level.getBlockState(other).is(this)) {
            LockableDoorBlockEntity b = LockableDoorBlockEntity.find(level, other);
            if (b != null) b.setKeyId(keyId);
        }
    }

    public static void dropResources(BlockState pState, Level pLevel, BlockPos pPos, @javax.annotation.Nullable BlockEntity pBlockEntity, Entity pEntity, ItemStack pTool) {
        if (pLevel instanceof ServerLevel) {
            getDrops(pState, (ServerLevel)pLevel, pPos, pBlockEntity, pEntity, pTool).forEach((p_49925_) -> {
                if (pBlockEntity instanceof LockableDoorBlockEntity entity)
                    if (p_49925_.getItem() instanceof LockableDoorItem)
                        KeyData.writeDoorKeyId(p_49925_, entity.getKeyId());
                popResource(pLevel, pPos, p_49925_);
            });
            pState.spawnAfterBreak((ServerLevel)pLevel, pPos, pTool);
        }

    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        pPlayer.awardStat(Stats.BLOCK_MINED.get(this));
        pPlayer.causeFoodExhaustion(0.005F);
        dropResources(pState, pLevel, pPos, pBlockEntity, pPlayer, pTool);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        ItemStack stack = super.getCloneItemStack(pLevel, pPos, pState);

        BlockEntity entity = pLevel.getBlockEntity(pPos);

        if (entity instanceof LockableDoorBlockEntity doorBlockEntity) {
            if (doorBlockEntity.isBound()) {
                KeyData.writeDoorKeyId(stack, doorBlockEntity.getKeyId());
            }
        }

        return stack;
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LockableDoorBlockEntity(pPos, pState);
    }
}
