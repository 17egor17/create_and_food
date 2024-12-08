package net.egorplaytv.create_and_food.block.custom.connect;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.foundation.utility.Couple;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ModPartialModels {
    public static final Map<ResourceLocation, Couple<PartialModel>> FOLDING_DOORS = new HashMap<>();
    public static final PartialModel CANON_HEAD = block("canon/head");

    static {
        FluidTransportBehaviour.AttachmentTypes.ComponentPartials[] var0 = FluidTransportBehaviour.AttachmentTypes.ComponentPartials.values();
        int var1 = var0.length;


        putFoldingDoor("steel_door");
        var0 = FluidTransportBehaviour.AttachmentTypes.ComponentPartials.values();
        var1 = var0.length;


        putFoldingDoor("steel_door");
    }

    private static void putFoldingDoor(String path) {
        FOLDING_DOORS.put(CreateAndFood.asResource(path),
                Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
    }
    private static PartialModel block(String path) {
        return new PartialModel(CreateAndFood.asResource("block/" + path));
    }

    public static void init() {
        // init static fields
    }
}
