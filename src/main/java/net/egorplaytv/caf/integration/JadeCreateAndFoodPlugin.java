package net.egorplaytv.caf.integration;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.config.IPluginConfig;
import mcp.mobius.waila.api.ui.IElementHelper;
import mcp.mobius.waila.api.ui.IProgressStyle;
import net.egorplaytv.caf.block.custom.berry.*;
import net.egorplaytv.caf.energy.energy_interface.EnergyCapability;
import net.egorplaytv.caf.energy.energy_interface.IEnergyStorage;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.jade.VanillaPlugin;
import snownee.jade.addon.forge.ForgeCapabilityProvider;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.addon.vanilla.VanillaProvider;
import vectorwing.farmersdelight.common.tag.ModTags;

import static net.egorplaytv.caf.CreateAndFood.MOD_ID;
import static net.egorplaytv.caf.util.CAFTags.Blocks.MINEABLE_WITH_HAMMER;
import static snownee.jade.addon.harvest.HarvestToolProvider.registerHandler;
import static vectorwing.farmersdelight.common.registry.ModItems.IRON_KNIFE;

@WailaPlugin
public class JadeCreateAndFoodPlugin implements IWailaPlugin {
    public static final ResourceLocation CAF_ENERGY = CAF("energy.caf");

    private static ResourceLocation CAF(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(new EnergyCapabilityProvider(), BlockEntity.class);
        registration.addConfig(CAF_ENERGY, true);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(new ToolProvider(), TooltipPosition.BODY, Block.class);
        registration.registerIconProvider(new AgeProvider(), CropBlock.class);
        registration.registerComponentProvider(new AgeProvider(), TooltipPosition.BODY, Block.class);
        registration.registerComponentProvider(new EnergyCapabilityProvider(), TooltipPosition.BODY, Block.class);
    }

    public static class ToolProvider extends HarvestToolProvider {
        public ToolProvider() {
            registerHandler(new SimpleToolHandler("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item[]{Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, CAFItems.COPPER_PICKAXE.get(), Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE,
                            Items.NETHERITE_PICKAXE, CAFItems.TANTALUM_PICKAXE.get(), CAFItems.TUNGSTEN_PICKAXE.get()}));

            registerHandler(new SimpleToolHandler("axe", BlockTags.MINEABLE_WITH_AXE,
                    new Item[]{Items.WOODEN_AXE, Items.STONE_AXE, CAFItems.COPPER_AXE.get(), Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE,
                            CAFItems.TANTALUM_AXE.get(), CAFItems.TUNGSTEN_AXE.get()}));

            registerHandler(new SimpleToolHandler("shovel", BlockTags.MINEABLE_WITH_SHOVEL,
                    new Item[]{Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, CAFItems.COPPER_SHOVEL.get(), Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL,
                            Items.NETHERITE_SHOVEL, CAFItems.TANTALUM_SHOVEL.get(), CAFItems.TUNGSTEN_SHOVEL.get()}));

            registerHandler(new SimpleToolHandler("hoe", BlockTags.MINEABLE_WITH_HOE,
                    new Item[]{Items.WOODEN_HOE, Items.STONE_HOE, CAFItems.COPPER_HOE.get(), Items.IRON_HOE, Items.DIAMOND_HOE,
                            Items.NETHERITE_HOE, CAFItems.TANTALUM_HOE.get(), CAFItems.TUNGSTEN_HOE.get()}));


            registerHandler(new SimpleToolHandler("hammer", MINEABLE_WITH_HAMMER,
                    new Item[]{CAFItems.STEEL_HAMMER.get(), CAFItems.TANTALUM_HAMMER.get(), CAFItems.TUNGSTEN_HAMMER.get()}));

            registerHandler(new SimpleToolHandler("knife", ModTags.MINEABLE_WITH_KNIFE, IRON_KNIFE.get()));
        }
    }

    public static class EnergyCapabilityProvider extends ForgeCapabilityProvider {
        public EnergyCapabilityProvider() {
        }

        @Override
        public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
            BlockEntity entity = accessor.getBlockEntity();
            if (entity != null) {
                if (config.get(JadeCreateAndFoodPlugin.CAF_ENERGY)) {
                    IEnergyStorage storage = entity.getCapability(EnergyCapability.ENERGY).orElse(null);
                    if (storage != null && (!accessor.isServerConnected() || accessor.getServerData().contains("cafEnergy"))) {
                        IElementHelper helper = tooltip.getElementHelper();
                        int cur;
                        int max;
                        if (accessor.isServerConnected()) {
                            cur = accessor.getServerData().getInt("cafEnergy");
                            max = accessor.getServerData().getInt("cafMaxEnergy");
                        } else {
                            cur = storage.getEnergyStored();
                            max = storage.getMaxEnergyStored();
                        }

                        ChatFormatting var10000 = ChatFormatting.WHITE;
                        String curText = var10000 + VanillaPlugin.getDisplayHelper().humanReadableNumber((double) cur, "CAF", false) + ChatFormatting.GRAY;
                        String maxText = VanillaPlugin.getDisplayHelper().humanReadableNumber((double) max, "CAF", false);
                        MutableComponent text = (new TranslatableComponent("caf.energy.caf", new Object[]{curText, maxText})).withStyle(ChatFormatting.GRAY);
                        IProgressStyle progressStyle = helper.progressStyle().color(-65536, -10092544);
                        tooltip.add(helper.progress((float) cur / (float) max, text, progressStyle, helper.borderStyle()).tag(VanillaPlugin.FORGE_ENERGY));
                    }
                }
            }
        }

        @Override
        public void appendServerData(CompoundTag data, ServerPlayer player, Level world, BlockEntity tile, boolean showDetails) {
            IEnergyStorage storage = tile.getCapability(EnergyCapability.ENERGY).orElse(null);
            if (storage != null) {
                data.putInt("cafEnergy", storage.getEnergyStored());
                data.putInt("cafMaxEnergy", storage.getMaxEnergyStored());
            }
        }
    }

    public static class AgeProvider extends VanillaProvider {
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
