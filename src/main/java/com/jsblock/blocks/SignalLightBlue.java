package com.jsblock.blocks;

import com.jsblock.Joestu;
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
            super(Joestu.SIGNAL_LIGHT_BLUE_ENTITY, pos, state);
        }
    }
}