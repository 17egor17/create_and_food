package net.egorplaytv.create_and_food;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.providers.ProviderType;
import net.egorplaytv.create_and_food.block.entity.ModWoodTypes;
import net.egorplaytv.create_and_food.config.CAFConfigs;
import net.egorplaytv.create_and_food.config.CreateAndFoodClientConfigs;
import net.egorplaytv.create_and_food.config.CreateAndFoodCommonConfigs;
import net.egorplaytv.create_and_food.data.CAFRegistrate;
import net.egorplaytv.create_and_food.datagen.custom.ModItemModelsProperties;
import net.egorplaytv.create_and_food.networking.ModMessages;
import net.egorplaytv.create_and_food.ponder.CAFPonders;
import net.egorplaytv.create_and_food.villager.ModVillagers;
import net.egorplaytv.create_and_food.world.VillageStructures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.BiConsumer;

import static net.egorplaytv.create_and_food.block.entity.custom.TerminalBlockEntity.setModId;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateAndFood.MOD_ID)
public class CreateAndFood {
    // Directly reference a slf4j logger
    public static final String MOD_ID = "create_and_food";
    public static final String MINECRAFT_ID = "minecraft";
    public static final CAFRegistrate REGISTRATE = CAFRegistrate.create();
    public static final Logger LOGGER = LogManager.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER =
            NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    @Deprecated
    public static final Random RANDOM = new Random();

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

        setModId(MOD_ID);

        new register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::gatherData);
        eventBus.addListener(this::modelRegistryEvent);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CreateAndFoodClientConfigs.SPEC, "create_and_food-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CreateAndFoodCommonConfigs.SPEC, "create_and_food-common.toml");
        CAFConfigs.register(modLoadingContext);

        MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        MinecraftForge.EVENT_BUS.register(this);


        DistExecutor.safeRunWhenOn(Dist.CLIENT, () ->{
                return CreateAndFoodClient::new;
        });
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    public static ResourceLocation asFluid(String path) {
        return new ResourceLocation(MOD_ID, "fluid/" + path);
    }

    private void clientSetup(final FMLCommonSetupEvent event){
        new render(event);
        event.enqueueWork(CAFPonders::register);
    }

    @OnlyIn(Dist.CLIENT)
    public void modelRegistryEvent(ModelRegistryEvent event) {
        ModItemModelsProperties.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() ->{
            ModVillagers.registerPOIs();
            new Compostables();
            new Fueling();
            new Sample();
            new Weight();
            Sheets.addWoodType(ModWoodTypes.ALMOND);
        });

        ModMessages.register();
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
