package net.egorplaytv.caf;

import net.egorplaytv.caf.item.ItemEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class InitEntityRendering {

    public static void init(RendererConsumer consumer) {
        consumer.register(ItemEntities.MELT_ITEM, ItemEntityRenderer::new);
    }

    @FunctionalInterface
    public interface RendererConsumer {
        <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider);
    }
}
