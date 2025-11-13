package net.egorplaytv.create_and_food.fluid;

import com.mojang.math.Vector3f;
import com.simibubi.create.AllFluids;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.utility.Color;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.egorplaytv.create_and_food.CreateAndFood.REGISTRATE;

public class ModFluids {
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateAndFood.MOD_ID);

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR = FLUIDS.register("apple_vinegar",
            () -> new ForgeFlowingFluid.Source(ModFluids.APPLE_VINEGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR_FLOWING = FLUIDS.register("apple_vinegar_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.APPLE_VINEGAR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties APPLE_VINEGAR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> APPLE_VINEGAR.get(), () -> APPLE_VINEGAR_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("apple_vinegar_still"),
                    CreateAndFood.asFluid("apple_vinegar_flow"))
            .density(9).viscosity(5).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.APPLE_VINEGAR_BLOCK.get()).bucket(() -> ModItems.APPLE_VINEGAR_BUCKET.get());

    public static final RegistryObject<LiquidBlock> APPLE_VINEGAR_BLOCK = ModBlocks.BLOCKS.register("apple_vinegar",
            () -> new LiquidBlock(() -> ModFluids.APPLE_VINEGAR.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> COCOA_OIL = FLUIDS.register("cocoa_oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.COCOA_OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> COCOA_OIL_FLOWING = FLUIDS.register("cocoa_oil_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.COCOA_OIL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties COCOA_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> COCOA_OIL.get(), () -> COCOA_OIL_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("cocoa_oil_still"),
                    CreateAndFood.asFluid("cocoa_oil_flow"))
            .density(1400).viscosity(15).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.COCOA_OIL_BLOCK.get()).bucket(() -> ModItems.COCOA_OIL_BUCKET.get());

    public static final RegistryObject<LiquidBlock> COCOA_OIL_BLOCK = ModBlocks.BLOCKS.register("cocoa_oil",
            () -> new LiquidBlock(() -> ModFluids.COCOA_OIL.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE = FLUIDS.register("white_chocolate",
            () -> new ForgeFlowingFluid.Source(ModFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE_FLOWING = FLUIDS.register("white_chocolate_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WHITE_CHOCOLATE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> WHITE_CHOCOLATE.get(), () -> WHITE_CHOCOLATE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("white_chocolate_still"),
                    CreateAndFood.asFluid("white_chocolate_flow"))
            .density(1400).viscosity(1500).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.WHITE_CHOCOLATE_BLOCK.get()).bucket(() -> ModItems.WHITE_CHOCOLATE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> WHITE_CHOCOLATE_BLOCK = ModBlocks.BLOCKS.register("white_chocolate",
            () -> new LiquidBlock(() -> ModFluids.WHITE_CHOCOLATE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> RED_GRAPE_JUICE = FLUIDS.register("red_grape_juice",
            () -> new ForgeFlowingFluid.Source(ModFluids.RED_GRAPE_JUICE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> RED_GRAPE_JUICE_FLOWING = FLUIDS.register("red_grape_juice_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.RED_GRAPE_JUICE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties RED_GRAPE_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> RED_GRAPE_JUICE.get(), () -> RED_GRAPE_JUICE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("red_grape_juice_still"),
                    CreateAndFood.asFluid("red_grape_juice_flow"))
            .density(9).viscosity(5).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.RED_GRAPE_JUICE_BLOCK.get()).bucket(() -> ModItems.RED_GRAPE_JUICE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> RED_GRAPE_JUICE_BLOCK = ModBlocks.BLOCKS.register("red_grape_juice",
            () -> new LiquidBlock(() -> ModFluids.RED_GRAPE_JUICE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final FluidEntry<VirtualFluid> NIXIE_FLUID = REGISTRATE.virtualFluid("nixie_fluid")
            .tag(ModTags.Fluids.modTag("nixie_fluid"))
            .register();

    public static final FluidEntry<VirtualFluid> CREAM = REGISTRATE.virtualFluid("cream")
            .tag(ModTags.Fluids.modTag("creams"))
            .register();

    public static final FluidEntry<VirtualFluid> CUSTARD = REGISTRATE.virtualFluid("custard")
            .tag(ModTags.Fluids.modTag("creams"))
            .register();

    public static final FluidEntry<VirtualFluid> GLAZE = REGISTRATE.virtualFluid("glaze")
            .tag(ModTags.Fluids.modTag("creams"))
            .register();

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
