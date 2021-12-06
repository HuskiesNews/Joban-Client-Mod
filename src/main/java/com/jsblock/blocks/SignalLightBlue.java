package com.jsblock.blocks;

import com.jsblock.Blocks;
import minecraftmappings.BlockEntityMapper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SignalLightBlue extends mtr.block.BlockSignalLightBase {

    public SignalLightBlue(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySignalLightBlue(pos, state);
    }

    public static class TileEntitySignalLightBlue extends BlockEntityMapper {

        public TileEntitySignalLightBlue(BlockPos pos, BlockState state) {
            super(Blocks.SIGNAL_LIGHT_BLUE_ENTITY, pos, state);
        }
    }
}