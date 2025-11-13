package net.egorplaytv.create_and_food.event;

import net.egorplaytv.create_and_food.fluid.ModFluids;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void getFogDensity(EntityViewRenderEvent.RenderFogEvent event) {
        Camera camera = event.getCamera();
        Level level = Minecraft.getInstance().level;
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);
        if (camera.getPosition().y >= blockPos.getY() + fluidState.getHeight(level, blockPos))
            return;

        Fluid fluid = fluidState.getType();
        Entity entity = camera.getEntity();

        if (ModFluids.APPLE_VINEGAR.get()
                .isSame(fluid)){
            event.scaleFarPlaneDistance(1f / 2f);
            event.setCanceled(true);
            return;
        }

        if (ModFluids.COCOA_OIL.get()
                .isSame(fluid)){
            event.scaleFarPlaneDistance(1f / 32f);
            event.setCanceled(true);
            return;
        }

        if (ModFluids.WHITE_CHOCOLATE.get()
                .isSame(fluid)){
            event.scaleFarPlaneDistance(1f / 32f);
            event.setCanceled(true);
            return;
        }

        if (ModFluids.RED_GRAPE_JUICE.get()
                .isSame(fluid)){
            event.scaleFarPlaneDistance(1f / 32f);
            event.setCanceled(true);
            return;
        }

        if (entity.isSpectator())
            return;
    }

    @SubscribeEvent
    public static void getFogColor(EntityViewRenderEvent.FogColors event) {
        Camera info = event.getCamera();
        Level level = Minecraft.getInstance().level;
        BlockPos blockPos = info.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);
        if (info.getPosition().y > blockPos.getY() + fluidState.getHeight(level, blockPos))
            return;

        Fluid fluid = fluidState.getType();

        if (ModFluids.APPLE_VINEGAR.get()
                .isSame(fluid)){
            event.setRed(50 / 255f);
            event.setGreen(134 / 255f);
            event.setBlue(188 / 255f);
            return;
        }

        if (ModFluids.COCOA_OIL.get()
                .isSame(fluid)){
            event.setRed(213 / 255f);
            event.setGreen(200 / 255f);
            event.setBlue(158 / 255f);
            return;
        }

        if (ModFluids.WHITE_CHOCOLATE.get()
                .isSame(fluid)){
            event.setRed(234 / 255f);
            event.setGreen(227 / 255f);
            event.setBlue(187 / 255f);
            return;
        }

        if (ModFluids.RED_GRAPE_JUICE.get()
                .isSame(fluid)){
            event.setRed(179 / 255f);
            event.setGreen(11 / 255f);
            event.setBlue(21 / 255f);
            return;
        }
    }
}
