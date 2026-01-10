package net.egorplaytv.caf.world.feature;

import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeatures {
    public static final RuleTest NETHERRACK_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.NETHERRACK_ORE_REPLACEABLE);
    public static final RuleTest WARPED_NYLIUM_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.WARPED_NYLIUM_ORE_REPLACEABLE);
    public static final RuleTest END_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.END_ORE_REPLACEABLE);
    public static final RuleTest CRIMSON_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.CRIMSON_ORE_REPLACEABLE);
    public static final RuleTest BLACKSTONE_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.BLACKSTONE_ORE_REPLACEABLE);
    public static final RuleTest BASALT_ORE_REPLACEABLE = new TagMatchTest(CAFTags.Blocks.BASALT_ORE_REPLACEABLE);
}
