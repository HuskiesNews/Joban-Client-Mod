package com.jsblock;

import com.jsblock.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Blocks {
    public static final Block BUTTERFLY_LIGHT = new ButterflyLight(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(3.0f).luminance(4));
    public static final Block CIRCLE_WALL_1 = new CircleWall();
    public static final Block CIRCLE_WALL_2 = new CircleWall();
    public static final Block CIRCLE_WALL_3 = new CircleWall();
    public static final Block CIRCLE_WALL_4 = new CircleWall();
    public static final Block CIRCLE_WALL_5 = new CircleWall();
    public static final Block CIRCLE_WALL_6 = new CircleWall();
    public static final Block CIRCLE_WALL_7 = new CircleWall();
    public static final Block DEPARTURE_POLE = new DeparturePole(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(3.0f).nonOpaque());
    public static final Block DEPARTURE_TIMER = new DepartureTimer(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(3.0f));
    public static final Block SIGNAL_LIGHT_RED_1 = new SignalLightRed1(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_RED_2 = new SignalLightRed2(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_BLUE = new SignalLightBlue(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_GREEN = new SignalLightGreen(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_INVERTED_1 = new SignalLightInverted1(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_INVERTED_2 = new SignalLightInverted2(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block CEILING_1 = new Ceiling1(FabricBlockSettings.of(Material.METAL).hardness(2.0f).nonOpaque());
    public static final Block ENQUIRY_MACHINE_1 = new EnquiryMachine1(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block EMG_STOP_1 = new EmgStop1(FabricBlockSettings.of(Material.METAL).hardness(1.0f));
    public static final Block EXIT_SIGN_1 = new ExitSign1(FabricBlockSettings.of(Material.METAL).hardness(0.5f).luminance(10));
    public static final Block AUTO_IRON_DOOR = new AutoDoor(AbstractBlock.Settings.copy(net.minecraft.block.Blocks.IRON_DOOR));
    public static final Block HELPLINE_1 = new HelpLine1(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static final Block HELPLINE_2 = new HelpLine2(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static final Block HELPLINE_3 = new HelpLine3(FabricBlockSettings.of(Material.METAL).hardness(3.0f).nonOpaque());
    public static final Block LIGHT_1 = new Light1(FabricBlockSettings.of(Material.METAL).hardness(1.0f).luminance(15));
    public static final Block LIGHT_2 = new Light2(FabricBlockSettings.of(Material.METAL).hardness(1.0f).luminance(15));
    public static final Block LIGHT_BLOCK = new LightBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f).luminance(15).nonOpaque());
    public static final Block PIDS_1A = new PIDS1A();
    public static final Block PIDS_RV = new PIDSRV();
    public static final Block MODEL_E44 = new ModelE44(FabricBlockSettings.of(Material.METAL).hardness(1.0f).nonOpaque());
    public static final Block STATION_NAME_TALL_STAND = new StationNameTallStand();
    public static final Block SUBSIDY_MACHINE_1 = new SubsidyMachine1(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block TRESPASS_SIGN_1 = new TrespassSign1(FabricBlockSettings.of(Material.METAL).hardness(2.0f).nonOpaque());
    public static final Block TRESPASS_SIGN_2 = new TrespassSign2(FabricBlockSettings.of(Material.METAL).hardness(1.0f).nonOpaque());
    public static final Block WATER_MACHINE_1 = new WaterMachine1(FabricBlockSettings.of(Material.METAL).hardness(4.0f));

    public static void registerBlocks() {
        registerBlock("butterfly_light", BUTTERFLY_LIGHT);
        registerBlock("ceiling_1", CEILING_1);
        registerBlock("circle_wall_1", CIRCLE_WALL_1);
        registerBlock("circle_wall_2", CIRCLE_WALL_2);
        registerBlock("circle_wall_3", CIRCLE_WALL_3);
        registerBlock("circle_wall_4", CIRCLE_WALL_4);
        registerBlock("circle_wall_5", CIRCLE_WALL_5);
        registerBlock("circle_wall_6", CIRCLE_WALL_6);
        registerBlock("circle_wall_7", CIRCLE_WALL_7);
        registerBlock("departure_pole", DEPARTURE_POLE);
        registerBlock("departure_timer", DEPARTURE_TIMER);
        registerBlock("emg_stop_1", EMG_STOP_1);
        registerBlock("enquiry_machine_1", ENQUIRY_MACHINE_1);
        registerBlock("exit_sign_1", EXIT_SIGN_1);
        registerBlock("auto_iron_door", AUTO_IRON_DOOR);
        registerBlock("helpline_1", HELPLINE_1);
        registerBlock("helpline_2", HELPLINE_2);
        registerBlock("helpline_3", HELPLINE_3);
        registerBlock("light_1", LIGHT_1);
        registerBlock("light_2", LIGHT_2);
        registerBlock("light_block", LIGHT_BLOCK);
        registerBlock("pids_1a", PIDS_1A);
        registerBlock("pids_rv", PIDS_RV);
        registerBlock("station_name_tall_stand", STATION_NAME_TALL_STAND);
        registerBlock("signal_light_red_1", SIGNAL_LIGHT_RED_1);
        registerBlock("signal_light_red_2", SIGNAL_LIGHT_RED_2);
        registerBlock("signal_light_green", SIGNAL_LIGHT_GREEN);
        registerBlock("signal_light_blue", SIGNAL_LIGHT_BLUE);
        registerBlock("signal_light_inverted_1", SIGNAL_LIGHT_INVERTED_1);
        registerBlock("signal_light_inverted_2", SIGNAL_LIGHT_INVERTED_2);
        registerBlock("subsidy_machine_1", SUBSIDY_MACHINE_1);
        registerBlock("train_model_e44", MODEL_E44);
        registerBlock("trespass_sign_1", TRESPASS_SIGN_1);
        registerBlock("trespass_sign_2", TRESPASS_SIGN_2);
        registerBlock("water_machine_1", WATER_MACHINE_1);
    }

    private static void registerBlock(String path, Block block) {
        registerBlock(path, block, Joestu.MOD_ID);
    }

    private static void registerBlock(String path, Block block, String modID) {
        Registry.register(Registry.BLOCK, new Identifier(modID, path), block);
        Registry.register(Registry.ITEM, new Identifier(modID, path), new BlockItem(block, new FabricItemSettings().group(ItemGroups.MAIN)));
    }
}