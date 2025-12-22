package net.egorplaytv.create_and_food.ponder.scene;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.block.custom.connect.EncasedShaftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;

public class SteelShaftScene {

    public static void relay(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("steel_shaft", "Relaying rotational force using Steel Shafts");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 6, 0, 5), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.position(5, 0, 1), f -> -32F);
        scene.world.modifyKineticSpeed(util.select.fromTo(5, 1, 2, 1, 1, 2), f -> 64F);

        BlockPos gaugePos = util.grid.at(0, 1, 2);
        Selection gauge = util.select.position(gaugePos);

        scene.world.showSection(gauge, Direction.UP);
        scene.world.setKineticSpeed(gauge, 0);

        scene.idle(5);
        scene.world.showSection(util.select.position(5, 1, 2), Direction.DOWN);
        scene.idle(10);

        for (int i = 4; i >= 1; i--) {
            if (i == 2)
                scene.rotateCameraY(70);
            scene.idle(5);
            scene.world.showSection(util.select.position(i, 1, 2), Direction.DOWN);
        }

        scene.world.setKineticSpeed(gauge, 64);
        scene.effects.indicateSuccess(gaugePos);
        scene.idle(10);
        scene.overlay.showText(1000)
                .placeNearTarget()
                .text("Shafts will relay rotation in a straight line.")
                .pointAt(util.vector.of(3, 1.5, 2.5));

        scene.idle(20);
        scene.markAsFinished();
    }

    public static void encasing(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("steel_shaft_casing", "Encasing Steel Shafts");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 6, 0, 5), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.position(5, 0, 3), f -> -32F);
        scene.world.modifyKineticSpeed(util.select.fromTo(5, 1, 2, 0, 1, 2), f -> 64F);


        Selection shaft = util.select.cuboid(new BlockPos(0, 1, 2), new Vec3i(5, 0, 2));
        Selection steel = util.select.position(3, 1, 2);
        Selection secure_block = util.select.position(2, 1, 2);
        Selection andesite = util.select.position(1, 1, 2);
        Selection brass = util.select.position(0, 1, 2);

        scene.world.showSection(shaft, Direction.DOWN);
        scene.idle(20);

        BlockEntry<EncasedShaftBlock> steelEncased = CAFBlocks.STEEL_ENCASED_STEEL_SHAFT;
        ItemStack steelCasingItem = CAFBlocks.STEEL_CASING.asStack();

        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(3, 1, 2), Pointing.DOWN).rightClick()
                .withItem(steelCasingItem), 60);
        scene.idle(7);
        scene.world.setBlocks(steel, steelEncased.getDefaultState()
                .setValue(EncasedShaftBlock.AXIS, Direction.Axis.X), true);
        scene.world.setKineticSpeed(shaft, 32);
        scene.idle(10);
        scene.overlay.showText(100)
                .placeNearTarget()
                .text("Steel Block can be used to decorate Shafts")
                .pointAt(util.vector.topOf(0, 1, 2));
        scene.idle(70);
    }
}
