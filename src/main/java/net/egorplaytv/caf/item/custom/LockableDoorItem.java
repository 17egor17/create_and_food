package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.block.custom.LockableDoorBlock;
import net.egorplaytv.caf.data.KeyData;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LockableDoorItem extends DoubleHighBlockItem {
    public LockableDoorItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        String id = KeyData.readDoorKeyId(pStack);

        if (!id.equals(KeyData.UNBOUND)) {
            pTooltip.add(TextUtils.getToolTipTranslation("door.id", new TextComponent(id)
                    .withStyle(ChatFormatting.AQUA)));
        } else {
            pTooltip.add(TextUtils.getToolTipTranslation("door.unbound"));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = super.useOn(context);
        Level level = context.getLevel();
        if (level.isClientSide())
            return result;

        ItemStack stack = context.getItemInHand();
        String id = KeyData.readDoorKeyId(stack);
        if (id.equals(KeyData.UNBOUND))
            return result;

        BlockPos clicked = context.getClickedPos();
        BlockPos placed = level.getBlockState(clicked).getBlock() instanceof LockableDoorBlock
                ? clicked : clicked.relative(context.getClickedFace());
        if (level.getBlockState(placed).getBlock() instanceof LockableDoorBlock door) {
            door.writeBoth(level, placed, id);
        }
        return result;

    }
}
