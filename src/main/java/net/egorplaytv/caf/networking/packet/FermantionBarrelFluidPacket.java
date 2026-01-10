package net.egorplaytv.caf.networking.packet;

import net.egorplaytv.caf.block.entity.custom.FermentationBarrelBlockEntity;
import net.egorplaytv.caf.screen.FermentationBarrelMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FermantionBarrelFluidPacket {
    private final FluidStack fluid;
    private final BlockPos pos;

    public FermantionBarrelFluidPacket(FluidStack fluid, BlockPos pos) {
        this.fluid = fluid;
        this.pos = pos;
    }

    public FermantionBarrelFluidPacket(FriendlyByteBuf buf) {
        this.fluid = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluid);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof FermentationBarrelBlockEntity blockEntity) {
                blockEntity.setFluid(fluid);

                if (Minecraft.getInstance().player.containerMenu instanceof FermentationBarrelMenu menu &&
                    menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluid);
                }
            }
        });
        return true;
    }
}
