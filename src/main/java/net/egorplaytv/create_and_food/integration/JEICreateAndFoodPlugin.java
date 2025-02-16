package net.egorplaytv.create_and_food.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.integration.jei.resource.ingredientInfo;
import net.egorplaytv.create_and_food.recipe.*;
import net.egorplaytv.create_and_food.screen.FermentationBarrelScreen;
import net.egorplaytv.create_and_food.screen.MarbleBlastFurnaceScreen;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class JEICreateAndFoodPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CreateAndFood.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new FermentationFluidBarrelRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FermentationItemBarrelRecipeCategory(guiHelper));
        registration.addRecipeCategories(new MarbleBlastFurnaceRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        IIngredientManager ingredientManager = registration.getIngredientManager();

        List<FermentationFluidBarrelRecipe> fermentationFluid = rm.getAllRecipesFor(FermentationFluidBarrelRecipe.Type.INSTANCE);
        registration.addRecipes(FermentationFluidBarrelRecipeCategory.FERMENTATION_FLUID_TYPE, fermentationFluid);

        List<FermentationItemBarrelRecipe> fermentationItem = rm.getAllRecipesFor(FermentationItemBarrelRecipe.Type.INSTANCE);
        registration.addRecipes(FermentationItemBarrelRecipeCategory.FERMENTATION_ITEM_TYPE, fermentationItem);

        List<MarbleFurnaceRecipe> blasting = rm.getAllRecipesFor(MarbleFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(MarbleBlastFurnaceRecipeCategory.BLASTING_TYPE, blasting);



        new ingredientInfo(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FERMENTATION_BARREL.get()), RecipeTypes.FERMENTATION_FLUID, RecipeTypes.FERMENTATION_ITEM);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MARBLE_BLAST_FURNACE.get()), RecipeTypes.BLASTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.OAK_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SPRUCE_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BIRCH_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.JUNGLE_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ACACIA_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DARK_OAK_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRIMSON_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WARPED_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALMOND_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FermentationBarrelScreen.class, 74, 39, 24, 12,
                FermentationFluidBarrelRecipeCategory.FERMENTATION_FLUID_TYPE, FermentationItemBarrelRecipeCategory.FERMENTATION_ITEM_TYPE);
        registration.addRecipeClickArea(MarbleBlastFurnaceScreen.class, 55,51,30,10,
                MarbleBlastFurnaceRecipeCategory.BLASTING_TYPE);
    }
}
