package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SignalLightRed1 extends mtr.block.BlockSignalLightBase {

	public SignalLightRed1(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySignalLightRed(pos, state);
	}

	public static class TileEntitySignalLightRed extends BlockEntityMapper {

		public TileEntitySignalLightRed(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_1.get(), pos, state);
		}
	}
}
