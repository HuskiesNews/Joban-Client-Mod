package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TrespassSign1 extends HorizontalMultiBlockBase {

    public TrespassSign1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        if (IBlock.getStatePropertySafe(state, LEFT)) {
            VoxelShape vx1 = IBlock.getVoxelShapeByDirection(0, 4, 7.95, 14, 16, 9.05, facing);
            VoxelShape vx2 = IBlock.getVoxelShapeByDirection(8, 0, 8, 9, 7, 9, facing);
            return VoxelShapes.union(vx1, vx2);
        } else {
            VoxelShape vx1 = IBlock.getVoxelShapeByDirection(2, 4, 7.95, 16, 16, 9.05, facing);
            VoxelShape vx2 = IBlock.getVoxelShapeByDirection(7, 0, 8, 8, 7, 9, facing);
            return VoxelShapes.union(vx1, vx2);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT);
    }
}
