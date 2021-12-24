package com.jsblock.blocks;

import mtr.SoundEvents;
import mtr.block.IBlock;
import mtr.data.TicketSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnquiryMachine1 extends mtr.block.BlockDirectionalDoubleBlockBase {
	public EnquiryMachine1(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		if (IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER) {
			VoxelShape vx1 = IBlock.getVoxelShapeByDirection(4, 0, 7, 12, 5.62, 14, facing);
			VoxelShape vx2 = IBlock.getVoxelShapeByDirection(4, 5.62, 8.275, 12, 10.12, 8.425, facing);
			return Shapes.or(vx1, vx2);
		} else {
			return IBlock.getVoxelShapeByDirection(4, 0, 7, 12, 16, 14, facing);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (!world.isClientSide) {
			final int playerScore = TicketSystem.getPlayerScore(world, player, TicketSystem.BALANCE_OBJECTIVE).getScore();
			player.displayClientMessage(new TranslatableComponent("gui.mtr.balance", String.valueOf(playerScore)), true);
			world.playSound(null, pos, SoundEvents.TICKET_PROCESSOR_ENTRY, SoundSource.BLOCKS, 1, 1);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}
}
