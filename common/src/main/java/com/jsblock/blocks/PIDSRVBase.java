package com.jsblock.blocks;

import mtr.block.BlockPIDSBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PIDSRVBase extends BlockPIDSBase {

	public static abstract class TileEntityBlockPIDSBase extends BlockPIDSBase.TileEntityBlockPIDSBase {
		private boolean hidePlatformNumber;
		private static final String KEY_HIDE_PLATFORM_NUMBER = "hide_platform_number";

		public TileEntityBlockPIDSBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, pos, state);
		}

		public void readCompoundTag(CompoundTag compoundTag) {
			this.hidePlatformNumber = compoundTag.getBoolean(KEY_HIDE_PLATFORM_NUMBER);
		}

		public void writeCompoundTag(CompoundTag compoundTag) {
			compoundTag.putBoolean(KEY_HIDE_PLATFORM_NUMBER, this.hidePlatformNumber);
		}

		public void setData(String[] messages, boolean[] hideArrival, boolean hidePlatformNumber) {
			super.setData(messages, hideArrival);
			this.hidePlatformNumber = hidePlatformNumber;
			this.setChanged();
			this.syncData();
		}

		@Override
		protected abstract int getMaxArrivals();

		public boolean getHidePlatformNumber() {
			return hidePlatformNumber;
		}

		public void setHidePlatformNumber(boolean hidePlatformNumber) {
			this.hidePlatformNumber = hidePlatformNumber;
		}
	}
}