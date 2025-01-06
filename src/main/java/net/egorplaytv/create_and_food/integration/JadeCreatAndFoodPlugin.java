package net.egorplaytv.create_and_food.integration;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.ui.IDisplayHelper;
import mcp.mobius.waila.api.ui.IElementHelper;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.SimpleToolHandler;
import vectorwing.farmersdelight.common.tag.ModTags;

import static net.egorplaytv.create_and_food.util.ModTags.Blocks.MINEABLE_WITH_HAMMER;
import static vectorwing.farmersdelight.common.registry.ModItems.IRON_KNIFE;

@WailaPlugin
public class JadeCreatAndFoodPlugin implements IWailaPlugin {
    public JadeCreatAndFoodPlugin(){
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(new ToolProvider(), TooltipPosition.BODY, Block.class);
    }

    public class ToolProvider extends HarvestToolProvider {
        public ToolProvider(){
            registerHandler(new SimpleToolHandler("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item[]{Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE,
                            Items.NETHERITE_PICKAXE, ModItems.TANTALUM_PICKAXE.get(), ModItems.TUNGSTEN_PICKAXE.get()}));

            registerHandler(new SimpleToolHandler("axe", BlockTags.MINEABLE_WITH_AXE,
                    new Item[]{Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE,
                            ModItems.TANTALUM_AXE.get(), ModItems.TUNGSTEN_AXE.get()}));

            registerHandler(new SimpleToolHandler("shovel", BlockTags.MINEABLE_WITH_SHOVEL,
                    new Item[]{Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL,
                            Items.NETHERITE_SHOVEL, ModItems.TANTALUM_SHOVEL.get(), ModItems.TUNGSTEN_SHOVEL.get()}));

            registerHandler(new SimpleToolHandler("hoe", BlockTags.MINEABLE_WITH_HOE,
                    new Item[]{Items.WOODEN_HOE, Items.STONE_HOE, Items.IRON_HOE, Items.DIAMOND_HOE,
                            Items.NETHERITE_HOE, ModItems.TANTALUM_HOE.get(), ModItems.TUNGSTEN_HOE.get()}));


            registerHandler(new SimpleToolHandler("hammer", MINEABLE_WITH_HAMMER,
                    new Item[]{ModItems.STEEL_HAMMER.get(), ModItems.TANTALUM_HAMMER.get(), ModItems.TUNGSTEN_HAMMER.get()}));

            registerHandler(new SimpleToolHandler("knife", ModTags.MINEABLE_WITH_KNIFE, IRON_KNIFE.get()));
        }
    }
}
