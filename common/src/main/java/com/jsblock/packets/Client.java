package com.jsblock.packets;

import com.jsblock.blocks.SoundLooper;
import com.jsblock.gui.SoundLooperScreen;
import io.netty.buffer.Unpooled;
import mtr.RegistryClient;
import mtr.mappings.UtilitiesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

import static com.jsblock.packets.IPacketJoban.PACKET_UPDATE_SOUND_LOOPER;

public class Client {
	public static void buyTicketC2S(int type) {
		final FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeInt(type);
		RegistryClient.sendToServer(new ResourceLocation("packet_buy_tickets"), packet);
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

	public static void sendSoundLooperC2S(BlockPos pos, int categoryIndex, String soundId, int loopInterval) {
		final FriendlyByteBuf packet = new FriendlyByteBuf(Unpooled.buffer());
		packet.writeBlockPos(pos);
		packet.writeUtf(soundId);
		packet.writeInt(categoryIndex);
		packet.writeInt(loopInterval);
		RegistryClient.sendToServer(PACKET_UPDATE_SOUND_LOOPER, packet);
	}
}
