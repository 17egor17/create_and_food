package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.sound.ModSounds;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static net.egorplaytv.create_and_food.block.custom.connect.PaletteBlockPattern.*;

public enum PaletteStoneTypes {

//    MARBLE(MARBLE_RANGE, r -> () -> ModBlocks.MARBLE.get()),
//    MARBLE_BLACK_GALAXY(MARBLE_RANGE, r -> () -> ModBlocks.MARBLE_BLACK_GALAXY.get()),
//    MARBLE_PERLIN_PINK(MARBLE_RANGE, r -> () -> ModBlocks.MARBLE_PERLIN_PINK.get()),

    BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_BROWN))
            .register()),
    BLACK_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("black_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_BLACK))
            .register()),
    BLUE_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("blue_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_BLUE))
            .register()),
    CYAN_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("cyan_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_CYAN))
            .register()),
    GRAY_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("gray_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_GRAY))
            .register()),
    GREEN_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("green_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_GREEN))
            .register()),
    ORANGE_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("orange_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_ORANGE))
            .register()),
    RED_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("red_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_RED))
            .register()),
    WHITE_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("white_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_LIGHT_GRAY))
            .register()),
    YELLOW_BAKED_CLAY(BAKED_CLAY_RANGE, r -> r.paletteStoneBlock("yellow_baked_clay", () -> Blocks.TERRACOTTA, false, false)
            .properties(p -> p.destroyTime(1.25f).sound(ModSounds.BAKED_CLAY)
                    .color(MaterialColor.COLOR_YELLOW))
            .register())
    ;
    private final Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private PalettesVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public final PaletteBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    PaletteStoneTypes(PaletteBlockPattern[] variantTypes,
                              Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public PalettesVariantEntry getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registration) {
        for (PaletteStoneTypes paletteStoneVariants : values()) {
            paletteStoneVariants.baseBlock = paletteStoneVariants.factory.apply(registration);
            String id = Lang.asId(paletteStoneVariants.name());
            paletteStoneVariants.materialTag =
                    AllTags.optionalTag(ForgeRegistries.ITEMS, CreateAndFood.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new PalettesVariantEntry(id, paletteStoneVariants);
        }
    }

}

