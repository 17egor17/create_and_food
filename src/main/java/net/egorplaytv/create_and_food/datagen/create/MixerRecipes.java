package net.egorplaytv.create_and_food.datagen.create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.egorplaytv.create_and_food.datagen.custom.MixingRecipeBuilder;
import net.egorplaytv.create_and_food.fluid.CAFFluids;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.util.CAFTags;
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
import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class MixerRecipes {
    private static void mixing(Consumer<FinishedRecipe> pConsumer) {
        MixingRecipeBuilder.mixingRecipe(CAFFluids.APPLE_VINEGAR.get(), 1000, HEATED)
                .addIngredient(Items.SUGAR).addIngredient(Items.SUGAR).addIngredient(Items.SUGAR).addIngredient(Items.SUGAR)
                .addIngredient(Items.APPLE).addIngredient(Items.APPLE).addIngredient(Fluids.WATER, 1000)
                .addIngredient(CAFTags.forgeFluidTag("honey"), 500)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.APPLE_VINEGAR.get())));

        MixingRecipeBuilder.mixingRecipe(AllFluids.CHOCOLATE.get(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR)
                .addIngredient(CAFItems.COCOA_POWDER.get()).addIngredient(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .save(pConsumer, getMixing(getRecipeId(AllFluids.CHOCOLATE.get())));

        MixingRecipeBuilder.mixingRecipe(AllFluids.CHOCOLATE.get(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(Items.SUGAR).addIngredient(CAFItems.COCOA_POWDER.get())
                .addIngredient(CAFFluids.COCOA_OIL.get(), 250)
                .save(pConsumer, getMixing(getRecipeIdFluidFrom(AllFluids.CHOCOLATE.get(), CAFFluids.COCOA_OIL.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.COCOA_OIL.get(), 250, HEATED)
                .addIngredient(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .save(pConsumer, getMixing(getRecipeIdFluidFrom(CAFFluids.COCOA_OIL.get(), CAFItems.COCOA_BUTTER_BRIQUETTE.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CREAM.get(), 500)
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(Items.SUGAR).addIngredient(Items.SUGAR)
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CREAM.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.CUSTARD.get(), 500, HEATED)
                .addIngredient(Tags.Fluids.MILK, 250).addIngredient(CAFItems.RAW_EGG.get())
                .addIngredient(CAFItems.RAW_EGG.get()).addIngredient(Items.SUGAR)
                .addIngredient(Items.SUGAR).addIngredient(AllItems.WHEAT_FLOUR.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.CUSTARD.get())));

        MixingRecipeBuilder.mixingRecipe(CAFFluids.GLAZE.get(), 500)
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.RAW_PROTEIN.get()).addIngredient(CAFItems.RAW_PROTEIN.get())
                .addIngredient(CAFItems.POWDERED_SUGAR.get()).addIngredient(CAFItems.POWDERED_SUGAR.get())
                .save(pConsumer, getMixing(getRecipeId(CAFFluids.GLAZE.get())));

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
