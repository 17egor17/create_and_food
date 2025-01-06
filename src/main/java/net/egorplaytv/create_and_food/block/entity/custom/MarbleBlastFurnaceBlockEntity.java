package net.egorplaytv.create_and_food.block.entity.custom;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.MarbleBlastFurnaceBlock;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.entity.WrappedHandler;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.egorplaytv.create_and_food.screen.MarbleBlastFurnaceMenu;
import net.egorplaytv.create_and_food.util.ModTags;
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
                case 0 -> stack.is(ModTags.Items.FUEL);
                case 1, 2, 3 -> true;
                case 4 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public ItemStack getItem(int index){
        return itemHandler.getStackInSlot(index);
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
    private int progress_deg = 0;
    private int progress_tick = 0;
    private int progress = 0;
    private int deg;
    private int time;
    private final Object2IntOpenHashMap<ResourceLocation> experience;

    public MarbleBlastFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MARBLE_BLAST_FURNACE_ENTITY.get(), pPos, pBlockState);
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

        if (hasRecipe(pBlockEntity, pLevel)){
            pBlockEntity.deg = recipe.map(MarbleFurnaceRecipe::getDeg).orElse(100);
            pBlockEntity.time = recipe.map(MarbleFurnaceRecipe::getTime).orElse(300);
        } else {
            pBlockEntity.resetDeg();
            setChanged(pLevel, pPos, pState);
        }

        if (hasRecipe(pBlockEntity, pLevel) && hasDegree(pBlockEntity, recipe.get().getDeg())) {
            if (pBlockEntity.progress_deg > recipe.get().getDeg() + 4000){
                int time = 10;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg > recipe.get().getDeg() + 1000){
                int time = 5;
                pBlockEntity.progress = pBlockEntity.progress + time;
            } else if (pBlockEntity.progress_deg < recipe.get().getDeg() ||
                    pBlockEntity.progress_deg >= recipe.get().getDeg()){
                pBlockEntity.progress++;
            }
            pBlockEntity.RecipeExperience(recipe.get());
            setChanged(pLevel, pPos, pState);
            if (pBlockEntity.progress >= pBlockEntity.time) {
                craftItem(pBlockEntity, pLevel);
            }
        } else if (hasRecipe(pBlockEntity, pLevel) && pBlockEntity.progress_deg < pBlockEntity.deg){
            if (pBlockEntity.progress > 0) {
                pBlockEntity.progress--;
                setChanged(pLevel, pPos, pState);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }


        if (hasFuel(pBlockEntity)) {
            if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_1000)) {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 1000;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_BUCKET)) {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 100;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
                pBlockEntity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_100)){
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 100;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_90)){
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 90;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if(pBlockEntity.itemHandler.getStackInSlot(0).is(ItemTags.BOATS)) {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 75;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_15)){
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 15;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_10)){
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 10;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_5)) {
                pBlockEntity.progress_deg = pBlockEntity.progress_deg + 5;
                pBlockEntity.itemHandler.extractItem(0, 1, false);
            }
        }
        if (hasTemperature(pBlockEntity)){
            int tick = CreateAndFoodCommonConfigs.SPEED_ATTENUATION_FURNACE.get() * 20;
            pBlockEntity.progress_tick++;
            if (pBlockEntity.progress_tick >= tick){
                pBlockEntity.progress_deg--;
                pBlockEntity.resetProgressTick();
            }
        }
        if (hasTemperature(pBlockEntity)) {
            pState = pState.setValue(MarbleBlastFurnaceBlock.LIT, Boolean.valueOf(hasTemperature(pBlockEntity)));
            pLevel.setBlock(pPos, pState, 3);
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
        if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_1000) && pBlockEntity.progress_deg <= 8000){
            return true;
        } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_BUCKET) ||
                pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_100) && pBlockEntity.progress_deg <= 8900){
            return true;
        } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_90) && pBlockEntity.progress_deg <= 8910) {
            return true;
        } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_15) && pBlockEntity.progress_deg <= 8985){
            return true;
        } else if (pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_10) && pBlockEntity.progress_deg <= 8990){
            return true;
        } else
            return pBlockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.FUEL_5) && pBlockEntity.progress_deg <= 8995;
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack resultItem) {
        return inventory.getItem(4).getItem() == resultItem.getItem() || inventory.getItem(4).isEmpty();
    }
    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack resultItem) {
        return inventory.getItem(4).getMaxStackSize() >= inventory.getItem(4).getCount() + resultItem.getCount();
    }
}
