package net.egorplaytv.create_and_food.recipe;

import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.recipe.recipe.ICAFSerializableRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BlastFurnaceFuelRecipe extends ICAFSerializableRecipe {
    public final NonNullList<Ingredient> input;
    public final int degree;

    protected BlastFurnaceFuelRecipe(ResourceLocation id, NonNullList<Ingredient> input, int degree) {
        super(LAZY_EMPTY, Type.INSTANCE, id, Serializer.INSTANCE);
        this.input = input;
        this.degree = degree;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return input;
    }

    public int getDeg() {
        return degree;
    }
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return input.get(0).test(pContainer.getItem(1));
    }

    public static class Type implements RecipeType<BlastFurnaceFuelRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "fueling";
    }
    public static class Serializer implements RecipeSerializer<BlastFurnaceFuelRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(CreateAndFood.MOD_ID, "fueling");

        @Override
        public BlastFurnaceFuelRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> input = readIngredients(GsonHelper.getAsJsonArray(json, "fuel"));
            if (input.isEmpty()) {
                throw new JsonParseException("No ingredients for fueling recipe");
            } else if (input.size() > 1) {
                throw new JsonParseException("Too many ingredients for fueling recipe! The max is 1");
            } else {
                int degree = GsonHelper.getAsInt(json, "degree");
                return new BlastFurnaceFuelRecipe(id, input, degree);
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
        public BlastFurnaceFuelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> input = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int degree = buf.readVarInt();
            return new BlastFurnaceFuelRecipe(id, input, degree);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BlastFurnaceFuelRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeVarInt(recipe.degree);
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
