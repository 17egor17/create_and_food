package net.egorplaytv.create_and_food.block.entity.custom;

import com.google.common.collect.Maps;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.screen.SampleOfMetalsMenu;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TerminalBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.is(Tags.Items.INGOTS);
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public static final Map<Item, Integer> METALS = Maps.newLinkedHashMap();
    public static final Map<Item, Integer> INGREDIENTS_LINES = Maps.newLinkedHashMap();
    public static final Map<Item, String> TYPES = Maps.newLinkedHashMap();
    public static final Map<Item, String> TAGS = Maps.newLinkedHashMap();
    public static String modId;

    public static Map<Item, Integer> getMetals(){
        add(ModTags.Items.ALUMINUM, 7, "metal", "aluminum");
        add(ModTags.Items.BRASS, 7, "alloy", "brass");
        add(ModTags.Items.BRONZE, 7, "alloy", "bronze");
        add(ModTags.Items.CONSTANTAN, 7, "alloy", "constantan");
        add(Tags.Items.INGOTS_COPPER, 7, "metal", "copper");
        add(ModTags.Items.ELECTRUM, 7, "alloy", "electrum");
        add(ModTags.Items.ENDERIUM, 7, 2, "alloy", "enderium");
        add(Tags.Items.INGOTS_GOLD, 7, "metal", "gold");
        add(ModTags.Items.INVAR, 7, "alloy", "invar");
        add(Tags.Items.INGOTS_IRON, 7, "metal", "iron");
        add(ModTags.Items.LEAD, 7, "metal", "lead");
        add(ModTags.Items.LUMIUM, 7, 2, "alloy", "lumium");
        add(Tags.Items.INGOTS_NETHERITE, 7, "alloy", "netherite");
        add(ModTags.Items.NICKEL, 7, "metal", "nickel");
        add(ModTags.Items.OSMIUM, 7, "metal", "osmium");
        add(ModTags.Items.SIGNALUM, 7, "alloy", "signalum");
        add(ModTags.Items.SILVER, 7, "metal", "silver");
        add(ModTags.Items.STEEL, 7, "alloy", "steel");
        add(ModTags.Items.TIN, 7, "metal", "tin");
        add(ModTags.Items.TANTALUM, 7, "metal", "tantalum");
        add(ModTags.Items.TUNGSTEN, 7, "metal", "tungsten");
        add(ModTags.Items.URANIUM, 7, "metal", "uranium");
        add(ModTags.Items.ZINC, 7, "metal", "zinc");
        return METALS;
    }

    public static Map<Item, Integer> getIngredientsLines(){
        return INGREDIENTS_LINES;
    }

    public static Map<Item, String> getTypes(){
        return TYPES;
    }

    public static Map<Item, String> getTags(){
        return TAGS;
    }

    public static void setModId(String modId){
        TerminalBlockEntity.modId = modId;
    }

    public static String getModId() {
        return modId;
    }

    private static void add(TagKey<Item> pItemTag, int lines, String type, String tag){
        add(pItemTag, lines, 1, type, tag);
    }

    private static void add(TagKey<Item> pItemTag, int lines, int countIngredientLines, String type, String tag) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (lines > 0 && lines <= 7) {
                METALS.put(holder.value(), lines);
                INGREDIENTS_LINES.put(holder.value(), countIngredientLines);
                TYPES.put(holder.value(), type);
                TAGS.put(holder.value(), tag);
            }
        }

    }

    private static void add(ItemLike pItem, int lines, String type) {
        add(pItem, lines, 1, type);
    }

    private static void add(ItemLike pItem, int lines, int countIngredientLines, String type) {
        Item item = pItem.asItem();
        if (SharedConstants.IS_RUNNING_IN_IDE) {
            throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack) null).getString() + " a furnace fuel. That will not work!"));
        }
        if (lines > 0 && lines <= 7) {
            METALS.put(item, lines);
            INGREDIENTS_LINES.put(item, countIngredientLines);
            TYPES.put(item, type);
        }
    }


    public ItemStack getItem(int index){
        return itemHandler.getStackInSlot(index);
    }
    public Component getItemName(){
        return itemHandler.getStackInSlot(0).getItem().getName(itemHandler.getStackInSlot(0));
    }
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public TerminalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TABLET_ENTITY.get(), pPos, pBlockState);
        getMetals();
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SampleOfMetalsMenu(pContainerId, pPlayerInventory, this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return this.lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
