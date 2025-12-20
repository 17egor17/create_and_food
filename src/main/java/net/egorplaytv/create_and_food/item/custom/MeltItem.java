package net.egorplaytv.create_and_food.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MeltItem extends Item {
    int degrees;
    public MeltItem(int degrees ,Properties pProperties) {
        super(pProperties);
        this.degrees = degrees;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        pTooltip.add(new TranslatableComponent("tooltip.create_and_food.degrees", degrees));
    }
}
