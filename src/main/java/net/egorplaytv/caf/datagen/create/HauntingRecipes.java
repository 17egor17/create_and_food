package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.HauntingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class HauntingRecipes {
    private static void haunting(Consumer<FinishedRecipe> pConsumer) {
        HauntingRecipeBuilder.sandpaperPolishingRecipe(CAFItems.ALLOY_SOULS_INGOT.get())
                .addIngredient(Items.IRON_INGOT)
                .save(pConsumer, getHaunting(getRecipeId(CAFItems.ALLOY_SOULS_INGOT.get())));

        HauntingRecipeBuilder.sandpaperPolishingRecipe(AllItems.BRASS_NUGGET.get())
                .addIngredient(CAFItems.GLOWING_BRASS_INGOT.get())
                .addResult(AllItems.POLISHED_ROSE_QUARTZ.get())
                .save(pConsumer, getHaunting(getRecipeId(AllItems.BRASS_NUGGET.get())));

        HauntingRecipeBuilder.sandpaperPolishingRecipe(AllItems.POLISHED_ROSE_QUARTZ.get())
                .addIngredient(CAFItems.GLOWING_BRASS_NUGGET.get())
                .save(pConsumer, getHaunting(getRecipeId(AllItems.POLISHED_ROSE_QUARTZ.get())));

        HauntingRecipeBuilder.sandpaperPolishingRecipe(AllItems.BRASS_NUGGET.get(), 4)
                .addIngredient(CAFItems.GLOWING_BRASS_SHEET.get())
                .save(pConsumer, getHaunting(getRecipeIdItemFrom(AllItems.BRASS_NUGGET.get(), CAFItems.GLOWING_BRASS_SHEET.get())));

        HauntingRecipeBuilder.sandpaperPolishingRecipe(CAFItems.SOUL_LANTERN.get())
                .addIngredient(CAFItems.LANTERN.get())
                .save(pConsumer, getHaunting(getRecipeId(CAFItems.SOUL_LANTERN.get())));

//        HauntingRecipeBuilder.sandpaperPolishingRecipe()
//                .addIngredient()
//                .save(pConsumer, getHaunting(getRecipeId()));
    }


    public static ResourceLocation getHaunting(String id) {
        return new ResourceLocation(MOD_ID, "create/haunting/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeIdItemFrom(ItemLike result, ItemLike from){
        return result.asItem().getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        haunting(pConsumer);
    }
}
