package net.egorplaytv.caf.datagen.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.datagen.custom.BlastingRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;
import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFBlastingRecipes {

    private static void blasting(Consumer<FinishedRecipe> pConsumer) {
        new BlastingRecipeBuilder(CAFItems.NETHER_ALLOY.get(), 1, 200, 1000, 0.1F)
                .addIngredient(AllItems.CINDER_FLOUR.get())
                .addIngredient(CAFItems.ALLOY_SOULS.get())
                .unlockedBy("has_cinder_flour", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.CINDER_FLOUR.get()).build()))
                .unlockedBy("has_alloy_souls", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(CAFItems.ALLOY_SOULS.get()).build()))
                .save(pConsumer, getCAFBlasting(getRecipeId(CAFItems.NETHER_ALLOY.get())));
    }


    public static ResourceLocation getCAFBlasting(String id) {
        return new ResourceLocation(MOD_ID, "blasting/" + id);
    }
    public static ResourceLocation getCAFBlastingDouble(String id) {
        return new ResourceLocation(MOD_ID, "blasting/double/" + id);
    }
    public static ResourceLocation getCAFBlastingTriple(String id) {
        return new ResourceLocation(MOD_ID, "blasting/triple/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }


    public static void register(Consumer<FinishedRecipe> pConsumer){
        blasting(pConsumer);
    }
}
