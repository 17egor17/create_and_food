package net.egorplaytv.create_and_food.data;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class CAFRegistrate extends CreateRegistrate {

    public static String autoLang(String id) {
        StringBuilder builder = new StringBuilder();
        boolean b = true;
        for (char c: id.toCharArray()) {
            if(c == '_') {
                builder.append(' ');
                b = true;
            } else {
                builder.append(b ? String.valueOf(c).toUpperCase() : c);
                b = false;
            }
        }
        return builder.toString();
    }

    protected CAFRegistrate() {
        super(CreateAndFood.MOD_ID);
    }

    public static CAFRegistrate create() {
        return new CAFRegistrate();
    }

    public static Block getBlock(String name) {
        return CreateAndFood.REGISTRATE.get(name, ForgeRegistries.BLOCKS.getRegistryKey()).get();
    }
    public static Item getItem(String name) {
        return CreateAndFood.REGISTRATE.get(name, ForgeRegistries.ITEMS.getRegistryKey()).get();
    }
    public static Item getBucket(String name) {
        return CreateAndFood.REGISTRATE.get(name + "_bucket", ForgeRegistries.ITEMS.getRegistryKey()).get();
    }
}
