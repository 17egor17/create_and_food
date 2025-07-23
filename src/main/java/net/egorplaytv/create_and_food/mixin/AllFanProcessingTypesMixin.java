package net.egorplaytv.create_and_food.mixin;

import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingTypeRegistry;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import java.util.Map;
import net.egorplaytv.create_and_food.recipe.FanTypes.FreezingType;
import org.spongepowered.asm.mixin.*;

@Mixin(AllFanProcessingTypes.class)
public abstract class AllFanProcessingTypesMixin {
    private static final FreezingType FREEZING = register("freezing", new FreezingType());

    @Shadow private static <T extends FanProcessingType> T register(String id, T type) {
        FanProcessingTypeRegistry.register(Create.asResource(id), type);
        return type;
    }

    @Mutable
    @Shadow @Final private static Map<String, FanProcessingType> LEGACY_NAME_MAP;

    static {
        Object2ReferenceOpenHashMap<String, FanProcessingType> map = new Object2ReferenceOpenHashMap<>();
        map.put("FREEZING", FREEZING);
        map.trim();
        LEGACY_NAME_MAP = map;
    }
}