package com.jsblock.blocks;

import com.jsblock.Blocks;
import mapper.BlockEntityMapper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SignalLightRed2 extends mtr.block.BlockSignalLightBase {

    public SignalLightRed2(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySignalLightRed2(pos, state);
    }

    public static class TileEntitySignalLightRed2 extends BlockEntityMapper {

        public TileEntitySignalLightRed2(BlockPos pos, BlockState state) {
            super(Blocks.SIGNAL_LIGHT_RED_ENTITY_2, pos, state);
        }
    }
}