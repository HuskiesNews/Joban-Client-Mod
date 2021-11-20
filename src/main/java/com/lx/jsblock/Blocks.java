package com.lx.jsblock;

import com.lx.jsblock.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Blocks {
    public static final Block CIRCLE_WALL_1 = new CircleWall();
    public static final Block CIRCLE_WALL_2 = new CircleWall();
    public static final Block CIRCLE_WALL_3 = new CircleWall();
    public static final Block CIRCLE_WALL_4 = new CircleWall();
    public static final Block CIRCLE_WALL_5 = new CircleWall();
    public static final Block CIRCLE_WALL_6 = new CircleWall();
    public static final Block CIRCLE_WALL_7 = new CircleWall();
    public static final Block SIGNAL_LIGHT_RED_1 = new SignalLightRed1(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_RED_2 = new SignalLightRed2(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_BLUE = new SignalLightBlue(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_GREEN = new SignalLightGreen(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_INVERTED_1 = new SignalLightInverted1(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGNAL_LIGHT_INVERTED_2 = new SignalLightInverted2(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).hardness(1.0f));
    public static final Block SIGN_POLE_EVEN = new SignPoleEven(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block SIGN_POLE_ODD = new SignPoleOdd(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block PIDS_POLE = new PIDSPole(FabricBlockSettings.of(Material.METAL).hardness(1.0f));
    public static final Block CLOCK_POLE = new ClockPole(FabricBlockSettings.of(Material.METAL).hardness(1.0f));
    public static final Block LIGHT_BLOCK = new LightBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f).luminance(15).nonOpaque());
    public static final Block HELPLINE_1 = new HelpLine1(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static final Block HELPLINE_2 = new HelpLine2(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static final Block HELPLINE_3 = new HelpLine3(FabricBlockSettings.of(Material.METAL).hardness(3.0f).nonOpaque());
    public static final Block CEILING_1 = new Ceiling1(FabricBlockSettings.of(Material.METAL).hardness(2.0f).nonOpaque());
    public static final Block WATER_MACHINE_1 = new WaterMachine1(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block SUBSIDY_MACHINE = new SubsidyMachine(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block ENQUIRY_MACHINE_1 = new EnquiryMachine1(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Block MODEL_E44 = new ModelE44(FabricBlockSettings.of(Material.METAL).hardness(1.0f).nonOpaque());
    public static final Block LIGHT_1 = new Light1(FabricBlockSettings.of(Material.METAL).hardness(1.0f).luminance(15));
    public static final Block EMG_STOP_1 = new EmgStop1(FabricBlockSettings.of(Material.METAL).hardness(1.0f));
    public static final Block STATION_NAME_TALL_STAND = new StationNameTallStand();

    public static final BlockEntityType<StationNameTallStand.TileEntityStationNameTallStand> STATION_NAME_TALL_STAND_TILE_ENTITY = registerTileEntity("station_name_tall_stand", StationNameTallStand.TileEntityStationNameTallStand::new, Blocks.STATION_NAME_TALL_STAND);
    public static final BlockEntityType<SignalLightRed1.TileEntitySignalLightRed> SIGNAL_LIGHT_RED_ENTITY_1 = registerTileEntity("signal_light_red_1", SignalLightRed1.TileEntitySignalLightRed::new, Blocks.SIGNAL_LIGHT_RED_1);
    public static final BlockEntityType<SignalLightRed2.TileEntitySignalLightRed2> SIGNAL_LIGHT_RED_ENTITY_2 = registerTileEntity("signal_light_red_2", SignalLightRed2.TileEntitySignalLightRed2::new, Blocks.SIGNAL_LIGHT_RED_2);
    public static final BlockEntityType<SignalLightBlue.TileEntitySignalLightBlue> SIGNAL_LIGHT_BLUE_ENTITY = registerTileEntity("signal_light_blue", SignalLightBlue.TileEntitySignalLightBlue::new, Blocks.SIGNAL_LIGHT_BLUE);
    public static final BlockEntityType<SignalLightGreen.TileEntitySignalLightGreen> SIGNAL_LIGHT_GREEN_ENTITY = registerTileEntity("signal_light_green", SignalLightGreen.TileEntitySignalLightGreen::new, Blocks.SIGNAL_LIGHT_GREEN);
    public static final BlockEntityType<SignalLightInverted1.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_1 = registerTileEntity("signal_light_inverted_1", SignalLightInverted1.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_1);
    public static final BlockEntityType<SignalLightInverted2.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_2 = registerTileEntity("signal_light_inverted_2", SignalLightInverted2.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_2);

    public static void registerBlocks() {
        registerBlock("light_block", LIGHT_BLOCK);
        registerBlock("helpline_1", HELPLINE_1);
        registerBlock("helpline_2", HELPLINE_2);
        registerBlock("helpline_3", HELPLINE_3);
        registerBlock("sign_pole_even", SIGN_POLE_EVEN);
        registerBlock("sign_pole_odd", SIGN_POLE_ODD);
        registerBlock("train_model_e44", MODEL_E44);
        registerBlock("water_machine_1", WATER_MACHINE_1);
        registerBlock("subsidy_machine", SUBSIDY_MACHINE);
        registerBlock("enquiry_machine_1", ENQUIRY_MACHINE_1);
        registerBlock("light_1", LIGHT_1);
        registerBlock("emg_stop_1", EMG_STOP_1);
        registerBlock("station_name_tall_stand", STATION_NAME_TALL_STAND);
        registerBlock("pids_pole", PIDS_POLE);
        registerBlock("clock_pole", CLOCK_POLE);
        registerBlock("ceiling_1", CEILING_1);
        registerBlock("signal_light_red_1", SIGNAL_LIGHT_RED_1);
        registerBlock("signal_light_red_2", SIGNAL_LIGHT_RED_2);
        registerBlock("signal_light_blue", SIGNAL_LIGHT_BLUE);
        registerBlock("signal_light_green", SIGNAL_LIGHT_GREEN);
        registerBlock("signal_light_inverted_1", SIGNAL_LIGHT_INVERTED_1);
        registerBlock("signal_light_inverted_2", SIGNAL_LIGHT_INVERTED_2);
        registerBlock("circle_wall_1", CIRCLE_WALL_1);
        registerBlock("circle_wall_2", CIRCLE_WALL_2);
        registerBlock("circle_wall_3", CIRCLE_WALL_3);
        registerBlock("circle_wall_4", CIRCLE_WALL_4);
        registerBlock("circle_wall_5", CIRCLE_WALL_5);
        registerBlock("circle_wall_6", CIRCLE_WALL_6);
        registerBlock("circle_wall_7", CIRCLE_WALL_7);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerTileEntity(String path, Supplier<T> supplier, Block block) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, Joestu.MOD_ID + ":" + path, BlockEntityType.Builder.create(supplier, block).build(null));
    }

    private static void registerBlock(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Joestu.MOD_ID, path), block);
        Registry.register(Registry.ITEM, new Identifier(Joestu.MOD_ID, path), new BlockItem(block, new FabricItemSettings().group(ItemGroups.MAIN)));
    }
}
