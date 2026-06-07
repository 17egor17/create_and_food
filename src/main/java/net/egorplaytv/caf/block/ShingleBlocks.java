package net.egorplaytv.caf.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.caf.data.CAFRegistrate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static net.egorplaytv.caf.block.ShingleBlockPattern.*;

public enum ShingleBlocks {
    PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.PACKED_MUD.getBaseBlock().get()),
    BLACK_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.BLACK_PACKED_MUD.getBaseBlock().get()),
    BLUE_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.BLUE_PACKED_MUD.getBaseBlock().get()),
    CYAN_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.CYAN_PACKED_MUD.getBaseBlock().get()),
    GRAY_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.GRAY_PACKED_MUD.getBaseBlock().get()),
    GREEN_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.GREEN_PACKED_MUD.getBaseBlock().get()),
    ORANGE_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.ORANGE_PACKED_MUD.getBaseBlock().get()),
    RED_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.RED_PACKED_MUD.getBaseBlock().get()),
    WHITE_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.WHITE_PACKED_MUD.getBaseBlock().get()),
    YELLOW_PACKED_MUD(SHINGLE_RANGE, r -> () -> PaletteStoneTypes.YELLOW_PACKED_MUD.getBaseBlock().get())
    ;

    private final Function<CAFRegistrate, NonNullSupplier<Block>> factory;
    private ShingleVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public final ShingleBlockPattern[] variantTypes;
    public TagKey<Block> materialBlockTag;
    public TagKey<Item> materialTag;

    ShingleBlocks(ShingleBlockPattern[] variantTypes,
                      Function<CAFRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public ShingleVariantEntry getVariants() {
        return variants;
    }

    public static void register(CAFRegistrate registration) {
        for (ShingleBlocks shingleBlocks : values()) {
            shingleBlocks.baseBlock = shingleBlocks.factory.apply(registration);
            String id = Lang.asId(shingleBlocks.name());
            shingleBlocks.materialBlockTag =
                    AllTags.optionalTag(ForgeRegistries.BLOCKS, CreateAndFood.asResource("shingles/" + id));
            shingleBlocks.materialTag =
                    AllTags.optionalTag(ForgeRegistries.ITEMS, CreateAndFood.asResource("shingles/" + id));
            shingleBlocks.variants = new ShingleVariantEntry(id, shingleBlocks);
        }
    }
}
