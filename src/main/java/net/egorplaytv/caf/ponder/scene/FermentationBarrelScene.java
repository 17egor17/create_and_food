package net.egorplaytv.caf.ponder.scene;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FermentationBarrelScene {

    public static void non_connectable(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("fermentation_barrel_connect", "Connect Fermentation Barrel");
        scene.configureBasePlate(0, 0, 9);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 9, 0, 9), Direction.UP);
        scene.idle(5);
        BlockPos fermentationPos1 = util.grid.at(4, 2, 1);
        BlockPos fermentationPos2 = util.grid.at(1, 2, 2);
        BlockPos fermentationPos3 = util.grid.at(1, 2, 5);
        scene.world.showSection(util.select.fromTo(5, 1, 5, 7, 2, 7), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(6, 1, 2, 7, 2, 3), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(4, 1, 3, 4, 2, 3), Direction.UP);
        scene.idle(20);
        scene.world.showSection(util.select.fromTo(1, 1, 5, 3, 2, 7), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(1, 1, 2, 2, 2, 3), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(4, 1, 1, 4, 2, 1), Direction.UP);
        scene.idle(20);
        scene.overlay.showText(60).text("As you can see, Fermentation Barrel is not connected by height")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(fermentationPos1));
        scene.idle(140);
        scene.overlay.showText(60).text("As you may have noticed Fermentation Barrel does not connect 2x2")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(fermentationPos2));
        scene.idle(140);
        scene.overlay.showText(60).text("As you may have noticed Fermentation Barrel does not connect 3x3")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(fermentationPos3));
        scene.idle(60);
    }

    public static void tube_in_up(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("fermentation_barrel_connect_pipe", "Connect Fermentation Barrel with a pipe");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        BlockPos fermentationPos = util.grid.at(2, 1, 2);
        BlockPos pipePos1 = util.grid.at(1, 1, 1);
        BlockPos pipePos2 = util.grid.at(2, 1, 1);
        BlockPos pipePos3 = util.grid.at(3, 1, 1);
        BlockPos pipePos4 = util.grid.at(3, 1, 2);
        BlockPos pipePos5 = util.grid.at(3, 1, 3);
        BlockPos pipePos6 = util.grid.at(2, 1, 3);
        BlockPos pipePos7 = util.grid.at(1, 1, 3);
        BlockPos pipePos8 = util.grid.at(1, 1, 2);
        BlockPos pipePos9 = util.grid.at(2, 2, 3);
        BlockPos pipePos10 = util.grid.at(2, 2, 2);
        scene.world.showSection(util.select.position(fermentationPos), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos1), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos2), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos3), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos4), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos5), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos6), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos7), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos8), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos9), Direction.NORTH);
        scene.idle(5);
        scene.world.showSection(util.select.position(pipePos10), Direction.NORTH);
        scene.idle(20);
        scene.overlay.showText(60).text("You can see that only the pipe at the top was connected")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(pipePos10));
        scene.idle(70);
        scene.overlay.showText(60).text("You can only pour in liquid, but you can't pump it out")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(fermentationPos));
        scene.idle(60);
    }

    public static void item_in_fermentation_barrel(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("fermentation_barrel_placing_items", "Placing items in Fermentation Barrel");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.fromTo(3, 0, 5, 3, 0, 5), f -> -8F);
        scene.world.modifyKineticSpeed(util.select.fromTo(2, 1, 2, 4, 1, 5), f -> 16F);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 6), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(2, 1, 2, 5, 1, 6), Direction.UP);
        BlockPos funnelPos = util.grid.at(2, 2, 2);
        BlockPos fermentationPos = util.grid.at(1, 2, 2);
        BlockPos beltEnd = util.grid.at(4, 1, 2);
        ItemStack stack = new ItemStack(Items.COPPER_BLOCK);
        ElementLink<EntityElement> item =
                scene.world.createItemEntity(util.vector.centerOf(4, 4, 2), util.vector.of(0, 0, 0), stack);
        scene.idle(13);
        scene.world.showSection(util.select.fromTo(1, 1, 2, 1, 2, 2), Direction.UP);
        scene.world.showSection(util.select.fromTo(2, 2, 2, 2, 2, 2), Direction.UP);
        scene.overlay.showText(60).text("In Fermentation Barrel you can place items through the auxiliary blocks, but you can't place items through the top")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(funnelPos));
        scene.world.modifyEntity(item, Entity::discard);
        scene.world.createItemOnBelt(beltEnd, Direction.DOWN, stack);
        scene.idle(70);
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(fermentationPos), Pointing.DOWN)).withItem(stack), 57);
        scene.overlay.showText(60).text("You can see the item fits into Fermentation Barrel")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(fermentationPos));
        scene.idle(60);
    }
}