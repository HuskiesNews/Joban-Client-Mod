package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SignalLightInverted2 extends mtr.block.BlockSignalLightBase {

	public SignalLightInverted2(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySignalLightInverted(pos, state);
	}

	public static class TileEntitySignalLightInverted extends BlockEntityMapper {

		public TileEntitySignalLightInverted(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SIGNAL_LIGHT_INVERTED_ENTITY_2.get(), pos, state);
		}
	}
}