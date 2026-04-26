package net.egorplaytv.caf.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CAFGameSettings extends ConfigBase {
    public final ConfigGroup gameSettings = group(0, "gameSettings");

    public final ConfigInt speedAttenuationBlastFurnace = i(50, 10, 100, "speedAttenuationBlastFurnace", Comments.speedAttenuationBlastFurnace);

    public final ConfigEnum<DegreeUnits> unitsOfMeasurement = e(DegreeUnits.DEGREES_CELSIUS, "unitsOfMeasurement", Comments.unitsOfMeasurement);

    public final ConfigBool enableKilograms = b(true, "enableKilograms", Comments.enableKilograms);
    public final ConfigBool enableGrams = b(true, "enableGrams", Comments.enableGrams);
    public final ConfigBool enableTones = b(true, "enableTones", Comments.enableTones);

    @Override
    public String getName() {
        return "gamesettings";
    }

    private static class Comments {
        //Values
        static String speedAttenuationBlastFurnace = "Time In Seconds";
        static String unitsOfMeasurement = "Units Of Measurement";
        static String enableKilograms = "Includes Kilograms";
        static String enableGrams = "Includes Grams";
        static String enableTones = "Includes Tones";
    }
}
