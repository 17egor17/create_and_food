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

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 200, 1064, 0.1F)
                .addIngredient(CAFItems.GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.GOLD_NUGGET.get(), 200, 1064, 0.1F)
                .addIngredient(CAFItems.BROKEN_GOLDEN_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.GOLD_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538, 0.7F)
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538, 0.7F)
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_INGOT.get(), 400, 1538, 0.7F)
                .addIngredient(CAFItems.RAW_IRON.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.IRON_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 200, 1538, 0.1F)
                .addIngredient(CAFItems.IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.DEFAULT)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.IRON_NUGGET.get(), 200, 1538, 0.1F)
                .addIngredient(CAFItems.IRON_COIN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCoin(CAFItems.IRON_NUGGET.get(), CoinType.BROKEN)));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHERITE_INGOT.get(), 400, 3133, 0.7F)
                .addIngredient(CAFTags.forgeItemTag("dusts/netherite"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHERITE_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538, 0.9F)
                .addIngredient(CAFTags.forgeItemTag("dusts/coal")).addIngredient(CAFTags.forgeItemTag("dusts/coal"))
                .addIngredient(CAFTags.forgeItemTag("ingots/iron"))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.STEEL_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.STEEL_INGOT.get(), 400, 1538, 0.7F)
                .addIngredient(CAFTags.forgeItemTag("dusts/steel"))
                .save(pConsumer, getCAFBlasting(getRecipeIdFromDust(CAFItems.STEEL_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 400, 3016, 0.1F)
                .addIngredient(CAFItems.RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TANTALUM_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TANTALUM_INGOT.get(), 400, 3016, 0.1F)
                .addIngredient(CAFItems.CRUSHED_RAW_TANTALUM.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TANTALUM_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 400, 3421, 0.1F)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.TUNGSTEN_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.TUNGSTEN_INGOT.get(), 400, 3421, 0.1F)
                .addIngredient(CAFItems.CRUSHED_RAW_TUNGSTEN.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(CAFItems.TUNGSTEN_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 400, 419, 0.7F)
                .addIngredient(AllItems.RAW_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeId(AllItems.ZINC_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(AllItems.ZINC_INGOT.get(), 400, 419, 0.1F)
                .addIngredient(AllItems.CRUSHED_ZINC.get())
                .save(pConsumer, getCAFBlasting(getRecipeIdFromCrushed(AllItems.ZINC_INGOT.get())));

        BlastingRecipeBuilder.blastingRecipe(CAFItems.NETHER_ALLOY.get(), 1, 200, 1000, 0.1F)
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

    }

    private static void tripleBlasting(Consumer<FinishedRecipe> pConsumer) {

    }

    private static void blastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){
            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.ALUMINUM, ModID.IE), 400, 660, 0.1F)
                    .addIngredient(AllItems.CRUSHED_BAUXITE.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.ALUMINUM, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.ALUMINUM, ModID.IE), 400, 660, 0.1F)
                    .addIngredient(getDusts(Metals.ALUMINUM, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.ALUMINUM, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.CONSTANTAN, ModID.IE), 2, 800, 1455, 0.9F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/copper")).addIngredient(CAFTags.forgeItemTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.CONSTANTAN, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.ELECTRUM, ModID.IE), 2, 800, 1064, 0.9F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/gold")).addIngredient(CAFTags.forgeItemTag("ingots/silver"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.ELECTRUM, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.IE), 400, 327, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.LEAD, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.NICKEL, ModID.IE), 400, 1455, 0.1F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.NICKEL, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.SILVER, ModID.IE), 400, 961, 0.1F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.SILVER, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.STEEL, ModID.IE), 400, 1538, 0.7F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.IE))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.STEEL, ModID.IE), ModID.IE), ModID.IE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.URANIUM, ModID.IE), 400, 1132, 0.1F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.URANIUM, ModID.IE), ModID.IE), ModID.IE));
        }

        if (CreateAndFood.MEKIsPresent){
            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.MEKANISM), 3, 1200, 1085, 1.0F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/tin")).addIngredient(CAFTags.forgeItemTag("ingots/copper"))
                    .addIngredient(CAFTags.forgeItemTag("ingots/copper"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.BRONZE, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.MEKANISM), 400, 1000, 0.5F)
                    .addIngredient(MekanismItems.BRONZE_DUST.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.BRONZE, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.MEKANISM), 400, 327, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.LEAD, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.MEKANISM), 400, 327, 0.1F)
                    .addIngredient(getDusts(Metals.LEAD, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.LEAD, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.OSMIUM, ModID.MEKANISM), 400, 3033, 0.1F)
                    .addIngredient(AllItems.CRUSHED_OSMIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.OSMIUM, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.OSMIUM, ModID.MEKANISM), 400, 3033, 0.1F)
                    .addIngredient(getDusts(Metals.OSMIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.OSMIUM, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.STEEL, ModID.MEKANISM), 400, 1538, 0.7F)
                    .addIngredient(getDusts(Metals.STEEL, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.STEEL, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.TIN, ModID.MEKANISM), 400, 231, 0.1F)
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.TIN, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.TIN, ModID.MEKANISM), 400, 231, 0.1F)
                    .addIngredient(getDusts(Metals.TIN, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.TIN, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.URANIUM, ModID.MEKANISM), 400, 1132, 0.1F)
                    .addIngredient(AllItems.CRUSHED_URANIUM.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.URANIUM, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.URANIUM, ModID.MEKANISM), 400, 1132, 0.1F)
                    .addIngredient(getDusts(Metals.URANIUM, ModID.MEKANISM))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.URANIUM, ModID.MEKANISM), ModID.MEKANISM), ModID.MEKANISM));
        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.THERMAL), 3, 1200, 1085, 1.0F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/tin")).addIngredient(CAFTags.forgeItemTag("ingots/copper"))
                    .addIngredient(CAFTags.forgeItemTag("ingots/copper"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.BRONZE, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.THERMAL), 800, 1000, 0.5F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.BRONZE, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.CONSTANTAN, ModID.THERMAL), 2, 800, 1455, 0.9F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/copper")).addIngredient(CAFTags.forgeItemTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.CONSTANTAN, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.ELECTRUM, ModID.THERMAL), 2, 800, 1064, 0.9F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/gold")).addIngredient(CAFTags.forgeItemTag("ingots/silver"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.ELECTRUM, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.INVAR, ModID.THERMAL), 3, 1200, 1538, 0.9F)
                    .addIngredient(CAFTags.forgeItemTag("ingots/iron")).addIngredient(CAFTags.forgeItemTag("ingots/iron"))
                    .addIngredient(CAFTags.forgeItemTag("ingots/nickel"))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.INVAR, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.THERMAL), 400, 327, 0.1F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.LEAD, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.NICKEL, ModID.THERMAL), 400, 1455, 0.1F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.NICKEL, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.SILVER, ModID.THERMAL), 400, 961, 0.1F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.SILVER, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.TIN, ModID.THERMAL), 400, 231, 0.1F)
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.TIN, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL));
        }
    }

    private static void doubleBlastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){

        }

        if (CreateAndFood.MEKIsPresent){

        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.THERMAL), 2, 800, 1000, 1.0F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL)).addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.BRONZE, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.THERMAL), 2, 800, 327, 0.2F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.LEAD, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.NICKEL, ModID.THERMAL), 2, 800, 1455, 0.2F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.NICKEL, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.SILVER, ModID.THERMAL), 2, 800, 961, 0.2F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.SILVER, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.TIN, ModID.THERMAL), 2, 800, 231, 0.2F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.TIN, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.DOUBLE));
        }
    }

    private static void tripleBlastingCompat(Consumer<FinishedRecipe> pConsumer) {
        if (CreateAndFood.IEIsPresent){

        }

        if (CreateAndFood.MEKIsPresent){

        }

        if (CreateAndFood.THIsPresent){
            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.BRONZE, ModID.THERMAL), 3, 1200, 1000, 1.5F)
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL)).addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .addIngredient(getDusts(Metals.BRONZE, ModID.THERMAL))
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDFromDustCompat(getIngots(Metals.BRONZE, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.LEAD, ModID.THERMAL), 3, 1200, 327, 0.3F)
                    .addIngredient(AllItems.CRUSHED_LEAD.get()).addIngredient(AllItems.CRUSHED_LEAD.get())
                    .addIngredient(AllItems.CRUSHED_LEAD.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.LEAD, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.NICKEL, ModID.THERMAL), 3, 1200, 1455, 0.3F)
                    .addIngredient(AllItems.CRUSHED_NICKEL.get()).addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .addIngredient(AllItems.CRUSHED_NICKEL.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.NICKEL, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.SILVER, ModID.THERMAL), 3, 1200, 961, 0.3F)
                    .addIngredient(AllItems.CRUSHED_SILVER.get()).addIngredient(AllItems.CRUSHED_SILVER.get())
                    .addIngredient(AllItems.CRUSHED_SILVER.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.SILVER, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));

            BlastingRecipeBuilder.blastingRecipe(getIngots(Metals.TIN, ModID.THERMAL), 3, 1200, 231, 0.3F)
                    .addIngredient(AllItems.CRUSHED_TIN.get()).addIngredient(AllItems.CRUSHED_TIN.get())
                    .addIngredient(AllItems.CRUSHED_TIN.get())
                    .save(pConsumer,
                            getCAFBlastingCompat(getRIDCompat(getIngots(Metals.TIN, ModID.THERMAL), ModID.THERMAL), ModID.THERMAL, RecipeType.TRIPLE));
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
