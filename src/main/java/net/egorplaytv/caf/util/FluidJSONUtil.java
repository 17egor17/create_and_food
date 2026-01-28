package net.egorplaytv.caf.util;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.egorplaytv.caf.recipe.fluids.FluidStack;

public class FluidJSONUtil {
    public static net.minecraftforge.fluids.FluidStack fromJson(JsonElement json) {
        return FluidStack.CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
    }

    public static JsonElement toJson(net.minecraftforge.fluids.FluidStack stack) {
        return FluidStack.CODEC.encodeStart(JsonOps.INSTANCE, (FluidStack) stack).result().orElseThrow();
    }
}
