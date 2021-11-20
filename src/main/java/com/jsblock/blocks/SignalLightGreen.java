package com.jsblock.blocks;

import com.jsblock.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SignalLightGreen extends mtr.block.BlockSignalLightBase {

    public SignalLightGreen(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntitySignalLightGreen();
    }

    public static class TileEntitySignalLightGreen extends BlockEntity {

        public TileEntitySignalLightGreen() {
            super(Blocks.SIGNAL_LIGHT_GREEN_ENTITY);
        }
    }
}