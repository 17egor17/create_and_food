package net.egorplaytv.create_and_food.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.util.FluidJSONUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class FermentationBarrelRecipe implements Recipe<SimpleContainer> {
    protected final ResourceLocation id;
    protected final ItemStack output;
    protected final NonNullList<Ingredient> inputItems;
    protected final FluidStack inputFluid;
    protected final ItemStack inputTool;
    protected int time;
    public FermentationBarrelRecipe(ResourceLocation id, ItemStack output,
                                    NonNullList<Ingredient> recipeItems, FluidStack inputFluid, ItemStack inputTool, int time) {
        this.id = id;
        this.output = output;
        this.inputItems = recipeItems;
        this.inputFluid = inputFluid;
        this.inputTool = inputTool;
        this.time = time;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public ItemStack getInputTool(){
        return inputTool;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public boolean matches(SimpleContainer pContainer,Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        List<ItemStack> inputs = new ArrayList();
        int i = 0;
//   Your count slots + 1 \/
        for(int j = 1; j < 4; ++j) {
            ItemStack itemstack = pContainer.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    public FluidStack getInputFluid(){
        return inputFluid;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.inputItems.size();
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<FermentationBarrelRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "fermentation";
    }
    public static class Serializer implements RecipeSerializer<FermentationBarrelRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(CreateAndFood.MOD_ID, "fermentation");


        @Override
        public FermentationBarrelRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> inputs = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (inputs.isEmpty()) {
                throw new JsonParseException("No ingredients for fermentation recipe");
            } else if (inputs.size() > 3) {
                throw new JsonParseException("Too many ingredients for fermentation recipe! The max is 3");
            } else {
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                FluidStack inputFluid = FluidJSONUtil.readFluid(json.get("fluid_ingredient"));
                ItemStack Tool = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "tool"));
                int time = GsonHelper.getAsInt(json, "time", 1000);

                return new FermentationBarrelRecipe(id, output, inputs, inputFluid, Tool, time);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonNullList = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonNullList.add(ingredient);
                }
            }

            return nonNullList;
        }

        @Nullable
        @Override
        public FermentationBarrelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            FluidStack inputFluid = buf.readFluidStack();

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            ItemStack Tool = buf.readItem();
            int time = buf.readVarInt();
            return new FermentationBarrelRecipe(id, output, inputs, inputFluid, Tool, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FermentationBarrelRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeFluidStack(recipe.inputFluid);
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeItemStack(recipe.getInputTool(), false);
            buf.writeVarInt(recipe.time);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>) cls;
        }
    }
}
