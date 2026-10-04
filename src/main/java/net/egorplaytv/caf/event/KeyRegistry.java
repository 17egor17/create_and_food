package net.egorplaytv.caf.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Random;

public class KeyRegistry extends SavedData {
    private static final String DATA_NAME = "caf_keys";

    private String nextId;

    public KeyRegistry() {
        Random randomFirst = new Random();
        StringBuilder sbFirst = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int digit = randomFirst.nextInt(10);
            sbFirst.append(digit);
        }
        String firstId = sbFirst.toString();
        Random randomSecond = new Random();
        StringBuilder sbSecond = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int digit = randomSecond.nextInt(10);
            sbSecond.append(digit);
        }
        String secondId = sbSecond.toString();
        this.nextId = firstId + "-" + secondId;
    }

    private KeyRegistry(String nextId) {
        this.nextId = nextId;
    }

    public static KeyRegistry get(Level level) {
        ServerLevel storage = storageLevel(level);
        return storage.getDataStorage()
                .computeIfAbsent(KeyRegistry::fromTag, KeyRegistry::new, DATA_NAME);
    }

    private static ServerLevel storageLevel(Level level) {
        if (!(level instanceof ServerLevel serverLevel)) {
            throw new IllegalStateException("KeyRegistry.get() is called on the client side");
        }
        MinecraftServer server = serverLevel.getServer();
        ServerLevel overworld = server.overworld();
        return overworld != null ? overworld : serverLevel;
    }

    public String allocate() {
        String id = nextId;
        Random randomFirst = new Random();
        StringBuilder sbFirst = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int digit = randomFirst.nextInt(10);
            sbFirst.append(digit);
        }
        String firstId = sbFirst.toString();

        Random randomSecond = new Random();
        StringBuilder sbSecond = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int digit = randomSecond.nextInt(10);
            sbSecond.append(digit);
        }
        String secondId = sbSecond.toString();
        nextId = firstId + "-" + secondId;

        setDirty();
        return id;
    }

    public String peek() { return nextId; }

    private static KeyRegistry fromTag(CompoundTag tag) {
        return new KeyRegistry(tag.getString("nextId"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putString("nextId", nextId);
        return tag;
    }
}
