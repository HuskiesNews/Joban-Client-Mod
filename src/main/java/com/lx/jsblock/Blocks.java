package com.lx.jsblock;

import com.lx.jsblock.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    private static final String NAMESPACE = "jsblock";
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

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "light_block"), Blocks.LIGHT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "light_block"), new BlockItem(Blocks.LIGHT_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "helpline_1"), Blocks.HELPLINE_1);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "helpline_1"), new BlockItem(Blocks.HELPLINE_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "helpline_2"), Blocks.HELPLINE_2);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "helpline_2"), new BlockItem(Blocks.HELPLINE_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "helpline_3"), Blocks.HELPLINE_3);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "helpline_3"), new BlockItem(Blocks.HELPLINE_3, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "sign_pole_even"), Blocks.SIGN_POLE_EVEN);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "sign_pole_even"), new BlockItem(Blocks.SIGN_POLE_EVEN, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "sign_pole_odd"), Blocks.SIGN_POLE_ODD);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "sign_pole_odd"), new BlockItem(Blocks.SIGN_POLE_ODD, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "train_model_e44"), Blocks.MODEL_E44);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "train_model_e44"), new BlockItem(Blocks.MODEL_E44, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "light_1"), Blocks.LIGHT_1);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "light_1"), new BlockItem(Blocks.LIGHT_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "emg_stop_1"), Blocks.EMG_STOP_1);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "emg_stop_1"), new BlockItem(Blocks.EMG_STOP_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_1"), Blocks.CIRCLE_WALL_1);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_1"), new BlockItem(Blocks.CIRCLE_WALL_1, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_2"), Blocks.CIRCLE_WALL_2);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_2"), new BlockItem(Blocks.CIRCLE_WALL_2, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_3"), Blocks.CIRCLE_WALL_3);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_3"), new BlockItem(Blocks.CIRCLE_WALL_3, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_4"), Blocks.CIRCLE_WALL_4);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_4"), new BlockItem(Blocks.CIRCLE_WALL_4, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_5"), Blocks.CIRCLE_WALL_5);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_5"), new BlockItem(Blocks.CIRCLE_WALL_5, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_6"), Blocks.CIRCLE_WALL_6);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_6"), new BlockItem(Blocks.CIRCLE_WALL_6, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "circle_wall_7"), Blocks.CIRCLE_WALL_7);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "circle_wall_7"), new BlockItem(Blocks.CIRCLE_WALL_7, new FabricItemSettings().group(ItemGroup.MISC)));
    }
}
