package com.jsblock.blocks;

import minecraftmappings.Utilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;


public class AutoDoor extends DoorBlock {

    public AutoDoor(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 3);
        Utilities.scheduleBlockTick(world, pos, state.getBlock(), 0);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        /* FAILSAFE */
        if(!Utilities.isScheduled(world, pos, state.getBlock())) {
            Utilities.scheduleBlockTick(world, pos, state.getBlock(), 15);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world == null || world.isClient()) return;

        Box box = new Box(new BlockPos(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3), new BlockPos(pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3));
        List<PlayerEntity> playerList = world.getNonSpectatingEntities(PlayerEntity.class, box);

        if (playerList.size() > 0) {
            if (!state.get(OPEN)) {
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1, 1);
            }
            world.setBlockState(pos, state.with(OPEN, true), 10);
        } else {
            if (state.get(OPEN)) {
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1, 1);
            }
            world.setBlockState(pos, state.with(OPEN, false), 10);
        }

        Utilities.scheduleBlockTick(world, pos, state.getBlock(), 15);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
}