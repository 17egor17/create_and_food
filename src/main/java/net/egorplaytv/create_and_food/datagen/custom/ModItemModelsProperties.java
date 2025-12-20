package net.egorplaytv.create_and_food.datagen.custom;

import net.egorplaytv.create_and_food.item.ModItems;
import net.egorplaytv.create_and_food.item.custom.IngotItem;
import net.egorplaytv.create_and_food.item.custom.SequencedAssemblyItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

@OnlyIn(Dist.CLIENT)
public final class ModItemModelsProperties {

    public static final String TAG_DEGREE = "deg";
    public static final ResourceLocation DEGREE_PREDICATE_ID = new ResourceLocation(MOD_ID, "degree");
    public static final ResourceLocation SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID = new ResourceLocation(MOD_ID, "sequenced_assembly_progress");

    public static void init() {
        // Ingot Items
        registerIngotItem(ModItems.STEEL_INGOT.get());
        registerIngotItem(ModItems.GLOWING_BRASS_INGOT.get());
        registerIngotItem(ModItems.ALLOY_SOULS_INGOT.get());
        registerIngotItem(ModItems.TUNGSTEN_INGOT.get());
        registerIngotItem(ModItems.TANTALUM_INGOT.get());

        // Sequenced Assembly Progress Items
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_IRON_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_DIAMOND_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_NETHERITE_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_GOLDEN_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_STEEL_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_TANTALUM_KNIFE.get());
        registerSequencedAssemblyProgress(ModItems.INCOMPLETE_TUNGSTEN_KNIFE.get());
    }

    public static void registerIngotItem(Item item) {
        ItemProperties.register(item.asItem(), DEGREE_PREDICATE_ID,
                (is, level, p, s) -> is.getTag() != null ? is.getTag().getInt(TAG_DEGREE) : 0);
    }

    public static void registerSequencedAssemblyProgress(Item item) {
        SequencedAssemblyItem assemblyItem = (SequencedAssemblyItem) item;
        ItemProperties.register(item.asItem(), SEQUENCED_ASSEMBLY_PROGRESS_PREDICATE_ID,
                (is, level, p, s) -> assemblyItem.getStep(is));
    }
}
