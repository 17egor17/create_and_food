package net.egorplaytv.caf.block.entity.custom;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.egorplaytv.caf.config.CAFConfigs;
import net.egorplaytv.caf.units.weight.CAFWeightUnits;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ScalesBlockEntity extends SyncedBlockEntity implements IHaveGoggleInformation {
    public final ItemStackHandler itemHandler = new ItemStackHandler() {
        public int getSlotLimit(int slot) {
            return 1;
        }

        protected void onContentsChanged(int slot) {
            ScalesBlockEntity.this.inventoryChanged();
        }
    };

    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> this.itemHandler);
    public boolean isWeighing;
    public int weighingTicks;
    public int maxWeighingTicks = 3;
    private ResourceLocation lastRecipeID;

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedWeightTooltip(tooltip, isPlayerSneaking, itemHandler.getStackInSlot(0).getItem());
    }

    public ScalesBlockEntity(BlockPos pos, BlockState state) {
        super(CAFBlockEntities.SCALES_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ScalesBlockEntity pBlockEntity) {
        if (pBlockEntity.weighingTicks == 0 && !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty())
            pBlockEntity.isWeighing = true;

        if (pBlockEntity.isWeighing) {
            ++pBlockEntity.weighingTicks;
        }

        if (pBlockEntity.weighingTicks == pBlockEntity.maxWeighingTicks && !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty()){
            pBlockEntity.isWeighing = false;
        }


        if (pBlockEntity.weighingTicks <= 0){
            pBlockEntity.isWeighing = false;
            pBlockEntity.weighingTicks = 0;
        } else if (pBlockEntity.weighingTicks <= pBlockEntity.maxWeighingTicks && pBlockEntity.itemHandler.getStackInSlot(0).isEmpty()) {
            pBlockEntity.isWeighing = false;
            --pBlockEntity.weighingTicks;
        }
    }

    public void setIsWeighing(boolean bool){
        this.isWeighing = bool;
    }

    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", this.itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    public void load(CompoundTag tag) {
        super.load(tag);
        this.itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        weighingTicks = 0;
        isWeighing = true;
    }


    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (this.level != null) {
            this.level.playSound(null, (float)this.worldPosition.getX() + 0.5F,
                    (float)this.worldPosition.getY() + 0.5F, (float)this.worldPosition.getZ() + 0.5F,
                    sound, SoundSource.BLOCKS, volume, pitch);
        }
    }

    public boolean addItem(ItemStack itemStack) {
        if (this.isEmpty() && !itemStack.isEmpty()) {
            this.itemHandler.setStackInSlot(0, itemStack.split(1));
            this.inventoryChanged();
            return true;
        } else {
            return false;
        }
    }

    public ItemStack removeItem() {
        if (!this.isEmpty()) {
            ItemStack item = this.getStoredItem().split(1);
            this.inventoryChanged();
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public IItemHandler getInventory() {
        return this.itemHandler;
    }

    public ItemStack getStoredItem() {
        return itemHandler.getStackInSlot(0);
    }

    public boolean isEmpty() {
        return itemHandler.getStackInSlot(0).isEmpty();
    }


    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) ? this.inputHandler.cast() : super.getCapability(cap, side);
    }

    public void setRemoved() {
        super.setRemoved();
        this.inputHandler.invalidate();
    }
}
