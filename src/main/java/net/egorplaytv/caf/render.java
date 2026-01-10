package net.egorplaytv.caf;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.entity.ModBlockEntities;
import net.egorplaytv.caf.block.entity.ModWoodTypes;
import net.egorplaytv.caf.block.entity.renderer.*;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.screen.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;

public class render {
    public render() {
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.PASTRY_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RYE_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RICE_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RICE_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RICE_CROP_PANICLES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.NIXIE_VASE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.NIXIE_VASE_PERLIN_PINK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.NIXIE_VASE_BLACK_GALAXY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.BLUEBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_BLUEBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.CRANBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_CRANBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RASPBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_RASPBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.BLUE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_BLUE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GREEN_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_GREEN_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.PURPLE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_PURPLE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.RED_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_RED_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.PUMPKIN_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.MELON_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_PUMPKIN_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WILD_MELON_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_TRAPDOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.APPLE_VINEGAR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.APPLE_VINEGAR_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.APPLE_VINEGAR_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.WHITE_CHOCOLATE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.WHITE_CHOCOLATE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.WHITE_CHOCOLATE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.COCOA_OIL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.COCOA_OIL_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.COCOA_OIL_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.RED_GRAPE_JUICE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.RED_GRAPE_JUICE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFFluids.RED_GRAPE_JUICE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.TORN_SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.TORN_SOUL_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FRAMED_CALCITE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_STEEL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_WAXED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.STEEL_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.STEEL_LAMP_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FURNITURE_CUTTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.OAK_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.SPRUCE_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.BIRCH_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.JUNGLE_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ACACIA_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.DARK_OAK_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.CRIMSON_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WARPED_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_CUTTING_BOARD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FARMLAND_SUMP_DIRT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FARMLAND_SUMP_RED_SAND.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FARMLAND_SUMP_SAND.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.FARMLAND_SUMP_RICH_SOIL.get(), RenderType.cutoutMipped());

        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.KITCHEN_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.KITCHEN_TABLE_INNER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.KITCHEN_TABLE_OUTER.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.OAK_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.OAK_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.SPRUCE_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.SPRUCE_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.BIRCH_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.BIRCH_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.JUNGLE_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.JUNGLE_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ACACIA_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ACACIA_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.DARK_OAK_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.DARK_OAK_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.CRIMSON_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.CRIMSON_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WARPED_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.WARPED_TERRACE_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_TERRACE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CAFBlocks.ALMOND_TERRACE_STAIRS.get(), RenderType.cutout());

        MenuScreens.register(CAFMenuTypes.FERMENTATION_BARREL_MENU.get(), FermentationBarrelScreen::new);
        MenuScreens.register(CAFMenuTypes.BLASTING_MENU.get(), MarbleBlastFurnaceScreen::new);
        MenuScreens.register(CAFMenuTypes.SAMPLE_MENU.get(), SampleOfMetalsScreen::new);

        WoodType.register(ModWoodTypes.ALMOND);

        BlockEntityRenderers.register(ModBlockEntities.SCALES_BLOCK_ENTITY.get(), ScalesBlockRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.VASE_BLOCK_ENTITY.get(), VaseBlockRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.OAK_CUTTING_BOARD.get(), OakCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SPRUCE_CUTTING_BOARD.get(), SpruceCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BIRCH_CUTTING_BOARD.get(), BirchCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.JUNGLE_CUTTING_BOARD.get(), JungleCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.ACACIA_CUTTING_BOARD.get(), AcaciaCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.DARK_OAK_CUTTING_BOARD.get(), DarkOakCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.CRIMSON_CUTTING_BOARD.get(), CrimsonCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.WARPED_CUTTING_BOARD.get(), WarpedCuttingBoardRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.ALMOND_CUTTING_BOARD.get(), AlmondCuttingBoardRenderer::new);
    }
}
