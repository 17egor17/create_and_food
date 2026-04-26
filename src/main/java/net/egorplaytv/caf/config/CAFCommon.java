package net.egorplaytv.caf.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CAFCommon extends ConfigBase {
    public final CAFWorldGeneration worldGen = nested(0, CAFWorldGeneration::new, Comments.worldGen);
    public final CAFGameSettings gameSettings = nested(0, CAFGameSettings::new, Comments.gameSettings);

    @Override
    public String getName() {
        return "common";
    }

    private static class Comments {
        static String worldGen = "World Generation Configs";
        static String gameSettings = "Game Settings";
    }
}
