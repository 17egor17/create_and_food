package net.egorplaytv.caf.datagen.caf;

import blusunrize.immersiveengineering.common.register.IEFluids;
import com.simibubi.create.AllFluids;
import net.egorplaytv.caf.datagen.custom.FermentationRecipeBuilder;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFFermentationRecipes {

    private static void fermentation(Consumer<FinishedRecipe> pConsumer) {
        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 70, IEFluids.ETHANOL.getStill(), 80)
                .addIngredient(Items.POTATO).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.POTATO), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 10, IEFluids.ETHANOL.getStill(), 20)
                .addIngredient(Items.MELON_SLICE).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.MELON_SLICE), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 70, IEFluids.ETHANOL.getStill(), 80)
                .addIngredient(ModItems.TOMATO.get()).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), ModItems.TOMATO.get()), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 40, IEFluids.ETHANOL.getStill(), 50)
                .addIngredient(Items.SWEET_BERRIES).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.SWEET_BERRIES), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 70, IEFluids.ETHANOL.getStill(), 80)
                .addIngredient(Items.APPLE).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.APPLE), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 70, IEFluids.ETHANOL.getStill(), 80)
                .addIngredient(Items.SUGAR_CANE).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.SUGAR_CANE), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 90, IEFluids.ETHANOL.getStill(), 100)
                .addIngredient(Items.GLOW_BERRIES).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.GLOW_BERRIES), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(AllFluids.HONEY.get(), 250, IEFluids.ETHANOL.getStill(), 250)
                .addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill()), ModID.IE));

        FermentationRecipeBuilder.fermentationRecipe(Fluids.WATER, 30, IEFluids.ETHANOL.getStill(), 40)
                .addIngredient(Items.BEETROOT).addTimeInSeconds(40)
                .save(pConsumer, getFermentationCompat(getRecipeId(IEFluids.ETHANOL.getStill(), Items.BEETROOT), ModID.IE));

    }

    public static ResourceLocation getFermentation(String id) {
        return new ResourceLocation(MOD_ID, "fermentation/" + id);
    }

    private static ResourceLocation getFermentationCompat(String id, ModID modID) {
        return switch (modID) {
            case IE -> new ResourceLocation("immersiveengineering", "fermentation/" + id);
            case THERMAL -> new ResourceLocation("thermal", "fermentation/" + id);
            case MEKANISM -> new ResourceLocation("mekanism", "fermentation/" + id);
        };
    }

    private static String getRecipeId(ItemLike item, ItemLike from) {
        return item.asItem().getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(ItemLike item) {
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(Fluid fluid, ItemLike from) {
        return fluid.getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(Fluid fluid) {
        return fluid.getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer) {
        fermentation(pConsumer);
    }

    private enum ModID {
        IE, MEKANISM, THERMAL
    }
}
