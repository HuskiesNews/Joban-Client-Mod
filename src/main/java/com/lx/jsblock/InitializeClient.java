package com.lx.jsblock;

import com.lx.jsblock.render.RenderSignalLight;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class InitializeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HELPLINE_3, RenderLayer.getCutout());
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_RED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFFFF0000, false,false, 0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_RED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFFFF0000, false,true, 0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_BLUE_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFF0000FF, false, false,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_GREEN_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0xFF00FF00, false,false,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_1, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0, true,true,0xFF0000FF));
        BlockEntityRendererRegistry.INSTANCE.register(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_2, dispatcher -> new RenderSignalLight<>(dispatcher, true, 0, true,false,0xFF00FF00));
    }
}
