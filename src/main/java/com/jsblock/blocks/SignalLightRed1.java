package com.jsblock.blocks;

import com.jsblock.Blocks;
import mapper.BlockEntityMapper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SignalLightRed1 extends mtr.block.BlockSignalLightBase {

    public SignalLightRed1(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySignalLightRed(pos, state);
    }

    public static class TileEntitySignalLightRed extends BlockEntityMapper {

        public TileEntitySignalLightRed(BlockPos pos, BlockState state) {
            super(Blocks.SIGNAL_LIGHT_RED_ENTITY_1, pos, state);
        }
    }
}
