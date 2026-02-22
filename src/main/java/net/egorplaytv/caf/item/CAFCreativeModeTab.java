package net.egorplaytv.caf.item;

import net.egorplaytv.caf.block.CAFBlocks;
//import net.minecraft.core.registries.Registries;
import net.egorplaytv.caf.block.ShingleBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CAFCreativeModeTab {
//    for 1.19.2
//    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
//            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateAndFood.MOD_ID);

//    public static final RegistryObject<CreativeModeTab> CREATE_FOOD_FOOD = CREATIVE_MODE_TABS.register("caf_food",
//        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_CAKE.get()))
//                .title(Component.translatable("itemGroup.caf_food"))
//                .displayItems((pParameters, pOutput) -> {
//                    pOutput.accept(ModItems.STEEL_KNIFE.get());
//                })
//                .build());

    public static final CreativeModeTab CREATE_FOOD_FOOD = new CreativeModeTab("caf_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CAFItems.RAW_CAKE.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD = new CreativeModeTab("caf") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CAFItems.STEEL_KNIFE.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_DECORATIVE = new CreativeModeTab("caf_decorative") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CAFBlocks.GOLDEN_WALL.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_TOOL_AND_TIP = new CreativeModeTab("caf_tool_and_tip") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CAFItems.TANTALUM_KNIFE.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_KITCHEN = new CreativeModeTab("caf_kitchen") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CAFBlocks.OAK_CUTTING_BOARD.get());
        }
    };
    public static final CreativeModeTab CREATE_AND_FOOD_ROOFS = new CreativeModeTab("caf_roofs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ShingleBlocks.SAMAN.getVariants().getBlocks().get());
        }
    };

// for 1.19.2
//    public static void register(IEventBus bus){
//        CREATIVE_MODE_TABS.register(bus);
//    }
}
