package net.egorplaytv.caf.block;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.custom.ShingleBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import java.util.function.Function;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class ShingleBlockPattern {

    public static final ShingleBlockPattern
    OAK_SHINGLE = create("oak_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    SPRUCE_SHINGLE = create("spruce_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    BIRCH_SHINGLE = create("birch_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    JUNGLE_SHINGLE = create("jungle_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    ACACIA_SHINGLE = create("acacia_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    DARK_OAK_SHINGLE = create("dark_oak_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    CRIMSON_SHINGLE = create("crimson_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    WARPED_SHINGLE = create("warped_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle"),
    ALMOND_SHINGLE = create("almond_shingle", PatternNameType.SUFFIX).blockStateFactory(p -> p::shingleBlock)
            .block(ShingleBlock::new)
            .textures("shingle_top", "shingle_bottom", "shingle_angle")

    ;

    public static final ShingleBlockPattern[] SHINGLE_RANGE = { OAK_SHINGLE, SPRUCE_SHINGLE, BIRCH_SHINGLE, JUNGLE_SHINGLE, ACACIA_SHINGLE, DARK_OAK_SHINGLE, CRIMSON_SHINGLE, WARPED_SHINGLE, ALMOND_SHINGLE };

    static final String TEXTURE_LOCATION = "block/shingles/%s/%s";

    private PatternNameType nameType;
    private String[] textures;
    private String id;
    private boolean isTranslucent;
    private TagKey<Block>[] blockTags;
    private TagKey<Item>[] itemTags;

    private IPatternBlockStateGenerator blockStateGenerator;
    private NonNullFunction<BlockBehaviour.Properties, ? extends Block> blockFactory;
    private NonNullFunction<NonNullSupplier<Block>, NonNullBiConsumer<DataGenContext<Block, ? extends Block>, RegistrateRecipeProvider>> additionalRecipes;

    @OnlyIn(Dist.CLIENT)
    private RenderType renderType;

    private static ShingleBlockPattern create(String name, PatternNameType nameType) {
        ShingleBlockPattern pattern = new ShingleBlockPattern();
        pattern.id = name;
        pattern.additionalRecipes = $ -> NonNullBiConsumer.noop();
        pattern.nameType = nameType;
        pattern.isTranslucent = false;
        pattern.blockFactory = Block::new;
        pattern.textures = new String[] { name };
        return pattern;
    }

    public IPatternBlockStateGenerator getBlockStateGenerator() {
        return blockStateGenerator;
    }

    public boolean isTranslucent() {
        return isTranslucent;
    }

    public NonNullFunction<BlockBehaviour.Properties, ? extends Block> getBlockFactory() {
        return blockFactory;
    }

    public TagKey<Block>[] getBlockTags() {
        return blockTags;
    }

    public TagKey<Item>[] getItemTags() {
        return itemTags;
    }

    public void addRecipes(NonNullSupplier<Block> baseBlock, DataGenContext<Block, ? extends Block> c,
                           RegistrateRecipeProvider p) {
        additionalRecipes.apply(baseBlock)
                .accept(c, p);
    }

    // Builder

    private ShingleBlockPattern blockStateFactory(IPatternBlockStateGenerator factory) {
        blockStateGenerator = factory;
        return this;
    }

    private ShingleBlockPattern textures(String... textures) {
        this.textures = textures;
        return this;
    }

    private ShingleBlockPattern block(NonNullFunction<BlockBehaviour.Properties, ? extends Block> blockFactory) {
        this.blockFactory = blockFactory;
        return this;
    }

    // Model generators

    public IBlockStateProvider shingleBlock(String variant) {
        ResourceLocation shingle_top = toLocation(variant, textures[0]);
        ResourceLocation shingle_bottom = toLocation(variant, textures[1]);
        ResourceLocation shingle_angle = toLocation(variant, textures[2]);
        String[] split = id.split("_");
        boolean isAlmondPlanks = split[0].equals("almond");
        boolean isDarkOakPlanks = split[0].equals("dark");
        String plank = isDarkOakPlanks ? "dark_oak_planks" : split[0] + "_planks";
        ResourceLocation planks = isAlmondPlanks ? new ResourceLocation(MOD_ID, "block/" + plank)
                : new ResourceLocation("block/" + plank);

        return (ctx, prov) -> prov.getVariantBuilder(ctx.get())
                .forAllStates(state -> {
                    Direction facing = state.getValue(ShingleBlock.FACING);
                    StairsShape shape = state.getValue(ShingleBlock.SHAPE);

                    if (facing == Direction.NORTH && shape == StairsShape.STRAIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_north_straight",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_straight_north"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.SOUTH && shape == StairsShape.STRAIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_south_straight",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_straight_south"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.WEST && shape == StairsShape.STRAIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_west_straight",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_straight_west"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.EAST && shape == StairsShape.STRAIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath(),
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_straight_east"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.NORTH && shape == StairsShape.INNER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_west_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_west"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.SOUTH && shape == StairsShape.INNER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_east_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_east"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.WEST && shape == StairsShape.INNER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_south_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_south"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.EAST && shape == StairsShape.INNER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_north_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_north"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.NORTH && shape == StairsShape.INNER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_north_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_north"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.SOUTH && shape == StairsShape.INNER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_south_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_south"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.WEST && shape == StairsShape.INNER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_west_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_west"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.EAST && shape == StairsShape.INNER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_east_inner",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_inner_east"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.NORTH && shape == StairsShape.OUTER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_west_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_west"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.SOUTH && shape == StairsShape.OUTER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_east_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_east"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.WEST && shape == StairsShape.OUTER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_south_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_south"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.EAST && shape == StairsShape.OUTER_LEFT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_north_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_north"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.NORTH && shape == StairsShape.OUTER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_north_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_north"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.SOUTH && shape == StairsShape.OUTER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_south_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_south"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else if (facing == Direction.WEST && shape == StairsShape.OUTER_RIGHT) {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_west_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_west"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    } else {
                        return ConfiguredModel.builder()
                                .modelFile(prov.models().withExistingParent(ctx.get().getRegistryName().getPath() + "_east_outer",
                                                new ResourceLocation(MOD_ID, "custom/block/shingles/shingle_outer_east"))
                                        .texture("shingle_top", shingle_top)
                                        .texture("shingle_bottom", shingle_bottom)
                                        .texture("shingle_angle", shingle_angle)
                                        .texture("base", planks))
                                .build();
                    }
                });
    }

    // Utility

    protected String createName(String variant) {
        if (nameType == PatternNameType.WRAP) {
            String[] split = id.split("_");
            if (split.length == 2) {
                String formatString = "%s_%s_%s";
                return String.format(formatString, split[0], variant, split[1]);
            }
        }
        String formatString = "%s_%s";
        return nameType == PatternNameType.SUFFIX ? String.format(formatString, variant, id) : String.format(formatString, id, variant);
    }

    protected static ResourceLocation toLocation(String variant, String texture) {
        return CreateAndFood.asResource(
                String.format(TEXTURE_LOCATION, texture, variant + "_" + texture));
    }

    @FunctionalInterface
    static interface IPatternBlockStateGenerator
            extends Function<ShingleBlockPattern, Function<String, IBlockStateProvider>> {
    }

    @FunctionalInterface
    static interface IBlockStateProvider
            extends NonNullBiConsumer<DataGenContext<Block, ? extends Block>, RegistrateBlockstateProvider> {
    }

    enum PatternNameType {
        PREFIX, SUFFIX, WRAP
    }
}
