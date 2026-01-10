package net.egorplaytv.caf.item;

import net.egorplaytv.caf.item.entity.custom.MIEntity;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;

public class ItemEntities {
    private static final Map<ResourceLocation, EntityType<?>> ENTITY_TYPES = new HashMap<>();

    public static Map<ResourceLocation, EntityType<?>> getEntityTypes() {
        return Collections.unmodifiableMap(ENTITY_TYPES);
    }

    public static EntityType<MIEntity> MELT_ITEM = create("melt_item",
            MIEntity::new,
            MobCategory.MISC,
            builder -> builder.sized(0.25F, 0.4F));


    private static <T extends Entity> EntityType<T> create(String id,
                                                           EntityType.EntityFactory<T> entityFactory,
                                                           MobCategory classification,
                                                           Consumer<EntityType.Builder<T>> customizer) {
        String registryLoc = MOD_ID + ":" + id;
        EntityType.Builder<T> builder = EntityType.Builder.of(entityFactory, classification);
        customizer.accept(builder);
        boolean prev = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
        EntityType<T> result = builder.build(registryLoc);
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = prev;
        ENTITY_TYPES.put(new ResourceLocation(registryLoc), result);
        return result;
    }


    public static void init(IForgeRegistry<EntityType<?>> registry) {
        for (var entry : ItemEntities.getEntityTypes().entrySet()) {
            var id = entry.getKey();
            var entityType = entry.getValue();
            entityType.setRegistryName(id);
            registry.register(entityType);
        }
    }
}
