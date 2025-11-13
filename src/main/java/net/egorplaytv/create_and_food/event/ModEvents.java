package net.egorplaytv.create_and_food.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BERRY_CAKE.get(), 2);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.COPPER_COIN.get(), 1),
                    stack, 10, 8,0.02F));
        }
        if(event.getType() == ModVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GLOW_BERRY_CAKE.get(), 2);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.COPPER_COIN.get(), 1),
                    new ItemStack(Items.GLOW_BERRIES, 1),
                    stack, 10, 8,0.02F));
        }

        if(event.getType() == ModVillagers.CONFECTIONER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.CAKE, 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.COPPER_COIN.get(),5),
                    stack, 5, 8,0.02F));
        }
    }
}
