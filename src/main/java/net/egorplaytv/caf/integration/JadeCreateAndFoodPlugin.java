package net.egorplaytv.caf.integration;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.egorplaytv.caf.block.custom.berry.*;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.jade.VanillaPlugin;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.addon.vanilla.VanillaProvider;
import vectorwing.farmersdelight.common.tag.ModTags;

import static net.egorplaytv.caf.util.CAFTags.Blocks.MINEABLE_WITH_HAMMER;
import static snownee.jade.addon.harvest.HarvestToolProvider.registerHandler;
import static vectorwing.farmersdelight.common.registry.ModItems.IRON_KNIFE;

@WailaPlugin
public class JadeCreateAndFoodPlugin implements IWailaPlugin {

    public JadeCreateAndFoodPlugin(){
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider((IComponentProvider) new ToolProvider(), TooltipPosition.BODY, Block.class);
        registration.registerIconProvider(new AgeProvider(), CropBlock.class);
        registration.registerComponentProvider(new AgeProvider(), TooltipPosition.BODY, Block.class);
    }

    public class ToolProvider {
        public ToolProvider(){
            registerHandler(new SimpleToolHandler("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item[]{Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE,
                            Items.NETHERITE_PICKAXE, CAFItems.TANTALUM_PICKAXE.get(), CAFItems.TUNGSTEN_PICKAXE.get()}));

            registerHandler(new SimpleToolHandler("axe", BlockTags.MINEABLE_WITH_AXE,
                    new Item[]{Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE,
                            CAFItems.TANTALUM_AXE.get(), CAFItems.TUNGSTEN_AXE.get()}));

            registerHandler(new SimpleToolHandler("shovel", BlockTags.MINEABLE_WITH_SHOVEL,
                    new Item[]{Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL,
                            Items.NETHERITE_SHOVEL, CAFItems.TANTALUM_SHOVEL.get(), CAFItems.TUNGSTEN_SHOVEL.get()}));

            registerHandler(new SimpleToolHandler("hoe", BlockTags.MINEABLE_WITH_HOE,
                    new Item[]{Items.WOODEN_HOE, Items.STONE_HOE, Items.IRON_HOE, Items.DIAMOND_HOE,
                            Items.NETHERITE_HOE, CAFItems.TANTALUM_HOE.get(), CAFItems.TUNGSTEN_HOE.get()}));


            registerHandler(new SimpleToolHandler("hammer", MINEABLE_WITH_HAMMER,
                    new Item[]{CAFItems.STEEL_HAMMER.get(), CAFItems.TANTALUM_HAMMER.get(), CAFItems.TUNGSTEN_HAMMER.get()}));

            registerHandler(new SimpleToolHandler("knife", ModTags.MINEABLE_WITH_KNIFE, IRON_KNIFE.get()));
        }
    }

    public class AgeProvider extends VanillaProvider {
        public AgeProvider(){
        }
        @OnlyIn(Dist.CLIENT)
        public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
            if (accessor.getTooltipPosition() == TooltipPosition.HEAD) {
                this.appendHead(tooltip, accessor, config);
            } else {
                BlockState state = accessor.getBlockState();
                Block block = state.getBlock();

                if (config.get(VanillaPlugin.CROP_PROGRESS)) {
                    if (state.hasProperty(BlockStateProperties.AGE_5) && (block instanceof WildMelonBushBlock || block instanceof WildPumpkinBushBlock)) {
                        addMaturityTooltip(tooltip, (float) (Integer) state.getValue(BlockStateProperties.AGE_5) / 5.0F);
                    }
                }
            }
        }

        @OnlyIn(Dist.CLIENT)
        private static void addMaturityTooltip(ITooltip tooltip, float growthValue) {
            growthValue *= 100.0F;
            if (growthValue < 100.0F) {
                tooltip.add(new TranslatableComponent("tooltip.waila.crop_growth", new Object[]{String.format("%.0f%%", growthValue)}));
            } else {
                tooltip.add(new TranslatableComponent("tooltip.waila.crop_growth", new Object[]{(new TranslatableComponent("tooltip.waila.crop_mature")).withStyle(ChatFormatting.GREEN)}));
            }

        }
    }
}
