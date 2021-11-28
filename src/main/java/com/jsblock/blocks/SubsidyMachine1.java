package com.jsblock.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.state.StateManager;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SubsidyMachine1 extends HorizontalFacingBlock {
    public final int SUBSIDY_PRICE = 10;
    public SubsidyMachine1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return mtr.block.IBlock.getVoxelShapeByDirection(3, 0, 0, 14, 18, 3, state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return mtr.block.IBlock.getVoxelShapeByDirection(3, 0, 0, 14, 16, 3, state.get(FACING));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        mtr.data.TicketSystem.addObjectivesIfMissing(world);
        ScoreboardPlayerScore balanceScore = mtr.data.TicketSystem.getPlayerScore(world, player, mtr.data.TicketSystem.BALANCE_OBJECTIVE);
        balanceScore.setScore(balanceScore.getScore() + SUBSIDY_PRICE);
        player.sendMessage(new TranslatableText("gui.jsblock.subsidy", balanceScore.getScore()), true);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}