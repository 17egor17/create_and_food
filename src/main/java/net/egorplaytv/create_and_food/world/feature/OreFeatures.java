package net.egorplaytv.create_and_food.world.feature;

import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeatures {
    public static final RuleTest NETHERRACK_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.NETHERRACK_ORE_REPLACEABLE);
    public static final RuleTest WARPED_NYLIUM_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.WARPED_NYLIUM_ORE_REPLACEABLE);
    public static final RuleTest END_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.END_ORE_REPLACEABLE);
    public static final RuleTest CRIMSON_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.CRIMSON_ORE_REPLACEABLE);
    public static final RuleTest BLACKSTONE_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.BLACKSTONE_ORE_REPLACEABLE);
    public static final RuleTest BASALT_ORE_REPLACEABLE = new TagMatchTest(ModTags.Blocks.BASALT_ORE_REPLACEABLE);
}
