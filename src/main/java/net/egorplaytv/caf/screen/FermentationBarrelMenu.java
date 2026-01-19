package net.egorplaytv.caf.screen;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.entity.custom.FermentationBarrelBlockEntity;
import net.egorplaytv.caf.screen.slot.CAFResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class FermentationBarrelMenu extends AbstractContainerMenu {
    public final FermentationBarrelBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public final ItemStackHandler inventory;
    private FluidStack fluidStack;
    private FluidStack fluidStackOut;
    public FermentationBarrelMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, getTileEntity(inv, extraData), new SimpleContainerData(2));
    }
    public FermentationBarrelMenu(int pContainerId, Inventory inv, FermentationBarrelBlockEntity entity, ContainerData data) {
        super(CAFMenuTypes.FERMENTATION_BARREL_MENU.get(), pContainerId);
//    // DON'T FORGET TO CHANGE THE NUMBER\/
//        checkContainerSize(inv, 6);
        this.blockEntity = entity;
        this.inventory = entity.getItemHandler();
        this.level = inv.player.level;
        this.data = data;
        this.fluidStack = blockEntity.getFluid();
        this.fluidStackOut = blockEntity.getFluidOut();

        this.addSlot(new SlotItemHandler(this.inventory, 0, 11,20));
        this.addSlot(new SlotItemHandler(this.inventory, 1, 54,20));
        this.addSlot(new SlotItemHandler(this.inventory, 2, 54,38));
        this.addSlot(new SlotItemHandler(this.inventory, 3, 54,56));
        this.addSlot(new SlotItemHandler(this.inventory, 4, 79,20));
        this.addSlot(new CAFResultSlot(this.inventory, 5, 106,38));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        addDataSlots(data);
    }

    private static FermentationBarrelBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof FermentationBarrelBlockEntity) {
            return (FermentationBarrelBlockEntity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public FermentationBarrelBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public void setFluid(FluidStack stack) {
        this.fluidStack = stack;
    }

    public FluidStack getFluidStack() {
        return fluidStack;
    }

    public void setFluidOut(FluidStack stack) {
        this.fluidStackOut = stack;
    }

    public FluidStack getFluidStackOut() {
        return fluidStackOut;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        int te_inventory_slot_count = 6; // Your count slots!

        int vanilla_slot_count = 36;
        int vanilla_first_slot_index = 0;
        int te_inventory_first_slot_index = vanilla_first_slot_index + vanilla_slot_count; //36
        int vanilla_all_slots_count = te_inventory_first_slot_index + te_inventory_slot_count;


        Slot slot = (Slot) slots.get(index);
        ItemStack sourceStack = slot.getItem();
        ItemStack itemstack = sourceStack.copy();
        if (slot != null && slot.hasItem()) {
            if (index < te_inventory_first_slot_index) {
                if (!moveItemStackTo(sourceStack, te_inventory_first_slot_index, vanilla_all_slots_count, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < vanilla_all_slots_count) {
                if (!moveItemStackTo(sourceStack, vanilla_first_slot_index, te_inventory_first_slot_index, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                System.out.println("Invalid slotIndex:" + index);
                return ItemStack.EMPTY;
            }

            if (sourceStack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(playerIn, sourceStack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, CAFBlocks.FERMENTATION_BARREL.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 29 + l * 18,98 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i =0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 29 + i * 18, 156));
        }
    }


}
