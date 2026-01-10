package net.egorplaytv.caf.screen.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.gui.UIRenderHelper;
import com.simibubi.create.foundation.gui.element.ScreenElement;
import com.simibubi.create.foundation.utility.Color;
import net.egorplaytv.caf.CreateAndFood;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum CAFGuiTextures implements ScreenElement {
    //inventories
    INVENTORY_LEFT("inventory", 170,105),
    INVENTORY_RIGHT("inventory", 0, 112, 170, 105),
    COPPER_INVENTORY_LEFT("copper_inventory", 170,105),
    COPPER_INVENTORY_RIGHT("copper_inventory", 0, 112, 170, 105),
    STEEL_INVENTORY_LEFT("steel_inventory", 170,105),
    STEEL_INVENTORY_RIGHT("steel_inventory", 0, 112, 170, 105),
    JEI_FRAGILE("widgets", 48, 49, 13, 23)
    ;
    public final ResourceLocation location;
    public final int width, height;
    public final int startX, startY;

    CAFGuiTextures(String location, int x, int y) {
        this(location, 0, 0, x, y);
    }

    CAFGuiTextures(String location, int startX, int startY, int width, int height) {
        this(CreateAndFood.MOD_ID, location, startX, startY, width, height);
    }
    CAFGuiTextures(String namespace, String location, int startX, int startY, int width, int height) {
        this.location = new ResourceLocation(namespace, "textures/gui/" + location + ".png");
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
    }

    @OnlyIn(Dist.CLIENT)
    public void bind() {
        RenderSystem.setShaderTexture(0, location);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(PoseStack ms, int x, int y) {
        bind();
        GuiComponent.blit(ms, x, y, 0, startX, startY, width, height, 256, 256);
    }
    @OnlyIn(Dist.CLIENT)
    public void render(PoseStack ms, int x, int y, GuiComponent component) {
        bind();
        component.blit(ms, x, y, startX, startY, width, height);
    }

    @OnlyIn(Dist.CLIENT)
    public void render(PoseStack ms, int x, int y, Color c) {
        bind();
        UIRenderHelper.drawColoredTexture(ms, c, x, y, startX, startY, width, height);
    }
}
