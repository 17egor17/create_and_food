package net.egorplaytv.create_and_food.ponder;

import com.simibubi.create.AllParticleTypes;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.ponder.scene.BlastFurnaceScene;
import net.egorplaytv.create_and_food.ponder.scene.FermentationBarrelScene;
import net.egorplaytv.create_and_food.ponder.scene.TabletScene;
import net.minecraft.world.level.ItemLike;

import static net.egorplaytv.create_and_food.block.ModBlocks.*;

public class CAFPonders {
    public static final PonderTag CREATE_AND_FOOD;
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateAndFood.MOD_ID);

    public CAFPonders() {
    }

    private static PonderTag create(String id) {
        return new PonderTag(CreateAndFood.asResource(id));
    }

    public static void register(){
        HELPER.addStoryBoard(TABLET.getId(), "tablet", TabletScene::tablet, CREATE_AND_FOOD);
        HELPER.addStoryBoard(MARBLE_BLAST_FURNACE.getId(), "blast_furnace", BlastFurnaceScene::blast_furnace, CREATE_AND_FOOD);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(),"fermentation/non_connectable", FermentationBarrelScene::non_connectable, CAFPonders.CREATE_AND_FOOD);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(), "fermentation/tube_in_up", FermentationBarrelScene::tube_in_up, AllPonderTags.FLUIDS);
        HELPER.addStoryBoard(FERMENTATION_BARREL.getId(), "fermentation/item_in_fermentation_barrel", FermentationBarrelScene::item_in_fermentation_barrel, AllPonderTags.LOGISTICS);

        PonderRegistry.TAGS.forTag(CREATE_AND_FOOD).add(TABLET.getId()).add(MARBLE_BLAST_FURNACE.getId()).add(FERMENTATION_BARREL.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.FLUIDS).add(FERMENTATION_BARREL.getId());
        PonderRegistry.TAGS.forTag(AllPonderTags.LOGISTICS).add(FERMENTATION_BARREL.getId());
    }

    static {
        CREATE_AND_FOOD = create("create_and_food").item((ItemLike) GOLDEN_WALL.get())
                .defaultLang("Create And Food", "Components in Create And Food").addToIndex();

    }
}
