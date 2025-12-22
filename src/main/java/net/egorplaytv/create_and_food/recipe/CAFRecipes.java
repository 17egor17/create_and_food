package net.egorplaytv.create_and_food.recipe;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CAFRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CreateAndFood.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FermentationBarrelRecipe>> FERMENTATION_SERIALIZER =
            SERIALIZERS.register("fermentation", () -> FermentationBarrelRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MarbleFurnaceRecipe>> BLASTING_SERIALIZER =
            SERIALIZERS.register("blasting", () -> MarbleFurnaceRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<LegacyRecipe>> LEGACY_RECIPE =
            SERIALIZERS.register("legacy_recipe", () -> {
                return new SimpleRecipeSerializer<>(LegacyRecipe::new);
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
