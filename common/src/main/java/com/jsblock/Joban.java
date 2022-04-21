package com.jsblock;

import com.jsblock.packets.Server;
import mtr.Registry;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;

import static com.jsblock.packets.IPacketJoban.PACKET_UPDATE_SOUND_LOOPER;

public class Joban {
	public static final String MOD_ID = "jsblock";
	public static final String VERSION = "1.0.7";
	public static final Logger LOGGER = LogManager.getLogger("Joban Client");

	public static void init(
			RegisterBlockItem registerBlockItem,
			BiConsumer<String, BlockEntityType<? extends BlockEntityMapper>> registerBlockEntityType
	) {
		LOGGER.info("[Joban Client] Version " + VERSION);

		/* Try to register the block */
		try {
			registerBlockItem.accept("auto_iron_door", Blocks.AUTO_IRON_DOOR, ItemGroups.MAIN);
			registerBlockItem.accept("butterfly_light", Blocks.BUTTERFLY_LIGHT, ItemGroups.MAIN);
			registerBlockItem.accept("bufferstop_1", Blocks.BUFFERSTOP_1, ItemGroups.MAIN);
			registerBlockItem.accept("ceiling_1", Blocks.CEILING_1, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_1", Blocks.CIRCLE_WALL_1, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_2", Blocks.CIRCLE_WALL_2, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_3", Blocks.CIRCLE_WALL_3, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_4", Blocks.CIRCLE_WALL_4, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_5", Blocks.CIRCLE_WALL_5, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_6", Blocks.CIRCLE_WALL_6, ItemGroups.MAIN);
			registerBlockItem.accept("circle_wall_7", Blocks.CIRCLE_WALL_7, ItemGroups.MAIN);
			registerBlockItem.accept("departure_pole", Blocks.DEPARTURE_POLE, ItemGroups.MAIN);
			registerBlockItem.accept("departure_timer", Blocks.DEPARTURE_TIMER, ItemGroups.MAIN);
			registerBlockItem.accept("emg_stop_1", Blocks.EMG_STOP_1, ItemGroups.MAIN);
			registerBlockItem.accept("enquiry_machine_1", Blocks.ENQUIRY_MACHINE_1, ItemGroups.MAIN);
			registerBlockItem.accept("exit_sign_1", Blocks.EXIT_SIGN_1O, ItemGroups.MAIN);
			registerBlockItem.accept("exit_sign_1e", Blocks.EXIT_SIGN_1E, ItemGroups.MAIN);
			registerBlockItem.accept("helpline_1", Blocks.HELPLINE_1, ItemGroups.MAIN);
			registerBlockItem.accept("helpline_2", Blocks.HELPLINE_2, ItemGroups.MAIN);
			registerBlockItem.accept("helpline_3", Blocks.HELPLINE_3, ItemGroups.MAIN);
			registerBlockItem.accept("helpline_4", Blocks.HELPLINE_4, ItemGroups.MAIN);
			registerBlockItem.accept("helpline_5", Blocks.HELPLINE_5, ItemGroups.MAIN);
			registerBlockItem.accept("kcr_name_sign", Blocks.KCR_NAME_SIGN, ItemGroups.MAIN);
			registerBlockItem.accept("kcr_emg_stop_sign", Blocks.KCR_EMG_STOP_SIGN, ItemGroups.MAIN);
			registerBlockItem.accept("light_1", Blocks.LIGHT_1, ItemGroups.MAIN);
			registerBlockItem.accept("light_2", Blocks.LIGHT_2, ItemGroups.MAIN);
			registerBlockItem.accept("light_block", Blocks.LIGHT_BLOCK, ItemGroups.MAIN);
			registerBlockItem.accept("op_button", Blocks.OP_BUTTONS, ItemGroups.MAIN);
			registerBlockItem.accept("pids_1a", Blocks.PIDS_1A, ItemGroups.PIDS);
			registerBlockItem.accept("pids_4", Blocks.PIDS_4, ItemGroups.PIDS);
			registerBlockItem.accept("pids_rv", Blocks.PIDS_RV, ItemGroups.PIDS);
			registerBlockItem.accept("rv_pids_pole", Blocks.RV_PIDS_POLE, ItemGroups.PIDS);
			registerBlockItem.accept("station_name_tall_stand", Blocks.STATION_NAME_TALL_STAND, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_red_1", Blocks.SIGNAL_LIGHT_RED_1, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_red_2", Blocks.SIGNAL_LIGHT_RED_2, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_green", Blocks.SIGNAL_LIGHT_GREEN, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_blue", Blocks.SIGNAL_LIGHT_BLUE, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_inverted_1", Blocks.SIGNAL_LIGHT_INVERTED_1, ItemGroups.MAIN);
			registerBlockItem.accept("signal_light_inverted_2", Blocks.SIGNAL_LIGHT_INVERTED_2, ItemGroups.MAIN);
			registerBlockItem.accept("sound_looper", Blocks.SOUND_LOOPER, ItemGroups.MAIN);
			registerBlockItem.accept("subsidy_machine_1", Blocks.SUBSIDY_MACHINE_1, ItemGroups.MAIN);
			registerBlockItem.accept("train_model_e44", Blocks.MODEL_E44, ItemGroups.MAIN);
			registerBlockItem.accept("ticket_barrier_1_entrance", Blocks.TICKET_BARRIER_1_ENTRANCE, ItemGroups.MAIN);
			registerBlockItem.accept("ticket_barrier_1_exit", Blocks.TICKET_BARRIER_1_EXIT, ItemGroups.MAIN);
			registerBlockItem.accept("ticket_barrier_1_bare", Blocks.TICKET_BARRIER_1_DECOR, ItemGroups.MAIN);
			registerBlockItem.accept("trespass_sign_1", Blocks.TRESPASS_SIGN_1, ItemGroups.MAIN);
			registerBlockItem.accept("trespass_sign_2", Blocks.TRESPASS_SIGN_2, ItemGroups.MAIN);
			registerBlockItem.accept("water_machine_1", Blocks.WATER_MACHINE_1, ItemGroups.MAIN);

			registerBlockEntityType.accept("butterfly_light", BlockEntityTypes.BUTTERFLY_LIGHT_TILE_ENTITY);
			registerBlockEntityType.accept("departure_timer", BlockEntityTypes.DEPARTURE_TIMER_TILE_ENTITY);
			registerBlockEntityType.accept("light_block", BlockEntityTypes.LIGHT_BLOCK_TILE_ENTITY);
			registerBlockEntityType.accept("station_name_tall_stand", BlockEntityTypes.STATION_NAME_TALL_STAND_TILE_ENTITY);
			registerBlockEntityType.accept("signal_light_red_1", BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_1);
			registerBlockEntityType.accept("signal_light_red_2", BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_2);
			registerBlockEntityType.accept("signal_light_blue", BlockEntityTypes.SIGNAL_LIGHT_BLUE_ENTITY);
			registerBlockEntityType.accept("signal_light_green", BlockEntityTypes.SIGNAL_LIGHT_GREEN_ENTITY);
			registerBlockEntityType.accept("signal_light_inverted_1", BlockEntityTypes.SIGNAL_LIGHT_INVERTED_ENTITY_1);
			registerBlockEntityType.accept("signal_light_inverted_2", BlockEntityTypes.SIGNAL_LIGHT_INVERTED_ENTITY_2);
			registerBlockEntityType.accept("sound_looper", BlockEntityTypes.SOUND_LOOPER_TILE_ENTITY);
			registerBlockEntityType.accept("pids_4", BlockEntityTypes.PIDS_1A_TILE_ENTITY);
			registerBlockEntityType.accept("pids_5", BlockEntityTypes.PIDS_5_TILE_ENTITY);
			registerBlockEntityType.accept("pids_4a", BlockEntityTypes.PIDS_4_TILE_ENTITY);
			registerBlockEntityType.accept("kcr_name_sign", BlockEntityTypes.KCR_NAME_SIGN_TILE_ENTITY);
		} catch (NoClassDefFoundError error) {
			/* If we end up with a NoClassDefFoundError, this means some method we use are not found, presumably MTR Mod */
			/* Let's warn the user beforehand, as the game will crash eventually */
			LOGGER.error("[Joban Client] Cannot find class " + error.getMessage() + " which is required by this mod!");
			LOGGER.error("[Joban Client] Make sure you're running the correct version of MTR Mod and Minecraft Version.");
			LOGGER.info("");
			/* Give them 20s to read */
			try {
				Thread.sleep(20000);
			} catch (InterruptedException ignored) {
			}
		}

		Registry.registerNetworkReceiver(PACKET_UPDATE_SOUND_LOOPER, Server::receiveSoundLooperC2S);
	}

	@FunctionalInterface
	public interface RegisterBlockItem {
		void accept(String string, Block block, CreativeModeTab tab);
	}
}
