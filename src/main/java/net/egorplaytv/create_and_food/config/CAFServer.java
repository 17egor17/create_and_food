package net.egorplaytv.create_and_food.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CAFServer extends ConfigBase {
    public final CAFRecipes recipes = nested(0, CAFRecipes::new, Comments.recipes);
    public final CAFKinetics kinetics = nested(0, CAFKinetics::new, Comments.kinetics);

    @Override
    public String getName() {
        return "server";
    }

    private static class Comments {
        static String recipes = "Packmakers' control panel for internal recipe compat";
        static String kinetics = "Parameters and abilities of Vintage Improvements's kinetic mechanisms";
    }
}
