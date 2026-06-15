package net.egorplaytv.caf.util;

import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.LangBuilder;
import com.simibubi.create.foundation.utility.LangNumberFormat;
import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class Lang {

    public static LangBuilder number(double d) {
        return builder().text(LangNumberFormat.format(d));
    }


    public static LangBuilder builder() {
        return new LangBuilder(CreateAndFood.MOD_ID);
    }

    public static LangBuilder translate(String langKey, Object... args) {
        return builder().translate(langKey, args);
    }

    public static LangBuilder itemName(ItemStack stack) {
        return builder().add(stack.getHoverName()
                .copy());
    }

    public static LangBuilder text(String text) {
        return builder().text(text);
    }
}
