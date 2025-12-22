package net.egorplaytv.create_and_food.screen;

import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.block.entity.custom.MarbleBlastFurnaceBlockEntity;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.config.DegreeUnits;
import net.egorplaytv.create_and_food.screen.slot.CAFFurnaceResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class MarbleBlastFurnaceMenu extends AbstractContainerMenu {
    public MarbleBlastFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public final ItemStackHandler inventory;

    public MarbleBlastFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, getTileEntity(inv, extraData), new SimpleContainerData(4));
    }

    public MarbleBlastFurnaceMenu(int pContainerId, Inventory inv, MarbleBlastFurnaceBlockEntity entity, ContainerData data) {
        super(CAFMenuTypes.BLASTING_MENU.get(), pContainerId);
//    // DON'T FORGET TO CHANGE THE NUMBER\/
//        checkContainerSize(inv, 5);
        blockEntity = entity;
        inventory = entity.getItemHandler();
        level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);


        this.addSlot(new SlotItemHandler(this.inventory, 0, 26,86));
        this.addSlot(new SlotItemHandler(this.inventory, 1, 16,28));
        this.addSlot(new SlotItemHandler(this.inventory, 2, 36,28));
        this.addSlot(new SlotItemHandler(this.inventory, 3, 26,48));
        this.addSlot(new CAFFurnaceResultSlot(inv.player, this.inventory, 4, 120,48));

        this.addDataSlots(data);
    }

    private static MarbleBlastFurnaceBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof MarbleBlastFurnaceBlockEntity) {
            return (MarbleBlastFurnaceBlockEntity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
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
        int maxProgress = 5000;
        int progressSize = 51;

        return progress_deg != 0 ? progress_deg * progressSize / maxProgress : 0;

    }
    public TranslatableComponent getDegreeProgress() {
        int progress_degC = this.data.get(2);

        if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_CELSIUS){
            return new TranslatableComponent("ui.marble_furnace.degC", progress_degC);
        } else if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_FAHRENHEIT){
            float progress_degF = progress_degC*1.8F+32;
            return new TranslatableComponent("ui.marble_furnace.degF", progress_degF);
        } else {
            float progress_degK = progress_degC+273.15F;
            return new TranslatableComponent("ui.marble_furnace.degK", progress_degK);
        }
    }
    public TranslatableComponent getDegProgress() {
        int degC = this.data.get(3);

        if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_CELSIUS){
            return new TranslatableComponent("ui.marble_furnace.degC", degC);
        } else if (CreateAndFoodCommonConfigs.getUnits() == DegreeUnits.DEGREES_FAHRENHEIT){
            float degF = degC*1.8F+32;
            return new TranslatableComponent("ui.marble_furnace.degF", degF);
        } else {
            float degK = degC+273.15F;
            return new TranslatableComponent("ui.marble_furnace.degK", degK);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        int te_inventory_slot_count = 5; // Your count slots!

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
                pPlayer, CAFBlocks.MARBLE_BLAST_FURNACE.get());
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