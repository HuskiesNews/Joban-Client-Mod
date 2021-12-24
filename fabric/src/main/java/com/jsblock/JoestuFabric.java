package com.jsblock;

import mtr.mappings.BlockEntityMapper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class JoestuFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		Joestu.init(JoestuFabric::registerBlock, JoestuFabric::registerBlockEntityType);
	}

	private static void registerBlock(String path, Block block, CreativeModeTab itemGroup) {
		Registry.register(Registry.BLOCK, new ResourceLocation(Joestu.MOD_ID, path), block);
		Registry.register(Registry.ITEM, new ResourceLocation(Joestu.MOD_ID, path), new BlockItem(block, new Item.Properties().tab(itemGroup)));
	}

	private static <T extends BlockEntityMapper> void registerBlockEntityType(String path, BlockEntityType<T> blockEntityType) {
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Joestu.MOD_ID, path), blockEntityType);
	}
}
