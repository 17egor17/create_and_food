package net.egorplaytv.caf.block.praperties;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlockStateProperties {
    public static final EnumProperty<LanternAttachType> LANTERN_ATTACHMENT =
            EnumProperty.create("attachment", LanternAttachType.class);

    public static final EnumProperty<TerraceAttachType> TERRACE_ATTACHMENT =
            EnumProperty.create("attachment", TerraceAttachType.class);

    public static final int MAX_AGE_14 = 14;
    public static final IntegerProperty AGE_14 = IntegerProperty.create("age",0,14);

    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 5);
}
