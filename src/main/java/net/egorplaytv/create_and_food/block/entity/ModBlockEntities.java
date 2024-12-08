package net.egorplaytv.create_and_food.block.entity;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.custom.*;
import net.egorplaytv.create_and_food.block.entity.renderer.SlidingDoorRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CreateAndFood.MOD_ID);
    public static final RegistryObject<BlockEntityType<FermentationBarrelBlockEntity>> FERMENTATION_BARREL_ENTITY =
            BLOCK_ENTITIES.register("fermentation_barrel_entity", () ->
                    BlockEntityType.Builder.of(FermentationBarrelBlockEntity::new,
                            ModBlocks.FERMENTATION_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<MarbleBlastFurnaceBlockEntity>> MARBLE_BLAST_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("marble_blast_furnace_entity", () ->
                    BlockEntityType.Builder.of(MarbleBlastFurnaceBlockEntity::new,
                            ModBlocks.MARBLE_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TabletBlockEntity>> TABLET_ENTITY =
            BLOCK_ENTITIES.register("tablet_entity", () ->
                    BlockEntityType.Builder.of(TabletBlockEntity::new,
                            ModBlocks.TABLET.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.ALMOND_WALL_SIGN.get(),
                            ModBlocks.ALMOND_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<OakCuttingBoardBlockEntity>> OAK_CUTTING_BOARD =
            BLOCK_ENTITIES.register("oak_cutting_board", () -> {
                    return BlockEntityType.Builder.of(OakCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.OAK_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<SpruceCuttingBoardBlockEntity>> SPRUCE_CUTTING_BOARD =
            BLOCK_ENTITIES.register("spruce_cutting_board", () -> {
                    return BlockEntityType.Builder.of(SpruceCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.SPRUCE_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<BirchCuttingBoardBlockEntity>> BIRCH_CUTTING_BOARD =
            BLOCK_ENTITIES.register("birch_cutting_board", () -> {
                    return BlockEntityType.Builder.of(BirchCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.BIRCH_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<JungleCuttingBoardBlockEntity>> JUNGLE_CUTTING_BOARD =
            BLOCK_ENTITIES.register("jungle_cutting_board", () -> {
                    return BlockEntityType.Builder.of(JungleCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.JUNGLE_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<AcaciaCuttingBoardBlockEntity>> ACACIA_CUTTING_BOARD =
            BLOCK_ENTITIES.register("acacia_cutting_board", () -> {
                    return BlockEntityType.Builder.of(AcaciaCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.ACACIA_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<DarkOakCuttingBoardBlockEntity>> DARK_OAK_CUTTING_BOARD =
            BLOCK_ENTITIES.register("dark_oak_cutting_board", () -> {
                    return BlockEntityType.Builder.of(DarkOakCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.DARK_OAK_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<CrimsonCuttingBoardBlockEntity>> CRIMSON_CUTTING_BOARD =
            BLOCK_ENTITIES.register("crimson_cutting_board", () -> {
                    return BlockEntityType.Builder.of(CrimsonCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.CRIMSON_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<WarpedCuttingBoardBlockEntity>> WARPED_CUTTING_BOARD =
            BLOCK_ENTITIES.register("warped_cutting_board", () -> {
                    return BlockEntityType.Builder.of(WarpedCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.WARPED_CUTTING_BOARD.get()}).build(null);
            });
    public static final RegistryObject<BlockEntityType<AlmondCuttingBoardBlockEntity>> ALMOND_CUTTING_BOARD =
            BLOCK_ENTITIES.register("almond_cutting_board", () -> {
                    return BlockEntityType.Builder.of(AlmondCuttingBoardBlockEntity::new,
                            new Block[]{(Block)ModBlocks.ALMOND_CUTTING_BOARD.get()}).build(null);
            });

    public static final BlockEntityEntry<SlidingDoorBlockEntity> SLIDING_DOOR =
    CreateAndFood.REGISTRATE.blockEntity("sliding_door", SlidingDoorBlockEntity::new)
                    .renderer(() -> SlidingDoorRenderer::new)
                    .validBlock(ModBlocks.STEEL_DOOR)
                    .register();


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
