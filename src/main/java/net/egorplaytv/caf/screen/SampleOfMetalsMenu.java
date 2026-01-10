package net.egorplaytv.caf.screen;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.entity.custom.TerminalBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SampleOfMetalsMenu extends AbstractContainerMenu {
    public TerminalBlockEntity blockEntity;
    private final Level level;
    public SampleOfMetalsMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }
    public SampleOfMetalsMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(CAFMenuTypes.SAMPLE_MENU.get(), pContainerId);
        checkContainerSize(inv, 1);
        blockEntity = (TerminalBlockEntity) entity;
        level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    this.addSlot(new SlotItemHandler(handler, 0, 15, 15));
        });
    }

    public boolean isItem(){
        return blockEntity.getItem(0) != ItemStack.EMPTY;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        int te_inventory_slot_count = 1; // Your count slots!

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
                pPlayer, CAFBlocks.TERMINAL.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 72 + l * 18,146 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i =0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 72 + i * 18, 204));
        }
    }
}
