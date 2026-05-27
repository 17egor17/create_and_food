package net.egorplaytv.caf.block.custom.combi_steamer;

import net.egorplaytv.caf.block.entity.custom.combi_steamer.CombiSteamerBlockEntity;
import net.egorplaytv.caf.block.pattern.CombiSteamerBaseBlock;
import net.egorplaytv.caf.block.pattern.interfaces.ICommunicationBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CombiSteamerEnergyBlock extends CombiSteamerBaseBlock implements ICommunicationBlock {
    public CombiSteamerEnergyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnergyCommunication() {
        return true;
    }

    @Override
    public boolean isFluidCommunication() {
        return false;
    }

    @Override
    public boolean isController() {
        return false;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (getComplete(pState))
            return new CombiSteamerBlockEntity(pPos, pState);
        return null;
    }
}
