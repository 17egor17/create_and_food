package net.egorplaytv.create_and_food.event;

import net.egorplaytv.create_and_food.event.loot.AddModLootTableModifier;
import net.egorplaytv.create_and_food.event.loot.RyeSeedsFromGrassAdditionModifier;
import net.egorplaytv.create_and_food.particle.CAFParticles;
import net.egorplaytv.create_and_food.particle.custom.SoulParticles;
import net.egorplaytv.create_and_food.recipe.FermentationBarrelRecipe;
import net.egorplaytv.create_and_food.recipe.MarbleFurnaceRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

import static net.egorplaytv.create_and_food.CreateAndFood.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CAFEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        event.getRegistry().registerAll(
                new AddModLootTableModifier.Serializer().setRegistryName(new ResourceLocation(MOD_ID, "add_loot_table")),
                new RyeSeedsFromGrassAdditionModifier.Serealizer().setRegistryName(new ResourceLocation(MOD_ID, "rye_seeds_drop"))
        );
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, FermentationBarrelRecipe.Type.ID, FermentationBarrelRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, MarbleFurnaceRecipe.Type.ID, MarbleFurnaceRecipe.Type.INSTANCE);
    }
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(CAFParticles.SOUL_PARTICLES.get(), SoulParticles.Provider::new);
    }
}
