package com.lx.jsblock.blocks;

import com.lx.jsblock.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SignalLightRed1 extends mtr.block.BlockSignalLightBase {

    public SignalLightRed1(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntitySignalLightRed();
    }

    public static class TileEntitySignalLightRed extends BlockEntity {

        public TileEntitySignalLightRed() {
            super(Blocks.SIGNAL_LIGHT_RED_ENTITY_1);
        }
    }
}
