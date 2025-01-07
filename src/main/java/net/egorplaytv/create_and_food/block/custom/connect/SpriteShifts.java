package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import com.simibubi.create.foundation.block.render.SpriteShifter;
import net.egorplaytv.create_and_food.CreateAndFood;

public class SpriteShifts {

    public static final CTSpriteShiftEntry ALLOY_SOULS_CASING = omniCasing("alloy_souls_casing"),
            STEEL_BLOCK = omniCasing("steel_block"),
            STEEL_SCAFFOLD = horizontal("steel_scaffold"),
            STEEL_SCAFFOLD_INSIDE = horizontal("steel_scaffold_inside"),

            FRAMED_WALL = omniWall("framed_wall"),
            BLACK_FRAMED_WALL = omniWall("black_framed_wall"),
            BLUE_FRAMED_WALL = omniWall("blue_framed_wall"),
            BROWN_FRAMED_WALL = omniWall("brown_framed_wall"),
            CYAN_FRAMED_WALL = omniWall("cyan_framed_wall"),
            GREEN_FRAMED_WALL = omniWall("green_framed_wall"),
            LUMINOUS_FRAMED_WALL = omniWall("luminous_framed_wall"),
            ORANGE_FRAMED_WALL = omniWall("orange_framed_wall"),
            RED_FRAMED_WALL = omniWall("red_framed_wall"),
            FRAMED_WALL_SMALL_BRICK = omniWall("framed_wall_small_brick"),
            BLACK_FRAMED_WALL_SMALL_BRICK = omniWall("black_framed_wall_small_brick"),
            BLUE_FRAMED_WALL_SMALL_BRICK = omniWall("blue_framed_wall_small_brick"),
            BROWN_FRAMED_WALL_SMALL_BRICK = omniWall("brown_framed_wall_small_brick"),
            CYAN_FRAMED_WALL_SMALL_BRICK = omniWall("cyan_framed_wall_small_brick"),
            GREEN_FRAMED_WALL_SMALL_BRICK = omniWall("green_framed_wall_small_brick"),
            LUMINOUS_FRAMED_WALL_SMALL_BRICK = omniWall("luminous_framed_wall_small_brick"),
            ORANGE_FRAMED_WALL_SMALL_BRICK = omniWall("orange_framed_wall_small_brick"),
            RED_FRAMED_WALL_SMALL_BRICK = omniWall("red_framed_wall_small_brick"),
            FRAMED_WALL_BRICK = omniWall("framed_wall_brick"),
            BLACK_FRAMED_WALL_BRICK = omniWall("black_framed_wall_brick"),
            BLUE_FRAMED_WALL_BRICK = omniWall("blue_framed_wall_brick"),
            BROWN_FRAMED_WALL_BRICK = omniWall("brown_framed_wall_brick"),
            CYAN_FRAMED_WALL_BRICK = omniWall("cyan_framed_wall_brick"),
            GREEN_FRAMED_WALL_BRICK = omniWall("green_framed_wall_brick"),
            LUMINOUS_FRAMED_WALL_BRICK = omniWall("luminous_framed_wall_brick"),
            ORANGE_FRAMED_WALL_BRICK = omniWall("orange_framed_wall_brick"),
            RED_FRAMED_WALL_BRICK = omniWall("red_framed_wall_brick"),
            FRAMED_WALL_GOLDEN = omniWall("framed_wall_golden"),
            BLACK_FRAMED_WALL_GOLDEN = omniWall("black_framed_wall_golden"),
            BLUE_FRAMED_WALL_GOLDEN = omniWall("blue_framed_wall_golden"),
            BROWN_FRAMED_WALL_GOLDEN = omniWall("brown_framed_wall_golden"),
            CYAN_FRAMED_WALL_GOLDEN = omniWall("cyan_framed_wall_golden"),
            GREEN_FRAMED_WALL_GOLDEN = omniWall("green_framed_wall_golden"),
            LUMINOUS_FRAMED_WALL_GOLDEN = omniWall("luminous_framed_wall_golden"),
            ORANGE_FRAMED_WALL_GOLDEN = omniWall("orange_framed_wall_golden"),
            RED_FRAMED_WALL_GOLDEN = omniWall("red_framed_wall_golden"),
            SECURE_BLOCK = omniCasing("secure_block"),
            STONE_WALKWAY = omniCasing("stone_walkway"),
            DEEPSLATE_WALKWAY = omniCasing("deepslate_walkway"),
            SANDSTONE_WALKWAY = omniCasing("sandstone_walkway"),
            RED_SANDSTONE_WALKWAY = omniCasing("red_sandstone_walkway"),
            GOLDEN_WALL_TOP = omni("golden_wall_top");

    public static final CTSpriteShiftEntry
            GOLDEN_WALL = vertical("golden_wall"),
            ALMOND_GLASS = omni( "almond_glass"),
            ALLOY_SOULS_GLASS = omni("alloy_souls_glass");






    public SpriteShifts(){
    }
    public static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }
    public static CTSpriteShiftEntry omniCasing(String name) {
        return getCasingCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }
    public static CTSpriteShiftEntry omniWall(String name){
        return getWallCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    public static CTSpriteShiftEntry horizontal(String name) {
        return getScaffoldCT(AllCTTypes.HORIZONTAL_KRYPPERS, name);
    }
    private static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }

    private static CTSpriteShiftEntry getCasingCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateAndFood.asResource("block/palettes/casing/" + blockTextureName),
                CreateAndFood.asResource("block/palettes/casing/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCasingCT(CTType type, String blockTextureName) {
        return getCasingCT(type, blockTextureName, blockTextureName);
    }

    private static CTSpriteShiftEntry getWallCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateAndFood.asResource("block/palettes/framed_wall/" + blockTextureName),
                CreateAndFood.asResource("block/palettes/framed_wall/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getWallCT(CTType type, String blockTextureName) {
        return getWallCT(type, blockTextureName, blockTextureName);
    }


    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateAndFood.asResource("block/palettes/" + blockTextureName),
                CreateAndFood.asResource("block/palettes/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    private static CTSpriteShiftEntry getScaffoldCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateAndFood.asResource("block/scaffold/" + blockTextureName),
                CreateAndFood.asResource("block/scaffold/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getScaffoldCT(CTType type, String blockTextureName) {
        return getScaffoldCT(type, blockTextureName, blockTextureName);
    }
}
