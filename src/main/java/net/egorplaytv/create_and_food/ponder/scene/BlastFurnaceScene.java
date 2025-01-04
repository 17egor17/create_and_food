package net.egorplaytv.create_and_food.ponder.scene;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.custom.MarbleBlastFurnaceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlastFurnaceScene {

    public static void blast_furnace(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("blast_furnace", "Black Marble Blast Furnace");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        BlockPos furnacePos = util.grid.at(2, 1, 2);
        scene.world.showSection(util.select.position(furnacePos), Direction.NORTH);
        Block furnace = ModBlocks.MARBLE_BLAST_FURNACE.get();
        ItemStack air = Items.AIR.getDefaultInstance();
        ItemStack blaze_cake = AllItems.BLAZE_CAKE.asStack();
        scene.idle(20);
        scene.overlay.showText(60).text("Since this smelter works according to a different scheme, different from Minecraft’s, its operating principle is as follows:")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.idle(70);
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(furnacePos), Pointing.DOWN)).rightClick().withItem(blaze_cake), 57);
        scene.world.setBlocks(util.select.position(2, 1, 2), (BlockState) ((BlockState) furnace.defaultBlockState().setValue(MarbleBlastFurnaceBlock.LIT, true)), false);
        scene.idle(20);
        scene.overlay.showText(60).text("By putting fuel in the fuel cell you will raise the heat level to a certain level. For example, blaze cake will raise the heat level by 1000°C")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.idle(70);
        scene.overlay.showText(60).text("To remelt the metal, you need to observe the melting degree")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.idle(70);
        scene.overlay.showText(60).text("If the melting degree is lower than required, the process will not be performed")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.idle(70);
        scene.overlay.showText(60).text("And if the melting degree is more than required, the process will be performed faster")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.idle(70);
        scene.overlay.showText(60).text("The main thing is to remember that the smelting furnace will cool down over time")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(furnacePos));
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(furnacePos), Pointing.DOWN)).withItem(air), 57);
        scene.world.setBlocks(util.select.position(2, 1, 2), (BlockState) ((BlockState) furnace.defaultBlockState().setValue(MarbleBlastFurnaceBlock.LIT, false)), false);
    }
}
