package com.jsblock;

import com.jsblock.mappings.ForgeUtilities;
import mtr.RegistryObject;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.DeferredRegisterHolder;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Joban.MOD_ID)
public class JobanForge {

	private static final DeferredRegisterHolder<Item> ITEMS = new DeferredRegisterHolder<>(Joban.MOD_ID, Registry.ITEM_REGISTRY);
	private static final DeferredRegisterHolder<Block> BLOCKS = new DeferredRegisterHolder<>(Joban.MOD_ID, Registry.BLOCK_REGISTRY);
	private static final DeferredRegisterHolder<BlockEntityType<?>> BLOCK_ENTITY_TYPES = new DeferredRegisterHolder<>(Joban.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	static{
		Joban.init(JobanForge::registerBlock, JobanForge::registerBlockEntityType);
	}

	public JobanForge() {
		final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ForgeUtilities.registerModEventBus(Joban.MOD_ID, eventBus);
		ITEMS.register();
		BLOCKS.register();
		BLOCK_ENTITY_TYPES.register();
		eventBus.register(MTRForgeRegistry.class);
	}

	private static void registerBlock(String path, RegistryObject<Block> block) {
		BLOCKS.register(path, block::register);
	}

	private static void registerBlock(String path, RegistryObject<Block> block, CreativeModeTab itemGroup) {
		registerBlock(path, block);
		ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup)));
	}

	private static <T extends BlockEntityMapper> void registerBlockEntityType(String path, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>> blockEntityType) {
		BLOCK_ENTITY_TYPES.register(path, blockEntityType::register);
	}

	private static class MTRForgeRegistry {

		@SubscribeEvent
		public static void onClientSetupEvent(FMLClientSetupEvent event) {
			JobanClient.init();
		}
	}
}
