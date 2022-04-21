package com.jsblock.blocks;

import mtr.block.BlockPIDSBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PIDSRVBase extends BlockPIDSBase {

	public static abstract class TileEntityBlockPIDSBase extends BlockPIDSBase.TileEntityBlockPIDSBase {

		public TileEntityBlockPIDSBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, pos, state);
		}

		@Override
		protected abstract int getMaxArrivals();
	}
}