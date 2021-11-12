package com.lx.jsblock;

import com.lx.jsblock.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Blocks {
    private static final String MOD_ID = "jsblock";
    public static final Block LIGHT_BLOCK = new LightBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).luminance(15).nonOpaque());
    public static final Block HELPLINE_1 = new HelpLine1(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block HELPLINE_2 = new HelpLine2(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block HELPLINE_3 = new HelpLine3(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_1 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_2 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_3 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_4 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_5 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_6 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block CIRCLE_WALL_7 = new CircleWall(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block SIGN_POLE_EVEN = new SignPoleEven(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Block SIGN_POLE_ODD = new SignPoleOdd(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Block MODEL_E44 = new ModelE44(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block LIGHT_1 = new Light_1(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque().luminance(15));
    public static final Block EMG_STOP_1 = new EmgStop_1(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block STATION_NAME_TALL_STAND = new StationNameTallStand();
    public static final Block SIGNAL_LIGHT_RED_1 = new SignalLightRed1(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block SIGNAL_LIGHT_RED_2 = new SignalLightRed2(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block SIGNAL_LIGHT_BLUE_1 = new SignalLightBlue(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block SIGNAL_LIGHT_GREEN_1 = new SignalLightGreen(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block SIGNAL_LIGHT_INVERTED_1 = new SignalLightInverted1(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());
    public static final Block SIGNAL_LIGHT_INVERTED_2 = new SignalLightInverted2(FabricBlockSettings.of(Material.METAL).strength(1.0f).nonOpaque());

    public static final BlockEntityType<StationNameTallStand.TileEntityStationNameTallStand> STATION_NAME_TALL_STAND_TILE_ENTITY = registerTileEntity("station_name_tall_stand", StationNameTallStand.TileEntityStationNameTallStand::new, Blocks.STATION_NAME_TALL_STAND);
    public static final BlockEntityType<SignalLightRed1.TileEntitySignalLightRed> SIGNAL_LIGHT_RED_ENTITY_1 = registerTileEntity("signal_light_red_1", SignalLightRed1.TileEntitySignalLightRed::new, Blocks.SIGNAL_LIGHT_RED_1);
    public static final BlockEntityType<SignalLightRed2.TileEntitySignalLightRed2> SIGNAL_LIGHT_RED_ENTITY_2 = registerTileEntity("signal_light_red_2", SignalLightRed2.TileEntitySignalLightRed2::new, Blocks.SIGNAL_LIGHT_RED_2);
    public static final BlockEntityType<SignalLightBlue.TileEntitySignalLightBlue> SIGNAL_LIGHT_BLUE_ENTITY_1 = registerTileEntity("signal_light_blue_1", SignalLightBlue.TileEntitySignalLightBlue::new, Blocks.SIGNAL_LIGHT_BLUE_1);
    public static final BlockEntityType<SignalLightInverted1.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_1 = registerTileEntity("signal_light_inverted_1", SignalLightInverted1.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_1);
    public static final BlockEntityType<SignalLightInverted2.TileEntitySignalLightInverted> SIGNAL_LIGHT_INVERTED_ENTITY_2 = registerTileEntity("signal_light_inverted_2", SignalLightInverted2.TileEntitySignalLightInverted::new, Blocks.SIGNAL_LIGHT_INVERTED_2);
    public static final BlockEntityType<SignalLightGreen.TileEntitySignalLightGreen> SIGNAL_LIGHT_GREEN_ENTITY_1 = registerTileEntity("signal_light_green_1", SignalLightGreen.TileEntitySignalLightGreen::new, Blocks.SIGNAL_LIGHT_GREEN_1);

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "light_block"), Blocks.LIGHT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_block"), new BlockItem(Blocks.LIGHT_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "helpline_1"), Blocks.HELPLINE_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "helpline_1"), new BlockItem(Blocks.HELPLINE_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "helpline_2"), Blocks.HELPLINE_2);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "helpline_2"), new BlockItem(Blocks.HELPLINE_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "helpline_3"), Blocks.HELPLINE_3);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "helpline_3"), new BlockItem(Blocks.HELPLINE_3, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "sign_pole_even"), Blocks.SIGN_POLE_EVEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sign_pole_even"), new BlockItem(Blocks.SIGN_POLE_EVEN, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "sign_pole_odd"), Blocks.SIGN_POLE_ODD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sign_pole_odd"), new BlockItem(Blocks.SIGN_POLE_ODD, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "train_model_e44"), Blocks.MODEL_E44);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "train_model_e44"), new BlockItem(Blocks.MODEL_E44, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "light_1"), Blocks.LIGHT_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_1"), new BlockItem(Blocks.LIGHT_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "emg_stop_1"), Blocks.EMG_STOP_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "emg_stop_1"), new BlockItem(Blocks.EMG_STOP_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_red_1"), Blocks.SIGNAL_LIGHT_RED_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_red_1"), new BlockItem(Blocks.SIGNAL_LIGHT_RED_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_red_2"), Blocks.SIGNAL_LIGHT_RED_2);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_red_2"), new BlockItem(Blocks.SIGNAL_LIGHT_RED_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_blue_1"), Blocks.SIGNAL_LIGHT_BLUE_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_blue_1"), new BlockItem(Blocks.SIGNAL_LIGHT_BLUE_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_green_1"), Blocks.SIGNAL_LIGHT_GREEN_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_green_1"), new BlockItem(Blocks.SIGNAL_LIGHT_GREEN_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_inverted_1"), Blocks.SIGNAL_LIGHT_INVERTED_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_inverted_1"), new BlockItem(Blocks.SIGNAL_LIGHT_INVERTED_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "signal_light_inverted_2"), Blocks.SIGNAL_LIGHT_INVERTED_2);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "signal_light_inverted_2"), new BlockItem(Blocks.SIGNAL_LIGHT_INVERTED_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_1"), Blocks.CIRCLE_WALL_1);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_1"), new BlockItem(Blocks.CIRCLE_WALL_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_2"), Blocks.CIRCLE_WALL_2);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_2"), new BlockItem(Blocks.CIRCLE_WALL_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_3"), Blocks.CIRCLE_WALL_3);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_3"), new BlockItem(Blocks.CIRCLE_WALL_3, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_4"), Blocks.CIRCLE_WALL_4);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_4"), new BlockItem(Blocks.CIRCLE_WALL_4, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_5"), Blocks.CIRCLE_WALL_5);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_5"), new BlockItem(Blocks.CIRCLE_WALL_5, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_6"), Blocks.CIRCLE_WALL_6);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_6"), new BlockItem(Blocks.CIRCLE_WALL_6, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "circle_wall_7"), Blocks.CIRCLE_WALL_7);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "circle_wall_7"), new BlockItem(Blocks.CIRCLE_WALL_7, new FabricItemSettings().group(ItemGroup.MISC)));
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerTileEntity(String path, Supplier<T> supplier, Block block) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":" + path, BlockEntityType.Builder.create(supplier, block).build(null));
    }

    private static void registerBlock(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, path), block);
    }
}
