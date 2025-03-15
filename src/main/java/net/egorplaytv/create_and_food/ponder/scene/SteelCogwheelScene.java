package net.egorplaytv.create_and_food.ponder.scene;

import com.simibubi.create.foundation.ponder.*;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.CAFCogWheelBlock;
import net.egorplaytv.create_and_food.block.custom.CAFShaftBlock;
import net.egorplaytv.create_and_food.block.custom.connect.EncasedCogwheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SteelCogwheelScene {

    public static void encasing(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("steel_cogwheel_casing", "Encasing Steel Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 6, 0, 5), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.position(5, 0, 3), f -> -16F);
        scene.world.modifyKineticSpeed(util.select.position(4, 1, 3), f -> 16F);
        scene.world.modifyKineticSpeed(util.select.fromTo(3, 1, 2, 3, 2, 2), f -> -32F);
        scene.world.modifyKineticSpeed(util.select.position(2, 1, 2), f -> 32F);
        scene.world.modifyKineticSpeed(util.select.position(1, 1, 3), f -> -16F);

        Selection large1 = util.select.position(4, 1, 3);
        Selection small1 = util.select.fromTo(3, 1, 2, 3, 2, 2);
        Selection small2 = util.select.position(2, 1, 2);
        Selection large2 = util.select.fromTo(1, 1, 3, 1, 1, 4);
        Selection shaft2 = util.select.position(2, 2, 2);

        scene.world.setKineticSpeed(shaft2, 0);
        scene.idle(10);

        scene.world.showSection(large1, Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(small1, Direction.DOWN);
        scene.world.showSection(small2, Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(large2, Direction.EAST);
        scene.idle(20);

        BlockEntry<EncasedCogwheelBlock> steelEncased = ModBlocks.STEEL_ENCASED_STEEL_COGWHEEL;
        ItemStack steelCasingItem = ModBlocks.STEEL_BLOCK.asStack();

        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(3, 0, 2), Pointing.UP).rightClick()
                .withItem(steelCasingItem), 100);
        scene.idle(7);
        scene.world.setBlocks(util.select.position(3, 1, 2), steelEncased.getDefaultState()
                .setValue(EncasedCogwheelBlock.AXIS, Direction.Axis.Y)
                .setValue(EncasedCogwheelBlock.TOP_SHAFT, true), true);
        scene.world.setKineticSpeed(util.select.position(3, 1, 2), -32);

        scene.idle(10);
        scene.overlay.showText(70)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Steel Block can be used to decorate Cogwheels")
                .pointAt(util.vector.topOf(1, 1, 3));
        scene.idle(80);

        ElementLink<WorldSectionElement> shaftLink = scene.world.showIndependentSection(shaft2, Direction.DOWN);
        scene.idle(15);
        scene.overlay.showText(90)
                .placeNearTarget()
                .colored(PonderPalette.RED)
                .attachKeyFrame()
                .text("Components added after encasing will not connect to the shaft outputs")
                .pointAt(util.vector.centerOf(2, 2, 2));
        scene.idle(90);

        scene.world.moveSection(shaftLink, new Vec3(0, .5f, 0), 10);
        scene.idle(10);

        scene.addKeyframe();
        Vec3 wrenchHere = util.vector.topOf(2, 1, 2)
                .add(.25, 0, -.25);
        scene.overlay.showControls(new InputWindowElement(wrenchHere, Pointing.RIGHT).rightClick()
                .withWrench(), 25);
        scene.idle(7);
        scene.world.cycleBlockProperty(util.grid.at(2, 1, 2), EncasedCogwheelBlock.TOP_SHAFT);
        scene.idle(15);
        scene.world.moveSection(shaftLink, new Vec3(0, -.5f, 0), 10);
        scene.idle(10);
        scene.world.setKineticSpeed(shaft2, 32);
        scene.effects.rotationDirectionIndicator(util.grid.at(2, 2, 2));
        scene.idle(20);

        scene.overlay.showText(90)
                .placeNearTarget()
                .colored(PonderPalette.GREEN)
                .text("The Wrench can be used to toggle connections")
                .pointAt(wrenchHere.add(-.5, 0, .5));
        scene.idle(40);

        scene.overlay.showControls(new InputWindowElement(wrenchHere, Pointing.RIGHT).rightClick()
                .withWrench(), 25);
        scene.idle(7);
        scene.world.cycleBlockProperty(util.grid.at(2, 1, 2), EncasedCogwheelBlock.TOP_SHAFT);
        scene.world.setKineticSpeed(shaft2, 0);
    }

    public static void large(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("large_steel_cogwheel", "Relaying rotational force using Large Steel Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.fromTo(2, 0, 2, 2, 1, 2), f -> 16F);
        scene.world.modifyKineticSpeed(util.select.fromTo(2, 2, 4, 2, 2, 0), f -> 16F);
        scene.world.modifyKineticSpeed(util.select.fromTo(4, 2, 2, 0, 2, 2), f -> 16F);

        BlockPos shaft = util.grid.at(2, 2, 2);
        Block steel_shaft = ModBlocks.STEEL_SHAFT.get();

        scene.idle(5);
        scene.world.showSection(util.select.position(2, 1, 2), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(2, 2, 3), Direction.NORTH);

        for (int i = 2; i >= 0; i--) {
            scene.idle(5);
            if (i == 2)
                scene.world.showSection(util.select.position(2, 2, 4), Direction.DOWN);
            scene.world.showSection(util.select.position(2, 2, i), Direction.DOWN);
        }

        scene.overlay.showText(70)
                .text("Large cogwheels can connect to each other at right angles")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(2, 1, 3));
        scene.idle(70);
        scene.world.hideSection(util.select.fromTo(2, 2, 4, 2, 2, 0), Direction.SOUTH);

        scene.idle(15);
        scene.world.setBlock(shaft, steel_shaft.defaultBlockState().setValue(CAFShaftBlock.AXIS, Direction.Axis.X), false);
        scene.world.showSection(util.select.position(3, 2, 2), Direction.NORTH);

        for (int i = 2; i >= 0; i--) {
            scene.idle(5);
            if (i == 2)
                scene.world.showSection(util.select.position(4, 2, 2), Direction.DOWN);
            scene.world.showSection(util.select.position(i, 2, 2), Direction.DOWN);
        }

        scene.idle(5);
        scene.overlay.showText(90)
                .text("It will help relaying conveyed speed to other axes of rotation")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(util.grid.at(0, 2, 2), Direction.WEST));
        scene.effects.rotationSpeedIndicator(util.grid.at(2, 1, 2));
        scene.effects.rotationSpeedIndicator(util.grid.at(3, 2, 2));
        scene.idle(60);
    }

    public static void small(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("steel_cogwheel", "Relaying rotational force using Steel Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        BlockPos gauge = util.grid.at(4, 1, 1);
        Selection gaugeSelect = util.select.position(gauge);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(gaugeSelect, Direction.UP);
        scene.world.setKineticSpeed(gaugeSelect, 0);
        scene.world.modifyKineticSpeed(util.select.position(2, 0, 5), f -> 32F);
        scene.world.modifyKineticSpeed(util.select.fromTo(1, 1, 5, 1, 1, 1), f -> -64F);
        scene.world.modifyKineticSpeed(util.select.fromTo(2, 1, 2, 2, 1, 1), f -> 64F);
        scene.world.modifyKineticSpeed(util.select.position(0, 1, 2), f -> 64F);
        scene.world.modifyKineticSpeed(util.select.position(3, 1, 2), f -> -64F);
        scene.world.modifyKineticSpeed(util.select.position(4, 1, 2), f -> 64F);

        scene.idle(5);
        scene.world.showSection(util.select.fromTo(1, 1, 3, 1, 1, 5), Direction.DOWN);
        scene.idle(10);

        for (int i = 1; i <= 4; i++) {
            scene.idle(5);
            if (i == 2)
                scene.world.showSection(util.select.position(0, 1, 2), Direction.DOWN);
            scene.world.showSection(util.select.position(i, 1, 2), Direction.DOWN);
        }

        scene.world.setKineticSpeed(gaugeSelect, 64);
        scene.effects.indicateSuccess(gauge);
        scene.idle(10);
        scene.overlay.showText(60)
                .text("Steel Cogwheels will relay rotation to other adjacent cogwheels")
                .pointAt(util.vector.blockSurface(util.grid.at(0, 1, 2), Direction.EAST));

        scene.idle(60);
        scene.world.showSection(util.select.fromTo(1, 1, 1, 2, 1, 1), Direction.SOUTH);
        scene.idle(10);
        scene.effects.rotationDirectionIndicator(util.grid.at(1, 1, 1));
        scene.effects.rotationDirectionIndicator(util.grid.at(2, 1, 1));
        scene.idle(20);
        scene.overlay.showText(100)
                .text("Neighbouring shafts connected like this will rotate in opposite directions")
                .placeNearTarget()
                .attachKeyFrame()
                .pointAt(util.vector.blockSurface(util.grid.at(1, 1, 2), Direction.NORTH));
        scene.idle(70);
    }

    public static void speedup(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("cog_speedup", "Gearshifting with Cogs");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(5, 1, 2, 4, 1, 2), Direction.DOWN);
        scene.world.modifyKineticSpeed(util.select.position(5, 0, 3), f -> -16F);
        scene.world.modifyKineticSpeed(util.select.fromTo(5, 1, 2, 0, 1, 2), f -> 32F);
        scene.idle(10);

        BlockPos lowerCog = util.grid.at(3, 1, 2);
        BlockPos upperCog = util.grid.at(3, 2, 3);
        BlockState largeCogState = ModBlocks.LARGE_STEEL_COGWHEEL.getDefaultState()
                .setValue(CAFCogWheelBlock.AXIS, Direction.Axis.X);
        BlockState smallCogState = ModBlocks.STEEL_COGWHEEL.getDefaultState()
                .setValue(CAFCogWheelBlock.AXIS, Direction.Axis.X);

        scene.world.setBlock(lowerCog, largeCogState, false);
        scene.world.setBlock(upperCog, smallCogState, false);
        BlockPos upperShaftEnd = upperCog.west(3);
        BlockPos lowerShaftEnd = lowerCog.west(3);

        scene.world.setKineticSpeed(util.select.fromTo(upperCog, upperShaftEnd), -64);
        scene.world.showSection(util.select.fromTo(lowerCog, upperCog), Direction.EAST);
        scene.overlay.showText(60)
                .text("Large and Small cogs can be connected diagonally")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(upperCog, Direction.WEST));
        scene.idle(80);

        Selection gaugesSelect = util.select.fromTo(0, 1, 2, 2, 2, 3);
        scene.world.showSection(gaugesSelect, Direction.DOWN);
        scene.overlay.showText(80)
                .text("Shifting from large to small cogs, the conveyed speed will be doubled")
                .colored(PonderPalette.GREEN)
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(util.grid.at(1, 2, 3), Direction.NORTH));
        scene.idle(30);
        scene.effects.rotationSpeedIndicator(upperCog);
        scene.idle(60);

        scene.overlay.showText(30)
                .sharedText("rpm32")
                .colored(PonderPalette.FAST)
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(upperShaftEnd, Direction.WEST));
        scene.idle(5);
        scene.overlay.showText(30)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(lowerShaftEnd, Direction.WEST));
        scene.idle(45);

        scene.world.setKineticSpeed(util.select.fromTo(lowerCog, upperShaftEnd), 0);
        ElementLink<WorldSectionElement> cogs =
                scene.world.makeSectionIndependent(util.select.fromTo(lowerCog, upperCog));
        scene.world.moveSection(cogs, util.vector.of(0, 1, 0), 5);
        scene.idle(5);
        scene.world.rotateSection(cogs, 180, 0, 0, 10);
        scene.idle(10);
        scene.world.setBlock(lowerCog, smallCogState, false);
        scene.world.setBlock(upperCog, largeCogState, false);
        scene.world.rotateSection(cogs, 180, 0, 0, 0);
        scene.world.moveSection(cogs, util.vector.of(0, -1, 0), 5);
        scene.idle(5);

        scene.world.setKineticSpeed(util.select.fromTo(lowerCog, lowerShaftEnd), 32);
        scene.world.setKineticSpeed(util.select.fromTo(upperCog, upperShaftEnd), -16);

        scene.overlay.showText(80)
                .text("Shifting the opposite way, the conveyed speed will be halved")
                .colored(PonderPalette.RED)
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(util.grid.at(1, 2, 3), Direction.NORTH));
        scene.idle(10);
        scene.effects.rotationSpeedIndicator(upperCog);
        scene.idle(80);

        scene.overlay.showText(60)
                .sharedText("rpm8")
                .colored(PonderPalette.SLOW)
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(upperShaftEnd, Direction.WEST));
        scene.idle(5);
        scene.overlay.showText(60)
                .sharedText("rpm16")
                .colored(PonderPalette.MEDIUM)
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(lowerShaftEnd, Direction.WEST));
        scene.idle(40);
    }
}
