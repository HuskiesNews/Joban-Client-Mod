package com.jsblock;

import com.jsblock.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;


public interface Blocks {
	Block AUTO_IRON_DOOR = new AutoDoor(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.IRON_DOOR));
	Block BUTTERFLY_LIGHT = new ButterflyLight(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.0f).lightLevel(state -> 4));
	Block BUFFERSTOP_1 = new Bufferstop1(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f).lightLevel(state -> 15).noOcclusion());
	Block CIRCLE_WALL_1 = new CircleWall();
	Block CIRCLE_WALL_2 = new CircleWall();
	Block CIRCLE_WALL_3 = new CircleWall();
	Block CIRCLE_WALL_4 = new CircleWall();
	Block CIRCLE_WALL_5 = new CircleWall();
	Block CIRCLE_WALL_6 = new CircleWall();
	Block CIRCLE_WALL_7 = new CircleWall();
	Block DEPARTURE_POLE = new DeparturePole(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.0f).noOcclusion());
	Block DEPARTURE_TIMER = new DepartureTimer(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.0f));
	Block SIGNAL_LIGHT_RED_1 = new SignalLightRed1(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SIGNAL_LIGHT_RED_2 = new SignalLightRed2(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SIGNAL_LIGHT_BLUE = new SignalLightBlue(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SIGNAL_LIGHT_GREEN = new SignalLightGreen(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SIGNAL_LIGHT_INVERTED_1 = new SignalLightInverted1(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SIGNAL_LIGHT_INVERTED_2 = new SignalLightInverted2(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(1.0f));
	Block SOUND_LOOPER = new SoundLooper(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.0f));
	Block CEILING_1 = new Ceiling1(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f).noOcclusion());
	Block ENQUIRY_MACHINE_1 = new EnquiryMachine1(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f));
	Block EMG_STOP_1 = new EmgStop1(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f));
	Block EXIT_SIGN_1O = new ExitSign1o(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).lightLevel(state -> 10));
	Block EXIT_SIGN_1E = new ExitSign1e(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).lightLevel(state -> 10));
	Block HELPLINE_1 = new HelpLine1(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f));
	Block HELPLINE_2 = new HelpLine2(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f));
	Block HELPLINE_3 = new HelpLine3(BlockBehaviour.Properties.of(Material.METAL).strength(3.0f).noOcclusion());
	Block HELPLINE_4 = new HelpLine4(BlockBehaviour.Properties.of(Material.METAL).strength(3.0f).noOcclusion());
	Block KCR_NAME_SIGN = new KCRNameSign(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f).lightLevel(state -> 15).noOcclusion());
	Block LIGHT_1 = new Light1(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).lightLevel(state -> 15));
	Block LIGHT_2 = new Light2(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).lightLevel(state -> 15));
	Block LIGHT_BLOCK = new LightBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f).lightLevel(state -> 15).noOcclusion());
	Block OP_BUTTONS = new OpOnlyButton(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f).lightLevel(state -> 15).noOcclusion());
	Block PIDS_1A = new PIDS1A();
	Block PIDS_4 = new PIDS4();
	Block PIDS_RV = new PIDSRV();
	Block MODEL_E44 = new ModelE44(BlockBehaviour.Properties.of(Material.METAL).strength(0.5f).noOcclusion());
	Block RV_PIDS_POLE = new RVPIDSPole(BlockBehaviour.Properties.of(Material.METAL).strength(0.5f).noOcclusion());
	Block STATION_NAME_TALL_STAND = new StationNameTallStand();
	Block SUBSIDY_MACHINE_1 = new SubsidyMachine1(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f));
	Block TICKET_BARRIER_1_ENTRANCE = new ThalesTicketBarrier(true);
	Block TICKET_BARRIER_1_EXIT = new ThalesTicketBarrier(false);
	Block TICKET_BARRIER_1_DECOR = new ThalesTicketBarrierDecor();
	Block TRESPASS_SIGN_1 = new TrespassSign1(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f).noOcclusion());
	Block TRESPASS_SIGN_2 = new TrespassSign2(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).noOcclusion());
	Block WATER_MACHINE_1 = new WaterMachine1(BlockBehaviour.Properties.of(Material.METAL).strength(4.0f));
}