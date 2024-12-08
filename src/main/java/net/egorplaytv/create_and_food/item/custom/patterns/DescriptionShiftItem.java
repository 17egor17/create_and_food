package net.egorplaytv.create_and_food.item.custom.patterns;

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

public class DescriptionShiftItem extends Item {
    String ID;

    public DescriptionShiftItem(Properties pProperties, String name) {
        super(pProperties);
        this.ID = name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()){
            pTooltip.add(new TranslatableComponent("tooltip.create_and_food.shift_down"));
            pTooltip.add(new TextComponent(" "));
            pTooltip.add(new TranslatableComponent("tooltip.create_and_food.desc_item." + ID + ".shift_desc"));
        } else {
            pTooltip.add(new TranslatableComponent("tooltip.create_and_food.shift"));
        }
    }
}
