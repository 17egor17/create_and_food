package net.egorplaytv.caf.block.entity.custom.combi_steamer;

import net.egorplaytv.caf.block.entity.CAFBlockEntities;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.egorplaytv.caf.block.pattern.interfaces.ICombiSteamerBlock;
import net.egorplaytv.caf.block.pattern.interfaces.ICommunicationBlock;
import net.egorplaytv.caf.block.pattern.interfaces.IHaveGoggleInformation;
import net.egorplaytv.caf.energy.EnergyStorage;
import net.egorplaytv.caf.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CombiSteamerBlockEntity extends BlockEntity implements IHaveGoggleInformation {
    protected FluidTank fluidInventory = new FluidTank(2000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };
    protected LazyOptional<IFluidHandler> fluidCapability = LazyOptional.of(() -> fluidInventory);
    protected ItemStackHandler inventory = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    protected LazyOptional<IItemHandler> itemCapability = LazyOptional.of(() -> inventory);
    protected EnergyStorage energyStorage = new EnergyStorage(10000);
    protected LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energyStorage);

    protected BlockPos energyCommunication;
    protected BlockPos fluidCommunication;
    protected BlockPos controller;


    public CombiSteamerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(CAFBlockEntities.COMBI_STEAMER_ENTITY.get(), pPos, pBlockState);
    }

    public <T extends BlockEntity> void tick(Level pLevel, BlockState pState, BlockPos pPos, T pBlockEntity) {
        CombiSteamerBlockEntity entity = (CombiSteamerBlockEntity) pBlockEntity;

        if (pState.getBlock() instanceof ICommunicationBlock iCommunicationBlock) {
            if (iCommunicationBlock.isEnergyCommunication())
                energyCommunication = pPos;
            if (iCommunicationBlock.isFluidCommunication())
                fluidCommunication = pPos;
        } else if (pState.getBlock() instanceof ICombiSteamerBlock iCombiSteamerBlock) {
            if (iCombiSteamerBlock.isController())
                controller = pPos;
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        CombiSteamerBlockEntity entity = (CombiSteamerBlockEntity) level.getBlockEntity(controller);
        if (entity == null)
            return false;
        return containedFluidTooltip(tooltip, isPlayerSneaking, entity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY))
                && containedEnergyTooltip(tooltip, isPlayerSneaking, entity.getCapability(EnergyCapability.ENERGY));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (level.getBlockEntity(fluidCommunication) != null) {
            if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
                Direction dir = level.getBlockState(fluidCommunication).getValue(CombiSteamerBaseBlock.FACING);
                if (side == dir.getOpposite())
                    return fluidCapability.cast();
            }
        }
        if (level.getBlockEntity(energyCommunication) != null) {
            if (cap == EnergyCapability.ENERGY) {
                Direction dir = level.getBlockState(energyCommunication).getValue(CombiSteamerBaseBlock.FACING);
                if (side == dir.getOpposite())
                    return energyCapability.cast();
            }
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        fluidCapability.invalidate();
        itemCapability.invalidate();
        energyCapability.invalidate();
    }
}
