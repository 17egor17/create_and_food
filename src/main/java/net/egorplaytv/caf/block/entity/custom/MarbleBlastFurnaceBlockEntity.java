package net.egorplaytv.caf.block.entity.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.egorplaytv.caf.block.custom.MarbleBlastFurnaceBlock;
import net.egorplaytv.caf.block.entity.ModBlockEntities;
import net.egorplaytv.caf.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.caf.entity.WrappedHandler;
import net.egorplaytv.caf.item.custom.MeltItem;
import net.egorplaytv.caf.recipe.MarbleFurnaceRecipe;
import net.egorplaytv.caf.screen.MarbleBlastFurnaceMenu;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MarbleBlastFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.is(CAFTags.Items.FUEL);
                case 1, 2, 3 -> true;
                case 4 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public ItemStack getItem(int index){
        return itemHandler.getStackInSlot(index);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2,
                            (index, stack) -> itemHandler.isItemValid(2, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 3,
                            (index, stack) -> itemHandler.isItemValid(3, stack))),
                    Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 4, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
                            (index, stack) -> itemHandler.isItemValid(0, stack))));

    protected final ContainerData data;
    public int progress_deg = 0;
    private int progress_tick = 0;
    private int progress = 0;
    private int i = 0;
    private int deg;
    private int time;
    private boolean isCreativeDeg = false;
    private final Object2IntOpenHashMap<ResourceLocation> experience;
    public static Map<Item, Integer> FUELS = Maps.newLinkedHashMap();

    public int getDeg() {
        return progress_deg;
    }

    public static Map<Item, Integer> getFuel(){
        int i = 1000;
        int i1 = 100;
        int i2 = 90;
        int i3 = 75;
        int i4 = 15;
        int i5 = 10;
        int i6 = 5;
        add(Items.BLAZE_ROD, i1);
        add(Items.COAL, i1);
        add(Items.CHARCOAL, i1);
        add(CAFTags.Items.COAL_DUST, i1);
        add(Blocks.COAL_BLOCK, i2);
        add(ItemTags.BOATS, i3);
        add(ItemTags.LOGS, i4);
        add(ItemTags.PLANKS, i4);
        add(ItemTags.WOODEN_STAIRS, i4);
        add(ItemTags.WOODEN_SLABS, i4);
        add(ItemTags.WOODEN_TRAPDOORS, i4);
        add(Blocks.OAK_FENCE, i4);
        add(Blocks.BIRCH_FENCE, i4);
        add(Blocks.SPRUCE_FENCE, i4);
        add(Blocks.JUNGLE_FENCE, i4);
        add(Blocks.DARK_OAK_FENCE, i4);
        add(Blocks.ACACIA_FENCE, i4);
        add(Blocks.OAK_FENCE_GATE, i4);
        add(Blocks.BIRCH_FENCE_GATE, i4);
        add(Blocks.SPRUCE_FENCE_GATE, i4);
        add(Blocks.JUNGLE_FENCE_GATE, i4);
        add(Blocks.DARK_OAK_FENCE_GATE, i4);
        add(Blocks.ACACIA_FENCE_GATE, i4);
        add(Blocks.NOTE_BLOCK, i4);
        add(Blocks.BOOKSHELF, i4);
        add(Blocks.LECTERN, i4);
        add(Blocks.JUKEBOX, i4);
        add(Blocks.CHEST, i4);
        add(Blocks.TRAPPED_CHEST, i4);
        add(Blocks.CRAFTING_TABLE, i4);
        add(Blocks.DAYLIGHT_DETECTOR, i4);
        add(ItemTags.BANNERS, i4);
        add(Blocks.LADDER, i4);
        add(ItemTags.WOODEN_DOORS, i4);
        add(Blocks.DRIED_KELP_BLOCK, i4);
        add(Blocks.LOOM, i4);
        add(Blocks.BARREL, i4);
        add(Blocks.CARTOGRAPHY_TABLE, i4);
        add(Blocks.FLETCHING_TABLE, i4);
        add(Blocks.SMITHING_TABLE, i4);
        add(Blocks.COMPOSTER, i4);
        add(Items.STICK, i5);
        add(ItemTags.WOODEN_PRESSURE_PLATES, i6);
        add(Items.BOW, i6);
        add(Items.FISHING_ROD, i6);
        add(ItemTags.SIGNS, i6);
        add(Items.WOODEN_SHOVEL, i6);
        add(Items.WOODEN_SWORD, i6);
        add(Items.WOODEN_HOE, i6);
        add(Items.WOODEN_AXE, i6);
        add(Items.WOODEN_PICKAXE, i6);
        add(ItemTags.WOOL, i6);
        add(ItemTags.WOODEN_BUTTONS, i6);
        add(ItemTags.SAPLINGS, i6);
        add(Items.BOWL, i6);
        add(ItemTags.CARPETS, i6);
        add(Items.CROSSBOW, i6);
        add(Blocks.BAMBOO, i6);
        add(Blocks.DEAD_BUSH, i6);
        add(Blocks.SCAFFOLDING, i6);
        add(Blocks.AZALEA, i6);
        add(Blocks.FLOWERING_AZALEA, i6);
        return FUELS;
    }

    private static boolean isNeverAFurnaceFuel(Item pItem) {
        return pItem.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(TagKey<Item> pItemTag, int degree) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (degree > 0 && degree <= 1000) {
                if (!isNeverAFurnaceFuel(holder.value())) {
                    FUELS.put(holder.value(), degree);
                }
            }
        }

    }

    private static void add(ItemLike pItem, int degree) {
        Item item = pItem.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            if (degree > 0 && degree <= 1000) {
                FUELS.put(item, degree);
            }
        }
    }


    public MarbleBlastFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MARBLE_BLAST_FURNACE_ENTITY.get(), pPos, pBlockState);
        getFuel();
        this.experience = new Object2IntOpenHashMap<>();
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return MarbleBlastFurnaceBlockEntity.this.progress;
                    case 1:
                        return MarbleBlastFurnaceBlockEntity.this.time;
                    case 2:
                        return MarbleBlastFurnaceBlockEntity.this.progress_deg;
                    case 3:
                        return MarbleBlastFurnaceBlockEntity.this.deg;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        MarbleBlastFurnaceBlockEntity.this.progress = value;
                        break;
                    case 1:
                        MarbleBlastFurnaceBlockEntity.this.time = value;
                        break;
                    case 2:
                        MarbleBlastFurnaceBlockEntity.this.progress_deg = value;
                        break;
                    case 3:
                        MarbleBlastFurnaceBlockEntity.this.deg = value;
                        break;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return new TextComponent("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MarbleBlastFurnaceMenu(pContainerId, pInventory, this, this.data);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) {
                return lazyItemHandler.cast();
            }

            if (directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(MarbleBlastFurnaceBlock.FACING);

                if (side == Direction.DOWN || side == Direction.UP) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
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
        tag.putInt("marble_furnace.progress", progress);
        tag.putInt("marble_furnace.time", time);
        tag.putInt("marble_furnace.progress_deg", progress_deg);
        tag.putInt("marble_furnace.deg", deg);

        CompoundTag compoundRecipes = new CompoundTag();
        this.experience.forEach((recipeId, craftedAmount) -> {
            compoundRecipes.putInt(recipeId.toString(), craftedAmount);
        });
        tag.put("RecipesUsed", compoundRecipes);

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("marble_furnace.progress");
        time = tag.getInt("marble_furnace.time");
        progress_deg = tag.getInt("marble_furnace.progress_deg");
        deg = tag.getInt("marble_furnace.deg");
        itemHandler.deserializeNBT(tag.getCompound("inventory"));

        CompoundTag compoundRecipes = tag.getCompound("RecipesUsed");

        for (String key : compoundRecipes.getAllKeys()) {
            this.experience.put(new ResourceLocation(key), compoundRecipes.getInt(key));
        }
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MarbleBlastFurnaceBlockEntity pBlockEntity) {
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }
        Optional<MarbleFurnaceRecipe> recipe = pLevel.getRecipeManager()
                .getRecipeFor(MarbleFurnaceRecipe.Type.INSTANCE, inventory, pLevel);

        if (pLevel.isClientSide) {
            return;
        }

        if (hasRecipe(pBlockEntity, pLevel)) {
            pBlockEntity.deg = recipe.map(MarbleFurnaceRecipe::getDeg).orElse(100);
            pBlockEntity.time = recipe.map(MarbleFurnaceRecipe::getTime).orElse(300);
        } else {
            pBlockEntity.resetDeg();
            setChanged(pLevel, pPos, pState);
        }

        if (hasRecipe(pBlockEntity, pLevel) && hasDegree(pBlockEntity, recipe.get().getDeg()) && hasSmeltingPointIsFull(pBlockEntity)) {
            if (pBlockEntity.progress_deg > recipe.get().getDeg() + 4000) {
                int time = 40;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg > recipe.get().getDeg() + 3000) {
                int time = 30;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg > recipe.get().getDeg() + 2000) {
                int time = 20;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg > recipe.get().getDeg() + 1000) {
                int time = 10;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg > recipe.get().getDeg() + 500) {
                int time = 5;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg < recipe.get().getDeg() ||
                    pBlockEntity.progress_deg >= recipe.get().getDeg()) {
                pBlockEntity.progress++;
            }

            pBlockEntity.RecipeExperience(recipe.get());
            setChanged(pLevel, pPos, pState);

            if (pBlockEntity.progress >= pBlockEntity.time) {
                craftItem(pBlockEntity, pLevel);
            }
        } else if (hasRecipe(pBlockEntity, pLevel) && pBlockEntity.progress_deg < pBlockEntity.deg) {
            if (pBlockEntity.progress > 0) {
                pBlockEntity.progress--;
                setChanged(pLevel, pPos, pState);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }


        if (hasFuel(pBlockEntity)) {
            if (pBlockEntity.itemHandler.getStackInSlot(0).is(CAFTags.Items.CREATIVE_FUEL)) {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem());
            } else {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem());
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            }
        }

        if (pBlockEntity.itemHandler.getStackInSlot(0).is(CAFTags.Items.CREATIVE_FUEL)) {
            pBlockEntity.isCreativeDeg = true;
        } else if (pBlockEntity.itemHandler.getStackInSlot(0).isEmpty()
                || !pBlockEntity.itemHandler.getStackInSlot(0).is(CAFTags.Items.CREATIVE_FUEL)) {
            pBlockEntity.isCreativeDeg = false;
        }

        if (!pBlockEntity.isCreativeDeg) {
            if (hasTemperature(pBlockEntity)) {
                int tick = CreateAndFoodCommonConfigs.SPEED_ATTENUATION_FURNACE.get() * 20;
                pBlockEntity.progress_tick++;
                if (pBlockEntity.progress_tick >= tick) {
                    pBlockEntity.progress_deg--;
                    pBlockEntity.resetProgressTick();
                }
            }
        }
        if (hasTemperature(pBlockEntity)) {
            pState = pState.setValue(MarbleBlastFurnaceBlock.LIT, Boolean.valueOf(hasTemperature(pBlockEntity)));
            pLevel.setBlock(pPos, pState, 3);


            int maxDegreeRange = 0;
            if (pBlockEntity.progress_deg > 24) {
                ItemStack itemStackSlot1 = pBlockEntity.itemHandler.getStackInSlot(1);
                ItemStack itemStackSlot2 = pBlockEntity.itemHandler.getStackInSlot(2);
                ItemStack itemStackSlot3 = pBlockEntity.itemHandler.getStackInSlot(3);

                if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot2.getItem() instanceof MeltItem slot2
                        && itemStackSlot3.getItem() instanceof MeltItem slot3) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (40 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (40 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (40 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (30 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (30 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (30 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (20 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (20 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (20 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (10 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (10 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (10 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (5 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (5 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (5 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot2.getItem() instanceof MeltItem slot2) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (40 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (40 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (30 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (30 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (20 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (20 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (10 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (10 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (5 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (5 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot3.getItem() instanceof MeltItem slot3) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (40 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (40 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (30 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (30 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (20 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (20 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (10 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (10 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (5 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (5 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot2.getItem() instanceof MeltItem slot2 && itemStackSlot3.getItem() instanceof MeltItem slot3) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (40 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (40 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (30 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (30 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (20 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (20 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (10 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (10 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (5 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (5 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot1.getItem() instanceof MeltItem slot1) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (40 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (30 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (20 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (10 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                        } else {
                            slot1.setDeg(itemStackSlot1, slot1.getDeg(itemStackSlot1) <= slot1.getMeltingPoint() + maxDegreeRange
                                    ? slot1.getDeg(itemStackSlot1) + (5 * slot1.getHeatingSpeed()) : slot1.getDeg(itemStackSlot1));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot2.getItem() instanceof MeltItem slot2) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (40 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (20 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (10 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        } else {
                            slot2.setDeg(itemStackSlot2, slot2.getDeg(itemStackSlot2) <= slot2.getMeltingPoint() + maxDegreeRange
                                    ? slot2.getDeg(itemStackSlot2) + (5 * slot2.getHeatingSpeed()) : slot2.getDeg(itemStackSlot2));
                        }
                        pBlockEntity.i = 0;
                    }
                } else if (itemStackSlot3.getItem() instanceof MeltItem slot3) {
                    pBlockEntity.i++;
                    if (pBlockEntity.i >= 20) {
                        if (pBlockEntity.progress_deg >= 4000) {
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (40 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 2000) {
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (30 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 1000) {
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (20 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else if (pBlockEntity.progress_deg >= 500) {
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (10 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        } else {
                            slot3.setDeg(itemStackSlot3, slot3.getDeg(itemStackSlot3) <= slot3.getMeltingPoint() + maxDegreeRange
                                    ? slot3.getDeg(itemStackSlot3) + (5 * slot3.getHeatingSpeed()) : slot3.getDeg(itemStackSlot3));
                        }
                        pBlockEntity.i = 0;
                    }
                }
            }
        }

        if (hasTemperatureZero(pBlockEntity)) {
            pState = pState.setValue(MarbleBlastFurnaceBlock.LIT, Boolean.valueOf(hasTemperature(pBlockEntity)));
            pLevel.setBlock(pPos, pState, 3);
        }
    }

    public void RecipeExperience(@javax.annotation.Nullable Recipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation recipeID = recipe.getId();
            this.experience.addTo(recipeID, 1);
        }

    }

    public void clearUsedRecipes(ServerPlayer player) {
        List<Recipe<?>> list = this.grantStoredRecipeExperience(player.getLevel(), player.position());
        player.awardRecipes(list);
        this.experience.clear();
    }

    public List<Recipe<?>> grantStoredRecipeExperience(ServerLevel world, Vec3 pos) {
        List<Recipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.experience.object2IntEntrySet()) {
            world.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                splitAndSpawnExperience(world, pos, entry.getIntValue(), ((AbstractCookingRecipe)recipe).getExperience());
            });
        }

        return list;
    }

    private static void splitAndSpawnExperience(ServerLevel world, Vec3 pos, int craftedAmount, float experience) {
        int expTotal = Mth.floor((float)craftedAmount * experience);
        float expFraction = Mth.frac((float)craftedAmount * experience);
        if (expFraction != 0.0F && Math.random() < (double)expFraction) {
            ++expTotal;
        }

        ExperienceOrb.award(world, pos, expTotal);
    }

    private static boolean hasSmeltingPointIsFull(MarbleBlastFurnaceBlockEntity pBlockEntity) {
        ItemStack itemStackSlot1 = pBlockEntity.itemHandler.getStackInSlot(1);
        ItemStack itemStackSlot2 = pBlockEntity.itemHandler.getStackInSlot(2);
        ItemStack itemStackSlot3 = pBlockEntity.itemHandler.getStackInSlot(3);
        if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot2.getItem() instanceof MeltItem slot2
                && itemStackSlot3.getItem() instanceof MeltItem slot3) {
            return slot1.getDeg(itemStackSlot1) >= slot1.getMeltingPoint() && slot2.getDeg(itemStackSlot2) >= slot2.getMeltingPoint()
                    && slot3.getDeg(itemStackSlot3) >= slot3.getMeltingPoint();
        } else if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot2.getItem() instanceof MeltItem slot2) {
            return slot1.getDeg(itemStackSlot1) >= slot1.getMeltingPoint() && slot2.getDeg(itemStackSlot2) >= slot2.getMeltingPoint();
        } else if (itemStackSlot1.getItem() instanceof MeltItem slot1 && itemStackSlot3.getItem() instanceof MeltItem slot3) {
            return slot1.getDeg(itemStackSlot1) >= slot1.getMeltingPoint() && slot3.getDeg(itemStackSlot3) >= slot3.getMeltingPoint();
        } else if (itemStackSlot2.getItem() instanceof MeltItem slot2
                && itemStackSlot3.getItem() instanceof MeltItem slot3) {
            return slot2.getDeg(itemStackSlot2) >= slot2.getMeltingPoint() && slot3.getDeg(itemStackSlot3) >= slot3.getMeltingPoint();
        } else if (itemStackSlot1.getItem() instanceof MeltItem slot1) {
            return slot1.getDeg(itemStackSlot1) >= slot1.getMeltingPoint();
        } else if (itemStackSlot2.getItem() instanceof MeltItem slot2) {
            return slot2.getDeg(itemStackSlot2) >= slot2.getMeltingPoint();
        } else if (itemStackSlot3.getItem() instanceof MeltItem slot3) {
            return slot3.getDeg(itemStackSlot3) >= slot3.getMeltingPoint();
        } else {
            return true;
        }
    }

    private static boolean hasRecipe(MarbleBlastFurnaceBlockEntity entity, Level level) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MarbleFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(MarbleFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory, match.get().getResultItem())
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(MarbleBlastFurnaceBlockEntity entity, Level level) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<MarbleFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(MarbleFurnaceRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent()) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);
            entity.itemHandler.extractItem(3, 1, false);
            entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + match.get().getResultItem().getCount()));

            if (entity.itemHandler.getStackInSlot(4).getItem() instanceof MeltItem item) {
                item.setDeg(entity.itemHandler.getStackInSlot(4), match.get().getDeg() - 50);
            }
            entity.resetProgress();
        }
    }
    private void resetProgress () {
        this.progress = 0;
    }
    private void resetTemperature(){
        this.progress_deg = 0;
    }
    private void resetDeg() {
        this.deg = 0;
    }
    private void resetProgressTick () {
        this.progress_tick = 0;
    }
    private static boolean hasTemperature(MarbleBlastFurnaceBlockEntity pBlockEntity) {
        return pBlockEntity.progress_deg > 0;
    }
    private static boolean hasTemperatureZero(MarbleBlastFurnaceBlockEntity pBlockEntity) {
        return pBlockEntity.progress_deg <= 0;
    }
    private static boolean hasDegree(MarbleBlastFurnaceBlockEntity pBlockEntity, int deg) {
        return pBlockEntity.progress_deg >= deg;
    }
    private static boolean hasFuel(MarbleBlastFurnaceBlockEntity pBlockEntity) {
        if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()) != null) {
            if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(1000) && pBlockEntity.progress_deg <= 4000) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(100) && pBlockEntity.progress_deg <= 4900) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(90) && pBlockEntity.progress_deg <= 4910) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(75) && pBlockEntity.progress_deg <= 4925) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(15) && pBlockEntity.progress_deg <= 4985) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(10) && pBlockEntity.progress_deg <= 4990) {
                return true;
            } else if (getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()).equals(5) && pBlockEntity.progress_deg <= 4995) {
                return true;
            } else
                return getFuel().get(pBlockEntity.itemHandler.getStackInSlot(0).getItem()) != null && pBlockEntity.progress_deg <= 4000;
        } else {
            return false;
        }
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack resultItem) {
        return inventory.getItem(4).getItem() == resultItem.getItem() || inventory.getItem(4).isEmpty();
    }
    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack resultItem) {
        return inventory.getItem(4).getMaxStackSize() >= inventory.getItem(4).getCount() + resultItem.getCount();
    }
}
