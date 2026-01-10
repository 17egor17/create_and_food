package net.egorplaytv.caf.datagen.farmersdelight;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.CuttingBoardRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CuttingRecipes {
    private static void cutting(Consumer<FinishedRecipe> pConsumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Items.EGG, CAFTags.forgeItemTag("tools/knives"), CAFItems.RAW_EGG.get())
                .addResult(CAFItems.EGG_SHELL.get())
                .save(pConsumer, getCutting(getRecipeId(Items.EGG)));

        CuttingBoardRecipeBuilder.cuttingRecipe(CAFItems.PIZZA.get(), CAFTags.forgeItemTag("tools/knives"), CAFItems.PIZZA_SLICE.get(), 8)
                .save(pConsumer, getCutting(getRecipeId(CAFItems.PIZZA_SLICE.get())));

        CuttingBoardRecipeBuilder.cuttingRecipe(AllItems.DOUGH.get(), CAFTags.forgeItemTag("tools/knives"), CAFItems.SMALL_DOUGH.get(), 4)
                .save(pConsumer, getCutting(getRecipeId(CAFItems.SMALL_DOUGH.get())));

        CuttingBoardRecipeBuilder.cuttingRecipe(CAFItems.RAW_EGG.get(), CAFItems.EGG_SHELL.get(), CAFItems.RAW_PROTEIN.get())
                .addResult(CAFItems.RAW_YOLK.get())
                .save(pConsumer, getCutting("yolk_and_protein"));

//        CuttingBoardRecipeBuilder.cuttingRecipe()
//                .unlockedBy("has_", inventoryTrigger(ItemPredicate.Builder.item().of().build()))
//                .save(pConsumer, getCutting(getRecipeId()));
    }

    public static ResourceLocation getCutting(String id) {
        return new ResourceLocation(MOD_ID, "farmersdelight/cutting/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        cutting(pConsumer);
    }
}
