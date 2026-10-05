package net.egorplaytv.caf.block.entity.custom;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.data.KeyData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class LockableDoorBlockEntity extends BlockEntity {
    private String keyId = KeyData.NO_KEY;

    public LockableDoorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.LOCKABLE_DOOR_ENTITY.get(), pPos, pBlockState);
    }

    @Nullable
    public static LockableDoorBlockEntity find(BlockGetter level, BlockPos pos) {
        return level.getBlockEntity(pos) instanceof LockableDoorBlockEntity door ? door : null;
    }

    public boolean isBound() {
        return !keyId.isEmpty();
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("boundKeyId", keyId);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        keyId = tag.getString("boundKeyId");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (pkt.getTag() != null) load(pkt.getTag());
    }
}
