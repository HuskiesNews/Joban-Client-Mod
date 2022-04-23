package com.jsblock.packets;

import com.jsblock.blocks.PIDSRVBase;
import com.jsblock.blocks.SoundLooper;
import io.netty.buffer.Unpooled;
import mtr.Registry;
import mtr.data.SerializedDataBase;
import mtr.mappings.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import static com.jsblock.packets.IPacketJoban.PACKET_OPEN_SOUND_LOOPER_SCREEN;

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

	public static void receiveRVPIDSMessageC2S(MinecraftServer minecraftServer, ServerPlayer player, FriendlyByteBuf packet) {
		final BlockPos pos1 = packet.readBlockPos();
		final BlockPos pos2 = packet.readBlockPos();
		final int maxArrivals = packet.readInt();
		final String[] messages = new String[maxArrivals];
		final boolean[] hideArrivals = new boolean[maxArrivals];
		for (int i = 0; i < maxArrivals; i++) {
			messages[i] = packet.readUtf(SerializedDataBase.PACKET_STRING_READ_LENGTH);
			hideArrivals[i] = packet.readBoolean();
		}
		final boolean hidePlatformNumber = packet.readBoolean();
		System.out.println(hidePlatformNumber + " Server");
		minecraftServer.execute(() -> {
			final BlockEntity entity1 = player.level.getBlockEntity(pos1);
			if (entity1 instanceof PIDSRVBase.TileEntityBlockPIDSBase) {
				((PIDSRVBase.TileEntityBlockPIDSBase) entity1).setData(messages, hideArrivals, hidePlatformNumber);
			}
			final BlockEntity entity2 = player.level.getBlockEntity(pos2);
			if (entity2 instanceof PIDSRVBase.TileEntityBlockPIDSBase) {
				((PIDSRVBase.TileEntityBlockPIDSBase) entity2).setData(messages, hideArrivals, hidePlatformNumber);
			}
		});
	}

	public static void openSoundLooperScreenS2C(ServerPlayer player, BlockPos pos) {
		final FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeBlockPos(pos);
		Registry.sendToPlayer(player, PACKET_OPEN_SOUND_LOOPER_SCREEN, packet);
	}

	public static void receiveSoundLooperC2S(MinecraftServer minecraftServer, ServerPlayer player, FriendlyByteBuf packet) {
		final BlockPos pos = packet.readBlockPos();
		final String soundId = packet.readUtf();
		final int soundCategory = packet.readInt();
		final int interval = packet.readInt();
		final float volume = packet.readFloat();
		final boolean needRedstone = packet.readBoolean();
		minecraftServer.execute(() -> {
			final BlockEntity entity = player.level.getBlockEntity(pos);
			if (entity instanceof SoundLooper.TileEntitySoundLooper) {
				((SoundLooper.TileEntitySoundLooper) entity).setData(soundId, soundCategory, interval, volume, needRedstone);
			}
		});
	}
}
