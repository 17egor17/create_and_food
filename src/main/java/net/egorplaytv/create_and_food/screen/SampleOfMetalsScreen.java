package net.egorplaytv.create_and_food.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.block.entity.custom.TabletBlockEntity;
import net.egorplaytv.create_and_food.screen.gui.ModGuiTextures;
import net.egorplaytv.create_and_food.util.ModTags;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

public class SampleOfMetalsScreen extends AbstractContainerScreen<SampleOfMetalsMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CreateAndFood.MOD_ID, "textures/gui/sample_of_metals.png");

    public SampleOfMetalsScreen(SampleOfMetalsMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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

        this.blit(pPoseStack, x, y,0,0, 221, 157);

        ModGuiTextures.INVENTORY_RIGHT.render(pPoseStack, x + 67, y + 131);
    }

    public void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.font.draw(poseStack, ModBlocks.TABLET.get().getName(), 38, 9, 0xFFffffff);
        this.font.draw(poseStack, new TranslatableComponent("ui.inventory"), 165, 134, 0xFF808080);
        if (menu.isItem()) {
            this.font.draw(poseStack, menu.blockEntity.getItemName(), 38, 21, 0xFF808080);
            String item = "-";
            if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.ALUMINUM)) {
                item = "aluminum";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.BRASS)) {
                item = "brass";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.BRONZE)) {
                item = "bronze";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.CONSTANTAN)) {
                item = "constantan";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(Tags.Items.INGOTS_COPPER)) {
                item = "copper";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.ELECTRUM)) {
                item = "electrum";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.ENDERIUM)){
                item = "enderium";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(Tags.Items.INGOTS_GOLD)) {
                item = "gold";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.INVAR)) {
                item = "invar";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(Tags.Items.INGOTS_IRON)) {
                item = "iron";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.LEAD)) {
                item = "lead";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.LUMIUM)) {
                item = "lumium";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(Tags.Items.INGOTS_NETHERITE)) {
                item = "netherite";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.NICKEL)) {
                item = "nickel";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.OSMIUM)) {
                item = "osmium";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.SIGNALUM)) {
                item = "signalum";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.SILVER)) {
                item = "silver";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.STEEL)) {
                item = "steel";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.TIN)) {
                item = "tin";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.TANTALUM)) {
                item = "tantalum";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.TUNGSTEN)) {
                item = "tungsten";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.URANIUM)) {
                item = "uranium";
            } else if (menu.blockEntity.itemHandler.getStackInSlot(0).is(ModTags.Items.ZINC)) {
                item = "zinc";
            }

            if (!item.equals("-")){
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.1"),
                        16, 41, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.2"),
                        16, 51, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.3"),
                        16, 61, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.4"),
                        16, 71, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.5"),
                        16, 81, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.6"),
                        16, 91, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.7"),
                        16, 101, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.8"),
                        16, 111, 0xFF808080);
                this.font.draw(poseStack,
                        new TranslatableComponent("create_and_food.sample." + item + ".desc.line.9"),
                        16, 121, 0xFF808080);
            }
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
