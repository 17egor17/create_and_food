package net.egorplaytv.caf.ponder.scene;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.egorplaytv.caf.block.entity.custom.ScalesBlockEntity;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class ScalesScene {
    public static void scales(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("scales", "Using scales");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.position(2, 1, 2), Direction.DOWN);
        scene.idle(10);
        scene.world.showSection(util.select.position(2, 2, 2), Direction.DOWN);

        BlockPos scalesPos = util.grid.at(2, 2, 2);
        Vec3 scalesSide = util.vector.blockSurface(scalesPos, Direction.WEST);
        ItemStack steel_ingot = new ItemStack(CAFItems.STEEL_INGOT.get());
        scene.idle(10);


        scene.idle(40);
        scene.overlay.showText(40).pointAt(scalesSide).placeNearTarget().attachKeyFrame()
                .text("To start weighing, you need to put the item on the scales");
        Class<ScalesBlockEntity> scales = ScalesBlockEntity.class;
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(scalesPos), Pointing.DOWN).withItem(steel_ingot), 30);
        scene.world.modifyBlockEntity(scalesPos, scales, sc -> sc.setIsWeighing(true));
        scene.world.modifyBlockEntity(scalesPos, scales, sc -> sc.itemHandler.setStackInSlot(0, steel_ingot));
        scene.idle(60);
        scene.overlay.showText(40)
                .pointAt(scalesSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Then right-click on the scales with an empty hand");
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(scalesPos), Pointing.DOWN).rightClick(), 30);
        scene.idle(60);
        scene.overlay.showText(40).pointAt(scalesSide).placeNearTarget().attachKeyFrame()
                .text("This ingot weighs 1 kg");
        scene.idle(60);
        scene.overlay.showText(40).text("To remove the item from the scales, you need to click");
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(scalesPos), Pointing.DOWN).whileSneaking().rightClick(), 30);
        scene.idle(20);
        scene.world.modifyBlockEntity(scalesPos, scales, sc -> sc.itemHandler.setStackInSlot(0, new ItemStack(Items.AIR)));
        scene.world.modifyBlockEntity(scalesPos, scales, sc -> sc.setIsWeighing(false));
        scene.idle(20);
    }
}