package net.egorplaytv.caf.energy;

import net.minecraft.core.BlockPos;

public class EnergyPacket {
    public final int amount;
    public final BlockPos sourcePos;
    public final float ttl;

    public EnergyPacket(int amount, BlockPos sourcePos, float ttl) {
        this.amount = amount;
        this.sourcePos = sourcePos;
        this.ttl = ttl;
    }

    public boolean isExpired() {
        return ttl <= 0;
    }
}
