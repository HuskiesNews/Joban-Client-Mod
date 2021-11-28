package com.jsblock.blocks;
import mtr.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class WaterMachine1 extends mtr.block.BlockDirectionalDoubleBlockBase {
    public WaterMachine1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(2.5, 0, 0, 13.5, 16, 11, facing);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        /* Loop through player's hand, as on 1.9+ player can use both the left hand and the right hand */
        for(ItemStack itm : player.getItemsHand()) {
            if(itm.getItem().asItem().equals(Items.GLASS_BOTTLE)) {
                ItemStack playerHolding = player.getStackInHand(hand);
                ItemStack waterBottle = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                /* Remove 1 water bottle from player */
                playerHolding.decrement(1);

                /* If player is not holding anything, give the water bottle directly to their hand */
                if(playerHolding.isEmpty()) {
                    player.setStackInHand(hand, waterBottle);
                } else {
                    /* Otherwise, just give them the water bottle and let Minecraft sorts it out */
                    player.giveItemStack(waterBottle);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
}
