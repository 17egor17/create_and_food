package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.block.custom.FloodedFarmlandBlock;
import net.egorplaytv.caf.block.custom.FloodedRichSoilFarmlandBlock;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class RiceItem extends ItemNameBlockItem {
    public RiceItem(Block blockIn, Item.Properties properties) {
        super(blockIn, properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = this.place(new BlockPlaceContext(context));
        if (result.equals(InteractionResult.FAIL)) {
            Player player = context.getPlayer();
            BlockState targetState = context.getLevel().getBlockState(context.getClickedPos());
            if (player != null && context.getClickedFace().equals(Direction.UP)
                    && (targetState.is(BlockTags.DIRT) || targetState.getBlock() instanceof FarmBlock)
                    || (targetState.getBlock() instanceof FloodedFarmlandBlock || targetState.getBlock() instanceof FloodedRichSoilFarmlandBlock)) {
                if (targetState.getBlock() instanceof FloodedFarmlandBlock || targetState.getBlock() instanceof FloodedRichSoilFarmlandBlock){
                    if (!(targetState.getValue(FloodedFarmlandBlock.WATERLOGGED) || targetState.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED))){
                        player.displayClientMessage(TextUtils.getTranslation("block.rice.invalid_placement"), true);
                    } else {
                        result = InteractionResult.sidedSuccess(targetState.getValue(FloodedFarmlandBlock.WATERLOGGED)
                                || targetState.getValue(FloodedRichSoilFarmlandBlock.WATERLOGGED));
                    }
                } else {
                    player.displayClientMessage(TextUtils.getTranslation("block.rice.invalid_placement"), true);
                }
            }
        }

        return !result.consumesAction() && this.isEdible() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : result;
    }
}