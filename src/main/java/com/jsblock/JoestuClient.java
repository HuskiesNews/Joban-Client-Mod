package com.jsblock;

import com.jsblock.blocks.PIDS4;
import com.jsblock.blocks.PIDS5;
import com.jsblock.render.*;
import minecraftmappings.UtilitiesClient;
import mtr.render.RenderPIDS;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class JoestuClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        /* Allow transparent texture for the block */
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.CEILING_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ENQUIRY_MACHINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.LIGHT_2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.STATION_NAME_TALL_STAND, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SUBSIDY_MACHINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TRESPASS_SIGN_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.WATER_MACHINE_1, RenderLayer.getCutout());

        /* Register entity that requires to be rendered, and pointing to the corresponding method */
        UtilitiesClient.registerTileEntityRenderer(Joestu.DEPARTURE_TIMER_TILE_ENTITY, RenderDepartureTimer::new);
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_RED_ENTITY_1, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFFFF0000, false));
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_RED_ENTITY_2, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFFFF0000, true));
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_BLUE_ENTITY, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFF0000FF, false));
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_GREEN_ENTITY, dispatcher -> new RenderConstantSignalLight<>(dispatcher, true, 0xFF00FF00,false));
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_INVERTED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, true,true, 0xFF0000FF));
        UtilitiesClient.registerTileEntityRenderer(Joestu.SIGNAL_LIGHT_INVERTED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, true,false,0xFF00FF00));
        UtilitiesClient.registerTileEntityRenderer(Joestu.STATION_NAME_TALL_STAND_TILE_ENTITY, RenderStationNameTall::new);
        UtilitiesClient.registerTileEntityRenderer(Joestu.PIDS_4_TILE_ENTITY, dispatcher -> new RenderPIDS<>(dispatcher, PIDS4.TileEntityBlockPIDS4.MAX_ARRIVALS, 1, 9.5F, 6, 8.8F, 30, true, false, false, 0xFF9900, 0xFF9900));
        UtilitiesClient.registerTileEntityRenderer(Joestu.PIDS_5_TILE_ENTITY, dispatcher -> new RenderRVPIDS<>(dispatcher, PIDS5.TileEntityBlockPIDS5.MAX_ARRIVALS, 6, 8.25F, 6, 11F, 20, true, false, true, 0x000000, 0x000000));
        registerStationColor(Blocks.STATION_NAME_TALL_STAND);
    }

    /* Method copied from MTR Mod as it's private */
    private static void registerStationColor(Block block) {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            final int defaultColor = 0x7F7F7F;
            if (pos != null) {
                return mtr.gui.ClientData.STATIONS.stream().filter(station1 -> station1.inArea(pos.getX(), pos.getZ())).findFirst().map(station2 -> station2.color).orElse(defaultColor);
            } else {
                return defaultColor;
            }
        }, block);
    }
}
