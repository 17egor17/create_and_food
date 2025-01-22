package net.egorplaytv.create_and_food.block.praperties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlockStateProperties {
    public static final EnumProperty<LanternAttachType> LANTERN_ATTACHMENT =
            EnumProperty.create("attachment", LanternAttachType.class);

    public static final EnumProperty<TerraceAttachType> TERRACE_ATTACHMENT =
            EnumProperty.create("attachment", TerraceAttachType.class);
    public static final BooleanProperty TERRACE_ENCASED = BooleanProperty.create("encased");

    public static final int MAX_AGE_8 = 8;
    public static final IntegerProperty AGE_8 = IntegerProperty.create("age",0,8);

    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 5);
}
