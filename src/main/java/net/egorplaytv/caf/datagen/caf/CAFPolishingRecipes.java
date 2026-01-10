package net.egorplaytv.caf.datagen.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.PolishingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFPolishingRecipes {
    private static void polishing(Consumer<FinishedRecipe> pConsumer) {
        PolishingRecipeBuilder.polishingRecipe(CAFItems.RUBY.get(), 20, true)
                .addIngredient(CAFItems.RAW_RUBY.get())
                .save(pConsumer, getPolishing(getRecipeId(CAFItems.RUBY.get())));

        PolishingRecipeBuilder.polishingRecipe(AllItems.POLISHED_ROSE_QUARTZ.get(), 20, true)
                .addIngredient(AllItems.ROSE_QUARTZ.get())
                .save(pConsumer, getPolishing(getRecipeId(AllItems.POLISHED_ROSE_QUARTZ.get())));
    }

    public static ResourceLocation getPolishing(String id) {
        return new ResourceLocation(MOD_ID, "polishing/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer) {
        polishing(pConsumer);
    }
}
