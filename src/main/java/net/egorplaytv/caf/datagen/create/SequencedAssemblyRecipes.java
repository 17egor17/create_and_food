package net.egorplaytv.caf.datagen.create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.block.custom.connect.PaletteStoneTypes;
import net.egorplaytv.caf.datagen.custom.SequencedAssemblyRecipeBuilder;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.recipe.PolishingRecipe;
import net.egorplaytv.caf.util.CAFTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class SequencedAssemblyRecipes {
    private static void sequencedAssembly(Consumer<FinishedRecipe> pConsumer) {
        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.IRON_KNIFE.get(), 2,
                        AllItems.IRON_SHEET.get(), CAFItems.INCOMPLETE_IRON_KNIFE.get())
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(CAFItems.TOOL_HANDLE.get()))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(CAFItems.TOOL_HANDLE.get()))
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.IRON_KNIFE.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.MARBLE_BLACK_GALAXY_BRICK.get(), 9, 80f,
                        PaletteStoneTypes.MARBLE_BLACK_GALAXY.getBaseBlock().get(), CAFItems.INCOMPLETE_MARBLE_BLACK_GALAXY_BRICK.get())
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(FillingRecipe::new, r -> r.require(Fluids.WATER, 250))
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .addResult(CAFBlocks.COBBLED_MARBLE_BLACK_GALAXY.get(), 20f)
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.MARBLE_BLACK_GALAXY_BRICK.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.MARBLE_BRICK.get(), 9, 80f,
                        PaletteStoneTypes.MARBLE.getBaseBlock().get(), CAFItems.INCOMPLETE_MARBLE_BRICK.get())
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(FillingRecipe::new, r -> r.require(Fluids.WATER, 250))
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .addResult(CAFBlocks.COBBLED_MARBLE.get(), 20f)
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.MARBLE_BRICK.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.MARBLE_PERLIN_PINK_BRICK.get(), 9, 80f,
                        PaletteStoneTypes.MARBLE_PERLIN_PINK.getBaseBlock().get(), CAFItems.INCOMPLETE_MARBLE_PERLIN_PINK_BRICK.get())
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(FillingRecipe::new, r -> r.require(Fluids.WATER, 250))
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .addResult(CAFBlocks.COBBLED_MARBLE_PERLIN_PINK.get(), 20f)
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.MARBLE_PERLIN_PINK_BRICK.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(Items.NETHERITE_INGOT, 50f,
                        CAFItems.NETHER_ALLOY.get(), CAFItems.INCOMPLETE_NETHERITE_INGOT.get())
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.GOLD_NUGGET))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.GOLD_NUGGET))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.GOLD_NUGGET))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.GOLD_NUGGET))
                .addStep(PressingRecipe::new, r -> r)
                .addResult(Items.GOLD_NUGGET, 20f)
                .addResult(CAFItems.NETHER_ALLOY.get(), 5f)
                .addResult(CAFItems.ALLOY_SOULS.get(), 15f)
                .addResult(CAFItems.PIECE_OF_GOLD.get(), 10f)
                .save(pConsumer, getSequencedAssembly(getRecipeId(Items.NETHERITE_INGOT)));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.NETHERITE_KNIFE.get(), 2,
                        Items.NETHERITE_INGOT, CAFItems.INCOMPLETE_NETHERITE_KNIFE.get())
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(PressingRecipe::new, r -> r)
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(CAFItems.TOOL_HANDLE.get()))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(CAFItems.TOOL_HANDLE.get()))
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.NETHERITE_KNIFE.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.RAW_BERRY_CAKE.get(),
                        CAFItems.SMALL_DOUGH_BASE.get(), CAFItems.INCOMPLETE_RAW_BERRY_CAKE.get())
                .addStep(FillingRecipe::new, r -> r.require(AllFluids.CHOCOLATE.get(), 250))
                .addStep(FillingRecipe::new, r -> r.require(CAFTags.CAFFluidTag("creams"), 250))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.SWEET_BERRIES))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.RAW_BERRY_CAKE.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.RAW_CAKE.get(),
                        CAFItems.BASE_OF_DOUGH.get(), CAFItems.INCOMPLETE_RAW_CAKE.get())
                .addStep(FillingRecipe::new, r -> r.require(AllFluids.CHOCOLATE.get(), 500))
                .addStep(FillingRecipe::new, r -> r.require(CAFTags.CAFFluidTag("creams"), 250))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.SWEET_BERRIES))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.RAW_CAKE.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.RAW_GLOW_BERRY_CAKE.get(),
                        CAFItems.SMALL_DOUGH_BASE.get(), CAFItems.INCOMPLETE_RAW_GLOW_BERRY_CAKE.get())
                .addStep(FillingRecipe::new, r -> r.require(CAFTags.forgeFluidTag("honey"), 250))
                .addStep(FillingRecipe::new, r -> r.require(CAFTags.CAFFluidTag("creams"), 250))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.GLOW_BERRIES))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.RAW_GLOW_BERRY_CAKE.get())));

        SequencedAssemblyRecipeBuilder.sequencedAssemblyRecipe(CAFItems.TOOL_HANDLE.get(), 2,
                        AllItems.IRON_SHEET.get(), CAFItems.INCOMPLETE_TOOL_HANDLE.get(), 4)
                .addStep(CuttingRecipe::new, r -> r.duration(100))
                .addStep(DeployerApplicationRecipe::new, r -> r.require(Items.IRON_NUGGET))
                .addStep(PressingRecipe::new, r -> r)
                .addStep(PolishingRecipe::new, r -> r.duration(100))
                .save(pConsumer, getSequencedAssembly(getRecipeId(CAFItems.TOOL_HANDLE.get())));
    }


    public static ResourceLocation getSequencedAssembly(String id) {
        return new ResourceLocation(MOD_ID, "create/sequenced_assembly/" + id);
    }

    private static String getRecipeId(ItemLike item){
        return item.asItem().getRegistryName().getPath();
    }

    public static void register(Consumer<FinishedRecipe> pConsumer){
        sequencedAssembly(pConsumer);
    }
}
