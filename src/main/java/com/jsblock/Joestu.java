package com.jsblock;

import com.jsblock.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static minecraftmappings.Utilities.registerTileEntity;

public class Joestu implements ModInitializer {
    public static final BlockEntityType<ButterflyLight.TileEntityButterFlyLight> BUTTERFLY_LIGHT_TILE_ENTITY = registerTileEntity("jsblock:butterfly_light", ButterflyLight.TileEntityButterFlyLight::new, Blocks.BUTTERFLY_LIGHT);
    public static final BlockEntityType<DepartureTimer.TileEntityDepartureTimer> DEPARTURE_TIMER_TILE_ENTITY = registerTileEntity("jsblock:departure_timer", DepartureTimer.TileEntityDepartureTimer::new, Blocks.DEPARTURE_TIMER);
    public static final BlockEntityType<StationNameTallStand.TileEntityStationNameTallStand> STATION_NAME_TALL_STAND_TILE_ENTITY = registerTileEntity("jsblock:station_name_tall_stand", StationNameTallStand.TileEntityStationNameTallStand::new, Blocks.STATION_NAME_TALL_STAND);
    public static final BlockEntityType<SignalLightRed1.TileEntitySignalLightRed> SIGNAL_LIGHT_RED_ENTITY_1 = registerTileEntity("jsblock:signal_light_red_1", SignalLightRed1.TileEntitySignalLightRed::new, Blocks.SIGNAL_LIGHT_RED_1);
    public static final BlockEntityType<SignalLightRed2.TileEntitySignalLightRed2> SIGNAL_LIGHT_RED_ENTITY_2 = registerTileEntity("jsblock:signal_light_red_2", SignalLightRed2.TileEntitySignalLightRed2::new, Blocks.SIGNAL_LIGHT_RED_2);
    public static final BlockEntityType<SignalLightBlue.TileEntitySignalLightBlue> SIGNAL_LIGHT_BLUE_ENTITY = registerTileEntity("jsblock:signal_light_blue", SignalLightBlue.TileEntitySignalLightBlue::new, Blocks.SIGNAL_LIGHT_BLUE);
    public static final BlockEntityType<SignalLightGreen.TileEntitySignalLightGreen> SIGNAL_LIGHT_GREEN_ENTITY = registerTileEntity("jsblock:signal_light_green", SignalLightGreen.TileEntitySignalLightGreen::new, Blocks.SIGNAL_LIGHT_GREEN);
    public static final BlockEntityType<SignalLightInverted1.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_1 = registerTileEntity("jsblock:signal_light_inverted_1", SignalLightInverted1.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_1);
    public static final BlockEntityType<SignalLightInverted2.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_2 = registerTileEntity("jsblock:signal_light_inverted_2", SignalLightInverted2.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_2);

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
