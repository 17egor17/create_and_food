package net.egorplaytv.create_and_food.fluid;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateAndFood.MOD_ID);

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR_FLUID = FLUIDS.register("apple_vinegar",
            () -> new ForgeFlowingFluid.Source(ModFluids.APPLE_VINEGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR_FLOWING = FLUIDS.register("apple_vinegar_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.APPLE_VINEGAR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties APPLE_VINEGAR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> APPLE_VINEGAR_FLUID.get(), () -> APPLE_VINEGAR_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("apple_vinegar_still"),
                    CreateAndFood.asFluid("apple_vinegar_flow"))
            .density(9).luminosity(2).viscosity(5).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.APPLE_VINEGAR_BLOCK.get()).bucket(() -> ModItems.APPLE_VINEGAR_BUCKET.get());

    public static final RegistryObject<LiquidBlock> APPLE_VINEGAR_BLOCK = ModBlocks.BLOCKS.register("apple_vinegar",
            () -> new LiquidBlock(() -> ModFluids.APPLE_VINEGAR_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> COCOA_OIL_FLUID = FLUIDS.register("cocoa_oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.COCOA_OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> COCOA_OIL_FLOWING = FLUIDS.register("cocoa_oil_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.COCOA_OIL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties COCOA_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> COCOA_OIL_FLUID.get(), () -> COCOA_OIL_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("cocoa_oil_still"),
                    CreateAndFood.asFluid("cocoa_oil_flow"))
            .density(9).luminosity(2).viscosity(5).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.COCOA_OIL_BLOCK.get()).bucket(() -> ModItems.COCOA_OIL_BUCKET.get());

    public static final RegistryObject<LiquidBlock> COCOA_OIL_BLOCK = ModBlocks.BLOCKS.register("cocoa_oil",
            () -> new LiquidBlock(() -> ModFluids.COCOA_OIL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE_FLUID = FLUIDS.register("white_chocolate",
            () -> new ForgeFlowingFluid.Source(ModFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE_FLOWING = FLUIDS.register("white_chocolate_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WHITE_CHOCOLATE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> WHITE_CHOCOLATE_FLUID.get(), () -> WHITE_CHOCOLATE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("white_chocolate_still"),
                    CreateAndFood.asFluid("white_chocolate_flow"))
            .density(9).luminosity(2).viscosity(5).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.WHITE_CHOCOLATE_BLOCK.get()).bucket(() -> ModItems.WHITE_CHOCOLATE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> WHITE_CHOCOLATE_BLOCK = ModBlocks.BLOCKS.register("white_chocolate",
            () -> new LiquidBlock(() -> ModFluids.WHITE_CHOCOLATE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));


    public static final RegistryObject<FlowingFluid> NIXIE_FLUID = FLUIDS.register("nixie_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.NIXIE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> NIXIE_FLOWING = FLUIDS.register("nixie_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.NIXIE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties NIXIE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> NIXIE_FLUID.get(), () -> NIXIE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("nixie_fluid_still"),
                    CreateAndFood.asFluid("nixie_fluid_flow"))
            .density(9).luminosity(2).viscosity(5).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> ModFluids.NIXIE_BLOCK.get());

    public static final RegistryObject<LiquidBlock> NIXIE_BLOCK = ModBlocks.BLOCKS.register("nixie_fluid",
            () -> new LiquidBlock(() -> ModFluids.NIXIE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).lightLevel((p_50755_) -> {
                        return 10;
                    }).noDrops()));



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
