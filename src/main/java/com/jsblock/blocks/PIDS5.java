package com.jsblock.blocks;

import com.jsblock.Joestu;
import mtr.block.BlockPIDSBase;
import mtr.block.IBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;


public class PIDS5 extends BlockPIDSBase {

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape1 = IBlock.getVoxelShapeByDirection(5.9, -3.0, 0, 10.1, 11, 12, IBlock.getStatePropertySafe(state, FACING));
        VoxelShape shape2 = IBlock.getVoxelShapeByDirection(7.5, 11, 8.5, 8.5, 16, 9.5, IBlock.getStatePropertySafe(state, FACING));
        return VoxelShapes.union(shape1, shape2);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityBlockPIDS5(pos, state);
    }

    public static class TileEntityBlockPIDS5 extends TileEntityBlockPIDSBase {

        public static final int MAX_ARRIVALS = 4;

        public TileEntityBlockPIDS5(BlockPos pos, BlockState state) {
            super(Joestu.PIDS_5_TILE_ENTITY, pos, state);
        }

        @Override
        protected int getMaxArrivals() {
            return MAX_ARRIVALS;
        }
    }
}