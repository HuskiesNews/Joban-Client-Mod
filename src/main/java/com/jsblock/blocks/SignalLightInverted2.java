package com.jsblock.blocks;

import com.jsblock.Joestu;
import minecraftmappings.BlockEntityMapper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

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
            super(Joestu.SIGNAL_LIGHT_INVERTED_ENTITY_2, pos, state);
        }
    }
}