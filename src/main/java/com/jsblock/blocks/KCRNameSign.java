package com.jsblock.blocks;

import com.jsblock.Joestu;
import minecraftmappings.BlockEntityMapper;
import minecraftmappings.BlockEntityProviderMapper;
import mtr.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class KCRNameSign extends HorizontalFacingBlock implements BlockEntityProviderMapper {

    public static final BooleanProperty EXIT_ON_LEFT = BooleanProperty.of("exit_on_left");

    public KCRNameSign(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
           world.setBlockState(pos, state.cycle(EXIT_ON_LEFT));
        });
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing()).with(EXIT_ON_LEFT, false);
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return Joestu.KCR_NAME_SIGN_TILE_ENTITY;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(-7, 0, 5, 23, 14, 9.01, facing);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, EXIT_ON_LEFT);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityKCRNameSign(pos, state);
    }

    public static class TileEntityKCRNameSign extends BlockEntityMapper {

        public TileEntityKCRNameSign(BlockPos pos, BlockState state) {
            super(Joestu.KCR_NAME_SIGN_TILE_ENTITY, pos, state);
        }
    }
}