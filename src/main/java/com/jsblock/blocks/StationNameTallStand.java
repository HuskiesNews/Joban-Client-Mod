package com.jsblock.blocks;

import com.jsblock.Blocks;
import mtr.block.IBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class StationNameTallStand extends mtr.block.BlockStationNameTallBase {

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Pair<Integer, Integer> bounds = getBounds(state);
        return IBlock.getVoxelShapeByDirection(1, bounds.getLeft(), 0, 15, bounds.getRight(), 0.5, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        final Direction blockSide = ctx.getSide();
        final Direction facing = blockSide == Direction.UP || blockSide == Direction.DOWN ? ctx.getPlayerFacing() : blockSide.getOpposite();
        return IBlock.isReplaceable(ctx, Direction.UP, 3) ? getDefaultState().with(FACING, facing).with(METAL, true).with(THIRD, EnumThird.LOWER) : null;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntityStationNameTallStand();
    }

    public static class TileEntityStationNameTallStand extends mtr.block.BlockStationNameTallWall.TileEntityStationNameTallBase {
        public TileEntityStationNameTallStand() {
            super(Blocks.STATION_NAME_TALL_STAND_TILE_ENTITY, 0.04025F);
        }
    }

    public static Pair<Integer, Integer> getBounds(BlockState state) {
        final EnumThird third = IBlock.getStatePropertySafe(state, THIRD);
        final int start, end;
        switch (third) {
            case LOWER:
                start = 0;
                end = 16;
                break;
            case UPPER:
                start = 0;
                end = 6;
                break;
            default:
                start = 0;
                end = 16;
                break;
        }
        return new Pair<>(start, end);
    }
}
