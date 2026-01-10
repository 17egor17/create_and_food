package net.egorplaytv.caf.block.entity.custom;

import com.google.common.collect.Maps;
import net.egorplaytv.caf.block.entity.ModBlockEntities;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class ScalesBlockEntity extends SyncedBlockEntity {
    public final ItemStackHandler itemHandler = new ItemStackHandler() {
        public int getSlotLimit(int slot) {
            return 1;
        }

        protected void onContentsChanged(int slot) {
            ScalesBlockEntity.this.inventoryChanged();
        }
    };

    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> this.itemHandler);
    public boolean isWeighing;
    public int weighingTicks;
    public int maxWeighingTicks = 3;
    private ResourceLocation lastRecipeID;
    public static final Map<Item, Integer> WEIGHT = Maps.newLinkedHashMap();

    public static Map<Item, Integer> getWeight(){
        addI(Items.FLINT_AND_STEEL, 900);
        addI(Items.COMPASS, 100);
        addI(Items.SPYGLASS, 500);
        addI(Items.SHEARS, 500);
        addI(Items.LEAD, 50);
        addI(Items.ENCHANTED_BOOK, 500);
        addI(Items.BOOK, 500);
        addI(Items.NAME_TAG, 10);
        addI(Items.BOW, 800);
        addI(Items.FISHING_ROD, 800);
        addI(Items.CLOCK, 200);
        addI(Items.ARROW, 15);
        addI(Items.TIPPED_ARROW, 15);
        addI(Items.SPECTRAL_ARROW, 15);
        addI(Items.SHIELD, 3000);
        addI(Items.TOTEM_OF_UNDYING, 500);
        addI(Items.TRIDENT, 800);
        addI(Items.CROSSBOW, 1500);

        addI(Items.POTION, 200);
        addI(Items.SPLASH_POTION, 200);
        addI(Items.LINGERING_POTION, 200);
        addI(Items.GLASS_BOTTLE, 150);
        addI(Items.DRAGON_BREATH, 200);
        addI(Items.GHAST_TEAR, 1);
        addI(Items.FERMENTED_SPIDER_EYE, 8);
        addI(Items.SPIDER_EYE, 8);
        addI(Items.BLAZE_POWDER, 25);
        addI(Items.BLAZE_ROD, 25);
        addI(Items.MAGMA_CREAM, 16);
        addI(Items.BREWING_STAND, 1600);
        addI(Items.CAULDRON, 16000);
        addI(Items.GLISTERING_MELON_SLICE, 200);
        addI(Items.GOLDEN_CARROT, 600);
        addI(Items.CARROT, 100);
        addI(Items.RABBIT_FOOT, 150);
        addI(Items.PHANTOM_MEMBRANE, 10);

        addI(Items.APPLE, 176);
        addI(Items.MUSHROOM_STEW, 1000);
        addI(Items.BREAD, 500);
        addI(Items.PORKCHOP, 200);
        addI(Items.COOKED_PORKCHOP, 200);
        addI(Items.GOLDEN_APPLE, 180);
        addI(Items.ENCHANTED_GOLDEN_APPLE, 180);
        addI(Items.COD, 1500);
        addI(Items.SALMON, 4000);
        addI(Items.TROPICAL_FISH, 2000);
        addI(Items.PUFFERFISH, 1000);
        addI(Items.COOKED_COD, 1500);
        addI(Items.COOKED_SALMON, 4000);
        addI(Items.CAKE, 2000);
        addI(Items.COOKIE, 8);
        addI(Items.MELON_SLICE, 200);
        addI(Items.DRIED_KELP, 75);
        addI(Items.BEEF, 500);
        addI(Items.COOKED_BEEF, 500);
        addI(Items.CHICKEN, 1000);
        addI(Items.COOKED_CHICKEN, 1000);
        addI(Items.ROTTEN_FLESH, 1000);
        addI(Items.POTATO, 192);
        addI(Items.BAKED_POTATO, 192);
        addI(Items.POISONOUS_POTATO, 192);
        addI(Items.PUMPKIN_PIE, 1000);
        addI(Items.RABBIT, 4000);
        addI(Items.COOKED_RABBIT, 4000);
        addI(Items.RABBIT_STEW, 1000);
        addI(Items.MUTTON, 350);
        addI(Items.COOKED_MUTTON, 350);
        addI(Items.BEETROOT, 300);
        addI(Items.BEETROOT_SOUP, 1000);
        addI(CAFTags.Items.BERRIES, 150);
        addI(Items.HONEY_BOTTLE, 700);

        addI(Items.BEACON, 50);
        addI(CAFTags.Items.EGGS, 55);
        addI(Items.CONDUIT, 100);
        addI(Items.SCUTE, 160);
        addI(Items.COAL, 300);
        addI(Items.CHARCOAL, 200);
        addI(Items.DIAMOND, 300);
        addI(Items.EMERALD, 10);
        addI(Items.LAPIS_LAZULI, 100);
        addI(Items.QUARTZ, 40);
        addI(Items.AMETHYST_SHARD, 14);
        addI(Tags.Items.RAW_MATERIALS, 1500);
        addI(Tags.Items.RAW_MATERIALS_GOLD, 11800);
        addI(CAFTags.Items.INGOTS, 9000);
        addI(Tags.Items.INGOTS_GOLD, 11300);
        addI(Items.NETHERITE_SCRAP, 100);
        addI(Items.STICK, 350);
        addI(Items.BOWL, 180);
        addI(Items.STRING, 10);
        addI(Items.FEATHER, 5);
        addI(Items.GUNPOWDER, 10);
        addI(Tags.Items.SEEDS, 6);
        addI(Items.WHEAT, 80);
        addI(Items.FLINT, 80);
        addI(Items.BUCKET, 3500);
        addI(Items.WATER_BUCKET, 11000);
        addI(Items.LAVA_BUCKET, 11000);
        addI(Items.POWDER_SNOW_BUCKET, 4700);
        addI(Items.SNOWBALL, 50);
        addI(Items.LEATHER, 750);
        addI(Items.MILK_BUCKET, 9500);
        addI(Items.PUFFERFISH_BUCKET, 11500);
        addI(Items.SALMON_BUCKET, 11500);
        addI(Items.COD_BUCKET, 11500);
        addI(Items.TROPICAL_FISH_BUCKET, 11500);
        addI(Items.AXOLOTL_BUCKET, 11500);
        addI(Items.BRICK, 4000);
        addI(Items.CLAY_BALL, 1350);
        addI(Items.PAPER, 5);
        addI(Items.SLIME_BALL, 100);
        addI(Items.GLOWSTONE_DUST, 10);
        addI(Items.INK_SAC, 1000);
        addI(Items.GLOW_INK_SAC, 1000);
        addI(Tags.Items.DYES, 50);
        addI(Items.BONE_MEAL, 30);
        addI(Items.BONE, 400);
        addI(Items.SUGAR, 10);
        addI(Items.ENDER_PEARL, 8);
        addI(Tags.Items.NUGGETS, 1000);
        addI(Tags.Items.NUGGETS_GOLD, 1222);
        addI(Items.NETHER_WART, 150);
        addI(Items.ENDER_EYE, 8);
        addI(Items.EXPERIENCE_BOTTLE, 150);
        addI(Items.FIRE_CHARGE, 150);
        addI(Items.WRITABLE_BOOK, 500);
        addI(Items.WRITTEN_BOOK, 500);
        addI(Items.MAP, 5);
        addI(Items.FILLED_MAP, 5);
        addI(Items.NETHER_STAR, 20);
        addI(Items.FIREWORK_ROCKET, 570);
        addI(Items.FIREWORK_STAR, 490);
        addI(Items.NETHER_BRICK, 4000);
        addI(Items.PRISMARINE_SHARD, 150);
        addI(Items.PRISMARINE_CRYSTALS, 100);
        addI(Items.RABBIT_HIDE, 150);
        addI(Items.IRON_HORSE_ARMOR, 20000);
        addI(Items.GOLDEN_HORSE_ARMOR, 75000);
        addI(Items.DIAMOND_HORSE_ARMOR, 15000);
        addI(Items.LEATHER_HORSE_ARMOR, 3000);
        addI(Items.CHORUS_FRUIT, 150);
        addI(Items.POPPED_CHORUS_FRUIT, 250);
        addI(Items.SHULKER_SHELL, 200);
        addI(ItemTags.MUSIC_DISCS, 180);
        addI(Items.NAUTILUS_SHELL, 1000);
        addI(Items.HEART_OF_THE_SEA, 250);
        addI(CAFTags.Items.BANNER_PATTERNS, 355);
        addI(Items.HONEYCOMB, 300);

        addI(ItemTags.RAILS, 38000);
        addI(Items.SADDLE, 1600);
        addI(Items.MINECART, 625000);
        addI(Items.CHEST_MINECART, 635000);
        addI(Items.FURNACE_MINECART, 627800);
        addI(Items.TNT_MINECART, 625500);
        addI(Items.HOPPER_MINECART, 626000);
        addI(Items.CARROT_ON_A_STICK, 800);
        addI(Items.WARPED_FUNGUS_ON_A_STICK, 800);
        addI(Items.ELYTRA, 800);
        addI(ItemTags.BOATS, 29000);

        addI(Items.REDSTONE, 10);
        addI(Items.REDSTONE_TORCH, 1500);
        addI(Items.REDSTONE_BLOCK, 4000);
        addI(Items.REPEATER, 1000);
        addI(Items.COMPARATOR, 1000);
        addI(Items.PISTON, 2000);
        addI(Items.STICKY_PISTON, 2000);
        addI(Items.SLIME_BLOCK, 1345);
        addI(Items.HONEY_BLOCK, 1345);
        addI(Items.OBSERVER, 2800);
        addI(Items.HOPPER, 1000);
        addI(Items.DISPENSER, 2800);
        addI(Items.DROPPER, 2800);
        addI(Items.LECTERN, 3000);
        addI(Items.TARGET, 4000);
        addI(Items.LEVER, 800);
        addI(Items.LIGHTNING_ROD, 2000);
        addI(Items.DAYLIGHT_DETECTOR, 2000);
        addI(Items.TRIPWIRE_HOOK, 600);
        addI(Items.TRAPPED_CHEST, 10000);
        addI(Items.TNT, 500);
        addI(Items.REDSTONE_LAMP, 3000);
        addI(Items.NOTE_BLOCK, 2000);
        addI(Items.STONE_BUTTON, 800);
        addI(Items.POLISHED_BLACKSTONE_BUTTON, 800);
        addI(ItemTags.WOODEN_BUTTONS, 700);
        addI(Items.STONE_PRESSURE_PLATE, 800);
        addI(Items.POLISHED_BLACKSTONE_PRESSURE_PLATE, 800);
        addI(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 7000);
        addI(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 1000);
        addI(ItemTags.WOODEN_PRESSURE_PLATES, 700);
        addI(Items.IRON_DOOR, 5000);
        addI(ItemTags.WOODEN_DOORS, 2000);
        addI(Items.IRON_TRAPDOOR, 3000);
        addI(ItemTags.WOODEN_TRAPDOORS, 1000);
        addI(Tags.Items.FENCE_GATES_WOODEN, 1000);

        addI(ItemTags.SAPLINGS, 800);
        addI(ItemTags.LEAVES, 1000);
        addI(Items.COBWEB, 10);
        addB(BlockTags.REPLACEABLE_PLANTS, 200);
        addB(BlockTags.FLOWERS, 200);
        addB(BlockTags.UNDERWATER_BONEMEALS, 200);
        addI(Items.SEA_PICKLE, 200);
        addI(Items.SPORE_BLOSSOM, 200);
        addI(Tags.Items.MUSHROOMS, 100);
        addI(Items.CRIMSON_ROOTS, 150);
        addI(Items.WARPED_ROOTS, 150);
        addI(Items.NETHER_SPROUTS, 100);
        addI(Items.WEEPING_VINES, 200);
        addI(Items.TWISTING_VINES, 200);
        addI(Items.SUGAR_CANE, 100);
        addI(Items.KELP, 150);
        addI(Items.MOSS_CARPET, 50);
        addI(Items.MOSS_BLOCK, 250);
        addI(Items.BIG_DRIPLEAF, 250);
        addI(Items.SMALL_DRIPLEAF, 150);
        addI(Items.BAMBOO, 200);
        addI(CAFTags.Items.TORCHES, 250);
        addI(Tags.Items.STONE, 2800);
        addI(Tags.Items.COBBLESTONE, 2800);
        addI(Items.END_ROD, 150);
        addI(Items.CHORUS_PLANT, 250);
        addI(Items.CHORUS_FLOWER, 250);
        addI(Tags.Items.CHESTS_WOODEN, 15000);
        addI(Items.CRAFTING_TABLE, 700);
        addI(Items.FARMLAND, 900);
        addI(Items.FURNACE, 5000);
        addI(Items.LADDER, 500);
        addI(Items.SNOW, 200);
        addI(Items.CACTUS, 2000);
        addI(Items.JUKEBOX, 2500);
        addI(Tags.Items.FENCES_WOODEN, 2000);
        addI(Items.INFESTED_STONE_BRICKS, 2800);
        addI(Items.INFESTED_MOSSY_STONE_BRICKS, 2800);
        addI(Items.INFESTED_CRACKED_STONE_BRICKS, 2800);
        addI(Items.INFESTED_CHISELED_STONE_BRICKS, 2800);
        addI(Items.BROWN_MUSHROOM_BLOCK, 400);
        addI(Items.RED_MUSHROOM_BLOCK, 400);
        addI(Items.MUSHROOM_STEM, 400);
        addI(Items.IRON_BARS, 5000);
        addI(CAFTags.Items.CHAINS, 2000);
        addI(Tags.Items.GLASS_PANES, 1000);
        addI(Items.VINE, 300);
        addI(Items.LILY_PAD, 200);
        addI(Items.ENCHANTING_TABLE, 10000);
        addI(Items.END_PORTAL_FRAME, 2000);
        addI(Tags.Items.CHESTS_ENDER, 20000);
        addI(ItemTags.WALLS, 2800);
        addI(ItemTags.ANVIL, 95000);
        addI(ItemTags.CARPETS, 147);
        addI(Items.DIRT_PATH, 1500);
        addI(Tags.Items.GLASS_PANES, 563);
        addB(BlockTags.SHULKER_BOXES, 1400);
        addI(CAFTags.Items.TERRACOTTA, 0);
        addI(ItemTags.COALS, 1000);
        addI(CAFTags.Items.DEAD_CORALS, 1000);
        addI(Items.SCAFFOLDING, 900);
        addI(Items.PAINTING, 600);
        addI(ItemTags.SIGNS, 700);
        addI(ItemTags.BEDS, 65000);
        addI(Items.ITEM_FRAME, 500);
        addI(Items.GLOW_ITEM_FRAME, 500);
        addB(BlockTags.FLOWER_POTS, 6000);
        addI(Tags.Items.HEADS, 3000);
        addI(Items.ARMOR_STAND, 2100);
        addI(ItemTags.BANNERS, 1500);
        addI(Items.END_CRYSTAL, 1000);
        addI(Items.LOOM, 20000);
        addI(Items.COMPARATOR, 21000);
        addB(Tags.Blocks.BARRELS_WOODEN, 15000);
        addI(Items.SMOKER, 5000);
        addI(Items.BLAST_FURNACE, 5000);
        addI(Items.CARTOGRAPHY_TABLE, 24000);
        addI(Items.FLETCHING_TABLE, 24000);
        addI(Items.GRINDSTONE, 4600);
        addI(Items.SMITHING_TABLE, 42000);
        addI(Items.STONECUTTER, 13200);
        addI(Items.BELL, 10000);
        addI(CAFTags.Items.LANTERNS, 500);
        addB(BlockTags.CAMPFIRES, 300);
        addI(Items.SHROOMLIGHT, 2000);
        addI(Items.BEE_NEST, 7000);
        addI(Items.BEEHIVE, 36900);
        addI(Items.HONEYCOMB_BLOCK, 2700);
        addI(Items.LODESTONE, 5000);
        addI(Items.RESPAWN_ANCHOR, 6000);
        addI(ItemTags.CANDLES, 40);
        addI(Items.SMALL_AMETHYST_BUD, 200);
        addI(Items.MEDIUM_AMETHYST_BUD, 400);
        addI(Items.LARGE_AMETHYST_BUD, 800);
        addI(Items.AMETHYST_CLUSTER, 1182);
        addI(Items.POINTED_DRIPSTONE, 3000);

        addI(Items.CALCITE, 1355);

        addI(Items.TURTLE_HELMET, 800);
        addI(Items.LEATHER_HELMET, 750);
        addI(Items.LEATHER_CHESTPLATE, 750);
        addI(Items.LEATHER_LEGGINGS, 750);
        addI(Items.LEATHER_BOOTS, 750);
        addI(Items.CHAINMAIL_HELMET, 1500);
        addI(Items.CHAINMAIL_CHESTPLATE, 2400);
        addI(Items.CHAINMAIL_LEGGINGS, 1500);
        addI(Items.CHAINMAIL_BOOTS, 600);
        addI(Items.IRON_HELMET, 5000);
        addI(Items.IRON_CHESTPLATE, 8000);
        addI(Items.IRON_LEGGINGS, 5000);
        addI(Items.IRON_BOOTS, 2000);
        addI(Items.GOLDEN_HELMET, 18750);
        addI(Items.GOLDEN_CHESTPLATE, 30000);
        addI(Items.GOLDEN_LEGGINGS, 18750);
        addI(Items.GOLDEN_BOOTS, 7500);
        addI(Items.DIAMOND_HELMET, 3750);
        addI(Items.DIAMOND_CHESTPLATE, 6000);
        addI(Items.DIAMOND_LEGGINGS, 3750);
        addI(Items.DIAMOND_BOOTS, 1500);
        addI(Items.NETHERITE_HELMET, 22000);
        addI(Items.NETHERITE_CHESTPLATE, 35200);
        addI(Items.NETHERITE_LEGGINGS, 30800);
        addI(Items.NETHERITE_BOOTS, 17600);

        addI(Items.WOODEN_SWORD, 700);
        addI(Items.WOODEN_AXE, 700);
        addI(Items.WOODEN_SHOVEL, 700);
        addI(Items.WOODEN_HOE, 700);
        addI(Items.WOODEN_PICKAXE, 700);
        addI(Items.STONE_SWORD, 1500);
        addI(Items.STONE_AXE, 1000);
        addI(Items.STONE_SHOVEL, 1200);
        addI(Items.STONE_HOE, 1000);
        addI(Items.STONE_PICKAXE, 1500);
        addI(Items.GOLDEN_SWORD, 2500);
        addI(Items.GOLDEN_AXE, 2000);
        addI(Items.GOLDEN_SHOVEL, 2000);
        addI(Items.GOLDEN_HOE, 2000);
        addI(Items.GOLDEN_PICKAXE, 2500);
        addI(Items.IRON_SWORD, 2000);
        addI(Items.IRON_AXE, 1500);
        addI(Items.IRON_SHOVEL, 1500);
        addI(Items.IRON_HOE, 1500);
        addI(Items.IRON_PICKAXE, 2000);
        addI(Items.DIAMOND_SWORD, 1500);
        addI(Items.DIAMOND_AXE, 1000);
        addI(Items.DIAMOND_SHOVEL, 1200);
        addI(Items.DIAMOND_HOE, 1000);
        addI(Items.DIAMOND_PICKAXE, 1500);
        addI(Items.NETHERITE_SWORD, 2000);
        addI(Items.NETHERITE_AXE, 1500);
        addI(Items.NETHERITE_SHOVEL, 1500);
        addI(Items.NETHERITE_HOE, 1500);
        addI(Items.NETHERITE_PICKAXE, 2000);

        return WEIGHT;
    }

    private static void addI(TagKey<Item> pItemTag, int weight) {
        for(Holder<Item> holder : Registry.ITEM.getTagOrEmpty(pItemTag)) {
            if (weight > 0) {
                    WEIGHT.put(holder.value(), weight);
            }
        }

    }

    private static void addI(ItemLike pItem, int weight) {
        Item item = pItem.asItem();
        if (weight > 0) {
            WEIGHT.put(item, weight);
        }
    }

    private static void addB(TagKey<Block> pBlockTag, int weight) {
        for(Holder<Block> holder : Registry.BLOCK.getTagOrEmpty(pBlockTag)) {
            if (weight > 0) {
                WEIGHT.put(holder.value().asItem(), weight);
            }
        }
    }

    public ScalesBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCALES_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ScalesBlockEntity pBlockEntity) {
        if (pBlockEntity.weighingTicks == 0 && !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty())
            pBlockEntity.isWeighing = true;

        if (pBlockEntity.isWeighing) {
            ++pBlockEntity.weighingTicks;
        }

        if (pBlockEntity.weighingTicks == pBlockEntity.maxWeighingTicks && !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty()){
            pBlockEntity.isWeighing = false;
        }


        if (pBlockEntity.weighingTicks <= 0){
            pBlockEntity.isWeighing = false;
            pBlockEntity.weighingTicks = 0;
        } else if (pBlockEntity.weighingTicks <= pBlockEntity.maxWeighingTicks && pBlockEntity.itemHandler.getStackInSlot(0).isEmpty()) {
            pBlockEntity.isWeighing = false;
            --pBlockEntity.weighingTicks;
        }
    }

    public void setIsWeighing(boolean bool){
        this.isWeighing = bool;
    }

    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", this.itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    public void load(CompoundTag tag) {
        super.load(tag);
        this.itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        weighingTicks = 0;
        isWeighing = true;
    }


    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (this.level != null) {
            this.level.playSound(null, (float)this.worldPosition.getX() + 0.5F,
                    (float)this.worldPosition.getY() + 0.5F, (float)this.worldPosition.getZ() + 0.5F,
                    sound, SoundSource.BLOCKS, volume, pitch);
        }
    }

    public boolean addItem(ItemStack itemStack) {
        if (this.isEmpty() && !itemStack.isEmpty()) {
            this.itemHandler.setStackInSlot(0, itemStack.split(1));
            this.inventoryChanged();
            return true;
        } else {
            return false;
        }
    }

    public ItemStack removeItem() {
        if (!this.isEmpty()) {
            ItemStack item = this.getStoredItem().split(1);
            this.inventoryChanged();
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public IItemHandler getInventory() {
        return this.itemHandler;
    }

    public ItemStack getStoredItem() {
        return itemHandler.getStackInSlot(0);
    }

    public boolean isEmpty() {
        return itemHandler.getStackInSlot(0).isEmpty();
    }


    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) ? this.inputHandler.cast() : super.getCapability(cap, side);
    }

    public void setRemoved() {
        super.setRemoved();
        this.inputHandler.invalidate();
    }
}
