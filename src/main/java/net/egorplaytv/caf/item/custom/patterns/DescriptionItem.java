package net.egorplaytv.caf.item.custom.patterns;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescriptionItem extends Item {
    String ID;

    public DescriptionItem(Properties pProperties, String name) {
        super(pProperties);
        this.ID = name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
            pTooltip.add(new TranslatableComponent("tooltip.caf.desc_item." + ID + ".desc"));
    }
}
