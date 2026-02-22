package net.egorplaytv.caf.block;

import com.google.common.collect.ImmutableList;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.caf.data.CAFRegistrate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class ShingleVariantEntry {

    public final ImmutableList<BlockEntry<? extends Block>> registeredBlocks;
    public final ImmutableList<BlockEntry<? extends Block>> registeredPartials;

    public ShingleVariantEntry(String name, ShingleBlocks shingleBlocks, PaletteStoneTypes stoneTypes) {
        ImmutableList.Builder<BlockEntry<? extends Block>> registeredBlocks = ImmutableList.builder();
        ImmutableList.Builder<BlockEntry<? extends Block>> registeredPartials = ImmutableList.builder();
        NonNullSupplier<Block> baseBlock = shingleBlocks.baseBlock;

        for (ShingleBlockPattern pattern : shingleBlocks.variantTypes) {
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

            itemBuilder.tag(shingleBlocks.materialTag);

            if (pattern.isTranslucent())
                builder.addLayer(() -> RenderType::translucent);

            builder.recipe((c, p) -> {
                createRecipe(stoneTypes, c, p, pattern);
                pattern.addRecipes(baseBlock, c, p);
            });

            itemBuilder.register();
            BlockEntry<? extends Block> block = builder.register();
            registeredBlocks.add(block);
        }

        CreateAndFood.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, p -> p.tag(shingleBlocks.materialTag));

        this.registeredBlocks = registeredBlocks.build();
        this.registeredPartials = registeredPartials.build();
    }

    private <T extends ItemLike & IForgeRegistryEntry<?>> void createRecipe(PaletteStoneTypes stoneTypes, Supplier<? extends T> c, RegistrateRecipeProvider p,
                                                                            ShingleBlockPattern[] variantTypes) {
        for (ShingleBlockPattern pattern : variantTypes) {
            createRecipe(stoneTypes, c, p, pattern);
        }
    }


    private <T extends ItemLike & IForgeRegistryEntry<?>> void createRecipe(PaletteStoneTypes stoneTypes, Supplier<? extends T> c, RegistrateRecipeProvider p,
                              ShingleBlockPattern pattern) {
        DataIngredient ingredient = DataIngredient.tag(stoneTypes.materialTag);
        Item ingredient1;
        if (pattern == ShingleBlockPattern.OAK_SHINGLE) {
            ingredient1 = Items.OAK_PLANKS;
        } else if (pattern == ShingleBlockPattern.SPRUCE_SHINGLE) {
            ingredient1 = Items.SPRUCE_PLANKS;
        } else if (pattern == ShingleBlockPattern.BIRCH_SHINGLE) {
            ingredient1 = Items.BIRCH_PLANKS;
        } else if (pattern == ShingleBlockPattern.JUNGLE_SHINGLE) {
            ingredient1 = Items.JUNGLE_PLANKS;
        } else if (pattern == ShingleBlockPattern.ACACIA_SHINGLE) {
            ingredient1 = Items.ACACIA_PLANKS;
        } else if (pattern == ShingleBlockPattern.DARK_OAK_SHINGLE) {
            ingredient1 = Items.DARK_OAK_PLANKS;
        } else if (pattern == ShingleBlockPattern.CRIMSON_SHINGLE) {
            ingredient1 = Items.CRIMSON_PLANKS;
        } else if (pattern == ShingleBlockPattern.WARPED_SHINGLE) {
            ingredient1 = Items.WARPED_PLANKS;
        } else {
            ingredient1 = CAFBlocks.ALMOND_PLANKS.get().asItem();
        }

        ShapedRecipeBuilder.shaped(c.get(), 3)
                .pattern("0  ").pattern("10 ").pattern("110")
                .define('0', stoneTypes.materialTag)
                .define('1', ingredient1)
                .unlockedBy("has_" + p.safeName(ingredient), ingredient.getCritereon(p))
                .unlockedBy("has_" + ingredient1.getRegistryName().getPath(),
                        inventoryTrigger(ItemPredicate.Builder.item().of(ingredient1).build()))
                .save(p, p.safeId(c.get()));
    }
}
