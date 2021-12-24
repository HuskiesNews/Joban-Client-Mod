package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SignalLightRed2 extends mtr.block.BlockSignalLightBase {

	public SignalLightRed2(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySignalLightRed2(pos, state);
	}

	public static class TileEntitySignalLightRed2 extends BlockEntityMapper {

		public TileEntitySignalLightRed2(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SIGNAL_LIGHT_RED_ENTITY_2, pos, state);
		}
	}
}