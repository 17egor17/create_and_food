package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.behaviour.DoorMovingInteraction;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlockItem;
import com.simibubi.create.content.decoration.MetalScaffoldingCTBehaviour;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorMovementBehaviour;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.function.Supplier;

import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.TagGen.*;

public class BuilderTransformers {
    public static <B extends CasingBlock> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> casing(
            Supplier<CTSpriteShiftEntry> ct) {
        return b -> b.initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD))
                .transform(axeOrPickaxe())
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .tag(AllTags.AllBlockTags.CASING.tag)
                .item()
                .tag(AllTags.AllItemTags.CASING.tag)
                .build();
    }

    public static <B extends CTFramedWall> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> walkway(
            Supplier<CTSpriteShiftEntry> ct) {
        return b -> b.initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.STONE))
                .transform(pickaxeOnly())
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }

    public static <B extends CTFramedWall> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> framedWall(
            Supplier<CTSpriteShiftEntry> ct) {
        return b -> b.initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD))
                .transform(axeOnly())
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }

    public static <B extends SlidingDoorBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> slidingDoor(String type) {
        return b -> b.initialProperties(Material.NETHER_WOOD) // for villager AI..
                .properties(p -> p.requiresCorrectToolForDrops()
                        .strength(3.0F, 6.0F))
                .blockstate((c, p) -> {
                    ModelFile bottom = AssetLookup.partialBaseModel(c, p, "bottom");
                    ModelFile top = AssetLookup.partialBaseModel(c, p, "top");
                    p.doorBlock(c.get(), bottom, bottom, top, top);
                })
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(pickaxeOnly())
                .onRegister(interactionBehaviour(new DoorMovingInteraction()))
                .onRegister(movementBehaviour(new SlidingDoorMovementBehaviour()))
                .tag(BlockTags.DOORS)
                .tag(BlockTags.WOODEN_DOORS) // for villager AI
                .tag(AllTags.AllBlockTags.NON_DOUBLE_DOOR.tag)
                .loot((lr, block) -> lr.add(block, BlockLoot.createDoorTable(block)))
                .item()
                .tag(ItemTags.DOORS)
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c, p) -> p.blockSprite(c, p.modLoc("item/" + type + "_door")))
                .build();
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> scaffold(String name,
                                                                                         Supplier<DataIngredient> ingredient, MaterialColor color, CTSpriteShiftEntry scaffoldShift,
                                                                                         CTSpriteShiftEntry scaffoldInsideShift, CTSpriteShiftEntry casingShift) {
        return b -> b.initialProperties(() -> Blocks.SCAFFOLDING)
                .properties(p -> p.sound(SoundType.COPPER)
                        .color(color))
                .addLayer(() -> RenderType::cutout)
                .blockstate((c, p) -> p.getVariantBuilder(c.get())
                        .forAllStatesExcept(s -> {
                            String suffix = s.getValue(MetalScaffoldingBlock.BOTTOM) ? "_horizontal" : "";
                            return ConfiguredModel.builder()
                                    .modelFile(p.models()
                                            .withExistingParent(c.getName() + suffix, p.modLoc("block/scaffold/block" + suffix))
                                            .texture("top", p.modLoc("block/funnel/" + name + "_funnel_frame"))
                                            .texture("inside", p.modLoc("block/scaffold/" + name + "_scaffold_inside"))
                                            .texture("side", p.modLoc("block/scaffold/" + name + "_scaffold"))
                                            .texture("casing", p.modLoc("block/" + name + "_casing"))
                                            .texture("particle", p.modLoc("block/scaffold/" + name + "_scaffold")))
                                    .build();
                        }, MetalScaffoldingBlock.WATERLOGGED, MetalScaffoldingBlock.DISTANCE))
                .onRegister(connectedTextures(
                        () -> new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift)))
                .transform(pickaxeOnly())
                .tag(BlockTags.CLIMBABLE)
                .item(MetalScaffoldingBlockItem::new)
                .recipe((c, p) -> p.stonecutting(ingredient.get(), c::get, 2))
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + c.getName())))
                .build();
    }
}
