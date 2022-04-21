package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PIDSRVTCL extends PIDSRVBase {

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntityBlockPIDS5(pos, state);
	}

	public static class TileEntityBlockPIDS5 extends TileEntityBlockPIDSBase {

		public static final int MAX_ARRIVALS = 4;

		public TileEntityBlockPIDS5(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.PIDS_5_TILE_ENTITY, pos, state);
		}

		@Override
		protected int getMaxArrivals() {
			return MAX_ARRIVALS;
		}
	}
}