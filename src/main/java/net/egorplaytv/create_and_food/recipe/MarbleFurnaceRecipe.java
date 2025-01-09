package net.egorplaytv.create_and_food.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarbleFurnaceRecipe implements Recipe<SimpleContainer> {
    public static final Serializer SERIALIZER = new Serializer();
    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> inputItems;
    protected final ItemStack output;
    protected int time;
    protected int deg;
    protected float experience;

    public MarbleFurnaceRecipe(ResourceLocation id, ItemStack output,
                                    NonNullList<Ingredient> recipeItems, int time, int deg, float experience) {
        this.id = id;
        this.inputItems = recipeItems;
        this.output = output;
        this.time = time;
        this.deg = deg;
        this.experience = experience;
    }
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }
    public int getDeg() {
        return this.deg;
    }
    public int getTime() {
        return this.time;
    }

    public float getExperience() {
        return this.experience;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
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
    public static class Type implements RecipeType<MarbleFurnaceRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "blasting";
    }
    public static class Serializer implements RecipeSerializer<MarbleFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(CreateAndFood.MOD_ID, "blasting");


        @Override
        public MarbleFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> inputs = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (inputs.isEmpty()) {
                throw new JsonParseException("No ingredients for blasting recipe");
            } else if (inputs.size() > 3) {
                throw new JsonParseException("Too many ingredients for blasting recipe! The max is 3");
            } else {
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                int time = GsonHelper.getAsInt(json, "time", 300);
                int deg = GsonHelper.getAsInt(json, "degree", 100);
                float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);

                return new MarbleFurnaceRecipe(id, output, inputs, time, deg, experience);
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
        public MarbleFurnaceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int i = buf.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < inputs.size(); j++) {
                inputs.set(j, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            int time = buf.readVarInt();
            int deg = buf.readVarInt();
            float experience = buf.readFloat();
            return new MarbleFurnaceRecipe(id, output, inputs, time, deg, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MarbleFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeVarInt(recipe.time);
            buf.writeVarInt(recipe.deg);
            buf.writeFloat(recipe.experience);
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
