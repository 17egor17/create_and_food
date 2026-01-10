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

public class Scroll extends Item {
    String Scroll;
    String nameOneCount;
    String nameTwoCount;
    String nameTreeCount;
    String nameFourCount;
    String nameFifeCount;
    String nameSixCount;

    public Scroll(Properties pProperties, String Scroll, String OneCount, String TwoCount,
                  String TreeCount, String FourCount, String FifeCount, String SixCount) {
        super(pProperties);
        this.Scroll = Scroll;
        this.nameOneCount = OneCount;
        this.nameTwoCount = TwoCount;
        this.nameTreeCount = TreeCount;
        this.nameFourCount = FourCount;
        this.nameFifeCount = FifeCount;
        this.nameSixCount = SixCount;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll.down"));
            pTooltip.add(new TextComponent(""));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + Scroll));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll.recipe"));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameOneCount));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameTwoCount));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameTreeCount));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameFourCount));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameFifeCount));
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll." + nameSixCount));
        } else {
            pTooltip.add(new TranslatableComponent("tooltip.caf.item.scroll.shift"));
        }
    }
}
