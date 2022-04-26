package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SignalLightBlue extends mtr.block.BlockSignalLightBase {

	public SignalLightBlue(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySignalLightBlue(pos, state);
	}

	public static class TileEntitySignalLightBlue extends BlockEntityMapper {

		public TileEntitySignalLightBlue(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SIGNAL_LIGHT_BLUE_ENTITY.get(), pos, state);
		}
	}
}