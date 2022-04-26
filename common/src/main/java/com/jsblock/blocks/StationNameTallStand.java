package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.block.IBlock;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StationNameTallStand extends mtr.block.BlockStationNameTallBase {

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Tuple<Integer, Integer> bounds = getBounds(state);
		return IBlock.getVoxelShapeByDirection(1, bounds.getA(), 0, 15, bounds.getB(), 0.5, IBlock.getStatePropertySafe(state, FACING));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		final Direction blockSide = ctx.getClickedFace();
		final Direction facing = blockSide == Direction.UP || blockSide == Direction.DOWN ? ctx.getHorizontalDirection() : blockSide.getOpposite();
		return IBlock.isReplaceable(ctx, Direction.UP, 3) ? defaultBlockState().setValue(FACING, facing).setValue(METAL, true).setValue(THIRD, EnumThird.LOWER) : null;
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntityStationNameTallStand(pos, state);
	}

	public static class TileEntityStationNameTallStand extends mtr.block.BlockStationNameTallWall.TileEntityStationNameTallBase {
		public TileEntityStationNameTallStand(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.STATION_NAME_TALL_STAND_TILE_ENTITY.get(), pos, state, 0.04025F);
		}
	}

	public static Tuple<Integer, Integer> getBounds(BlockState state) {
		final EnumThird third = IBlock.getStatePropertySafe(state, THIRD);
		final int start, end;
		if (third == EnumThird.UPPER) {
			start = 0;
			end = 6;
		} else {
			start = 0;
			end = 16;
		}
		return new Tuple<>(start, end);
	}
}
