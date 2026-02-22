package net.egorplaytv.caf.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.caf.block.custom.connect.PalettesVariantEntry;
import net.egorplaytv.caf.data.CAFRegistrate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static net.egorplaytv.caf.block.ShingleBlockPattern.*;

public enum ShingleBlocks {
    SAMAN(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.BAKED_CLAY.getBaseBlock().get());

    ;

    private final Function<CAFRegistrate, NonNullSupplier<Block>> factory;
    private ShingleVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public final ShingleBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    ShingleBlocks(ShingleBlockPattern[] variantTypes,
                      Function<CAFRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public ShingleVariantEntry getVariants() {
        return variants;
    }

    public static void register(CAFRegistrate registration) {
        for (ShingleBlocks shingleBlocks : values()) {
            shingleBlocks.baseBlock = shingleBlocks.factory.apply(registration);
            String id = Lang.asId(shingleBlocks.name());
            shingleBlocks.materialTag =
                    AllTags.optionalTag(ForgeRegistries.ITEMS, CreateAndFood.asResource("shingles/" + id));
            shingleBlocks.variants = new ShingleVariantEntry(id, shingleBlocks);
        }
    }
}
