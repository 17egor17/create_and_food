package net.egorplaytv.caf.data;

import net.minecraft.world.item.ItemStack;

public final class KeyData {
    public static final String UNBOUND = "";
    public static final String NO_KEY = UNBOUND;

    private static final String TAG_KEY_ID = "keyId";

    private KeyData() {}

    public static boolean hasNumber(ItemStack stack) {
        return !stack.isEmpty() && stack.getTag() != null
                && stack.getTag().contains(TAG_KEY_ID);
    }

    public static String readKeyId(ItemStack stack) {
        if (stack.isEmpty() || stack.getTag() == null) return UNBOUND;
        return stack.getTag().getString(TAG_KEY_ID);
    }

    public static void writeKeyId(ItemStack stack, String id) {
        stack.getOrCreateTag().putString(TAG_KEY_ID, id);
    }

    public static ItemStack copyWithNumber(ItemStack key) {
        return key.copy();
    }
}