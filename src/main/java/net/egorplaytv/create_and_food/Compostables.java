package net.egorplaytv.create_and_food;

import com.simibubi.create.AllItems;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.item.ModItems;
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
        ComposterBlock.COMPOSTABLES.put(ModBlocks.ALMOND_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.ALMOND_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_FLOUR.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CREAM.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CUSTARD.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.GLAZE.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.BIZET.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.ALMOND_NUT.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_DOUGH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.SMALL_DOUGH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.EGG_SHELL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_EGG.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_YOLK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PROTEIN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_BERRY_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_GLOW_BERRY_CAKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PIZZA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_SWEET_ROLL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.MOZZARELLA_CHEESE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.GLOW_BERRY_CAKE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.PIZZA.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.BERRY_CAKE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_BREAD.get(), 0.85F);

        //Minecraft
        ComposterBlock.COMPOSTABLES.put(Items.EGG, 0.65F);
    }
}
