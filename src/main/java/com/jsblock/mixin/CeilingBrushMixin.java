package com.jsblock.mixin;

import mtr.block.BlockCeilingAuto;
import mtr.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

import static mtr.block.BlockCeilingAuto.LIGHT;

/* This mixin disables the "enforced" ceiling light pattern, and allows for manual changes via brush */
@Mixin(BlockCeilingAuto.class)
public class CeilingBrushMixin extends Block {
    public CeilingBrushMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            /* This cycles the LIGHT property (boolean), which toggles between true and false*/
            world.setBlockState(pos, state.cycle(LIGHT));
        });
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        /* Return the original state, this makes it possible to stack ceiling next to each other while having a custom lighting configuration */
        return state;
    }
}
