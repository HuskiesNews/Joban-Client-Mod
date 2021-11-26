package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public abstract class HorizontalMultiBlockBase extends HorizontalFacingBlock {
    public static final BooleanProperty LEFT = BooleanProperty.of("left");

    public HorizontalMultiBlockBase(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        /* This gets the direction of the block is facing, then rotate 90 degree *Counter* Clockwise if this is the left part. Otherwise rotate 90 degree Clockwise if this is the right part */
        /* The result is that if it's the left part, it will check for right. If it's right part, check for the left. */
        final Direction facing = IBlock.getStatePropertySafe(state, LEFT) ? IBlock.getStatePropertySafe(state, FACING).rotateYCounterclockwise() : IBlock.getStatePropertySafe(state, FACING).rotateYClockwise();

        /* If the direction of the block update is issued in the same direction as the "facing", and the block is not ours: Destroy the block */
        /* (facing == direction should only be true if another part of this block is placed) */
        if(facing == direction && !newState.isOf(this)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return state;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        final Direction direction = ctx.getPlayerFacing().getOpposite();
        return IBlock.isReplaceable(ctx, direction.rotateYCounterclockwise(), 2) ? getDefaultState().with(FACING, direction).with(LEFT, true) : null;
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
}