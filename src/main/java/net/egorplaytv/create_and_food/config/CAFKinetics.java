package net.egorplaytv.create_and_food.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CAFKinetics extends ConfigBase {

    public final CAFStress stressValues = nested(1, CAFStress::new, Comments.stress);

    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String stress = "Fine tune the kinetic stats of individual components";
    }
}
