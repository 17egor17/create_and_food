package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.datagen.custom.CrushingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CrushingRecipes {
    private static void crushing(Consumer<FinishedRecipe> pConsumer) {
        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TANTALUM.get(), 2, 350)
                .addIngredient(CAFBlocks.DEEPSLATE_TANTALUM_ORE.get())
                .addResult(CAFItems.CRUSHED_RAW_TANTALUM.get(), 0.25f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLED_DEEPSLATE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.DEEPSLATE_TANTALUM_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TUNGSTEN.get(), 2, 350)
                .addIngredient(CAFBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .addResult(CAFItems.CRUSHED_RAW_TUNGSTEN.get(), 0.25f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLED_DEEPSLATE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.DEEPSLATE_TUNGSTEN_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TANTALUM.get(), 400)
                .addIngredient(CAFItems.RAW_TANTALUM.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.RAW_TANTALUM.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TANTALUM.get(), 9, 400)
                .addIngredient(CAFBlocks.RAW_TANTALUM_BLOCK.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f, 9)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.RAW_TANTALUM_BLOCK.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TUNGSTEN.get(), 400)
                .addIngredient(CAFItems.RAW_TUNGSTEN.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.RAW_TUNGSTEN.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRUSHED_RAW_TUNGSTEN.get(), 9, 400)
                .addIngredient(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f, 9)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.RAW_TUNGSTEN_BLOCK.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.RAW_TANTALUM.get(), 250)
                .addIngredient(CAFBlocks.STONE_TANTALUM_ORE.get())
                .addResult(CAFItems.CRUSHED_RAW_TANTALUM.get(), 0.75f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLESTONE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.STONE_TANTALUM_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.RAW_TUNGSTEN.get(), 250)
                .addIngredient(CAFBlocks.STONE_TUNGSTEN_ORE.get())
                .addResult(CAFItems.RAW_TUNGSTEN.get(), 0.75f)
                .addResult(AllItems.EXP_NUGGET.get(), 0.75f)
                .addResult(Items.COBBLESTONE, 0.125f)
                .save(pConsumer, getCrushing(getRecipeId(CAFBlocks.STONE_TUNGSTEN_ORE.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.WOOD_CHIPS.get(), 4, 200)
                .addIngredient(ItemTags.LOGS_THAT_BURN)
                .addResult(CAFItems.WOOD_SAWDUST.get(), 0.25f, 2)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.WOOD_CHIPS.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.WOOD_SAWDUST.get(), 200)
                .addIngredient(CAFItems.WOOD_CHIPS.get())
                .addResult(CAFItems.WOOD_SAWDUST.get(), 0.5f, 1)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.WOOD_SAWDUST.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRIMSON_CHIPS.get(), 4, 200)
                .addIngredient(ItemTags.CRIMSON_STEMS)
                .addResult(CAFItems.CRIMSON_SAWDUST.get(), 0.25f, 2)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.CRIMSON_CHIPS.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.CRIMSON_SAWDUST.get(), 200)
                .addIngredient(CAFItems.CRIMSON_CHIPS.get())
                .addResult(CAFItems.CRIMSON_SAWDUST.get(), 0.5f, 1)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.CRIMSON_SAWDUST.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.WARPED_CHIPS.get(), 4, 200)
                .addIngredient(ItemTags.WARPED_STEMS)
                .addResult(CAFItems.WARPED_SAWDUST.get(), 0.25f, 2)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.WARPED_CHIPS.get())));

        CrushingRecipeBuilder.crushingRecipe(CAFItems.WARPED_SAWDUST.get(), 200)
                .addIngredient(CAFItems.WARPED_CHIPS.get())
                .addResult(CAFItems.WARPED_SAWDUST.get(), 0.5f, 1)
                .save(pConsumer, getCrushing(getRecipeId(CAFItems.WARPED_SAWDUST.get())));


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