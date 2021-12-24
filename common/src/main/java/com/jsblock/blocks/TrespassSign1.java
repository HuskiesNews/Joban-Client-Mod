package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrespassSign1 extends HorizontalMultiBlockBase {

	public TrespassSign1(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		if (IBlock.getStatePropertySafe(state, LEFT)) {
			VoxelShape vx1 = IBlock.getVoxelShapeByDirection(0, 4, 7.95, 14, 16, 9.05, facing);
			VoxelShape vx2 = IBlock.getVoxelShapeByDirection(8, 0, 8, 9, 7, 9, facing);
			return Shapes.or(vx1, vx2);
		} else {
			VoxelShape vx1 = IBlock.getVoxelShapeByDirection(2, 4, 7.95, 16, 16, 9.05, facing);
			VoxelShape vx2 = IBlock.getVoxelShapeByDirection(7, 0, 8, 8, 7, 9, facing);
			return Shapes.or(vx1, vx2);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LEFT);
	}
}
