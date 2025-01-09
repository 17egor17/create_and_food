package net.egorplaytv.create_and_food;


import com.tterrag.registrate.providers.ProviderType;
import net.egorplaytv.create_and_food.block.entity.custom.MarbleBlastFurnaceBlockEntity;
import net.egorplaytv.create_and_food.ponder.CAFPonders;
import net.egorplaytv.create_and_food.recipe.recipe.Recipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.function.BiConsumer;

public class CreateAndFoodDataGen {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        addExtraRegistrateData();
        generator.addProvider(new Recipes(generator));
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
