package com.jsblock.blocks;

import com.jsblock.Blocks;
import mapper.BlockEntityMapper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class SignalLightInverted2 extends mtr.block.BlockSignalLightBase {

    public SignalLightInverted2(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySignalLightInverted(pos, state);
    }

    public static class TileEntitySignalLightInverted extends BlockEntityMapper {

        public TileEntitySignalLightInverted(BlockPos pos, BlockState state) {
            super(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_2, pos, state);
        }
    }
}