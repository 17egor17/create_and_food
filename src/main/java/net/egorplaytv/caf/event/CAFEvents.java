package net.egorplaytv.caf.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.item.CAFItems;
import net.egorplaytv.caf.villager.CAFVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID)
public class CAFEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == CAFVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(CAFItems.BERRY_CAKE.get(), 2);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CAFItems.COPPER_COIN.get(), 1),
                    stack, 10, 8,0.02F));
        }
        if(event.getType() == CAFVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(CAFItems.GLOW_BERRY_CAKE.get(), 2);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CAFItems.COPPER_COIN.get(), 1),
                    new ItemStack(Items.GLOW_BERRIES, 1),
                    stack, 10, 8,0.02F));
        }

        if(event.getType() == CAFVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.CAKE, 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CAFItems.COPPER_COIN.get(),5),
                    stack, 5, 8,0.02F));
        }
    }
}
