package net.egorplaytv.create_and_food.block.entity.custom;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.equipment.sandPaper.SandPaperPolishingRecipe;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.recipe.RecipeConditions;
import com.simibubi.create.foundation.recipe.RecipeFinder;
import com.simibubi.create.foundation.utility.*;
import com.simibubi.create.infrastructure.config.AllConfigs;

import net.egorplaytv.create_and_food.config.CAFConfigs;
import net.egorplaytv.create_and_food.content.kinetics.grinder.GrinderFilterSlot;
import net.egorplaytv.create_and_food.foundation.utility.CreateAndFoodLang;
import net.egorplaytv.create_and_food.recipe.AllRecipeTypes;
import net.egorplaytv.create_and_food.recipe.PolishingRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrinderBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    private static final Object polishingRecipesKey = new Object();

    public ProcessingInventory inventory;
    private int recipeIndex;
    private final LazyOptional<IItemHandler> invProvider;
    private FilteringBehaviour filtering;

    private ItemStack playEvent;
    private int textureType;

    public GrinderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        inventory = new ProcessingInventory(this::start).withSlotLimit(!AllConfigs.server().recipes.bulkCutting.get());
        inventory.remainingTime = -1;
        recipeIndex = 0;
        invProvider = LazyOptional.of(() -> inventory);
        playEvent = ItemStack.EMPTY;
        textureType = 0;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        filtering = new FilteringBehaviour(this, new GrinderFilterSlot()).forRecipes();
        behaviours.add(filtering);
        behaviours.add(new DirectBeltInputBehaviour(this));
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.put("Inventory", inventory.serializeNBT());
        compound.putInt("RecipeIndex", recipeIndex);
        compound.putInt("TextureType", textureType);
        super.write(compound, clientPacket);

        if (!clientPacket || playEvent.isEmpty())
            return;
        compound.put("PlayEvent", playEvent.serializeNBT());
        playEvent = ItemStack.EMPTY;
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        recipeIndex = compound.getInt("RecipeIndex");
        textureType = compound.getInt("TextureType");
        if (compound.contains("PlayEvent"))
            playEvent = ItemStack.of(compound.getCompound("PlayEvent"));
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return new AABB(worldPosition).inflate(.125f);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void tickAudio() {
        super.tickAudio();
        if (getSpeed() == 0)
            return;

        if (!playEvent.isEmpty()) {
            spawnEventParticles(playEvent);
            playEvent = ItemStack.EMPTY;

            AllSoundEvents.SANDING_SHORT.playAt(level, worldPosition, 3, 1, true);
        }
    }

    private void findEntities() {
        if (!inventory.isEmpty())
            return;

        Vec3 center = VecHelper.getCenterOf(worldPosition);
        AABB searchArea = new AABB(center.add(0, 0.5, 0), center.add(0, -0.5, 0)).inflate(.45f);
        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, searchArea)) {
            insertItem(itemEntity);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (getSpeed() == 0)
            return;
        if (inventory.remainingTime == -1) {
            if (!inventory.isEmpty() && !inventory.appliedRecipe)
                start(inventory.getStackInSlot(0));
            return;
        }

        if (inventory.isEmpty()) findEntities();

        float processingSpeed = Mth.clamp(Math.abs(getSpeed()) / 24, 1, 128);
        inventory.remainingTime -= processingSpeed;

        if (inventory.remainingTime > 0)
            spawnParticles(inventory.getStackInSlot(0));

        if (inventory.remainingTime < 5 && !inventory.appliedRecipe) {
            if (level.isClientSide && !isVirtual())
                return;
            playEvent = inventory.getStackInSlot(0);
            applyRecipe();
            inventory.appliedRecipe = true;
            inventory.recipeDuration = 20;
            inventory.remainingTime = 20;
            sendData();
            return;
        }

        Vec3 itemMovement = getItemMovementVec();
        Direction itemMovementFacing = Direction.getNearest(itemMovement.x, itemMovement.y, itemMovement.z);
        if (inventory.remainingTime > 0)
            return;
        inventory.remainingTime = 0;

        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack.isEmpty())
                continue;
            ItemStack tryExportingToBeltFunnel = getBehaviour(DirectBeltInputBehaviour.TYPE)
                    .tryExportingToBeltFunnel(stack, itemMovementFacing.getOpposite(), false);
            if (tryExportingToBeltFunnel != null) {
                if (tryExportingToBeltFunnel.getCount() != stack.getCount()) {
                    inventory.setStackInSlot(slot, tryExportingToBeltFunnel);
                    notifyUpdate();
                    return;
                }
                if (!tryExportingToBeltFunnel.isEmpty())
                    return;
            }
        }

        BlockPos nextPos = worldPosition.offset(itemMovement.x, itemMovement.y, itemMovement.z);
        DirectBeltInputBehaviour behaviour = BlockEntityBehaviour.get(level, nextPos, DirectBeltInputBehaviour.TYPE);
        if (behaviour != null) {
            boolean changed = false;
            if (!behaviour.canInsertFromSide(itemMovementFacing))
                return;
            if (level.isClientSide && !isVirtual())
                return;
            for (int slot = 0; slot < inventory.getSlots(); slot++) {
                ItemStack stack = inventory.getStackInSlot(slot);
                if (stack.isEmpty())
                    continue;
                ItemStack remainder = behaviour.handleInsertion(stack, itemMovementFacing, false);
                if (remainder.equals(stack, false))
                    continue;
                inventory.setStackInSlot(slot, remainder);
                changed = true;
            }
            if (changed) {
                setChanged();
                sendData();
            }
            return;
        }

        // Eject Items
        Vec3 outPos = VecHelper.getCenterOf(worldPosition)
                .add(itemMovement.scale(.5f)
                        .add(0, .5, 0));
        Vec3 outMotion = itemMovement.scale(.0625)
                .add(0, .125, 0);
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack.isEmpty())
                continue;
            ItemEntity entityIn = new ItemEntity(level, outPos.x, outPos.y, outPos.z, stack);
            entityIn.setDeltaMovement(outMotion);
            level.addFreshEntity(entityIn);
        }
        inventory.clear();
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        inventory.remainingTime = -1;
        setChanged();
        sendData();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        invProvider.invalidate();
    }

    @Override
    public void destroy() {
        super.destroy();
        ItemHelper.dropContents(level, worldPosition, inventory);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return invProvider.cast();
        return super.getCapability(cap, side);
    }

    protected void spawnEventParticles(ItemStack stack) {
        if (stack == null || stack.isEmpty())
            return;

        ParticleOptions particleData = null;
        if (stack.getItem() instanceof BlockItem)
            particleData = new BlockParticleOption(ParticleTypes.BLOCK, ((BlockItem) stack.getItem()).getBlock()
                    .defaultBlockState());
        else
            particleData = new ItemParticleOption(ParticleTypes.ITEM, stack);

        Random r = level.random;
        Vec3 v = VecHelper.getCenterOf(this.worldPosition)
                .add(0, 5 / 16f, 0);
        for (int i = 0; i < 10; i++) {
            Vec3 m = VecHelper.offsetRandomly(new Vec3(0, 0.25f, 0), r, .125f);
            level.addParticle(particleData, v.x, v.y, v.z, m.x, m.y, m.y);
        }
    }

    protected void spawnParticles(ItemStack stack) {
        if (stack == null || stack.isEmpty())
            return;

        ParticleOptions particleData = null;
        float speed = 1;
        if (stack.getItem() instanceof BlockItem)
            particleData = new BlockParticleOption(ParticleTypes.BLOCK, ((BlockItem) stack.getItem()).getBlock()
                    .defaultBlockState());
        else {
            particleData = new ItemParticleOption(ParticleTypes.ITEM, stack);
            speed = .125f;
        }

        Random r = level.random;
        Vec3 vec = getItemMovementVec();
        Vec3 pos = VecHelper.getCenterOf(this.worldPosition);
        float offset = inventory.recipeDuration != 0 ? (float) (inventory.remainingTime) / inventory.recipeDuration : 0;
        offset /= 2;
        if (inventory.appliedRecipe)
            offset -= .5f;
        level.addParticle(particleData, pos.x() + -vec.x * offset, pos.y() + .45f, pos.z() + -vec.z * offset,
                -vec.x * speed, r.nextFloat() * speed, -vec.z * speed);
    }

    public Vec3 getItemMovementVec() {
        boolean alongX = getBlockState().getValue(HORIZONTAL_FACING) == Direction.WEST ||
                getBlockState().getValue(HORIZONTAL_FACING) == Direction.EAST;
        int offset = getSpeed() < 0 ? -1 : 1;
        return new Vec3(offset * (alongX ? 0 : 1), 0, offset * (alongX ? -1 : 0));
    }

    private void applyRecipe() {
        List<? extends Recipe<?>> recipes = getRecipes();
        if (recipes.isEmpty())
            return;
        if (recipeIndex >= recipes.size())
            recipeIndex = 0;

        Recipe<?> recipe = recipes.get(recipeIndex);

        if (recipe.getType() == com.simibubi.create.AllRecipeTypes.SANDPAPER_POLISHING.getType()) {
            if (CAFConfigs.server().recipes.speedLimitsForSandpaperPolishingRecipes.get() != 0) {
                int speed = (int)Math.abs(getSpeed());
                boolean wrongLimit = false;

                if (CAFConfigs.server().recipes.speedLimitsForSandpaperPolishingRecipes.get() == 1 && speed > CAFConfigs.server().recipes.lowSpeedValue.get()) wrongLimit = true;
                if (CAFConfigs.server().recipes.speedLimitsForSandpaperPolishingRecipes.get() == 2 && (speed > CAFConfigs.server().recipes.mediumSpeedValue.get() || speed <= CAFConfigs.server().recipes.lowSpeedValue.get())) wrongLimit = true;
                if (CAFConfigs.server().recipes.speedLimitsForSandpaperPolishingRecipes.get() == 3 && speed <= CAFConfigs.server().recipes.mediumSpeedValue.get()) wrongLimit = true;

                if (wrongLimit) {
                    if (CAFConfigs.server().recipes.destroyOnWrongGrinderSpeed.get()) inventory.clear();
                    return;
                }
            }

            int rolls = inventory.getStackInSlot(0)
                    .getCount();
            inventory.clear();

            List<ItemStack> list = new ArrayList<>();
            for (int roll = 0; roll < rolls; roll++) {
                List<ItemStack> results = new LinkedList<ItemStack>();
                if (recipe instanceof SandPaperPolishingRecipe)
                    results = ((SandPaperPolishingRecipe) recipe).rollResults();

                for (int i = 0; i < results.size(); i++) {
                    ItemStack stack = results.get(i);
                    ItemHelper.addToList(stack, list);
                }
            }

            for (int slot = 0; slot < list.size() && slot + 1 < inventory.getSlots(); slot++)
                inventory.setStackInSlot(slot + 1, list.get(slot));

            return;
        }

        if (recipe instanceof PolishingRecipe polishingRecipe) {
            if (polishingRecipe.getSpeedLimits() != 0) {
                int speed = (int)Math.abs(getSpeed());
                boolean wrongLimit = false;

                if (polishingRecipe.getSpeedLimits() == 1 && speed > CAFConfigs.server().recipes.lowSpeedValue.get()) wrongLimit = true;
                if (polishingRecipe.getSpeedLimits() == 2 && (speed > CAFConfigs.server().recipes.mediumSpeedValue.get() || speed <= CAFConfigs.server().recipes.lowSpeedValue.get())) wrongLimit = true;
                if (polishingRecipe.getSpeedLimits() == 3 && speed <= CAFConfigs.server().recipes.mediumSpeedValue.get()) wrongLimit = true;

                if (wrongLimit) {
                    if (CAFConfigs.server().recipes.destroyOnWrongGrinderSpeed.get()
                            || polishingRecipe.isFragile()) inventory.clear();
                    return;
                }
            }

            int rolls = inventory.getStackInSlot(0)
                    .getCount();
            inventory.clear();

            List<ItemStack> list = new ArrayList<>();
            for (int roll = 0; roll < rolls; roll++) {
                List<ItemStack> results = polishingRecipe.rollResults();

                for (int i = 0; i < results.size(); i++) {
                    ItemStack stack = results.get(i);
                    ItemHelper.addToList(stack, list);
                }
            }

            for (int slot = 0; slot < list.size() && slot + 1 < inventory.getSlots(); slot++)
                inventory.setStackInSlot(slot + 1, list.get(slot));
        }
    }

    private List<? extends Recipe<?>> getRecipes() {
        Optional<PolishingRecipe> assemblyRecipe = SequencedAssemblyRecipe.getRecipe(level, inventory.getStackInSlot(0),
                AllRecipeTypes.POLISHING.getType(), PolishingRecipe.class);
        if (assemblyRecipe.isPresent() && filtering.test(assemblyRecipe.get()
                .getResultItem()))
            return ImmutableList.of(assemblyRecipe.get());

        Predicate<Recipe<?>> types = RecipeConditions.isOfType(AllRecipeTypes.POLISHING.getType(),
                CAFConfigs.server().recipes.allowSandpaperPolishingOnGrinder.get() ? com.simibubi.create.AllRecipeTypes.SANDPAPER_POLISHING.getType() : null);

        List<Recipe<?>> startedSearch = RecipeFinder.get(polishingRecipesKey, level, types);
        startedSearch = startedSearch.stream()
                .filter(RecipeConditions.outputMatchesFilter(filtering))
                .filter(RecipeConditions.firstIngredientMatches(inventory.getStackInSlot(0)))
                .filter(r -> !AllRecipeTypes.shouldIgnoreInAutomation(r))
                .collect(Collectors.toList());

        List<Recipe<?>> grinder = new ArrayList<>();
        List<Recipe<?>> grinderWrongSpeed = new ArrayList<>();
        List<Recipe<?>> sandpaper = new ArrayList<>();

        for (Recipe<?> recipe : startedSearch) {
            if (recipe instanceof PolishingRecipe re) {
                if (re.getSpeedLimits() == getCurrentSpeedMode()) grinder.add(recipe);
                else grinderWrongSpeed.add(recipe);
            }
            else sandpaper.add(recipe);
        }

        if (!grinder.isEmpty()) return grinder;
        if (!grinderWrongSpeed.isEmpty()) return grinderWrongSpeed;
        return sandpaper;
    }

    public int getCurrentSpeedMode() {
        if (getSpeed() == 0) return 0;
        if (Mth.abs(getSpeed()) <= CAFConfigs.server().recipes.lowSpeedValue.get()) return 1;
        if (Mth.abs(getSpeed()) <= CAFConfigs.server().recipes.mediumSpeedValue.get()) return 2;
        return 3;
    }

    public void insertItem(ItemEntity entity) {
        if (!inventory.isEmpty())
            return;
        if (!entity.isAlive())
            return;
        if (level.isClientSide)
            return;

        inventory.clear();
        ItemStack remainder = inventory.insertItem(0, entity.getItem()
                .copy(), false);
        if (remainder.isEmpty())
            entity.discard();
        else
            entity.setItem(remainder);
    }

    public void start(ItemStack inserted) {
        if (inventory.isEmpty())
            return;
        if (level.isClientSide && !isVirtual())
            return;

        List<? extends Recipe<?>> recipes = getRecipes();
        boolean valid = !recipes.isEmpty();
        int time = 50;

        if (recipes.isEmpty()) {
            inventory.remainingTime = inventory.recipeDuration = 10;
            inventory.appliedRecipe = false;
            sendData();
            return;
        }

        if (valid) {
            recipeIndex++;
            if (recipeIndex >= recipes.size())
                recipeIndex = 0;
        }

        Recipe<?> recipe = recipes.get(recipeIndex);
        if (recipe instanceof PolishingRecipe) {
            time = ((PolishingRecipe) recipe).getProcessingDuration();
        }

        inventory.remainingTime = time * Math.max(1, (inserted.getCount() / 5));
        inventory.recipeDuration = inventory.remainingTime;
        inventory.appliedRecipe = false;
        sendData();
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        if (getSpeed() == 0) return false;

        LangBuilder reqSpd = CreateAndFoodLang.translate("gui.goggles.current_speed").add(Lang.text(" "));

        int speedMode = Math.abs(getSpeed()) <= CAFConfigs.server().recipes.lowSpeedValue.get() ? 1 : (Math.abs(getSpeed()) <= CAFConfigs.server().recipes.mediumSpeedValue.get() ? 2 : 3);

        switch (speedMode) {
            case 2:
                reqSpd.add(CreateAndFoodLang.translate("gui.goggles.medium")).style(ChatFormatting.YELLOW).forGoggles(tooltip);
                break;

            case 3:
                reqSpd.add(CreateAndFoodLang.translate("gui.goggles.high")).style(ChatFormatting.RED).forGoggles(tooltip);
                break;

            default:
                reqSpd.add(CreateAndFoodLang.translate("gui.goggles.low")).style(ChatFormatting.GREEN).forGoggles(tooltip);
                break;
        }

        return true;
    }

    public int getTextureType() {
        return textureType;
    }
}