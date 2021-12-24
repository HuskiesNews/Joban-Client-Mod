package com.jsblock.packets;

import mtr.mappings.Utilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Server {
	public static void receiveTicketsC2S(MinecraftServer minecraftServer, ServerPlayer player, FriendlyByteBuf packet) {
		final int type = packet.readInt();
		final int emeralds = packet.readInt();

		minecraftServer.execute(() -> {
			final Level world = player.level;
			// TODO: Merge code from server tickets mod
			ContainerHelper.clearOrCountMatchingItems(Utilities.getInventory(player), itemStack -> itemStack.getItem() == Items.EMERALD, emeralds, false);
			world.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1, 1);
		});
	}
}
