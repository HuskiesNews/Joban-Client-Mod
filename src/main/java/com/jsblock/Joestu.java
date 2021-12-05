package com.jsblock;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Joestu implements ModInitializer {
    public static final String MOD_ID = "jsblock";
    public static final Logger LOGGER = LogManager.getLogger("Joestu Client");

    @Override
    public void onInitialize() {
        // RESERVED FOR TICKETS
        //ServerPlayNetworking.registerGlobalReceiver(new Identifier("packet_buy_tickets"), (minecraftServer, player, handler, packet, sender) -> PacketTrainDataGuiServer.receiveAddBalanceC2S(minecraftServer, player, packet));

        LOGGER.info("[Joestu Client] Version 1.0.2 (1.16)");

        /* Try to register the block */
        try {
            Blocks.registerBlocks();
        } catch (NoClassDefFoundError error) {
            /* If we end up with a NoClassDefFoundError, this means some method we use are not found, presumably MTR Mod */
            /* Let's warn the user beforehand, as the game will crash eventually */
            LOGGER.error("[Joestu Client] Cannot find class " + error.getMessage() + " which is required by this mod!");
            LOGGER.info("");
            LOGGER.error("[Joestu Client] Please make sure you're on the latest unofficial MTR Mod (https://discord.gg/PVZ2nfUaTW).");
            LOGGER.info("");
            LOGGER.error("[Joestu Client] Otherwise, contact a staff on https://discord.gg/ue7reMvMc2.");

            /* Give them 20s to read */
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
