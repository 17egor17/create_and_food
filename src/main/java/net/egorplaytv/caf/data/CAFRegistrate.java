package net.egorplaytv.caf.data;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.encasing.CasingConnectivity;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.block.connected.CTModel;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class CAFRegistrate extends AbstractRegistrate<CAFRegistrate> {
    @Nullable
    protected Function<Item, TooltipModifier> currentTooltipModifierFactory;

    protected CAFRegistrate(String modId) {
        super(modId);
    }

    public static CAFRegistrate create(String modId) {
        return new CAFRegistrate(modId);
    }

    public static Block getBlock(String name) {
        return CreateAndFood.REGISTRATE.get(name, ForgeRegistries.BLOCKS.getRegistryKey()).get();
    }
    public static Item getItem(String name) {
        return CreateAndFood.REGISTRATE.get(name, ForgeRegistries.ITEMS.getRegistryKey()).get();
    }
    public static Item getBucket(String name) {
        return CreateAndFood.REGISTRATE.get(name + "_bucket", ForgeRegistries.ITEMS.getRegistryKey()).get();
    }

    public static String autoLang(String id) {
        StringBuilder builder = new StringBuilder();
        boolean b = true;
        for (char c: id.toCharArray()) {
            if(c == '_') {
                builder.append(' ');
                b = true;
            } else {
                builder.append(b ? String.valueOf(c).toUpperCase() : c);
                b = false;
            }
        }
        return builder.toString();
    }

    public CAFRegistrate setTooltipModifierFactory(@Nullable Function<Item, TooltipModifier> factory) {
        currentTooltipModifierFactory = factory;
        return self();
    }

    @Nullable
    public Function<Item, TooltipModifier> getTooltipModifierFactory() {
        return currentTooltipModifierFactory;
    }

    @Override
    public CAFRegistrate registerEventListeners(IEventBus bus) {
        return super.registerEventListeners(bus);
    }

    @Override
    protected <R extends IForgeRegistryEntry<R>, T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type, Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator, NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);
        if (type.equals(Registry.ITEM_REGISTRY)) {
            if (currentTooltipModifierFactory != null) {
                TooltipModifier.REGISTRY.registerDeferred(entry.getId(), currentTooltipModifierFactory);
            }
        }
        return entry;
    }

    @Override
    public <T extends BlockEntity> CreateBlockEntityBuilder<T, CAFRegistrate> blockEntity(String name,
                                                                                             BlockEntityBuilder.BlockEntityFactory<T> factory) {
        return blockEntity(self(), name, factory);
    }

    @Override
    public <T extends BlockEntity, P> CreateBlockEntityBuilder<T, P> blockEntity(P parent, String name,
                                                                                 BlockEntityBuilder.BlockEntityFactory<T> factory) {
        return (CreateBlockEntityBuilder<T, P>) entry(name,
                (callback) -> CreateBlockEntityBuilder.create(this, parent, name, callback, factory));
    }

    @Override
    public <T extends Entity> CreateEntityBuilder<T, CAFRegistrate> entity(String name,
                                                                              EntityType.EntityFactory<T> factory, MobCategory classification) {
        return this.entity(self(), name, factory, classification);
    }

    @Override
    public <T extends Entity, P> CreateEntityBuilder<T, P> entity(P parent, String name,
                                                                  EntityType.EntityFactory<T> factory, MobCategory classification) {
        return (CreateEntityBuilder<T, P>) this.entry(name, (callback) -> {
            return CreateEntityBuilder.create(this, parent, name, callback, factory, classification);
        });
    }

    /* Palettes */

    public <T extends Block> BlockBuilder<T, CAFRegistrate> paletteStoneBlock(String name,
                                                                                 NonNullFunction<BlockBehaviour.Properties, T> factory, NonNullSupplier<Block> propertiesFrom, boolean worldGenStone,
                                                                                 boolean hasNaturalVariants) {
        BlockBuilder<T, CAFRegistrate> builder = super.block(name, factory).initialProperties(propertiesFrom)
                .transform(pickaxeOnly())
                .blockstate(hasNaturalVariants ? BlockStateGen.naturalStoneTypeBlock(name) : (c, p) -> {
                    final String location = "block/palettes/stone_types/" + c.getName();
                    p.simpleBlock(c.get(), p.models()
                            .cubeAll(c.getName(), p.modLoc(location)));
                })
                .tag(BlockTags.DRIPSTONE_REPLACEABLE)
                .tag(BlockTags.AZALEA_ROOT_REPLACEABLE)
                .tag(BlockTags.MOSS_REPLACEABLE)
                .tag(BlockTags.LUSH_GROUND_REPLACEABLE)
                .item()
                .model((c, p) -> p.cubeAll(c.getName(),
                        p.modLoc(hasNaturalVariants ? "block/palettes/stone_types/natural/" + name + "_1"
                                : "block/palettes/stone_types/" + c.getName())))
                .build();
        return builder;
    }

    public BlockBuilder<Block, CAFRegistrate> paletteStoneBlock(String name, NonNullSupplier<Block> propertiesFrom,
                                                                   boolean worldGenStone, boolean hasNaturalVariants) {
        return paletteStoneBlock(name, Block::new, propertiesFrom, worldGenStone, hasNaturalVariants);
    }

    /* Fluids */

    public FluidBuilder<VirtualFluid, CAFRegistrate> virtualFluid(String name) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, new ResourceLocation(MOD_ID, "fluid/" + name + "_still"),
                        new ResourceLocation(MOD_ID, "fluid/" + name + "_flow"), null, VirtualFluid::new));
    }

    /* Util */

    public static <T extends Block> NonNullConsumer<? super T> casingConnectivity(
            BiConsumer<T, CasingConnectivity> consumer) {
        return entry -> onClient(() -> () -> registerCasingConnectivity(entry, consumer));
    }

    public static <T extends Block> NonNullConsumer<? super T> blockModel(
            Supplier<NonNullFunction<BakedModel, ? extends BakedModel>> func) {
        return entry -> onClient(() -> () -> registerBlockModel(entry, func));
    }

    public static <T extends Item> NonNullConsumer<? super T> itemModel(
            Supplier<NonNullFunction<BakedModel, ? extends BakedModel>> func) {
        return entry -> onClient(() -> () -> registerItemModel(entry, func));
    }

    public static <T extends Block> NonNullConsumer<? super T> connectedTextures(
            Supplier<ConnectedTextureBehaviour> behavior) {
        return entry -> onClient(() -> () -> registerCTBehviour(entry, behavior));
    }

    protected static void onClient(Supplier<Runnable> toRun) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, toRun);
    }

    @OnlyIn(Dist.CLIENT)
    private static <T extends Block> void registerCasingConnectivity(T entry,
                                                                     BiConsumer<T, CasingConnectivity> consumer) {
        consumer.accept(entry, CreateClient.CASING_CONNECTIVITY);
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerBlockModel(Block entry,
                                           Supplier<NonNullFunction<BakedModel, ? extends BakedModel>> func) {
        CreateClient.MODEL_SWAPPER.getCustomBlockModels()
                .register(RegisteredObjects.getKeyOrThrow(entry), func.get());
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerItemModel(Item entry,
                                          Supplier<NonNullFunction<BakedModel, ? extends BakedModel>> func) {
        CreateClient.MODEL_SWAPPER.getCustomItemModels()
                .register(RegisteredObjects.getKeyOrThrow(entry), func.get());
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerCTBehviour(Block entry, Supplier<ConnectedTextureBehaviour> behaviorSupplier) {
        ConnectedTextureBehaviour behavior = behaviorSupplier.get();
        CreateClient.MODEL_SWAPPER.getCustomBlockModels()
                .register(RegisteredObjects.getKeyOrThrow(entry), model -> new CTModel(model, behavior));
    }
}
