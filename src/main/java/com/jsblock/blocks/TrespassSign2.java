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
import net.minecraft.world.BlockView;

public class TrespassSign2 extends HorizontalMultiBlockBase {
    public static final BooleanProperty LEFT = BooleanProperty.of("left");

    public TrespassSign2(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        if(IBlock.getStatePropertySafe(state, LEFT)) {
            return IBlock.getVoxelShapeByDirection(0, 2, 15.9, 12, 14, 16, facing);
        } else {
            return IBlock.getVoxelShapeByDirection(4, 2, 15.9, 16, 14, 16, facing);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT);
    }
}
