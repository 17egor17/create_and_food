package net.egorplaytv.caf.datagen;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class CAFItemTagsProvider extends TagsProvider<Item> {
    public CAFItemTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.ITEM, CreateAndFood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Minecraft Tags
        this.tag(CAFTags.Items.DEAD_CORALS)
                .add(Items.DEAD_BRAIN_CORAL).add(Items.DEAD_BUBBLE_CORAL)
                .add(Items.DEAD_FIRE_CORAL).add(Items.DEAD_HORN_CORAL)
                .add(Items.DEAD_TUBE_CORAL).add(Items.DEAD_BRAIN_CORAL_FAN)
                .add(Items.DEAD_BUBBLE_CORAL_FAN).add(Items.DEAD_FIRE_CORAL_FAN)
                .add(Items.DEAD_HORN_CORAL_FAN).add(Items.DEAD_TUBE_CORAL_FAN);
        this.tag(ItemTags.DOORS).add(CAFBlocks.ALMOND_DOOR.get().asItem()).add(CAFBlocks.STEEL_DOOR.get().asItem());
        this.tag(Tags.Items.FENCE_GATES).add(CAFBlocks.ALMOND_FENCE_GATE.get().asItem());
        this.tag(ItemTags.FOX_FOOD).addTag(CAFTags.Items.BERRIES);
        this.tag(ItemTags.LEAVES).add(CAFBlocks.ALMOND_LEAVES.get().asItem());
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(CAFTags.Items.ALMOND_LOGS);
        this.tag(ItemTags.PLANKS).add(CAFBlocks.ALMOND_PLANKS.get().asItem());
        this.tag(ItemTags.SAPLINGS).add(CAFBlocks.ALMOND_SAPLING.get().asItem());
        this.tag(ItemTags.SIGNS).add(CAFBlocks.ALMOND_SIGN.get().asItem());
        this.tag(ItemTags.WOODEN_BUTTONS).add(CAFBlocks.ALMOND_BUTTON.get().asItem());
        this.tag(ItemTags.WOODEN_DOORS).add(CAFBlocks.ALMOND_DOOR.get().asItem()).add(CAFBlocks.STEEL_DOOR.get().asItem());
        this.tag(ItemTags.WOODEN_FENCES).add(CAFBlocks.ALMOND_FENCE.get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(CAFBlocks.ALMOND_PRESSURE_PLATE.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS).add(CAFBlocks.ALMOND_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS).add(CAFBlocks.ALMOND_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(CAFBlocks.ALMOND_TRAPDOOR.get().asItem());

        // Create and Food Tags
        this.tag(CAFTags.Items.ALMOND_LOGS)
                .add(CAFBlocks.ALMOND_WOOD.get().asItem()).add(CAFBlocks.ALMOND_LOG.get().asItem())
                .add(CAFBlocks.STRIPPED_ALMOND_WOOD.get().asItem()).add(CAFBlocks.STRIPPED_ALMOND_LOG.get().asItem());
        this.tag(CAFTags.Items.BARRELS)
                .add(CAFBlocks.ACACIA_BARREL.get().asItem()).add(CAFBlocks.ALMOND_BARREL.get().asItem())
                .add(CAFBlocks.BIRCH_BARREL.get().asItem()).add(CAFBlocks.CRIMSON_BARREL.get().asItem())
                .add(CAFBlocks.DARK_OAK_BARREL.get().asItem()).add(CAFBlocks.JUNGLE_BARREL.get().asItem())
                .add(CAFBlocks.OAK_BARREL.get().asItem()).add(CAFBlocks.SPRUCE_BARREL.get().asItem())
                .add(CAFBlocks.WARPED_BARREL.get().asItem());
        this.tag(CAFTags.Items.MOD_BERRIES)
                .add(CAFItems.BLUEBERRY.get()).add(CAFItems.CRANBERRY.get())
                .add(CAFItems.BLUE_GRAPE.get()).add(CAFItems.GREEN_GRAPE.get())
                .add(CAFItems.PURPLE_GRAPE.get()).add(CAFItems.RED_GRAPE.get());
        this.tag(CAFTags.Items.BERRIES).addTag(CAFTags.Items.MOD_BERRIES);
        this.tag(CAFTags.Items.CREATIVE_FUEL).add(AllItems.CREATIVE_BLAZE_CAKE.get());
        this.tag(CAFTags.Items.CUTTING_BOARDS)
                .add(CAFBlocks.ACACIA_CUTTING_BOARD.get().asItem()).add(CAFBlocks.ALMOND_CUTTING_BOARD.get().asItem())
                .add(CAFBlocks.BIRCH_CUTTING_BOARD.get().asItem()).add(CAFBlocks.CRIMSON_CUTTING_BOARD.get().asItem())
                .add(CAFBlocks.DARK_OAK_CUTTING_BOARD.get().asItem()).add(CAFBlocks.JUNGLE_CUTTING_BOARD.get().asItem())
                .add(CAFBlocks.OAK_CUTTING_BOARD.get().asItem()).add(CAFBlocks.SPRUCE_CUTTING_BOARD.get().asItem())
                .add(CAFBlocks.WARPED_CUTTING_BOARD.get().asItem());
        this.tag(CAFTags.Items.EGG).add(Items.EGG);
        this.tag(CAFTags.Items.SPAWN_EGGS)
                .add(Items.AXOLOTL_SPAWN_EGG).add(Items.BAT_SPAWN_EGG)
                .add(Items.BEE_SPAWN_EGG).add(Items.BLAZE_SPAWN_EGG)
                .add(Items.CAT_SPAWN_EGG).add(Items.CAVE_SPIDER_SPAWN_EGG)
                .add(Items.CHICKEN_SPAWN_EGG).add(Items.COD_SPAWN_EGG)
                .add(Items.COW_SPAWN_EGG).add(Items.CREEPER_SPAWN_EGG)
                .add(Items.DOLPHIN_SPAWN_EGG).add(Items.DONKEY_SPAWN_EGG)
                .add(Items.DROWNED_SPAWN_EGG).add(Items.ELDER_GUARDIAN_SPAWN_EGG)
                .add(Items.ENDERMAN_SPAWN_EGG).add(Items.ENDERMITE_SPAWN_EGG)
                .add(Items.EVOKER_SPAWN_EGG).add(Items.FOX_SPAWN_EGG)
                .add(Items.GHAST_SPAWN_EGG).add(Items.GLOW_SQUID_SPAWN_EGG)
                .add(Items.GOAT_SPAWN_EGG).add(Items.GUARDIAN_SPAWN_EGG)
                .add(Items.HOGLIN_SPAWN_EGG).add(Items.HORSE_SPAWN_EGG)
                .add(Items.HUSK_SPAWN_EGG).add(Items.LLAMA_SPAWN_EGG)
                .add(Items.MAGMA_CUBE_SPAWN_EGG).add(Items.MOOSHROOM_SPAWN_EGG)
                .add(Items.MULE_SPAWN_EGG).add(Items.OCELOT_SPAWN_EGG)
                .add(Items.PANDA_SPAWN_EGG).add(Items.PARROT_SPAWN_EGG)
                .add(Items.PHANTOM_SPAWN_EGG).add(Items.PIG_SPAWN_EGG)
                .add(Items.PIGLIN_SPAWN_EGG).add(Items.PIGLIN_BRUTE_SPAWN_EGG)
                .add(Items.PILLAGER_SPAWN_EGG).add(Items.POLAR_BEAR_SPAWN_EGG)
                .add(Items.PUFFERFISH_SPAWN_EGG).add(Items.RABBIT_SPAWN_EGG)
                .add(Items.RAVAGER_SPAWN_EGG).add(Items.SALMON_SPAWN_EGG)
                .add(Items.SHEEP_SPAWN_EGG).add(Items.SHULKER_SPAWN_EGG)
                .add(Items.SILVERFISH_SPAWN_EGG).add(Items.SKELETON_SPAWN_EGG)
                .add(Items.SKELETON_HORSE_SPAWN_EGG).add(Items.SLIME_SPAWN_EGG)
                .add(Items.SPIDER_SPAWN_EGG).add(Items.SQUID_SPAWN_EGG)
                .add(Items.STRAY_SPAWN_EGG).add(Items.STRIDER_SPAWN_EGG)
                .add(Items.TRADER_LLAMA_SPAWN_EGG).add(Items.TROPICAL_FISH_SPAWN_EGG)
                .add(Items.TURTLE_SPAWN_EGG).add(Items.VEX_SPAWN_EGG)
                .add(Items.VILLAGER_SPAWN_EGG).add(Items.VINDICATOR_SPAWN_EGG)
                .add(Items.WANDERING_TRADER_SPAWN_EGG).add(Items.WITCH_SPAWN_EGG)
                .add(Items.WITHER_SKELETON_SPAWN_EGG).add(Items.WOLF_SPAWN_EGG)
                .add(Items.ZOGLIN_SPAWN_EGG).add(Items.ZOMBIE_SPAWN_EGG)
                .add(Items.ZOMBIE_HORSE_SPAWN_EGG).add(Items.ZOMBIE_VILLAGER_SPAWN_EGG)
                .add(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
        this.tag(CAFTags.Items.TURTLE_EGG).add(Items.TURTLE_EGG);
        this.tag(CAFTags.Items.EGGS).addTag(CAFTags.Items.EGG).addTag(CAFTags.Items.SPAWN_EGGS).addTag(CAFTags.Items.TURTLE_EGG);
        this.tag(CAFTags.Items.FUEL)
                .addTag(ItemTags.WOODEN_DOORS).addTag(ItemTags.WOODEN_BUTTONS)
                .addTag(ItemTags.WOODEN_FENCES).addTag(ItemTags.WOODEN_SLABS)
                .addTag(ItemTags.WOODEN_STAIRS).addTag(ItemTags.WOODEN_PRESSURE_PLATES)
                .addTag(ItemTags.WOODEN_TRAPDOORS).addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.PLANKS).addTag(ItemTags.BANNERS)
                .addTag(Tags.Items.FENCE_GATES).addTag(ItemTags.SIGNS)
                .addTag(ItemTags.WOOL).addTag(ItemTags.COALS)
                .addTag(ItemTags.BOATS).addTag(Tags.Items.CHESTS_WOODEN)
                .addTag(CAFTags.Items.COAL_DUST).addTag(CAFTags.Items.CREATIVE_FUEL)
                .add(Items.LADDER).add(Items.STICK)
                .add(Items.BOWL).add(Items.BOW)
                .add(Items.WOODEN_AXE).add(Items.WOODEN_HOE)
                .add(Items.WOODEN_PICKAXE).add(Items.WOODEN_SHOVEL)
                .add(Items.WOODEN_SWORD).add(Items.FISHING_ROD)
                .add(Items.COAL_BLOCK).add(CAFItems.ALLOY_SOULS_NUGGET.get())
                .add(CAFItems.ALLOY_SOULS.get()).add(CAFItems.ALLOY_SOULS_INGOT.get())
                .add(CAFItems.ALLOY_SOULS_SHEET.get()).add(AllItems.BLAZE_CAKE.get());
        this.tag(CAFTags.Items.RED_JUICE)
                .add(CAFItems.BLUE_GRAPE.get()).add(CAFItems.PURPLE_GRAPE.get())
                .add(CAFItems.RED_GRAPE.get());
        this.tag(CAFTags.Items.WHITE_JUICE).add(CAFItems.GREEN_GRAPE.get());
        this.tag(CAFTags.Items.GRAPES).addTag(CAFTags.Items.RED_JUICE).addTag(CAFTags.Items.WHITE_JUICE);
        this.tag(CAFTags.Items.TERRACES)
                .add(CAFBlocks.ACACIA_TERRACE.get().asItem()).add(CAFBlocks.ACACIA_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.ALMOND_TERRACE.get().asItem()).add(CAFBlocks.ALMOND_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.BIRCH_TERRACE.get().asItem()).add(CAFBlocks.BIRCH_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.CRIMSON_TERRACE.get().asItem()).add(CAFBlocks.CRIMSON_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.DARK_OAK_TERRACE.get().asItem()).add(CAFBlocks.DARK_OAK_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.JUNGLE_TERRACE.get().asItem()).add(CAFBlocks.JUNGLE_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.OAK_TERRACE.get().asItem()).add(CAFBlocks.OAK_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.SPRUCE_TERRACE.get().asItem()).add(CAFBlocks.SPRUCE_TERRACE_STAIRS.get().asItem())
                .add(CAFBlocks.WARPED_TERRACE.get().asItem()).add(CAFBlocks.WARPED_TERRACE_STAIRS.get().asItem());
    }

    @Override
    public String getName() {
        return "Create and Food Item Tags";
    }
}
