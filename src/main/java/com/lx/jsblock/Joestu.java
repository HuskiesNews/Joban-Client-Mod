package com.lx.jsblock;

import net.fabricmc.api.ModInitializer;

public class Joestu implements ModInitializer {
    public static final String MOD_ID = "jsblock";

    @Override
    public void onInitialize() {
        System.out.println("[Joestu Block] Version Beta");
        System.out.println();
        Blocks.registerBlocks();
    }
}
