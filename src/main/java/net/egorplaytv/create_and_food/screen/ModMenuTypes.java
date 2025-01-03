package net.egorplaytv.create_and_food.screen;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, CreateAndFood.MOD_ID);

    public static final RegistryObject<MenuType<FermentationBarrelMenu>> FERMENTATION_BARREL_MENU =
            registerMenuType(FermentationBarrelMenu::new, "fermentation_barrel_menu");
    public static final RegistryObject<MenuType<MarbleBlastFurnaceMenu>> BLASTING_MENU =
            registerMenuType(MarbleBlastFurnaceMenu::new, "blasting_menu");
    public static final RegistryObject<MenuType<SampleOfMetalsMenu>> SAMPLE_MENU =
            registerMenuType(SampleOfMetalsMenu::new, "sample_menu");

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
