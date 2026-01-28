package net.egorplaytv.caf.block.entity.custom;

import com.simibubi.create.AllFluids;
import com.simibubi.create.foundation.fluid.CombinedTankWrapper;
import net.egorplaytv.caf.block.custom.FermentationBarrelBlock;
import net.egorplaytv.caf.block.entity.ModBlockEntities;
import net.egorplaytv.caf.entity.WrappedFluidHandler;
import net.egorplaytv.caf.entity.WrappedHandler;
import net.egorplaytv.caf.networking.CAFMessages;
import net.egorplaytv.caf.networking.packet.FermantionBarrelFluidPacket;
import net.egorplaytv.caf.networking.packet.FermantionBarrelFluidPacketOut;
import net.egorplaytv.caf.recipe.FermentationBarrelRecipe;
import net.egorplaytv.caf.screen.FermentationBarrelMenu;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FermentationBarrelBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent() || stack.is(CAFTags.Items.BOTTLES);
                case 1, 2, 3, 4 -> true;
                case 5 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    private final SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());

    private final FluidTank FLUID_TANK_IN = new FluidTank(4000){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) {
                CAFMessages.sendToClients(new FermantionBarrelFluidPacket(this.fluid, worldPosition));
            }
        }
    };

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK_IN.setFluid(stack);
    }

    public FluidStack getFluid() {
        return this.FLUID_TANK_IN.getFluid();
    }

    private final FluidTank FLUID_TANK_OUT = new FluidTank(4000){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) {
                CAFMessages.sendToClients(new FermantionBarrelFluidPacketOut(this.fluid, worldPosition));
            }
        }
    };

    public void setFluidOut(FluidStack stack) {
        this.FLUID_TANK_OUT.setFluid(stack);
    }

    public FluidStack getFluidOut() {
        return this.FLUID_TANK_OUT.getFluid();
    }

    public ItemStack getItem(int index){
        return itemHandler.getStackInSlot(index);
    }
    private final LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(() -> itemHandler);
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 5, (i, s) -> false)),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2,
                                    (index, stack) -> itemHandler.isItemValid(2, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 4,
                                    (index, stack) -> itemHandler.isItemValid(4, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 3,
                                    (index, stack) -> itemHandler.isItemValid(3, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                                    (index, stack) -> itemHandler.isItemValid(1, stack))));
    private final LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.of(() -> {
        LazyOptional<? extends IFluidHandler> inputCap = LazyOptional.of(() -> FLUID_TANK_IN);
        LazyOptional<? extends IFluidHandler> outputCap = LazyOptional.of(() -> FLUID_TANK_OUT);
        return new CombinedTankWrapper(inputCap.orElse(null), outputCap.orElse(null));
    });

    private final Map<Direction, LazyOptional<?>> directionWrappedFluidHandlerMap =
            Map.of(Direction.UP, LazyOptional.of(() -> new WrappedFluidHandler(FLUID_TANK_IN, FLUID_TANK_OUT)),
                    Direction.DOWN, LazyOptional.empty(),
                    Direction.WEST, LazyOptional.empty(),
                    Direction.SOUTH, LazyOptional.empty(),
                    Direction.EAST, LazyOptional.empty(),
                    Direction.NORTH, LazyOptional.empty());
    int progress = 0;
    int time;
    protected final ContainerData data;
    public FermentationBarrelBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FERMENTATION_BARREL_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return FermentationBarrelBlockEntity.this.progress;
                    case 1:
                        return FermentationBarrelBlockEntity.this.time;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        FermentationBarrelBlockEntity.this.progress = value;
                        break;
                    case 1:
                        FermentationBarrelBlockEntity.this.time = value;
                        break;
                }
            }

            public int getCount() {
                return 2;
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
        CAFMessages.sendToClients(new FermantionBarrelFluidPacket(this.getFluid(), worldPosition));
        CAFMessages.sendToClients(new FermantionBarrelFluidPacketOut(this.getFluidOut(), worldPosition));
        return new FermentationBarrelMenu(pContainerId, pInventory, this, this.data);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) {
                return lazyItemHandler.cast();
            }

            if (directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(FermentationBarrelBlock.FACING);

                if (side == Direction.DOWN) {
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

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (side == null) {
                return lazyFluidHandler.cast();
            }

            if (side == Direction.UP) {
                return directionWrappedFluidHandlerMap.get(side).cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("fermentation_barrel.progress", progress);
        tag.putInt("fermentation_barrel.time", time);
        tag.put("inputFluid", getFluid().writeToNBT(new CompoundTag()));
        tag.put("outputFluid", getFluidOut().writeToNBT(new CompoundTag()));
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("fermentation_barrel.progress");
        time = tag.getInt("fermentation_barrel.time");
        setFluid(FluidStack.loadFluidStackFromNBT(tag.getCompound("inputFluid")));
        setFluidOut(FluidStack.loadFluidStackFromNBT(tag.getCompound("outputFluid")));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, FermentationBarrelBlockEntity pBlockEntity) {
        if (pLevel.isClientSide) {
            return;
        }

        if (hasRecipe(pBlockEntity, pLevel) && !pBlockEntity.FLUID_TANK_IN.isEmpty()) {
            for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
                pBlockEntity.inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
            }
            Optional<FermentationBarrelRecipe> recipe = pLevel.getRecipeManager()
                    .getRecipeFor(FermentationBarrelRecipe.Type.INSTANCE, pBlockEntity.inventory, pLevel);

            pBlockEntity.progress++;
            if (recipe.isPresent()) {
                pBlockEntity.time = recipe.map(FermentationBarrelRecipe::getTime).orElse(1000);
            }
            setChanged(pLevel, pPos, pState);
            if (pBlockEntity.progress > pBlockEntity.time) {
                craft(pBlockEntity, pLevel);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }

        if (hasFluidItem(pBlockEntity)) {
            transferFluidItem(pBlockEntity);
        }
    }

    private static void transferFluidItem(FermentationBarrelBlockEntity pBlockEntity) {
        if (pBlockEntity.itemHandler.getStackInSlot(0).is(CAFTags.Items.BOTTLES)) {
            ItemStack bottle = pBlockEntity.itemHandler.getStackInSlot(0);
            FluidStack fluid = FluidStack.EMPTY;

            if (bottle.getItem() instanceof HoneyBottleItem) {
                fluid = new FluidStack(AllFluids.HONEY.get().getSource(), 250);
            } else if (bottle.getItem() instanceof PotionItem) {
                if (PotionUtils.getPotion(bottle) == Potions.WATER) {
                    fluid = new FluidStack(Fluids.WATER, 250);
                } else {
                    return;
                }
            }

            if (!fluid.isEmpty() && pBlockEntity.FLUID_TANK_IN.isFluidValid(fluid)) {
                pBlockEntity.itemHandler.extractItem(0, 1, false);
                pBlockEntity.itemHandler.setStackInSlot(0, new ItemStack(Items.GLASS_BOTTLE));

                pBlockEntity.FLUID_TANK_IN.fill(fluid, IFluidHandler.FluidAction.EXECUTE);
            }
        } else {
            pBlockEntity.itemHandler.getStackInSlot(0).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
                int drainAmount = Math.min(pBlockEntity.FLUID_TANK_IN.getSpace(), 1000);

                FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
                if (pBlockEntity.FLUID_TANK_IN.isFluidValid(stack)) {
                    stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                    fillFluidTank(pBlockEntity, stack, handler.getContainer());
                }
            });
        }
    }

    private static void fillFluidTank(FermentationBarrelBlockEntity pBlockEntity, FluidStack stack, ItemStack container) {
        pBlockEntity.FLUID_TANK_IN.fill(stack, IFluidHandler.FluidAction.EXECUTE);

        pBlockEntity.itemHandler.extractItem(0, 1, false);
        pBlockEntity.itemHandler.insertItem(0, container, false);
    }

    private static boolean hasFluidItem(FermentationBarrelBlockEntity pBlockEntity) {
        ItemStack stack = pBlockEntity.itemHandler.getStackInSlot(0);

        if (stack.getItem() instanceof HoneyBottleItem) {
            return stack.getCount() > 0;
        } else if (stack.getItem() instanceof PotionItem) {
            if (PotionUtils.getPotion(stack) == Potions.WATER) {
                return stack.getCount() > 0;
            } else {
                return false;
            }
        } else if (pBlockEntity.FLUID_TANK_IN.getFluid().getFluid().getBucket() == stack.getItem()) {
            return stack.getCount() > 0;
        } else if (pBlockEntity.FLUID_TANK_IN.isEmpty()){
            return stack.getCount() > 0;
        } else {
            return false;
        }
    }

    private static boolean hasRecipe(FermentationBarrelBlockEntity entity, Level level) {
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            entity.inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<FermentationBarrelRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(FermentationBarrelRecipe.Type.INSTANCE, entity.inventory, level);

        if (recipe.isPresent()){
            boolean fluidInTankEqualsRecipeFluid = false;
            List<FluidStack> fluid = recipe.get().getInputFluid();
            for (FluidStack fluidStack : fluid) {
                fluidInTankEqualsRecipeFluid = entity.FLUID_TANK_IN.getFluid().equals(fluidStack);
            }
            if (!recipe.get().getInputTool().isEmpty() && !recipe.get().getResultFluid().isEmpty()
                    && !recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertItemIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertAmountIntoOutputFluidTank(entity, recipe.get().getResultFluid().getAmount())
                        && canInsertFluidIntoOutputFluidTank(entity, recipe.get().getResultFluid())
                        && canToolInSlot(entity, recipe.get().getInputTool())
                        && fluidInTankEqualsRecipeFluid;
            } else if (!recipe.get().getInputTool().isEmpty() && !recipe.get().getResultFluid().isEmpty()
                    && recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputFluidTank(entity, recipe.get().getResultFluid().getAmount())
                        && canInsertFluidIntoOutputFluidTank(entity, recipe.get().getResultFluid())
                        && canToolInSlot(entity, recipe.get().getInputTool())
                        && fluidInTankEqualsRecipeFluid;
            } else if (!recipe.get().getInputTool().isEmpty() && recipe.get().getResultFluid().isEmpty()
                    && !recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertItemIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canToolInSlot(entity, recipe.get().getInputTool())
                        && fluidInTankEqualsRecipeFluid;
            } else if (recipe.get().getInputTool().isEmpty() && !recipe.get().getResultFluid().isEmpty()
                    && recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputFluidTank(entity, recipe.get().getResultFluid().getAmount())
                        && canInsertFluidIntoOutputFluidTank(entity, recipe.get().getResultFluid())
                        && entity.itemHandler.getStackInSlot(4).isEmpty()
                        && fluidInTankEqualsRecipeFluid;
            } else if (recipe.get().getInputTool().isEmpty() && recipe.get().getResultFluid().isEmpty()
                    && !recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertItemIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && entity.itemHandler.getStackInSlot(4).isEmpty()
                        && fluidInTankEqualsRecipeFluid;
            } else if (recipe.get().getInputTool().isEmpty() && !recipe.get().getResultFluid().isEmpty()
                    && !recipe.get().getResultItem().isEmpty()) {
                return canInsertAmountIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertItemIntoOutputSlot(entity.inventory, recipe.get().getResultItem())
                        && canInsertAmountIntoOutputFluidTank(entity, recipe.get().getResultFluid().getAmount())
                        && canInsertFluidIntoOutputFluidTank(entity, recipe.get().getResultFluid())
                        && entity.itemHandler.getStackInSlot(4).isEmpty()
                        && fluidInTankEqualsRecipeFluid;
            }
        }
        return false;
    }

    private static boolean canToolInSlot(FermentationBarrelBlockEntity entity, ItemStack inputTool) {
        return entity.itemHandler.getStackInSlot(4).getItem() == inputTool.getItem();
    }

    private static void craft(FermentationBarrelBlockEntity entity, Level level) {
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            entity.inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<FermentationBarrelRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(FermentationBarrelRecipe.Type.INSTANCE, entity.inventory, level);

        if (recipe.isPresent()){
            entity.FLUID_TANK_IN.drain(recipe.get().getInputFluidIngredient().getRequiredAmount(), IFluidHandler.FluidAction.EXECUTE);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);
            entity.itemHandler.extractItem(3, 1, false);
            if (!recipe.get().getInputTool().isEmpty()) {
                if (entity.itemHandler.getStackInSlot(4).isDamageableItem()) {
                    entity.itemHandler.getStackInSlot(4).hurt(1, level.random, (ServerPlayer) null);
                } else {
                    entity.itemHandler.extractItem(4, 1, false);
                }
            }

            if (!recipe.get().getResultItem().isEmpty()){
                entity.itemHandler.setStackInSlot(5, new ItemStack(recipe.get().getResultItem().getItem(),
                        entity.itemHandler.getStackInSlot(5).getCount() + recipe.get().getResultItem().getCount()));
            }
            if (!recipe.get().getResultFluid().isEmpty()){
                entity.FLUID_TANK_OUT.fill(recipe.get().getResultFluid(), IFluidHandler.FluidAction.EXECUTE);
            }

            entity.resetProgress();
        }
    }

    private void resetProgress () {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(5).getItem() == output.getItem() || inventory.getItem(5).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(5).getMaxStackSize() >= inventory.getItem(5).getCount() + output.getCount();
    }

    private static boolean canInsertFluidIntoOutputFluidTank(FermentationBarrelBlockEntity entity, FluidStack output) {
        return entity.FLUID_TANK_OUT.getFluid() == output || entity.FLUID_TANK_OUT.isEmpty();
    }

    private static boolean canInsertAmountIntoOutputFluidTank(FermentationBarrelBlockEntity entity, int amount) {
        return entity.FLUID_TANK_OUT.getSpace() >= entity.FLUID_TANK_OUT.getFluid().getAmount() + amount;
    }
}
