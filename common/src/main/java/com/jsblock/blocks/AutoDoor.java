package com.jsblock.blocks;

import mtr.mappings.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;

public class AutoDoor extends DoorBlock {

	public AutoDoor(Properties settings) {
		super(settings);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
		world.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
		Utilities.scheduleBlockTick(world, pos, state.getBlock(), 0);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
		/* FAILSAFE */
		if (!world.getBlockTicks().hasScheduledTick(pos, state.getBlock())) {
			Utilities.scheduleBlockTick(world, pos, state.getBlock(), 15);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
		if (world == null || world.isClientSide) {
			return;
		}

		AABB box = new AABB(new BlockPos(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3), new BlockPos(pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3));
		List<Player> playerList = world.getEntitiesOfClass(Player.class, box);

		if (playerList.size() > 0) {
			if (!state.getValue(OPEN)) {
				world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1, 1);
			}
			world.setBlock(pos, state.setValue(OPEN, true), 10);
		} else {
			if (state.getValue(OPEN)) {
				world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1, 1);
			}
			world.setBlock(pos, state.setValue(OPEN, false), 10);
		}

		Utilities.scheduleBlockTick(world, pos, state.getBlock(), 15);
	}

	@Override
	public boolean isRandomlyTicking(BlockState blockState) {
		return true;
	}
}