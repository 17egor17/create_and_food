package net.egorplaytv.caf.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class WorktableMenu extends AbstractContainerMenu {
    private final Container container;
    private int size;

    private WorktableMenu(MenuType<?> pMenuType, int pContainerId, Inventory pPlayerInventory, int size, boolean isDouble) {
        this(pMenuType, pContainerId, pPlayerInventory, new SimpleContainer(size), size, isDouble);
    }

    public static WorktableMenu worktable(int pContainerId, Inventory pPlayerInventory) {
        return new WorktableMenu(CAFMenuTypes.WORKTABLE_MENU.get(), pContainerId, pPlayerInventory, 9, false);
    }

    public static WorktableMenu worktableDouble(int pContainerId, Inventory pPlayerInventory) {
        return new WorktableMenu(CAFMenuTypes.WORKTABLE_DOUBLE_MENU.get(), pContainerId, pPlayerInventory, 18, true);
    }

    public static WorktableMenu worktable(int pContainerId, Inventory pPlayerInventory, Container container) {
        return new WorktableMenu(CAFMenuTypes.WORKTABLE_MENU.get(), pContainerId, pPlayerInventory, container, 9, false);
    }

    public static WorktableMenu worktableDouble(int pContainerId, Inventory pPlayerInventory, Container container) {
        return new WorktableMenu(CAFMenuTypes.WORKTABLE_DOUBLE_MENU.get(), pContainerId, pPlayerInventory, container, 18, true);
    }


    protected WorktableMenu(@Nullable MenuType<?> pMenuType, int pContainerId, Inventory pPlayerInventory, Container container, int size, boolean isDouble) {
        super(pMenuType, pContainerId);
        checkContainerSize(container, size);
        this.container = container;
        this.size = size;

        if (isDouble) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(container, i, 8 + i * 18, 18));
            }
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(container, j + 9, 8 + j * 18, 36));
            }
        } else {
            for (int k = 0; k < 5; ++k) {
                this.addSlot(new Slot(container, k, 17 + k * 18, 18));
            }
            for (int g = 0; g < 4; ++g) {
                this.addSlot(new Slot(container, g + 5, 26 + g * 18, 36));
            }
        }

        addPlayerInventory(pPlayerInventory);
        addPlayerHotbar(pPlayerInventory);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex < this.size) {
                if (!this.moveItemStackTo(itemstack1, this.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 11 + l * 18,71 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i =0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 11 + i * 18, 129));
        }
    }

    public int getSize() {
        return size;
    }
}
