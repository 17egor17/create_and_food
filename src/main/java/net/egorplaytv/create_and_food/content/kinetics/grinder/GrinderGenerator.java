package net.egorplaytv.create_and_food.content.kinetics.grinder;

import com.simibubi.create.content.processing.basin.BasinBlock;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.egorplaytv.create_and_food.block.custom.GrinderBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;

public class GrinderGenerator extends SpecialBlockStateGen {

    @Override
    protected int getXRotation(BlockState state) {
        return 0;
    }

    @Override
    protected int getYRotation(BlockState state) {
        return horizontalAngle(state.getValue(GrinderBlock.HORIZONTAL_FACING));
    }

    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                BlockState state) {
        String path = "block/" + ctx.getName() + "/";

        return prov.models()
                .getExistingFile(prov.modLoc(path + "block"));
    }

}