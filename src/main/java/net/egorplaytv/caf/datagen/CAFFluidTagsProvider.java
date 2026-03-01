package net.egorplaytv.caf.datagen;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CAFFluidTagsProvider extends TagsProvider<Fluid> {

    public CAFFluidTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.FLUID, CreateAndFood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(FluidTags.WATER)
                .add(CAFFluids.APPLE_VINEGAR.get(), CAFFluids.APPLE_VINEGAR_FLOWING.get())
                .add(CAFFluids.COCOA_OIL.get(), CAFFluids.COCOA_OIL_FLOWING.get())
                .add(CAFFluids.WHITE_CHOCOLATE.get(), CAFFluids.WHITE_CHOCOLATE_FLOWING.get())
                .add(CAFFluids.RED_GRAPE_JUICE.get(), CAFFluids.RED_GRAPE_JUICE_FLOWING.get());

    }

    @Override
    public String getName() {
        return "Create and Food Fluid Tags";
    }
}
