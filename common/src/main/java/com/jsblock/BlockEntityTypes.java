package com.jsblock;

import com.jsblock.blocks.*;
import mtr.mappings.RegistryUtilities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface BlockEntityTypes {

	BlockEntityType<ButterflyLight.TileEntityButterFlyLight> BUTTERFLY_LIGHT_TILE_ENTITY = RegistryUtilities.getBlockEntityType(ButterflyLight.TileEntityButterFlyLight::new, Blocks.BUTTERFLY_LIGHT);
	BlockEntityType<DepartureTimer.TileEntityDepartureTimer> DEPARTURE_TIMER_TILE_ENTITY = RegistryUtilities.getBlockEntityType(DepartureTimer.TileEntityDepartureTimer::new, Blocks.DEPARTURE_TIMER);
	BlockEntityType<SoundLooper.TileEntitySoundLooper> SOUND_LOOPER_TILE_ENTITY = RegistryUtilities.getBlockEntityType(SoundLooper.TileEntitySoundLooper::new, Blocks.SOUND_LOOPER);
	BlockEntityType<StationNameTallStand.TileEntityStationNameTallStand> STATION_NAME_TALL_STAND_TILE_ENTITY = RegistryUtilities.getBlockEntityType(StationNameTallStand.TileEntityStationNameTallStand::new, Blocks.STATION_NAME_TALL_STAND);
	BlockEntityType<SignalLightRed1.TileEntitySignalLightRed> SIGNAL_LIGHT_RED_ENTITY_1 = RegistryUtilities.getBlockEntityType(SignalLightRed1.TileEntitySignalLightRed::new, Blocks.SIGNAL_LIGHT_RED_1);
	BlockEntityType<SignalLightRed2.TileEntitySignalLightRed2> SIGNAL_LIGHT_RED_ENTITY_2 = RegistryUtilities.getBlockEntityType(SignalLightRed2.TileEntitySignalLightRed2::new, Blocks.SIGNAL_LIGHT_RED_2);
	BlockEntityType<SignalLightBlue.TileEntitySignalLightBlue> SIGNAL_LIGHT_BLUE_ENTITY = RegistryUtilities.getBlockEntityType(SignalLightBlue.TileEntitySignalLightBlue::new, Blocks.SIGNAL_LIGHT_BLUE);
	BlockEntityType<SignalLightGreen.TileEntitySignalLightGreen> SIGNAL_LIGHT_GREEN_ENTITY = RegistryUtilities.getBlockEntityType(SignalLightGreen.TileEntitySignalLightGreen::new, Blocks.SIGNAL_LIGHT_GREEN);
	BlockEntityType<SignalLightInverted1.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_1 = RegistryUtilities.getBlockEntityType(SignalLightInverted1.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_1);
	BlockEntityType<SignalLightInverted2.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_2 = RegistryUtilities.getBlockEntityType(SignalLightInverted2.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_2);
	BlockEntityType<PIDS1A.TileEntityBlockPIDS1A> PIDS_1A_TILE_ENTITY = RegistryUtilities.getBlockEntityType(PIDS1A.TileEntityBlockPIDS1A::new, Blocks.PIDS_1A);
	BlockEntityType<PIDS4.TileEntityBlockPIDS4> PIDS_4_TILE_ENTITY = RegistryUtilities.getBlockEntityType(PIDS4.TileEntityBlockPIDS4::new, Blocks.PIDS_4);
	BlockEntityType<PIDSRV.TileEntityBlockPIDS5> PIDS_5_TILE_ENTITY = RegistryUtilities.getBlockEntityType(PIDSRV.TileEntityBlockPIDS5::new, Blocks.PIDS_RV);
	BlockEntityType<KCRNameSign.TileEntityKCRNameSign> KCR_NAME_SIGN_TILE_ENTITY = RegistryUtilities.getBlockEntityType(KCRNameSign.TileEntityKCRNameSign::new, Blocks.KCR_NAME_SIGN);
}
