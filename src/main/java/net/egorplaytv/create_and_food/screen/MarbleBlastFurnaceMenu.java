package net.egorplaytv.create_and_food.screen;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.custom.MarbleBlastFurnaceBlockEntity;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.egorplaytv.create_and_food.screen.slot.ModFurnaceResultSlot;
import net.egorplaytv.create_and_food.screen.slot.ModResultSlot;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Optional;

public class MarbleBlastFurnaceMenu extends AbstractContainerMenu {
    public MarbleBlastFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public MarbleBlastFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }
    public MarbleBlastFurnaceMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.BLASTING_MENU.get(), pContainerId);
    // DON'T FORGET TO CHANGE THE NUMBER\/
        checkContainerSize(inv, 5);
        blockEntity = (MarbleBlastFurnaceBlockEntity) entity;
        level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 26,86));
            this.addSlot(new SlotItemHandler(handler, 1, 16,28));
            this.addSlot(new SlotItemHandler(handler, 2, 36,28));
            this.addSlot(new SlotItemHandler(handler, 3, 26,48));
            this.addSlot(new ModFurnaceResultSlot(inv.player ,handler, 4, 120,48));

        });

        addDataSlots(data);
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public boolean isDegrees() {
        return data.get(2) > 0;
    }
    public boolean isDeg() {
        return data.get(3) > 0;
    }

    public MarbleBlastFurnaceBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 30;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    public int getDegrees() {
        int progress_deg = this.data.get(2);
        int maxProgress = 10000;
        int progressSize = 51;

        return progress_deg != 0 ? progress_deg * progressSize / maxProgress : 0;

    }
    public TranslatableComponent getDegreeProgress() {
        int progress_deg = this.data.get(2);

        return new TranslatableComponent("ui.marble_furnace.deg", progress_deg);
    }
    public TranslatableComponent getDegProgress() {
        int deg = this.data.get(3);

        return new TranslatableComponent("ui.marble_furnace.deg", deg);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        int te_inventory_slot_count = 5; // Your count slots!

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
                pPlayer, ModBlocks.MARBLE_BLAST_FURNACE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 64 + l * 18,87 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i =0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 64 + i * 18, 145));
        }
    }
}