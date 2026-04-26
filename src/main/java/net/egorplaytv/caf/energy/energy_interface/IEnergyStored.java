package net.egorplaytv.caf.energy.energy_interface;

import net.egorplaytv.caf.energy.EnergyPacket;

import org.jetbrains.annotations.Nullable;

public interface IEnergyStored {
    /**
     * Adds energy to the storage. Returns quantity of energy that was accepted.
     *
     * @param packet
     *            An energy packet that can be injected.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    int receiveEnergyPacket(EnergyPacket packet, boolean simulate);

    /**
     * Removes energy from the storage. Returns quantity of energy that was removed.
     *
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return An energy packet that was (or would have been, if simulated) extracted from storage.
     */
    @Nullable EnergyPacket extractEnergyPacket(int maxExtract, boolean simulate, float ttl);
}
