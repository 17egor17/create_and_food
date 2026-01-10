package net.egorplaytv.caf.fluid;

import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.util.CAFTags;
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

import static net.egorplaytv.caf.CreateAndFood.REGISTRATE;

public class CAFFluids {
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateAndFood.MOD_ID);

    //The color of the fluid is set in ClientEvents!!!!!

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR = FLUIDS.register("apple_vinegar",
            () -> new ForgeFlowingFluid.Source(CAFFluids.APPLE_VINEGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> APPLE_VINEGAR_FLOWING = FLUIDS.register("apple_vinegar_flowing",
            () -> new ForgeFlowingFluid.Flowing(CAFFluids.APPLE_VINEGAR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties APPLE_VINEGAR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> APPLE_VINEGAR.get(), () -> APPLE_VINEGAR_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("apple_vinegar_still"),
                    CreateAndFood.asFluid("apple_vinegar_flow"))
            .density(9).viscosity(5).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> CAFFluids.APPLE_VINEGAR_BLOCK.get()).bucket(() -> CAFItems.APPLE_VINEGAR_BUCKET.get());

    public static final RegistryObject<LiquidBlock> APPLE_VINEGAR_BLOCK = CAFBlocks.BLOCKS.register("apple_vinegar",
            () -> new LiquidBlock(() -> CAFFluids.APPLE_VINEGAR.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> COCOA_OIL = FLUIDS.register("cocoa_oil",
            () -> new ForgeFlowingFluid.Source(CAFFluids.COCOA_OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> COCOA_OIL_FLOWING = FLUIDS.register("cocoa_oil_flowing",
            () -> new ForgeFlowingFluid.Flowing(CAFFluids.COCOA_OIL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties COCOA_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> COCOA_OIL.get(), () -> COCOA_OIL_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("cocoa_oil_still"),
                    CreateAndFood.asFluid("cocoa_oil_flow"))
            .density(1400).viscosity(15).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> CAFFluids.COCOA_OIL_BLOCK.get()).bucket(() -> CAFItems.COCOA_OIL_BUCKET.get());

    public static final RegistryObject<LiquidBlock> COCOA_OIL_BLOCK = CAFBlocks.BLOCKS.register("cocoa_oil",
            () -> new LiquidBlock(() -> CAFFluids.COCOA_OIL.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE = FLUIDS.register("white_chocolate",
            () -> new ForgeFlowingFluid.Source(CAFFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> WHITE_CHOCOLATE_FLOWING = FLUIDS.register("white_chocolate_flowing",
            () -> new ForgeFlowingFluid.Flowing(CAFFluids.WHITE_CHOCOLATE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WHITE_CHOCOLATE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> WHITE_CHOCOLATE.get(), () -> WHITE_CHOCOLATE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("white_chocolate_still"),
                    CreateAndFood.asFluid("white_chocolate_flow"))
            .density(1400).viscosity(1500).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> CAFFluids.WHITE_CHOCOLATE_BLOCK.get()).bucket(() -> CAFItems.WHITE_CHOCOLATE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> WHITE_CHOCOLATE_BLOCK = CAFBlocks.BLOCKS.register("white_chocolate",
            () -> new LiquidBlock(() -> CAFFluids.WHITE_CHOCOLATE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> RED_GRAPE_JUICE = FLUIDS.register("red_grape_juice",
            () -> new ForgeFlowingFluid.Source(CAFFluids.RED_GRAPE_JUICE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> RED_GRAPE_JUICE_FLOWING = FLUIDS.register("red_grape_juice_flowing",
            () -> new ForgeFlowingFluid.Flowing(CAFFluids.RED_GRAPE_JUICE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties RED_GRAPE_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> RED_GRAPE_JUICE.get(), () -> RED_GRAPE_JUICE_FLOWING.get(), FluidAttributes.builder(CreateAndFood.asFluid("red_grape_juice_still"),
                    CreateAndFood.asFluid("red_grape_juice_flow"))
            .density(9).viscosity(5).overlay(WATER_OVERLAY_RL)).slopeFindDistance(6).levelDecreasePerBlock(1)
            .block(() -> CAFFluids.RED_GRAPE_JUICE_BLOCK.get()).bucket(() -> CAFItems.RED_GRAPE_JUICE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> RED_GRAPE_JUICE_BLOCK = CAFBlocks.BLOCKS.register("red_grape_juice",
            () -> new LiquidBlock(() -> CAFFluids.RED_GRAPE_JUICE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final FluidEntry<VirtualFluid> NIXIE_FLUID = REGISTRATE.virtualFluid("nixie_fluid")
            .tag(CAFTags.Fluids.modTag("nixie_fluid"))
            .register();

    public static final FluidEntry<VirtualFluid> CREAM = REGISTRATE.virtualFluid("cream")
            .tag(CAFTags.Fluids.modTag("creams"))
            .register();

    public static final FluidEntry<VirtualFluid> CUSTARD = REGISTRATE.virtualFluid("custard")
            .tag(CAFTags.Fluids.modTag("creams"))
            .register();

    public static final FluidEntry<VirtualFluid> GLAZE = REGISTRATE.virtualFluid("glaze")
            .tag(CAFTags.Fluids.modTag("creams"))
            .register();

    public static final FluidEntry<VirtualFluid> WOOD_PULP = REGISTRATE.virtualFluid("wood_pulp")
            .tag(CAFTags.Fluids.modTag("pulp"))
            .register();

    public static final FluidEntry<VirtualFluid> CRIMSON_PULP = REGISTRATE.virtualFluid("crimson_pulp")
            .tag(CAFTags.Fluids.modTag("pulp"))
            .register();

    public static final FluidEntry<VirtualFluid> WARPED_PULP = REGISTRATE.virtualFluid("warped_pulp")
            .tag(CAFTags.Fluids.modTag("pulp"))
            .register();

    public static final FluidEntry<VirtualFluid> CONCENTRATED_WOOD_PULP = REGISTRATE.virtualFluid("concentrated_wood_pulp")
            .tag(CAFTags.Fluids.modTag("concentrate_pulp"))
            .register();

    public static final FluidEntry<VirtualFluid> PAPER_PULP = REGISTRATE.virtualFluid("paper_pulp").register();

    public static final FluidEntry<VirtualFluid> SULFURIC_ACID = REGISTRATE.virtualFluid("sulfuric_acid").register();


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
