package net.egorplaytv.caf;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.providers.ProviderType;
import net.egorplaytv.caf.block.entity.ModWoodTypes;
import net.egorplaytv.caf.config.CAFConfigs;
import net.egorplaytv.caf.config.CreateAndFoodClientConfigs;
import net.egorplaytv.caf.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.caf.data.CAFRegistrate;
import net.egorplaytv.caf.item.ItemEntities;
import net.egorplaytv.caf.networking.CAFMessages;
import net.egorplaytv.caf.ponder.CAFPonders;
import net.egorplaytv.caf.villager.CAFVillagers;
import net.egorplaytv.caf.world.VillageStructures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.BiConsumer;

import static net.egorplaytv.caf.block.entity.custom.TerminalBlockEntity.setModId;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateAndFood.MOD_ID)
public class CreateAndFood {
    // Directly reference a slf4j logger
    public static final String MOD_ID = "caf";
    public static final CAFRegistrate REGISTRATE = CAFRegistrate.create(MOD_ID);
    public static final Logger LOGGER = LogManager.getLogger();
    public static boolean IEIsPresent = false;
    public static boolean MEKIsPresent = false;
    public static boolean THIsPresent = false;
    public static boolean VIIsPresent = false;

    static {
        REGISTRATE.setTooltipModifierFactory(item -> {
            return new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                    .andThen(TooltipModifier.mapNull(KineticStats.create(item)));
        });
    }

    public CreateAndFood(){
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        REGISTRATE.registerEventListeners(eventBus);

        Optional<? extends ModContainer> thModContainer = ModList.get().getModContainerById("thermal");
        if (thModContainer.isPresent()){
            THIsPresent = true;
        }
        Optional<? extends ModContainer> mekModContainer = ModList.get().getModContainerById("mekanism");
        if (mekModContainer.isPresent()){
            MEKIsPresent = true;
        }
        Optional<? extends ModContainer> ieModContainer = ModList.get().getModContainerById("immersiveengineering");
        if (ieModContainer.isPresent()){
            IEIsPresent = true;
        }
        Optional<? extends ModContainer> viModContainer = ModList.get().getModContainerById("vintageimprovements");
        if (viModContainer.isPresent()){
            VIIsPresent = true;
        }

        setModId(MOD_ID);

        new register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::gatherData);
        eventBus.addGenericListener(EntityType.class, this::registerEntities);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CreateAndFoodClientConfigs.SPEC, "create_and_food-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CreateAndFoodCommonConfigs.SPEC, "create_and_food-common.toml");
        CAFConfigs.register(modLoadingContext);

        MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CreateAndFoodClient::new);
    }

    private void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        ItemEntities.init(event.getRegistry());
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation asFluid(String path) {
        return new ResourceLocation(MOD_ID, "fluid/" + path);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        new render();
        event.enqueueWork(CAFPonders::register);
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() ->{
            CAFVillagers.registerPOIs();
            new Compostables();
            new Fueling();
            new Sample();
            new Weight();

            Sheets.addWoodType(ModWoodTypes.ALMOND);
        });

        CAFMessages.register();
    }

    public void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        addExtraRegistrateData();
    }

    private static void addExtraRegistrateData() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;
            CAFPonders.register();
        });
    }
}
