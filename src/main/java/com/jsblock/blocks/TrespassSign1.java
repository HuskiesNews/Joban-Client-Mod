package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class TrespassSign1 extends HorizontalFacingBlock {
    public static final BooleanProperty LEFT = BooleanProperty.of("left");

    public TrespassSign1(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        Direction directionRotated = direction.rotateYClockwise();
        if ((IBlock.getStatePropertySafe(state, FACING) == directionRotated || IBlock.getStatePropertySafe(state, FACING) == directionRotated.getOpposite()) && !newState.isOf(this)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return state;
        }
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
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        final Direction direction = ctx.getPlayerFacing().getOpposite();
        return IBlock.isReplaceable(ctx, direction.rotateYCounterclockwise(), 2) ? getDefaultState().with(FACING, direction).with(LEFT, true) : null;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        if (facing == Direction.NORTH || facing == Direction.EAST) {
            IBlock.onBreakCreative(world, player, pos.offset(facing.rotateYCounterclockwise()));
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            final Direction direction = IBlock.getStatePropertySafe(state, FACING);
            world.setBlockState(pos.offset(direction.rotateYCounterclockwise()), getDefaultState().with(FACING, direction).with(LEFT, false), 3);
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, 3);
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT);
    }
}
