package com.jsblock.packets;

import io.netty.buffer.Unpooled;
import mtr.RegistryClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class Client {
	public static void buyTicketC2S(int type) {
		final FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeInt(type);
		RegistryClient.sendToServer(new ResourceLocation("packet_buy_tickets"), packet);
	}
}