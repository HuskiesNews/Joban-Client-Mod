package com.jsblock.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ExitSign1 extends HorizontalFacingBlock {
    public ExitSign1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return mtr.block.IBlock.getVoxelShapeByDirection(0, 9, 8, 16, 16, 8.1, state.get(FACING));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockAbove = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
        return blockAbove.getBlock().equals(Blocks.AIR) ? null : getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
