package net.egorplaytv.create_and_food.integration;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.ui.IDisplayHelper;
import mcp.mobius.waila.api.ui.IElementHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.world.level.block.Block;

@WailaPlugin
public class JadeCreatAndFoodPlugin implements IWailaPlugin {
    public JadeCreatAndFoodPlugin(){
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(new ToolProvider(), TooltipPosition.BODY, Block.class);
    }
}
