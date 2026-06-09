package net.egorplaytv.caf.block.custom.berry;

import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class NetherBushBlock extends BerryBushBlock {
    private final Type type;
    public NetherBushBlock(Type type, Properties pProperties) {
        super(pProperties);
        this.type = type;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return CAFItems.NETHER_FRUITLIGHT.get().getDefaultInstance();
    }

    @Override
    public ItemStack getSapling() {
        return switch (type) {
            case WARPED -> CAFItems.WARPED_FRUITLIGHT_SAPLING.get().getDefaultInstance();
            case CRIMSON -> CAFItems.CRIMSON_FRUITLIGHT_SAPLING.get().getDefaultInstance();
        };
    }

    @Override
    public ItemStack getBerry() {
        return CAFItems.NETHER_FRUITLIGHT.get().getDefaultInstance();
    }

    @Override
    public boolean isNether() {
        return true;
    }

    public enum Type {
        WARPED, CRIMSON;
    }
}
