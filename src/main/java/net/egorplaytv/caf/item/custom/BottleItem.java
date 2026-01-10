package net.egorplaytv.caf.item.custom;

import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BottleItem extends BlockItem {
    private final String content;
    private final String id;


    public BottleItem(String content, Block bottelBlock, Properties pProperties) {
        super(bottelBlock, pProperties);
        this.content = content;
        this.id = this.getRegistryName().getPath();
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextUtils.getToolTipTranslation(id + content));
    }

    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}
