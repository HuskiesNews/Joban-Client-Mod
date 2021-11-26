package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.block.*;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class TrespassSign2 extends HorizontalFacingBlock {
    public static final BooleanProperty LEFT = BooleanProperty.of("left");

    public TrespassSign2(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        final Direction facing = IBlock.getStatePropertySafe(state, LEFT) ? IBlock.getStatePropertySafe(state, FACING).rotateYCounterclockwise() : IBlock.getStatePropertySafe(state, FACING).rotateYClockwise();
        if(facing == direction && !newState.isOf(this)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return state;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        if(IBlock.getStatePropertySafe(state, LEFT)) {
            return IBlock.getVoxelShapeByDirection(0, 0, 15.9, 12, 12, 16, facing);
        } else {
            return IBlock.getVoxelShapeByDirection(4, 0, 15.9, 16, 12, 16, facing);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        final Direction direction = ctx.getPlayerFacing().getOpposite();
        return IBlock.isReplaceable(ctx, direction.rotateYCounterclockwise(), 2) ? getDefaultState().with(FACING, direction).with(LEFT, true) : null;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
//        if (!IBlock.getStatePropertySafe(state, LEFT)) {
//            IBlock.onBreakCreative(world, player, pos.offset(facing.rotateYClockwise(), 1));
//        } else {
//            IBlock.onBreakCreative(world, player, pos.offset(facing.rotateYCounterclockwise(), 1));
//        }
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
