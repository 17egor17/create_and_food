package net.egorplaytv.caf.datagen.create;

import net.egorplaytv.caf.datagen.custom.MillingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class MillingRecipes {
    private static void milling(Consumer<FinishedRecipe> pConsumer) {
        MillingRecipeBuilder.millingRecipe(CAFItems.BRICK_DUST.get(), 100)
                .addIngredient(Items.BRICK)
                .save(pConsumer, getMilling(getRecipeId(CAFItems.BRICK_DUST.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.BRICK_DUST.get(), 4, 100)
                .addIngredient(Items.BRICKS)
                .addResult(CAFItems.BRICK_DUST.get(), 0.3f)
                .save(pConsumer, getMilling(getRecipeIdItemFrom(CAFItems.BRICK_DUST.get(), Items.BRICKS)));

        MillingRecipeBuilder.millingRecipe(CAFItems.COAL_DUST.get(), 100)
                .addIngredient(Items.COAL)
                .addResult(CAFItems.COAL_DUST.get(), 0.3f)
                .save(pConsumer, getMilling(getRecipeId(CAFItems.COAL_DUST.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.COCOA_POWDER.get(), 4, 100)
                .addIngredient(CAFItems.HARD_COCOA.get())
                .save(pConsumer, getMilling(getRecipeId(CAFItems.COCOA_POWDER.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.IRON_DUST.get(), 150)
                .addIngredient(Items.IRON_INGOT)
                .save(pConsumer, getMilling(getRecipeId(CAFItems.IRON_DUST.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.POWDERED_SUGAR.get(), 2, 100)
                .addIngredient(Items.SUGAR)
                .addResult(CAFItems.POWDERED_SUGAR.get(), 0.5f, 2)
                .save(pConsumer, getMilling(getRecipeId(CAFItems.POWDERED_SUGAR.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.RYE_FLOUR.get(), 100)
                .addIngredient(CAFItems.RYE.get())
                .addResult(CAFItems.RYE_FLOUR.get(), 0.25f, 2)
                .addResult(CAFItems.RYE_SEEDS.get(), 0.25f, 2)
                .save(pConsumer, getMilling(getRecipeId(CAFItems.RYE_FLOUR.get())));

        MillingRecipeBuilder.millingRecipe(CAFItems.STEEL_DUST.get(), 150)
                .addIngredient(CAFItems.STEEL_INGOT.get())
                .save(pConsumer, getMilling(getRecipeIdItemFrom(CAFItems.STEEL_DUST.get(), CAFItems.STEEL_INGOT.get())));


//        MillingRecipeBuilder.millingRecipe()
//                .addIngredient()
//                .save(pConsumer, getMilling(getRecipeId()));
    }

    public static ResourceLocation getMilling(String id) {
        return new ResourceLocation(MOD_ID, "create/milling/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeIdItemFrom(ItemLike result, ItemLike from){
        return result.asItem().getRegistryName().getPath() + "_from_" + from.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        milling(pConsumer);
    }
}
