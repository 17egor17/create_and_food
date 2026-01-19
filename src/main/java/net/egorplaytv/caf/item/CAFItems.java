package net.egorplaytv.caf.item;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.block.CAFBlocks;
import net.egorplaytv.caf.fluid.CAFFluids;
import net.egorplaytv.caf.item.custom.MetalItem.Type;
import net.egorplaytv.caf.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public class CAFItems {
        public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateAndFood.MOD_ID);


//________________________Create and Food: Food________________________\\
    public static final RegistryObject<Item> BASE_OF_DOUGH = registerItem("base_of_dough",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE = registerItem("small_dough_base",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> RAW_PIZZA = registerItem("raw_pizza",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> PIZZA = registerItem("pizza",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.PIZZA)));

    public static final RegistryObject<Item> PIZZA_SLICE = registerItem("pizza_slice",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.PIZZA_SLICE)));

    public static final RegistryObject<Item> RAW_GLOW_BERRY_CAKE = registerItem("raw_glow_berry_cake",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_RAW_GLOW_BERRY_CAKE = registerItem("incomplete_raw_glow_berry_cake",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> GLOW_BERRY_CAKE = registerItem("glow_berry_cake",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.BERRY_GLITTER_CAKE)));

    public static final RegistryObject<Item> RAW_BERRY_CAKE = registerItem("raw_berry_cake",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_RAW_BERRY_CAKE = registerItem("incomplete_raw_berry_cake",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> BERRY_CAKE = registerItem("berry_cake",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.BERRY_CAKE)));

    public static final RegistryObject<Item> RAW_CAKE = registerItem("raw_cake",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_RAW_CAKE = registerItem("incomplete_raw_cake",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SWEET_ROLL = registerItem("raw_sweet_roll",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> DOUGH_BASE_WITH_CHOCOLATE = registerItem("dough_base_with_chocolate",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE_WHiH_HONEY = registerItem("small_dough_base_with_honey",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE_WHiH_CHOCOLATE = registerItem("small_dough_base_with_chocolate",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_DOUGH = registerItem("rye_dough",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH = registerItem("small_dough",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> BIZET = registerItem("bizet",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> POWDERED_SUGAR = registerItem("powdered_sugar",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> COCOA_POWDER = registerItem("cocoa_powder",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> HARD_COCOA = registerItem("hard_cocoa",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> COCOA_BUTTER_BRIQUETTE = registerItem("cocoa_butter_briquette",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_FLOUR = registerItem("rye_flour",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_BREAD = registerItem("rye_bread",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.RYE_BREAD)));

    public static final RegistryObject<Item> MOZZARELLA_CHEESE = registerItem("mozzarella_cheese",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_EGG = registerItem("raw_egg",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_YOLK = registerItem("raw_yolk",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_PROTEIN = registerItem("raw_protein",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));


    //Berries//
    public static final RegistryObject<Item> BLUEBERRY = registerItem("blueberry",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_SAPLING = registerItem("blueberry_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.BLUEBERRY_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> CRANBERRY = registerItem("cranberry",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> CRANBERRY_SAPLING = registerItem("cranberry_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.CRANBERRY_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RASPBERRY = registerItem("raspberry",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> RASPBERRY_SAPLING = registerItem("raspberry_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.RASPBERRY_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> BLUE_GRAPE = registerItem("blue_grape",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> BLUE_GRAPE_SAPLING = registerItem("blue_grape_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.BLUE_GRAPE_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> GREEN_GRAPE = registerItem("green_grape",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> GREEN_GRAPE_SAPLING = registerItem("green_grape_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.GREEN_GRAPE_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> PURPLE_GRAPE = registerItem("purple_grape",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> PURPLE_GRAPE_SAPLING = registerItem("purple_grape_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.PURPLE_GRAPE_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RED_GRAPE = registerItem("red_grape",
            () -> new Item(new Item.Properties().food(CAFFoods.BERRIES).tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> RED_GRAPE_SAPLING = registerItem("red_grape_sapling",
            () -> new ItemNameBlockItem(CAFBlocks.RED_GRAPE_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> PUMPKIN_SEEDS = registerItem("pumpkin_seeds",
            () -> new ItemNameBlockItem(CAFBlocks.PUMPKIN_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> MELON_SEEDS = registerItem("melon_seeds",
            () -> new ItemNameBlockItem(CAFBlocks.MELON_BUSH.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    //Berries//

    public static final RegistryObject<Item> APPLE_VINEGAR_BUCKET = registerItem("apple_vinegar_bucket",
            () -> new BucketItem(CAFFluids.APPLE_VINEGAR, new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> COCOA_OIL_BUCKET = registerItem("cocoa_oil_bucket",
            () -> new BucketItem(CAFFluids.COCOA_OIL, new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> WHITE_CHOCOLATE_BUCKET = registerItem("white_chocolate_bucket",
            () -> new BucketItem(CAFFluids.WHITE_CHOCOLATE, new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> RED_GRAPE_JUICE_BUCKET = registerItem("red_grape_juice_bucket",
            () -> new BucketItem(CAFFluids.RED_GRAPE_JUICE, new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> HONEY_MILK = registerItem("honey_milk",
            () -> new DrinkableItem(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).food(CAFFoods.HONEY_MILK).craftRemainder(Items.GLASS_BOTTLE), true,false));

    public static final RegistryObject<Item> ALMOND_NUT = registerItem("almond_nut",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    //Macaroni//
    public static final RegistryObject<Item> MACARONI = registerItem("macaroni",
            () -> new Item(new Item.Properties().food(CAFFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> PINK_MACARONI = registerItem("pink_macaroni",
            () -> new Item(new Item.Properties().food(CAFFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> RED_MACARONI = registerItem("red_macaroni",
            () -> new Item(new Item.Properties().food(CAFFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> WHITE_MACARONI = registerItem("white_macaroni",
            () -> new Item(new Item.Properties().food(CAFFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> EGG_SHELL = registerItem("egg_shell",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD).durability(1)));
    public static final RegistryObject<Item> RYE_SEEDS = registerItem("rye_seeds",
            () -> new ItemNameBlockItem(CAFBlocks.RYE_PLANT.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> RYE = registerItem("rye",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RICE = registerItem("rice",
            () -> new RiceItem(CAFBlocks.RICE_CROP.get(), new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> ROASTED_COCOA_BEANS = registerItem("roasted_cocoa_beans",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_FOOD_FOOD)));







//________________________Create and Food________________________\\
    public static final RegistryObject<MetalItem> RAW_IRON = registerItem("raw_iron",
            () -> new MetalItem(1538, Type.RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> IRON_INGOT = registerItem("iron_ingot",
            () -> new MetalItem(1538, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> IRON_NUGGET = registerItem("iron_nugget",
            () -> new MetalItem(1538, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> RAW_COPPER = registerItem("raw_copper",
            () -> new MetalItem(1085, Type.RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> COPPER_INGOT = registerItem("copper_ingot",
            () -> new MetalItem(1085, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> RAW_GOLD = registerItem("raw_gold",
            () -> new MetalItem(1064, Type.RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> GOLD_INGOT = registerItem("gold_ingot",
            () -> new MetalItem(1064, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> PIECE_OF_GOLD = registerItem("piece_of_gold",
            () -> new MetalItem(1064, Type.PIECE, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> GOLD_NUGGET = registerItem("gold_nugget",
            () -> new MetalItem(1064, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> NETHERITE_INGOT = registerItem("netherite_ingot",
            () -> new MetalItem(3133, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> STEEL_INGOT = registerItem("steel_ingot",
            () -> new MetalItem(1530, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> STEEL_NUGGET = registerItem("steel_nugget",
            () -> new MetalItem(1530, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> STEEL_SHEET = registerItem("steel_sheet",
            () -> new MetalItem(1530, Type.SHEET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> GLOWING_BRASS_INGOT = registerItem("glowing_brass_ingot",
            () -> new MetalItem(950, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> GLOWING_BRASS_NUGGET = registerItem("glowing_brass_nugget",
            () -> new MetalItem(950, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> GLOWING_BRASS_SHEET = registerItem("glowing_brass_sheet",
            () -> new MetalItem(950, Type.SHEET,new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> ALLOY_SOULS = registerItem("alloy_souls",
            () -> new MetalItem(1400, Type.PIECE, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> ALLOY_SOULS_INGOT = registerItem("alloy_souls_ingot",
            () -> new MetalItem(1400, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> ALLOY_SOULS_NUGGET = registerItem("alloy_souls_nugget",
            () -> new MetalItem(1400, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> ALLOY_SOULS_SHEET = registerItem("alloy_souls_sheet",
            () -> new MetalItem(1400, Type.SHEET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_NETHERITE_INGOT = registerItem("incomplete_netherite_ingot",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> NETHER_ALLOY = registerItem("nether_alloy",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> RAW_TANTALUM = registerItem("raw_tantalum",
            () -> new MetalItem(3016, Type.RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> CRUSHED_RAW_TANTALUM = registerItem("crushed_raw_tantalum",
            () -> new MetalItem(3016, Type.CRASHED_RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> TANTALUM_INGOT = registerItem("tantalum_ingot",
            () -> new MetalItem(3016, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> TANTALUM_NUGGET = registerItem("tantalum_nugget",
            () -> new MetalItem(3016, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> RAW_TUNGSTEN = registerItem("raw_tungsten",
            () -> new MetalItem(3421, Type.RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> CRUSHED_RAW_TUNGSTEN = registerItem("crushed_raw_tungsten",
            () -> new MetalItem(3421, Type.CRASHED_RAW, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            () -> new MetalItem(3421, Type.INGOT, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> TUNGSTEN_NUGGET = registerItem("tungsten_nugget",
            () -> new MetalItem(3421, Type.NUGGET, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_COIL = registerItem("steel_coil",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ELECTRUM_COIL = registerItem("electrum_coil",
            () -> new IEDependentIngredientItem(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ALUMINUM_COIL = registerItem("aluminum_coil",
            () -> new IEDependentIngredientItem(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_COIL = registerItem("copper_coil",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_COIN = registerItem("incomplete_coin",
            () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final RegistryObject<MetalItem> COPPER_COIN = registerItem("copper_coin",
            () -> new MetalItem(1085, Type.COIN, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> IRON_COIN = registerItem("iron_coin",
            () -> new MetalItem(1538, Type.COIN, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<MetalItem> GOLDEN_COIN = registerItem("golden_coin",
            () -> new MetalItem(1064, Type.COIN, new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<MetalItem> BROKEN_COPPER_COIN = registerItem("broken_copper_coin",
            () -> new MetalItem(1085, Type.COIN, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<MetalItem> BROKEN_IRON_COIN = registerItem("broken_iron_coin",
            () -> new MetalItem(1538, Type.COIN, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<MetalItem> BROKEN_GOLDEN_COIN = registerItem("broken_golden_coin",
            () -> new MetalItem(1064, Type.COIN, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust",
            () -> new FuelItem(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD), 1600));

    public static final RegistryObject<Item> IRON_DUST = registerItem("iron_dust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_DUST = registerItem("steel_dust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> BRICK_DUST = registerItem("brick_dust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> WOOD_SAWDUST = registerItem("wood_sawdust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> WOOD_CHIPS = registerItem("wood_chips",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> CRIMSON_SAWDUST = registerItem("crimson_sawdust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> CRIMSON_CHIPS = registerItem("crimson_chips",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> WARPED_SAWDUST = registerItem("warped_sawdust",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> WARPED_CHIPS = registerItem("warped_chips",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> THIN_CARDBOARD = registerItem("thin_cardboard",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> CRIMSON_THIN_CARDBOARD = registerItem("crimson_thin_cardboard",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> WARPED_THIN_CARDBOARD = registerItem("warped_thin_cardboard",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RAW_RUBY = registerItem("raw_ruby",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RUBY = registerItem("ruby",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> FIRECLAY_BRICK = registerItem("fireclay_brick",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> FIRECLAY_CLAY_BALL = registerItem("fireclay_clay_ball",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_MARBLE_BRICK = registerItem("incomplete_marble_brick",
            () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final RegistryObject<Item> MARBLE_BRICK = registerItem("marble_brick",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_MARBLE_BLACK_GALAXY_BRICK = registerItem("incomplete_marble_black_galaxy_brick",
            () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final RegistryObject<Item> MARBLE_BLACK_GALAXY_BRICK = registerItem("marble_black_galaxy_brick",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_MARBLE_PERLIN_PINK_BRICK = registerItem("incomplete_marble_perlin_pink_brick",
            () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final RegistryObject<Item> MARBLE_PERLIN_PINK_BRICK = registerItem("marble_perlin_pink_brick",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD)));







//________________________Create and Food: Tools and Tips________________________\\
    public static final RegistryObject<Item> INCOMPLETE_TOOL_HANDLE = registerItem("incomplete_tool_handle",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> TOOL_HANDLE = registerItem("tool_handle",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_DIAMOND_KNIFE = registerItem("incomplete_diamond_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_KNIFE = registerItem("diamond_knife",
            () -> new KnifeItem(CAFTiers.diamond(CAFTiers.HandelType.WOOD), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> IRON_SHOVEL = registerItem("iron_shovel",
            () -> new ShovelItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), 1.5F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> IRON_PICKAXE = registerItem("iron_pickaxe",
            () -> new PickaxeItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), 1, -2.8F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> IRON_HOE = registerItem("iron_hoe",
            () -> new HoeItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), -2, -1.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> IRON_AXE = registerItem("iron_axe",
            () -> new AxeItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), 6.0F, -3.1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> IRON_SWORD = registerItem("iron_sword",
            () -> new SwordItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), 3, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_IRON_KNIFE = registerItem("incomplete_iron_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> IRON_KNIFE = registerItem("iron_knife",
            () -> new KnifeItem(CAFTiers.iron(CAFTiers.HandelType.WOOD), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> COPPER_SHOVEL = registerItem("copper_shovel",
            () -> new ShovelItem(CAFTiers.copper(CAFTiers.HandelType.WOOD), 1.5F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> COPPER_PICKAXE = registerItem("copper_pickaxe",
            () -> new PickaxeItem(CAFTiers.copper(CAFTiers.HandelType.WOOD), 1, -2.8F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> COPPER_HOE = registerItem("copper_hoe",
            () -> new HoeItem(CAFTiers.copper(CAFTiers.HandelType.WOOD), 4, -1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> COPPER_AXE = registerItem("copper_axe",
            () -> new AxeItem(CAFTiers.copper(CAFTiers.HandelType.WOOD), 6.0F, -3.1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> COPPER_SWORD = registerItem("copper_sword",
            () -> new SwordItem(CAFTiers.copper(CAFTiers.HandelType.WOOD), 3, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> GOLDEN_SHOVEL = registerItem("golden_shovel",
            () -> new ShovelItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 1.5F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> GOLDEN_PICKAXE = registerItem("golden_pickaxe",
            () -> new PickaxeItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 1, -2.8F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> GOLDEN_HOE = registerItem("golden_hoe",
            () -> new HoeItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 0, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> GOLDEN_AXE = registerItem("golden_axe",
            () -> new AxeItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 6.0F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> GOLDEN_SWORD = registerItem("golden_sword",
            () -> new SwordItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 3, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_GOLDEN_KNIFE = registerItem("incomplete_golden_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_KNIFE = registerItem("golden_knife",
            () -> new KnifeItem(CAFTiers.gold(CAFTiers.HandelType.WOOD), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> NETHERITE_SHOVEL = registerItem("netherite_shovel",
            () -> new ShovelItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), 1.5F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> NETHERITE_PICKAXE = registerItem("netherite_pickaxe",
            () -> new PickaxeItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), 1, -2.8F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> NETHERITE_HOE = registerItem("netherite_hoe",
            () -> new HoeItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), -4, 0.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> NETHERITE_AXE = registerItem("netherite_axe",
            () -> new AxeItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), 5.0F, -3.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> NETHERITE_SWORD = registerItem("netherite_sword",
            () -> new SwordItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), 3, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_NETHERITE_KNIFE = registerItem("incomplete_netherite_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_KNIFE = registerItem("netherite_knife",
            () -> new KnifeItem(CAFTiers.netherite(CAFTiers.HandelType.WOOD), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP).fireResistant()));

    public static final RegistryObject<Item> STEEL_SHOVEL = registerItem("steel_shovel",
            () -> new ShovelItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 1, -3F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> STEEL_PICKAXE = registerItem("steel_pickaxe",
            () -> new PickaxeItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 2, -2.8F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> STEEL_HOE = registerItem("steel_hoe",
            () -> new HoeItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 4, -1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> STEEL_AXE = registerItem("steel_axe",
            () -> new AxeItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 6, -3.1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> STEEL_SWORD = registerItem("steel_sword",
            () -> new SwordItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 4, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> STEEL_HAMMER = registerItem("steel_hammer",
            () -> new HammerItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 6, -3.1F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_STEEL_KNIFE = registerItem("incomplete_steel_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_KNIFE = registerItem("steel_knife",
            () -> new KnifeItem(CAFTiers.steel(CAFTiers.HandelType.IRON), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_SHOVEL = registerItem("tantalum_shovel",
            () -> new ShovelItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_PICKAXE = registerItem("tantalum_pickaxe",
            () -> new PickaxeItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -2.2F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_HOE = registerItem("tantalum_hoe",
            () -> new HoeItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -0.6F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_AXE = registerItem("tantalum_axe",
            () -> new AxeItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -2.5F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_SWORD = registerItem("tantalum_sword",
            () -> new SwordItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TANTALUM_HAMMER = registerItem("tantalum_hammer",
            () -> new HammerItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 5, -2.5F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_TANTALUM_KNIFE = registerItem("incomplete_tantalum_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> TANTALUM_KNIFE = registerItem("tantalum_knife",
            () -> new KnifeItem(CAFTiers.tantalum(CAFTiers.HandelType.IRON), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = registerItem("tungsten_shovel",
            () -> new ShovelItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -2.4F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = registerItem("tungsten_pickaxe",
            () -> new PickaxeItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -2.2F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_HOE = registerItem("tungsten_hoe",
            () -> new HoeItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -0.6F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_AXE = registerItem("tungsten_axe",
            () -> new AxeItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -2.5F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_SWORD = registerItem("tungsten_sword",
            () -> new SwordItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> TUNGSTEN_HAMMER = registerItem("tungsten_hammer",
            () -> new HammerItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), (int)5.5, -2.5F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> INCOMPLETE_TUNGSTEN_KNIFE = registerItem("incomplete_tungsten_knife",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> TUNGSTEN_KNIFE = registerItem("tungsten_knife",
            () -> new KnifeItem(CAFTiers.tungsten(CAFTiers.HandelType.IRON), 0.5F, -2.0F,
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP)));

    public static final RegistryObject<Item> KITCHEN_HAMMER = registerItem("kitchen_hammer",
            () -> new Item(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_TOOL_AND_TIP).durability(1500)));





//________________________Create and Food: Decorative________________________\\

    public static final RegistryObject<Item> TORN_SOUL_CHAIN = registerItem("torn_soul_chain",
            () -> new ItemNameBlockItem(CAFBlocks.TORN_SOUL_CHAIN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> STEEL_CHAIN = registerItem("steel_chain",
            () -> new ItemNameBlockItem(CAFBlocks.STEEL_CHAIN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> TORN_SOUL_LANTERN = registerItem("torn_soul_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.TORN_SOUL_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_COPPER_LANTERN = registerItem("glowing_brass_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_EXPOSED_COPPER_LANTERN = registerItem("glowing_brass_exposed_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_EXPOSED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_WEATHERED_COPPER_LANTERN = registerItem("glowing_brass_weathered_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_WEATHERED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_OXIDIZED_COPPER_LANTERN = registerItem("glowing_brass_oxidized_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_OXIDIZED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_WAXED_COPPER_LANTERN = registerItem("glowing_brass_waxed_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_WAXED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN = registerItem("glowing_brass_waxed_exposed_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_WAXED_EXPOSED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN = registerItem("glowing_brass_waxed_weathered_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_WAXED_WEATHERED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN = registerItem("glowing_brass_waxed_oxidized_copper_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_WAXED_OXIDIZED_COPPER_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_STEEL_LANTERN = registerItem("glowing_brass_steel_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.GLOWING_BRASS_STEEL_LANTERN.get(),
                    new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> LANTERN = registerItem("lantern",
            () -> new ItemNameBlockItem(CAFBlocks.LANTERN.get(), new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> SOUL_LANTERN = registerItem("soul_lantern",
            () -> new ItemNameBlockItem(CAFBlocks.SOUL_LANTERN.get(), new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> ALMOND_SIGN = registerItem("almond_sign",
            () -> new SignItem(new Item.Properties().tab(CAFCreativeModeTab.CREATE_AND_FOOD_DECORATIVE).stacksTo(16),
                    CAFBlocks.ALMOND_SIGN.get(), CAFBlocks.ALMOND_WALL_SIGN.get()));


    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item){
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}