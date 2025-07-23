package net.egorplaytv.create_and_food.integration;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.compat.jei.CreateJEI;
import com.simibubi.create.compat.jei.DoubleItemIcon;
import com.simibubi.create.compat.jei.EmptyBackground;
import com.simibubi.create.compat.jei.ItemIcon;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.ProcessingViaFanCategory;
import com.simibubi.create.content.equipment.sandPaper.SandPaperPolishingRecipe;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.config.CAFConfigs;
import net.egorplaytv.create_and_food.config.CAFRecipes;
import net.egorplaytv.create_and_food.foundation.utility.CreateAndFoodLang;
import net.egorplaytv.create_and_food.integration.jei.resource.ingredientInfo;
import net.egorplaytv.create_and_food.recipe.*;
import net.egorplaytv.create_and_food.screen.FermentationBarrelMenu;
import net.egorplaytv.create_and_food.screen.FermentationBarrelScreen;
import net.egorplaytv.create_and_food.screen.MarbleBlastFurnaceMenu;
import net.egorplaytv.create_and_food.screen.MarbleBlastFurnaceScreen;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.simibubi.create.compat.jei.CreateJEI.consumeAllRecipes;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class JEICreateAndFoodPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CreateAndFood.MOD_ID, "jei_plugin");
    }

    private final List<CreateRecipeCategory<?>> allCategories = new ArrayList<>();

    private void loadCategories() {
        allCategories.clear();

        CreateRecipeCategory<?>
                chopping = builder(BasinRecipe.class)
                        .addTypedRecipes(AllRecipeTypes.CHOPPING)
                        .catalyst(ModBlocks.MECHANICAL_BLENDER::get)
                        .catalyst(AllBlocks.BASIN::get)
                        .doubleItemIcon(ModBlocks.MECHANICAL_BLENDER.get(), AllBlocks.BASIN.get())
                        .emptyBackground(177, 103)
                        .build("chopping", BlenderCategory::standard),

                beating = builder(BasinRecipe.class)
                        .addTypedRecipes(AllRecipeTypes.BEATING)
                        .catalyst(ModBlocks.MECHANICAL_BLENDER::get)
                        .catalyst(AllBlocks.BASIN::get)
                        .doubleItemIcon(ModBlocks.MECHANICAL_BLENDER.get(), AllBlocks.BASIN.get())
                        .emptyBackground(177, 103)
                        .build("beating", BlenderCategory::beating),

                polishing = builder(PolishingRecipe.class)
                        .addTypedRecipes(AllRecipeTypes.POLISHING)
                        .catalyst(ModBlocks.MECHANICAL_GRINDER::get)
                        .itemIcon(ModBlocks.MECHANICAL_GRINDER.get())
                        .emptyBackground(177, 85)
                        .build("polishing", GrinderPolishingCategory::new),

                grinder_sandpaper_polishing = builder(SandPaperPolishingRecipe.class)
                        .enableWhen(c -> c.allowSandpaperPolishingOnGrinder)
                        .addAllRecipesIf(r -> r instanceof SandPaperPolishingRecipe
                                && ModRecipesList.isPolishing(r))
                        .catalyst(ModBlocks.MECHANICAL_GRINDER::get)
                        .doubleItemIcon(ModBlocks.MECHANICAL_GRINDER.get(), AllItems.SAND_PAPER.get())
                        .emptyBackground(177, 85)
                        .build("grinder_sandpaper_polishing", GrinderSandpaperPolishingCategory::new),

                freezing = builder(FreezingRecipe.class)
                        .addTypedRecipes(AllRecipeTypes.FREEZING)
                        .catalystStack(ProcessingViaFanCategory.getFan("fan_freezing"))
                        .doubleItemIcon(AllItems.PROPELLER.get(), Items.POWDER_SNOW_BUCKET)
                        .emptyBackground(178, 72)
                        .build("fan_freezing", FanFreezeCategory::new)
                ;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        loadCategories();
        registration.addRecipeCategories(allCategories.toArray(IRecipeCategory[]::new));

        registration.addRecipeCategories(
                new FermentationBarrelRecipeCategory(guiHelper),
                new MarbleBlastFurnaceRecipeCategory(guiHelper),
                new FuelingRecipeCategory(guiHelper)
        );

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        IIngredientManager ingredientManager = registration.getIngredientManager();

        List<FermentationBarrelRecipe> fermentation = rm.getAllRecipesFor(FermentationBarrelRecipe.Type.INSTANCE);
        registration.addRecipes(FermentationBarrelRecipeCategory.FERMENTATION_TYPE, fermentation);

        List<MarbleFurnaceRecipe> blasting = rm.getAllRecipesFor(MarbleFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(MarbleBlastFurnaceRecipeCategory.BLASTING_TYPE, blasting);

        registration.addRecipes(RecipeTypes.FUELING, FuelingRecipeMaker.getRecipes(ingredientManager));

        allCategories.forEach(c -> c.registerRecipes(registration));

        new ingredientInfo(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FERMENTATION_BARREL.get()), RecipeTypes.FERMENTATION);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MARBLE_BLAST_FURNACE.get()), RecipeTypes.BLASTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MARBLE_BLAST_FURNACE.get()), RecipeTypes.FUELING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.OAK_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SPRUCE_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BIRCH_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.JUNGLE_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ACACIA_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DARK_OAK_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRIMSON_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WARPED_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALMOND_CUTTING_BOARD.get()), FDRecipeTypes.CUTTING);
        allCategories.forEach(c -> c.registerCatalysts(registration));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FermentationBarrelScreen.class, 74, 39, 24, 12,
                FermentationBarrelRecipeCategory.FERMENTATION_TYPE);
        registration.addRecipeClickArea(MarbleBlastFurnaceScreen.class, 55,51,30,10,
                MarbleBlastFurnaceRecipeCategory.BLASTING_TYPE, RecipeTypes.FUELING);
    }


    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(MarbleBlastFurnaceMenu.class, RecipeTypes.BLASTING, 1, 3, 3, 36);
        registration.addRecipeTransferHandler(MarbleBlastFurnaceMenu.class, RecipeTypes.FUELING, 1, 1, 3, 36);
        registration.addRecipeTransferHandler(FermentationBarrelMenu.class, RecipeTypes.FERMENTATION, 1, 4, 3, 36);

    }

    private <T extends Recipe<?>> JEICreateAndFoodPlugin.CategoryBuilder<T> builder(Class<? extends T> recipeClass) {
        return new JEICreateAndFoodPlugin.CategoryBuilder<>(recipeClass);
    }

    private class CategoryBuilder<T extends Recipe<?>> {
        private final Class<? extends T> recipeClass;
        private Predicate<CAFRecipes> predicate = cRecipes -> true;

        private IDrawable background;
        private IDrawable icon;

        private final List<Consumer<List<T>>> recipeListConsumers = new ArrayList<>();
        private final List<Supplier<? extends ItemStack>> catalysts = new ArrayList<>();

        public CategoryBuilder(Class<? extends T> recipeClass) {
            this.recipeClass = recipeClass;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addRecipeListConsumer(Consumer<List<T>> consumer) {
            recipeListConsumers.add(consumer);
            return this;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addRecipes(Supplier<Collection<? extends T>> collection) {
            return addRecipeListConsumer(recipes -> recipes.addAll(collection.get()));
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addTypedRecipes(IRecipeTypeInfo recipeTypeEntry) {
            return addTypedRecipes(recipeTypeEntry::getType);
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addTypedRecipes(Supplier<RecipeType<? extends T>> recipeType) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipes::add, recipeType.get()));
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addTypedRecipes(Supplier<RecipeType<? extends T>> recipeType, Function<Recipe<?>, T> converter) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipe -> recipes.add(converter.apply(recipe)), recipeType.get()));
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> addTypedRecipesIf(Supplier<RecipeType<? extends T>> recipeType, Predicate<Recipe<?>> pred) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipe -> {
                if (pred.test(recipe)) {
                    recipes.add(recipe);
                }
            }, recipeType.get()));
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> catalystStack(Supplier<ItemStack> supplier) {
            catalysts.add(supplier);
            return this;
        }

        public CategoryBuilder<T> addAllRecipesIf(Predicate<Recipe<?>> pred) {
            return addRecipeListConsumer(recipes -> consumeAllRecipes(recipe -> {
                if (pred.test(recipe)) {
                    recipes.add((T) recipe);
                }
            }));
        }

        public CategoryBuilder<T> addAllRecipesIf(Predicate<Recipe<?>> pred, Function<Recipe<?>, T> converter) {
            return addRecipeListConsumer(recipes -> consumeAllRecipes(recipe -> {
                if (pred.test(recipe)) {
                    recipes.add(converter.apply(recipe));
                }
            }));
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> catalyst(Supplier<ItemLike> supplier) {
            return catalystStack(() -> new ItemStack(supplier.get()
                    .asItem()));
        }

        public CategoryBuilder<T> enableWhen(Function<CAFRecipes, ConfigBase.ConfigBool> configValue) {
            predicate = c -> configValue.apply(c).get();
            return this;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> icon(IDrawable icon) {
            this.icon = icon;
            return this;
        }

        public CategoryBuilder<T> itemIcon(ItemLike item) {
            icon(new ItemIcon(() -> new ItemStack(item)));
            return this;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> doubleItemIcon(ItemLike item1, ItemLike item2) {
            icon(new DoubleItemIcon(() -> new ItemStack(item1), () -> new ItemStack(item2)));
            return this;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> background(IDrawable background) {
            this.background = background;
            return this;
        }

        public JEICreateAndFoodPlugin.CategoryBuilder<T> emptyBackground(int width, int height) {
            background(new EmptyBackground(width, height));
            return this;
        }

        public CreateRecipeCategory<T> build(String name, CreateRecipeCategory.Factory<T> factory) {
            Supplier<List<T>> recipesSupplier;
            if (predicate.test(CAFConfigs.server().recipes)) {
                recipesSupplier = () -> {
                    List<T> recipes = new ArrayList<>();
                    for (Consumer<List<T>> consumer : recipeListConsumers)
                        consumer.accept(recipes);
                    return recipes;
                };
            } else {
                recipesSupplier = () -> Collections.emptyList();
            }

            CreateRecipeCategory.Info<T> info = new CreateRecipeCategory.Info<>(
                    new mezz.jei.api.recipe.RecipeType<>(CreateAndFood.asResource(name), recipeClass),
                    CreateAndFoodLang.translateDirect("recipe." + name), background, icon, recipesSupplier, catalysts);
            CreateRecipeCategory<T> category = factory.create(info);
            allCategories.add(category);
            return category;
        }
    }
}
