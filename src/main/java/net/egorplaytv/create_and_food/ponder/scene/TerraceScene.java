package net.egorplaytv.create_and_food.ponder.scene;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.TerraceBlock;
import net.egorplaytv.create_and_food.block.custom.TerraceStairsBlock;
import net.egorplaytv.create_and_food.block.praperties.TerraceAttachType;
import net.egorplaytv.create_and_food.block.praperties.TerraceEncasedType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TerraceScene {

    public static void terrace_all(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("terrace_all", "Terraces and Terrace Stairs");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        BlockPos terracePos = util.grid.at(2, 1, 2);
        scene.world.showSection(util.select.position(terracePos), Direction.DOWN);
        Block oak_terrace = ModBlocks.OAK_TERRACE.get();
        Block oak_terrace_stairs = ModBlocks.OAK_TERRACE_STAIRS.get();
        Block acacia_terrace = ModBlocks.ACACIA_TERRACE.get();
        Block acacia_terrace_stairs = ModBlocks.ACACIA_TERRACE_STAIRS.get();
        Block almond_terrace = ModBlocks.ALMOND_TERRACE.get();
        Block almond_terrace_stairs = ModBlocks.ALMOND_TERRACE_STAIRS.get();
        Block birch_terrace = ModBlocks.BIRCH_TERRACE.get();
        Block birch_terrace_stairs = ModBlocks.BIRCH_TERRACE_STAIRS.get();
        Block crimson_terrace = ModBlocks.CRIMSON_TERRACE.get();
        Block crimson_terrace_stairs = ModBlocks.CRIMSON_TERRACE_STAIRS.get();
        Block jungle_terrace = ModBlocks.JUNGLE_TERRACE.get();
        Block jungle_terrace_stairs = ModBlocks.JUNGLE_TERRACE_STAIRS.get();
        Block dark_oak_terrace = ModBlocks.DARK_OAK_TERRACE.get();
        Block dark_oak_terrace_stairs = ModBlocks.DARK_OAK_TERRACE_STAIRS.get();
        Block spruce_terrace = ModBlocks.SPRUCE_TERRACE.get();
        Block spruce_terrace_stairs = ModBlocks.SPRUCE_TERRACE_STAIRS.get();
        Block warped_terrace = ModBlocks.WARPED_TERRACE.get();
        Block warped_terrace_stairs = ModBlocks.WARPED_TERRACE_STAIRS.get();
        scene.idle(20);
        scene.overlay.showText(150).text("Terraces come in different types of wood, as well as terrace stairs")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos));
        scene.world.setBlocks(util.select.position(2, 1, 2), oak_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), acacia_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), acacia_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), almond_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), almond_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), birch_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), birch_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), crimson_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), crimson_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), jungle_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), jungle_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), dark_oak_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), dark_oak_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), spruce_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), spruce_terrace_stairs.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), warped_terrace.defaultBlockState(), false);
        scene.idle(10);
        scene.world.setBlocks(util.select.position(2, 1, 2), warped_terrace_stairs.defaultBlockState(), false);
        scene.idle(70);
    }

    public static void terrace_connect(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("terrace_connect", "Connecting Terraces and Terrace Stairs");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        BlockPos terracePos1 = util.grid.at(2, 1, 2);
        BlockPos terracePos2 = util.grid.at(2, 2, 2);
        BlockPos terracePos3 = util.grid.at(2, 3, 2);
        Block oak_terrace = ModBlocks.OAK_TERRACE.get();
        Block oak_terrace_stairs = ModBlocks.OAK_TERRACE_STAIRS.get();
        scene.world.setBlock(terracePos1, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos1), Direction.DOWN);
        scene.idle(20);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos2), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.world.setBlock(terracePos1, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.LOW), false);
        scene.overlay.showText(60).text("Can be connected into 2 blocks in height")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
        scene.world.setBlock(terracePos3, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos3), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.MIDDLE), false);
        scene.world.setBlock(terracePos3, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.overlay.showText(60).text("Can be connected into 3 blocks in height, but they can do more")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
        scene.world.setBlock(terracePos3, Blocks.AIR.defaultBlockState(), true);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.idle(5);
        scene.world.setBlock(terracePos2, Blocks.AIR.defaultBlockState(), true);
        scene.world.setBlock(terracePos1, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.SINGLE), false);
        scene.idle(5);
        scene.world.setBlock(terracePos1, Blocks.AIR.defaultBlockState(), true);
        scene.idle(5);
        scene.world.hideSection(util.select.fromTo(terracePos1, terracePos3), Direction.DOWN);
        scene.idle(20);
        scene.world.setBlock(terracePos1, oak_terrace_stairs.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos1), Direction.DOWN);
        scene.overlay.showText(60).text("Here we see terrace stairs")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos1));
        scene.idle(70);
        scene.world.setBlock(terracePos2, oak_terrace_stairs.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos2), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos1, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.LOW), false);
        scene.world.setBlock(terracePos2, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.overlay.showText(60).text("Can be connected into 2 blocks in height")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
        scene.world.setBlock(terracePos3, oak_terrace_stairs.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos3), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos2, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.MIDDLE), false);
        scene.world.setBlock(terracePos3, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.overlay.showText(60).text("Can be connected into 3 blocks in height, but they can do more")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
        scene.world.setBlock(terracePos3, Blocks.AIR.defaultBlockState(), true);
        scene.world.setBlock(terracePos2, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.idle(5);
        scene.world.setBlock(terracePos2, Blocks.AIR.defaultBlockState(), true);
        scene.world.setBlock(terracePos1, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.SINGLE), false);
        scene.idle(5);
        scene.world.hideSection(util.select.fromTo(terracePos2, terracePos3), Direction.DOWN);
        scene.idle(40);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos2), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos1, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.LOW), false);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.overlay.showText(60).text("They can connect")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
        scene.world.setBlock(terracePos2, Blocks.AIR.defaultBlockState(), true);
        scene.world.setBlock(terracePos1, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.SINGLE), false);
        scene.idle(5);
        scene.world.setBlock(terracePos1, Blocks.AIR.defaultBlockState(), true);
        scene.world.hideSection(util.select.fromTo(terracePos1, terracePos2), Direction.DOWN);
        scene.idle(40);
        scene.world.setBlock(terracePos1, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos1), Direction.DOWN);
        scene.idle(20);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos2), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos1, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.LOW), false);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.idle(5);
        scene.world.setBlock(terracePos3, oak_terrace_stairs.defaultBlockState(), false);
        scene.world.showSection(util.select.position(terracePos3), Direction.DOWN);
        scene.idle(15);
        scene.world.setBlock(terracePos2, oak_terrace.defaultBlockState().setValue(TerraceBlock.ATTACHMENT, TerraceAttachType.MIDDLE), false);
        scene.world.setBlock(terracePos3, oak_terrace_stairs.defaultBlockState().setValue(TerraceStairsBlock.ATTACHMENT, TerraceAttachType.UP), false);
        scene.overlay.showText(60).text("If you have a limited number of Terrace stairs then you can use the blocks in this way")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(terracePos2));
        scene.idle(70);
    }
}