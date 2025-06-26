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
    public static final PartialModel
            MECHANICAL_BLENDER_HEAD = block("mechanical_blender/head"),
            MECHANICAL_BLENDER_POLE = block("mechanical_blender/pole"),
            STEEL_COGWHEEL = block("steel_cogwheel_shaftless"),
            LARGE_STEEL_COGWHEEL = block("large_steel_cogwheel_shaftless"),
            COGWHEEL_STEEL_SHAFT = block("cogwheel_steel_shaft"),
            STEEL_SHAFT_HALF = block("steel_shaft_half"),
            SCALES_HEAD = block("kitchen_scales/head"),
            FLUID_BOX = customBlock("vases/fluid_box"),
            GRINDER_BELT_ACTIVE = block("mechanical_grinder/belt_active"),
            GRINDER_BELT_INACTIVE = block("mechanical_grinder/belt_inactive"),
            GRINDER_BELT_REVERSED = block("mechanical_grinder/belt_reversed")
            ;

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
    private static PartialModel customBlock(String path) {
        return new PartialModel(CreateAndFood.asResource("custom/" + path));
    }

    public static void init() {
        // init static fields
    }
}
