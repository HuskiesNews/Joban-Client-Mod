package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.MTR;
import mtr.block.IBlock;
import mtr.data.Platform;
import mtr.data.RailwayData;
import mtr.data.ScheduleEntry;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.EntityBlockMapper;
import mtr.mappings.TickableMapper;
import mtr.mappings.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class ButterflyLight extends HorizontalDirectionalBlock implements EntityBlockMapper {

	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	public ButterflyLight(Properties settings) {
		super(settings);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(LIT, false);
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
		world.setBlockAndUpdate(pos, state.setValue(LIT, false));
	}

	@Override
	public BlockEntityType<? extends BlockEntityMapper> getType() {
		return BlockEntityTypes.BUTTERFLY_LIGHT_TILE_ENTITY.get();
	}

	@Override
	public <T extends BlockEntityMapper> void tick(Level world, BlockPos pos, T blockEntity) {
		TileEntityButterFlyLight.tick(world, pos, blockEntity);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		return IBlock.getVoxelShapeByDirection(2, 0, 0, 14, 5.8, 10, facing);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntityButterFlyLight(pos, state);
	}

	public static class TileEntityButterFlyLight extends BlockEntityMapper implements TickableMapper {

		public TileEntityButterFlyLight(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.BUTTERFLY_LIGHT_TILE_ENTITY.get(), pos, state);
		}

		@Override
		public void tick() {
			tick(level, worldPosition, this);
		}

		public static <T extends BlockEntityMapper> void tick(Level world, BlockPos pos, T blockEntity) {
			if (world != null && !world.isClientSide) {
				final BlockState state = world.getBlockState(pos);
				final RailwayData railwayData = RailwayData.getInstance(world);
				if (railwayData == null) {
					return;
				}

				/* Get the closest platform */
				final long platformId = RailwayData.getClosePlatformId(railwayData.platforms, railwayData.dataCache, pos, 5, 3, 3);
				if (platformId == 0) {
					return;
				}

				/* Get the arrivals on that platform */
				final List<ScheduleEntry> schedules = railwayData.getSchedulesAtPlatform(platformId);
				if (schedules == null) {
					return;
				}

				final List<ScheduleEntry> scheduleList = new ArrayList<>(schedules);
				if (!scheduleList.isEmpty()) {
					Collections.sort(scheduleList);

					/* If the train has not yet arrived (Should be negative when train arrived) */
					if (scheduleList.get(0).arrivalMillis - System.currentTimeMillis() > 0) {
						/* Run the schedule tick after 16 ticks, if not already scheduled. */
						/* The schedule tick will reset the butterfly light */
						if (!world.getBlockTicks().hasScheduledTick(pos, state.getBlock())) {
							Utilities.scheduleBlockTick(world, new BlockPos(pos), state.getBlock(), 16);
						}
						return;
					}

					int remainingSecond = (int) (scheduleList.get(0).arrivalMillis - System.currentTimeMillis()) / 1000;
					final Platform platform = railwayData.dataCache.platformIdMap.get(platformId);
					/* platform.getDwellTime() returns the dwell second x2, so we have to divide it by 2 to get the second */
					int seconds = platform == null ? 0 : (platform.getDwellTime() / 2) - Math.abs(remainingSecond);
					/* If the departure time is still more than 10 seconds, return and don't blink the light */
					if (seconds > 10) {
						return;
					}
					/* The following setBlockState code will be run every 16 ticks or 0.8 second */
					if (MTR.isGameTickInterval(16)) {
						/* This cycles through the block state of the "lit" property, which has true and false (as it's a boolean), creating a blinking effect */
						world.setBlockAndUpdate(pos, state.cycle(LIT));
					}
				}
			}
		}
	}
}