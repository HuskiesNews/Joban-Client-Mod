package com.jsblock.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExitSign1e extends HorizontalMultiBlockBase {
	public ExitSign1e(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		if(state.getValue(LEFT)) {
			return mtr.block.IBlock.getVoxelShapeByDirection(0, 9, 8, 8, 16, 8.1, state.getValue(FACING));
		} else {
			return mtr.block.IBlock.getVoxelShapeByDirection(8, 9, 8, 16, 16, 8.1, state.getValue(FACING));
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockAbove = ctx.getLevel().getBlockState(ctx.getClickedPos().above());
		if(blockAbove.getBlock().equals(Blocks.AIR)) {
			return null;
		}

		return super.getStateForPlacement(ctx);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LEFT);
	}
}
