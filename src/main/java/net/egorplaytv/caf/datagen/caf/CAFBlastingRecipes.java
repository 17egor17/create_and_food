package net.egorplaytv.caf.datagen.caf;

import com.simibubi.create.AllItems;
import mekanism.common.registries.MekanismItems;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.datagen.custom.BlastingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFBlastingRecipes {

    private static void blasting(Consumer<FinishedRecipe> pConsumer) {
        BlastingRecipeBuilder.blastingRecipe(AllItems.ANDESITE_ALLOY.get(), 4, 400, 1538F, 0.9F)
                .addIngredient(Items.ANDESITE)
                .addIngredient(Items.ANDESITE)
                .addIngredient(CAFTags.Items.forgeTag("ingots/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ANDESITE_ALLOY.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1083.4F, 0.9F)
                .addIngredient(CAFTags.Items.forgeTag("ingots/copper"))
                .addIngredient(CAFTags.Items.forgeTag("ingots/zinc"))
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1083.4F, 0.9F)
                .addIngredient(AllItems.CRUSHED_COPPER.get())
                .addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.BRASS_INGOT.get(), 2, 800, 1083.4F, 0.9F)
                .addIngredient(CAFItems.RAW_COPPER.get())
                .addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromRaw(AllItems.BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1083.4F, 0.7F)
                .addIngredient(CAFItems.RAW_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1083.4F, 0.1F)
                .addIngredient(AllItems.CRUSHED_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 400, 1083.4F, 0.1F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/copper"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.COPPER_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 200, 1083.4F, 0.1F)
                .addIngredient(CAFItems.COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 200, 1083.4F, 0.1F)
                .addIngredient(CAFItems.BROKEN_COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GLOWING_BRASS_INGOT.get(), 2, 1200, 950F, 0.2F)
                .addIngredient(AllItems.POLISHED_ROSE_QUARTZ.get())
                .addIngredient(CAFTags.Items.forgeTag("ingots/brass"))
                .addIngredient(Items.GLOWSTONE_DUST)
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GLOWING_BRASS_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064.18F, 0.7F)
                .addIngredient(CAFItems.RAW_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064.18F, 0.1F)
                .addIngredient(AllItems.CRUSHED_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064.18F, 0.7F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.GOLD_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 400, 1064.18F, 0.5F)
                .addIngredient(CAFItems.PIECE_OF_GOLD.get()).addIngredient(CAFItems.PIECE_OF_GOLD.get())
                .addIngredient(CAFTags.Items.forgeTag("nuggets/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get()) + "_from_piece"));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 200, 1064.18F, 0.1F)
                .addIngredient(CAFItems.GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 200, 1064.18F, 0.1F)
                .addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538F, 0.7F)
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538F, 0.7F)
                .addIngredient(AllItems.CRUSHED_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538F, 0.7F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 200, 1538F, 0.1F)
                .addIngredient(CAFItems.IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 200, 1538F, 0.1F)
                .addIngredient(CAFItems.BROKEN_IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHERITE_INGOT.get(), 400, 3133F, 0.7F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/netherite"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHERITE_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538F, 0.9F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/coal")).addIngredient(CAFTags.Items.forgeTag("dusts/coal"))
                .addIngredient(CAFTags.Items.forgeTag("ingots/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.STEEL_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538F, 0.7F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/steel"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.STEEL_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 400, 3016F, 0.1F)
                .addIngredient(CAFItems.RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TANTALUM_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 400, 3016F, 0.1F)
                .addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TANTALUM_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 400, 3421F, 0.1F)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TUNGSTEN_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 400, 3421F, 0.1F)
                .addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TUNGSTEN_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 400, 419F, 0.7F)
                .addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ZINC_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 400, 419F, 0.1F)
                .addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.ZINC_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHER_ALLOY.get(), 1, 200, 1000F, 0.1F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHER_ALLOY.get())));


        doubleBlasting(pConsumer);
        tripleBlasting(pConsumer);
        blastingCompat(pConsumer);
        doubleBlastingCompat(pConsumer);
        tripleBlastingCompat(pConsumer);
    }

    private static void doubleBlasting(Consumer<FinishedRecipe> pConsumer) {
        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 2, 800, 1083.4F, 1.4F)
                .addIngredient(CAFItems.RAW_COPPER.get()).addIngredient(CAFItems.RAW_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.COPPER_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 2, 800, 1083.4F, 0.2F)
                .addIngredient(AllItems.CRUSHED_COPPER.get()).addIngredient(AllItems.CRUSHED_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.COPPER_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 2, 800, 1083.4F, 0.2F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/copper")).addIngredient(CAFTags.Items.forgeTag("dusts/copper"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.COPPER_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 2, 400, 1083.4F, 0.2F)
                .addIngredient(CAFItems.COPPER_COIN.get()).addIngredient(CAFItems.COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.DEFAULT), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 2, 400, 1083.4F, 0.2F)
                .addIngredient(CAFItems.BROKEN_COPPER_COIN.get()).addIngredient(CAFItems.BROKEN_COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.BROKEN), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 2, 800, 1064.18F, 1.4F)
                .addIngredient(CAFItems.RAW_GOLD.get()).addIngredient(CAFItems.RAW_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 2, 800, 1064.18F, 0.2F)
                .addIngredient(AllItems.CRUSHED_GOLD.get()).addIngredient(AllItems.CRUSHED_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.GOLD_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 2, 800, 1064.18F, 1.4F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/gold")).addIngredient(CAFTags.Items.forgeTag("dusts/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.GOLD_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 2, 400, 1064.18F, 0.2F)
                .addIngredient(CAFItems.GOLDEN_COIN.get()).addIngredient(CAFItems.GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.DEFAULT), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 2, 400, 1064.18F, 0.2F)
                .addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get()).addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.BROKEN), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 2, 800, 1538F, 1.4F)
                .addIngredient(CAFItems.RAW_IRON.get()).addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.IRON_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 2, 800, 1538F, 1.4F)
                .addIngredient(AllItems.CRUSHED_IRON.get()).addIngredient(AllItems.CRUSHED_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.IRON_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 2, 800, 1538F, 1.4F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/iron")).addIngredient(CAFTags.Items.forgeTag("dusts/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.IRON_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 2, 400, 1538F, 0.2F)
                .addIngredient(CAFItems.IRON_COIN.get()).addIngredient(CAFItems.IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.DEFAULT), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 2, 400, 1538F, 0.2F)
                .addIngredient(CAFItems.BROKEN_IRON_COIN.get()).addIngredient(CAFItems.BROKEN_IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.BROKEN), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHERITE_INGOT.get(), 2, 800, 3133F, 1.4F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/netherite")).addIngredient(CAFTags.Items.forgeTag("dusts/netherite"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHERITE_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 2, 800, 1538F, 1.4F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/steel")).addIngredient(CAFTags.Items.forgeTag("dusts/steel"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.STEEL_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 2, 800, 3016F, 0.2F)
                .addIngredient(CAFItems.RAW_TANTALUM.get()).addIngredient(CAFItems.RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TANTALUM_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 2, 800, 3016F, 0.2F)
                .addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get()).addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TANTALUM_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 2, 800, 3421F, 0.2F)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get()).addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TUNGSTEN_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 2, 800, 3421F, 0.2F)
                .addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get()).addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TUNGSTEN_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 2, 800, 419F, 1.4F)
                .addIngredient(AllItems.RAW_ZINC.get()).addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ZINC_INGOT.get()), RecipeType.DOUBLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 2, 800, 419F, 0.2F)
                .addIngredient(AllItems.CRUSHED_ZINC.get()).addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.ZINC_INGOT.get()), RecipeType.DOUBLE));
    }

    private static void tripleBlasting(Consumer<FinishedRecipe> pConsumer) {
        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 3, 1200, 1083.4F, 2.1F)
                .addIngredient(CAFItems.RAW_COPPER.get()).addIngredient(CAFItems.RAW_COPPER.get())
                .addIngredient(CAFItems.RAW_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.COPPER_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 3, 1200, 1083.4F, 0.3F)
                .addIngredient(AllItems.CRUSHED_COPPER.get()).addIngredient(AllItems.CRUSHED_COPPER.get())
                .addIngredient(AllItems.CRUSHED_COPPER.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.COPPER_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.COPPER_INGOT.get(), 3, 1200, 1083.4F, 0.3F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/copper")).addIngredient(CAFTags.Items.forgeTag("dusts/copper"))
                .addIngredient(CAFTags.Items.forgeTag("dusts/copper"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.COPPER_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 3, 600, 1083.4F, 0.3F)
                .addIngredient(CAFItems.COPPER_COIN.get()).addIngredient(CAFItems.COPPER_COIN.get())
                .addIngredient(CAFItems.COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.DEFAULT), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.COPPER_NUGGET.get(), 3, 600, 1083.4F, 0.3F)
                .addIngredient(CAFItems.BROKEN_COPPER_COIN.get()).addIngredient(CAFItems.BROKEN_COPPER_COIN.get())
                .addIngredient(CAFItems.BROKEN_COPPER_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(AllItems.COPPER_NUGGET.get(), CoinType.BROKEN), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 3, 1200, 1064.18F, 2.1F)
                .addIngredient(CAFItems.RAW_GOLD.get()).addIngredient(CAFItems.RAW_GOLD.get())
                .addIngredient(CAFItems.RAW_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.GOLD_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 3, 1200, 1064.18F, 0.3F)
                .addIngredient(AllItems.CRUSHED_GOLD.get()).addIngredient(AllItems.CRUSHED_GOLD.get())
                .addIngredient(AllItems.CRUSHED_GOLD.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.GOLD_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_INGOT.get(), 3, 1200, 1064.18F, 2.1F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/gold")).addIngredient(CAFTags.Items.forgeTag("dusts/gold"))
                .addIngredient(CAFTags.Items.forgeTag("dusts/gold"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.GOLD_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 3, 600, 1064.18F, 0.3F)
                .addIngredient(CAFItems.GOLDEN_COIN.get()).addIngredient(CAFItems.GOLDEN_COIN.get())
                .addIngredient(CAFItems.GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.DEFAULT), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 3, 600, 1064.18F, 0.3F)
                .addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get()).addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get())
                .addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.BROKEN), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 3, 1200, 1538F, 2.1F)
                .addIngredient(CAFItems.RAW_IRON.get()).addIngredient(CAFItems.RAW_IRON.get())
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.IRON_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 3, 1200, 1538F, 2.1F)
                .addIngredient(AllItems.CRUSHED_IRON.get()).addIngredient(AllItems.CRUSHED_IRON.get())
                .addIngredient(AllItems.CRUSHED_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.IRON_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 3, 1200, 1538F, 2.1F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/iron")).addIngredient(CAFTags.Items.forgeTag("dusts/iron"))
                .addIngredient(CAFTags.Items.forgeTag("dusts/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.IRON_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 3, 600, 1538F, 0.3F)
                .addIngredient(CAFItems.IRON_COIN.get()).addIngredient(CAFItems.IRON_COIN.get())
                .addIngredient(CAFItems.IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.DEFAULT), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 3, 600, 1538F, 0.3F)
                .addIngredient(CAFItems.BROKEN_IRON_COIN.get()).addIngredient(CAFItems.BROKEN_IRON_COIN.get())
                .addIngredient(CAFItems.BROKEN_IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.BROKEN), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHERITE_INGOT.get(), 3, 1200, 3133F, 2.1F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/netherite")).addIngredient(CAFTags.Items.forgeTag("dusts/netherite"))
                .addIngredient(CAFTags.Items.forgeTag("dusts/netherite"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHERITE_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 3, 1200, 1538F, 2.1F)
                .addIngredient(CAFTags.Items.forgeTag("dusts/steel")).addIngredient(CAFTags.Items.forgeTag("dusts/steel"))
                .addIngredient(CAFTags.Items.forgeTag("dusts/steel"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.STEEL_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 3, 1200, 3016F, 0.3F)
                .addIngredient(CAFItems.RAW_TANTALUM.get()).addIngredient(CAFItems.RAW_TANTALUM.get())
                .addIngredient(CAFItems.RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TANTALUM_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 3, 1200, 3016F, 0.3F)
                .addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get()).addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get())
                .addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TANTALUM_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 3, 1200, 3421F, 0.3F)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get()).addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TUNGSTEN_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 3, 1200, 3421F, 0.3F)
                .addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get()).addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get())
                .addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TUNGSTEN_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 3, 1200, 419F, 2.1F)
                .addIngredient(AllItems.RAW_ZINC.get()).addIngredient(AllItems.RAW_ZINC.get())
                .addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ZINC_INGOT.get()), RecipeType.TRIPLE));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 3, 1200, 419F, 0.3F)
                .addIngredient(AllItems.CRUSHED_ZINC.get()).addIngredient(AllItems.CRUSHED_ZINC.get())
                .addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.ZINC_INGOT.get()), RecipeType.TRIPLE));
    }

    private static void blastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 400, 660, 0.1F)
                    .addIngredient(AllItems.CRUSHED_BAUXITE.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 400, 660, 0.1F)
                    .addIngredient(getDusts(Metals.ALUMINUM, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.CONSTANTAN_INGOT.get(), 2, 800, 1455, 0.9F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/copper")).addIngredient(CAFTags.Items.forgeTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.CONSTANTAN_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.ELECTRUM_INGOT.get(), 2, 800, 1064.18F, 0.9F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/gold")).addIngredient(CAFTags.Items.forgeTag("ingots/silver"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.ELECTRUM_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 400, 327, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 400, 1455, 0.1F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 400, 961, 0.1F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538, 0.7F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.STEEL_INGOT.get(), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 400, 1132, 0.1F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.IE), ModID.IE));
        }

        if (CreateAndFood.MEKIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 3, 1200, 1083.4F, 1.0F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/tin")).addIngredient(CAFTags.Items.forgeTag("ingots/copper"))
                    .addIngredient(CAFTags.Items.forgeTag("ingots/copper"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.BRONZE_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 400, 1000, 0.5F)
                    .addIngredient(MekanismItems.BRONZE_DUST.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 400, 327, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 400, 327, 0.1F)
                    .addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 400, 3033, 0.1F)
                    .addIngredient(AllItems.CRUSHED_OSMIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 400, 3033, 0.1F)
                    .addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538, 0.7F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.STEEL_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 400, 231, 0.1F)
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 400, 231, 0.1F)
                    .addIngredient(getDusts(Metals.TIN, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 400, 1132, 0.1F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 400, 1132, 0.1F)
                    .addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM));
        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 3, 1200, 1083.4F, 1.0F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/tin")).addIngredient(CAFTags.Items.forgeTag("ingots/copper"))
                    .addIngredient(CAFTags.Items.forgeTag("ingots/copper"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.BRONZE_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 800, 1000F, 0.5F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.CONSTANTAN_INGOT.get(), 2, 800, 1455F, 0.9F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/copper")).addIngredient(CAFTags.Items.forgeTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.CONSTANTAN_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.ELECTRUM_INGOT.get(), 2, 800, 1064.18F, 0.9F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/gold")).addIngredient(CAFTags.Items.forgeTag("ingots/silver"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.ELECTRUM_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.INVAR_INGOT.get(), 3, 1200, 1538F, 0.9F)
                    .addIngredient(CAFTags.Items.forgeTag("ingots/iron")).addIngredient(CAFTags.Items.forgeTag("ingots/iron"))
                    .addIngredient(CAFTags.Items.forgeTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.INVAR_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 400, 327F, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 400, 1455F, 0.1F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 400, 961F, 0.1F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 400, 231F, 0.1F)
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.THERMAL), ModID.THERMAL));
        }
    }

    private static void doubleBlastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 2, 800, 660F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_BAUXITE.get()).addIngredient(AllItems.CRUSHED_BAUXITE.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 2, 800, 660F, 0.2F)
                    .addIngredient(getDusts(Metals.ALUMINUM, ModID.IE)).addIngredient(getDusts(Metals.ALUMINUM, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 2, 800, 327F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 2, 800, 1455F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 2, 800, 961F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 2, 800, 1538F, 1.4F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.IE)).addIngredient(getDusts(Metals.STEEL, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.STEEL_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 2, 800, 1132F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get()).addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.DOUBLE));
        }

        if (CreateAndFood.MEKIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 2, 800, 1000F, 1.0F)
                    .addIngredient(MekanismItems.BRONZE_DUST.get()).addIngredient(MekanismItems.BRONZE_DUST.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 2, 800, 327F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 2, 800, 327F, 0.2F)
                    .addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM)).addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 2, 800, 3033F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_OSMIUM.get()).addIngredient(AllItems.CRUSHED_OSMIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 2, 800, 3033F, 0.2F)
                    .addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM)).addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 2, 800, 1538F, 1.4F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM)).addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.STEEL_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 2, 800, 231F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 2, 800, 231F, 0.2F)
                    .addIngredient(getDusts(Metals.TIN, ModID.MEKANISM)).addIngredient(getDusts(Metals.TIN, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 2, 800, 1132F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get()).addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 2, 800, 1132F, 0.2F)
                    .addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM)).addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.DOUBLE));
        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 2, 800, 1000F, 1.0F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL)).addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 2, 800, 327F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 2, 800, 1455F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 2, 800, 961F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 2, 800, 231F, 0.2F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));
        }
    }

    private static void tripleBlastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 3, 1200, 660F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_BAUXITE.get()).addIngredient(AllItems.CRUSHED_BAUXITE.get())
                    .addIngredient(AllItems.CRUSHED_BAUXITE.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.ALUMINUM_INGOT.get(), 3, 1200, 660F, 0.3F)
                    .addIngredient(getDusts(Metals.ALUMINUM, ModID.IE)).addIngredient(getDusts(Metals.ALUMINUM, ModID.IE))
                    .addIngredient(getDusts(Metals.ALUMINUM, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.ALUMINUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 3, 1200, 327F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 3, 1200, 1455F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 3, 1200, 961F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 3, 1200, 1538F, 2.1F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.IE)).addIngredient(getDusts(Metals.STEEL, ModID.IE))
                    .addIngredient(getDusts(Metals.STEEL, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.STEEL_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 3, 1200, 1132F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get()).addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.IE), ModID.IE, RecipeType.TRIPLE));
        }

        if (CreateAndFood.MEKIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 3, 800, 1000F, 1.5F)
                    .addIngredient(MekanismItems.BRONZE_DUST.get()).addIngredient(MekanismItems.BRONZE_DUST.get())
                    .addIngredient(MekanismItems.BRONZE_DUST.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 3, 800, 327F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 3, 800, 327F, 0.3F)
                    .addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM)).addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM))
                    .addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.LEAD_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 3, 800, 3033F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_OSMIUM.get()).addIngredient(AllItems.CRUSHED_OSMIUM.get())
                    .addIngredient(AllItems.CRUSHED_OSMIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.OSMIUM_INGOT.get(), 3, 800, 3033F, 0.3F)
                    .addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM)).addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM))
                    .addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.OSMIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 3, 800, 1538F, 2.1F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM)).addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM))
                    .addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.STEEL_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 3, 800, 231F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 3, 800, 231F, 0.3F)
                    .addIngredient(getDusts(Metals.TIN, ModID.MEKANISM)).addIngredient(getDusts(Metals.TIN, ModID.MEKANISM))
                    .addIngredient(getDusts(Metals.TIN, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.TIN_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 3, 800, 1132F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get()).addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.URANIUM_INGOT.get(), 3, 800, 1132F, 0.3F)
                    .addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM)).addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM))
                    .addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.URANIUM_INGOT.get(), ModID.MEKANISM), ModID.MEKANISM, RecipeType.TRIPLE));
        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(CAFItems.BRONZE_INGOT.get(), 3, 1200, 1000F, 1.5F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL)).addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(CAFItems.BRONZE_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.LEAD_INGOT.get(), 3, 1200, 327F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.LEAD_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.NICKEL_INGOT.get(), 3, 1200, 1455F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.NICKEL_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.SILVER_INGOT.get(), 3, 1200, 961F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.SILVER_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(CAFItems.TIN_INGOT.get(), 3, 1200, 231F, 0.3F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(CAFItems.TIN_INGOT.get(), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));
        }
    }

    public static ResourceLocation getCAFBlasting(String id, RecipeType type) {
        return switch (type) {
            case DEFAULT -> new ResourceLocation(MOD_ID, "blasting/" + id);
            case DOUBLE -> new ResourceLocation(MOD_ID, "blasting/double/" + id);
            case TRIPLE -> new ResourceLocation(MOD_ID, "blasting/triple/" + id);
        };
    }

    public static ResourceLocation getCAFBlasting(String id){
        return getCAFBlasting(id, RecipeType.DEFAULT);
    }

    private static ResourceLocation getCAFBlastingCompat(String id, ModID modID, RecipeType type) {
        return switch (type) {
            case DEFAULT -> switch (modID) {
                case IE -> new ResourceLocation("immersiveengineering", "blasting/" + id);
                case THERMAL -> new ResourceLocation("thermal", "blasting/" + id);
                case MEKANISM -> new ResourceLocation("mekanism", "blasting/" + id);
            };
            case DOUBLE -> switch (modID) {
                case IE -> new ResourceLocation("immersiveengineering", "blasting/double/" + id);
                case THERMAL -> new ResourceLocation("thermal", "blasting/double/" + id);
                case MEKANISM -> new ResourceLocation("mekanism", "blasting/double/" + id);
            };
            case TRIPLE -> switch (modID) {
                case IE -> new ResourceLocation("immersiveengineering", "blasting/triple/" + id);
                case THERMAL -> new ResourceLocation("thermal", "blasting/triple/" + id);
                case MEKANISM -> new ResourceLocation("mekanism", "blasting/triple/" + id);
            };
        };
    }

    private static ResourceLocation getCAFBlastingCompat(String id, ModID modID) {
        return getCAFBlastingCompat(id, modID, RecipeType.DEFAULT);
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

    private static String getRIDCompat(ItemLike item, ModID modID) {
        return switch (modID) {
            case IE -> item.asItem().getRegistryName().getPath() + "_compat_immersiveengineering";
            case THERMAL -> item.asItem().getRegistryName().getPath() + "_compat_thermal";
            case MEKANISM -> item.asItem().getRegistryName().getPath() + "_compat_mekanism";
        };
    }

    private static String getRIDFromCrushedCompat(ItemLike item, ModID modID) {
        return switch (modID) {
            case IE -> item.asItem().getRegistryName().getPath() + "_from_crushed_compat_immersiveengineering";
            case THERMAL -> item.asItem().getRegistryName().getPath() + "_from_crushed_compat_thermal";
            case MEKANISM -> item.asItem().getRegistryName().getPath() + "_from_crushed_compat_mekanism";
        };
    }

    private static String getRIDFromRawCompat(ItemLike item, ModID modID) {
        return switch (modID) {
            case IE -> item.asItem().getRegistryName().getPath() + "_from_raw_compat_immersiveengineering";
            case THERMAL -> item.asItem().getRegistryName().getPath() + "_from_raw_compat_thermal";
            case MEKANISM -> item.asItem().getRegistryName().getPath() + "_from_raw_compat_mekanism";
        };
    }

    private static String getRIDFromDustCompat(ItemLike item, ModID modID) {
        return switch (modID) {
            case IE -> item.asItem().getRegistryName().getPath() + "_from_dust_compat_immersiveengineering";
            case THERMAL -> item.asItem().getRegistryName().getPath() + "_from_dust_compat_thermal";
            case MEKANISM -> item.asItem().getRegistryName().getPath() + "_from_dust_compat_mekanism";
        };
    }

    private static Item getIngots(Metals metals, ModID id) {
        return switch (id) {
            case IE -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering", "ingot_" + metals.tagName()));
            case THERMAL -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal", metals.tagName() + "_ingot"));
            case MEKANISM -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("mekanism", "ingot_" + metals.tagName()));
        };
    }

    private static Item getDusts(Metals metals, ModID id) {
        return switch (id) {
            case IE -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering", "dust_" + metals.tagName()));
            case THERMAL -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal", metals.tagName() + "_dust"));
            case MEKANISM -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("mekanism", "dust_" + metals.tagName()));
        };
    }


    public static void register(Consumer<FinishedRecipe> pConsumer){
        blasting(pConsumer);
    }

    private enum CoinType {
        DEFAULT, BROKEN
    }

    private enum ModID {
        IE, MEKANISM, THERMAL
    }

    private enum RecipeType {
        DEFAULT, DOUBLE, TRIPLE
    }

    private enum Metals {
        ALUMINUM, LEAD, SILVER, NICKEL, URANIUM, CONSTANTAN, ELECTRUM, STEEL, TIN, OSMIUM, BRONZE, INVAR;

        public String tagName() {
            return this.name().toLowerCase(Locale.US);
        }
    }
}
