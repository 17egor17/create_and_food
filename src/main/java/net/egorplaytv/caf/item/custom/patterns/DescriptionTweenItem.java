package net.egorplaytv.caf.item.custom.patterns;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescriptionTweenItem extends Item {
    String ID;

    public DescriptionTweenItem(Properties pProperties, String name) {
        super(pProperties);
        this.ID = name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()){
            pTooltip.add(new TranslatableComponent("tooltip.caf.shift_down"));
            pTooltip.add(new TextComponent(" "));
            pTooltip.add(new TranslatableComponent("tooltip.caf.desc_item." + ID + ".shift_desc"));
        } else {
            pTooltip.add(new TranslatableComponent("tooltip.caf.shift"));
        }
        if (Screen.hasControlDown()) {
            pTooltip.add(new TranslatableComponent("tooltip.caf.control_down"));
            pTooltip.add(new TextComponent(" "));
            pTooltip.add(new TranslatableComponent("tooltip.caf.desc_item." + ID + ".control_desc"));
        } else {
            pTooltip.add(new TranslatableComponent("tooltip.caf.control"));
        }
    }
}
