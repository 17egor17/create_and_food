package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassBlock;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.WindowGen;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.custom.ConnectedWall;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

import static com.simibubi.create.Create.REGISTRATE;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;

public class CTBlocks {

    private static BlockBehaviour.Properties glassProperties(BlockBehaviour.Properties p) {
        return p.isValidSpawn(CTBlocks::never)
                .isRedstoneConductor(CTBlocks::never)
                .isSuffocating(CTBlocks::never)
                .isViewBlocking(CTBlocks::never);
    }
    private static boolean never(BlockState p_235436_0_, BlockGetter p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }
    private static Boolean never(BlockState p_235427_0_, BlockGetter p_235427_1_, BlockPos p_235427_2_,
                                 EntityType<?> p_235427_3_) {
        return false;
    }


    public static BlockEntry<ConnectedWall> goldenWall(String name,
                                                              Supplier<ConnectedTextureBehaviour> behaviour) {
        return CreateAndFood.REGISTRATE.block(name, ConnectedWall::new)
                .onRegister(connectedTextures(behaviour))
                .initialProperties(() -> Blocks.GOLD_BLOCK)
                .properties(p -> p.sound(SoundType.AMETHYST))
                .loot((t, g) -> t.dropWhenSilkTouch(g))
                .blockstate((c, p) ->
                        p.getVariantBuilder(c.getEntry()).partialState().setModels(ConfiguredModel.builder()
                                        .modelFile(p.models().withExistingParent(name, "block/cube_column")
                                                .texture("side", palettesDir() + c.getName())
                                                .texture("end", palettesDir() + c.getName() + "_top"))
                                .build()))
                .simpleItem()
                .register();
    }

    public static BlockEntry<ConnectedGlassBlock> alloySoulsGlass(String name,
                                                              Supplier<ConnectedTextureBehaviour> behaviour) {
        return CreateAndFood.REGISTRATE.block(name, ConnectedGlassBlock::new)
                .onRegister(connectedTextures(behaviour))
                .addLayer(() -> RenderType::translucent)
                .initialProperties(() -> Blocks.GLASS)
                .properties(CTBlocks::glassProperties)
                .loot((t, g) -> t.dropWhenSilkTouch(g))
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(Tags.Items.GLASS_COLORLESS), c::get))
                .blockstate((c, p) -> BlockStateGen.cubeAll(c, p, "palettes/", "alloy_souls_glass"))
                .tag(Tags.Blocks.GLASS_COLORLESS, BlockTags.IMPERMEABLE)
                .item()
                .tag(Tags.Items.GLASS_COLORLESS)
                .model((c, p) -> p.cubeAll(c.getName(), p.modLoc(palettesDir() + c.getName())))
                .build()
                .register();
    }
    public static BlockEntry<ConnectedGlassBlock> almondGlass(String name,
                                                                  Supplier<ConnectedTextureBehaviour> behaviour) {
        return CreateAndFood.REGISTRATE.block(name, ConnectedGlassBlock::new)
                .onRegister(connectedTextures(behaviour))
                .addLayer(() -> RenderType::cutout)
                .initialProperties(() -> Blocks.GLASS)
                .properties(CTBlocks::glassProperties)
                .loot((t, g) -> t.dropWhenSilkTouch(g))
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(Tags.Items.GLASS_COLORLESS), c::get))
                .blockstate((c, p) -> BlockStateGen.cubeAll(c, p, "palettes/", "almond_glass"))
                .tag(Tags.Blocks.GLASS_COLORLESS, BlockTags.IMPERMEABLE)
                .item()
                .tag(Tags.Items.GLASS_COLORLESS)
                .model((c, p) -> p.cubeAll(c.getName(), p.modLoc(palettesDir() + c.getName())))
                .build()
                .register();
    }

    private static String palettesDir() {
        return "block/palettes/";
    }
}
