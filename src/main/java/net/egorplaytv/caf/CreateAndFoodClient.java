package net.egorplaytv.caf;

import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.custom.connect.ModPartialModels;
import net.egorplaytv.caf.color.CAFFoliageColor;
import net.egorplaytv.caf.datagen.custom.ModItemModelsProperties;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class CreateAndFoodClient {
    public CreateAndFoodClient() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModPartialModels.init();
        eventBus.addListener(this::modelRegistryEvent);
        eventBus.addListener(this::registerEntityRenderers);
        eventBus.addListener(this::registerCAFBlockColors);
        eventBus.addListener(this::registerCAFItemColors);
        eventBus.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    public void modelRegistryEvent(ModelRegistryEvent event) {
        ModItemModelsProperties.init();
    }

    @OnlyIn(Dist.CLIENT)
    private void registerCAFBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((bs, world, pos, index) -> {
                    return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : CAFFoliageColor.getAlmondColor();
                    },
                CAFBlocks.ALMOND_LEAVES.get()
        );

        event.getBlockColors().register((bs, world, pos, index) -> {
                    return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.get(0.5D, 1.0D);
                },
                CAFBlocks.BLUEBERRY_BUSH.get(),
                CAFBlocks.CRANBERRY_BUSH.get(),
                CAFBlocks.MELON_BUSH.get(),
                CAFBlocks.PUMPKIN_BUSH.get(),
                CAFBlocks.RASPBERRY_BUSH.get(),
                CAFBlocks.BLUE_GRAPE_BUSH.get(),
                CAFBlocks.RED_GRAPE_BUSH.get(),
                CAFBlocks.GREEN_GRAPE_BUSH.get(),
                CAFBlocks.PURPLE_GRAPE_BUSH.get(),
                CAFBlocks.WILD_BLUEBERRY_BUSH.get(),
                CAFBlocks.WILD_CRANBERRY_BUSH.get(),
                CAFBlocks.WILD_MELON_BUSH.get(),
                CAFBlocks.WILD_PUMPKIN_BUSH.get(),
                CAFBlocks.WILD_RASPBERRY_BUSH.get(),
                CAFBlocks.WILD_BLUE_GRAPE_BUSH.get(),
                CAFBlocks.WILD_RED_GRAPE_BUSH.get(),
                CAFBlocks.WILD_GREEN_GRAPE_BUSH.get(),
                CAFBlocks.WILD_PURPLE_GRAPE_BUSH.get()
        );
    }

    @OnlyIn(Dist.CLIENT)
    private void registerCAFItemColors(ColorHandlerEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();
        event.getItemColors().register((stack, index) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
                    return blockColors.getColor(blockState, (BlockAndTintGetter)null, (BlockPos)null, index);
                },
                CAFBlocks.ALMOND_LEAVES.get()
        );
    }

    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        InitEntityRendering.init(event::registerEntityRenderer);
    }
}
