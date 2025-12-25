package net.egorplaytv.create_and_food.datagen.create;

import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.datagen.custom.CrushingRecipeBuilder;
import net.egorplaytv.create_and_food.item.CAFItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;
import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

public class CrushingRecipes {
    private static void crushing(Consumer<FinishedRecipe> pConsumer) {
        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TANTALUM.get(), 2, 350)
                .addIngredient(CAFBlocks.DEEPSLATE_TANTALUM_ORE.get())
                .addResult(CAFItems.CRASHED_RAW_TANTALUM.get(), 0.25f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLED_DEEPSLATE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.DEEPSLATE_TANTALUM_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TUNGSTEN.get(), 2, 350)
                .addIngredient(CAFBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .addResult(CAFItems.CRASHED_RAW_TUNGSTEN.get(), 0.25f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLED_DEEPSLATE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.DEEPSLATE_TUNGSTEN_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TANTALUM.get(), 400)
                .addIngredient(CAFItems.RAW_TANTALUM.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.RAW_TANTALUM.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TANTALUM.get(), 9, 400)
                .addIngredient(CAFBlocks.RAW_TANTALUM_BLOCK.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f, 9)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.RAW_TANTALUM_BLOCK.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TUNGSTEN.get(), 400)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.RAW_TUNGSTEN.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRASHED_RAW_TUNGSTEN.get(), 9, 400)
                .addIngredient(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f, 9)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.RAW_TANTALUM.get(), 250)
                .addIngredient(CAFBlocks.STONE_TANTALUM_ORE.get())
                .addResult(CAFItems.CRASHED_RAW_TANTALUM.get(), 0.75f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLESTONE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.STONE_TANTALUM_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.RAW_TUNGSTEN.get(), 250)
                .addIngredient(CAFBlocks.STONE_TUNGSTEN_ORE.get())
                .addResult(CAFItems.RAW_TUNGSTEN.get(), 0.75f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLESTONE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.STONE_TUNGSTEN_ORE.get())));

//        CrushingRecipeBuilder.crushingRecipe()
//                .addIngredient()
//                .save(pConsumer, getCrushing(getRecipeId()));
    }

    public static ResourceLocation getCrushing(String id) {
        return new ResourceLocation(MOD_ID, "create/crushing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        crushing(pConsumer);
    }
}
