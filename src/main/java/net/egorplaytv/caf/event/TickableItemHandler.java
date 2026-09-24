package net.egorplaytv.caf.event;

import net.egorplaytv.caf.item.custom.interfaces.IMetalItem;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Method;

@Mod.EventBusSubscriber
public class TickableItemHandler {
    private static final Method GET_CHUNKS_METHOD;

    static {
        try {
            GET_CHUNKS_METHOD = ChunkMap.class.getDeclaredMethod("getChunks");
            GET_CHUNKS_METHOD.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find ChunkMap.getChunks()", e);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.world.isClientSide()) {
            return;
        }

        if (!(event.world instanceof ServerLevel serverLevel)) {
            return;
        }

        try {
            ChunkMap chunkMap = serverLevel.getChunkSource().chunkMap;

            @SuppressWarnings("unchecked")
            Iterable<ChunkHolder> chunkHolders =
                    (Iterable<ChunkHolder>) GET_CHUNKS_METHOD.invoke(chunkMap);

            for (ChunkHolder holder : chunkHolders) {
                LevelChunk chunk = holder.getFullChunk(); // null, если чанк ещё не загружен полностью
                if (chunk == null) continue;

                for (BlockEntity be : chunk.getBlockEntities().values()) {
                    if (be instanceof Container container) {
                        boolean changed = false;
                        for (int i = 0; i < container.getContainerSize(); i++) {
                            ItemStack stack = container.getItem(i);
                            if (!stack.isEmpty()
                                    && stack.getItem() instanceof IMetalItem ticking) {
                                ticking.tickInInventory(stack, serverLevel);
                                changed = true;
                            }
                        }
                        if (changed) {
                            be.setChanged();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}