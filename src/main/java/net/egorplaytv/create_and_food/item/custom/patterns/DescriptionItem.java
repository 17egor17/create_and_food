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
import org.stringtemplate.v4.ST;

import java.util.List;

public class DescriptionItem extends Item {
    String ID;

    public DescriptionItem(Properties pProperties, String name) {
        super(pProperties);
        this.ID = name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
            pTooltip.add(new TranslatableComponent("tooltip.create_and_food.desc_item." + ID + ".desc"));
    }
}
