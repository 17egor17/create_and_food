package net.egorplaytv.caf.block;

import com.google.common.collect.ImmutableList;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.data.CAFRegistrate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class ShingleVariantEntry {

    public final ImmutableList<BlockEntry<? extends Block>> registeredBlocks;
    public final ImmutableList<BlockEntry<? extends Block>> registeredPartials;

    public ShingleVariantEntry(String name, ShingleBlocks paletteStoneVariants) {
        ImmutableList.Builder<BlockEntry<? extends Block>> registeredBlocks = ImmutableList.builder();
        ImmutableList.Builder<BlockEntry<? extends Block>> registeredPartials = ImmutableList.builder();
        NonNullSupplier<Block> baseBlock = paletteStoneVariants.baseBlock;

        for (ShingleBlockPattern pattern : paletteStoneVariants.variantTypes) {
            BlockBuilder<? extends Block, CAFRegistrate> builder =
                    CreateAndFood.REGISTRATE.block(pattern.createName(name), pattern.getBlockFactory())
                            .initialProperties(baseBlock)
                            .transform(pickaxeOnly())
                            .blockstate(pattern.getBlockStateGenerator()
                                    .apply(pattern)
                                    .apply(name)::accept);

            ItemBuilder<BlockItem, ? extends BlockBuilder<? extends Block, CAFRegistrate>> itemBuilder =
                    builder.item();

            TagKey<Block>[] blockTags = pattern.getBlockTags();
            if (blockTags != null)
                builder.tag(blockTags);
            TagKey<Item>[] itemTags = pattern.getItemTags();
            if (itemTags != null)
                itemBuilder.tag(itemTags);

            itemBuilder.tag(paletteStoneVariants.materialTag);

            if (pattern.isTranslucent())
                builder.addLayer(() -> RenderType::translucent);

            builder.recipe((c, p) -> {
                p.stonecutting(DataIngredient.tag(paletteStoneVariants.materialTag), c);
                pattern.addRecipes(baseBlock, c, p);
            });

            itemBuilder.register();
            BlockEntry<? extends Block> block = builder.register();
            registeredBlocks.add(block);
        }

        CreateAndFood.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, p -> p.tag(paletteStoneVariants.materialTag));

        this.registeredBlocks = registeredBlocks.build();
        this.registeredPartials = registeredPartials.build();
    }

}
