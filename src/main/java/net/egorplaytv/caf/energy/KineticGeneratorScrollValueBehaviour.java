package net.egorplaytv.caf.energy;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBoard;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsFormatter;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import net.egorplaytv.caf.util.Lang;
import net.egorplaytv.caf.util.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

public class KineticGeneratorScrollValueBehaviour extends ScrollValueBehaviour {
    public KineticGeneratorScrollValueBehaviour(Component label, SmartBlockEntity be, ValueBoxTransform slot) {
        super(label, be, slot);
        withFormatter(v -> String.valueOf(Math.abs(v)));
    }

    @Override
    public ValueSettingsBoard createBoard(Player player, BlockHitResult hitResult) {
        ImmutableList<Component> rows = ImmutableList.of(TextUtils.getUnitsTranslation("dimperage"),
                TextUtils.getUnitsTranslation("dimperage"));
        ValueSettingsFormatter formatter = new ValueSettingsFormatter(this::formatSettings);
        return new ValueSettingsBoard(label, 500, 32, rows, formatter);
    }

    @Override
    public void setValueSettings(Player player, ValueSettings valueSetting, boolean ctrlDown) {
        int value = valueSetting.value();
        int multiplier = switch (valueSetting.row()) {
            case 0 -> 1;
            default -> 5;
        };
        if (!valueSetting.equals(getValueSettings()))
            playFeedbackSound(this);
        setValue(Math.max(1, Math.max(1, value) * multiplier));
    }

    @Override
    public ValueSettings getValueSettings() {
        int row = 0;
        int value = this.value;

        if (value > 500) {
            value = value / 5;
            row = 1;
        }
        return new ValueSettings(row, value);
    }

    public MutableComponent formatSettings(ValueSettings settings) {
        return Lang.number( switch (settings.row()) {
                    case 0 -> Math.max(1, Math.abs(settings.value()));
                    default -> Math.max(1, Math.abs(settings.value() * 5));
                        })
                .add(Lang.text("\u26A1").style(ChatFormatting.BOLD))
                .component();
    }

    @Override
    public String getClipboardKey() {
        return "Kinetic Generator Amperage";
    }
}
