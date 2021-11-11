package com.lx.jsblock;

import net.fabricmc.api.ModInitializer;

public class Initialize implements ModInitializer {

    @Override
    public void onInitialize() {
        System.out.println();
        System.out.println("[Joestu Block] Version 1.0.0");
        System.out.println();
        Blocks.registerBlocks();

    }
}
