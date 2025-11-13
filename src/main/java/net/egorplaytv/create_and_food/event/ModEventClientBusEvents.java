package net.egorplaytv.create_and_food.event;

import net.egorplaytv.create_and_food.CreateAndFood;
import net.egorplaytv.create_and_food.block.entity.ModBlockEntities;
import net.egorplaytv.create_and_food.block.entity.renderer.*;
import net.egorplaytv.create_and_food.entity.ModEntityTypes;
import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateAndFood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    public static class ClientModBusEvents{
    }
}
