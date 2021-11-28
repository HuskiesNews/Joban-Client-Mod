package com.jsblock.blocks;

import mtr.MTR;
import mtr.block.IBlock;
import mtr.data.TicketSystem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EnquiryMachine1 extends mtr.block.BlockDirectionalDoubleBlockBase {
    public EnquiryMachine1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        if (IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER) {
            VoxelShape vx1 = IBlock.getVoxelShapeByDirection(4, 0, 7, 12, 5.62, 14, facing);
            VoxelShape vx2 = IBlock.getVoxelShapeByDirection(4, 5.62, 8.275, 12, 10.12, 8.425, facing);
            return VoxelShapes.union(vx1, vx2);
        } else {
            return IBlock.getVoxelShapeByDirection(4, 0, 7, 12, 16, 14, facing);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            final int playerScore = TicketSystem.getPlayerScore(world, player, TicketSystem.BALANCE_OBJECTIVE).getScore();
            player.sendMessage(new TranslatableText("gui.mtr.balance", String.valueOf(playerScore)), true);
            world.playSound(null, pos, MTR.TICKET_PROCESSOR_ENTRY, SoundCategory.BLOCKS, 1, 1);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
}
