package net.egorplaytv.create_and_food.integration;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.config.IPluginConfig;
import mcp.mobius.waila.api.ui.IElement;
import net.egorplaytv.create_and_food.block.custom.berry.*;
import net.egorplaytv.create_and_food.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import snownee.jade.VanillaPlugin;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.addon.vanilla.VanillaProvider;
import vectorwing.farmersdelight.common.tag.ModTags;

import static net.egorplaytv.create_and_food.util.ModTags.Blocks.MINEABLE_WITH_HAMMER;
import static vectorwing.farmersdelight.common.registry.ModItems.IRON_KNIFE;

@WailaPlugin
public class JadeCreateAndFoodPlugin implements IWailaPlugin {

    public JadeCreateAndFoodPlugin(){
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(new ToolProvider(), TooltipPosition.BODY, Block.class);
        registration.registerIconProvider(new AgeProvider(), CropBlock.class);
        registration.registerComponentProvider(new AgeProvider(), TooltipPosition.BODY, Block.class);
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
