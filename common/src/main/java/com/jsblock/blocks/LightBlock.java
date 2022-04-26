package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.EntityBlockMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LightBlock extends Block implements EntityBlockMapper {
	public LightBlock(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		return Shapes.box(0f, 0f, 0f, 1f, 1f, 1f);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext collisionContext) {
		return Shapes.empty();
	}

	@Override
	public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return true;
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new TileEntityLightBlock(blockPos, blockState);
	}


	public static class TileEntityLightBlock extends BlockEntityMapper {
		public TileEntityLightBlock(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.LIGHT_BLOCK_TILE_ENTITY.get(), pos, state);
		}
	}
}
