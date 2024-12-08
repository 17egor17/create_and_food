package net.egorplaytv.create_and_food.block.custom.connect;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class framedWall {
    public framedWall(CreateRegistrate registrate) {
        ModBlocks.FRAMED_WALL = (CreateAndFood.REGISTRATE.block("framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.BLACK_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("black_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.BLACK_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.BLUE_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("blue_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.BLUE_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.BROWN_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("brown_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.BROWN_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.CYAN_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("cyan_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.CYAN_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.GREEN_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("green_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.GREEN_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.LUMINOUS_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("luminous_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.LUMINOUS_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
                .properties(p -> p.lightLevel($ -> 5))
        ).register();
        ModBlocks.ORANGE_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("orange_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.ORANGE_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
        ModBlocks.RED_FRAMED_WALL = (CreateAndFood.REGISTRATE.block("red_framed_wall", CTFramedWall::new)
                .properties(p -> p.color(MaterialColor.WOOD))
                .transform(BuilderTransformers.framedWall(() -> {
                    return SpriteShifts.RED_FRAMED_WALL;
                })).properties(p -> p.sound(SoundType.WOOD))
        ).register();
    }
}
