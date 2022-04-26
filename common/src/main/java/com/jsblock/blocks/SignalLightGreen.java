package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SignalLightGreen extends mtr.block.BlockSignalLightBase {

	public SignalLightGreen(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySignalLightGreen(pos, state);
	}

	public static class TileEntitySignalLightGreen extends BlockEntityMapper {

		public TileEntitySignalLightGreen(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SIGNAL_LIGHT_GREEN_ENTITY.get(), pos, state);
		}
	}
}