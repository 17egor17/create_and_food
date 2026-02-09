package net.egorplaytv.caf.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.util.FluidJSONUtil;
import net.egorplaytv.caf.util.ItemIngredient;
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

import java.util.ArrayList;
import java.util.List;

public class FermentationBarrelRecipe implements Recipe<SimpleContainer> {
    protected final ResourceLocation id;
    protected final NonNullList<ItemIngredient> inputItems;
    protected final NonNullList<FluidIngredient> inputFluid;
    protected final FluidStack outputFluid;
    protected final NonNullList<ItemStack> output;
    protected final ItemStack inputTool;
    protected int time;
    public FermentationBarrelRecipe(ResourceLocation id, NonNullList<ItemStack> output, FluidStack outputFluid,
                                    NonNullList<ItemIngredient> recipeItems, NonNullList<FluidIngredient> inputFluid, ItemStack inputTool, int time) {
        this.id = id;
        this.output = output;
        this.outputFluid = outputFluid;
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
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (ItemIngredient ingredient : inputItems) {
            for (ItemStack stack : ingredient.getMatchingItemStacks()) {
                ingredients.add(Ingredient.of(stack));
            }
        }
        return ingredients;
    }

    public NonNullList<ItemIngredient> getInputItems() {
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

    public List<FluidStack> getInputFluid(){
        return inputFluid.get(0).getMatchingFluidStacks();
    }

    public FluidIngredient getInputFluidIngredient(){
        return inputFluid.get(0);
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
        return output.isEmpty() ? ItemStack.EMPTY : output.get(0);
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
            NonNullList<ItemIngredient> inputs = NonNullList.create();
            NonNullList<FluidIngredient> inputFluid = NonNullList.create();
            FluidStack fluidResult = FluidStack.EMPTY;
            NonNullList<ItemStack> output = NonNullList.create();

            for (JsonElement je : GsonHelper.getAsJsonArray(json, "ingredients")) {
                if (FluidIngredient.isFluidIngredient(je))
                    inputFluid.add(FluidIngredient.deserialize(je));
                else if (ItemIngredient.isItemIngredient(je))
                    inputs.add(ItemIngredient.deserialize(je));
            }

            for (JsonElement je : GsonHelper.getAsJsonArray(json, "results")) {
                JsonObject jsonObject = je.getAsJsonObject();
                if (GsonHelper.isValidNode(jsonObject, "fluid"))
                    fluidResult = FluidJSONUtil.fromJson(je);
                else
                    output.add(ShapedRecipe.itemStackFromJson(jsonObject));
            }

            int time = GsonHelper.getAsInt(json, "time", 1000);
            ItemStack Tool = GsonHelper.isValidNode(json, "tool") ? CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "tool"), true) : ItemStack.EMPTY;
            return new FermentationBarrelRecipe(id, output, fluidResult, inputs, inputFluid, Tool, time);

        }

        @Nullable
        @Override
        public FermentationBarrelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<ItemIngredient> inputs = NonNullList.create();
            NonNullList<FluidIngredient> inputFluids = NonNullList.create();
            NonNullList<ItemStack> output = NonNullList.create();

            int size = buf.readVarInt();
            for (int i = 0; i < size; i++)
                inputs.add(ItemIngredient.read(buf));

            size = buf.readVarInt();
            for (int i = 0; i < size; i++)
                inputFluids.add(FluidIngredient.read(buf));

            FluidStack fluidResult = buf.readFluidStack();

            size = buf.readVarInt();
            for (int i = 0; i < size; i++)
                output.add(buf.readItem());

            ItemStack Tool = buf.readItem();
            int time = buf.readVarInt();
            return new FermentationBarrelRecipe(id, output, fluidResult, inputs, inputFluids, Tool, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FermentationBarrelRecipe recipe) {
            NonNullList<ItemIngredient> inputs = recipe.inputItems;
            NonNullList<FluidIngredient> fluidIngredient = recipe.inputFluid;
            NonNullList<ItemStack> output = recipe.output;
            buf.writeInt(inputs.size());
            inputs.forEach(i -> i.write(buf));
            buf.writeVarInt(fluidIngredient.size());
            fluidIngredient.forEach(i -> i.write(buf));
            buf.writeFluidStack(recipe.getResultFluid());
            buf.writeVarInt(output.size());
            output.forEach(o -> buf.writeItemStack(o, false));
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
