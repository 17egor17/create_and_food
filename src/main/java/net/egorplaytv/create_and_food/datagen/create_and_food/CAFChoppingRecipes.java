package net.egorplaytv.create_and_food.datagen.create_and_food;

import net.egorplaytv.create_and_food.datagen.custom.ChoppingRecipeBuilder;
import net.egorplaytv.create_and_food.fluid.CAFFluids;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.egorplaytv.create_and_food.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class CAFChoppingRecipes {
    private static void chopping(Consumer<FinishedRecipe> pConsumer) {
        ChoppingRecipeBuilder.choppingRecipe(Items.MAGENTA_DYE, 2)
                .addIngredient(Items.ALLIUM)
                .save(pConsumer, getChopping(getRecipeId(Items.ALLIUM)));

        ChoppingRecipeBuilder.choppingRecipe(Items.LIGHT_GRAY_DYE, 2)
                .addIngredient(Items.AZURE_BLUET)
                .save(pConsumer, getChopping(getRecipeId(Items.AZURE_BLUET)));

        ChoppingRecipeBuilder.choppingRecipe(Items.LIGHT_BLUE_DYE, 2)
                .addIngredient(Items.BLUE_ORCHID)
                .save(pConsumer, getChopping(getRecipeId(Items.BLUE_ORCHID)));

        ChoppingRecipeBuilder.choppingRecipe(Items.BONE_MEAL, 3)
                .addIngredient(Items.BONE)
                .addResult(Items.WHITE_DYE, 0.25f)
                .addResult(Items.BONE_MEAL, 0.25f, 3)
                .save(pConsumer, getChopping(getRecipeId(Items.BONE)));

        ChoppingRecipeBuilder.choppingRecipe(Items.BLUE_DYE, 2)
                .addIngredient(Items.CORNFLOWER)
                .save(pConsumer, getChopping(getRecipeId(Items.CORNFLOWER)));

        ChoppingRecipeBuilder.choppingRecipe(Items.YELLOW_DYE, 2)
                .addIngredient(Items.DANDELION)
                .save(pConsumer, getChopping(getRecipeId(Items.DANDELION)));

        ChoppingRecipeBuilder.choppingRecipe(Items.BLACK_DYE, 2)
                .addIngredient(Items.INK_SAC)
                .save(pConsumer, getChopping(getRecipeId(Items.INK_SAC)));

        ChoppingRecipeBuilder.choppingRecipe(Items.WHITE_DYE, 2)
                .addIngredient(Items.LILY_OF_THE_VALLEY)
                .save(pConsumer, getChopping(getRecipeId(Items.LILY_OF_THE_VALLEY)));

        ChoppingRecipeBuilder.choppingRecipe(ModItems.MINCED_BEEF.get(), 2)
                .addIngredient(Items.BEEF)
                .save(pConsumer, getChopping(getRecipeId(ModItems.MINCED_BEEF.get())));

        ChoppingRecipeBuilder.choppingRecipe(Items.ORANGE_DYE, 2)
                .addIngredient(Items.ORANGE_TULIP)
                .save(pConsumer, getChopping(getRecipeId(Items.ORANGE_TULIP)));

        ChoppingRecipeBuilder.choppingRecipe(Items.LIGHT_GRAY_DYE, 2)
                .addIngredient(Items.OXEYE_DAISY)
                .save(pConsumer, getChopping(getRecipeId(Items.OXEYE_DAISY)));

        ChoppingRecipeBuilder.choppingRecipe(Items.PINK_DYE, 2)
                .addIngredient(Items.PINK_TULIP)
                .save(pConsumer, getChopping(getRecipeId(Items.PINK_TULIP)));

        ChoppingRecipeBuilder.choppingRecipe(Items.RED_DYE, 2)
                .addIngredient(Items.POPPY)
                .save(pConsumer, getChopping(getRecipeId(Items.POPPY)));

        ChoppingRecipeBuilder.choppingRecipe(ModItems.RAW_PASTA.get())
                .addIngredient(ModItems.WHEAT_DOUGH.get())
                .save(pConsumer, getChopping(getRecipeId(ModItems.RAW_PASTA.get())));

        ChoppingRecipeBuilder.choppingRecipe(CAFFluids.RED_GRAPE_JUICE.get(), 250)
                .addIngredient(CAFTags.CAFItemTag("grapes/red_juice"))
                .save(pConsumer, getChopping(getRecipeId(CAFFluids.RED_GRAPE_JUICE.get())));

        ChoppingRecipeBuilder.choppingRecipe(Items.RED_DYE, 2)
                .addIngredient(Items.RED_TULIP)
                .save(pConsumer, getChopping(getRecipeId(Items.RED_TULIP)));

        ChoppingRecipeBuilder.choppingRecipe(CAFItems.RICE.get())
                .addIngredient(ModItems.RICE_PANICLE.get())
                .addResult(ModItems.STRAW.get())
                .save(pConsumer, getChopping(getRecipeId(ModItems.RICE_PANICLE.get())));

        ChoppingRecipeBuilder.choppingRecipe(Items.WHITE_DYE, 2)
                .addIngredient(Items.WHITE_TULIP)
                .save(pConsumer, getChopping(getRecipeId(Items.WHITE_TULIP)));

        ChoppingRecipeBuilder.choppingRecipe(Items.BEETROOT)
                .addIngredient(ModItems.WILD_BEETROOTS.get())
                .addResult(Items.RED_DYE)
                .save(pConsumer, getChopping(getRecipeId(ModItems.WILD_BEETROOTS.get())));

        ChoppingRecipeBuilder.choppingRecipe(ModItems.CABBAGE_SEEDS.get())
                .addIngredient(ModItems.WILD_CABBAGES.get())
                .addResult(Items.YELLOW_DYE, 0.5f)
                .save(pConsumer, getChopping(getRecipeId(ModItems.WILD_CABBAGES.get())));

        ChoppingRecipeBuilder.choppingRecipe(CAFItems.RICE.get())
                .addIngredient(ModItems.WILD_RICE.get())
                .addResult(ModItems.STRAW.get(), 0.5f)
                .save(pConsumer, getChopping(getRecipeId(ModItems.WILD_RICE.get())));

        ChoppingRecipeBuilder.choppingRecipe(Items.BLACK_DYE, 2)
                .addIngredient(Items.WITHER_ROSE)
                .save(pConsumer, getChopping(getRecipeId(Items.WITHER_ROSE)));
    }

    public static ResourceLocation getChopping(String id) {
        return new ResourceLocation(MOD_ID, "chopping/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer) {
        chopping(pConsumer);
    }
}
