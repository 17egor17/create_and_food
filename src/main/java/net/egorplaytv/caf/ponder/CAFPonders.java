package net.egorplaytv.caf.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.ponder.scene.*;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.caf.block.CAFBlocks.*;

public class CAFPonders {
    public static final PonderTag CREATE_AND_FOOD;
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateAndFood.MOD_ID);

    public CAFPonders() {
    }

    private static PonderTag create(String id) {
        return new PonderTag(CreateAndFood.asResource(id));
    }

    public static void register(){
        HELPER.addStoryBoard(TERMINAL.getId(), "terminal", TerminalScene::terminal, CREATE_AND_FOOD);
        HELPER.addStoryBoard(MARBLE_BLAST_FURNACE.getId(), "blast_furnace", BlastFurnaceScene::blast_furnace, CREATE_AND_FOOD);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(),"fermentation/non_connectable", FermentationBarrelScene::non_connectable, CAFPonders.CREATE_AND_FOOD);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(), "fermentation/tube_in_up", FermentationBarrelScene::tube_in_up, AllPonderTags.FLUIDS);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(), "fermentation/item_in_fermentation_barrel", FermentationBarrelScene::item_in_fermentation_barrel, AllPonderTags.LOGISTICS);
        HELPER.addStoryBoard(ACACIA_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ACACIA_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ACACIA_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ACACIA_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ALMOND_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ALMOND_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ALMOND_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(ALMOND_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(BIRCH_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(BIRCH_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(BIRCH_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(BIRCH_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(CRIMSON_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(CRIMSON_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(CRIMSON_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(CRIMSON_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(DARK_OAK_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(DARK_OAK_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(DARK_OAK_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(DARK_OAK_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(JUNGLE_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(JUNGLE_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(JUNGLE_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(JUNGLE_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(OAK_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(OAK_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(OAK_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(OAK_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(SPRUCE_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(SPRUCE_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(SPRUCE_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(SPRUCE_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(WARPED_TERRACE.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(WARPED_TERRACE.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);
        HELPER.addStoryBoard(WARPED_TERRACE_STAIRS.getId(), "terrace/terrace_all", TerraceScene::terrace_all, CREATE_AND_FOOD);
        HELPER.addStoryBoard(WARPED_TERRACE_STAIRS.getId(), "terrace/terrace_connect", TerraceScene::terrace_connect, CREATE_AND_FOOD);

        HELPER.addStoryBoard(SCALES.getId(), "scales", ScalesScene::scales, CREATE_AND_FOOD);

        HELPER.addStoryBoard(MECHANICAL_BLENDER, "blender", MechanicalBlenderScene::blender, CREATE_AND_FOOD, AllPonderTags.KINETIC_APPLIANCES);
        HELPER.forComponents(STEEL_SHAFT)
                .addStoryBoard("shaft/relay", SteelShaftScene::relay, CREATE_AND_FOOD, AllPonderTags.KINETIC_RELAYS);
        HELPER.forComponents(STEEL_SHAFT, STEEL_ENCASED_STEEL_SHAFT)
                .addStoryBoard("shaft/encasing", SteelShaftScene::encasing);
        HELPER.forComponents(STEEL_COGWHEEL)
                .addStoryBoard("cog/small", SteelCogwheelScene::small, CREATE_AND_FOOD, AllPonderTags.KINETIC_RELAYS)
                .addStoryBoard("cog/speedup", SteelCogwheelScene::speedup)
                .addStoryBoard("cog/encasing", SteelCogwheelScene::encasing);
        HELPER.forComponents(LARGE_STEEL_COGWHEEL)
                .addStoryBoard("cog/speedup", SteelCogwheelScene::speedup)
                .addStoryBoard("cog/large", SteelCogwheelScene::large, CREATE_AND_FOOD, AllPonderTags.KINETIC_RELAYS)
                .addStoryBoard("cog/encasing", SteelCogwheelScene::encasing);

        PonderRegistry.TAGS.forTag(CREATE_AND_FOOD).add(TERMINAL.getId()).add(MARBLE_BLAST_FURNACE.getId()).add(FERMENTATION_BARREL.getId())
                .add(OAK_TERRACE.getId()).add(OAK_TERRACE_STAIRS.getId()).add(MECHANICAL_BLENDER.getId()).add(STEEL_COGWHEEL.getId()).add(LARGE_STEEL_COGWHEEL.getId())
                        .add(STEEL_SHAFT.getId()).add(SCALES.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.FLUIDS).add(FERMENTATION_BARREL.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.LOGISTICS).add(FERMENTATION_BARREL.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.KINETIC_APPLIANCES).add(MECHANICAL_BLENDER.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.KINETIC_RELAYS).add(STEEL_COGWHEEL.getId()).add(LARGE_STEEL_COGWHEEL.getId())
                .add(STEEL_SHAFT.getId());
    }

    static {
        CREATE_AND_FOOD = create("caf").item((ItemLike) GOLDEN_WALL.get())
                .defaultLang("Create And Food", "Components in Create And Food").addToIndex();

    }
}
