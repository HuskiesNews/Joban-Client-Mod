package com.jsblock;

import com.jsblock.mappings.ForgeUtilities;
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

@Mod(Joestu.MOD_ID)
public class JoestuForge {

	private static final DeferredRegisterHolder<Item> ITEMS = new DeferredRegisterHolder<>(Joestu.MOD_ID, Registry.ITEM_REGISTRY);
	private static final DeferredRegisterHolder<Block> BLOCKS = new DeferredRegisterHolder<>(Joestu.MOD_ID, Registry.BLOCK_REGISTRY);
	private static final DeferredRegisterHolder<BlockEntityType<?>> BLOCK_ENTITY_TYPES = new DeferredRegisterHolder<>(Joestu.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public JoestuForge() {
		final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ForgeUtilities.registerModEventBus(Joestu.MOD_ID, eventBus);
		eventBus.register(MTRForgeRegistry.class);
		Joestu.init(JoestuForge::registerBlock, JoestuForge::registerBlockEntityType);
		ITEMS.register();
		BLOCKS.register();
		BLOCK_ENTITY_TYPES.register();
	}

	private static void registerBlock(String path, Block block) {
		BLOCKS.register(path, () -> block);
	}

	private static void registerBlock(String path, Block block, CreativeModeTab itemGroup) {
		registerBlock(path, block);
		ITEMS.register(path, () -> new BlockItem(block, new Item.Properties().tab(itemGroup)));
	}

	private static <T extends BlockEntityMapper> void registerBlockEntityType(String path, BlockEntityType<T> blockEntityType) {
		BLOCK_ENTITY_TYPES.register(path, () -> blockEntityType);
	}

	private static class MTRForgeRegistry {

		@SubscribeEvent
		public static void onClientSetupEvent(FMLClientSetupEvent event) {
			JoestuClient.init();
		}
	}
}
