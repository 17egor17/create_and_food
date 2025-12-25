package net.egorplaytv.create_and_food.util;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraftforge.fluids.FluidStack;

public class FluidJSONUtil {
    public static FluidStack fromJson(JsonElement json){
        return net.egorplaytv.create_and_food.recipe.fluids.FluidStack.CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
    }

    public static JsonElement toJson(FluidStack stack){
        return net.egorplaytv.create_and_food.recipe.fluids.FluidStack.CODEC.encodeStart(JsonOps.INSTANCE, stack).result().orElseThrow();
    }
}
