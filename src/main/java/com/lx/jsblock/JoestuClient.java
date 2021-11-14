package com.lx.jsblock;

import com.lx.jsblock.render.RenderSignalLight;
import com.lx.jsblock.render.RenderStationNameTall;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class JoestuClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.WATER_MACHINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.CEILING_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.STATION_NAME_TALL_STAND, RenderLayer.getCutout());
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_RED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFFFF0000, false,false, 0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_RED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFFFF0000, false,true, 0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_BLUE_ENTITY, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFF0000FF, false, false,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_GREEN_ENTITY, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFF00FF00, false,false,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0, true,true,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0, true,false,0xFF00FF00));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.STATION_NAME_TALL_STAND_TILE_ENTITY, RenderStationNameTall::new);
        registerStationColor(Blocks.STATION_NAME_TALL_STAND);
    }

    private static void registerStationColor(Block block) {
        /* Copied from MTR Mod as the method is private */
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
