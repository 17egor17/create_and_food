package net.egorplaytv.caf;

import com.simibubi.create.AllItems;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.item.CAFItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;

public class Compostables {
    public Compostables(){
        //Create
        ComposterBlock.COMPOSTABLES.put(AllItems.CHOCOLATE_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(AllItems.BAR_OF_CHOCOLATE.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(AllItems.WHEAT_FLOUR.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(AllItems.DOUGH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(AllItems.SWEET_ROLL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(AllItems.HONEYED_APPLE.get(), 0.65F);

        //Create And Food
        ComposterBlock.COMPOSTABLES.put(CAFBlocks.ALMOND_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFBlocks.ALMOND_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RYE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RYE_FLOUR.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.BIZET.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.ALMOND_NUT.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RYE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RYE_DOUGH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.SMALL_DOUGH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.EGG_SHELL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_EGG.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_YOLK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_PROTEIN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_BERRY_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_GLOW_BERRY_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_PIZZA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RAW_SWEET_ROLL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.MOZZARELLA_CHEESE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.GLOW_BERRY_CAKE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.PIZZA.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.PIZZA_SLICE.get(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.BERRY_CAKE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(CAFItems.RYE_BREAD.get(), 0.85F);

        //Minecraft
        ComposterBlock.COMPOSTABLES.put(Items.EGG, 0.65F);
    }
}
