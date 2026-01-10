package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.MixingRecipeBuilder;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.simibubi.create.content.processing.recipe.HeatCondition.HEATED;
import static com.simibubi.create.content.processing.recipe.HeatCondition.SUPERHEATED;
import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class MixerRecipes {
    private static void mixing(Consumer<FinishedRecipe> pConsumer) {
        MixingRecipeBuilder.mixingRecipe(CAFFluids.APPLE_VINEGAR.get(), 1000, HEATED)
                .addIngredient(Items.SUGAR).addIngredient(Items.SUGAR).addIngredient(Items.SUGAR).addIngredient(Items.SUGAR)
                .addIngredient(Items.APPLE).addIngredient(Items.APPLE).addIngredient(Fluids.WATER, 1000)
                .addIngredient(CAFTags.forgeFluidTag("honey"), 500)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.APPLE_VINEGAR.get())));

        MixingRecipeBuilder.mixingRecipe(AllFluids.CHOCOLATE.get().getSource(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR)
                .addIngredient(CAFItems.COCOA_POWDER.get()).addIngredient(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .save(pConsumer, getMixing(getRecipeId(AllFluids.CHOCOLATE.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(AllFluids.CHOCOLATE.get().getSource(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR).addIngredient(CAFItems.COCOA_POWDER.get())
                .addIngredient(CAFFluids.COCOA_OIL.get(), 250)
                .save(pConsumer, getMixing(getRecipeIdFluidFrom(AllFluids.CHOCOLATE.get().getSource(), CAFFluids.COCOA_OIL.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.COCOA_OIL.get().getSource(), 250, HEATED)
                .addIngredient(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .save(pConsumer, getMixing(getRecipeIdFluidFrom(CAFFluids.COCOA_OIL.get(), CAFItems.COCOA_BUTTER_BRIQUETTE.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CREAM.get().getSource(), 500)
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(Items.SUGAR).addIngredient(Items.SUGAR)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CREAM.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CUSTARD.get().getSource(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(CAFItems.RAW_EGG.get())
                .addIngredient(CAFItems.RAW_EGG.get()).addIngredient(Items.SUGAR)
                .addIngredient(Items.SUGAR).addIngredient(AllItems.WHEAT_FLOUR.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CUSTARD.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.GLAZE.get().getSource(), 500)
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.POWDERED_SUGAR.get()).addIngredient(CAFItems.POWDERED_SUGAR.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.GLAZE.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFItems.GLOWING_BRASS_INGOT.get(), 2, HEATED)
                .addIngredient(CAFTags.Items.BRASS).addIngredient(Items.GLOWSTONE_DUST)
                .addIngredient(Items.GLOWSTONE_DUST).addIngredient(AllItems.POLISHED_ROSE_QUARTZ.get())
                .save(pConsumer, getMixing(getRecipeId(CAFItems.GLOWING_BRASS_INGOT.get())));

        MixingRecipeBuilder.mixingRecipe(CAFItems.NETHER_ALLOY.get(), SUPERHEATED)
                .addIngredient(AllItems.CINDER_FLOUR.get()).addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getMixing(getRecipeId(CAFItems.NETHER_ALLOY.get())));

        MixingRecipeBuilder.mixingRecipe(CAFItems.RYE_DOUGH.get())
                .addIngredient(Fluids.WATER, 1000).addIngredient(CAFItems.RYE_FLOUR.get())
                .save(pConsumer, getMixing(getRecipeId(CAFItems.RYE_DOUGH.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.WHITE_CHOCOLATE.get(), 500)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR)
                .addIngredient(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.WHITE_CHOCOLATE.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.WHITE_CHOCOLATE.get(), 500)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR)
                .addIngredient(CAFFluids.COCOA_OIL.get(), 250)
                .save(pConsumer, getMixing(getRecipeIdFluidFrom(CAFFluids.WHITE_CHOCOLATE.get(), CAFFluids.COCOA_OIL.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.SULFURIC_ACID.get().getSource(), 1000, HEATED)
                .addIngredient(Items.GUNPOWDER, 5)
                .addIngredient(Fluids.WATER, 1000)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.SULFURIC_ACID.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.WOOD_PULP.get().getSource(), 500, HEATED)
                .addIngredient(CAFFluids.SULFURIC_ACID.get(), 1000)
                .addIngredient(CAFItems.WOOD_SAWDUST.get()).addIngredient(CAFItems.WOOD_SAWDUST.get())
                .addIngredient(CAFItems.WOOD_SAWDUST.get()).addIngredient(CAFItems.WOOD_SAWDUST.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.WOOD_PULP.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CONCENTRATED_WOOD_PULP.get().getSource(), 500, HEATED)
                .addIngredient(CAFFluids.WOOD_PULP.get(), 1000)
                .addIngredient(CAFItems.WOOD_SAWDUST.get()).addIngredient(CAFItems.WOOD_SAWDUST.get())
                .addIngredient(CAFItems.WOOD_SAWDUST.get()).addIngredient(CAFItems.WOOD_SAWDUST.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CONCENTRATED_WOOD_PULP.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CRIMSON_PULP.get().getSource(), 500, HEATED)
                .addIngredient(CAFFluids.SULFURIC_ACID.get(), 1000)
                .addIngredient(CAFItems.CRIMSON_SAWDUST.get()).addIngredient(CAFItems.CRIMSON_SAWDUST.get())
                .addIngredient(CAFItems.CRIMSON_SAWDUST.get()).addIngredient(CAFItems.CRIMSON_SAWDUST.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CRIMSON_PULP.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.WARPED_PULP.get().getSource(), 500, HEATED)
                .addIngredient(CAFFluids.SULFURIC_ACID.get(), 1000)
                .addIngredient(CAFItems.WARPED_SAWDUST.get()).addIngredient(CAFItems.WARPED_SAWDUST.get())
                .addIngredient(CAFItems.WARPED_SAWDUST.get()).addIngredient(CAFItems.WARPED_SAWDUST.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.WARPED_PULP.get().getSource())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.PAPER_PULP.get().getSource(), 500, HEATED)
                .addIngredient(CAFTags.CAFFluidTag("pulp"), 500)
                .addIngredient(Items.WHITE_DYE).addIngredient(Items.WHITE_DYE)
                .addIngredient(Items.WHITE_DYE).addIngredient(Items.WHITE_DYE)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.PAPER_PULP.get().getSource())));

//        MixingRecipeBuilder.mixingRecipe()
//                .addIngredient()
//                .save(pConsumer, getMixing(getRecipeId()));
    }


    public static ResourceLocation getMixing(String id) {
        return new ResourceLocation(MOD_ID, "create/mixing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }

    private static String getRecipeIdItemFrom(ItemLike result, ItemLike from){
        return result.asItem().getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }
    private static String getRecipeIdItemFrom(ItemLike result, Fluid from){
        return result.asItem().getRegistryName().getPath() + "_from_" + from.getRegistryName().getPath();
    }
    private static String getRecipeIdFluidFrom(Fluid result, ItemLike from){
        return result.getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }
    private static String getRecipeIdFluidFrom(Fluid result, Fluid from){
        return result.getRegistryName().getPath() + "_from_" + from.getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        mixing(pConsumer);
    }
}
