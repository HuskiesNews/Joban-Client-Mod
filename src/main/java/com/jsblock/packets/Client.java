package com.jsblock.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class Client {
    public static void buyTicketC2S(int type) {
        final PacketByteBuf packet = PacketByteBufs.create();
        packet.writeInt(type);
        ClientPlayNetworking.send(new Identifier("packet_buy_tickets"), packet);
    }
}
