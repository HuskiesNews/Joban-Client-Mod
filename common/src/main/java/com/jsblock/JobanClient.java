package com.jsblock;

import com.jsblock.blocks.PIDS1A;
import com.jsblock.blocks.PIDS4;
import com.jsblock.blocks.PIDSRVSIL;
import com.jsblock.blocks.PIDSRV;
import com.jsblock.config.ClientConfig;
import com.jsblock.gui.TicketMachineScreen;
import com.jsblock.packets.Client;
import com.jsblock.packets.IPacketJoban;
import com.jsblock.render.*;
import mtr.RegistryClient;
import mtr.mappings.UtilitiesClient;
import mtr.packet.IPacket;
import mtr.render.RenderPIDS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;

public class JobanClient {

	public static void init() {
		ClientConfig.loadConfig();
		if(ClientConfig.getRenderDisabled()) {
			Joban.LOGGER.info("[Joban Client] Rendering for all JCM block is disabled.");
		}
		/* Allow transparent texture for the block */
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.AUTO_IRON_DOOR);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.BUFFERSTOP_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.CEILING_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.ENQUIRY_MACHINE_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.HELPLINE_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.HELPLINE_2);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.HELPLINE_3);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.HELPLINE_4);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.HELPLINE_5);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.KCR_EMG_STOP_SIGN);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.KCR_NAME_SIGN);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.LIGHT_2);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.PIDS_RV_SIL);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.STATION_NAME_TALL_STAND);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.SUBSIDY_MACHINE_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.TRESPASS_SIGN_1);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.TICKET_BARRIER_1_ENTRANCE);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.TICKET_BARRIER_1_EXIT);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.TICKET_BARRIER_1_DECOR);
		RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.WATER_MACHINE_1);

		/* Register entity that requires to be rendered, and pointing to the corresponding method */
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.DEPARTURE_TIMER_TILE_ENTITY, RenderDepartureTimer::new);
		/* Sprite does not rotate reliably */
		//RegistryClient.registerTileEntityRenderer(BlockEntityTypes.LIGHT_BLOCK_TILE_ENTITY, RenderLightBlockParticle::new);
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_1, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFFFF0000, false));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_2, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFFFF0000, true));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_BLUE_ENTITY, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFF0000FF, false));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_GREEN_ENTITY, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFF00FF00, false));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_INVERTED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, true, true, 0xFF0000FF));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SIGNAL_LIGHT_INVERTED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, true, false, 0xFF00FF00));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.STATION_NAME_TALL_STAND_TILE_ENTITY, RenderStationNameTall::new);
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.PIDS_1A_TILE_ENTITY, dispatcher -> new RenderPIDS<>(dispatcher, PIDS1A.TileEntityBlockPIDS1A.MAX_ARRIVALS, 1, 9.5F, 6, 8.8F, 30, true, false, false, 0xFF9900, 0xFF9900));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.PIDS_4_TILE_ENTITY, dispatcher -> new RenderLCDPIDS<>(dispatcher, PIDS4.TileEntityBlockPIDS4.MAX_ARRIVALS, 5.7F, 9.5F, 6, 11.5F, 21, true, false, false, 0xEFE29E, 1.25F, false, "pids_tkl"));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.PIDS_RV_TILE_ENTITY, dispatcher -> new RenderRVPIDS<>(dispatcher, PIDSRV.TileEntityBlockPIDSRV.MAX_ARRIVALS, 6, 8.25F, 6, 11F, 20, true, false, true, 0x000000, 0));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.PIDS_RV_SIL_TILE_ENTITY, dispatcher -> new RenderRVPIDS<>(dispatcher, PIDSRVSIL.TileEntityBlockPIDSSIL.MAX_ARRIVALS, 6F, 11.7F, 2.45F, 11F, 20.7F, true, false, true, 0x000000, 22.5F));
		RegistryClient.registerTileEntityRenderer(BlockEntityTypes.KCR_NAME_SIGN_TILE_ENTITY, RenderKCRStationName::new);


		RegistryClient.registerBlockColors(Blocks.STATION_NAME_TALL_STAND);
		RegistryClient.registerNetworkReceiver(IPacket.PACKET_OPEN_TICKET_MACHINE_SCREEN, packet -> {
			final int balance = packet.readInt();
			final Minecraft minecraft = Minecraft.getInstance();
			minecraft.execute(() -> UtilitiesClient.setScreen(minecraft, new TicketMachineScreen(balance)));
		});

		/* Register Client Packet with the packet id, and points to the corresponding action in the client */
		RegistryClient.registerNetworkReceiver(IPacketJoban.PACKET_OPEN_SOUND_LOOPER_SCREEN, packet -> {
			final Minecraft minecraft = Minecraft.getInstance();
			BlockPos pos = packet.readBlockPos();
			minecraft.execute(() -> Client.openSoundLooperScreenS2C(minecraft, pos));
		});
	}
}
