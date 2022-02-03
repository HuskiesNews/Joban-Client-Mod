package com.jsblock.blocks;

import mtr.block.IBlock;
import mtr.data.TicketSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class TicketBarrier1 extends mtr.block.BlockTicketBarrier {

	public static final EnumProperty<TicketSystem.EnumTicketBarrierOpen> OPEN = EnumProperty.create("open", TicketSystem.EnumTicketBarrierOpen.class);

	public TicketBarrier1(boolean isEntrance) {
		super(isEntrance);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(OPEN, TicketSystem.EnumTicketBarrierOpen.CLOSED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		return IBlock.getVoxelShapeByDirection(12, 0, 0, 16, 15, 16, facing);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		final TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, OPEN);
		final VoxelShape base = IBlock.getVoxelShapeByDirection(15, 0, 0, 16, 24, 16, facing);
		return open.isOpen() ? base : Shapes.or(IBlock.getVoxelShapeByDirection(0, 0, 7, 16, 24, 9, facing), base);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, OPEN);
	}
}
