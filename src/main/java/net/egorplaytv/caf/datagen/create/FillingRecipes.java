package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.FillingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class FillingRecipes {
    private static void filling(Consumer<FinishedRecipe> pConsumer) {
        FillingRecipeBuilder.fillingRecipe(CAFItems.RAW_SWEET_ROLL.get())
                .addIngredient(AllItems.DOUGH.get()) // Item
                .addIngredient(CAFTags.CAFFluidTag("creams"), 250) // Fluid
                .save(pConsumer, getFilling(getRecipeId(CAFItems.RAW_SWEET_ROLL.get())));

//        FillingRecipeBuilder.fillingRecipe()
//                .addIngredient() // Item
//                .addIngredient() // Fluid
//                .save(pConsumer, getFilling(getRecipeId()));
    }

    public static ResourceLocation getFilling(String id) {
        return new ResourceLocation(MOD_ID, "create/filling/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        filling(pConsumer);
    }
}
