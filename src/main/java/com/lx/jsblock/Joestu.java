package com.lx.jsblock;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Joestu implements ModInitializer {
    public static final String MOD_ID = "jsblock";
    public static final Logger LOGGER = LogManager.getLogger("Joestu Client");
    @Override
    public void onInitialize() {
        LOGGER.info("-----Version Beta-----");
        Blocks.registerBlocks();
    }
}
