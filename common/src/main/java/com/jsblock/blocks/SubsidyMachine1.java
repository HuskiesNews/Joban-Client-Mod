package com.jsblock.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.scores.Score;

public class SubsidyMachine1 extends HorizontalDirectionalBlock {
	public final int SUBSIDY_PRICE = 10;

	public SubsidyMachine1(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		return mtr.block.IBlock.getVoxelShapeByDirection(3, 0, 0, 14, 18, 3, state.getValue(FACING));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext collisionContext) {
		return mtr.block.IBlock.getVoxelShapeByDirection(3, 0, 0, 14, 16, 3, state.getValue(FACING));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (!world.isClientSide()) {
			mtr.data.TicketSystem.addObjectivesIfMissing(world);
			Score balanceScore = mtr.data.TicketSystem.getPlayerScore(world, player, mtr.data.TicketSystem.BALANCE_OBJECTIVE);
			balanceScore.setScore(balanceScore.getScore() + SUBSIDY_PRICE);
			player.displayClientMessage(new TranslatableComponent("gui.jsblock.subsidy", balanceScore.getScore()), true);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}