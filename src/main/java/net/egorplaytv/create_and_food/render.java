package net.egorplaytv.create_and_food;

import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.block.entity.ModWoodTypes;
import net.egorplaytv.create_and_food.block.entity.renderer.*;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.egorplaytv.create_and_food.screen.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class render {
    public render(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PASTRY_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RYE_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP_PANICLES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NIXIE_VASE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NIXIE_VASE_PERLIN_PINK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NIXIE_VASE_BLACK_GALAXY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUEBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_BLUEBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRANBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_CRANBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RASPBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_RASPBERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_BLUE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_GREEN_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_PURPLE_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_RED_GRAPE_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PUMPKIN_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MELON_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_PUMPKIN_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_MELON_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_TRAPDOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WHITE_CHOCOLATE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WHITE_CHOCOLATE_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WHITE_CHOCOLATE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.RED_GRAPE_JUICE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.RED_GRAPE_JUICE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.RED_GRAPE_JUICE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORN_SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORN_SOUL_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FRAMED_CALCITE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWING_BRASS_STEEL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWING_BRASS_COPPER_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_LAMP_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FURNITURE_CUTTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OAK_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPRUCE_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BIRCH_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.JUNGLE_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACACIA_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_OAK_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRIMSON_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WARPED_CUTTING_BOARD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_CUTTING_BOARD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FARMLAND_SUMP_DIRT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FARMLAND_SUMP_RED_SAND.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FARMLAND_SUMP_SAND.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FARMLAND_SUMP_RICH_SOIL.get(), RenderType.cutoutMipped());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.KITCHEN_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.KITCHEN_TABLE_INNER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.KITCHEN_TABLE_OUTER.get(), RenderType.cutout());

        MenuScreens.register(ModMenuTypes.FERMENTATION_BARREL_MENU.get(), FermentationBarrelScreen::new);
        MenuScreens.register(ModMenuTypes.BLASTING_MENU.get(), MarbleBlastFurnaceScreen::new);
        MenuScreens.register(ModMenuTypes.SAMPLE_MENU.get(), SampleOfMetalsScreen::new);

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
