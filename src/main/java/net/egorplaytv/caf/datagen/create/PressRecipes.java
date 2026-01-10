package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.egorplaytv.caf.datagen.custom.CompactingRecipeBuilder;
import net.egorplaytv.caf.datagen.custom.PressingRecipeBuilder;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class PressRecipes {
    private static void pressing(Consumer<FinishedRecipe> pConsumer) {
        PressingRecipeBuilder.pressingRecipe(CAFItems.ALLOY_SOULS_SHEET.get())
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .save(pConsumer, getPressing(getRecipeId(CAFItems.ALLOY_SOULS_SHEET.get())));

        PressingRecipeBuilder.pressingRecipe(CAFItems.GLOWING_BRASS_SHEET.get())
                .addIngredient(CAFItems.GLOWING_BRASS_INGOT.get())
                .save(pConsumer, getPressing(getRecipeId(CAFItems.GLOWING_BRASS_SHEET.get())));

        PressingRecipeBuilder.pressingRecipe(CAFItems.STEEL_SHEET.get())
                .addIngredient(CAFTags.Items.STEEL)
                .save(pConsumer, getPressing(getRecipeId(CAFItems.STEEL_SHEET.get())));

//        PressingRecipeBuilder.pressingRecipe()
//                .addIngredient()
//                .save(pConsumer, getPressing(getRecipeId()));
    }

    private static void compacting(Consumer<FinishedRecipe> pConsumer){
        CompactingRecipeBuilder.compactingRecipe(CAFItems.BASE_OF_DOUGH.get())
                .addIngredient(AllItems.DOUGH.get())
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.BASE_OF_DOUGH.get())));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.COCOA_BUTTER_BRIQUETTE.get())
                .addIngredient(CAFFluids.COCOA_OIL.get(), 250)
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.COCOA_BUTTER_BRIQUETTE.get())));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.HARD_COCOA.get(), 0.75f, CAFFluids.COCOA_OIL.get(), 250)
                .addIngredient(CAFItems.ROASTED_COCOA_BEANS.get()).addIngredient(CAFItems.ROASTED_COCOA_BEANS.get())
                .addIngredient(CAFItems.ROASTED_COCOA_BEANS.get()).addIngredient(CAFItems.ROASTED_COCOA_BEANS.get())
                .save(pConsumer, getCompacting(getRecipeId(CAFFluids.COCOA_OIL.get())));

        CompactingRecipeBuilder.compactingRecipe(Items.DIAMOND, HeatCondition.SUPERHEATED)
                .addIngredient(CAFTags.forgeItemTag("dusts/coal")).addIngredient(CAFTags.forgeItemTag("dusts/coal"))
                .addIngredient(CAFTags.forgeItemTag("dusts/coal")).addIngredient(CAFTags.forgeItemTag("dusts/coal"))
                .save(pConsumer, getCompacting(getRecipeId(Items.DIAMOND)));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.SMALL_DOUGH_BASE.get())
                .addIngredient(CAFItems.SMALL_DOUGH.get())
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.SMALL_DOUGH_BASE.get())));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.THIN_CARDBOARD.get())
                .addIngredient(CAFFluids.WOOD_PULP.get().getSource(), 100)
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.THIN_CARDBOARD.get())));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.CRIMSON_THIN_CARDBOARD.get())
                .addIngredient(CAFFluids.CRIMSON_PULP.get().getSource(), 100)
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.CRIMSON_THIN_CARDBOARD.get())));

        CompactingRecipeBuilder.compactingRecipe(CAFItems.WARPED_THIN_CARDBOARD.get())
                .addIngredient(CAFFluids.WARPED_PULP.get().getSource(), 100)
                .save(pConsumer, getCompacting(getRecipeId(CAFItems.WARPED_THIN_CARDBOARD.get())));

        CompactingRecipeBuilder.compactingRecipe(Items.PAPER)
                .addIngredient(CAFFluids.PAPER_PULP.get().getSource(), 50)
                .save(pConsumer, getCompacting(getRecipeId(Items.PAPER)));

//        CompactingRecipeBuilder.compactingRecipe()
//                .addIngredient()
//                .save(pConsumer, getCompacting(getRecipeId()));
    }

    public static ResourceLocation getPressing(String id) {
        return new ResourceLocation(MOD_ID, "create/pressing/" + id);
    }

    public static ResourceLocation getCompacting(String id) {
        return new ResourceLocation(MOD_ID, "create/compacting/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    private static String getRecipeId(Fluid fluid){
        return fluid.getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        pressing(pConsumer);
        compacting(pConsumer);
    }
}
