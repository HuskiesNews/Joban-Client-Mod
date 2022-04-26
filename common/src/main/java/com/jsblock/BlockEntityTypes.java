package com.jsblock;

import com.jsblock.blocks.*;
import mtr.RegistryObject;
import mtr.mappings.RegistryUtilities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface BlockEntityTypes {

	RegistryObject<BlockEntityType<ButterflyLight.TileEntityButterFlyLight>> BUTTERFLY_LIGHT_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(ButterflyLight.TileEntityButterFlyLight::new, Blocks.BUTTERFLY_LIGHT.get()));
	RegistryObject<BlockEntityType<DepartureTimer.TileEntityDepartureTimer>> DEPARTURE_TIMER_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(DepartureTimer.TileEntityDepartureTimer::new, Blocks.DEPARTURE_TIMER.get()));
	RegistryObject<BlockEntityType<LightBlock.TileEntityLightBlock>> LIGHT_BLOCK_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(LightBlock.TileEntityLightBlock::new, Blocks.LIGHT_BLOCK.get()));
	RegistryObject<BlockEntityType<SoundLooper.TileEntitySoundLooper>> SOUND_LOOPER_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SoundLooper.TileEntitySoundLooper::new, Blocks.SOUND_LOOPER.get()));
	RegistryObject<BlockEntityType<StationNameTallStand.TileEntityStationNameTallStand>> STATION_NAME_TALL_STAND_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(StationNameTallStand.TileEntityStationNameTallStand::new, Blocks.STATION_NAME_TALL_STAND.get()));
	RegistryObject<BlockEntityType<SignalLightRed1.TileEntitySignalLightRed>> SIGNAL_LIGHT_RED_ENTITY_1 =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightRed1.TileEntitySignalLightRed::new, Blocks.SIGNAL_LIGHT_RED_1.get()));
	RegistryObject<BlockEntityType<SignalLightRed2.TileEntitySignalLightRed2>> SIGNAL_LIGHT_RED_ENTITY_2 =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightRed2.TileEntitySignalLightRed2::new, Blocks.SIGNAL_LIGHT_RED_2.get()));
	RegistryObject<BlockEntityType<SignalLightBlue.TileEntitySignalLightBlue>> SIGNAL_LIGHT_BLUE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightBlue.TileEntitySignalLightBlue::new, Blocks.SIGNAL_LIGHT_BLUE.get()));
	RegistryObject<BlockEntityType<SignalLightGreen.TileEntitySignalLightGreen>> SIGNAL_LIGHT_GREEN_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightGreen.TileEntitySignalLightGreen::new, Blocks.SIGNAL_LIGHT_GREEN.get()));
	RegistryObject<BlockEntityType<SignalLightInverted1.TileEntitySignalLightInverted>> SIGNAL_LIGHT_INVERTED_ENTITY_1 =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightInverted1.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_1.get()));
	RegistryObject<BlockEntityType<SignalLightInverted2.TileEntitySignalLightInverted>> SIGNAL_LIGHT_INVERTED_ENTITY_2 =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(SignalLightInverted2.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_2.get()));
	RegistryObject<BlockEntityType<PIDS1A.TileEntityBlockPIDS1A>> PIDS_1A_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(PIDS1A.TileEntityBlockPIDS1A::new, Blocks.PIDS_1A.get()));
	RegistryObject<BlockEntityType<PIDS4.TileEntityBlockPIDS4>> PIDS_4_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(PIDS4.TileEntityBlockPIDS4::new, Blocks.PIDS_4.get()));
	RegistryObject<BlockEntityType<PIDSRV.TileEntityBlockPIDSRV>> PIDS_RV_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(PIDSRV.TileEntityBlockPIDSRV::new, Blocks.PIDS_RV_TCL.get()));
	RegistryObject<BlockEntityType<PIDSRVSIL.TileEntityBlockPIDSSIL>> PIDS_RV_SIL_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(PIDSRVSIL.TileEntityBlockPIDSSIL::new, Blocks.PIDS_RV_SIL.get()));

	RegistryObject<BlockEntityType<KCRNameSign.TileEntityKCRNameSign>> KCR_NAME_SIGN_TILE_ENTITY =new RegistryObject<>(()-> RegistryUtilities.getBlockEntityType(KCRNameSign.TileEntityKCRNameSign::new, Blocks.KCR_NAME_SIGN.get()));
}
