package net.egorplaytv.create_and_food.screen;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.custom.FermentationBarrelBlockEntity;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.egorplaytv.create_and_food.screen.slot.ModResultSlot;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FermentationBarrelMenu extends AbstractContainerMenu {
    public final FermentationBarrelBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private FluidStack fluidStack;
    public FermentationBarrelMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public FermentationBarrelMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.FERMENTATION_BARREL_MENU.get(), pContainerId);
    // DON'T FORGET TO CHANGE THE NUMBER\/
        checkContainerSize(inv, 6);
        blockEntity = (FermentationBarrelBlockEntity) entity;
        level = inv.player.level;
        this.data = data;
        this.fluidStack = blockEntity.getFluid();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 11,20));
            this.addSlot(new SlotItemHandler(handler, 1, 54,20));
            this.addSlot(new SlotItemHandler(handler, 2, 54,38));
            this.addSlot(new SlotItemHandler(handler, 3, 54,56));
            this.addSlot(new SlotItemHandler(handler, 4, 79,20));
            this.addSlot(new ModResultSlot(handler, 5, 106,38));

        });

        addDataSlots(data);
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


        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = slot.getItem();
        ItemStack itemstack = sourceStack.copy();

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
        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.FERMENTATION_BARREL.get());
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
