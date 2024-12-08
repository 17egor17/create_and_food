package net.egorplaytv.create_and_food;


import com.tterrag.registrate.providers.ProviderType;
import net.egorplaytv.create_and_food.ponder.CAFPonders;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.function.BiConsumer;

public class CreateAndFoodDataGen {
    public static void gatherData(GatherDataEvent event) {
        addExtraRegistrateData();
    }

    private static void addExtraRegistrateData() {
        CreateAndFood.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;

            providePonderLang(langConsumer);
        });
    }

    private static void providePonderLang(BiConsumer<String, String> consumer) {
        CAFPonders.register();
    }
}
