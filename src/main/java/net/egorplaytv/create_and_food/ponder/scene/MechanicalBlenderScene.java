package net.egorplaytv.create_and_food.ponder.scene;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.IntAttached;
import com.simibubi.create.foundation.utility.NBTHelper;
import com.simibubi.create.foundation.utility.Pointing;
import net.egorplaytv.create_and_food.block.entity.custom.MechanicalBlenderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.registry.ModItems;

public class MechanicalBlenderScene {

    public static void blender(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("mechanical_blender", "Processing Items with the Mechanical Blender");
        scene.configureBasePlate(0, 0, 5);
        scene.world.setBlock(util.grid.at(1, 1, 2), AllBlocks.ANDESITE_CASING.getDefaultState(), false);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.modifyKineticSpeed(util.select.position(2, 0, 5), f -> 16F);
        scene.world.modifyKineticSpeed(util.select.fromTo(3, 1, 1, 1, 1, 1), f -> -32F);
        scene.world.modifyKineticSpeed(util.select.fromTo(3, 1, 5, 3, 1, 2), f -> -32F);
        scene.world.modifyKineticSpeed(util.select.fromTo(1, 4, 3, 1, 1, 3), f -> 32F);
        scene.world.modifyKineticSpeed(util.select.position(1, 4, 2), f -> -32F);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(1, 4, 3, 1, 1, 5), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 1, 2), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 2, 2), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 4, 2), Direction.SOUTH);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(3, 1, 1, 1, 1, 1), Direction.SOUTH);
        scene.world.showSection(util.select.fromTo(3, 1, 5, 3, 1, 2), Direction.SOUTH);
        scene.idle(20);

        BlockPos basin = util.grid.at(1, 2, 2);
        BlockPos blenderPos = util.grid.at(1, 4, 2);
        Vec3 basinSide = util.vector.blockSurface(basin, Direction.WEST);

        ItemStack beef = new ItemStack(Items.BEEF);
        ItemStack minced_beef = new ItemStack(ModItems.MINCED_BEEF.get());

        scene.overlay.showText(60)
                .pointAt(basinSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("With a Blender and Basin, some Crafting Recipes can be automated");
        scene.idle(40);

        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(basin), Pointing.DOWN).withItem(beef), 30);
        scene.idle(30);
        Class<MechanicalBlenderBlockEntity> type = MechanicalBlenderBlockEntity.class;
        scene.world.modifyBlockEntity(blenderPos, type, pte -> pte.startProcessingBasin());
        scene.world.createItemOnBeltLike(basin, Direction.UP, beef);
        scene.idle(80);
        scene.world.modifyBlockEntityNBT(util.select.position(basin), BasinBlockEntity.class, nbt -> {
            nbt.put("VisualizedItems",
                    NBTHelper.writeCompoundList(ImmutableList.of(IntAttached.with(2, minced_beef)), ia -> ia.getValue()
                            .serializeNBT()));
        });
        scene.idle(4);
        scene.world.createItemOnBelt(util.grid.at(1, 1, 1), Direction.UP, minced_beef);
        scene.idle(30);

        scene.overlay.showText(80)
                .pointAt(basinSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Available recipes include any Cutting Crafting Recipe, plus a couple extra ones");
        scene.idle(80);

        scene.rotateCameraY(-30);
        scene.idle(10);
        scene.world.setBlock(util.grid.at(1, 1, 2), AllBlocks.BLAZE_BURNER.getDefaultState()
                .setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.KINDLED), true);
        scene.idle(10);

        scene.overlay.showText(80)
                .pointAt(basinSide.subtract(0, 1, 0))
                .placeNearTarget()
                .text("Some of those recipes may require the heat of a Blaze Burner");
        scene.idle(40);

        scene.rotateCameraY(30);

        scene.idle(60);
        Vec3 filterPos = util.vector.of(1, 2.75f, 2.5f);
        scene.overlay.showFilterSlotInput(filterPos, Direction.WEST, 100);
        scene.overlay.showText(100)
                .pointAt(filterPos)
                .placeNearTarget()
                .attachKeyFrame()
                .text("The filter slot can be used in case two recipes are conflicting.");
        scene.idle(80);
    }
}
