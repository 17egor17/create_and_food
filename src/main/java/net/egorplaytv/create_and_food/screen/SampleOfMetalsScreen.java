package net.egorplaytv.create_and_food.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.egorplaytv.create_and_food.block.CAFBlocks;
import net.egorplaytv.create_and_food.screen.gui.CAFGuiTextures;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;
import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.*;

public class SampleOfMetalsScreen extends AbstractContainerScreen<SampleOfMetalsMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MOD_ID, "textures/gui/sample_of_metals.png");

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

        this.blit(pPoseStack, x, y,0,0, 237, 172);

        CAFGuiTextures.INVENTORY_RIGHT.render(pPoseStack, x + 67, y + 131);
    }

    public void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.font.draw(poseStack, CAFBlocks.TERMINAL.get().getName(), 38, 9, 0xFFffffff);
        this.font.draw(poseStack, new TranslatableComponent("ui.inventory"), 165, 134, 0xFF808080);
        if (menu.isItem()) {
            this.font.draw(poseStack, menu.blockEntity.getItemName(), 38, 21, 0xFF808080);
            Item item = menu.blockEntity.itemHandler.getStackInSlot(0).getItem();
            int ingredientLines = getIngredientsLines().get(item) == null ? 1 : getIngredientsLines().get(item);
            String tag = getTags().get(item);
            String type = getTypes().get(item) == null ? "unknown" : getTypes().get(item);
            String modId = getModId() == null ? MOD_ID : getModId();

            String itemOrTag = tag == null ? item.getRegistryName().getPath() : tag;

            if (item != null && getMetals().get(item) != null && type != null && modId != null) {
                if (type.equals("metal")){
                    if (getMetals().get(item) >= 1) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.1"),
                                16, 41, 0xFF808080);
                    }
                    if (getMetals().get(item) >= 2) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.2"),
                                16, 51, 0xFF808080);
                    }
                    if (getMetals().get(item) >= 3) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.3",
                                        new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.1")),
                                16, 61, 0xFF808080);
                    }
                    if (getMetals().get(item) >= 4) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.4",
                                        new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.2")),
                                16, 71, 0xFF808080);
                    }
                    if (getMetals().get(item) >= 5) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.5",
                                        new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.3")),
                                16, 81, 0xFF808080);
                    }
                    if (getMetals().get(item) >= 6) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.6",
                                        new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.4")),
                                16, 91, 0xFF808080);
                    }
                    if (getMetals().get(item) == 7) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.7",
                                        new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.5")),
                                16, 101, 0xFF808080);
                    }

                }

                if (type.equals("alloy")){
                    if (getMetals().get(item) >= 1) {
                        this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.1"),
                                16, 41, 0xFF808080);
                    }
                    if (ingredientLines == 1) {
                        if (getMetals().get(item) >= 2) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.2",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.1")),
                                    16, 51, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 3) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.3",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.2")),
                                    16, 61, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 4) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.4",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.3")),
                                    16, 71, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 5) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.5",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.4")),
                                    16, 81, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 6) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.6"),
                                    16, 91, 0xFF808080);
                        }
                        if (getMetals().get(item) == 7) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.7",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.5")),
                                    16, 101, 0xFF808080);
                        }
                    } else if (ingredientLines == 2){
                        if (getMetals().get(item) >= 2) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.2",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.1_1")),
                                    16, 51, 0xFF808080);
                            this.font.draw(poseStack, new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.1_2"),
                                    16, 61, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 3) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.3",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.2")),
                                    16, 71, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 4) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.4",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.3")),
                                    16, 81, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 5) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.5",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.4")),
                                    16, 91, 0xFF808080);
                        }
                        if (getMetals().get(item) >= 6) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.6"),
                                    16, 101, 0xFF808080);
                        }
                        if (getMetals().get(item) == 7) {
                            this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.alloy.line.7",
                                            new TranslatableComponent(modId + ".sample." + itemOrTag + ".desc.line.5")),
                                    16, 111, 0xFF808080);
                        }
                    }
                }

            } else if (item != null && type != null) {
                if (type.equals("unknown")) {
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.unknown.desc"),
                            16, 41, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent( "create_and_food.sample.alloy.line.2",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 51, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.3",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 61, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.4",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 71, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.5",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 81, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.6",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 91, 0xFF808080);
                    this.font.draw(poseStack, new TranslatableComponent("create_and_food.sample.metal.line.7",
                                    new TranslatableComponent("create_and_food.sample.unknown.desc")),
                            16, 101, 0xFF808080);
                }
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
