package net.egorplaytv.create_and_food.item;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.ModBlocks;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.egorplaytv.create_and_food.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateAndFood.MOD_ID);


//________________________Create and Food: Food________________________\\
    public static final RegistryObject<Item> BASE_OF_DOUGH = registerItem("base_of_dough",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE = registerItem("small_dough_base",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> RAW_PIZZA = registerItem("raw_pizza",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> PIZZA = registerItem("pizza",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).food(ModFoods.PIZZA)));

    public static final RegistryObject<Item> RAW_GLOW_BERRY_CAKE = registerItem("raw_glow_berry_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> GLOW_BERRY_CAKE = registerItem("glow_berry_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).food(ModFoods.BERRY_GLITTER_CAKE)));

    public static final RegistryObject<Item> RAW_BERRY_CAKE = registerItem("raw_berry_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> BERRY_CAKE = registerItem("berry_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).food(ModFoods.BERRY_CAKE)));

    public static final RegistryObject<Item> RAW_CAKE = registerItem("raw_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_SWEET_ROLL = registerItem("raw_sweet_roll",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> DOUGH_BASE_WITH_CHOCOLATE = registerItem("dough_base_with_chocolate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE_WHiH_HONEY = registerItem("small_dough_base_with_honey",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH_BASE_WHiH_CHOCOLATE = registerItem("small_dough_base_with_chocolate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_DOUGH = registerItem("rye_dough",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> SMALL_DOUGH = registerItem("small_dough",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> CREAM = registerItem("cream",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> BIZET = registerItem("bizet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> GLAZE = registerItem("glaze",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> CUSTARD = registerItem("custard",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> POWDERED_SUGAR = registerItem("powdered_sugar",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> COCOA_POWDER = registerItem("cocoa_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> COCOA_BUTTER_BRIQUETTE = registerItem("cocoa_butter_briquette",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_FLOUR = registerItem("rye_flour",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RYE_BREAD = registerItem("rye_bread",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).food(ModFoods.RYE_BREAD)));

    public static final RegistryObject<Item> INCOMPLETE_MOZZARELLA_CHEESE = registerItem("incomplete_mozzarella_cheese",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> MOZZARELLA_CHEESE = registerItem("mozzarella_cheese",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_EGG = registerItem("raw_egg",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_YOLK = registerItem("raw_yolk",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> RAW_PROTEIN = registerItem("raw_protein",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));


    //Berries//
    public static final RegistryObject<Item> BLUEBERRY = registerItem("blueberry",
            () -> new Item(new Item.Properties().food(ModFoods.BERRYS)));

    public static final RegistryObject<Item> APPLE_VINEGAR_BUCKET = registerItem("apple_vinegar_bucket",
            () -> new BucketItem(ModFluids.APPLE_VINEGAR_FLUID, new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));
    public static final RegistryObject<Item> COCOA_OIL_BUCKET = registerItem("cocoa_oil_bucket",
            () -> new BucketItem(ModFluids.COCOA_OIL_FLUID, new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));
    public static final RegistryObject<Item> WHITE_CHOCOLATE_BUCKET = registerItem("white_chocolate_bucket",
            () -> new BucketItem(ModFluids.WHITE_CHOCOLATE_FLUID, new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).stacksTo(1)));

    public static final RegistryObject<Item> HONEY_MILK = registerItem("honey_milk",
            () -> new DrinkableItem(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).food(ModFoods.HONEY_MILK).craftRemainder(Items.GLASS_BOTTLE), true,false));


    public static final RegistryObject<Item> ALMOND_NUT = registerItem("almond_nut",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    //Macaroni//
    public static final RegistryObject<Item> MACARONI = registerItem("macaroni",
            () -> new Item(new Item.Properties().food(ModFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> PINK_MACARONI = registerItem("pink_macaroni",
            () -> new Item(new Item.Properties().food(ModFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> RED_MACARONI = registerItem("red_macaroni",
            () -> new Item(new Item.Properties().food(ModFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> WHITE_MACARONI = registerItem("white_macaroni",
            () -> new Item(new Item.Properties().food(ModFoods.MACARONI).tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> EGG_SHELL = registerItem("egg_shell",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD).durability(1)));
    public static final RegistryObject<Item> RYE_SEEDS = registerItem("rye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RYE_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));
    public static final RegistryObject<Item> RYE = registerItem("rye",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));

    public static final RegistryObject<Item> ROASTED_COCOA_BEANS = registerItem("roasted_cocoa_beans",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_FOOD_FOOD)));







//________________________Create and Food________________________\\
    public static final RegistryObject<Item> STEEL_INGOT = registerItem("steel_ingot",
            () -> new MeltItem(1530, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_NUGGET = registerItem("steel_nugget",
            () -> new MeltItem(1530, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_SHEET = registerItem("steel_sheet",
            () -> new MeltItem(1530, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> GLOWING_BRASS_INGOT = registerItem("glowing_brass_ingot",
            () -> new MeltItem(950, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> GLOWING_BRASS_NUGGET = registerItem("glowing_brass_nugget",
            () -> new MeltItem(950, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> GLOWING_BRASS_SHEET = registerItem("glowing_brass_sheet",
            () -> new MeltItem(950 ,new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> PIECE_OF_GOLD = registerItem("piece_of_gold",
            () -> new MeltItem(1064, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ALLOY_SOULS = registerItem("alloy_souls",
            () -> new MeltItem(1400, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ALLOY_SOULS_INGOT = registerItem("alloy_souls_ingot",
            () -> new MeltItem(1400, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ALLOY_SOULS_SHEET = registerItem("alloy_souls_sheet",
            () -> new MeltItem(1400, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> ALLOY_SOULS_NUGGET = registerItem("alloy_souls_nugget",
            () -> new MeltItem(1400, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_NETHERITE_INGOT = registerItem("incomplete_netherite_ingot",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> NETHER_ALLOY = registerItem("nether_alloy",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));


    public static final RegistryObject<Item> STEEL_COIL = registerItem("steel_coil",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_COIL = registerItem("copper_coil",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_TOOL_HANDLE = registerItem("incomplete_tool_handle",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> TOOL_HANDLE = registerItem("tool_handle",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_SWORD = registerItem("steel_sword",
            () -> new SwordItem(ModTiers.STEEL, 4, -2.4F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_PICKAXE = registerItem("steel_pickaxe",
            () -> new PickaxeItem(ModTiers.STEEL, 2, -2.8F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_SHOVEL = registerItem("steel_shovel",
            () -> new ShovelItem(ModTiers.STEEL, 1, -3F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_AXE = registerItem("steel_axe",
            () -> new AxeItem(ModTiers.STEEL, 6, -3.1F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_HOE = registerItem("steel_hoe",
            () -> new HoeItem(ModTiers.STEEL, 4, -1F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_HAMMER = registerItem("steel_hammer",
            () -> new HammerItem(ModTiers.STEEL, 6, -3.1F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_KNIFE = registerItem("steel_knife",
            () -> new KnifeItem(ModTiers.STEEL, 0.5F, -2.0F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> KITCHEN_HAMMER = registerItem("kitchen_hammer",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD).durability(1500)));

    public static final RegistryObject<Item> COPPER_SWORD = registerItem("copper_sword",
            () -> new SwordItem(ModTiers.COPPER, 3, -2.4F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_PICKAXE = registerItem("copper_pickaxe",
            () -> new PickaxeItem(ModTiers.COPPER, 1, -2.8F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_SHOVEL = registerItem("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER, 1.5F, -3.0F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_AXE = registerItem("copper_axe",
            () -> new AxeItem(ModTiers.COPPER, 6.0F, -3.1F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> COPPER_HOE = registerItem("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER, 4, -1F,
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> INCOMPLETE_COIN = registerItem("incomplete_coin",
            () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_COIN = registerItem("copper_coin",
            () -> new MeltItem(1085, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> IRON_COIN = registerItem("iron_coin",
            () -> new MeltItem(1538, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> GOLDEN_COIN = registerItem("golden_coin",
            () -> new MeltItem(1064, new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> BROKEN_COPPER_COIN = registerItem("broken_copper_coin",
            () -> new MeltItem(1085, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<Item> BROKEN_IRON_COIN = registerItem("broken_iron_coin",
            () -> new MeltItem(1538, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<Item> BROKEN_GOLDEN_COIN = registerItem("broken_golden_coin",
            () -> new MeltItem(1064, new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> IRON_DUST = registerItem("iron_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> STEEL_DUST = registerItem("steel_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RAW_RUBY = registerItem("raw_ruby",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RUBY = registerItem("ruby",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RAW_TANTALUM = registerItem("raw_tantalum",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> CRASHED_RAW_TANTALUM = registerItem("crushed_raw_tantalum",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> TANTALUM_INGOT = registerItem("tantalum_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> TANTALUM_NUGGET = registerItem("tantalum_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));

    public static final RegistryObject<Item> RAW_TUNGSTEN = registerItem("raw_tungsten",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> CRASHED_RAW_TUNGSTEN = registerItem("crushed_raw_tungsten",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = registerItem("tungsten_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD)));







//________________________Create and Food: Decorative________________________\\

    public static final RegistryObject<Item> TORN_SOUL_CHAIN = registerItem("torn_soul_chain",
            () -> new ItemNameBlockItem(ModBlocks.TORN_SOUL_CHAIN.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> STEEL_CHAIN = registerItem("steel_chain",
            () -> new ItemNameBlockItem(ModBlocks.STEEL_CHAIN.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> TORN_SOUL_LANTERN = registerItem("torn_soul_lantern",
            () -> new ItemNameBlockItem(ModBlocks.TORN_SOUL_LANTERN.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_COPPER_LANTERN = registerItem("glowing_brass_copper_lantern",
            () -> new ItemNameBlockItem(ModBlocks.GLOWING_BRASS_COPPER_LANTERN.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> GLOWING_BRASS_STEEL_LANTERN = registerItem("glowing_brass_steel_lantern",
            () -> new ItemNameBlockItem(ModBlocks.GLOWING_BRASS_STEEL_LANTERN.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE)));

    public static final RegistryObject<Item> ALMOND_SIGN = registerItem("almond_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.CREATE_AND_FOOD_DECORATIVE).stacksTo(16),
                    ModBlocks.ALMOND_SIGN.get(), ModBlocks.ALMOND_WALL_SIGN.get()));

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item){
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}