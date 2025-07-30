package net.egorplaytv.create_and_food.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.weight.PlayerWeight;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;


public class ItemWeightOverlay implements IIngameOverlay {
    public static final ItemWeightOverlay INSTANCE = new ItemWeightOverlay();

    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;
        Player player = mc.player;
        if (player == null)
            return;
//        if (player.isCreative())
//            return;

        poseStack.pushPose();
        poseStack.translate(width / 2 + 90, height - 53, 0);

        PlayerWeight weight = new PlayerWeight(player);

        int posX = 4;
        int posY = 16;

        if (weight.AllPlayerWeight() > 900 && weight.AllPlayerWeight() < 10000) {
            if (CreateAndFoodCommonConfigs.ENABLE_TONES.get() && weight.AllPlayerWeight() >= 1000000) {
                mc.font.draw(poseStack, new TranslatableComponent("ui.create_and_food.playerWeightItems",
                                new TranslatableComponent("create_and_food.scales_weight_tn",
                                        weight.AllPlayerWeight() / 1000000)),
                        posX, posY, 0xffffff);
            } else if (CreateAndFoodCommonConfigs.ENABLE_KILOGRAMS.get() && weight.AllPlayerWeight() >= 1000) {
                mc.font.draw(poseStack, new TranslatableComponent("ui.create_and_food.playerWeightItems",
                                new TranslatableComponent("create_and_food.scales_weight_kg",
                                        weight.AllPlayerWeight() / 1000)),
                        posX, posY, 0xffffff);
            } else if (CreateAndFoodCommonConfigs.ENABLE_GRAMS.get()) {
                mc.font.draw(poseStack, new TranslatableComponent("ui.create_and_food.playerWeightItems",
                                new TranslatableComponent("create_and_food.scales_weight_g",
                                        weight.AllPlayerWeight())),
                        posX, posY, 0xffffff);
            } else {
                mc.font.draw(poseStack, new TranslatableComponent("ui.create_and_food.playerWeightItems",
                                new TranslatableComponent("create_and_food.scales_weight_kcaf",
                                        ((weight.AllPlayerWeight() * 3.14) / 10) / 1000)),
                        posX, posY, 0xffffff);
            }
        }
        poseStack.popPose();
    }
}
