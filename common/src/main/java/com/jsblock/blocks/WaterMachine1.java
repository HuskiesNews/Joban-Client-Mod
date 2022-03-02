package com.jsblock.blocks;

import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaterMachine1 extends mtr.block.BlockDirectionalDoubleBlockBase {
	public WaterMachine1(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		return IBlock.getVoxelShapeByDirection(2.5, 0, 0, 13.5, 16, 11, facing);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
		/* Loop through player's hand, as on MC 1.9+ player can use both the left hand and the right hand */
		for (ItemStack itm : player.getHandSlots()) {
			/* If player is holding glass bottle */
			if (itm.getItem().asItem().equals(Items.GLASS_BOTTLE)) {
				ItemStack playerHolding = player.getItemInHand(hand);
				ItemStack waterBottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
				/* Remove 1 water bottle from player */
				playerHolding.shrink(1);

				/* If player is not holding anything after removing 1 water bottle, give the water bottle directly to their hand */
				if (playerHolding.isEmpty()) {
					player.setItemInHand(hand, waterBottle);
				} else {
					/* Otherwise, just give them the water bottle and let Minecraft sorts it out */
					player.addItem(waterBottle);
				}
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}
}
