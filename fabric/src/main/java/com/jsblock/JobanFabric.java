package com.jsblock;

import mtr.RegistryObject;
import mtr.mappings.BlockEntityMapper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class JobanFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		Joban.init(JobanFabric::registerBlock, JobanFabric::registerBlockEntityType);
	}

	private static void registerBlock(String path, RegistryObject<Block> block, CreativeModeTab itemGroup) {
		Registry.register(Registry.BLOCK, new ResourceLocation(Joban.MOD_ID, path), block.get());
		Registry.register(Registry.ITEM, new ResourceLocation(Joban.MOD_ID, path), new BlockItem(block.get(), new Item.Properties().tab(itemGroup)));
	}

	private static <T extends BlockEntityMapper> void registerBlockEntityType(String path, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>> blockEntityType) {
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Joban.MOD_ID, path), blockEntityType.get());
	}
}
