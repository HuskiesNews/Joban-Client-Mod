package com.jsblock.blocks;

import com.jsblock.packets.Server;
import mtr.block.BlockPIDSBase;
import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public abstract class PIDSRVBase extends BlockPIDSBase {

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			BlockPos otherPos = pos.relative(IBlock.getStatePropertySafe(state, FACING));
			BlockEntity entity1 = world.getBlockEntity(pos);
			BlockEntity entity2 = world.getBlockEntity(otherPos);
			if (entity1 instanceof PIDSRVBase.TileEntityBlockPIDSBase && entity2 instanceof PIDSRVBase.TileEntityBlockPIDSBase) {
				((PIDSRVBase.TileEntityBlockPIDSBase)entity1).syncData();
				((PIDSRVBase.TileEntityBlockPIDSBase)entity2).syncData();
				Server.openRVPIDSConfigScreenS2C((ServerPlayer)player, pos, otherPos, ((PIDSRVBase.TileEntityBlockPIDSBase)entity1).getMaxArrivals());
			}

		});
	}

	public static abstract class TileEntityBlockPIDSBase extends BlockPIDSBase.TileEntityBlockPIDSBase {
		private boolean hidePlatformNumber;
		private static final String KEY_HIDE_PLATFORM_NUMBER = "hide_platform_number";

		public TileEntityBlockPIDSBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, pos, state);
		}

		@Override
		public void readCompoundTag(CompoundTag compoundTag) {
			this.hidePlatformNumber = compoundTag.getBoolean(KEY_HIDE_PLATFORM_NUMBER);
			super.readCompoundTag(compoundTag);
		}

		@Override
		public void writeCompoundTag(CompoundTag compoundTag) {
			compoundTag.putBoolean(KEY_HIDE_PLATFORM_NUMBER, this.hidePlatformNumber);
			super.writeCompoundTag(compoundTag);
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
	}
}