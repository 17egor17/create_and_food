package net.egorplaytv.create_and_food.foundation.block.render;

import net.egorplaytv.create_and_food.block.render.SpriteShiftEntry;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class SpriteShifter {

    private static final Map<String, SpriteShiftEntry> ENTRY_CACHE = new HashMap<>();

    public static SpriteShiftEntry get(ResourceLocation originalLocation, ResourceLocation targetLocation) {
        String key = originalLocation + "->" + targetLocation;
        if (ENTRY_CACHE.containsKey(key))
            return ENTRY_CACHE.get(key);

        SpriteShiftEntry entry = new SpriteShiftEntry();
        entry.set(originalLocation, targetLocation);
        ENTRY_CACHE.put(key, entry);
        return entry;
    }

}
