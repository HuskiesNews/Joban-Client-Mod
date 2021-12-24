package com.jsblock.fabric;

import com.jsblock.mappings.FabricRegistryUtilities;
import mtr.gui.ClientData;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.BlockEntityRendererMapper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Consumer;
import java.util.function.Function;

public class RegistryClientImpl {

	public static void registerBlockRenderType(RenderType type, Block block) {
		BlockRenderLayerMap.INSTANCE.putBlock(block, type);
	}

	public static <T extends BlockEntityMapper> void registerTileEntityRenderer(BlockEntityType<T> type, Function<BlockEntityRenderDispatcher, BlockEntityRendererMapper<T>> function) {
		FabricRegistryUtilities.registerTileEntityRenderer(type, function);
	}

	public static void registerBlockColors(Block block) {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			final int defaultColor = 0x7F7F7F;
			if (pos != null) {
				return ClientData.STATIONS.stream().filter(station1 -> station1.inArea(pos.getX(), pos.getZ())).findFirst().map(station2 -> station2.color).orElse(defaultColor);
			} else {
				return defaultColor;
			}
		}, block);
	}

	public static void registerNetworkReceiver(ResourceLocation resourceLocation, Consumer<FriendlyByteBuf> consumer) {
		ClientPlayNetworking.registerGlobalReceiver(resourceLocation, (client, handler, packet, responseSender) -> consumer.accept(packet));
	}

	public static void sendToServer(ResourceLocation id, FriendlyByteBuf packet) {
		ClientPlayNetworking.send(id, packet);
	}
}
