package net.egorplaytv.caf.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.screen.gui.CAFGuiTextures;
import net.egorplaytv.caf.screen.renderer.FluidTankRenderer;
import net.egorplaytv.caf.util.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.Optional;

public class FermentationBarrelScreen extends AbstractContainerScreen<FermentationBarrelMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/fermentation_barrel.png");
    private static final ResourceLocation WIDGETS =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/widgets.png");

    private FluidTankRenderer renderer;

    public FermentationBarrelScreen(FermentationBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assingFluidRenderer();
    }

    private void assingFluidRenderer() {
        renderer = new FluidTankRenderer(4000, true, 16, 56);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y,0,0, 175, 127);

//        blit(pPoseStack, x + 126, y + 17, 235, 1, 20, 60);

        renderer.render(pPoseStack, x + 32, y + 19, menu.getFluidStack());

        renderer.render(pPoseStack, x + 128, y + 19, menu.getFluidStackOut());

        CAFGuiTextures.COPPER_INVENTORY_LEFT.render(pPoseStack, x + 24, y + 83);

        RenderSystem.setShaderTexture(0, WIDGETS);
        blit(pPoseStack, x + 32, y + 19, 0, 49, 16, 56);
        blit(pPoseStack, x + 128, y + 19, 16, 49, 16, 56);
        if (menu.isCrafting()) {
            blit(pPoseStack, x + 75, y + 40, 177, 0, menu.getScaledProgress(), 12);
        }
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.font.draw(poseStack, CAFBlocks.FERMENTATION_BARREL.get().getName(), 3, 4, 0xFF606060);
        this.font.draw(poseStack, new TranslatableComponent("ui.inventory"), 28, 86, 0xFF606060);

        renderFluidAreaInputTooltips(poseStack, mouseX, mouseY, x, y);
        renderFluidAreaOutputTooltips(poseStack, mouseX, mouseY, x, y);
    }

    private void renderFluidAreaInputTooltips(PoseStack poseStack, int mouseX, int mouseY, int x, int y) {
        if (isMouseAboveArea(mouseX, mouseY, x, y, 32, 19)) {
            renderTooltip(poseStack, renderer.getTooltip(menu.getFluidStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), mouseX - x, mouseY - y);
        }
    }
    private void renderFluidAreaOutputTooltips(PoseStack poseStack, int mouseX, int mouseY, int x, int y) {
        if (isMouseAboveArea(mouseX, mouseY, x, y, 128, 19)) {
            renderTooltip(poseStack, renderer.getTooltip(menu.getFluidStackOut(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), mouseX - x, mouseY - y);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}
