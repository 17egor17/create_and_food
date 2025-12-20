package net.egorplaytv.create_and_food.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.util.FluidJSONUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FermentationBarrelRecipe implements Recipe<SimpleContainer> {
    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> inputItems;
    protected final FluidStack inputFluid;
    protected final FluidStack outputFluid;
    protected final ItemStack output;
    protected final ItemStack inputTool;
    protected int time;
    public FermentationBarrelRecipe(ResourceLocation id, ItemStack output, FluidStack outputFluid,
                                    NonNullList<Ingredient> recipeItems, FluidStack inputFluid, ItemStack inputTool, int time) {
        this.id = id;
        if (!output.isEmpty()){
            this.output = output;
        } else {
            this.output = ItemStack.EMPTY;
        }
        if (!outputFluid.isEmpty()){
            this.outputFluid = outputFluid;
        } else {
            this.outputFluid = FluidStack.EMPTY;
        }
        this.inputItems = recipeItems;
        this.inputFluid = inputFluid;
        if (!inputTool.isEmpty()) {
            this.inputTool = inputTool;
        } else {
            this.inputTool = ItemStack.EMPTY;
        }
        this.time = time;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public ItemStack getInputTool(){
        return inputTool.isEmpty() ? ItemStack.EMPTY : inputTool;
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

    public FluidStack getResultFluid(){
        return outputFluid.isEmpty() ? FluidStack.EMPTY : outputFluid;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.inputItems.size();
    }

    @Override
    public ItemStack getResultItem() {
        return output.isEmpty() ? ItemStack.EMPTY : output;
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
            NonNullList<Ingredient> inputs = NonNullList.create();
            FluidStack inputFluid = FluidStack.EMPTY;
            FluidStack fluidResult = FluidStack.EMPTY;
            ItemStack output = ItemStack.EMPTY;

            for (JsonElement je : GsonHelper.getAsJsonArray(json, "ingredients")) {
                JsonObject jsonObject = je.getAsJsonObject();
                if (GsonHelper.isValidNode(jsonObject, "fluid") && GsonHelper.isValidNode(jsonObject, "item")) {
                    inputFluid = FluidJSONUtil.fromJson(je);
                    inputs.add(Ingredient.fromJson(je));
                } else if (GsonHelper.isValidNode(jsonObject, "fluid"))
                    inputFluid = FluidJSONUtil.fromJson(je);
                else
                    inputs.add(Ingredient.fromJson(je));
            }

            for (JsonElement je : GsonHelper.getAsJsonArray(json, "results")) {
                JsonObject jsonObject = je.getAsJsonObject();
                if (GsonHelper.isValidNode(jsonObject, "fluid") && GsonHelper.isValidNode(jsonObject, "item")) {
                    fluidResult = FluidJSONUtil.fromJson(je);
                    output = ShapedRecipe.itemStackFromJson(jsonObject);
                } else if (GsonHelper.isValidNode(jsonObject, "fluid"))
                    fluidResult = FluidJSONUtil.fromJson(je);
                else
                    output = ShapedRecipe.itemStackFromJson(jsonObject);

            }
            int time = GsonHelper.getAsInt(json, "time", 1000);
            ItemStack Tool = GsonHelper.isValidNode(json, "tool") ? CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "tool"), true) : ItemStack.EMPTY;
            return new FermentationBarrelRecipe(id, output, fluidResult, inputs, inputFluid, Tool, time);

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
            FluidStack fluidResult = buf.readFluidStack();
            ItemStack Tool = buf.readItem();
            int time = buf.readVarInt();
            return new FermentationBarrelRecipe(id, output, fluidResult, inputs, inputFluid, Tool, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FermentationBarrelRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeFluidStack(recipe.getInputFluid());
            buf.writeFluidStack(recipe.getResultFluid());
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
