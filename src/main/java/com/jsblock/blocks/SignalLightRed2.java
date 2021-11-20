package com.jsblock.blocks;

import com.jsblock.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SignalLightRed2 extends mtr.block.BlockSignalLightBase {

    public SignalLightRed2(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntitySignalLightRed2();
    }

    public static class TileEntitySignalLightRed2 extends BlockEntity {

        public TileEntitySignalLightRed2() {
            super(Blocks.SIGNAL_LIGHT_RED_ENTITY_2);
        }
    }
}