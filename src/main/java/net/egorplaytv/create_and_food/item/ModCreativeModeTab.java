package net.egorplaytv.create_and_food.item;

import net.egorplaytv.create_and_food.block.ModBlocks;
//import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
//    for 1.19.2
//    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
//            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateAndFood.MOD_ID);

//    public static final RegistryObject<CreativeModeTab> CREATE_FOOD_FOOD = CREATIVE_MODE_TABS.register("create_and_food_food",
//        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_CAKE.get()))
//                .title(Component.translatable("itemGroup.create_and_food_food"))
//                .displayItems((pParameters, pOutput) -> {
//                    pOutput.accept(ModItems.STEEL_KNIFE.get());
//                })
//                .build());

    public static final CreativeModeTab CREATE_FOOD_FOOD = new CreativeModeTab("create_and_food_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_CAKE.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD = new CreativeModeTab("create_and_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STEEL_KNIFE.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_DECORATIVE = new CreativeModeTab("create_and_food_decorative") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.GOLDEN_WALL.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_KITCHEN = new CreativeModeTab("create_and_food_kitchen") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.OAK_CUTTING_BOARD.get());
        }
    };
// for 1.19.2
//    public static void register(IEventBus bus){
//        CREATIVE_MODE_TABS.register(bus);
//    }
}
