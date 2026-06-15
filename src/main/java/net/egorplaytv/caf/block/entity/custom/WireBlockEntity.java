package net.egorplaytv.caf.block.entity.custom;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.egorplaytv.caf.block.custom.CopperWireBlock;
import net.egorplaytv.caf.energy.EnergyTransportBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class WireBlockEntity extends SmartBlockEntity {
    public WireBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(new StandardWireEnergyTransportBehaviour(this));
        registerAwardables(behaviours);
    }

    class StandardWireEnergyTransportBehaviour extends EnergyTransportBehaviour {

        public StandardWireEnergyTransportBehaviour(SmartBlockEntity be) {
            super(be);
        }

        @Override
        public boolean canHaveFlowToward(BlockState state, Direction direction) {
            return (CopperWireBlock.isWire(state) || state.getBlock() instanceof CopperWireBlock)
                    && state.getValue(CopperWireBlock.PROPERTY_BY_DIRECTION.get(direction));
        }
    }
}
