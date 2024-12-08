package net.egorplaytv.create_and_food.recipe.recipe;

import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Lazy;

public abstract class ICAFSerializableRecipe implements Recipe<Container> {
    public static final Lazy<ItemStack> LAZY_EMPTY;
    protected final Lazy<ItemStack> output;
    protected final RecipeType<?> type;
    protected final ResourceLocation id;
    protected final RecipeSerializer serializer;

    protected ICAFSerializableRecipe(Lazy<ItemStack> output, RecipeType<?> type, ResourceLocation id, RecipeSerializer serializer){
        this.output = output;
        this.type = type;
        this.id = id;
        this.serializer = serializer;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return (ItemStack)this.output.get();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    public static Lazy<ItemStack> of(ItemStack value) {
        return Lazy.of(() -> {
            return value;
        });
    }

    static {
        LAZY_EMPTY = of(ItemStack.EMPTY);
    }
}
