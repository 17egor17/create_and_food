package net.egorplaytv.caf.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.screen.gui.CAFGuiTextures;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MarbleBlastFurnaceScreen extends AbstractContainerScreen<MarbleBlastFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/marble_furnace.png");
    private static final ResourceLocation WIDGETS =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");

    public MarbleBlastFurnaceScreen(MarbleBlastFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y,0,0, 167, 153);

        CAFGuiTextures.STEEL_INVENTORY_LEFT.render(pPoseStack,x + 59, y + 72);

        RenderSystem.setShaderTexture(0, WIDGETS);
        if (menu.isCrafting()) {
            this.blit(pPoseStack, x + 57, y + 43, 225, 3, menu.getScaledProgress(), 10);
        }
        if (menu.isDegrees()) {
            this.blit(pPoseStack, x + 5, y + 144, 177, 35, menu.getDegrees(), 5);
        }
    }


    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.font.draw(poseStack, CAFBlocks.MARBLE_BLAST_FURNACE.get().getName(), 3, 4, 0xFFffffff);
        this.font.draw(poseStack, new TranslatableComponent("ui.inventory"), 63, 75, 0xFF808080);
        this.font.draw(poseStack, menu.getDegreeProgress(), 5, 134, 0xFF606060);
        if (menu.isDeg()) {
            this.font.draw(poseStack, menu.getDegProgress(), 46, 57, 0xFF8b8b8b);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
