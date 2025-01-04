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
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class render {
    public render(FMLCommonSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PASTRY_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RYE_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALMOND_TRAPDOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.APPLE_VINEGAR_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.COCOA_OIL_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORN_SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORN_SOUL_CHAIN.get(), RenderType.cutout());
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
