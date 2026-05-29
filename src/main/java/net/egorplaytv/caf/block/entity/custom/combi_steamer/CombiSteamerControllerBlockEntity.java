package net.egorplaytv.caf.block.entity.custom.combi_steamer;

import net.egorplaytv.caf.block.custom.combi_steamer.CombiSteamerEnergyBlock;
import net.egorplaytv.caf.block.custom.combi_steamer.CombiSteamerFluidBlock;
import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CombiSteamerControllerBlockEntity extends BlockEntity implements IHaveGoggleInformation {
    protected ItemStackHandler inventory = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    protected LazyOptional<IItemHandler> itemCapability = LazyOptional.of(() -> inventory);

    private CombiSteamerFluidCommunicationBlockEntity fluidCommunication;
    private CombiSteamerEnergyCommunicationBlockEntity energyCommunication;

    public CombiSteamerControllerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.COMBI_STEAMER_CONTROLLER_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", inventory.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.inventory.getSlots());
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCapability.invalidate();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CombiSteamerControllerBlockEntity entity) {
        if (level.isClientSide) {
            return;
        }

        BlockPos fluidCommunicationPos1 = new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
        BlockPos fluidCommunicationPos2 = new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ() - 1);
        BlockPos fluidCommunicationPos3 = new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() - 1);
        BlockPos fluidCommunicationPos4 = new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ() + 1);
        BlockState fluidCommunicationState1 = level.getBlockState(fluidCommunicationPos1);
        BlockState fluidCommunicationState2 = level.getBlockState(fluidCommunicationPos2);
        BlockState fluidCommunicationState3 = level.getBlockState(fluidCommunicationPos3);
        BlockState fluidCommunicationState4 = level.getBlockState(fluidCommunicationPos4);
        if (fluidCommunicationState1.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.fluidCommunication = (CombiSteamerFluidCommunicationBlockEntity) level.getBlockEntity(fluidCommunicationPos1);
        } else if (fluidCommunicationState2.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.fluidCommunication = (CombiSteamerFluidCommunicationBlockEntity) level.getBlockEntity(fluidCommunicationPos2);
        } else if (fluidCommunicationState3.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.fluidCommunication = (CombiSteamerFluidCommunicationBlockEntity) level.getBlockEntity(fluidCommunicationPos3);
        } else if (fluidCommunicationState4.getBlock() instanceof CombiSteamerFluidBlock){
            entity.fluidCommunication = (CombiSteamerFluidCommunicationBlockEntity) level.getBlockEntity(fluidCommunicationPos4);
        }

        BlockPos energyCommunicationPos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1);
        BlockPos energyCommunicationPos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1);
        BlockPos energyCommunicationPos3 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
        BlockPos energyCommunicationPos4 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
        BlockState energyCommunicationState1 = level.getBlockState(energyCommunicationPos1);
        BlockState energyCommunicationState2 = level.getBlockState(energyCommunicationPos2);
        BlockState energyCommunicationState3 = level.getBlockState(energyCommunicationPos3);
        BlockState energyCommunicationState4 = level.getBlockState(energyCommunicationPos4);
        if (energyCommunicationState1.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.energyCommunication = (CombiSteamerEnergyCommunicationBlockEntity) level.getBlockEntity(energyCommunicationPos1);
        } else if (energyCommunicationState2.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.energyCommunication = (CombiSteamerEnergyCommunicationBlockEntity) level.getBlockEntity(energyCommunicationPos2);
        } else if (energyCommunicationState3.getBlock() instanceof CombiSteamerFluidBlock) {
            entity.energyCommunication = (CombiSteamerEnergyCommunicationBlockEntity) level.getBlockEntity(energyCommunicationPos3);
        } else if (energyCommunicationState4.getBlock() instanceof CombiSteamerEnergyBlock) {
            entity.energyCommunication = (CombiSteamerEnergyCommunicationBlockEntity) level.getBlockEntity(energyCommunicationPos4);
        }



    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedFluidTooltip(tooltip, isPlayerSneaking, fluidCommunication.fluidCapability)
                && containedEnergyTooltip(tooltip, isPlayerSneaking, energyCommunication.energyCapability);
    }
}
