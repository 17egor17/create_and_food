package net.egorplaytv.caf.networking;

import net.egorplaytv.caf.CreateAndFood;
import net.egorplaytv.caf.networking.packet.FermantionBarrelFluidPacket;
import net.egorplaytv.caf.networking.packet.FermantionBarrelFluidPacketOut;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class CAFMessages {
    private static SimpleChannel INSTANCE;
    private static int packedId = 0;
    private static int id() {
        return packedId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(CreateAndFood.MOD_ID, "message"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(FermantionBarrelFluidPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FermantionBarrelFluidPacket::new)
                .encoder(FermantionBarrelFluidPacket::toBytes)
                .consumer(FermantionBarrelFluidPacket::handle)
                .add();
        net.messageBuilder(FermantionBarrelFluidPacketOut.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FermantionBarrelFluidPacketOut::new)
                .encoder(FermantionBarrelFluidPacketOut::toBytes)
                .consumer(FermantionBarrelFluidPacketOut::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}