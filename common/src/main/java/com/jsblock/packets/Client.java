package com.jsblock.packets;

import com.jsblock.blocks.SoundLooper;
import com.jsblock.gui.SoundLooperScreen;
import io.netty.buffer.Unpooled;
import mtr.RegistryClient;
import mtr.mappings.UtilitiesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;

import static com.jsblock.packets.IPacketJoban.PACKET_UPDATE_SOUND_LOOPER;

public class Client {
	public static void sendRVPIDSConfigC2S(BlockPos pos1, BlockPos pos2, String[] messages, boolean[] hideArrival, boolean hidePlatformNumber) {
		FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeBlockPos(pos1);
		packet.writeBlockPos(pos2);
		packet.writeInt(messages.length);
		for(int i = 0; i < messages.length; ++i) {
			packet.writeUtf(messages[i]);
			packet.writeBoolean(hideArrival[i]);
		}

		packet.writeBoolean(hidePlatformNumber);
		RegistryClient.sendToServer(IPacketJoban.PACKET_UPDATE_RV_PIDS_CONFIG, packet);
	}

	public static void openSoundLooperScreenS2C(Minecraft minecraftClient, BlockPos pos) {
		minecraftClient.execute(() -> {
			if (minecraftClient.level != null) {
				final BlockEntity entity = minecraftClient.level.getBlockEntity(pos);
				if (entity instanceof SoundLooper.TileEntitySoundLooper) {
					UtilitiesClient.setScreen(minecraftClient, new SoundLooperScreen(pos));
				}
			}
		});;
	}

	public static void sendSoundLooperC2S(BlockPos pos, int categoryIndex, String soundId, int loopInterval, float volume, boolean needRedstone) {
		final FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeBlockPos(pos);
		packet.writeUtf(soundId);
		packet.writeInt(categoryIndex);
		packet.writeInt(loopInterval);
		packet.writeFloat(volume);
		packet.writeBoolean(needRedstone);
		RegistryClient.sendToServer(PACKET_UPDATE_SOUND_LOOPER, packet);
	}
}
