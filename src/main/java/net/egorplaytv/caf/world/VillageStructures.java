package net.egorplaytv.caf.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import vectorwing.farmersdelight.common.Configuration;

import static vectorwing.farmersdelight.common.world.VillageStructures.addBuildingToPool;

public class VillageStructures {
    public VillageStructures() {
    }

    public static void addNewVillageBuilding(ServerAboutToStartEvent event) {
        if ((Boolean) Configuration.GENERATE_VILLAGE_COMPOST_HEAPS.get()) {
            Registry<StructureTemplatePool> templatePools = event.getServer().registryAccess().registry(Registry.TEMPLATE_POOL_REGISTRY).get();
            Registry<StructureProcessorList> processorLists = event.getServer().registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).get();
            addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/plains/houses"), "create_and_food:village/houses/plains_confectioner_house", 5);
            addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/snowy/houses"), "create_and_food:village/houses/snowy_confectioner_house", 3);
            addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/savanna/houses"), "create_and_food:village/houses/savanna_confectioner_house", 4);
            addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/desert/houses"), "create_and_food:village/houses/desert_confectioner_house", 3);
            addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/taiga/houses"), "create_and_food:village/houses/taiga_confectioner_house", 4);
        }
    }
}
