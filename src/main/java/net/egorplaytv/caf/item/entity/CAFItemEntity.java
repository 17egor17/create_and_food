package net.egorplaytv.caf.item.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public abstract class CAFItemEntity extends ItemEntity {
    public CAFItemEntity(EntityType<? extends ItemEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected CAFItemEntity(EntityType<? extends CAFItemEntity> entityType, Level level, double x,
                               double y, double z, ItemStack stack) {
        this(entityType, level);
        this.setPos(x, y, z);
        this.setYRot(this.random.nextFloat() * 360.0F);
        this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
        this.setItem(stack);
        this.lifespan = stack.getEntityLifespan(level);
    }

    protected List<Entity> getCheckedEntitiesWithinAABBExcludingEntity(AABB region) {
        return this.level.getEntities(this, region);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
