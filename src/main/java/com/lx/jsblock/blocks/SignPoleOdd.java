package com.lx.jsblock.blocks;

import mtr.Blocks;
import mtr.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SignPoleOdd extends HorizontalFacingBlock {
    public static final IntProperty LENGTH = IntProperty.of("length", 2, 7);
    public SignPoleOdd(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        int length = IBlock.getStatePropertySafe(state, LENGTH);
        switch (length) {
            case 2:
            case 6:
                return mtr.block.IBlock.getVoxelShapeByDirection(14, 0, 7, 15.25, 16, 9, state.get(FACING));
            case 3:
            case 7:
                return mtr.block.IBlock.getVoxelShapeByDirection(10, 0, 7, 11.25, 16, 9, state.get(FACING));
            case 4:
                return mtr.block.IBlock.getVoxelShapeByDirection(6, 0, 7, 7.25, 16, 9, state.get(FACING));
            case 5:
                return mtr.block.IBlock.getVoxelShapeByDirection(2, 0, 7, 3.25, 16, 9, state.get(FACING));
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockBelow = ctx.getWorld().getBlockState(ctx.getBlockPos().down());

        /* Follow the rotation of the block below if its odd signs, or this pole */

        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_2_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 2);
        }
        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_3_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 3);
        }
        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_4_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 4);
        }
        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_5_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 5);
        }
        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_6_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 6);
        }
        if(blockBelow.isOf(Blocks.RAILWAY_SIGN_7_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, 7);
        }
        if(blockBelow.isOf(com.lx.jsblock.Blocks.SIGN_POLE_ODD)) {
            return getDefaultState().with(FACING, IBlock.getStatePropertySafe(blockBelow, FACING)).with(LENGTH, IBlock.getStatePropertySafe(blockBelow, LENGTH));
        }
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LENGTH);
    }
}
