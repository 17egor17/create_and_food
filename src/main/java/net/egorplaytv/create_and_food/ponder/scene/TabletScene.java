package net.egorplaytv.create_and_food.ponder.scene;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

public class TabletScene {

    public static void tablet(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("tablet", "Metal Sample Tablet");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        BlockPos tabletPos = util.grid.at(2, 1, 2);
        scene.world.showSection(util.select.position(tabletPos), Direction.NORTH);
        ItemStack steel = ModItems.STEEL_INGOT.get().getDefaultInstance();
        scene.idle(30);
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(tabletPos), Pointing.DOWN)).withItem(steel), 57);
        scene.idle(20);
        scene.overlay.showText(60).text("Having placed the ingot in Metal Sample Tablet can you find out more information about the ingot")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(tabletPos));
        scene.idle(70);
        scene.overlay.showText(60).text("In Metal Sample Tablet can put any identified metal")
                .attachKeyFrame().placeNearTarget().pointAt(util.vector.topOf(tabletPos));
    }
}
