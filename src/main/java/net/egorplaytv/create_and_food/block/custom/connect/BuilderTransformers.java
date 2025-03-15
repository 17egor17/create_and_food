package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.behaviour.DoorMovingInteraction;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlockItem;
import com.simibubi.create.content.decoration.MetalScaffoldingCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorMovementBehaviour;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.function.Supplier;

import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.TagGen.*;
import static net.egorplaytv.create_and_food.data.CAFRegistrate.*;

public class BuilderTransformers {
//    public static <B extends CasingBlock> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> casing(
//            Supplier<CTSpriteShiftEntry> ct) {
//        return b -> b.initialProperties(SharedProperties::stone)
//                .properties(p -> p.sound(SoundType.WOOD))
//                .transform(axeOrPickaxe())
//                .blockstate((c, p) -> p.simpleBlock(c.get()))
//                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
//                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
//                .tag(AllTags.AllBlockTags.CASING.tag)
//                .item()
//                .tag(AllTags.AllItemTags.CASING.tag)
//                .build();
//    }

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
                .blockstate((c, p) -> p.getVariantBuilder(c.get())
                        .partialState().setModels(new ConfiguredModel(p.models().cubeAll(c.get().getRegistryName().getPath(),
                                new ResourceLocation(c.get().getRegistryName().getNamespace(), "block/palettes/framed_wall/" + c.get().getRegistryName().getPath())))))
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

    public static <B extends EncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedShaft(String casing,
                                                                                                         Supplier<CTSpriteShiftEntry> casingShift) {
        return builder -> encasedBase(builder, AllBlocks.SHAFT::get)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/encased_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("encased_shaft", "item_" + casing))
                .build();
    }

    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedSteelCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedSteelCogwheelBase(b, casing, casingShift, AllBlocks.COGWHEEL::get, false);
    }

    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedSteelLargeCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedSteelCogwheelBase(b, casing, casingShift, AllBlocks.LARGE_COGWHEEL::get, true)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
    }

    private static <B extends EncasedCogwheelBlock, P> BlockBuilder<B, P> encasedSteelCogwheelBase(BlockBuilder<B, P> b,
                                                                                                   String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
        String encasedSuffix = "_encased_cogwheel_side" + (large ? "_connected" : "");
        String blockFolder = large ? "encased_large_cogwheel" : "encased_cogwheel";
        String wood = casing.equals("brass") ? "dark_oak" : "spruce";
        String gearbox = casing.equals("steel_block") ? "steel_gearbox" : "gearbox";
        String side = casing.equals("steel_block") ? "steel" : "brass";
        return encasedBase(b, drop).addLayer(() -> RenderType::cutoutMipped)
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.getValue(EncasedCogwheelBlock.AXIS)
                                && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                    String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                            + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                    String modelName = c.getName() + suffix;
                    return p.models()
                            .withExistingParent(modelName, p.modLoc("block/" + blockFolder + "/block" + suffix))
                            .texture("casing", CreateAndFood.asResource("block/" + casing))
                            .texture("particle", CreateAndFood.asResource("block/" + casing))
                            .texture("4", CreateAndFood.asResource("block/" + gearbox))
                            .texture("1", CreateAndFood.asResource("block/" + casing))
                            .texture("side", CreateAndFood.asResource("block/" + side + encasedSuffix));
                }, false))
                .item()
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + blockFolder + "/item"))
                        .texture("casing", CreateAndFood.asResource("block/" + casing))
                        .texture("particle", CreateAndFood.asResource("block/" + casing))
                        .texture("1", CreateAndFood.asResource("block/" + casing))
                        .texture("side", CreateAndFood.asResource("block/" + side + encasedSuffix)))
                .build();
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> steelScaffold(String name,
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
                                            .texture("top", p.modLoc("block/scaffold/" + name + "_funnel_frame"))
                                            .texture("inside", p.modLoc("block/scaffold/" + name + "_scaffold_inside"))
                                            .texture("side", p.modLoc("block/scaffold/" + name + "_scaffold"))
                                            .texture("casing", p.modLoc("block/" + name + "_block"))
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

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(BlockBuilder<B, P> b,
                                                                                           Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(BlockStressDefaults.setNoImpact())
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }
}
