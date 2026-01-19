package net.egorplaytv.caf.datagen.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.BlastingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;
import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFBlastingRecipes {

    private static void blasting(Consumer<FinishedRecipe> pConsumer) {
        BlastingRecipeBuilder.blastingRecipe(AllItems.ANDESITE_ALLOY.get(), 4, 400, 1538, 0.9F)
                .addIngredient(Items.ANDESITE)
                .addIngredient(Items.ANDESITE)
                .addIngredient(CAFTags.forgeItemTag("ingots/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ANDESITE_ALLOY.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1085, 0.9F)
                .addIngredient(CAFTags.forgeItemTag("ingots/copper"))
                .addIngredient(CAFTags.forgeItemTag("ingots/zinc"))
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1085, 0.9F)
                .addIngredient(AllItems.CRUSHED_COPPER.get())
                .addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1085, 0.9F)
                .addIngredient(CAFItems.RAW_COPPER.get())
                .addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromRaw(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1085, 0.7F)
                .addIngredient(CAFItems.RAW_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1085, 0.1F)
                .addIngredient(AllItems.CRUSHED_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1085, 0.1F)
                .addIngredient(CAFTags.forgeItemTag("dusts/copper"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 200, 185, 0.1F)
                .addIngredient(CAFItems.COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 200, 185, 0.1F)
                .addIngredient(CAFItems.BROKEN_COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GLOWING_BRASS_INGOT.get(), 2, 1200, 950, 0.2F)
                .addIngredient(AllItems.POLISHED_ROSE_QUARTZ.get())
                .addIngredient(CAFTags.forgeItemTag("ingots/brass"))
                .addIngredient(Items.GLOWSTONE_DUST)
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GLOWING_BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064, 0.7F)
                .addIngredient(CAFItems.RAW_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064, 0.1F)
                .addIngredient(AllItems.CRUSHED_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064, 0.7F)
                .addIngredient(CAFTags.forgeItemTag("dusts/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064, 0.5F)
                .addIngredient(CAFItems.PIECE_OF_GOLD.get()).addIngredient(CAFItems.PIECE_OF_GOLD.get())
                .addIngredient(CAFTags.forgeItemTag("nuggets/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get()) + "_from_piece"));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get())

                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_NUGGET.get())));




        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHER_ALLOY.get(), 1, 200, 1000, 0.1F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHER_ALLOY.get())));
    }


    public static ResourceLocation getCAFBlasting(String id) {
        return new ResourceLocation(MOD_ID, "blasting/" + id);
    }

    public static ResourceLocation getCAFBlastingDouble(String id) {
        return new ResourceLocation(MOD_ID, "blasting/double/" + id);
    }

    public static ResourceLocation getCAFBlastingTriple(String id) {
        return new ResourceLocation(MOD_ID, "blasting/triple/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeIdFromCrushed(ItemLike item){
        return item.asItem().getRegistryName().getPath() + "_from_crushed";
    }

    private static String getRecipeIdFromRaw(ItemLike item){
        return item.asItem().getRegistryName().getPath() + "_from_raw";
    }

    private static String getRecipeIdFromDust(ItemLike item){
        return item.asItem().getRegistryName().getPath() + "_from_dust";
    }

    private static String getRecipeIdFromCoin(ItemLike item, CoinType type){
        return switch (type) {
            case DEFAULT -> item.asItem().getRegistryName().getPath() + "_from_coin";
            case BROKEN -> item.asItem().getRegistryName().getPath() + "_from_broken_coin";
        };
    }


    public static void register(Consumer<FinishedRecipe> pConsumer){
        blasting(pConsumer);
    }

    private enum CoinType {
        DEFAULT, BROKEN
    }
}
