package net.egorplaytv.caf.util;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ItemIngredient implements Predicate<ItemStack> {

    public static final ItemIngredient EMPTY = new ItemIngredient.ItemStackIngredient();

    public List<ItemStack> matchingItemStacks;

    protected int count;

    protected abstract boolean testInternal(ItemStack t);

    protected abstract void readInternal(FriendlyByteBuf buffer);

    protected abstract void writeInternal(FriendlyByteBuf buffer);

    protected abstract void readInternal(JsonObject json);

    protected abstract void writeInternal(JsonObject json);

    protected abstract List<ItemStack> determineMatchingItemStacks();

    public int getRequiredCount() {
        return count;
    }

    public List<ItemStack> getMatchingItemStacks() {
        if (matchingItemStacks != null)
            return matchingItemStacks;
        return matchingItemStacks = determineMatchingItemStacks();
    }


    @Override
    public boolean test(ItemStack itemStack) {
        if (itemStack == null)
            throw new IllegalArgumentException("ItemStack cannot be null");
        return testInternal(itemStack);
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this instanceof ItemTagIngredient);
        buffer.writeVarInt(count);
        writeInternal(buffer);
    }

    public static ItemIngredient read(FriendlyByteBuf buffer) {
        boolean isTagIngredient = buffer.readBoolean();
        ItemIngredient ingredient = isTagIngredient ? new ItemTagIngredient() : new ItemStackIngredient();
        ingredient.count = buffer.readVarInt();
        ingredient.readInternal(buffer);
        return ingredient;
    }

    public JsonObject serialize() {
        JsonObject json = new JsonObject();
        writeInternal(json);
        json.addProperty("count", count);
        return json;
    }

    public static boolean isItemIngredient(@Nullable JsonElement je) {
        if (je == null || je.isJsonNull())
            return false;
        if (!je.isJsonObject())
            return false;
        JsonObject json = je.getAsJsonObject();
        if (json.has("tag"))
            return true;
        else if (json.has("item"))
            return true;
        return false;
    }

    public static ItemIngredient deserialize(@Nullable JsonElement je) {
        if (!isItemIngredient(je))
            throw new JsonSyntaxException("Invalid item ingredient: " + Objects.toString(je));

        JsonObject json = je.getAsJsonObject();
        ItemIngredient ingredient = json.has("tag") ? new ItemTagIngredient() : new ItemStackIngredient();
        ingredient.readInternal(json);

        if (!json.has("count"))
            ingredient.count = 1;
        else ingredient.count = GsonHelper.getAsInt(json, "count");
        return ingredient;
    }

    public static class ItemStackIngredient extends ItemIngredient {

        protected Item item;
        protected CompoundTag tagToMatch;

        public ItemStackIngredient() {
            tagToMatch = new CompoundTag();
        }

        @Override
        protected boolean testInternal(ItemStack t) {
            if (!t.getItem().equals(item))
                return false;
            if (tagToMatch.isEmpty())
                return true;
            CompoundTag tag = t.getOrCreateTag();
            return tag.copy()
                    .merge(tagToMatch)
                    .equals(tag);
        }

        @Override
        protected void readInternal(FriendlyByteBuf buffer) {
            item = buffer.readRegistryId();
            tagToMatch = buffer.readNbt();
        }

        @Override
        protected void writeInternal(FriendlyByteBuf buffer) {
            buffer.writeRegistryId(item);
            buffer.writeNbt(tagToMatch);
        }

        @Override
        protected void readInternal(JsonObject json) {
            ItemStack stack = ShapedRecipe.itemStackFromJson(json);
            item = stack.getItem();
            tagToMatch = stack.getOrCreateTag();
        }

        @Override
        protected void writeInternal(JsonObject json) {
            json.addProperty("item", RegisteredObjects.getKeyOrThrow(item)
                    .toString());
            json.add("nbt", JsonParser.parseString(tagToMatch.toString()));
        }

        @Override
        protected List<ItemStack> determineMatchingItemStacks() {
            return ImmutableList.of(tagToMatch.isEmpty() ? new ItemStack(item, count)
                    : new ItemStack(item, count, tagToMatch));
        }
    }

    public static class ItemTagIngredient extends ItemIngredient {

        protected TagKey<Item> tag;

        @SuppressWarnings("deprecation")
        @Override
        protected boolean testInternal(ItemStack t) {
            if (tag == null) {
                for (ItemStack accepted : getMatchingItemStacks())
                    if (accepted.getItem()
                            .equals(t.getItem()))
                        return true;
                return false;
            }
            return t.is(tag);
        }

        @Override
        protected void readInternal(FriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            matchingItemStacks = new ArrayList<>(size);
            for (int i = 0; i < size; i++)
                matchingItemStacks.add(buffer.readItem());
        }

        @Override
        protected void writeInternal(FriendlyByteBuf buffer) {
            // Tag has to be resolved on the server before sending
            List<ItemStack> matchingFluidStacks = getMatchingItemStacks();
            buffer.writeVarInt(matchingFluidStacks.size());
            matchingFluidStacks.stream()
                    .forEach(buffer::writeItem);
        }

        @Override
        protected void readInternal(JsonObject json) {
            ResourceLocation name = new ResourceLocation(GsonHelper.getAsString(json, "tag"));
            tag = ItemTags.create(name);
        }

        @Override
        protected void writeInternal(JsonObject json) {
            json.addProperty("tag", tag.location()
                    .toString());
        }

        @Override
        protected List<ItemStack> determineMatchingItemStacks() {
            return ForgeRegistries.ITEMS.tags()
                    .getTag(tag)
                    .stream()
                    .distinct()
                    .map(f -> new ItemStack(f, count))
                    .collect(Collectors.toList());
        }
    }
}
